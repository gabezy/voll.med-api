package med.voll.api.domain.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.address.AddressDto;

public record RegisterPatientDto(
        @NotBlank
        String name,

        @NotBlank @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank @Pattern(regexp = "\\d{11}")
        String cpf,

        @NotNull
        @Valid
        AddressDto address) {}
