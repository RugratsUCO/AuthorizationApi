package com.authorizationapi.authorizationapi.domain.publicacion;

import com.authorizationapi.authorizationapi.crosscutting.utils.UtilBoolean;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilText;
import com.authorizationapi.authorizationapi.crosscutting.utils.UtilUUID;
import com.authorizationapi.authorizationapi.domain.estructura.Grupo;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity
@Table(name = "Publicacion")
public final class Publicacion {
    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "identificador", nullable = false)
    private UUID identificador;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "contenido", nullable = false)
    private String contenido;

    @Column(name = "activo", nullable = false)
    private boolean activo;

    @Column(name = "fechaPublicacion", nullable = false)
    private LocalDateTime fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "grupo")
    private Grupo grupo;


    public Publicacion() {
        super();
        setIdentificador(UtilUUID.getDefaultValue());
        setActivo(UtilBoolean.getDefaultValue());
        setContenido(UtilText.getDefaultValue());
        setNombre(UtilText.getDefaultValue());
        setFechaPublicacion(LocalDateTime.now());
    }

    public UUID getIdentificador() {
        return identificador;
    }

    public Publicacion setIdentificador(UUID identificador) {
        this.identificador = identificador;
        return this;
    }

    public String getTitulo() {
        return titulo;
    }

    public Publicacion setTitulo(String titulo) {
        this.titulo = titulo;
        return this;
    }

    public String getNombre() {
        return nombre;
    }

    public Publicacion setNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public String getContenido() {
        return contenido;
    }

    public Publicacion setContenido(String contenido) {
        this.contenido = contenido;
        return this;
    }

    public boolean isActivo() {
        return activo;
    }

    public Publicacion setActivo(boolean activo) {
        this.activo = activo;
        return this;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public Publicacion setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
        return this;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public Publicacion setGrupo(Grupo grupo) {
        this.grupo = grupo;
        return this;
    }

    public static Publicacion create() {
        return new Publicacion();
    }

}

