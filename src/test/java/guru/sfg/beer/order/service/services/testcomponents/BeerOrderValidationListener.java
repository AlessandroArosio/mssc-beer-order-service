package guru.sfg.beer.order.service.services.testcomponents;

import guru.sfg.beer.order.service.config.JmsConfig;
import guru.sfg.brewery.model.events.ValidateOrderRequest;
import guru.sfg.brewery.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {
    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void list(Message msg) {
        boolean isValid = true;
        boolean sendResponse = true;

        var request = (ValidateOrderRequest) msg.getPayload();

        // condition to fail validation
        if (Objects.equals(request.getBeerOrder().getCustomerRef(), "fail-validation")) {
            isValid = false;
        } else if (Objects.equals(request.getBeerOrder().getCustomerRef(), "dont-validate")) {
            sendResponse = false;
        }

        if (sendResponse) {
            jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE,
                    ValidateOrderResult.builder()
                            .orderId(request.getBeerOrder().getId())
                            .isValid(isValid)
                            .build());
        }
    }
}
