package farcic.dev.orderService.mapper;

import farcic.dev.orderService.dto.reponse.OrderResponse;
import farcic.dev.orderService.dto.request.OrderCreateRequest;
import farcic.dev.orderService.dto.request.UpdateOrderRequest;
import farcic.dev.orderService.entity.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public Order toOrder (OrderCreateRequest orderCreateRequest) {
        return Order.builder()
                .customerId(orderCreateRequest.customerId())
                .basketId(orderCreateRequest.basketId())
                .amount(orderCreateRequest.amount())
                .shippingCost(orderCreateRequest.shippingCost())
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .basketId(order.getBasketId())
                .totalAmount(order.totalAmount())
                .status(order.getStatus())
                .paymentMethod(order.getPaymentMethod())
                .build();
    }

    public Order toUptadeOrder(UpdateOrderRequest request){
        return Order.builder()
                .id(request.orderId())
                .build();
    }

}
