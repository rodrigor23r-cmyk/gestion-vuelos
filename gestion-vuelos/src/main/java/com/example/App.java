package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
    	
   	
    	List<Pasajero> pasajeros = BaseDatosVirtual.obtenerPasajeros();
    	List<Vuelo> vuelos = BaseDatosVirtual.obtenerVuelos();
    	
    	// imprimir en pantalla
    	System.out.println(pasajeros);
    	System.out.println(vuelos);
    	// añadir pasajeros a los vuelos
    }
}
