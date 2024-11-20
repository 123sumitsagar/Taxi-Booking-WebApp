package com.taxi.model;
import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookingform")
public class BookingForm {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "name cant be empty")
	@NotBlank(message = "name cant be blank")
	@Size(min = 2,max = 30,message = "invalid name length")
	@Pattern(regexp ="^[A-Za-z]+$" ,message = "name must contain only alphabet")
	@Column(length = 30)
	private String name;
	
	@NotEmpty(message = "source cant be empty")
	@NotBlank(message = "source cant be blank")
	@Size(min = 2,max = 300,message = "invalid source length")
	@Column(length = 300)
	private String source;
	
	@NotEmpty(message = "email cant be empty")
	@NotBlank(message = "email cant be blank")
	@Size(min = 2,max = 50,message = "invalid email length")
	@Column(length = 50)
	private String email;
	
	@NotEmpty(message = "destination cant be empty")
	@NotBlank(message = "destination cant be blank")
	@Size(min = 2,max = 100,message = "invalid destination length")
	@Column(length = 100)
	private String destination;
	
	@NotNull(message = "time cant be empty")
	private LocalTime time;
	
	@NotNull(message = "date cant be empty")
	private LocalDate date;
	
	@NotEmpty(message = "comfort cant be empty")
	private String comfort;
	
	@Max(value = 4,message = "adult can be at most 4")
	@Min(value = 1,message = "adult can be at least 1")
    private int adult;
	
	@Max(value = 3,message = "children can be at most 3")
    private int children;
	
	@NotEmpty(message = "message cant be empty")
	@NotBlank(message = "message cant be blank")
	@Size(min = 5,max = 500,message = "invalid message length length")
	@Column(length = 500)
    private String message;

}
