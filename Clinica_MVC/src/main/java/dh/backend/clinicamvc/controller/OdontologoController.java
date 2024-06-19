package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologo")
public class OdontologoController {

    private IOdontologoService odontologoService; // Inyeccion de Dependencias con el constructor.

    public OdontologoController(IOdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }


    @PostMapping
    public ResponseEntity<Odontologo> registrarOdontologo(@RequestBody Odontologo odontologo) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(odontologoService.registrarOdontologo(odontologo));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologoPorID(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoEncontrado=odontologoService.buscarOdontologoporID(id);
        Odontologo odontologoADevolver=odontologoEncontrado.get();
        return ResponseEntity.ok(odontologoADevolver);

    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarOdontologos (){
        return ResponseEntity.ok(odontologoService.listarOdontologos());
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo (@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
            odontologoService.actualizarOdontologo(odontologo); // Entontes registras el resultado del opcional en un odontologo comun usando get por el optional
            return ResponseEntity.ok("{\"message\": \"odontologo actualizado\"}");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Integer id) throws ResourceNotFoundException {
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("{\"message\": \"odontologo actualizado\"}");
    }

    @GetMapping("/apellido/{apellido}")
    public ResponseEntity<List<Odontologo>> buscarporApellido (@PathVariable String apellido) throws ResourceNotFoundException {
        return ResponseEntity.ok(odontologoService.buscarporApellido(apellido));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarporNombre(@PathVariable String nombre) throws ResourceNotFoundException {
        List<Odontologo> listadoOdontologos = odontologoService.buscarporNombre(nombre);
        return ResponseEntity.ok(listadoOdontologos);
    }
}
