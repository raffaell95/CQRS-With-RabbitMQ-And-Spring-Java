package raffa.com.ms_beutique_qurery.services.Impl;

import org.springframework.stereotype.Service;
import raffa.com.ms_beutique_qurery.dtos.beautyprocedures.BeautyProcedureDTO;
import raffa.com.ms_beutique_qurery.repositorios.BeautyProcedureRepository;
import raffa.com.ms_beutique_qurery.services.BeautyProcedureService;

import java.util.List;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    private final BeautyProcedureRepository beautyProcedureRepository;

    public BeautyProcedureServiceImpl(BeautyProcedureRepository beautyProcedureRepository){
        this.beautyProcedureRepository = beautyProcedureRepository;
    }

    @Override
    public List<BeautyProcedureDTO> listAllBeautyProcedures() {
        try{
            return beautyProcedureRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Error listening all beauty procedures");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByNameIgnoreCase(String name) {
        try{
            return beautyProcedureRepository.findByNameIgnoreCase(name);
        }catch (Exception e){
            throw new RuntimeException("Error listing all beauty procedures by name");
        }
    }

    @Override
    public List<BeautyProcedureDTO> listByDescriptionIgnoreCase(String description) {
        try{
            return beautyProcedureRepository.findByEmailIgnoreCase(description);
        }catch (Exception e){
            throw new RuntimeException("Error listing all beauty procedures by description");
        }
    }

}
