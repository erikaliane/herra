package modelos;

import java.util.Date;

public class Herramienta {
	private String codigo;
	private String nombre;
	private String codigo_barra;

	public Herramienta(String codigo, String nombre, String codigo_barra) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.codigo_barra = codigo_barra;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodigo_barra() {
		return codigo_barra;
	}
	public void setCodigo_barra(String codigo_barra) {
		this.codigo_barra = codigo_barra;
	}


	

}
