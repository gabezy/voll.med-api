package med.voll.api.domain.patient;

public record ListPatientDto(
        String id,
        String name,
        String email,
        String cpf
) {
    public ListPatientDto(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf() );
    }
}
