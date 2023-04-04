package med.voll.api.patient;

import med.voll.api.address.UpdateAddressDto;

public record UpdatePatientDto(
        String id,
        String name,
        String phone,
        UpdateAddressDto address) {
}
