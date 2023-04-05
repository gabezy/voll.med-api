package med.voll.api.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
    List<Appointment> findAppointmentByPatientId(String id);
    List<Appointment> findAppointmentByDoctorId(String id);

    List<Appointment> findAppointmentByDate(LocalDateTime dateTime);
}
