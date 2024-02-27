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
import uco.doo.rugrats.uconnect.busisness.facade.TipoEstadoFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.TipoEstadoFacadeImpl;
import uco.doo.rugrats.uconnect.dto.TipoEstadoDTO;

@RestController
@RequestMapping("uconnect/api/v1/tipoestado")
public final class TipoEstadoController {
	
	
	
	@GetMapping("/dummy")
	public TipoEstadoDTO dummy() {
		return TipoEstadoDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<TipoEstadoDTO>> list(@RequestBody TipoEstadoDTO dto) {
		TipoEstadoFacade facade = new TipoEstadoFacadeImpl();
		List<TipoEstadoDTO> list = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Tipos de Estados consultados exitosamente");
		
		Response<TipoEstadoDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public TipoEstadoDTO listById(@PathVariable UUID id) {
		return TipoEstadoDTO.create().setIdentificador(id);
	}
	
}
