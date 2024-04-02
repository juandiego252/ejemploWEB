package com.krakedev.entidades;

public class Cliente {

	private String cedula;
	private String apellido;

	@Override
	public String toString() {
		return "Cliente [cedula=" + cedula + ", apellido=" + apellido + "]";
	}

	public Cliente() {
		super();
	}

	public Cliente(String cedula, String apellido) {
		super();
		this.cedula = cedula;
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

}
