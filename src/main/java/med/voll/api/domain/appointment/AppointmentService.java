package med.voll.api.domain.appointment;

import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.exception.AppointmentCancellationException;
import med.voll.api.infra.exception.InvalidAppointmentDateException;
import med.voll.api.infra.exception.PatientBookTwoAppointmentsInTheSameDayException;
import med.voll.api.util.DateTimeUtil;
import med.voll.api.util.RequestFiledValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public Appointment createAppointment(RegisterAppointmentDto appointment) {
        Doctor doctor;
        LocalDateTime appointmentDateTime = LocalDateTime.parse(appointment.date());
        if (!DateTimeUtil.isAppointmentDateValid(appointmentDateTime)) {
            throw new InvalidAppointmentDateException();
        }

        if (RequestFiledValidationUtil.isNullOrEmpty(appointment.doctorId())) {
            List<Doctor> avalibleDoctorList;
            List<Appointment> appointmentList = appointmentRepository.findAppointmentByDate(appointmentDateTime);
            if (appointmentList.size() > 0) {
                List<String> doctorIdList = appointmentList.stream().map(Appointment::getDoctor)
                        .toList().stream().map(Doctor::getId).toList();
                avalibleDoctorList = doctorRepository.findDoctorNotInIdList(doctorIdList);
            } else {
                avalibleDoctorList = doctorRepository.findAll();
            }

            Random random = new Random();
            int randomNumber = random.nextInt(avalibleDoctorList.size());
            doctor = avalibleDoctorList.get(randomNumber);
        } else {
            doctor = doctorRepository.getReferenceById(appointment.doctorId());

            List<Appointment> doctorAppointments = appointmentRepository.findAppointmentByDoctorId(appointment.doctorId());
            if (DateTimeUtil.doctorHasAppointmentInTheTime(doctorAppointments, appointmentDateTime)) {
                throw new InvalidAppointmentDateException();
            }
        }

        var patient = patientRepository.getReferenceById(appointment.patientId());
        List<Appointment> patientAppointments = appointmentRepository.findAppointmentByPatientId(appointment.patientId());
        boolean patientHasAppointmentInTheSameDay = DateTimeUtil.patientHasAppointmentInTheSameDay(patientAppointments,
                appointmentDateTime);

        if (patientHasAppointmentInTheSameDay) {
            throw new PatientBookTwoAppointmentsInTheSameDayException();
        }
        return new Appointment(patient, doctor, appointment.date());
    }

    public void cancelAppointment(Appointment appointment , String cancellationReasonString) {
        if(!DateTimeUtil.isCancellationRequestDateAtLeast24HourEarlierThanTheAppointment(appointment.getDate())) {
            throw new AppointmentCancellationException();
        }
        CancellationReason reason = CancellationReasonConvert.convertToEntityAttribute(cancellationReasonString);
        appointment.cancel(reason.getMeaning());
    }
}
