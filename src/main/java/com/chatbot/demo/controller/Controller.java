package com.chatbot.demo.controller;

import com.chatbot.demo.util.Response;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot.demo.util.GestorTablaPreguntas;

@RestController
public class Controller {

	private GestorTablaPreguntas gestPreg;
	
	@CrossOrigin(origins = "https://chat-bbva.onrender.com", allowedHeaders = "Requestor-Type", exposedHeaders = "X-Get-Header")
	@GetMapping("getData")
	public Response readGoogleSheet() {
		gestPreg = new GestorTablaPreguntas();
		gestPreg.cambiarDeNivel(0);
		
		return gestPreg.getRespActual();
	}

	@PostMapping("sendSelection")
	@ResponseBody
	public Response sendSelectedOption(@RequestBody OpcionSeleccionada opcion) {
		gestPreg.cambiarDeNivel(opcion.getRespuesta()); 
		
		return gestPreg.getRespActual();
	}

}
