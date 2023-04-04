package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.doctor.ListDoctorDto;
import med.voll.api.doctor.RegisterDoctorDto;
import med.voll.api.doctor.Doctor;
import med.voll.api.doctor.DoctorRepository;
import med.voll.api.doctor.UpdateDoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;
    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid RegisterDoctorDto data) {
        repository.save(new Doctor(data));
    }

    @GetMapping
    // by default the spring return 20 elements per page
    // params -> ?size=1 (elements) ?page=1 (page 1) | ?sort=atrr,(desc or asc)
    public Page<ListDoctorDto> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        return repository.findAll(pageable).map(ListDoctorDto::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateDoctorDto data) {
        var doctor = this.repository.getReferenceById(data.id());
        doctor.update(data);
    }
}
