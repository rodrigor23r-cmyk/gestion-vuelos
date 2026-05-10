package com.example;

import java.time.LocalDate;
import java.time.Period;

import lombok.Builder;

@Builder
public record Pasajero(String nombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento,
		Genero genero) implements Comparable<Pasajero> {
	
	public int getEdad() {
		
		return Period.between(this.fechaNacimiento,	LocalDate.now()).getYears();
		
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
	        "\n================ FICHA DE PASAJERO ================\n" +
	        "  Nombre completo: %s %s %s\n" +
	        "  Nacimiento     : %s\n" +
	        "  Género         : %s\n",
	        nombre, primerApellido(), segundoApellido(),
	        fechaNacimiento(), genero()
	    );
	}
	
	
	
}
