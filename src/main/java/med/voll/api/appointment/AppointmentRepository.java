package med.voll.api.appointment;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findAppointmentByPatientId(String id);
    List<Appointment> findAppointmentByDoctorId(String id);

    List<Appointment> findAppointmentByDate(LocalDateTime dateTime);

    @Query("SELECT a FROM Appointment a WHERE a.cancellationReason IS NULL")
    Page<Appointment> findAppointmentCancellationReasonNull(Pageable pageable);
}
