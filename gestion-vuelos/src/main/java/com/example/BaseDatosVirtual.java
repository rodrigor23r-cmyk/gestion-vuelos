package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import lombok.Builder;

// @Builder, no hace falta

public class BaseDatosVirtual {
	
	public static List<Vuelo> obtenerVuelos() {
		
    	Vuelo vuelo1 = Vuelo.builder()
       		    .destino(Destino.BARCELONA)
       		    .precio(BigDecimal.valueOf(100.05))
       		    .fechaSalida(LocalDate.now())
    		    .horaSalida(LocalTime.of(8, 0))
    		    .fechaLlegada(LocalDate.now())
    		    .horaLlegada(LocalTime.of(10, 0))
    		    .plazas(3)
    		    .listaPasajeros(new ArrayList<Pasajero>())
    		    .build();
		// --- VUELOS (4 restantes) ---

		// Vuelo a LIMA: Sale hoy mismo y dura más de 10 horas (Para el punto 2 y 3)
		Vuelo vuelo2 = Vuelo.builder()
		        .destino(Destino.LIMA)
		        .precio(BigDecimal.valueOf(850.75))
		        .fechaSalida(LocalDate.now()) 
		        .horaSalida(LocalTime.of(10, 0))
		        .fechaLlegada(LocalDate.now())
		        .horaLlegada(LocalTime.of(22, 30)) // Duración: 12.5 horas
		        .plazas(3)
		        .listaPasajeros(new ArrayList<>())
		        .build();

		// Vuelo a TEHERAN: Sale en los últimos 10 días del mes en curso (Para el punto 6)
		Vuelo vuelo3 = Vuelo.builder()
		        .destino(Destino.TEHERAN)
		        .precio(BigDecimal.valueOf(420.00))
		        .fechaSalida(LocalDate.now().withDayOfMonth(25)) // Día 25 del mes actual
		        .horaSalida(LocalTime.of(15, 0))
		        .fechaLlegada(LocalDate.now().withDayOfMonth(25))
		        .horaLlegada(LocalTime.of(21, 0))
		        .plazas(2) // Ponemos 2 plazas para dar variedad
		        .listaPasajeros(new ArrayList<>())
		        .build();

		// Vuelo a CIUDAD_DEL_CABO: Demora más de 1 día y no es en el mes en curso (Puntos 4 y 16)
		Vuelo vuelo4 = Vuelo.builder()
		        .destino(Destino.CIUDAD_DEL_CABO)
		        .precio(BigDecimal.valueOf(1200.50))
		        .fechaSalida(LocalDate.now().plusMonths(1).withDayOfMonth(5)) // Mes que viene
		        .horaSalida(LocalTime.of(23, 30))
		        .fechaLlegada(LocalDate.now().plusMonths(1).withDayOfMonth(7)) // Llega 2 días después
		        .horaLlegada(LocalTime.of(9, 15))
		        .plazas(2)
		        .listaPasajeros(new ArrayList<>())
		        .build();

		// Vuelo a VARSOVIA: Sale en las próximas 3 horas (Para el punto 13)
		Vuelo vuelo5 = Vuelo.builder()
		        .destino(Destino.VARSOVIA)
		        .precio(BigDecimal.valueOf(180.99))
		        .fechaSalida(LocalDate.now()) 
		        .horaSalida(LocalTime.now().plusHours(2)) // Sale en 2 horas exactas desde la ejecución
		        .fechaLlegada(LocalDate.now())
		        .horaLlegada(LocalTime.now().plusHours(5))
		        .plazas(3)
		        .listaPasajeros(new ArrayList<>())
		        .build();
		
		List<Vuelo> vuelos = List.of(vuelo1, vuelo2, vuelo3, vuelo4, vuelo5);
		
		return vuelos;
	}

	
	public static List<Pasajero> obtenerPasajeros () {
		
		Pasajero pasajero1 = Pasajero.builder()
    			.nombre("Duglas")
    			.primerApellido("López")
    			.segundoApellido("Martínez")
    			.fechaNacimiento(LocalDate.of(1990, Month.APRIL, 15))
    			.genero(Genero.HOMBRE)
    			.build();
		
		// --- PASAJEROS (19 restantes) ---

		Pasajero pasajero2 = Pasajero.builder().nombre("Ana").primerApellido("García").segundoApellido("Ruiz")
		        .fechaNacimiento(LocalDate.of(1985, Month.MARCH, 10)).genero(Genero.MUJER).build();

		Pasajero pasajero3 = Pasajero.builder().nombre("Carlos").primerApellido("Sánchez").segundoApellido("Pérez")
		        .fechaNacimiento(LocalDate.of(1972, Month.JULY, 22)).genero(Genero.HOMBRE).build();

		Pasajero pasajero4 = Pasajero.builder().nombre("Lucía").primerApellido("Gómez").segundoApellido("Navarro")
		        .fechaNacimiento(LocalDate.of(2005, Month.DECEMBER, 5)).genero(Genero.MUJER).build();

		Pasajero pasajero5 = Pasajero.builder().nombre("Miguel").primerApellido("Fernández").segundoApellido("Romero")
		        .fechaNacimiento(LocalDate.of(1960, Month.JANUARY, 30)).genero(Genero.HOMBRE).build();

		Pasajero pasajero6 = Pasajero.builder().nombre("Sofía").primerApellido("Torres").segundoApellido("Gil")
		        .fechaNacimiento(LocalDate.of(1995, Month.AUGUST, 14)).genero(Genero.MUJER).build();

		Pasajero pasajero7 = Pasajero.builder().nombre("Alex").primerApellido("Blanco").segundoApellido("Molina")
		        .fechaNacimiento(LocalDate.of(1998, Month.NOVEMBER, 2)).genero(Genero.OTRO).build();

		Pasajero pasajero8 = Pasajero.builder().nombre("Javier").primerApellido("Ruiz").segundoApellido("Díaz")
		        .fechaNacimiento(LocalDate.of(1988, Month.SEPTEMBER, 18)).genero(Genero.HOMBRE).build();

		Pasajero pasajero9 = Pasajero.builder().nombre("Carmen").primerApellido("Vázquez").segundoApellido("Iglesias")
		        .fechaNacimiento(LocalDate.of(1955, Month.FEBRUARY, 25)).genero(Genero.MUJER).build();

		Pasajero pasajero10 = Pasajero.builder().nombre("Raúl").primerApellido("Jiménez").segundoApellido("Marín")
		        .fechaNacimiento(LocalDate.of(2010, Month.OCTOBER, 11)).genero(Genero.HOMBRE).build();

		Pasajero pasajero11 = Pasajero.builder().nombre("Laura").primerApellido("Muñoz").segundoApellido("Ortega")
		        .fechaNacimiento(LocalDate.of(1992, Month.MAY, 3)).genero(Genero.MUJER).build();

		Pasajero pasajero12 = Pasajero.builder().nombre("Diego").primerApellido("Alonso").segundoApellido("Rubio")
		        .fechaNacimiento(LocalDate.of(1979, Month.JUNE, 16)).genero(Genero.HOMBRE).build();

		Pasajero pasajero13 = Pasajero.builder().nombre("Patricia").primerApellido("Gutiérrez").segundoApellido("Cano")
		        .fechaNacimiento(LocalDate.of(1983, Month.APRIL, 27)).genero(Genero.MUJER).build();

		Pasajero pasajero14 = Pasajero.builder().nombre("Sam").primerApellido("Reyes").segundoApellido("Ortiz")
		        .fechaNacimiento(LocalDate.of(2001, Month.JANUARY, 9)).genero(Genero.OTRO).build();

		Pasajero pasajero15 = Pasajero.builder().nombre("Marta").primerApellido("Garrido").segundoApellido("Cortés")
		        .fechaNacimiento(LocalDate.of(1968, Month.DECEMBER, 20)).genero(Genero.MUJER).build();

		Pasajero pasajero16 = Pasajero.builder().nombre("Hugo").primerApellido("Lázaro").segundoApellido("Prieto")
		        .fechaNacimiento(LocalDate.of(2015, Month.JULY, 7)).genero(Genero.HOMBRE).build();

		Pasajero pasajero17 = Pasajero.builder().nombre("Alba").primerApellido("Méndez").segundoApellido("Cruz")
		        .fechaNacimiento(LocalDate.of(1990, Month.SEPTEMBER, 30)).genero(Genero.MUJER).build();

		Pasajero pasajero18 = Pasajero.builder().nombre("Roberto").primerApellido("Herrera").segundoApellido("Flores")
		        .fechaNacimiento(LocalDate.of(1975, Month.MARCH, 15)).genero(Genero.HOMBRE).build();

		Pasajero pasajero19 = Pasajero.builder().nombre("Teresa").primerApellido("Cabrera").segundoApellido("Peña")
		        .fechaNacimiento(LocalDate.of(1981, Month.AUGUST, 24)).genero(Genero.MUJER).build();

		Pasajero pasajero20 = Pasajero.builder().nombre("Mario").primerApellido("Santana").segundoApellido("Gallego")
		        .fechaNacimiento(LocalDate.of(1997, Month.FEBRUARY, 12)).genero(Genero.HOMBRE).build();
		
		List<Pasajero> pasajeros = List.of(pasajero1, pasajero2, pasajero3, pasajero4, pasajero5, pasajero6, pasajero7,
				pasajero8, pasajero9, pasajero9, pasajero10, pasajero11, pasajero12, pasajero13, pasajero14, 
				pasajero15, pasajero16, pasajero17, pasajero18, pasajero19, pasajero20);
				
		return pasajeros;
		
	}
}
