package raffa.com.beautique.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raffa.com.beautique.api.entities.AppointmentsEntity;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentsEntity, Long> {
}
