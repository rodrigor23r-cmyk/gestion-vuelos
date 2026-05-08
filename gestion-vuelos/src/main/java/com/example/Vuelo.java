package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
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

	//@Override
	public void setPlazas (int plazas) {
		if (plazas > 3) {
			throw new IllegalArgumentException("El máximo permitido son 3 plazas");
		}
		this.plazas = plazas;
	}
}
