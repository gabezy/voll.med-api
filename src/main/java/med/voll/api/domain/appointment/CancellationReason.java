package med.voll.api.domain.appointment;

public enum CancellationReason {
    CANCELLED_BY_PATIENT_1(1, "Cancelled by patient"),
    CANCELLED_BY_PATIENT_2(2, "Cancelled by patient"),
    OTHERS(3, "Others"),
    NULL(4, "");
    private final int code;
    private final String meaning;

    CancellationReason(int code, String meaning) {
        this.code = code;
        this.meaning = meaning;
    }

    public int getCode() {
        return code;
    }

    public String getMeaning() {
        return meaning;
    }
}
