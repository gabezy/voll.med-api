package med.voll.api.domain.appointment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CancellationAppointmentDto(
        @NotBlank
        String id,
        @NotNull
        String cancellationReason) {

    public CancellationAppointmentDto(Appointment appointment) {
        this(appointment.getId() , appointment.getCancellationReason());
    }
}
