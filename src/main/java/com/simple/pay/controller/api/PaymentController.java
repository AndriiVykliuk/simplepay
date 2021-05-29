package com.simple.pay.controller.api;

import com.simple.pay.model.dto.PayResponseDto;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/", produces = "application/json;charsert=utf-8")
public class PaymentController {

    @RequestMapping(value = "transaction/{id}", method = RequestMethod.GET)
    public PayResponseDto getTransactionData(@PathVariable("id") Integer transactionId) {
        PayResponseDto payResponseDto = new PayResponseDto();
        payResponseDto.setApproved(true);
        return payResponseDto;
    }

}
