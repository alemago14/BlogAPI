package com.api.dto;

import java.util.Date;

public class ErrorDetalles {

	//atributos
	private Date marcaDeTiempo;
	private String mensaje;
	private String detalles;
	
	
	//constructores
	public ErrorDetalles(Date marcaDeTiempo, String mensaje, String detalles) {
		this.marcaDeTiempo = marcaDeTiempo;
		this.mensaje = mensaje;
		this.detalles = detalles;
	}
	
	public ErrorDetalles() {
	}

	//getters y setters
	public Date getMarcaDeTiempo() {
		return marcaDeTiempo;
	}

	public void setMarcaDeTiempo(Date marcaDeTiempo) {
		this.marcaDeTiempo = marcaDeTiempo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getDetalles() {
		return detalles;
	}

	public void setDetalles(String detalles) {
		this.detalles = detalles;
	}
	
}
