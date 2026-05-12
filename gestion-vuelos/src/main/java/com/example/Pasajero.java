package com.example;

//import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

import lombok.Builder;

@Builder
public record Pasajero(String nombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento,
		Genero genero) implements Comparable<Pasajero> {
	
	public int getEdad() {
		
		return Period.between(this.fechaNacimiento,	LocalDate.now()).getYears();
		 //return (int) ChronoUnit.YEARS.between(this.fechaNacimiento, LocalDate.now());// devuelve un Long
	}
	
	
	@Override
	
	public int compareTo(Pasajero pasajero) {
		int resultado = 0;
		if (this.nombre.compareTo(pasajero.nombre) == 0) {
			
			if (this.primerApellido.compareTo(pasajero.segundoApellido) == 0) {
				resultado = this.segundoApellido.compareTo(pasajero.segundoApellido);
			} else {
				resultado = this.primerApellido.compareTo(pasajero.primerApellido);
			}
				
		} else {
			resultado = this.nombre.compareTo(pasajero.nombre);
		} 
		 return resultado;
	}
	
	
	public String toString() {
	    return String.format(
	        
	        "\n  Pasajero: %s %s %s" +
	        "  Nacimiento: %s" +
	        "  Género: %s\n",
	        nombre, primerApellido(), segundoApellido(),
	        fechaNacimiento(), genero()
	    );
	}
	
	
	
}
