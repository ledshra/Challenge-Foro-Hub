package com.ledshra.Challenge_Foro_Hub.domain.topico;

import com.ledshra.Challenge_Foro_Hub.domain.usuario.UsuarioRepository;
import com.ledshra.Challenge_Foro_Hub.infra.errors.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public RespuestaTopicoDTO topicoCreado(TopicoDTO topicoDTO){
        if (!usuarioRepository.findById(topicoDTO.usuario_Id()).isPresent()){
            throw new ValidacionDeIntegridad("Este ID de usuario no existe.");
        }
        var title= topicoDTO.title();
        if (title != null && topicoRepository.existsByTitleIgnoreCase(title)){
            throw new ValidacionDeIntegridad("Este título ya existe.");
        }
        String message = topicoDTO.message();
        if (message != null && topicoRepository.existsByMessageIgnoreCase(message)){
            throw new ValidacionDeIntegridad("Este mensaje ya existe.");
        }
        var usuario = usuarioRepository.findById(topicoDTO.usuario_Id()).get();
        var topicoId= new Topico(null,title,message,topicoDTO.date(),topicoDTO.status(),usuario,topicoDTO.curso());
        topicoRepository.save(topicoId);
        return new RespuestaTopicoDTO(topicoId);
    }
}