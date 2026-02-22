package raffa.com.ms_beutique_qurery.services;

import raffa.com.ms_beutique_qurery.dtos.appointments.FullAppointmentDTO;

import java.util.List;

public interface AppointmentService {
    List<FullAppointmentDTO> listAllAppointments();
    List<FullAppointmentDTO> ListAllAppointmentsByCustomer(Long customerId);
    List<FullAppointmentDTO> listAllAppointmentsByBeautyProcedure(Long beautyProcedureId);
}
