package com.example;

import java.awt.desktop.PrintFilesEvent;
import java.math.BigDecimal;
import java.nio.file.DirectoryStream.Filter;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Random;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import lombok.ToString;



public class App {
    public static void main(String[] args) {
    	
   	
    	List<Pasajero> listaInmutablePasajeros = BaseDatosVirtual.obtenerPasajeros();
    	List<Pasajero> pasajeros = new ArrayList<>(listaInmutablePasajeros);
    	List<Vuelo> vuelos = BaseDatosVirtual.obtenerVuelos();
    	
    	//añadir pasajeros a los vuelos
    	Random rand = new Random();
    	
    	for (Vuelo vuelo : vuelos) {
    			while (vuelo.getPlazas() > vuelo.getListaPasajeros().size()) {
    				
    				int indice = rand.nextInt(pasajeros.size());
					vuelo.getListaPasajeros().add(pasajeros.get(indice));
					pasajeros.remove(indice);
				}
		}
    	/* imprimir en pantalla
    	System.out.println(pasajeros);
    	System.out.println(vuelos);
    	*/
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
    	//2.1. Ejercicio al margen 1. En lugar de mostrar los vuelos, mostrar los destinos,separados por coma.
    	System.out.println("2.1. Ejercicio al margen 1. En lugar de mostrar los vuelos, mostrar los destinos,separados por coma.");
    	
    	System.out.println(vuelos.stream().filter(v -> v.getFechaSalida().equals(LocalDate.now()))
    		.map(v -> v.getDestino().toString()).collect(Collectors.joining(" , ")));
    	
    	//2.2. Ejercicio al margen 2. Obtener una lista con los destinos de cada vuelo que cumple la condición de fecha de salida en el día de hoy
    	
    	List<Destino> destinosHoy = vuelos.stream().filter(v -> v.getFechaSalida().equals(LocalDate.now()))
    			.map(Vuelo::getDestino).collect(Collectors.toList());
    	System.out.println(destinosHoy);
    	
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
    			.flatMap(p -> p.getListaPasajeros().stream())//.sorted() con sorted consigo ordenar como pide el pto. 8
    			.collect(Collectors.groupingBy(Pasajero::genero,
    					Collectors.groupingBy(Pasajero::getEdad)));
    	System.out.println(pasajerosGeneroEdad);
    	
    	//8. Mostrar la colección anterior ordenada por el nombre y los apellidos en orden natural.
    	System.out.println("8. Mostrar la colección anterior ordenada por el nombre y los apellidos en orden natural.");
    	
    	pasajerosGeneroEdad.values().forEach(mapaEdades -> 
        mapaEdades.values().forEach(lista -> lista.sort(null)));
        System.out.println(pasajerosGeneroEdad);
        
        //8.1. Ejercicio al margen 3. Ordenar la clave Genero del mapa anterior en orden alfabético inverso
        System.out.println("8.1. Ejercicio al margen 3. Ordenar la clave Genero del mapa anterior en orden alfabético inverso");
        Map<Genero, Map<Integer, List<Pasajero>>> pasajerosGeneroInversoEdad = 
        		new TreeMap<>(Comparator.comparing(Genero::name).reversed());
        
        pasajerosGeneroInversoEdad.putAll(pasajerosGeneroEdad);
        System.out.println(pasajerosGeneroInversoEdad);
        
        //9. Mostrar la colección del punto 7 ordenada en orden alfabético inverso por el
        //   primer apellido, sin modificar el orden natural de la clase Pasajero.
        System.out.println("9. Mostrar la colección del punto 7 ordenada en orden alfabético inverso por el ...");
        pasajerosGeneroEdad.values().forEach(mapaEdades -> 
        mapaEdades.values().forEach(lista -> lista
        	.sort(Comparator.comparing(Pasajero::primerApellido, String.CASE_INSENSITIVE_ORDER).reversed())));
        
        System.out.println(pasajerosGeneroEdad);
        
        //10. Obtener una colección que almacene el nombre y el apellido de los
        //   pasajeros, agrupado por las horas de duración de su viaje.
        System.out.println("10. Obtener una colección que almacene el nombre y el apellido de los ...");
        Map<Integer, List<String>> pasajerosDuracion = vuelos.stream()
        		.collect(Collectors.groupingBy(Vuelo::getDuracionHoras,
        				Collectors.flatMapping(v -> v.getListaPasajeros().stream()
        						.map(p -> p.nombre() + " " + p.primerApellido()), Collectors.toList())));
        System.out.println(pasajerosDuracion);
        
        //11. Mostrar el listado de pasajeros ordenado de mayor a menor por la duración del viaje.
        System.out.println("11. Mostrar el listado de pasajeros ordenado de mayor a menor por la duración del viaje.");
        Map<Integer, List<String>> pasajerosDuracionOrdenado = vuelos.stream()
        		.collect(Collectors.groupingBy(Vuelo::getDuracionHoras,
        				//TreeMap::new, con este simple añadido se ordena por duración.
        				() -> new TreeMap<>(Comparator.reverseOrder()),
        				Collectors.flatMapping(v -> v.getListaPasajeros().stream()
        						.map(p -> p.nombre() + " " + p.primerApellido()), Collectors.toList())));
        System.out.println(pasajerosDuracionOrdenado);
        
        vuelos.stream()
        .sorted(Comparator.comparing(Vuelo::getDuracionHoras).reversed())
        .flatMap(v -> v.getListaPasajeros().stream()
            .map(p -> "[" + v.getDuracionHoras() + "h] " + p.nombre() + " " + p.primerApellido()))
                .forEach(System.out::println);
       
        // 12.Recuperar el vuelo que tiene la máxima duración y mostrar sus pasajeros agrupados por género y edad
        System.out.println("12.Recuperar el vuelo que tiene la máxima duración y mostrar sus pasajeros agrupados por género y edad");
        vuelos.stream().max(Comparator.comparing(Vuelo::getDuracionHoras))
        .ifPresent(vueloMasHoras -> {
        	
        	System.out.println("Vuelo con: " + vueloMasHoras.getDuracionHoras() + " horas:");
        	System.out.println(vueloMasHoras.getListaPasajeros().stream()
        			.collect(Collectors.groupingBy(Pasajero::genero,
        			Collectors.groupingBy(Pasajero::getEdad))));
        });
        
        // 13.Enviar un mensaje a los pasajeros cuyo vuelo saldrá en las próximas 3 horas.
        System.out.println("13.Enviar un mensaje a los pasajeros cuyo vuelo saldrá en las próximas 3 horas.");
        
        LocalDateTime ahora = LocalDateTime.now();
        LocalDateTime ahoraMas3Horas = ahora.plusHours(3);
        
        vuelos.stream().filter(v -> {
        	LocalDateTime momentoSalidaVuelo = LocalDateTime.of(v.getFechaSalida(), v.getHoraSalida());
        	return momentoSalidaVuelo.isBefore(ahoraMas3Horas) && momentoSalidaVuelo.isAfter(ahora);
        }).flatMap(v -> v.getListaPasajeros().stream()).forEach(p ->
        	System.out.println("que sepa el pasajero " +p.nombre()+ p.primerApellido()
        			+ " su vuelo sale en menos de 3 horas."));
                
        // 14.Enviar un mensaje a los pasajeros cuyo vuelo saldrá en los próximos 3 días.
        
System.out.println("14.Enviar un mensaje a los pasajeros cuyo vuelo saldrá en los próximos 3 días.");
        
        LocalDateTime ahoraMas3Dias = ahora.plusDays(3);
        
        vuelos.stream().filter(v -> {
        	LocalDateTime momentoSalidaVuelo = LocalDateTime.of(v.getFechaSalida(), v.getHoraSalida());
        	return momentoSalidaVuelo.isBefore(ahoraMas3Dias) && momentoSalidaVuelo.isAfter(ahora);
        }).flatMap(v -> v.getListaPasajeros().stream()).forEach(p ->
        	System.out.println("que sepa el pasajero " +p.nombre()+ p.primerApellido()
        			+ " su vuelo sale en menos de 3 días."));
        
        /* 15.Crear una colección que almacene el listado de pasajeros agrupado por el
        día en que tiene lugar su vuelo, considerando que el vuelo tiene lugar en el
        mes en curso. Al mostrar la colección resultante, mostrar el nombre del día
        de la semana en español.
        */
        System.out.println("15.Crear una colección que almacene el listado de pasajeros agrupado por el ...");
        
        LocalDate hoy = LocalDate.now();
        Locale espanol = Locale.of("es", "ES");
        
        Map<LocalDate, List<Pasajero>> pasajerosDeMesCursoOrdenFecha =
        vuelos.stream().filter(v -> v.getFechaSalida().getMonth().equals(hoy.getMonth())
        		&& v.getFechaSalida().getYear() == hoy.getYear())
        	.collect(Collectors.groupingBy(Vuelo::getFechaSalida,
        			Collectors.flatMapping(v -> v.getListaPasajeros().stream(), Collectors.toList())));
        
        pasajerosDeMesCursoOrdenFecha.forEach((fecha, listaPasajeros) -> {
        	
        	System.out.println("el día " + fecha.getDayOfMonth() + " de este mes de " + 
        	fecha.getMonth().getDisplayName(TextStyle.FULL, espanol) + " que es " + 
        	fecha.getDayOfWeek().getDisplayName(TextStyle.FULL, espanol) + " los pasajeros que viajan son: ");
        	
        	System.out.println(listaPasajeros);
        	
        });
        
        // 16.Crear una colección de los vuelos que no están previstos para el mes en curso y
        //mostrar el nombre del mes para el cual está prevista su fecha de salida, en español.
        System.out.println("16.Crear una colección de los vuelos que no están previstos para el mes en curso y ...");
        
        Map<String, List<Vuelo>> vuelosPorMesExcluyeActual = vuelos.stream()
        		.filter(v -> !v.getFechaSalida().getMonth().equals(hoy.getMonth()))
        		.collect(Collectors.groupingBy(v -> v.getFechaSalida().getMonth().getDisplayName(TextStyle.FULL, espanol)));
        
       vuelosPorMesExcluyeActual.forEach((mes, listVuelos) -> {
    	   System.out.println("para el mes " + mes);
    	   System.out.println(listVuelos);
       }); 
    }
}
