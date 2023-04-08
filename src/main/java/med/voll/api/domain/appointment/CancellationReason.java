package med.voll.api.domain.appointment;

public enum CancellationReason {
    CANCELLED_BY_PATIENT_1(1, "Cancelled by patient"),
    CANCELLED_BY_PATIENT_2(2, "Cancelled by patient"),
    OTHERS(3, "Others");

    private int code;
    private String meaning;

    CancellationReason(int code, String meaning) {
        this.code = code;
        this.meaning = meaning;
    }

    public int getCode() {
        return code;
    }

    public String getMeaning()   {
        return meaning;
    }
}
