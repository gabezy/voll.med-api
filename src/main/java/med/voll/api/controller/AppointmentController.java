package med.voll.api.controller;

import jakarta.transaction.Transactional;
import med.voll.api.appointment.Appointment;
import med.voll.api.appointment.AppointmentRepository;
import med.voll.api.appointment.RegisterAppointmentDto;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import med.voll.api.patient.PatientRepository;
import med.voll.api.util.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository repository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @PostMapping
    @Transactional
    // ResponseEntity<String>
    public ResponseEntity<String> register(@RequestBody RegisterAppointmentDto data) {
        Doctor doctor = null;
        LocalDateTime appointmentDateTime = LocalDateTime.parse(data.date());

        if (Validation.isNullOrEmpty(data.doctorId())) {
            List<Doctor> avalibleDoctorList = null;
            List<Appointment> appointmentList = repository.findAppointmentByDate(appointmentDateTime);
            if (appointmentList.size() > 0) {
                System.out.println(appointmentList);
                List<String> doctorIdList = appointmentList.stream().map(Appointment::getDoctor)
                        .toList().stream().map(Doctor::getId).toList();
                avalibleDoctorList = doctorRepository.findDoctorNotInIdList(doctorIdList);
                System.out.println(avalibleDoctorList);

            } else {
                avalibleDoctorList = doctorRepository.findAll();
            }

            Random random = new Random();
            int randomNumber = random.nextInt(avalibleDoctorList.size());
            doctor = avalibleDoctorList.get(randomNumber);
            System.out.println(doctor.getId());

        } else {
            doctor = doctorRepository.getReferenceById(data.doctorId());
            boolean doctorHasAppointmentInTheTime = false;

            List<Appointment> doctorAppointments = repository.findAppointmentByDoctorId(data.doctorId());
            if (Validation.doctorHasAppointmentInTheTime(doctorAppointments, appointmentDateTime)) {
                doctorHasAppointmentInTheTime = true;
            }

            if (doctorHasAppointmentInTheTime) return ResponseEntity.status(409).body("The doctor already has a appointment in this time");


        }
        var patient = patientRepository.getReferenceById(data.patientId());
        List<Appointment> patientAppointments = repository.findAppointmentByPatientId(data.patientId());
        boolean patientHasAppointmentInTheSameDay = Validation.patientHasAppointmentInTheSameDay(patientAppointments,
                appointmentDateTime);

        if (patientHasAppointmentInTheSameDay) return ResponseEntity.status(409).body("The same patient can't reserve the two or more appointments in this day");

        repository.save(new Appointment(patient, doctor, data.date()));
        return ResponseEntity.status(HttpStatus.CREATED).body("");
    }



}
