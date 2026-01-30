package raffa.com.beautique.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raffa.com.beautique.api.dtos.AppointmentDTO;
import raffa.com.beautique.api.entities.AppointmentsEntity;
import raffa.com.beautique.api.entities.BeautyProceduresEntity;
import raffa.com.beautique.api.entities.CustomerEntity;
import raffa.com.beautique.api.repositories.AppointmentRepository;
import raffa.com.beautique.api.repositories.BeautyProcedureRepository;
import raffa.com.beautique.api.repositories.CustomerRepository;
import raffa.com.beautique.api.services.AppointmentsService;
import raffa.com.beautique.api.utils.ConverterUtil;

import java.util.Optional;

@Service
public class AppointmentsServiceImpl implements AppointmentsService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private final ConverterUtil<AppointmentsEntity, AppointmentDTO> converterUtil = new ConverterUtil<>(AppointmentsEntity.class, AppointmentDTO.class);
    @Autowired
    private BeautyProcedureRepository beautyProcedureRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AppointmentDTO create(AppointmentDTO appointmentDTO) {
        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentDTO);
        AppointmentsEntity newAppointmentsEntity = appointmentRepository.save(appointmentsEntity);
        return converterUtil.convertToTarget(newAppointmentsEntity);
    }

    @Override
    public AppointmentDTO update(AppointmentDTO appointmentDTO) {
        Optional<AppointmentsEntity> currenteAppointment = appointmentRepository.findById(appointmentDTO.getId());
        if(currenteAppointment.isEmpty()){
            throw new RuntimeException("Appointment not found");
        }
        AppointmentsEntity appointmentsEntity = converterUtil.convertToSource(appointmentDTO);
        appointmentsEntity.setCreatedAt(currenteAppointment.get().getCreatedAt());
        AppointmentsEntity updatedAppointmentEntity = appointmentRepository.save(appointmentsEntity);
        return converterUtil.convertToTarget(updatedAppointmentEntity);
    }

    @Override
    public void deleteById(Long id) {
        AppointmentsEntity appointmentsEntity = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentRepository.delete(appointmentsEntity);
    }

    @Override
    public AppointmentDTO setCustomerToAppointment(AppointmentDTO appointmentDTO) {
        CustomerEntity customerEntity = findCustomerById(appointmentDTO.getCustomer());
        BeautyProceduresEntity beautyProceduresEntity = findBeautyProcedureById(appointmentDTO.getBeautyProcedure());
        AppointmentsEntity appointmentsEntity = findAppointmentById(appointmentDTO.getId());
        appointmentsEntity.setCustomer(customerEntity);
        appointmentsEntity.setBeautyProcedure(beautyProceduresEntity);
        appointmentsEntity.setAppointmentsOpen(false);

        AppointmentsEntity updatedAppointmentEntity = appointmentRepository.save(appointmentsEntity);
        return buildAppointmentsDTO(updatedAppointmentEntity);
    }

    private AppointmentsEntity findAppointmentById(Long id){
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    private BeautyProceduresEntity findBeautyProcedureById(Long id){
        return beautyProcedureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Beauty Procedure not found"));
    }

    private CustomerEntity findCustomerById(Long id){
        return customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    private AppointmentDTO buildAppointmentsDTO(AppointmentsEntity appointmentsEntity){
        return AppointmentDTO.builder()
                .id(appointmentsEntity.getId())
                .beautyProcedure(appointmentsEntity.getBeautyProcedure().getId())
                .dateTime(appointmentsEntity.getDateTime())
                .appointmentsOpen(appointmentsEntity.getAppointmentsOpen())
                .customer(appointmentsEntity.getCustomer().getId())
                .build();
    }

}
