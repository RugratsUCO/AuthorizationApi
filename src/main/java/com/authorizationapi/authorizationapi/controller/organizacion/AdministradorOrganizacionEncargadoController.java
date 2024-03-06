package com.authorizationapi.authorizationapi.controller.organizacion;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.domain.organizacion.AdministradorOrganizacionEncargado;
import com.authorizationapi.authorizationapi.service.organizacion.AdministradorOrganizacionEncargadoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public final class AdministradorOrganizacionEncargadoController {

   private final AdministradorOrganizacionEncargadoService service = new AdministradorOrganizacionEncargadoService();

    @PostMapping("/administradororganizacion")
    public ResponseEntity<Response<AdministradorOrganizacionEncargado>> concederPermisos(@RequestBody AdministradorOrganizacionEncargado administrador) {


        var statusCode = HttpStatus.OK;
        Response<AdministradorOrganizacionEncargado> response = new Response<>();

        try {
            service.concederPermisos(administrador);
            response.getMessages().add("El administrador se ha registrado exitosamente");

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido registrar el administrador");

        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/administradororganizacion")
    public ResponseEntity<Response<AdministradorOrganizacionEncargado>> consultar() {

        var statusCode = HttpStatus.OK;
        Response<AdministradorOrganizacionEncargado> response;

        try {
            List<String> messages = new ArrayList<>();
            List<AdministradorOrganizacionEncargado> list = service.consultar();

            if (!list.isEmpty()) {
                messages.add("Administradores de organizacion consultados exitosamente");

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add("No hay administradores organizacion para consultar");
            }

            response = new Response<>(list,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add("Error interno, no se ha podido realizar la consulta correctamente");
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/administradororganizacion/{identificador}")
    public ResponseEntity<Response<AdministradorOrganizacionEncargado>> cambiarEstado(@PathVariable UUID identificador, @RequestBody AdministradorOrganizacionEncargado administrador) {

        var statusCode = HttpStatus.OK;
        var response = new Response<AdministradorOrganizacionEncargado>();

        try {
            service.cambiarEstado(identificador, administrador);
            response.getMessages().add("Se ha cambiado el estado del administrador satisfactoriamente");

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido cambiar el estado");
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @DeleteMapping("/administradororganizacion/{identificador}")
    public ResponseEntity<Response<AdministradorOrganizacionEncargado>> eliminar(@RequestBody AdministradorOrganizacionEncargado administrador) {

        var statusCode = HttpStatus.OK;
        var response = new Response<AdministradorOrganizacionEncargado>();

        try {
            service.eliminar(administrador);
            response.getMessages().add("El administrador de la organizacion se ha podido eliminar satisfactoriamente");

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido eliminar al administrador de la organizacion");
        }

        return new ResponseEntity<>(response,statusCode);
    }


}
