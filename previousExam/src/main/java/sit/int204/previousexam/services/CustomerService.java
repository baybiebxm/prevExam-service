package sit.int204.previousexam.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import sit.int204.previousexam.entities.Customer;
import sit.int204.previousexam.repositories.CustomerRepository;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;

    public List<Customer> getAllCus(){
        return repository.findAll();
    }
}
