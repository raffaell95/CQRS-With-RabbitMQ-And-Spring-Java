package raffa.com.ms_beutique_qurery.services;

import raffa.com.ms_beutique_qurery.dtos.beautyprocedures.BeautyProcedureDTO;

import java.util.List;

public interface BeautyProcedureService {
    List<BeautyProcedureDTO> listAllBeautyProcedures();
    List<BeautyProcedureDTO> listByNameIgnoreCase(String name);
    List<BeautyProcedureDTO> listByDescriptionIgnoreCase(String description);
}
