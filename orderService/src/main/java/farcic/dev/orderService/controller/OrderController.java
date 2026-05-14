package farcic.dev.orderService.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    public void createOrder(){
        //implementar lógica de criação de pedido
    }

    public void updateOrder(){
        //implementar lógica de atualização de pedido
    }

}
