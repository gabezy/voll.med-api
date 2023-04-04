package med.voll.api.doctor;

public record ListDoctorDto(
        String id,
        String name,
        String email,
        String crm,
        Specialty specialty
) {
    public ListDoctorDto(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
