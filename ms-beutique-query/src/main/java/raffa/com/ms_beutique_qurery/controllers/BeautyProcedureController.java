package raffa.com.ms_beutique_qurery.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import raffa.com.ms_beutique_qurery.dtos.beautyprocedures.BeautyProcedureDTO;
import raffa.com.ms_beutique_qurery.services.BeautyProcedureService;

import java.util.List;

@RestController
@RequestMapping("/beauty-procedure")
public class BeautyProcedureController {

    private final BeautyProcedureService beautyProcedureService;

    public BeautyProcedureController(BeautyProcedureService beautyProcedureService){
        this.beautyProcedureService = beautyProcedureService;
    }

    @GetMapping()
    ResponseEntity<List<BeautyProcedureDTO>> listAllBeautyProcedures(){
        return ResponseEntity.ok(beautyProcedureService.listAllBeautyProcedures());
    }

    @GetMapping("/name/{name}")
    ResponseEntity<List<BeautyProcedureDTO>> listByNameLikeIgnoreCase(@PathVariable String name){
        return ResponseEntity.ok(beautyProcedureService.listByNameIgnoreCase(name));
    }

    @GetMapping("/description/{description}")
    ResponseEntity<List<BeautyProcedureDTO>> listByDescriptionAllBeautyProcdures(@PathVariable String description){
        return ResponseEntity.ok(beautyProcedureService.listByDescriptionIgnoreCase(description));
    }
}
