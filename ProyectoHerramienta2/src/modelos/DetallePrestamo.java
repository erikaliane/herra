package modelos;

import java.util.Date;

public class DetallePrestamo {
	public String nombre_usuario;
	public String nom_herramienta;
	
	public Date h_inicio;
	public Date h_fin;
	public DetallePrestamo(String nombre_usuario, String nom_herramienta, Date h_inicio, Date h_fin) {
		super();
		this.nombre_usuario = nombre_usuario;
		this.nom_herramienta = nom_herramienta;
		this.h_inicio = h_inicio;
		this.h_fin = h_fin;
	}
	public String getNombre_usuario() {
		return nombre_usuario;
	}
	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}
	public String getNom_herramienta() {
		return nom_herramienta;
	}
	public void setNom_herramienta(String nom_herramienta) {
		this.nom_herramienta = nom_herramienta;
	}
	public Date getH_inicio() {
		return h_inicio;
	}
	public void setH_inicio(Date h_inicio) {
		this.h_inicio = h_inicio;
	}
	public Date getH_fin() {
		return h_fin;
	}
	public void setH_fin(Date h_fin) {
		this.h_fin = h_fin;
	}
	
	

}
