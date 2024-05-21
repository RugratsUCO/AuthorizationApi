package com.authorizationapi.authorizationapi.controller.organizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.authorizationapi.authorizationapi.auth.AuthAdminService;
import com.authorizationapi.authorizationapi.controller.response.Response;
import com.authorizationapi.authorizationapi.crosscutting.utils.messages.UtilMessagesController;
import com.authorizationapi.authorizationapi.domain.organizacion.AdministradorOrganizacionEncargado;
import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;
import com.authorizationapi.authorizationapi.repository.organizacion.AdministradorOrganizacionEncargadoRepository;
import com.authorizationapi.authorizationapi.service.organizacion.OrganizacionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1")
public final class OrganizacionController {

    @Autowired
    private OrganizacionService service = new OrganizacionService();
    @Autowired
    private AdministradorOrganizacionEncargadoRepository  administradorRepository;
    @Autowired
    private AuthAdminService authService;
    private final Logger log = LoggerFactory.getLogger(OrganizacionController.class);

    @PostMapping("/organizacion")
    public ResponseEntity<Response<Organizacion>> crearNueva(@RequestBody Organizacion organizacion) {


        var statusCode = HttpStatus.OK;
        Response<Organizacion> response = new Response<>();

        try {
            service.crearNueva(organizacion);
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_CREADA_FINAL);

        } catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NO_CREADA_FINAL);
            log.error((UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NO_REGISTRADA_TECNICO));
        }
        return new ResponseEntity<>(response, statusCode);
    }
    @GetMapping("/organizacion/{identificadorAdministrador}")
    public ResponseEntity<Response<Organizacion>> consultar(@PathVariable UUID identificadorAdministrador,@RequestBody Organizacion organizacion) {
        AdministradorOrganizacionEncargado administrador = administradorRepository.findById(identificadorAdministrador).orElse(null);
        var statusCode = HttpStatus.OK;
        Response<Organizacion> response;

        try {
            List<String> messages = new ArrayList<>();
            List<Organizacion> list = new ArrayList<>();
            assert administrador != null;
            if(authService.tienePermisosEnOrganizacion(administrador.getPersona().getUsuario(),administrador.getOrganizacion(),organizacion.getIdentificador())){
               list = service.consultar();
            }

            if (!list.isEmpty()) {
                messages.add(UtilMessagesController.ControllerOrganizacion.ORGANIZACIONES_CONSULDATAS_FINAL);

            } else {
                statusCode = HttpStatus.NOT_FOUND;
                messages.add(UtilMessagesController.ControllerOrganizacion.ORGANIZACIONES_NO_CONSULTADAS_FINAL);
            }

            response = new Response<>(list,messages);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response = new Response<>();
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACIONES_NO_CONSULTADAS_INTERNO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/organizacionNombre/{identificador}")
    public ResponseEntity<Response<Organizacion>> cambiarNombre(@PathVariable UUID identificador,@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.cambiarNombre(identificador, organizacion);
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NOMBRE_EDITADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NOMBRE_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
    @PutMapping("/organizacionEstado/{identificador}")
    public ResponseEntity<Response<Organizacion>> cambiarEstado(@PathVariable UUID identificador,@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.cambiarNombre(identificador, organizacion);
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NOMBRE_EDITADO_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NOMBRE_NO_EDITADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }

    @PatchMapping("/organizacion/{identificador}")
    public ResponseEntity<Response<Organizacion>> changeStatus(@PathVariable UUID identificador) {
        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.cambiarEstado(identificador);
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_ESTADO_ACTUALIZADO_FINAL);

        }catch (Exception exception) {
            log.error(exception.getMessage());
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_ESTADO_NO_ACTUALIZADO_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }

    @DeleteMapping("/organizacion/{identificador}")
    public ResponseEntity<Response<Organizacion>> eliminar(@RequestBody Organizacion organizacion) {

        var statusCode = HttpStatus.OK;
        var response = new Response<Organizacion>();

        try {
            service.eliminar(organizacion);
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_ELIMINADA_FINAL);

        }catch (Exception exception) {
            statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            response.getMessages().add(UtilMessagesController.ControllerOrganizacion.ORGANIZACION_NO_ELIMINADA_FINAL);
        }

        return new ResponseEntity<>(response,statusCode);
    }
}