package raffa.com.beautique.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raffa.com.beautique.api.dtos.BeautyProcedureDTO;
import raffa.com.beautique.api.entities.BeautyProceduresEntity;
import raffa.com.beautique.api.repositories.BeautyProcedureRepository;
import raffa.com.beautique.api.services.BeautyProcedureService;
import raffa.com.beautique.api.services.BrockerService;
import raffa.com.beautique.api.utils.ConverterUtil;

import java.util.Optional;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Autowired
    private BrockerService brockerService;

    private final ConverterUtil<BeautyProceduresEntity, BeautyProcedureDTO> converterUtil = new ConverterUtil<>(BeautyProceduresEntity.class, BeautyProcedureDTO.class);

    @Override
    public BeautyProcedureDTO create(BeautyProcedureDTO beautyProcedureDTO) {

        BeautyProceduresEntity beautyProceduresEntity = converterUtil.convertToSource(beautyProcedureDTO);
        BeautyProceduresEntity newBeautyProcedureEntity = beautyProcedureRepository.save(beautyProceduresEntity);
        sendBeautyProceduresToQueue(newBeautyProcedureEntity);
        return converterUtil.convertToTarget(newBeautyProcedureEntity);
    }

    @Override
    public void delete(Long id) {
        Optional<BeautyProceduresEntity> beautyProceduresEntityOptional = beautyProcedureRepository.findById(id);
        if(beautyProceduresEntityOptional.isEmpty()){
            throw new RuntimeException("Beauty Procedure not found");
        }
        beautyProcedureRepository.deleteById(id);
    }

    @Override
    public BeautyProcedureDTO update(BeautyProcedureDTO beautyProcedureDTO) {
        Optional<BeautyProceduresEntity> beautyProceduresEntityOptional = beautyProcedureRepository.findById(beautyProcedureDTO.getId());
        if(beautyProceduresEntityOptional.isEmpty()){
            throw new RuntimeException("Beauty Procedure not found");
        }
        BeautyProceduresEntity beautyProceduresEntity = converterUtil.convertToSource(beautyProcedureDTO);
        beautyProceduresEntity.setAppointments(beautyProceduresEntityOptional.get().getAppointments());
        beautyProceduresEntity.setCreatedAt(beautyProceduresEntityOptional.get().getCreatedAt());

        BeautyProceduresEntity updatedBeautyProcedureEntity = beautyProcedureRepository.save(beautyProceduresEntity);

        sendBeautyProceduresToQueue(updatedBeautyProcedureEntity);

        return converterUtil.convertToTarget(updatedBeautyProcedureEntity);
    }

    private void sendBeautyProceduresToQueue(BeautyProceduresEntity beautyProceduresEntity){
        BeautyProcedureDTO beautyProcedureDTO = BeautyProcedureDTO.builder()
                .id(beautyProceduresEntity.getId())
                .name(beautyProceduresEntity.getName())
                .description(beautyProceduresEntity.getDescription())
                .price(beautyProceduresEntity.getPrice())
                .build();

        brockerService.send("beautyProcedures", beautyProcedureDTO);
    }
}
