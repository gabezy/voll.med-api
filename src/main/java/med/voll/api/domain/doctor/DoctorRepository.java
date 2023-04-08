package med.voll.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, String> {
    Page<Doctor> findAllByActiveTrue(Pageable pageable);
    @Query("SELECT d FROM Doctor d WHERE d.id NOT IN :idList")
    List<Doctor>  findDoctorNotInIdList(@Param("idList") List<String> idList);
}
