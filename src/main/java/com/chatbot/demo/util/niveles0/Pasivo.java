package com.chatbot.demo.util.niveles0;

import java.util.ArrayList;
import java.util.Arrays;

import com.chatbot.demo.util.GestorMenus;

public class Pasivo {

	private String salida = "Elija el numero del asunto que desee consultar: \n\t0. Salir\n";
	private GestorMenus gestMenus;
	
	// Opciones
	private ArrayList<String> nivel1 = new ArrayList<>(Arrays.asList("CUENTA BBVA", "CUENTA CX", "IPF", "FONDO"));

	// Flujo cuenta BBVA
	private ArrayList<String> cuentabbva, cuentabbva1, cuentabbva2;

	public Pasivo(GestorMenus gestMenus) {

		this.gestMenus = gestMenus;
		
		nivel1 = new ArrayList<>(Arrays.asList("CUENTA BBVA", "CUENTA CX", "IPF", "FONDO"));

		cuentabbva = new ArrayList<>(
				Arrays.asList("Disconformidad con el cobro de comisión de administración y mantenimiento",
						"Información sobre la cuenta remunerada", "Aclaraciones sobre un cobro de descubierto"));

		cuentabbva1 = new ArrayList<>(Arrays.asList(
				"¿Ha localizado en el FRONT de BONIFICACIONES el INDICADOR de cuyo cumplimiento es objeto la incidencia?",
				"Informacion de la cuenta",
				"¿Ha localizado en el FRONT de BONIFICACIONES el INDICADOR de cuyo cumplimiento es objeto la incidencia?"));

		cuentabbva2 = new ArrayList<>(Arrays.asList(
				"Si es así, siga las indicaciones que le dice el FRONT y pase la incidencia al equipo que se le indica, para que confirme el valor de ese INDICADOR\r\n",
				"Si no es así, y ya lo ha consultado con su responsable, entonces pase la incidencia a ANS CATALOGO Y CONDICIONES"));

	}

	// Methods
	public String getTextoNivel1() {
		String msg = salida;

		for (int i = 0; i < nivel1.size(); i++) {
			msg += "\t" + (i + 1) + ". " + nivel1.get(i) + "\n";
		}

		msg += "Eleccion: ";
		return msg;

	}

	public String pasarNivel1ANivel2(String respuesta) {
		String msg = "";

		try {
			int resp = Integer.parseInt(respuesta);
			msg = salida;
			switch (resp) {
			case 1:
				for (int i = 0; i < cuentabbva.size(); i++) {
					msg += "\t" + (i + 1) + ". " + cuentabbva.get(i) + "\n";
				}

				msg += "Eleccion: ";
				break;
			case 2:
			case 3:
			}

		} catch (NumberFormatException ex) {
			msg = salida;

			String resp = respuesta.toLowerCase();

		}

		return msg;
	}

	public String pasarNivel2ANivel3(String respuesta) {
		String msg = "";

		try {
			int resp = Integer.parseInt(respuesta);
			switch (resp) {
			case 1:
				for (int i = 0; i < cuentabbva1.size(); i++) {
					msg += "\t" + (i + 1) + ". " + cuentabbva1.get(i) + "\n";
				}

				for (int i = 0; i < cuentabbva2.size(); i++) {
					msg += "\t" + (i + 1) + ". " + cuentabbva2.get(i) + "\n";
				}
				gestMenus.setNivelPreguntas(0);
				break;
			case 2:
			case 3:
			}

		} catch (NumberFormatException ex) {
			msg = salida;

			String resp = respuesta.toLowerCase();

		}

		return msg;
	}
	
	
	

	public ArrayList<String> getNivel1() {
		return nivel1;
	}

	public void setNivel1(ArrayList<String> nivel1) {
		this.nivel1 = nivel1;
	}

	public ArrayList<String> getCuentabbva() {
		return cuentabbva;
	}

	public void setCuentabbva(ArrayList<String> cuentabbva) {
		this.cuentabbva = cuentabbva;
	}

	public ArrayList<String> getCuentabbva1() {
		return cuentabbva1;
	}

	public void setCuentabbva1(ArrayList<String> cuentabbva1) {
		this.cuentabbva1 = cuentabbva1;
	}

	public ArrayList<String> getCuentabbva2() {
		return cuentabbva2;
	}

	public void setCuentabbva2(ArrayList<String> cuentabbva2) {
		this.cuentabbva2 = cuentabbva2;
	}

}
