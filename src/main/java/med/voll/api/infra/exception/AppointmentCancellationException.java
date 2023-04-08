package med.voll.api.infra.exception;

public class AppointmentCancellationException extends RuntimeException{
    public AppointmentCancellationException() {
        super("the cancellation of an appointment can only be made at least 24 hours before the appointment");
    }
}
