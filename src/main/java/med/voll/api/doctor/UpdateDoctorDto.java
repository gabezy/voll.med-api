package med.voll.api.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import med.voll.api.address.UpdateAddressDto;

public record UpdateDoctorDto(
        @NotBlank
        String id,
        String name,
        String phone,
        @Valid
        UpdateAddressDto address) {}
