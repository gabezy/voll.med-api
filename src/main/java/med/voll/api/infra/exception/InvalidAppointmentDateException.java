package med.voll.api.infra.exception;

public class InvalidAppointmentDateException extends RuntimeException{
    public InvalidAppointmentDateException() {
        super("Invalid date");
    }
}
