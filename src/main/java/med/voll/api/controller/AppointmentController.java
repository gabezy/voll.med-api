package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.*;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.infra.exception.InvalidAppointmentDateException;
import med.voll.api.infra.exception.PatientBookTwoAppointmentsInTheSameDayException;
import med.voll.api.util.DateTimeUtil;
import med.voll.api.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

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
    public ResponseEntity<DetailAppointmentDto> register(@RequestBody @Valid RegisterAppointmentDto data, UriComponentsBuilder builder) {
        //  TODO: create URI for the new appointment and return the appointment created in the response body

        Doctor doctor = null;
        LocalDateTime appointmentDateTime = LocalDateTime.parse(data.date());
        if (!DateTimeUtil.isAppointmentDateValid(appointmentDateTime)) {
            throw new InvalidAppointmentDateException();
        }

        if (ValidationUtil.isNullOrEmpty(data.doctorId())) {
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
        } else {
            doctor = doctorRepository.getReferenceById(data.doctorId());

            List<Appointment> doctorAppointments = repository.findAppointmentByDoctorId(data.doctorId());
            if (DateTimeUtil.doctorHasAppointmentInTheTime(doctorAppointments, appointmentDateTime)) {
                throw new InvalidAppointmentDateException();
            }

        }
        var patient = patientRepository.getReferenceById(data.patientId());
        List<Appointment> patientAppointments = repository.findAppointmentByPatientId(data.patientId());
        boolean patientHasAppointmentInTheSameDay = DateTimeUtil.patientHasAppointmentInTheSameDay(patientAppointments,
                appointmentDateTime);

        if (patientHasAppointmentInTheSameDay) {
            throw new PatientBookTwoAppointmentsInTheSameDayException();
        }
        var appointment = new Appointment(patient, doctor, data.date());
        repository.save(appointment);
        URI uri = builder.path("appointments/{id}").buildAndExpand(appointment.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailAppointmentDto(appointment));
    }

    @GetMapping
    public Page<CancellationAppointmentDto> list(@PageableDefault(size = 20)Pageable pageable) {
        return repository.findAppointmentCancellationReasonNull(pageable).map(CancellationAppointmentDto::new);
    }

    @PutMapping //TODO: Change the HTPP method to PATCH
    @Transactional
    public ResponseEntity<String> cancel(@RequestBody CancellationAppointmentDto data) {
        var appointment = repository.getReferenceById(data.id());
        if (!DateTimeUtil.isCancellationRequestDateAtLeast24HourEarlierThanTheAppointment(appointment.getDate())) {
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("the cancellation of an appointment can only be made at least 24 hours before the appointment");
        }

        return ResponseEntity.status(HttpStatus.ACCEPTED).body("");
    }



}
