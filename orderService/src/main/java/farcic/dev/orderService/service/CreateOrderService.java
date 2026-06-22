package farcic.dev.orderService.service;

import farcic.dev.orderService.dto.reponse.OrderResponse;
import farcic.dev.orderService.dto.request.OrderCreateRequest;
import farcic.dev.orderService.entity.Order;
import farcic.dev.orderService.entity.enums.OrderEvent;
import farcic.dev.orderService.entity.enums.OrderStatus;
import farcic.dev.orderService.mapper.OrderMapper;
import farcic.dev.orderService.message.NotificationMessage;
import farcic.dev.orderService.producer.NotificationProducerService;
import farcic.dev.orderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CreateOrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final NotificationProducerService notificationService;

    public OrderResponse createOrder(OrderCreateRequest request){

        Order order = mapper.toOrder(request);
        order.setStatus(OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());
        order.setUpdatedAt(LocalDateTime.now());

        //Enviar mensagem para o Kafka
        notificationService.sendMessage(NotificationMessage
                .builder()
                .OrderId(request.customerId())
                .Message("Order created")
                .event(OrderEvent.CREATE)
                .build());

        Order saved = repository.save(order);

        return mapper.toOrderResponse(saved);
    }

}
