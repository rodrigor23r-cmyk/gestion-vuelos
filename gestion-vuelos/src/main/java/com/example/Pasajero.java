package com.example;

import java.time.LocalDate;
import java.time.Period;

import lombok.Builder;

@Builder
public record Pasajero(String nombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento,
		Genero genero) {
	
	public int getEdad() {
		
		return Period.between(this.fechaNacimiento,	LocalDate.now()).getYears();
		
	}
}
