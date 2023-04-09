package med.voll.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserDto(
        @NotBlank
        String username,
        @NotBlank
        String password,
        UserRole role
) {
}
