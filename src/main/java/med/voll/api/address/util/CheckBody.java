package med.voll.api.address.util;

public class CheckBody {

    public static boolean isNullOrEmpty(String string) {
        return string == null || string.trim().isEmpty();
    }
}
