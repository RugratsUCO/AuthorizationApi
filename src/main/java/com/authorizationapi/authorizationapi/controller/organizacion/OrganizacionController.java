package com.authorizationapi.authorizationapi.controller.organizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;
import com.authorizationapi.authorizationapi.service.organizacion.OrganizacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("uconnect/api/v1")
public final class OrganizacionController {

    private final OrganizacionService service = new OrganizacionService();
    @PostMapping("/organizacion")
    public ResponseEntity<Response<Organizacion>> crearNueva(@RequestBody Organizacion organizacion) {


        var statusCode = HttpStatus.OK;
        Response<Organizacion> response = new Response<>();

        try {
            service.crearNueva(organizacion);
            response.getMessages().add("La organizacion se ha creado exitosamente");

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido crear la organizacion");

        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/organizacion")
    public ResponseEntity<Response<Organizacion>> consultar() {

        var statusCode = HttpStatus.OK;
        Response<Organizacion> response;

        try {
            List<String> messages = new ArrayList<>();
            List<Organizacion> list = service.consultar();

            if (!list.isEmpty()) {
                messages.add("Organizaciones consultadas exitosamente");

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add("No hay organizaciones para consultar");
            }

            response = new Response<>(list,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add("Error interno, no se ha podido realizar la consulta correctamente");
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/organizacionNombre/{identificador}")
    public ResponseEntity<Response<Organizacion>> cambiarNombre(@PathVariable UUID identificador,@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.cambiarNombre(identificador, organizacion);
            response.getMessages().add("Se ha cambiado el nombre de la organizacion satisfactoriamente");

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido cambiar el nombre de la organizacion");
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/organizacionEstado/{identificador}")
    public ResponseEntity<Response<Organizacion>> cambiarEstado(@PathVariable UUID identificador,@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.cambiarEstado(identificador, organizacion);
            response.getMessages().add("Se ha cambiado el estado de la organizacion satisfactoriamente");

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido cambiar el estado de la organizacion");
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @DeleteMapping("/organizacion/{identificador}")
    public ResponseEntity<Response<Organizacion>> eliminar(@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.eliminar(organizacion);
            response.getMessages().add("La organizacion se ha podido eliminar satisfactoriamente");

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add("No se ha podido eliminar la organizacion");
        }

        return new ResponseEntity<>(response,statusCode);
    }
}
