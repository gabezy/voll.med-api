package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.doctor.*;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;
    @PostMapping
    @Transactional
    public ResponseEntity<DetailDoctorDto> register(@RequestBody @Valid RegisterDoctorDto data, UriComponentsBuilder uriComponentsBuilder) {
        var doctor = new Doctor(data);
        repository.save(doctor);
        URI uri = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailDoctorDto(doctor));
    }

    @GetMapping
    // by default the spring return 20 elements per page
    // params -> ?size=1 (elements) ?page=1 (page 1) | ?sort=atrr,(desc or asc)
    public ResponseEntity<Page<ListDoctorDto>>  list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<ListDoctorDto> page =  repository.findAllByActiveTrue(pageable).map(ListDoctorDto::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailDoctorDto> detail(@PathVariable String id) {
        var doctor = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailDoctorDto(doctor));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DetailDoctorDto> update(@RequestBody @Valid UpdateDoctorDto data) {
        var doctor = this.repository.getReferenceById(data.id());
        doctor.update(data);
        return ResponseEntity.ok(new DetailDoctorDto(doctor));
    }

    @DeleteMapping("/{id}") //{param} => dynamic parameter
    @Transactional
    public ResponseEntity delete(@PathVariable String id) {
        var doctor = repository.getReferenceById(id);
        doctor.delete();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204
    }

    @PatchMapping("/{id}/activate")
    @Transactional
    public ResponseEntity<DetailDoctorDto> activate(@PathVariable String id) {
        var doctor = repository.getReferenceById(id);
        doctor.activate();
        return ResponseEntity.ok(new DetailDoctorDto(doctor));
    }

}
