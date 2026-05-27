package farcic.dev.orderService.controller;

import farcic.dev.orderService.dto.reponse.OrderResponse;
import farcic.dev.orderService.dto.request.OrderCreateRequest;
import farcic.dev.orderService.service.CreateOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final CreateOrderService createOrderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createOrder(@RequestBody OrderCreateRequest request){
        return createOrderService.createOrder(request);
    }

    public void updateOrder(){
        //implementar lógica de atualização de pedido
    }

}
