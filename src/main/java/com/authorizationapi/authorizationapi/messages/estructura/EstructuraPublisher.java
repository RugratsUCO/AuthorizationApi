package com.authorizationapi.authorizationapi.messages.estructura;

import com.authorizationapi.authorizationapi.config.RabbitConfig;
import com.authorizationapi.authorizationapi.domain.estructura.Estructura;
import com.authorizationapi.authorizationapi.domain.organizacion.Organizacion;
import com.authorizationapi.authorizationapi.dto.estructura.EstructuraDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EstructuraPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public HttpStatus crearNueva(List<Estructura> estructuras){
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.ESTRUCTURA_EXCHANGE, "crear_estructura_key", estructuras, ParameterizedTypeReference.forType(HttpStatus.class));
    }
    public EstructuraDTO consultarPorId(Estructura estructura) {
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.ESTRUCTURA_EXCHANGE, "consultar_estructura_key", estructura, ParameterizedTypeReference.forType(EstructuraDTO.class));
    }
    public List<Estructura> consultarTodas() {
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.ESTRUCTURA_EXCHANGE, "consultar_estructuras_key", Estructura.create(), ParameterizedTypeReference.forType(List.class));
    }
    public List<Estructura> consultarPorOrganizacion(Organizacion organizacion) {
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.ESTRUCTURA_EXCHANGE, "consultar_estructuras_organizacion_key", organizacion, ParameterizedTypeReference.forType(List.class));
    }
    public HttpStatus cambiarNombre(Estructura estructura){
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.ESTRUCTURA_EXCHANGE, "cambiar_nombre_key", estructura, ParameterizedTypeReference.forType(HttpStatus.class));
    }

    public HttpStatus cambiarEstado(UUID identificador){
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.ESTRUCTURA_EXCHANGE, "cambiar_estado_key", identificador, ParameterizedTypeReference.forType(HttpStatus.class));
    }

    public HttpStatus eliminar(Estructura estructura){
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.ESTRUCTURA_EXCHANGE, "eliminar_estructura_key", estructura, ParameterizedTypeReference.forType(HttpStatus.class));
    }

}
