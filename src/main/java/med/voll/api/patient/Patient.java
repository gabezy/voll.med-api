package med.voll.api.controller;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.address.Address;
import med.voll.api.patient.DataRegisterPatient;

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

    public Patient(DataRegisterPatient data) {
        name = data.name();
        email = data.email();
        phone = data.phone();
        cpf = data.cpf();
    }

}
