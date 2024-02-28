package com.authorizationapi.authorizationapi.controller;

import java.util.ArrayList;
import java.util.List;

import com.authorizationapi.authorizationapi.domain.PersonaDomain;
import com.authorizationapi.authorizationapi.service.business.PersonaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uco.doo.rugrats.uconnect.api.controller.response.Response;

@RestController
@RequestMapping("persona")
public final class PersonaController {
	private PersonaService service;
	@GetMapping("/dummy")
	public String dummy() {
		return "";
	}
	@PostMapping
	public ResponseEntity<Response<PersonaDomain>> create(@RequestBody PersonaDomain domain) {
		service = new PersonaService();

		var statusCode = HttpStatus.OK;
		Response<PersonaDomain> response = new Response<>();

		try {
			service.registrar(domain);
			response.getMessages().add("La persona se ha registrado exitosamente");

		} catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("No se ha podido registrar la persona");

		}
		return new ResponseEntity<>(response, statusCode);
	}
	@GetMapping
	public ResponseEntity<Response<PersonaDomain>> list(@RequestParam("dtoJson") String domainJson) {
		service = new PersonaService();

		var statusCode = HttpStatus.OK;
		Response<PersonaDomain> response;
		ObjectMapper objectMapper = new ObjectMapper();

		try {
			PersonaDomain domain = objectMapper.readValue(domainJson, PersonaDomain.class);
			List<PersonaDomain> list = service.consultar(domain);
			List<String> messages = new ArrayList<>();
			messages.add("Personas consultadas exitosamente");

			response = new Response<>(list,messages);

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response = new Response<>();
			response.getMessages().add("Error interno, no se ha podido realizar la consulta correctamente");
		}

		return new ResponseEntity<>(response,statusCode);
	}

}
