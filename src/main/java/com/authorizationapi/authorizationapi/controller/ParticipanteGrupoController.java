package uco.doo.rugrats.uconnect.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import uco.doo.rugrats.uconnect.api.controller.response.Response;
import uco.doo.rugrats.uconnect.busisness.facade.ParticipanteGrupoFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.ParticipanteGrupoFacadeImpl;
import uco.doo.rugrats.uconnect.dto.ParticipanteGrupoDTO;

@RestController
@RequestMapping("uconnect/api/v1/participantegrupo")
public final class ParticipanteGrupoController {
	
	
	
	@GetMapping("/dummy")
	public ParticipanteGrupoDTO dummy() {
		return ParticipanteGrupoDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<ParticipanteGrupoDTO>> list(@RequestBody ParticipanteGrupoDTO dto) {
		ParticipanteGrupoFacade facade = new ParticipanteGrupoFacadeImpl();
		List<ParticipanteGrupoDTO> list = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Participantes de grupos consultados exitosamente");
		
		Response<ParticipanteGrupoDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ParticipanteGrupoDTO listById(@PathVariable UUID id) {
		return ParticipanteGrupoDTO.create().setIdentificador(id);
	}
	
}
