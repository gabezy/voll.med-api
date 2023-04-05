package med.voll.api.appointment;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.doctor.Doctor;
import med.voll.api.patient.Patient;

import java.time.LocalDateTime;

public record RegisterAppointmentDto(
        @NotBlank
        String patientId,
        String doctorId,
        @NotBlank
        String date
) {
}
