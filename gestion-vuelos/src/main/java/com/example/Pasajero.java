package com.example;

import java.time.LocalDate;

import lombok.Builder;

@Builder
public record Pasajero(String nombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento,
		Genero genero) {
}
