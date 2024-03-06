package com.authorizationapi.authorizationapi.controller.estructura;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.domain.estructura.Participante;
import com.authorizationapi.authorizationapi.service.estructura.ParticipanteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("uconnect/api/v1")
public final class ParticipanteController {
	private final ParticipanteService service = new ParticipanteService();
	@PostMapping("/participante")
	public ResponseEntity<Response<Participante>> registrar(@RequestBody Participante participante) {


		var statusCode = HttpStatus.OK;
		Response<Participante> response = new Response<>();

		try {
			service.registrar(participante);
			response.getMessages().add("El participante se ha registrado exitosamente");

		} catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("No se ha podido registrar el participante");

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
				messages.add("Participantes consultados exitosamente");

			} else {
				statusCode = HttpStatus.NOT_FOUND;
				messages.add("No hay participantes para consultar");
			}

			response = new Response<>(list,messages);

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response = new Response<>();
			response.getMessages().add("Error interno, no se ha podido realizar la consulta correctamente");
		}

		return new ResponseEntity<>(response,statusCode);
	}
	@PutMapping("/participante/{identificador}")
	public ResponseEntity<Response<Participante>> cambiarEstado(@PathVariable UUID identificador,@RequestBody Participante participante) {

		var statusCode = HttpStatus.OK;
		var response = new Response<Participante>();

		try {
			service.cambiarEstado(identificador, participante);
			response.getMessages().add("Se ha cambiado el estado del participante satisfactoriamente");

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("No se ha podido cambiar el estado del participante");
		}

		return new ResponseEntity<>(response,statusCode);
	}
	@DeleteMapping("/participante/{identificador}")
	public ResponseEntity<Response<Participante>> eliminar(@RequestBody Participante participante) {

		var statusCode = HttpStatus.OK;
		var response = new Response<Participante>();

		try {
			service.eliminar(participante);
			response.getMessages().add("El participante se ha podido eliminar satisfactoriamente");

		}catch (Exception exception) {
			statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
			response.getMessages().add("No se ha podido eliminar al participante");
		}

		return new ResponseEntity<>(response,statusCode);
	}

}
