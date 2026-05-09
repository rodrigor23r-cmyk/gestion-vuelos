package com.example;

import java.time.ZoneId;

public enum Destino {
	BARCELONA(ZoneId.of("Europe/Madrid")),
	LIMA(ZoneId.of("America/Lima")), 
	TEHERAN(ZoneId.of("Asia/Tehran")), 
	CIUDAD_DEL_CABO(ZoneId.of("Africa/Johannesburg")), 
	VARSOVIA(ZoneId.of("Europe/Warsaw"));

	// lo que va entre paréntesis ======
	private final ZoneId husoHorario;
	
	// constructor =====================
	Destino(ZoneId husoHorario) {
		this.husoHorario = husoHorario;
	}
	
	// getter del huso horario =========
	public ZoneId getHusoHorario() {
		
		return this.husoHorario;
	}
	
	
	
}
