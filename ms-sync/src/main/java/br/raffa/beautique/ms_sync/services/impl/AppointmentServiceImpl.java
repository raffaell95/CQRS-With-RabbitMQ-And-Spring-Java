package br.raffa.beautique.ms_sync.services.impl;

import br.raffa.beautique.ms_sync.dtos.appointments.FullAppointmentDTO;
import br.raffa.beautique.ms_sync.dtos.beautyprocedures.BeautyProcedureDTO;
import br.raffa.beautique.ms_sync.dtos.customers.CustomerDTO;
import br.raffa.beautique.ms_sync.repositories.AppointmentRepository;
import br.raffa.beautique.ms_sync.services.AppointmentService;
import br.raffa.beautique.ms_sync.utils.SyncLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveAppointment(FullAppointmentDTO appointment) {
        try{
            SyncLogger.info("Saving appointment: " + appointment.getId());
            appointmentRepository.save(appointment);
        }catch (Exception e){
            SyncLogger.error("Error saving appointment: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentCustomer(CustomerDTO customer) {
        try{
            SyncLogger.info("Update appointment customer: " + customer.getId());
            Query query = new Query(Criteria.where("customer.id").is(customer.getId()));
            Update update = new Update().set("customer", customer);
            mongoTemplate.updateMulti(query, update, FullAppointmentDTO.class);
        }catch (Exception e){
            SyncLogger.error("Error updating apppointment customer: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void updateAppointmentBeautyProcedures(BeautyProcedureDTO beautyProcedure) {
        try{
            SyncLogger.info("Update appointment beauty procedure: " + beautyProcedure.getId());
            Query query = new Query(Criteria.where("beautyProcedure.id").is(beautyProcedure.getId()));
            Update update = new Update()
                    .set("beautyProcedure.name", beautyProcedure.getName())
                    .set("beautyProcedure.description", beautyProcedure.getDescription());
            mongoTemplate.updateMulti(query, update, FullAppointmentDTO.class);
        }catch (Exception e){
            SyncLogger.error("Error updating beauty procedure: " + e.getMessage());
            SyncLogger.trace(Arrays.toString(e.getStackTrace()));
        }
    }
}
