package raffa.com.ms_beutique_qurery.repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import raffa.com.ms_beutique_qurery.dtos.appointments.FullAppointmentDTO;

import java.util.List;

public interface AppointmentRepository extends MongoRepository<FullAppointmentDTO, Long> {
    @Query("{ 'customerId' : ?0}")
    List<FullAppointmentDTO> listAppointmentsByCustomerId(Long customerId);

    @Query("{ 'beautyProcedureId' : ?0}")
    List<FullAppointmentDTO> listAppointmentsByBeautyProcedureId(Long beautyProcedureId);
}
