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
import uco.doo.rugrats.uconnect.busisness.facade.TipoIdentificacionFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.TipoIdentificacionFacadeImpl;
import uco.doo.rugrats.uconnect.dto.TipoIdentificacionDTO;

@RestController
@RequestMapping("uconnect/api/v1/tipoidentificacion")
public final class TipoIdentificacionController {
	
	
	
	@GetMapping("/dummy")
	public TipoIdentificacionDTO dummy() {
		return TipoIdentificacionDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<TipoIdentificacionDTO>> list(@RequestBody TipoIdentificacionDTO dto) {
		TipoIdentificacionFacade facade = new TipoIdentificacionFacadeImpl();
		List<TipoIdentificacionDTO> list = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Tipos de identificación consultados exitosamente");
		
		Response<TipoIdentificacionDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public TipoIdentificacionDTO listById(@PathVariable UUID id) {
		return TipoIdentificacionDTO.create().setIdentificador(id);
	}
	
}
