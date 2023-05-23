package sit.int204.previousexam.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Setter
@Getter
@Validated
public class createUpdateOrderDto {
    @NotNull
    private String orderNumber;
    private Date orderDate;
    private Date requiredDate;
    private Date shippedDate;
    private String status;
    private String comments;
    @NotNull
    private String customerNumber;
}
