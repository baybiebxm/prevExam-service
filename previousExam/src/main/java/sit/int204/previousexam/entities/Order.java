package sit.int204.previousexam.entities;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String orderNumber;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private String status;
    private String comments;
    @ManyToOne
    @JoinColumn(name = "customerNumber")
    private Customer customer;
}
