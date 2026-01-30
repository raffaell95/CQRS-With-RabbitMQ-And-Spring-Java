package raffa.com.beautique.api.services;

import raffa.com.beautique.api.dtos.AppointmentDTO;

public interface AppointmentsService {

    AppointmentDTO create(AppointmentDTO appointmentDTO);
    AppointmentDTO update(AppointmentDTO appointmentDTO);
    void deleteById(Long id);
    AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentsEntity);
}
