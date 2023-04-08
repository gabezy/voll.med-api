package med.voll.api.domain.patient;

import med.voll.api.domain.address.UpdateAddressDto;

public record UpdatePatientDto(
        String id,
        String name,
        String phone,
        UpdateAddressDto address) {
}
