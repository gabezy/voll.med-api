package med.voll.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.util.RequestFiledValidationUtil;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String logradouro;
    private String bairro;
    private String cep;
    private String cidade;
    private String uf;
    private String numero;
    private String complemento;

    public Address(AddressDto data) {
        this.logradouro = data.logradouro();
        this.bairro = data.bairro();
        this.cep = data.cep();
        this.cidade = data.cidade();
        this.uf = data.uf();
        this.numero = data.numero();
        this.complemento = data.complemento();
    }

    public void update(UpdateAddressDto data) {
        if (!RequestFiledValidationUtil.isNullOrEmpty(data.logradouro())) {
            this.logradouro = data.logradouro();
        }
        if (!RequestFiledValidationUtil.isNullOrEmpty(data.bairro())) {
            this.bairro = data.bairro();
        }
        if (!RequestFiledValidationUtil.isNullOrEmpty(data.cep())) {
            this.cep = data.cep();
        }
        if (!RequestFiledValidationUtil.isNullOrEmpty(data.cidade())) {
            this.cidade = data.cidade();
        }
        if (!RequestFiledValidationUtil.isNullOrEmpty(data.uf())) {
            this.uf = data.uf();
        }
        if (!RequestFiledValidationUtil.isNullOrEmpty(data.numero())) {
            this.numero = data.numero();
        }
        if (!RequestFiledValidationUtil.isNullOrEmpty(data.complemento())) {
            this.complemento = data.complemento();
        }
    }
}
