package farcic.dev.orderService.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderCreateRequest(

        String customerId,
        String basketId,
        BigDecimal amount,
        BigDecimal shippingCost
) {
}
