package med.voll.api.infra.exception;

public class UnauthorizedExpection extends RuntimeException {
    public UnauthorizedExpection() {
        super("Unauthorized");
    }
}
