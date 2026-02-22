package raffa.com.ms_beutique_qurery.dtos.appointments;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import raffa.com.ms_beutique_qurery.dtos.beautyprocedures.BeautyProcedureDTO;
import raffa.com.ms_beutique_qurery.dtos.customers.CustomerDTO;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "appointments")
public class FullAppointmentDTO {
    private Long id;
    private LocalDateTime dateTime;
    private Boolean appointmentsOpen;

    private CustomerDTO customer;
    private BeautyProcedureDTO beautyProcedure;
}
