package com.simple.pay.model.repo;

import com.simple.pay.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findByInvoice(Integer invoiceId);

}
