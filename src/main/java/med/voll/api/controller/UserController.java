package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.user.AuthenticateDto;
import med.voll.api.domain.user.DetailUserDto;
import med.voll.api.domain.user.User;
import med.voll.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;


    @Transactional
    @PostMapping("/register")
    public ResponseEntity<DetailUserDto> register(@RequestBody @Valid AuthenticateDto data, UriComponentsBuilder builder) {
        var user = new User(data);
        repository.save(user);
        URI uri = builder.path("/user/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailUserDto(user));
    }

}
