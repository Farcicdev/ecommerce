package farcic.dev.orderService.dto.request;

import farcic.dev.orderService.entity.enums.OrderEvent;

public record UpdateOrderRequest(

        String orderId,
        OrderEvent orderEvent
) {
}
