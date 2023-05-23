package sit.int204.previousexam.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import sit.int204.previousexam.entities.Customer;

import java.util.Date;

@Getter
@Setter
public class OrdersDto {
    private String orderNumber;
    private Date orderDate;
    private String status;
//    @Column(name = "customerNumber")
//    private String customerNumber;
    private CustomerDto customer;
    public String getCustomer(){
        return customer == null ? "-" : customer.getCustomer();
    }


}
