package med.voll.api.domain.doctor;

import med.voll.api.domain.address.Address;

public record DetailDoctorDto(
        String id, String name, String email,
        String crm, Specialty specialty, Address address, boolean active
) {

    public DetailDoctorDto(Doctor doctor) {
        this(
                doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(),
                doctor.getSpecialty(), doctor.getAddress(), doctor.isActive()
        );
    }

}
