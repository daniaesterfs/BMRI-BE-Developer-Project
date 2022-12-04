package com.example.bankmandiriproject.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "mst_transaction")
@Data
public class Transaction {

    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    private String transactionId;

    @ManyToOne
    @JoinColumn(name = "account_number")
    @JsonBackReference
    private Account account;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_sender")
    private String accountSender;

    @Column(name = "account_receiver")
    private String accountReceiver;

    @Column(name = "account_activity_type")
    private String accActivityType;


}
