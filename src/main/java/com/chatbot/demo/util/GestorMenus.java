package com.chatbot.demo.util;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;

import com.chatbot.demo.util.niveles0.Pasivo;

public class GestorMenus {

	private Pasivo pasivo = new Pasivo(this);

	private ArrayList<String> nivel0 = new ArrayList<>(Arrays.asList("pasivo", "activo", "tarjetas"));
	private String salidaInicial = "Elija el numero del asunto que desee consultar:Salir Pasivo Activo Tarjetas";
	private String salida = "Elija el numero del asunto que desee consultar: \n\t0. Salir\n";

	// Numero para comprobar si tratamos Pasivo(1), Activo(2) o Tarjetas(3)
	private int tipoConsulta = 0;

	private int nivelPreguntas = 0;
	private int numRespuestas = 3;

	// Constructors
	public GestorMenus() {
	}

	// Methods
	// Establecemos el flujo a seguir eligiendo entre las opciones del nivel 0.
	public String comprobarNivel0(String respuesta) {
		String msg = "Introduzca una opcion valida.";

		try {
			int resp = Integer.parseInt(respuesta);
			msg = salida;

			if (resp == 0) {
				msg = "Pase un buen dia.";

			} else if (resp >= 1 && resp <= 3) {
				tipoConsulta = resp;
				comprobarRespuesta(respuesta);
			}

		} catch (NumberFormatException ex) {
			msg = salida;

			String resp = respuesta.toLowerCase();
			if (resp.equals("salir")) {
				msg = "Pase un buen dia.";

			} else if (nivel0.contains(resp)) {
				boolean encontrado = false;
				for (int i = 0; i < nivel0.size() && !encontrado; i++) {
					if (nivel0.get(i).equals(resp)) {
						tipoConsulta = i;
						comprobarRespuesta(respuesta);
						encontrado = true;
					}
				}
			}

		}
		return msg;
	}

	public String comprobarRespuesta(String respuesta) {
		String msg = "Introduzca una opcion valida.";

		switch (tipoConsulta) {
		case 0:
			comprobarNivel0(respuesta);
			break;
		case 1:
			// Pasivo
			switch (nivelPreguntas) {
			case 0:
				msg = pasivo.getTextoNivel1();
				nivelPreguntas++;
				break;

			case 1:
				msg = pasivo.pasarNivel1ANivel2(respuesta);
				nivelPreguntas++;
				break;

			case 2:
				msg = pasivo.pasarNivel2ANivel3(respuesta);
				break;

			case 3:
				break;

			case 4:
				break;
			}

			break;
		case 2:
			// Activo

			break;
		case 3:
			// Tarjetas

			break;
		}

		return msg;
	}

	// Getter, Setter
	public Pasivo getPasivo() {
		return pasivo;
	}

	public void setPasivo(Pasivo pasivo) {
		this.pasivo = pasivo;
	}

	public String getSalidaInicial() {
		return salidaInicial;
	}

	public void setSalidaInicial(String salidaInicial) {
		this.salidaInicial = salidaInicial;
	}

	public int getNivelPreguntas() {
		return nivelPreguntas;
	}

	public void setNivelPreguntas(int nivelPreguntas) {
		this.nivelPreguntas = nivelPreguntas;
	}

	public String getSalida() {
		return salida;
	}

	public void setSalida(String salida) {
		this.salida = salida;
	}

	public int getTipoConsulta() {
		return tipoConsulta;
	}

	public void setTipoConsulta(int tipoConsulta) {
		this.tipoConsulta = tipoConsulta;
	}

	public int getNumRespuestas() {
		return numRespuestas;
	}

	public void setNumRespuestas(int numRespuestas) {
		this.numRespuestas = numRespuestas;
	}

}
