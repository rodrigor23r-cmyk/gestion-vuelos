package com.example;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class Vuelo {
	
	private Destino destino;
	private BigDecimal precio;
	private LocalDate fechaSalida;
	private LocalTime horaSalida;
	private LocalDate fechaLlegada;
	private LocalTime horaLlegada;
	private int plazas;
	private List<Pasajero> listaPasajeros;


	public void setPlazas (int plazas) {
		if (plazas > 3) {
			throw new IllegalArgumentException("El máximo permitido son 3 plazas");
		}
		this.plazas = plazas;
	}
	
	
	public int getDuracionHoras() {
	    LocalDateTime salida = LocalDateTime.of(this.fechaSalida, this.horaSalida);
	    LocalDateTime llegada = LocalDateTime.of(this.fechaLlegada, this.horaLlegada);
	    
	    return (int) Duration.between(salida, llegada).toHours();
	}
	
	
	/*
	public String toString() {
	    return String.format(
	        "\n================ FICHA DE EMPLEADO ================\n" +
	        "  Nombre completo: %s %s %s\n" +
	        "  Nacimiento     : %s\n" +
	        "  Género         : %s\n" +
	        "---------------------------------------------------\n" +
	        "  Departamento   : %s\n" +
	        "  Fecha de Alta  : %s\n" +
	        "  Salario        : %.2f €\n" + 
	        "===================================================\n",
	        super.getNombre(), super.getPrimerApellido(), super.getSegundoApellido(), // Atributos de Persona
	        super.getFechaNacimiento(), super.getGenero(),
	        this.getDpto(), this.getFechaAlta(), this.getSalario()                    // Atributos de Empleado
	    );
	}


	@Override  // implements Comparable
	
	public int compareTo(Empleado emp) {
		
		return this.fechaAlta.compareTo(emp.fechaAlta);
	}
	*/
	
	
}
