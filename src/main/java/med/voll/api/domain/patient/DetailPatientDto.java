package med.voll.api.domain.patient;

import med.voll.api.domain.address.Address;

public record DetailPatientDto(
        String id, String name, String phone, String email,
        String cpf, Address address, boolean active
) {
    public DetailPatientDto(Patient patient) {
        this(
                patient.getId(), patient.getName(), patient.getPhone(), patient.getEmail(), patient.getCpf(),
                patient.getAddress(), patient.isActive()
        );
    }

}
