package com.authorizationapi.authorizationapi.controller.estructura;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.estructura.Participante;
import com.authorizationapi.authorizationapi.service.estructura.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("authorization/api/v1")
public final class ParticipanteController {

	@Autowired
	private final ParticipanteService service = new ParticipanteService();
	@PostMapping("/participante")
	public ResponseEntity<Response<Participante>> registrar(@RequestBody Participante participante) {

		var statusCode = HttpStatus.OK;
		Response<Participante> response = new Response<>();

		try {
			service.registrar(participante);
			response.getMessages().add(UtilMessagesController.ControllerParticipante.PARTICIPANTE_CREADO_FINAL);

		} catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UtilMessagesController.ControllerParticipante.PARTICIPANTE_NO_CREADO_FINAL);

		}
		return new ResponseEntity<>(response, statusCode);
	}
	@GetMapping("/participante")
	public ResponseEntity<Response<Participante>> consultar() {

		var statusCode = HttpStatus.OK;
		Response<Participante> response;

		try {
			List<String> messages = new ArrayList<>();
			List<Participante> list = service.consultar();

			if (!list.isEmpty()) {
				messages.add(UtilMessagesController.ControllerParticipante.PARTICIPANTES_CONSULTADOS_FINAL);

			} else {
				statusCode = HttpStatus.NOT_FOUND;
				messages.add(UtilMessagesController.ControllerParticipante.PARTICIPANTES_NO_CONSULTADOS_FINAL);
			}

			response = new Response<>(list,messages);

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response = new Response<>();
			response.getMessages().add(UtilMessagesController.ControllerParticipante.PARTICIPANTES_NO_CONSULTADOS_INTERNO_FINAL);
		}

		return new ResponseEntity<>(response,statusCode);
	}
	@PutMapping("/participante/{identificador}")
	public ResponseEntity<Response<Participante>> cambiarEstado(@PathVariable UUID identificador,@RequestBody Participante participante) {

		var statusCode = HttpStatus.OK;
		var response = new Response<Participante>();

		try {
			service.cambiarEstado(identificador, participante);
			response.getMessages().add(UtilMessagesController.ControllerParticipante.PARTICIPANTE_ESTADO_EDITADO_FINAL);

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UtilMessagesController.ControllerParticipante.PARTICIPANTE_ESTADO_NO_EDITADO_FINAL);
		}

		return new ResponseEntity<>(response,statusCode);
	}
	@DeleteMapping("/participante/{identificador}")
	public ResponseEntity<Response<Participante>> eliminar(@RequestBody Participante participante) {

		var statusCode = HttpStatus.OK;
		var response = new Response<Participante>();

		try {
			service.eliminar(participante);
			response.getMessages().add(UtilMessagesController.ControllerParticipante.PARTICIPANTE_ELIMINADO_FINAL);

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add(UtilMessagesController.ControllerParticipante.PARTICIPANTE_NO_ELIMINADO_FINAL);
		}

		return new ResponseEntity<>(response,statusCode);
	}

}