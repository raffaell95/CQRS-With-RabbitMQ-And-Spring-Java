package raffa.com.beautique.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raffa.com.beautique.api.dtos.CustomerDTO;
import raffa.com.beautique.api.entities.CustomerEntity;
import raffa.com.beautique.api.repositories.CustomerRepository;
import raffa.com.beautique.api.services.BrockerService;
import raffa.com.beautique.api.services.CustomerService;
import raffa.com.beautique.api.utils.ConverterUtil;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private BrockerService brockerService;

    private final ConverterUtil<CustomerEntity, CustomerDTO> converterUtil = new ConverterUtil<>(CustomerEntity.class, CustomerDTO.class);

    @Override
    public CustomerDTO create(CustomerDTO customerDto) {
        CustomerEntity customerEntity = converterUtil.convertToSource(customerDto);
        CustomerEntity newCustomerEntity = customerRepository.save(customerEntity);
        sendCustomerToQueue(newCustomerEntity);
        return converterUtil.convertToTarget(newCustomerEntity);
    }

    @Override
    public void delete(Long id) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);
        if(customerEntityOptional.isEmpty()){
            throw new RuntimeException("Customer not found");
        }
        customerRepository.delete(customerEntityOptional.get());
    }

    @Override
    public CustomerDTO update(CustomerDTO customerDTO) {
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(customerDTO.getId());
        if(customerEntityOptional.isEmpty()){
            throw new RuntimeException("Customer not found");
        }
        CustomerEntity customerEntity = converterUtil.convertToSource(customerDTO);

        customerEntity.setAppointments(customerEntityOptional.get().getAppointments());
        customerEntity.setCreatedAt(customerEntityOptional.get().getCreatedAt());
        CustomerDTO updatedCustomerDTO = converterUtil.convertToTarget(customerRepository.save(customerEntity));
        sendCustomerToQueue(customerEntity);
        return updatedCustomerDTO;
    }

    private void sendCustomerToQueue(CustomerEntity customerEntity){
        CustomerDTO customerDTO = CustomerDTO.builder()
                .id(customerEntity.getId())
                .name(customerEntity.getName())
                .email(customerEntity.getEmail())
                .phone(customerEntity.getPhone())
                .build();
        brockerService.send("customer", customerDTO);
    }


}
