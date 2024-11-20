package com.taxi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "contactform")
public class ContactForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "Name Cannot be Empty")
	@Size(min = 3,max = 30,message = "Name size is Invalid")
	@Column(length = 30)
	private String name;
	
	@NotEmpty(message = "Email Cannot be Empty")
	@Size(min = 9,max = 50,message = "Email size is Invalid")
	@Column(length = 50)
	private String email;
	
	@NotNull(message = "Phone number cannot be Empty")
	@Min(value = 1000000000,message = "Phone size is Invalid")
	@Max(value = 9999999999L,message = "Phone size is Invalid")
	@Column(length = 10)
	private Long phone;
	
	@NotEmpty(message = "Message Cannot be Empty")
	@Size(min = 3,max = 500,message = "Message size is Invalid")
	@Column(length = 500)
	private String message;

}
