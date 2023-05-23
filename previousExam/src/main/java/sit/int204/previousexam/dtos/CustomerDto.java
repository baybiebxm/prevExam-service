package sit.int204.previousexam.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String customerNumber;
    private String contactFirstName;
    private String contactLastName;

    public String getCustomer(){
        return customerNumber + "-" + contactFirstName + " " + contactLastName;
    }
}
