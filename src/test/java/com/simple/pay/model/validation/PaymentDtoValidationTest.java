package com.simple.pay.model.validation;

import com.simple.pay.TestUtil;
import com.simple.pay.model.dto.Card;
import com.simple.pay.model.dto.CardHolder;
import com.simple.pay.model.dto.PaymentDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Stream;


public class PaymentDtoValidationTest {

    private static Stream<Arguments> dataProvider() {
        PaymentDto withOnlyRootFields = TestUtil.getDtoWithRootFields();

        PaymentDto withEmptyCard = TestUtil.getDtoWithRootFields();
        withEmptyCard.setCard(new Card());
        withEmptyCard.setCardHolder(TestUtil.getGoodCardHolder());

        PaymentDto withEmptyHolder = TestUtil.getDtoWithRootFields();
        withEmptyHolder.setCard(TestUtil.getGoodCard());
        withEmptyHolder.setCardHolder(new CardHolder());

        PaymentDto goodOne = TestUtil.getGoodDto();

        return Stream.of(
                Arguments.of(new PaymentDto(), 6),
                Arguments.of(withOnlyRootFields, 2),
                Arguments.of(withEmptyCard, 4),
                Arguments.of(withEmptyHolder, 2),
                Arguments.of(goodOne, 0)

        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    void testPaymentObject(PaymentDto dto, int expectedViolationCount) {
        Validator javaxValidator = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<PaymentDto>> violations = javaxValidator.validate(dto);
        Assertions.assertEquals(expectedViolationCount, violations.size());
    }

}
