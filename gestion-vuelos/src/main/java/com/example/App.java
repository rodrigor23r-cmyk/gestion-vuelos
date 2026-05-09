package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;



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
    	List<Vuelo> vueloPlazasCompletas = 
    		vuelos.stream().filter(v -> v.getPlazas() == v.getListaPasajeros().size()).collect(Collectors.toList());
    	
    	vueloPlazasCompletas.forEach(System.out::println);
    	
    	//2. Obtener un listado de los vuelos que tienen fecha de salida prevista para hoy.
    	System.out.println("2. Obtener un listado de los vuelos que tienen fecha de salida prevista para hoy.");
    	List<Vuelo> vuelosHoy = 
    		vuelos.stream().filter(v -> v.getFechaSalida().equals(LocalDate.now())).collect(Collectors.toList());
    	
    	vuelosHoy.forEach(System.out::println);
    	
    	//3. Obtener un listado de los vuelos cuya duración sea mayor de 10 horas
    	System.out.println("3. Obtener un listado de los vuelos cuya duración sea mayor de 10 horas");	
    	List<Vuelo> vuelosMas10Horas =
    		vuelos.stream().filter(v -> v.getDuracionHoras() > 10).collect(Collectors.toList());
    	
    	vuelosMas10Horas.forEach(System.out::println);
    	
    	//4. Obtener un listado de los vuelos que pueden demorar más de un día en llegar a su destino.
    	System.out.println("4. Obtener un listado de los vuelos que duren más de un día.");
    	List<Vuelo> vuelosMas1Dia = 
    		vuelos.stream().filter(v -> v.getDuracionHoras() > 24).collect(Collectors.toList());
    	
    	vuelosMas1Dia.forEach(System.out::println);
    	
    	System.out.println("=============== duración en horas de mis vuelos =============");
    	System.out.println(vuelos.stream().map(v -> v.getDuracionHoras())
    			.map(String::valueOf).collect(Collectors.joining(" , ")));
    	
    	//5. Obtener una colección que almacene un listado de pasajeros agrupado por el destino del vuelo
    	System.out.println("5. Obtener una colección con el listado de pasajeros agrupado por destino");
    	Map<Destino, List<Pasajero>> pasajerosPorDestino = vuelos.stream()
    		    .collect(Collectors.groupingBy(Vuelo::getDestino,
    		    Collectors.flatMapping(v -> v.getListaPasajeros().stream(), Collectors.toList())));
    	
    	pasajerosPorDestino.forEach((destino, lista) -> {
    		
    		System.out.println("______ " + destino + ". Pasajeros: ");
    		lista.forEach(l -> System.out.println(l.nombre() + " " + l.primerApellido()));
    	});
    	
    	//6. Colección de los vuelos que están programados salir en los últimos 10 días del mes en curso.
    	System.out.println("6. Colección de los vuelos que están programados salir en los últimos 10 días del mes en curso.");
    	
    	int ultimoDiaMes = LocalDate.now().lengthOfMonth();
    	int diaMesHoy = LocalDate.now().getDayOfMonth();
    	LocalDate ultiDiaMes = LocalDate.now().plusDays(ultimoDiaMes-diaMesHoy);
    	
    	List<Vuelo> diezUltiDiasMesActual = vuelos.stream()
    			.filter(v -> !v.getFechaSalida().isAfter(ultiDiaMes) && !v.getFechaSalida().isBefore(ultiDiaMes.minusDays(9)))
    			.collect(Collectors.toList());
    	
    	diezUltiDiasMesActual.forEach(v -> System.out.println(v.getDestino()+ " , "+ v.getFechaSalida()));
    	
    	//7. Crear una colección que almacene los pasajeros, por el genero y la edad
    	System.out.println("7. Crear una colección que almacene los pasajeros, por el genero y la edad");
    	
    	Map<Genero, Map<Integer, List<Pasajero>>> pasajerosGeneroEdad = vuelos.stream()
    			.flatMap(p -> p.getListaPasajeros().stream())
    			.collect(Collectors.groupingBy(Pasajero::genero,
    					Collectors.groupingBy(Pasajero::getEdad)));
    	System.out.println(pasajerosGeneroEdad);
    }
}
