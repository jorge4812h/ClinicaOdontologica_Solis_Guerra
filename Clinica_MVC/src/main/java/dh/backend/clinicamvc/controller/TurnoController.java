package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.dto.request.TurnoRequestDTO;
import dh.backend.clinicamvc.dto.response.TurnoResponseDTO;
import dh.backend.clinicamvc.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turno")
public class TurnoController {
    private ITurnoService turnoService;

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    @PostMapping
    public ResponseEntity<TurnoResponseDTO> agregarTurno(@RequestBody TurnoRequestDTO turno){
        TurnoResponseDTO turnoRegistrado= turnoService.registrarTurno(turno);
        if (turnoRegistrado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Colocamos build porque NO tenemos body
        } else {
            return ResponseEntity.status(HttpStatus.CREATED).body(turnoRegistrado); // Al estar correcto, implementamos el objeto a creaar.
        }
    }

    @GetMapping
    public ResponseEntity<List<TurnoResponseDTO>> buscarTodosTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodosTurnos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> actualizarTurno (@PathVariable Integer id, @RequestBody TurnoRequestDTO turno){
        turnoService.actualizarTurno(id,turno);
        return ResponseEntity.ok("Turno actualizado");

    }
}
