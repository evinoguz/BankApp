package com.sekom.bankapp.entities.concretes;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="transactions")
@Entity
public class Transaction {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="transaction_id")
	private int transactionId;
	
	@NotBlank
	@NotNull
	@Column(name="message")
	private String message;
		
	@Column(name="created_date")
	private LocalDateTime createdDate=LocalDateTime.now();
	
	@ManyToOne()
	@JoinColumn(name="account_id")
	private AccountUser account;
}
