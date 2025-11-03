package umg.edu.sistema_eventos.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import umg.edu.sistema_eventos.exception.ErrorResponse;

import java.util.stream.Collectors;

// @ControllerAdvice hace que esta clase escuche todas las excepciones en todos los controladores
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    // Esta es una excepción común que Spring lanza cuando falla una validación en un @PostMapping o @PutMapping.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        // Recopilamos todos los mensajes de error de los campos
        String detalleError = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ErrorResponse errorResponse = new ErrorResponse(
                status.value(),
                "Error de Validación",
                "Uno o más campos tienen errores de formato o están vacíos: " + detalleError,
                request.getDescription(false).replace("uri=", "") // Obtiene la URL de la petición
        );

        // Retorna 400 Bad Request
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // Esto atrapará cualquier excepción no manejada y la formateará.
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex, WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Error Interno del Servidor",
                "Ocurrió un error inesperado. Detalle: " + ex.getMessage(),
                request.getDescription(false).replace("uri=", "")
        );

        // Retorna 500 Internal Server Error
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}