package com.example;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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


	/*public void setPlazas (int plazas) {
		if (plazas > 3) {
			throw new IllegalArgumentException("El máximo permitido son 3 plazas");
		}
		this.plazas = plazas;
	}*/
	
	
	public int getDuracionHoras() {
	    ZonedDateTime salida = ZonedDateTime.of(this.fechaSalida, this.horaSalida, ZoneId.of("Europe/Madrid"));
	    ZonedDateTime llegada = ZonedDateTime.of(this.fechaLlegada, this.horaLlegada, this.destino.getHusoHorario());
	    
	    int duracion = (int) Duration.between(salida, llegada).toHours();
	    
	    return (duracion < 0 ) ? duracion+=24 : duracion;
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
