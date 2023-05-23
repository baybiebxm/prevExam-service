package sit.int204.previousexam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sit.int204.previousexam.dtos.OrdersDto;
import sit.int204.previousexam.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findAllByCustomer_CustomerNumber(String customerNumber);
}
