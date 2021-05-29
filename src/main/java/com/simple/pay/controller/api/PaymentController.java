package com.simple.pay.controller.api;

import com.simple.pay.exception.ServiceException;
import com.simple.pay.model.dto.PaymentDto;
import com.simple.pay.model.dto.ResponseDto;
import com.simple.pay.model.entity.Payment;
import com.simple.pay.model.repo.PaymentRepository;
import com.simple.pay.service.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/", produces = "application/json;charsert=utf-8")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private PaymentMapper paymentMapper;

    @RequestMapping(value = "invoice/{id}", method = RequestMethod.GET)
    public ResponseEntity<PaymentDto> getTransaction(@PathVariable("id") Integer invoice) {
        Payment payment = paymentRepository.findByInvoice(invoice);
        if (payment == null) {
            throw new ServiceException(404, "invoice not found by id", invoice);
        }
        return ResponseEntity.ok(paymentMapper.toDto(payment));
    }

    @RequestMapping(value = "transaction", method = RequestMethod.POST)
    public ResponseEntity<ResponseDto> saveTransaction(@RequestBody PaymentDto dto) {
        Payment payment = paymentMapper.toEntity(dto);
        paymentRepository.save(payment);
        return ResponseEntity.status(201).body(new ResponseDto());
    }

}
