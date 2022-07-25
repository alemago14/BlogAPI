package com.api.dto;

import java.util.List;

public class PublicacionRespuesta {

	private List<PublicacionDTO> contenido;
	private int numPagina;
	private int medidaDePagina;
	private Long totalElementos;
	private int totalPaginas;
	private boolean ultima;
	public List<PublicacionDTO> getContenido() {
		return contenido;
	}
	public void setContenido(List<PublicacionDTO> contenido) {
		this.contenido = contenido;
	}
	public int getNumPagina() {
		return numPagina;
	}
	public void setNumPagina(int numPagina) {
		this.numPagina = numPagina;
	}
	public int getMedidaDePagina() {
		return medidaDePagina;
	}
	public void setMedidaDePagina(int medidaDePagina) {
		this.medidaDePagina = medidaDePagina;
	}
	public Long getTotalElementos() {
		return totalElementos;
	}
	public void setTotalElementos(Long totalElementos) {
		this.totalElementos = totalElementos;
	}
	public int getTotalPaginas() {
		return totalPaginas;
	}
	public void setTotalPaginas(int totalPaginas) {
		this.totalPaginas = totalPaginas;
	}
	public boolean isUltima() {
		return ultima;
	}
	public void setUltima(boolean ultima) {
		this.ultima = ultima;
	}
	
	
}
