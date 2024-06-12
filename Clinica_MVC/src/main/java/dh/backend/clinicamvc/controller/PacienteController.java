package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/paciente")
public class PacienteController {
    private IPacienteService pacienteService;

    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }


    @PostMapping
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente){
        Paciente pacienteRegistrado=pacienteService.registrarPaciente(paciente);
        if (pacienteRegistrado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Colocamos build porque NO tenemos body
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(pacienteRegistrado); // Al estar correcto, implementamos el objeto a creaar.
        }
    }
    @GetMapping
    public ResponseEntity<List<Paciente>> buscarPacientes (){

        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorID(@PathVariable Integer id){
        Optional<Paciente> pacienteARetornar=pacienteService.buscarPorId(id);
        if (pacienteARetornar.isPresent()) {
            Paciente pacienteEncontrado=pacienteARetornar.get();
            return ResponseEntity.ok(pacienteEncontrado);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente (@RequestBody Paciente paciente) {
        Optional<Paciente> pacienteOptional=pacienteService.buscarPorId(paciente.getId());
        if (pacienteOptional.isPresent()) {
            pacienteService.actualizarPaciente(paciente);
            return ResponseEntity.ok("{\"message\": \"Paciente modificado\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPaciente (@PathVariable Integer id){
        Optional<Paciente> pacienteOptional=pacienteService.buscarPorId(id);
        if (pacienteOptional.isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok("{\"message\": \"Paciente actualizado\"}");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
