package farcic.dev.orderService.message;

import farcic.dev.orderService.entity.enums.OrderEvent;
import lombok.Builder;

@Builder
public record NotificationMessage(

        String OrderId,
        String Message,
        OrderEvent event

){
}
