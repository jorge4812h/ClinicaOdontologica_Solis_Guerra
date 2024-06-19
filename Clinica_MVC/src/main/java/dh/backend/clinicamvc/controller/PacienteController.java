package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
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
    public ResponseEntity<Paciente> registrarPaciente(@RequestBody Paciente paciente) throws BadRequestException {
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.registrarPaciente(paciente)); // Al estar correcto, implementamos el objeto a creaar.
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> buscarPacientes (){
        return ResponseEntity.ok(pacienteService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPacientePorID(@PathVariable Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteEncontrado=pacienteService.buscarPorId(id);
        Paciente pacienteADevolver=pacienteEncontrado.get();
        return ResponseEntity.ok(pacienteADevolver);
    }

    @PutMapping
    public ResponseEntity<String> actualizarPaciente (@RequestBody Paciente paciente) throws ResourceNotFoundException {
        pacienteService.actualizarPaciente(paciente);
        return ResponseEntity.ok("{\"message\": \"Paciente modificado\"}");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> borrarPaciente (@PathVariable Integer id) throws ResourceNotFoundException {
        pacienteService.eliminarPaciente(id);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
