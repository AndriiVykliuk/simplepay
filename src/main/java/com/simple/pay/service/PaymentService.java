package com.simple.pay.service;

import com.simple.pay.exception.ServiceException;
import com.simple.pay.model.entity.Payment;
import com.simple.pay.model.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public void save(Payment payment) {
        try {
            paymentRepository.save(payment);
        } catch (DataIntegrityViolationException e) {
            // although the manager should not be aware of http codes, we simplify a bit :)
            throw new ServiceException(409, "invoice", " Invoice with a given number already exists in DB");
        }
    }
}
