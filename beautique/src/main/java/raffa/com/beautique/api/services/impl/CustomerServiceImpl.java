package raffa.com.beautique.api.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import raffa.com.beautique.api.dtos.CustomerDTO;
import raffa.com.beautique.api.entities.CustomerEntity;
import raffa.com.beautique.api.repositories.CustomerRepository;
import raffa.com.beautique.api.services.CustomerService;
import raffa.com.beautique.api.utils.ConverterUtil;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    private final ConverterUtil<CustomerEntity, CustomerDTO> converterUtil = new ConverterUtil<>(CustomerEntity.class, CustomerDTO.class);

    @Override
    public CustomerDTO create(CustomerDTO customerDto) {
        CustomerEntity customerEntity = converterUtil.convertToSource(customerDto);
        CustomerEntity newCustomerEntity = customerRepository.save(customerEntity);
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

        return converterUtil.convertToTarget(customerRepository.save(customerEntity));
    }


}
