package med.voll.api.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;
import med.voll.api.address.util.CheckBody;
import med.voll.api.doctor.UpdateDoctorDto;

@Entity
@Table(name = "patients")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
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

    public Patient(RegisterPatientDto data) {
        name = data.name();
        email = data.email();
        phone = data.phone();
        cpf = data.cpf();
        address = new Address(data.address());
    }

    public void update(UpdatePatientDto data) {
        if (!CheckBody.isNullOrEmpty(data.name())) {
            this.name = data.name();
        }
        if (!CheckBody.isNullOrEmpty(data.phone())) {
            this.phone = data.phone();
        }
        if (data.address() != null) {
            this.address.update(data.address());
        }
    }

}
