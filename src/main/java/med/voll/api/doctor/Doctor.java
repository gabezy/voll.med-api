package med.voll.api.doctor;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.address.Address;
import med.voll.api.address.util.CheckBody;

@Entity
@Table(name = "doctors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String phone;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String crm;

    @Enumerated(EnumType.STRING)
    private Specialty specialty;

    @Embedded
    private Address address;

    public Doctor(RegisterDoctorDto data) {
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
    }

    public void update(UpdateDoctorDto data) {
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
