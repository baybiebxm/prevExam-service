package sit.int204.previousexam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.previousexam.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
