package br.raffa.beautique.ms_sync.services.impl;

import br.raffa.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import br.raffa.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.raffa.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.raffa.beautique.ms_sync.services.AppointmentService;
import br.raffa.beautique.ms_sync.services.BeautyProcedureService;
import br.raffa.beautique.ms_sync.services.CustomerService;
import br.raffa.beautique.ms_sync.services.SyncService;
import br.raffa.beautique.ms_sync.utils.SyncLogger;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class SyncServiceImpl implements SyncService {

    private final AppointmentService appointmentService;
    private final CustomerService customerService;
    private final BeautyProcedureService beautyProcedureService;

    public SyncServiceImpl(AppointmentServiceImpl appointmentService, CustomerService customerService, BeautyProcedureService beautyProcedureService){
        this.appointmentService = appointmentService;
        this.customerService = customerService;
        this.beautyProcedureService = beautyProcedureService;
    }

    @Override
    public void syncCustomer(CustomerDTO customer) {
        try{
            SyncLogger.info("Saving customer: " + customer.getId());
            customerService.saveCustomer(customer);
            SyncLogger.info("Updating appointment customer: " + customer.getId());
            appointmentService.updateAppointmentCustomer(customer);
        }catch (Exception e){
            SyncLogger.error("Error saving customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncAppointment(FullAppointmentDTO appointmentDTO) {
        try{
            SyncLogger.info("Saving appointment: " + appointmentDTO.getId());
            appointmentService.saveAppointment(appointmentDTO);
        }catch (Exception e){
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void syncBeautyProcedures(BeautyProcedureDTO beautyProcedure) {
        try{
            SyncLogger.info("SAving beauty procedure: " + beautyProcedure.getId());
            beautyProcedureService.saveBeautyProcedure(beautyProcedure);
            SyncLogger.info("Updating appointment beauty procedure: " + beautyProcedure.getId());
            appointmentService.updateAppointmentBeautyProcedures(beautyProcedure);
        }catch (Exception e){
            SyncLogger.error("Error saving beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
