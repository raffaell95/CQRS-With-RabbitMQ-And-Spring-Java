package raffa.com.ms_beutique_qurery.repositorios;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import raffa.com.ms_beutique_qurery.dtos.customers.CustomerDTO;

import java.util.List;

public interface CustomerRepository extends MongoRepository<CustomerDTO, Long> {
    @Query("{'name' : { $regex: ?0, $options: 'i'}}")
    List<CustomerDTO> findByNameLikeIgnoreCase(String name);

    @Query("{'email' : { $regex: ?0, $options: 'i'}}")
    List<CustomerDTO> findByEmailLikeIgnoreCase(String name);
}
