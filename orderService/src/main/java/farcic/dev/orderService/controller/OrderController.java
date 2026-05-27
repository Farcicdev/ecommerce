package farcic.dev.orderService.controller;

import farcic.dev.orderService.dto.reponse.OrderResponse;
import farcic.dev.orderService.dto.request.OrderCreateRequest;
import farcic.dev.orderService.dto.request.UpdateOrderRequest;
import farcic.dev.orderService.entity.Order;
import farcic.dev.orderService.mapper.OrderMapper;
import farcic.dev.orderService.service.CreateOrderService;
import farcic.dev.orderService.service.UpdateOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderService createOrderService;
    private final UpdateOrderService updateOrderService;
    private final OrderMapper orderMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody OrderCreateRequest request){
        return createOrderService.createOrder(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public OrderResponse updateOrder(@RequestBody UpdateOrderRequest request) {
        Order order = updateOrderService.updateOrder(request.orderEvent(), request.orderId());
        return orderMapper.toOrderResponse(order);
    }

}
