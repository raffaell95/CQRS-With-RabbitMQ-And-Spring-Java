package br.raffa.beautique.ms_sync.services;

import br.raffa.beautique.ms_sync.dtos.customers.CustomerDTO;

public interface CustomerService {
    void saveCustomer(CustomerDTO customer);
}
