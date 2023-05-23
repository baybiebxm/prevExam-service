package sit.int204.previousexam.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sit.int204.previousexam.dtos.OrdersDto;
import sit.int204.previousexam.dtos.PageDTO;
import sit.int204.previousexam.dtos.createUpdateOrderDto;
import sit.int204.previousexam.entities.Order;
import sit.int204.previousexam.exceptions.BadRequest;
import sit.int204.previousexam.services.OrderService;
import sit.int204.previousexam.utils.ListMapper;

import java.util.List;

@RestController
@RequestMapping("/api/64130500026/orders")
public class OrderController {
    @Autowired
    private OrderService service;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ListMapper listMapper;

    @GetMapping("")
    public List<Order> getAllOrders(){
        return service.getAllOrders();
    }

    @GetMapping("{orderNumber}")
    public Order getOrderById(@PathVariable String orderNumber){
        return service.getOrderById(orderNumber);
    }
    @GetMapping("/dto")
    public List<OrdersDto> getAllOrdersDto(){
        List<Order> orders = service.getAllOrders();
        return listMapper.mapList(orders, OrdersDto.class, modelMapper);
    }

    @GetMapping("/pages")
    public Page<OrdersDto> getOrderWithPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Page<Order> orders = service.getOrderWithPage(page, size);
        return orders.map(order -> modelMapper.map(order, OrdersDto.class));
    }

    @GetMapping("/pagesdto")
    public PageDTO<OrdersDto> getOrderWithPageDto(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ){
        Page<Order> orders = service.getOrderWithPage(page, size);
        return listMapper.toPageDTO(orders, OrdersDto.class, modelMapper);
    }

    @PostMapping("")
    public createUpdateOrderDto createOrder(@Valid @RequestBody createUpdateOrderDto newOrder){
        try{
            Order saveOrder = service.createNewOrder(newOrder);
            return modelMapper.map(saveOrder, createUpdateOrderDto.class);
        }
        catch (Exception e){
            throw new BadRequest("Cannot add order");
        }
    }

    @PutMapping("{orderNumber}")
    public createUpdateOrderDto updateOrder(@PathVariable String orderNumber, @RequestBody createUpdateOrderDto updateOrder){
        try{
            Order saveOrder = service.updateOrder(orderNumber,updateOrder);
            return modelMapper.map(saveOrder, createUpdateOrderDto.class);
        }
        catch (Exception e){
            throw new BadRequest("Cannot modify order");
        }
    }

    @DeleteMapping("{orderNumber}")
    public Order deleteOrder(@PathVariable String orderNumber){
        return service.deleteOrder(orderNumber);
    }

    @DeleteMapping("/cus/{customerNumber}")
    public void deleteAllByCustomer(@PathVariable String customerNumber){
        service.deleteAllOrder(customerNumber);
    }
}
