package raffa.com.beautique.api.services;

import raffa.com.beautique.api.dtos.CustomerDTO;

public interface CustomerService {
    CustomerDTO create(CustomerDTO customerEntity);
    void delete(Long id);
    CustomerDTO update(CustomerDTO customerDTO);
}
