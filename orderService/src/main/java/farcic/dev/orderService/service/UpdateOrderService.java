package farcic.dev.orderService.service;


import farcic.dev.orderService.entity.Order;
import farcic.dev.orderService.entity.enums.OrderEvent;
import farcic.dev.orderService.entity.enums.OrderStatus;
import farcic.dev.orderService.message.NotificationMessage;
import farcic.dev.orderService.producer.NotificationProducerService;
import farcic.dev.orderService.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UpdateOrderService {

    private final OrderRepository repository;
    private final OrderStatesService orderStatesService;
    private final NotificationProducerService notificationService;


    public Order updateOrder(OrderEvent event, String orderId) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        OrderStatus orderStatus = orderStatesService.processEvent(order.getStatus(), event);

        order.setStatus(orderStatus);
        order.setUpdatedAt(LocalDateTime.now());

        //Enviar mensagem para o Kafka
        notificationService.sendMessage(NotificationMessage
                .builder()
                .OrderId(order.getId())
                .Message("Order created")
                .event(OrderEvent.CREATE)
                .build());

        return repository.save(order);
    }
}
