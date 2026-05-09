package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;



public class App {
    public static void main(String[] args) {
    	
   	
    	List<Pasajero> pasajeros = BaseDatosVirtual.obtenerPasajeros();
    	List<Vuelo> vuelos = BaseDatosVirtual.obtenerVuelos();
    	
    	// añadir pasajeros a los vuelos
    	int i = 0;
    	
    	for (Vuelo vuelo : vuelos) {
    			while (vuelo.getPlazas() > vuelo.getListaPasajeros().size()) {
				
					vuelo.getListaPasajeros().add(pasajeros.get(i));
					i++;
				}
		}
    	// imprimir en pantalla
    	System.out.println(pasajeros);
    	System.out.println(vuelos);
    	System.out.println(i);
    	// vuelos que tinen más pasajeros==============version FOR ===========================
    	int maximo = 0;
    	for (Vuelo vuelo : vuelos) {
    		if ( maximo < vuelo.getListaPasajeros().size()) {
    			maximo = vuelo.getListaPasajeros().size();
    		}	
		}
    	System.out.println("========== vuelos con más pasajeros. total: " + maximo);
    	for (Vuelo vuelo : vuelos) {
			if (vuelo.getListaPasajeros().size() == maximo) {
				System.out.println(vuelo);
			}
		}
    	System.out.println("============= vuelos con máx pasajeros con Stream ============");
    	int maxi =vuelos.stream()
    			.mapToInt(v -> v.getListaPasajeros().size()).max().orElse(0);
    	/*OptionalInt maxi =vuelos.stream()
    			.mapToInt(v -> v.getListaPasajeros().size()).max();
    	*/
    	vuelos.stream()
    			.filter(v -> v.getListaPasajeros().size() == maxi)
    			.forEach(System.out::println);
    	
    	//1. Obtener un listado de los vuelos que tienen el número de plazas completo.
    	System.out.println("1. Obtener un listado de los vuelos que tienen el número de plazas completo.");
    	vuelos.stream().filter(v -> v.getPlazas() == v.getListaPasajeros().size())
    		.forEach(System.out::println);
    	//2. Obtener un listado de los vuelos que tienen fecha de salida prevista para hoy.
    	System.out.println("2. Obtener un listado de los vuelos que tienen fecha de salida prevista para hoy.");
    	vuelos.stream().filter(v -> v.getFechaSalida().equals(LocalDate.now()))
    		.forEach(System.out::println);
    	
    	//3. Obtener un listado de los vuelos cuya duración sea mayor de 10 horas
    	System.out.println("3. Obtener un listado de los vuelos cuya duración sea mayor de 10 horas");	
    	vuelos.stream().filter(v -> v.getDuracionHoras() > 10).forEach(System.out::println);
    	
    	//4. Obtener un listado de los vuelos que pueden demorar más de un día en llegar a su destino.
    	System.out.println("4. Obtener un listado de los vuelos que duren más de un día.");
    	vuelos.stream().filter(v -> v.getDuracionHoras() > 24).forEach(System.out::println);
    	
    	
    	
    	
    }
}
