package com.simple.pay.service;

import com.simple.pay.TestUtil;
import com.simple.pay.model.dto.PaymentDto;
import com.simple.pay.model.entity.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PaymentMapperTest {

    private static final String PAN = "2002301143967251";

    @Test
    public void testEntityMapping() {
        PaymentMapper mapper = new PaymentMapper();

        var entity = mapper.toEntity(getTestDto());

        Assertions.assertNull(entity.getId());
        Assertions.assertNotNull(entity.getAmount());
        Assertions.assertNotNull(entity.getCurrency());
        Assertions.assertNotNull(entity.getInvoice());
        Assertions.assertNotNull(entity.getCardSuffix());
        Assertions.assertNotNull(entity.getCardNumber());
        Assertions.assertNotNull(entity.getCardHolderEmail());
        Assertions.assertNotNull(entity.getCardHolderName());
        Assertions.assertEquals("7251", entity.getCardSuffix());
        Assertions.assertEquals("200230114396", entity.getCardNumber());
    }

    @Test
    public void testDtoMapping() {
        PaymentMapper mapper = new PaymentMapper();

        var output = mapper.toDto(getTestEntity());

        Assertions.assertEquals(12, output.getAmount());
        Assertions.assertEquals("EUR", output.getCurrency());
        Assertions.assertEquals(555, output.getInvoice());
        Assertions.assertNotNull(output.getCard());
        Assertions.assertNotNull(output.getCardHolder());

        Assertions.assertEquals("************7251", output.getCard().getPan());
        Assertions.assertEquals("****", output.getCard().getExpiry());
        Assertions.assertEquals("*********************", output.getCardHolder().getEmail());
        Assertions.assertEquals("**********", output.getCardHolder().getName());

    }

    private PaymentDto getTestDto() {
        PaymentDto goodDto = TestUtil.getGoodDto();
        goodDto.getCard().setPan(PAN);
        return goodDto;
    }

    private Payment getTestEntity() {
        Payment payment = new Payment();
        payment.setId(134);
        payment.setInvoice(555);
        payment.setAmount(12);
        payment.setCurrency("EUR");
        payment.setExpiry("1255");
        payment.setCardNumber("200230114396");
        payment.setCardSuffix("7251");
        payment.setCardHolderName("John Smith");
        payment.setCardHolderEmail("JohnSmith56@gmail.com");
        return payment;
    }

}
