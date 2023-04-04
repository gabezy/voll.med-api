package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.UpdateDoctorDto;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterPatientDto data) {
        this.repository.save(new Patient(data));
    }

    @GetMapping
    public Page<ListPatientDto> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return this.repository.findAll(pageable).map(ListPatientDto::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdatePatientDto data) {
        var patient = repository.getReferenceById(data.id());
        patient.update(data);
    }
}
