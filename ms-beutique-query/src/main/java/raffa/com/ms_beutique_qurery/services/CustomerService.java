package raffa.com.ms_beutique_qurery.services;

import raffa.com.ms_beutique_qurery.dtos.customers.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> listAllCustomers();
    List<CustomerDTO> listByNameLikeIgnoreCase(String name);
    List<CustomerDTO> listByEmailLikeIgnoreCase(String email);
}
