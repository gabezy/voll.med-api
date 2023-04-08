package med.voll.api.domain.user;

import jakarta.validation.constraints.NotBlank;

public record AuthenticateDto(
        @NotBlank
        String username,
        @NotBlank
        String password) {
}
