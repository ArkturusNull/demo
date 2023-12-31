package com.chatbot.demo.util;

import java.util.ArrayList;
import java.util.List;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;

public class GestorTablaPreguntas {

	private HojaCalculo hoja = new HojaCalculo();
	private List<List<Object>> respuestas, preguntas;
	private List<String> pregunta = new ArrayList<>();

	private Response respActual = new Response();

	private String idRespPadre = "N/A";

	private int nivelPreguntas = 0;

	// Constructors
	public GestorTablaPreguntas() {
		/*try {
			respuestas = hoja.getDataFromSheet();
		} catch (GoogleJsonResponseException gje) {
			gje.printStackTrace();
		}
		preguntas = hoja.getDescripcionesPreguntas1();*/
		 respuestas = hoja.serviceCredentials();
		 preguntas = hoja.getDescripcionesPreguntas2();

		for (List row : preguntas) {
			pregunta.add(String.valueOf(row.get(1)));

		}
	}

	/**
	 * Metodo llamado para avanzar a la siguiente pregunta o volver a la anterior.
	 * 
	 * @param idRespuesta es el numero de la respuesta seleccionada
	 * 
	 */
	public void cambiarDeNivel(int idRespuesta) {
		// List<String> options = new ArrayList<>();
		List<String> options = new ArrayList<>();
		List<Boolean> opsBloqueadas = new ArrayList<>();

		options.add("Inicio");
		opsBloqueadas.add(false);

		int respAtras = respActual.getOptions().size() - 1;

		if(nivelPreguntas != 1) {
			if (idRespuesta == respAtras) {
				nivelPreguntas--;
				if (nivelPreguntas != 1) {
					idRespPadre = idRespPadre.substring(0, idRespPadre.length() - 2);
				} else {
					options.remove(0);
					idRespPadre = "N/A";

				}

			} else {
				if (idRespuesta == 0) {
					nivelPreguntas = 1;
					idRespPadre = "N/A";
					options.remove(0);
					opsBloqueadas.remove(0);

				} else {
						idRespPadre = idRespPadre + "." + String.valueOf(idRespuesta);
						nivelPreguntas++;
				}
			}
			
		}else {
			idRespPadre = String.valueOf(idRespuesta + 1);
			nivelPreguntas++;
		}
		
		for (List row : respuestas) {
			String nivel = String.valueOf(row.get(1));
			String idPadre = String.valueOf(row.get(3));

			if (Integer.parseInt(nivel) == nivelPreguntas && idPadre.equals(idRespPadre)) {
				options.add(String.valueOf(row.get(2)));
				if (String.valueOf(row.get(4)).equals("1")) {
					opsBloqueadas.add(true);
				} else {
					opsBloqueadas.add(false);

				}
			}
		}

		if (nivelPreguntas != 1) {
			options.add("Atras");
			opsBloqueadas.add(false);
		}
		int numPregunta = (int) (Math.random() * pregunta.size());

		respActual.setDescription(pregunta.get(numPregunta));
		respActual.setOptions(options);
		respActual.setBloquedOptions(opsBloqueadas);

	}
	
	
	public Response getRespActual() {
		return respActual;
	}

	public void setRespActual(Response respActual) {
		this.respActual = respActual;
	}

	public String getIdRespAnterior() {
		return idRespPadre;
	}

	public void setIdRespAnterior(String idRespAnterior) {
		this.idRespPadre = idRespAnterior;
	}

}
