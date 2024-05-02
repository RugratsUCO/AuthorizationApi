package com.authorizationapi.authorizationapi.messages;

import com.authorizationapi.authorizationapi.config.RabbitConfig;
import com.authorizationapi.authorizationapi.domain.estructura.Estructura;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class RabbitMQPublisher {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void crearNueva(Estructura estructura){
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,"crear_estructura_key",estructura);
    }
    public Estructura consultarPorId(Estructura estructura) {
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.EXCHANGE, "consultar_estructura_key", estructura, ParameterizedTypeReference.forType(Estructura.class));
    }
    public List<Estructura> consultarTodas() {
        return rabbitTemplate.convertSendAndReceiveAsType(RabbitConfig.EXCHANGE, "consultar_estructuras_key", Estructura.create(), ParameterizedTypeReference.forType(List.class));
    }
    public void cambiarNombre(Estructura estructura){
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,"cambiar_nombre_key",estructura);
    }
    /*
    public void cambiarEstado(UUID identificador){
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,"cambiar_estado_key",identificador);
    }
     */
    public void eliminar(Estructura estructura){
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE,"eliminar_estructura_key",estructura);
    }

}
