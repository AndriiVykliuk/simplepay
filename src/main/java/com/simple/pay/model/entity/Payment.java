package com.simple.pay.model.entity;

import com.simple.pay.util.FieldEncryptor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private Integer invoice;

    @Column(nullable = false)
    private Integer amount;

    @Column(nullable = false)
    private String currency;

    @Convert(converter = FieldEncryptor.class)
    @Column(name = "card_holder_name", nullable = false)
    private String cardHolderName;

    @Convert(converter = FieldEncryptor.class)
    @Column(name = "card_holder_email")
    private String cardHolderEmail;

    @Convert(converter = FieldEncryptor.class)
    @Column(name = "card_number", nullable = false)
    private String cardNumber;

    @Column(name = "card_suffix", nullable = false)
    private Integer cardSuffix;

    @Convert(converter = FieldEncryptor.class)
    @Column(nullable = false)
    private String expiry;

}
