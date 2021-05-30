package com.simple.pay.service;

import com.simple.pay.exception.ServiceException;
import com.simple.pay.model.entity.Payment;
import com.simple.pay.model.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Service for Payment business operations.
 */
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment getByInvoice(Integer invoice) {
        Payment payment = paymentRepository.findByInvoice(invoice);
        if (payment == null) {
            throw new ServiceException(404, "Invoice not found by given number", invoice);
        }
        return payment;
    }

    public void save(Payment payment) {
        try {
            paymentRepository.save(payment);
        } catch (DataIntegrityViolationException e) {
            throw new ServiceException(409, "invoice", " Invoice with a given number already exists in DB");
        }
    }
}
