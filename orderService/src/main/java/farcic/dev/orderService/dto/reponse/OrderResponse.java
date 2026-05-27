package farcic.dev.orderService.dto.reponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import farcic.dev.orderService.entity.enums.OrderStatus;
import farcic.dev.orderService.entity.enums.PaymentMethod;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderResponse(

        String id,
        String customerId,
        String basketId,
        BigDecimal totalAmount,
        OrderStatus status,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        PaymentMethod paymentMethod
) {
}
