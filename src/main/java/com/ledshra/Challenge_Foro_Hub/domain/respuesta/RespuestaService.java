package com.ledshra.Challenge_Foro_Hub.domain.respuesta;

import com.ledshra.Challenge_Foro_Hub.domain.topico.TopicoRepository;
import com.ledshra.Challenge_Foro_Hub.domain.usuario.UsuarioRepository;
import com.ledshra.Challenge_Foro_Hub.infra.errors.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RespuestaService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private RespuestaRepository repository;

    public RespuestaCreadaDTO respuestaCreadaDTO(RespuestaDTO respuestaDTO) {
        if (!usuarioRepository.findById(respuestaDTO.usuario_Id()).isPresent()){
            throw new ValidacionDeIntegridad("Este ID no existe.");
        }
        if (!topicoRepository.findById(respuestaDTO.topico_Id()).isPresent()){
            throw new ValidacionDeIntegridad("Este id de tópico no existe. ");
        }
        var usuario = usuarioRepository.findById(respuestaDTO.usuario_Id()).get();
        var topico =topicoRepository.findById(respuestaDTO.topico_Id()).get();

        var rVerified= new Respuesta(null,respuestaDTO.solution(),usuario,topico,respuestaDTO.creationDate());
        repository.save(rVerified);
        return new RespuestaCreadaDTO(rVerified);
    }

}