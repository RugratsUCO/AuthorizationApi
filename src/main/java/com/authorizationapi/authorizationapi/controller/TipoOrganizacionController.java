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
import uco.doo.rugrats.uconnect.busisness.facade.TipoOrganizacionFacade;
import uco.doo.rugrats.uconnect.busisness.facade.facadeimpl.TipoOrganizacionFacadeImpl;
import uco.doo.rugrats.uconnect.dto.TipoOrganizacionDTO;

@RestController
@RequestMapping("uconnect/api/v1/tipoorganizacion")
public final class TipoOrganizacionController {
	
	
	
	@GetMapping("/dummy")
	public TipoOrganizacionDTO dummy() {
		return TipoOrganizacionDTO.create();
	}
	@GetMapping
	public ResponseEntity<Response<TipoOrganizacionDTO>> list(@RequestBody TipoOrganizacionDTO dto) {
		TipoOrganizacionFacade facade = new TipoOrganizacionFacadeImpl();
		List<TipoOrganizacionDTO> list = facade.consultar(dto);
		
		List<String> messages = new ArrayList<>();
		messages.add("Tipos de organizaciones consultadas exitosamente");
		
		Response<TipoOrganizacionDTO> response = new Response<>(list,messages);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public TipoOrganizacionDTO listById(@PathVariable UUID id) {
		return TipoOrganizacionDTO.create().setIdentificador(id);
	}
	
}
