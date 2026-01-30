package raffa.com.beautique.api.services;

import raffa.com.beautique.api.dtos.BeautyProcedureDTO;

public interface BeautyProcedureService {

    BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO);
    void delete(Long id);
    BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedureDTO);
}
