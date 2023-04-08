package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotBlank;

public record RegisterAppointmentDto(
        @NotBlank
        String patientId,
        String doctorId,
        @NotBlank
        String date
) {
}
