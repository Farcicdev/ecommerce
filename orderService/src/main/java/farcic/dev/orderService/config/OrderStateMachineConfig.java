package farcic.dev.orderService.config;

import farcic.dev.orderService.entity.enums.OrderEvent;
import farcic.dev.orderService.entity.enums.OrderStatus;
import org.springframework.statemachine.config.StateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

public class OrderStateMachineConfig extends StateMachineConfigurerAdapter<OrderStatus, OrderEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<OrderStatus, OrderEvent> states) throws Exception {
        states
                .withStates()
                .initial(OrderStatus.CREATED)
                .state(OrderStatus.PAID)
                .state(OrderStatus.SHIPPED)
                .state(OrderStatus.DELIVERED)
                .state(OrderStatus.CANCELLED);
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<OrderStatus, OrderEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(OrderStatus.CREATED).target(OrderStatus.PAID).event(OrderEvent.PAY)
                .and()
                .withExternal()
                .source(OrderStatus.PAID).target(OrderStatus.SHIPPED).event(OrderEvent.SHIP)
                .and()
                .withExternal()
                .source(OrderStatus.SHIPPED).target(OrderStatus.DELIVERED).event(OrderEvent.DELIVER)
                .and()
                .withExternal()
                .source(OrderStatus.CREATED).target(OrderStatus.CANCELLED).event(OrderEvent.CANCEL);
    }
}
