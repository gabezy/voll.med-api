package med.voll.api.appointment;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import med.voll.api.doctor.Doctor;
import med.voll.api.patient.Patient;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "appointments")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Appointment {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    private Patient patient;

    @ManyToOne
    private Doctor doctor;

    @NotNull
    private LocalDateTime date;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();


    public Appointment(Patient patient, Doctor doctor ,String date ) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = LocalDateTime.parse(date);
    }

}
