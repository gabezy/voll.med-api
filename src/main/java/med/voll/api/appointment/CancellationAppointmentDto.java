package med.voll.api.appointment;

public record CancellationAppointmentDto(String id, CancellationReason cancellationReason) {

    public CancellationAppointmentDto(Appointment appointment) {
        this(appointment.getId() , appointment.getCancellationReason());
    }
}
