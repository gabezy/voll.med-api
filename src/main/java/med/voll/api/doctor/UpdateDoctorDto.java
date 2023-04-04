package med.voll.api.patient;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.address.AddressDto;

public record UpdateDoctorDto(
        @NotBlank
        String id,
        String name,
        String phone,
        @Valid
        AddressDto address) {}
