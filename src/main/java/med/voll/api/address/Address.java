package med.voll.api.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.util.Validation;

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
        if (!Validation.isNullOrEmpty(data.logradouro())) {
            this.logradouro = data.logradouro();
        }
        if (!Validation.isNullOrEmpty(data.bairro())) {
            this.bairro = data.bairro();
        }
        if (!Validation.isNullOrEmpty(data.cep())) {
            this.cep = data.cep();
        }
        if (!Validation.isNullOrEmpty(data.cidade())) {
            this.cidade = data.cidade();
        }
        if (!Validation.isNullOrEmpty(data.uf())) {
            this.uf = data.uf();
        }
        if (!Validation.isNullOrEmpty(data.numero())) {
            this.numero = data.numero();
        }
        if (!Validation.isNullOrEmpty(data.complemento())) {
            this.complemento = data.complemento();
        }
    }
}
