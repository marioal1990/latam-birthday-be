package com.latam.pax.lbb.domains;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotBlank(message = "El campo Nombre es requerido")
	private String name;
	
	@NotBlank(message = "El campo Apellido es requerido")
	private String lastName;
	
	@NotBlank(message = "El campo Fecha de Nacimiento es requerido")
	private String birthday;
	
	private Integer age;
	
	@Column(length = 3000)
	private String poem;
	
	private String daysLeft;
}
