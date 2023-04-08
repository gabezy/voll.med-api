package med.voll.api.domain.appointment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import med.voll.api.domain.doctor.Doctor;
import med.voll.api.domain.patient.Patient;

import java.time.LocalDateTime;

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

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Patient patient;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(fetch = FetchType.LAZY)
    private Doctor doctor;

    @NotNull
    private LocalDateTime date;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

//    @Convert(converter = CancellationReasonConvert.class)
    @Column(name = "cancellation_reason")
    private String cancellationReason;


    public Appointment(Patient patient, Doctor doctor ,String date ) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = LocalDateTime.parse(date);
        this.cancellationReason = null;
    }

    public void cancel(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

}
