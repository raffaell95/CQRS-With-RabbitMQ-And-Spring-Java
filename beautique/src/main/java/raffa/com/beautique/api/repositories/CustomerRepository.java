package raffa.com.beautique.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raffa.com.beautique.api.entities.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {
}
