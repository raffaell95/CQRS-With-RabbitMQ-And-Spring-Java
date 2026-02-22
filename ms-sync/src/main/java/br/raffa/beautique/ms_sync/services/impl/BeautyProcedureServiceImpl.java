package br.raffa.beautique.ms_sync.services.impl;

import br.raffa.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.raffa.beautique.ms_sync.repositories.BeautyProcedureRepository;
import br.raffa.beautique.ms_sync.services.BeautyProcedureService;
import br.raffa.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class BeautyProcedureServiceImpl implements BeautyProcedureService {

    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;

    @Override
    public void saveBeautyProcedure(BeautyProcedureDTO beautyProcedure) {
        try{
            SyncLogger.error("Error saving beauty procedure: " + beautyProcedure.getId());
            beautyProcedureRepository.save(beautyProcedure);
        }catch (Exception e){
            SyncLogger.error("Error  saving beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
