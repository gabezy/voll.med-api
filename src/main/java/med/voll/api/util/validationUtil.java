package med.voll.api.util;

import med.voll.api.domain.user.User;
import med.voll.api.domain.user.UserRole;
import med.voll.api.infra.exception.UnauthorizedExpection;

public class validationUtil {

    public static void verifyUserRole (User user, UserRole roleToVerify) {
        if (user.getRole() != roleToVerify) {
            throw new UnauthorizedExpection();
        }
    }
}
