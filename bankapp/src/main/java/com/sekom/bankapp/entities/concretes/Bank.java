package com.sekom.bankapp.entities.concretes;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="banks")
@Entity
public class Bank {
	@Id
	@Column(name="bank_id")
	private int bankId;
	
	@NotBlank
	@NotNull
	@Column(name="bank_name")
	private String bankName;
}

