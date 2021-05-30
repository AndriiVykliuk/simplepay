package com.simple.pay.controller.api;

import com.simple.pay.exception.ServiceException;
import com.simple.pay.model.dto.PaymentDto;
import com.simple.pay.model.dto.ResponseDto;
import com.simple.pay.model.entity.Payment;
import com.simple.pay.model.repo.PaymentRepository;
import com.simple.pay.service.AuditService;
import com.simple.pay.service.PaymentMapper;
import com.simple.pay.service.PaymentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/invoice/")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private PaymentMapper paymentMapper;
    @Autowired
    private AuditService auditService;

    @ApiOperation(value = "Retrieve a prior transaction by given Invoice number", response = PaymentDto.class)
    @ApiResponses({
            @ApiResponse(code = 200, message = "The transaction is found and returned"),
            @ApiResponse(code = 404, message = "The transaction wa not found by given Id "),

    })
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentDto> getTransaction(@PathVariable("id") Integer invoice) {
        Payment payment = paymentRepository.findByInvoice(invoice);
        if (payment == null) {
            throw new ServiceException(404, "Invoice not found by given number", invoice);
        }
        return ResponseEntity.ok(paymentMapper.toDto(payment));
    }

    @ApiOperation(code = 201, value = "Submits a payment", response = ResponseDto.class)
    @ApiResponses({
            @ApiResponse(code = 201, message = "The transaction was stored"),
            @ApiResponse(code = 400, message = "Field validation error"),
            @ApiResponse(code = 409, message = "Paymant with the same invoice number already exists"),
            @ApiResponse(code = 500, message = "Internal server error"),

    })
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDto> saveTransaction(@Valid @RequestBody PaymentDto dto) {
        Payment payment = paymentMapper.toEntity(dto);
        paymentService.save(payment);
        auditService.audit(paymentMapper.toDto(payment));
        return ResponseEntity.status(201).body(new ResponseDto());
    }

}
