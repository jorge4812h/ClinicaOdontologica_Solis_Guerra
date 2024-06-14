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
    public ResponseEntity<Odontologo> buscarOdontologoPorID(@PathVariable Integer id){
        Optional<Odontologo> odontologo=odontologoService.buscarOdontologoporID(id); //Busco Odontologo y lo guardo en optional
        if (odontologo.isPresent()) { //Si odontologoARetornar existe...
            Odontologo odontologoARetornar=odontologo.get(); // Entontes registras el resultado del opcional en un odontologo comun usando get por el optional.
            return ResponseEntity.ok(odontologoARetornar);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
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
    public ResponseEntity<List<Odontologo>> buscarporApellido (@PathVariable String apellido){
        return ResponseEntity.ok(odontologoService.buscarporApellido(apellido));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<List<Odontologo>> buscarporNombre(@PathVariable String nombre){
        List<Odontologo> listadoOdontologos=odontologoService.buscarporNombre(nombre);
        if (listadoOdontologos.size()>0) {
            return ResponseEntity.ok(listadoOdontologos);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
