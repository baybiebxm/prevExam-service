package sit.int204.previousexam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sit.int204.previousexam.entities.Customer;
import sit.int204.previousexam.services.CustomerService;

import java.util.List;

@RestController
@RequestMapping("api/64130500026")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("/customers")
    public List<Customer> getAllCus(){
        return service.getAllCus();
    }
}
