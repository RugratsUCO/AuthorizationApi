package com.authorizationapi.authorizationapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uco.doo.rugrats.uconnect.api.controller.response.Response;

@RestController
@RequestMapping("persona")
public final class PersonaController {
	
	@GetMapping("/dummy")
	public String dummy() {
		return "";
	}
	@GetMapping
	public ResponseEntity<Response<String>> list(@RequestBody String dto) {

		List<String> data = new ArrayList<>();
		data.add(dto);
		List<String> messages = new ArrayList<>();
		messages.add("Personas consultadas exitosamente");
		
		Response<String> response = new Response<>(data,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
