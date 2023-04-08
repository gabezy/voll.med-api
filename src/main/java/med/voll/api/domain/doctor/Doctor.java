package med.voll.api.domain.doctor;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.address.Address;
import med.voll.api.util.ValidationUtil;

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

    private boolean active;

    public Doctor(RegisterDoctorDto data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
    }

    public void update(UpdateDoctorDto data) {
        if (!ValidationUtil.isNullOrEmpty(data.name())) {
            this.name = data.name();
        }
        if (!ValidationUtil.isNullOrEmpty(data.phone())) {
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
