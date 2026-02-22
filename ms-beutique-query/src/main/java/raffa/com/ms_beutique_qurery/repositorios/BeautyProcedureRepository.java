package raffa.com.ms_beutique_qurery.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import raffa.com.ms_beutique_qurery.dtos.beautyprocedures.BeautyProcedureDTO;

import java.util.List;

public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {

    @Query("{'name': {$regex: ?0, $options: 'i'}}")
    List<BeautyProcedureDTO> findByNameIgnoreCase(String name);

    @Query("{'description': {$regex: ?0, $options: 'i'}}")
    List<BeautyProcedureDTO> findByEmailIgnoreCase(String description);
}
