package med.voll.api.domain.patient;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.address.Address;
import med.voll.api.util.RequestFiledValidationUtil;

@Entity
@Table(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Patient {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String phone;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String cpf;

    @Embedded
    private Address address;

    private boolean active;

    public Patient(RegisterPatientDto data) {
        this.active = true;
        name = data.name();
        email = data.email();
        phone = data.phone();
        cpf = data.cpf();
        address = new Address(data.address());
    }

    public void update(UpdatePatientDto data) {
        if (!RequestFiledValidationUtil.isNullOrEmpty(data.name())) {
            this.name = data.name();
        }
        if (!RequestFiledValidationUtil.isNullOrEmpty(data.phone())) {
            this.phone = data.phone();
        }
        if (data.address() != null) {
            this.address.update(data.address());
        }
    }

    public void delete() {
        this.active = false;
    }

    public void activate() {
        this.active = true;
    }

}
