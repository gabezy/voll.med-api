package med.voll.api.infra.exception;

public class PatientBookTwoAppointmentsInTheSameDayException extends RuntimeException{

    public PatientBookTwoAppointmentsInTheSameDayException() {
        super("Patient can't book two appointments in the same day");
    }

}
