package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.appointment.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository repository;

    @Autowired
    AppointmentService service;


    @PostMapping
    @Transactional
    public ResponseEntity<DetailAppointmentDto> register(@RequestBody @Valid RegisterAppointmentDto data, UriComponentsBuilder builder) {
        var appointment = service.createAppointment(data);
        repository.save(appointment);
        URI uri = builder.path("appointments/{id}").buildAndExpand(appointment.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailAppointmentDto(appointment));
    }

    @GetMapping
    public Page<CancellationAppointmentDto> list(@PageableDefault(size = 20)Pageable pageable) {
        return repository.findAppointmentCancellationReasonNull(pageable).map(CancellationAppointmentDto::new);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Object> cancel(@RequestBody CancellationAppointmentDto data) {
        var appointment = repository.getReferenceById(data.id());
        service.cancelAppointment(appointment, data.cancellationReason());
        return ResponseEntity.ok().build();
    }



}
