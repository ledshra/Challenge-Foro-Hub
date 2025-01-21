package com.ledshra.Challenge_Foro_Hub.domain.usuario;

import com.ledshra.Challenge_Foro_Hub.infra.errors.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public RegistroUsuarioDTO registrarUsuario(RegistroUsuarioDTO registroUsuarioDTO) {
        // Verificar si el correo electrónico ya está registrado en la base de datos
        if (usuarioRepository.existsByEmail(registroUsuarioDTO.email())) {
            throw new ValidacionDeIntegridad("Este email ya existe.");
        }

        if (usuarioRepository.existsByUsername(registroUsuarioDTO.username())) {
            throw new ValidacionDeIntegridad("Este nombre de usuario ya existe.");
        }

        var usuario = new Usuario(registroUsuarioDTO, passwordEncoder);
        usuarioRepository.save(usuario);
        return new RegistroUsuarioDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword());
    }

    public RegistroUsuarioDTO actualizacionUsuario(Long id, ActualizacionUsuarioDTO actualizacionUsuarioDTO) {
        var usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacionDeIntegridad("El usuario con el ID no existe.");
        }

        var usuario = usuarioOptional.get();

        usuarioRepository.save(usuario);
        return new RegistroUsuarioDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword());
    }
    public RegistroUsuarioDTO desactivarUser(Long id) {
        var usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isEmpty()) {
            throw new ValidacionDeIntegridad("El ID no existe.");
        }

        var usuario = usuarioOptional.get();

        usuario.deactivateUser();

        usuarioRepository.save(usuario);
        return new RegistroUsuarioDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getUsername(),
                usuario.getPassword());
    }
}