package med.voll.api.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UpdateAddressDto(
        String logradouro,
        String bairro,
        @Pattern(regexp = "\\d{8}")
        String cep,
        String cidade,
        String uf,
        String numero,
        String complemento
    ) {
}
