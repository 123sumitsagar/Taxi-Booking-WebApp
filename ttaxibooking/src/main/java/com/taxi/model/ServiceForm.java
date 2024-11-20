package com.taxi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "serviceform")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ServiceForm {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;
	public String image;
	public String title;
	public String description;
}
