package com.users.apirestful.apirest.dto;

public class ResponseDTO {

	public static final String INVALID_PASSWORD = "La clave ingresada no es válida.";

	public static final String INVALID_MAIL = "Correo electrónico no válido.";

	public static final String EXIST_MAIL = "El correo ya registrado.";

	private String mensaje;
	
	public String getErrorMessage(String mensaje) {
		return "{\"mensaje\": \"" + mensaje + "\"}";
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	
}
