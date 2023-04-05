package med.voll.api.appointment;

import jakarta.persistence.AttributeConverter;
import lombok.SneakyThrows;

public class CancellationReasonConvert implements AttributeConverter<CancellationReason, String> {
    @Override
    public String convertToDatabaseColumn(CancellationReason cancellationReason) {
        return cancellationReason.getMeaning();
    }

    @SneakyThrows
    @Override
    public CancellationReason convertToEntityAttribute(String s) {
        if (s == null) {
            return  null;
        }
        for (CancellationReason reason : CancellationReason.values()) {
            if(reason.getMeaning().equalsIgnoreCase(s)) {
                return reason;
            }
        }
        throw new IllegalAccessException("Unknown CancellationReason meaning: " + s);
    }
}
