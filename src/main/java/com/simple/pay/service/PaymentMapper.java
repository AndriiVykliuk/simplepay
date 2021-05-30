package com.simple.pay.service;

import com.simple.pay.model.dto.Card;
import com.simple.pay.model.dto.CardHolder;
import com.simple.pay.model.dto.PaymentDto;
import com.simple.pay.model.entity.Payment;
import com.simple.pay.util.Sanitizer;
import org.springframework.stereotype.Component;

/**
 * Provides the mapping between the Payment entity and PaymentDto.
 */
@Component
public class PaymentMapper {

    public Payment toEntity(PaymentDto dto) {
        Payment payment = new Payment();

        payment.setAmount(dto.getAmount());
        payment.setCurrency(dto.getCurrency());
        payment.setInvoice(dto.getInvoice());

        payment.setCardHolderEmail(dto.getCardHolder().getEmail());
        payment.setCardHolderName(dto.getCardHolder().getName());

        payment.setCardNumber(dto.getCard().getPan().substring(0, 12));
        payment.setCardSuffix(dto.getCard().getPan().substring(12, 16));
        payment.setExpiry(dto.getCard().getExpiry());
        return payment;
    }

    public PaymentDto toDto(Payment entity) {
        PaymentDto dto = new PaymentDto();
        dto.setAmount(entity.getAmount());
        dto.setInvoice(entity.getInvoice());
        dto.setCurrency(entity.getCurrency());

        dto.setCard(new Card());
        dto.getCard().setPan(Sanitizer.sanitize(entity.getCardNumber()) + entity.getCardSuffix());
        dto.getCard().setExpiry(Sanitizer.sanitize(entity.getExpiry()));

        dto.setCardHolder(new CardHolder());
        dto.getCardHolder().setName(Sanitizer.sanitize(entity.getCardHolderName()));
        dto.getCardHolder().setEmail(Sanitizer.sanitize(entity.getCardHolderEmail()));

        return dto;
    }
}
