package com.simple.pay;

import com.simple.pay.model.dto.Card;
import com.simple.pay.model.dto.CardHolder;
import com.simple.pay.model.dto.PaymentDto;

public class TestUtil {

    public static CardHolder getGoodCardHolder() {
        CardHolder cardHolder = new CardHolder();
        cardHolder.setName("My Name");
        cardHolder.setEmail("myemail@gmail.com");
        return cardHolder;
    }

    public static Card getGoodCard() {
        Card card = new Card();
        card.setExpiry("1236");
        card.setCvv("123");
        card.setPan("2002301143967251");
        return card;
    }

    public static PaymentDto getDtoWithRootFields() {
        PaymentDto dto = new PaymentDto();
        dto.setAmount(2);
        dto.setCurrency("UAH");
        dto.setInvoice(125);
        return dto;
    }

    public static PaymentDto getGoodDto() {
        PaymentDto dto = getDtoWithRootFields();
        dto.setCardHolder(getGoodCardHolder());
        dto.setCard(getGoodCard());
        return dto;
    }
}
