package raffa.com.beautique.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raffa.com.beautique.api.entities.BeautyProceduresEntity;

@Repository
public interface BeautyProcedureRepository extends JpaRepository<BeautyProceduresEntity, Long> {

}
