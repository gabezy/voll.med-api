package med.voll.api.domain.appointment;

public record CancellationAppointmentDto(String id, CancellationReason cancellationReason) {

    public CancellationAppointmentDto(Appointment appointment) {
        this(appointment.getId() , appointment.getCancellationReason());
    }
}
