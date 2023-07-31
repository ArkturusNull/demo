package com.chatbot.demo.controller;

import com.chatbot.demo.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.chatbot.demo.util.GestorMenus;
import com.chatbot.demo.util.GestorTablaPreguntas;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

	private GestorMenus gestMenu = new GestorMenus();
	private GestorTablaPreguntas gestPreg;
	
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
