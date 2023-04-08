package med.voll.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.domain.address.UpdateAddressDto;

public record UpdateDoctorDto(
        @NotBlank
        String id,
        String name,
        String phone,
        @Valid
        UpdateAddressDto address) {}
