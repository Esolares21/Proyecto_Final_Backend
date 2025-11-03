package umg.edu.sistema_eventos.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umg.edu.sistema_eventos.model.Rol;
import umg.edu.sistema_eventos.model.Usuario;
import umg.edu.sistema_eventos.repository.UsuarioRepository;

import java.util.Collection; // <--- Importante
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Buscar el usuario en la base de datos
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        // 2. Definir authorities como Collection<GrantedAuthority>
        Collection<GrantedAuthority> authorities = mapRolesToAuthorities(usuario.getRoles()); // <--- Corregido

        // 3. Devolver un objeto UserDetails de Spring Security
        return new User(usuario.getUsername(), usuario.getPassword(), authorities);
    }

    // La función de ayuda debe devolver Collection
    private Collection<GrantedAuthority> mapRolesToAuthorities(Collection<Rol> roles) {
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());
    }
}