package dh.backend.clinicamvc.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // Lanzar las excepciones a traves del controlador.
public class GlobalException {
    // Indicamos que excepcion vamos a manejar, el E. Handler se realiza por cada excepcion.
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> recursoNoEncontrado(ResourceNotFoundException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body(e.getMessage());
    }

    // Cada vez que se ejecuten las excepciones detalladas en Exception Handler aplicaran dicho metodo para comunicar el error.

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> solicitudErronea(BadRequestException e){
        return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(e.getMessage());
    }
}
