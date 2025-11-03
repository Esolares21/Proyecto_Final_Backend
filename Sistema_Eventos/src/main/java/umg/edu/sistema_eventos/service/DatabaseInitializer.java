package umg.edu.sistema_eventos.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import umg.edu.sistema_eventos.model.Rol;
import umg.edu.sistema_eventos.model.Usuario;
import umg.edu.sistema_eventos.repository.RolRepository;
import umg.edu.sistema_eventos.repository.UsuarioRepository;

import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public DatabaseInitializer(UsuarioRepository usuarioRepository, RolRepository rolRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.rolRepository = rolRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (rolRepository.findByNombre("ADMIN").isEmpty()) {
            // 1. Crear Roles
            Rol adminRol = new Rol();
            adminRol.setNombre("ADMIN");
            rolRepository.save(adminRol);

            Rol userRol = new Rol();
            userRol.setNombre("USER");
            rolRepository.save(userRol);

            // 2. Crear un usuario ADMIN
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setEmail("admin@umg.edu");
                // ENCRIPTAR la contraseña usando BCrypt
                admin.setPassword(passwordEncoder.encode("admin123"));

                Set<Rol> adminRoles = new HashSet<>();
                adminRoles.add(adminRol);
                admin.setRoles(adminRoles);

                usuarioRepository.save(admin);
                System.out.println(">>> USUARIO ADMIN CREADO: admin / admin123");
            }
        }
    }
}