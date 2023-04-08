package med.voll.api.domain.appointment;

import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;

public class CancellationReasonConvert {

    public static CancellationReason convertToEntityAttribute(String s) {
        if (s == null) {
            return  null;
        }
        for (CancellationReason reason : CancellationReason.values()) {
            if(CancellationReason.valueOf(s).equals(reason)) {
                return reason;
            }
        }
        throw new RuntimeException("Unknown CancellationReason meaning: " + s);
    }
}
