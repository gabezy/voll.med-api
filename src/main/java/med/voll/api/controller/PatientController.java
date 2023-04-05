package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import med.voll.api.doctor.UpdateDoctorDto;
import med.voll.api.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/patients")
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
        System.out.println(LocalDateTime.now());
        return this.repository.findAllByActiveTrue(pageable).map(ListPatientDto::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdatePatientDto data) {
        var patient = repository.getReferenceById(data.id());
        patient.update(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable String id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
    }

    @PatchMapping("/{id}/activate")
    @Transactional
    public void activate(@PathVariable String id) {
        var patient = repository.getReferenceById(id);
        patient.activate();
    }

}
