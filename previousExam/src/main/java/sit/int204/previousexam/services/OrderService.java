package sit.int204.previousexam.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import sit.int204.previousexam.dtos.OrdersDto;
import sit.int204.previousexam.dtos.createUpdateOrderDto;
import sit.int204.previousexam.entities.Customer;
import sit.int204.previousexam.entities.Order;
import sit.int204.previousexam.exceptions.BadRequest;
import sit.int204.previousexam.exceptions.ResourceNotFound;
import sit.int204.previousexam.repositories.CustomerRepository;
import sit.int204.previousexam.repositories.OrderRepository;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository repository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<Order> getAllOrders(){
        try{
            return repository.findAll();
        }
        catch (Exception e){
            throw new ResourceNotFoundException("Cannot get orders");
        }
    }
    public Order getOrderById(String orderNumber){
        try{
            return repository.findById(orderNumber).orElseThrow(
                    () -> new ResourceNotFoundException(orderNumber + " cannot found")
            );
        }
        catch (Exception e){    // Exception must be 404 not found
            throw new ResourceNotFoundException(orderNumber + " cannot found");     // from GlobalExceptionHandler
        }
    }
    public Page<Order> getOrderWithPage(Integer page, Integer size){
        try {
            return repository.findAll(PageRequest.of(page,size));
        }
        catch (Exception e){
            throw new ResourceNotFound("Cannot get orders");
        }
    }
    public Order createNewOrder(createUpdateOrderDto newOrder){
        try{
            Order neworder = modelMapper.map(newOrder, Order.class);
            return repository.saveAndFlush(neworder);
        }
        catch (Exception e){
            throw new BadRequest("Cannot add order");
        }

    }
    public Order updateOrder(String orderNumber, createUpdateOrderDto updateOrder){
        try{
            Order existOrder = repository.findById(orderNumber).orElseThrow(
                    () -> new ResourceNotFoundException(orderNumber + "is not found")
            );
            existOrder.setOrderNumber(updateOrder.getOrderNumber());
            existOrder.setOrderDate(updateOrder.getOrderDate());
            existOrder.setRequiredDate(updateOrder.getRequiredDate());
            existOrder.setShippedDate(updateOrder.getShippedDate());
            existOrder.setStatus(updateOrder.getStatus());
            existOrder.setComments(updateOrder.getComments());
            Customer customer = customerRepository.findById(updateOrder.getCustomerNumber()).orElseThrow();
            existOrder.setCustomer(customer);
            return repository.saveAndFlush(existOrder);
        }
        catch (Exception e){
            throw new BadRequest("Cannot modify order");
        }
    }
    public Order deleteOrder(String orderNumber){
        try{
            Order existOrder = repository.findById(orderNumber).orElseThrow(
                    () -> new ResourceNotFoundException(orderNumber + " is not found")
            );
            repository.delete(existOrder);
            return existOrder;
        }
        catch (Exception e){
            throw new ResourceNotFound("Cannot delete");
        }
    }

    public void deleteAllOrder(String customerNumber){
        List<Order> orderList = repository.findAllByCustomer_CustomerNumber(customerNumber);
        repository.deleteAll(orderList);
    }
}
