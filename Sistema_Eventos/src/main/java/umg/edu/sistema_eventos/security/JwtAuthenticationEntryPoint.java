package umg.edu.sistema_eventos.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // Log para ver el error en consola
        System.out.println("Error 401: Intento de acceso sin autenticación. Mensaje: " + authException.getMessage());

        // Establecer el tipo de contenido y el código de estado 401
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // Escribir el mensaje de error en el cuerpo de la respuesta
        response.getOutputStream().println("{ \"error\": \"Unauthorized\", \"message\": \"Acceso denegado. Se requiere un token JWT válido.\"}");
    }
}