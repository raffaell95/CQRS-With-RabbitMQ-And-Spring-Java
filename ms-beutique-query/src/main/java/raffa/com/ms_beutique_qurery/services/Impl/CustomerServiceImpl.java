package raffa.com.ms_beutique_qurery.services.Impl;

import org.springframework.stereotype.Service;
import raffa.com.ms_beutique_qurery.dtos.customers.CustomerDTO;
import raffa.com.ms_beutique_qurery.repositorios.CustomerRepository;
import raffa.com.ms_beutique_qurery.services.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> listAllCustomers() {
        try{
            return customerRepository.findAll();
        }catch (Exception e){
            throw new RuntimeException("Error listing all customer");
        }
    }

    @Override
    public List<CustomerDTO> listByNameLikeIgnoreCase(String name) {
        try{
            return customerRepository.findByNameLikeIgnoreCase(name);
        }catch (Exception e){
            throw new RuntimeException("Error listing all customers by name");
        }
    }

    @Override
    public List<CustomerDTO> listByEmailLikeIgnoreCase(String email) {
        try{
            return customerRepository.findByEmailLikeIgnoreCase(email);
        }catch (Exception e){
            throw new RuntimeException("Error listing all customers by email");
        }
    }

}
