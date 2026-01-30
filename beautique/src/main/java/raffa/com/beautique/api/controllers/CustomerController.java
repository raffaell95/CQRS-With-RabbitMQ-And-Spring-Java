package raffa.com.beautique.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raffa.com.beautique.api.dtos.CustomerDTO;
import raffa.com.beautique.api.entities.CustomerEntity;
import raffa.com.beautique.api.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping()
    ResponseEntity<CustomerDTO> create(@RequestBody CustomerDTO customerDto){
        return ResponseEntity.ok(customerService.create(customerDto));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping()
    ResponseEntity<CustomerDTO> update(@RequestBody CustomerDTO customerDTO){
        return ResponseEntity.ok(customerService.update(customerDTO));
    }

}
