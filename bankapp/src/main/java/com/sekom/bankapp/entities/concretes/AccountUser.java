package com.sekom.bankapp.entities.concretes;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
public class AccountUser {
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="account_id")
	private int accountId;
	
	@NotBlank
	@NotNull
	@Column(name="balance")
	private double balance;
	
	@NotBlank
	@NotNull
	@Column(name="created_date")
	private LocalDateTime createdDate;
	
	@ManyToOne()
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne()
	@JoinColumn(name="bank_id")
	private Bank bank;
}
