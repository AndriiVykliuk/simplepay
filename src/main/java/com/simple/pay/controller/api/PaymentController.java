package com.simple.pay.controller.api;

import com.simple.pay.model.dto.PayResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping(value = "/api/v1/", produces = "application/json;charsert=utf-8")
public class PaymentController {

    @RequestMapping(value = "transaction/{id}", method = RequestMethod.GET)
    public PayResponse getTransactionData(@PathParam("id") Integer transactionId) {
        PayResponse payResponse = new PayResponse();
        payResponse.setApproved(true);
        return payResponse;
    }

}
