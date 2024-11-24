package com.authorizationapi.authorizationapi.crosscutting.utils.messages;

public class UtilMessagesController {
    public static final class ControllerPublicacion{
        public static final String PUBLICACION_REGISTRADA_FINAL = "La publicacion ha sido registrada exitosamente";
        public static final String PUBLICACION_NO_REGISTRADA_FINAL = "No se ha podido registrar la publicacion";
        public static final String PUBLICACION_NO_REGISTRADA_TECNICO = "No se ha podido registrar la publicacion";

        public static final String PUBLICACIONES_CONSULTADAS_FINAL = "Las publicaciones han sido consultadas exitosamente";
        public static final String PUBLICACIONES_NO_CONSULTADAS_FINAL = "No se han podido consultar las publicaciones";
        public static final String PUBLICACIONES_NO_CONSULTADAS_TECNICO = "No se han podido consultar las publicaciones";
        public static final String PUBLICACION_NO_CONSULTADA_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";
        public static final String NO_PUBLICACIONES_PARA_CONSULTAR_FINAL = "No hay publicaciones para consultar";
        public static final String PUBLICACIONES_NO_CONSULTADAS_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";

        public static final String PUBLICACION_ACTUALIZADA_FINAL = "Se ha cambiado la informacion de la publicacion exitosamente";
        public static final String PUBLICACION_NO_ACTUALIZADA_FINAL = "No se ha podido cambiar la informacion";

        public static final String PUBLICACION_ELIMINADA_FINAL = "La publicacion se ha podido eliminar exitosamente";
        public static final String PUBLICACION_NO_ELIMINADA_FINAL = "No se ha podido eliminar a la publicacion";
    }
    public static final class ControllerComentario{
        public static final String COMENTARIO_REGISTRADO_FINAL = "El comentario ha sido registrado exitosamente";
        public static final String COMENTARIO_NO_REGISTRADO_FINAL = "No se ha podido comentar";
        public static final String COMENTARIO_NO_REGISTRADO_TECNICO = "No se ha podido registrar el comentario";

        public static final String COMENTARIOS_CONSULTADOS_FINAL = "Los comentarios han sido consultados exitosamente";
        public static final String COMENTARIOS_NO_CONSULTADOS_FINAL = "No se han podido consultar los comentarios";
        public static final String COMENTARIOS_NO_CONSULTADOS_TECNICO = "No se han podido consultar los comentarios";
        public static final String COMENTARIO_NO_CONSULTADO_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";
        public static final String NO_COMENTARIOS_PARA_CONSULTAR_FINAL = "No hay comentarios para consultar";
        public static final String COMENTARIOS_NO_CONSULTADOS_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";

    }
    public static final class ControllerPersona{
        public static final String PERSONA_REGISTRADA_FINAL = "La persona ha sido registrada exitosamente";
        public static final String PERSONA_NO_REGISTRADA_FINAL = "No se ha podido registrar la persona";
        public static final String PERSONA_NO_REGISTRADA_TECNICO = "No se ha podido registrar la persona";

        public static final String PERSONAS_CONSULTADAS_FINAL = "Las personas han sido consultadas exitosamente";
        public static final String PERSONAS_NO_CONSULTADAS_FINAL = "No se han podido consultar las personas";
        public static final String PERSONAS_NO_CONSULTADAS_TECNICO = "No se han podido consultar las personas";
        public static final String PERSONA_NO_CONSULTADA_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";
        public static final String NO_PERSONAS_PARA_CONSULTAR_FINAL = "No hay personas para consular";
        public static final String PERSONAS_NO_CONSULTADAS_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";

        public static final String PERSONA_ACTUALIZADA_FINAL = "Se ha cambiado la informacion de la persona exitosamente";
        public static final String PERSONA_NO_ACTUALIZADA_FINAL = "No se ha podido cambiar la informacion";

        public static final String PERSONA_ELIMINADA_FINAL = "La persona se ha podido eliminar exitosamente";
        public static final String PERSONA_NO_ELIMINADA_FINAL = "No se ha podido eliminar a la persona";
    }

    public static final class ControllerOrganizacion{
        public static final String ORGANIZACION_CREADA_FINAL = "La organizacion se ha creado exitosamente";
        public static final String ORGANIZACION_NO_CREADA_FINAL = "No se ha podido crear la organizacion";
        public static final String ORGANIZACION_NO_REGISTRADA_TECNICO = "No se ha podido crear la organizacion";
        public static final String ORGANIZACIONES_CONSULDATAS_FINAL = "Organizaciones consultadas exitosamente";
        public static final String ORGANIZACIONES_NO_CONSULTADAS_FINAL = "No hay organizaciones para consultar";
        public static final String ORGANIZACIONES_NO_CONSULTADAS_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";
        public static final String ORGANIZACION_NOMBRE_EDITADO_FINAL = "Se ha cambiado el nombre de la organizacion exitosamente";
        public static final String ORGANIZACION_NOMBRE_NO_EDITADO_FINAL = "No se ha podido cambiar el nombre de la organizacion";
        public static final String ORGANIZACION_ESTADO_ACTUALIZADO_FINAL = "Se ha cambiado el estado de la organizacion exitosamente";
        public static final String ORGANIZACION_ESTADO_NO_ACTUALIZADO_FINAL = "No se ha podido cambiar el estaedo de la organizacion";
        public static final String ORGANIZACION_ELIMINADA_FINAL = "La organizacion se ha podido eliminar satisfactoriamente";
        public static final String ORGANIZACION_NO_ELIMINADA_FINAL = "No se ha podido eliminar la organizacion";
    }

    public static final class ControllerAdministradorOrganizacionEncargado{
        public static final String ADMINISTRADOR_ORGANIZACION_ENCARGADO_CREADO_FINAL = "El administrador se ha registrado exitosamente";
        public static final String ADMINISTRADOR_ORGANIZACION_ENCARGADO_NO_CREADO_FINAL = "No se ha podido registrar el administrador";
        public static final String ADMINISTRADOR_ORGANIZACION_ENCARGADO_CONSULTADO_FINAL = "Administradores de organizacion consultados exitosamente";
        public static final String ADMINISTRADOR_ORGANIZACION_ENCARGADO_NO_CONSULTADO_FINAL = "No hay administradores organizacion para consultar";
        public static final String ADMINISTRADOR_ORGANIZACION_ENCARGADO_NO_CONSULTADO_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";
        public static final String ADMINISTRADOR_ORGANIZACION_ENCARGADO_ESTADO_EDITADO_FINAL = "Se ha cambiado el estado del administrador satisfactoriamente";
        public static final String ADMINISTRADOR_ORGANIZACION_ENCARGADO_ESTADO_NO_EDITADO_FINAL = "No se ha podido cambiar el estado";
        public static final String ADMINISTRADOR_ORGANIZACION_ENCARGADO_ELIMINADO_FINAL = "El administrador de la organizacion se ha podido eliminar satisfactoriamente";
        public static final String ADMINISTRADOR_ORGANIZACION_ENCARGADO_NO_ELIMINADO_FINAL = "No se ha podido eliminar al administrador de la organizacion";
    }

    public static final class ControllerAdministradorEstructuraEncargado {
        public static final String ADMINISTRADOR_ESTRUCTURA_ENCARGADO_CREADO_FINAL = "El administrador se ha registrado exitosamente";
        public static final String ADMINISTRADOR_ESTRUCTURA_ENCARGADO_NO_CREADO_FINAL = "No se ha podido registrar el administrador";
        public static final String ADMINISTRADOR_ESTRUCTURA_ENCARGADO_CONSULTADO_FINAL = "Administradores de estructura consultados exitosamente";
        public static final String ADMINISTRADOR_ESTRUCTURA_ENCARGADO_NO_CONSULTADO_FINAL = "No hay administradores estructura para consultar";
        public static final String ADMINISTRADOR_ESTRUCTURA_ENCARGADO_NO_CONSULTADO_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";
        public static final String ADMINISTRADOR_ESTRUCTURA_ENCARGADO_ESTADO_EDITADO_FINAL = "Se ha cambiado el estado del administrador satisfactoriamente";
        public static final String ADMINISTRADOR_ESTRUCTURA_ENCARGADO_ESTADO_NO_EDITADO_FINAL = "No se ha podido cambiar el estado";
        public static final String ADMINISTRADOR_ESTRUCTURA_ENCARGADO_ELIMINADO_FINAL = "El administrador de la estructura se ha podido eliminar satisfactoriamente";
        public static final String ADMINISTRADOR_ESTRUCTURA_ENCARGADO_NO_ELIMINADO_FINAL = "No se ha podido eliminar al administrador de la organizacion";
    }

    public static final class ControllerEstructura {
        public static final String ESTRUCTURA_CREADA_FINAL = "La estructura se ha registrado exitosamente";
        public static final String ESTRUCTURA_NO_CREADA_FINAL = "No se ha podido registrar la estructura";
        public static final String ESTRUCTURAS_CONSULTADA_FINAL = "Estructuras consultadas exitosamente";
        public static final String ESTRUCTURAS_NO_CONSULTADAS_FINAL = "No hay estructuras para consultar";
        public static final String ESTRUCTURAS_NO_AUTORIZADO_CONSULTAR_FINAL = "No se puede consultar, dado que no estás autorizado";
        public static final String ESTRUCTURAS_NO_CONSULTADAS_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";
        public static final String ESTRUCTURA_ESTADO_EDITADO_FINAL = "Se ha cambiado el estado de la estructura satisfactoriamente";
        public static final String ESTRUCTURA_ESTADO_NO_EDITADO_FINAL = "No se ha podido cambiar el estado";
        public static final String ESTRUCTURA_NOMBRE_EDITADO_FINAL = "Se ha cambiado el nombre de la estructura satisfactoriamente";
        public static final String ESTRUCTURA_NOMBRE_NO_EDITADO_FINAL = "No se ha podido cambiar los datos de la estructura";
        public static final String ESTRUCTURA_ELIMINADA_FINAL = "La estructura se ha podido eliminar satisfactoriamente";
        public static final String ESTRUCTURA_NO_ELIMINADA_FINAL = "No se ha podido eliminar la estructura";
    }

    public static final class ControllerGrupo{
        public static final String GRUPO_CREADO_FINAL = "El grupo se ha registrado exitosamente";
        public static final String GRUPO_NO_CREADO_FINAL = "No se ha podido registrar el grupo";
        public static final String GRUPOS_CONSULTADOS_FINAL = "Grupos consultados exitosamente";
        public static final String GRUPOS_NO_CONSULTADOS_FINAL = "No hay grupos para consultar";
        public static final String GRUPOS_NO_CONSULTADOS_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";
        public static final String GRUPO_ESTADO_EDITADO_FINAL = "Se ha cambiado el estado del grupo satisfactoriamente";
        public static final String GRUPO_ESTADO_NO_EDITADO_FINAL = "No se ha podido cambiar el estado";
        public static final String GRUPO_DATOS_EDITADOS_FINAL = "Se han cambiado los datos del grupo satisfactoriamente";
        public static final String GRUPO_DATOS_NO_EDITADOS_FINAL = "No se ha podido cambiar los datos del grupo";
        public static final String GRUPO_ELIMINADO_FINAL = "El grupo se ha podido eliminar satisfactoriamente";
        public static final String GRUPO_NO_ELIMINADO_FINAL = "No se ha podido eliminar al grupo";
    }

    public static final class ControllerParticipante{
        public static final String PARTICIPANTE_CREADO_FINAL = "El participante se ha registrado exitosamente";
        public static final String PARTICIPANTE_NO_CREADO_FINAL = "No se ha podido registrar el participante";
        public static final String PARTICIPANTES_CONSULTADOS_FINAL = "Participantes consultados exitosamente";
        public static final String PARTICIPANTES_NO_CONSULTADOS_FINAL = "No hay participantes para consultar";
        public static final String PARTICIPANTES_NO_CONSULTADOS_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";
        public static final String PARTICIPANTE_ESTADO_EDITADO_FINAL = "Se ha cambiado el estado del participante satisfactoriamente";
        public static final String PARTICIPANTE_ESTADO_NO_EDITADO_FINAL = "No se ha podido cambiar el estado del participante";
        public static final String PARTICIPANTE_ELIMINADO_FINAL = "El participante se ha podido eliminar satisfactoriamente";
        public static final String PARTICIPANTE_NO_ELIMINADO_FINAL = "No se ha podido eliminar al participante";
    }

    public static final class ControllerParticipanteGrupo{
        public static final String PARTICIPANTE_GRUPO_CREADO_FINAL = "El participante se ha asignado al grupo exitosamente";
        public static final String PARTICIPANTE_GRUPO_NO_CREADO_FINAL = "No se ha podido asignar el grupo al participante";
        public static final String PARTICIPANTES_GRUPO_CONSULTADOS_FINAL = "Participantes consultados exitosamente";
        public static final String PARTICIPANTES_GRUPO_NO_CONSULTADOS_FINAL = "No hay participantes para consultar";
        public static final String PARTICIPANTES_GRUPO_NO_CONSULTADOS_INTERNO_FINAL = "Error interno, no se ha podido realizar la consulta correctamente";
        public static final String PARTICIPANTE_GRUPO_ELIMINADO = "El participante se ha podido eliminar del grupo satisfactoriamente";
        public static final String PARTICIPANTE_GRUPO_NO_ELIMINADO = "No se ha podido eliminar al participante del grupo";
    }
}
