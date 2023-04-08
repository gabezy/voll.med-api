package med.voll.api.domain.appointment;

import java.time.LocalDateTime;

public record DetailAppointmentDto(
        String id, String patientId, String doctorId,
        LocalDateTime date, String cancellationReason
) {
    public DetailAppointmentDto(Appointment appointment) {
        this(
                appointment.getId(), appointment.getPatient().getId(),
                appointment.getDoctor().getId(), appointment.getDate(), appointment.getCancellationReason()
        );
    }
}
