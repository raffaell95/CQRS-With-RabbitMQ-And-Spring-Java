package br.raffa.beautique.ms_sync.repositories;

import br.raffa.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BeautyProcedureRepository extends MongoRepository<BeautyProcedureDTO, Long> {
}
