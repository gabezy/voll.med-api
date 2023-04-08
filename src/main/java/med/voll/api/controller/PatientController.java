package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import med.voll.api.domain.patient.Patient;
import med.voll.api.domain.patient.PatientRepository;
import med.voll.api.domain.patient.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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
    public ResponseEntity<DetailPatientDto> register(@RequestBody @Valid RegisterPatientDto data, UriComponentsBuilder builder) {
        // TODO: return 201 HTTP Status code, create URI for the new patient
        //  and return the patient created in the response body
        var patient = new Patient(data);
        this.repository.save(patient);
        URI uri = builder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailPatientDto(patient));
    }

    @GetMapping
    public Page<ListPatientDto> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        System.out.println(LocalDateTime.now());
        return this.repository.findAllByActiveTrue(pageable).map(ListPatientDto::new);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DetailPatientDto> detail(@PathVariable String id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailPatientDto(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetailPatientDto> update(@RequestBody @Valid UpdatePatientDto data) {
        var patient = repository.getReferenceById(data.id());
        patient.update(data);
        return ResponseEntity.ok(new DetailPatientDto(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable String id) {
        var patient = repository.getReferenceById(id);
        patient.delete();
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/activate")
    @Transactional
    public ResponseEntity<DetailPatientDto> activate(@PathVariable String id) {
        var patient = repository.getReferenceById(id);
        patient.activate();
        return ResponseEntity.ok(new DetailPatientDto(patient));
    }

}
