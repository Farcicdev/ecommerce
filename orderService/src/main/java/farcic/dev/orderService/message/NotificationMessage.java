package farcic.dev.orderService.message;

import farcic.dev.orderService.entity.enums.OrderEvent;

public record NotificationMessage(

        String OrderId,
        String Message,
        OrderEvent event

){
}
