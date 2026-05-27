package farcic.dev.orderService.service;

import farcic.dev.orderService.entity.enums.OrderEvent;
import farcic.dev.orderService.entity.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStatesService {

    private final StateMachine<OrderStatus, OrderEvent> orderStateMachineService;

    public OrderStatus processEvent(OrderStatus currentStatus, OrderEvent event) {
        orderStateMachineService.stop();
        orderStateMachineService.getStateMachineAccessor()
                .doWithAllRegions(access ->
                        access.resetStateMachine(new DefaultStateMachineContext<>(currentStatus, null, null, null)));
        orderStateMachineService.start();

        boolean accepted = orderStateMachineService.sendEvent(event);

        if(accepted) {
          return orderStateMachineService.getState().getId();
        } else {
            throw new IllegalStateException("Event " + event + " is not accepted in state " + currentStatus);
        }
    }
}
