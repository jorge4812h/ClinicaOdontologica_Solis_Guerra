package dh.backend.clinicamvc.controller;


import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.service.IOdontologoService;
import dh.backend.clinicamvc.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/vista")
public class VistaController {
    public IPacienteService pacienteService;
    public IOdontologoService odontologoService;

    public VistaController(IPacienteService pacienteService, IOdontologoService odontologoService) {
        this.pacienteService = pacienteService;
        this.odontologoService = odontologoService;
    }

    @GetMapping("/buscarPaciente")
    public String buscarPacientePorId(Model model, @RequestParam Integer id) throws ResourceNotFoundException {
        Optional<Paciente> paciente = pacienteService.buscarPorId(id);
            Paciente pacienteEncontrado=paciente.get();
            model.addAttribute("especialidad", "Paciente");
            model.addAttribute("nombre", pacienteEncontrado.getNombre());
            model.addAttribute("apellido", pacienteEncontrado.getApellido());
        return "index";
    }

    @GetMapping("/buscarOdontologo")
    public String buscarOdontologoPorId(Model model, @RequestParam Integer id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologo=odontologoService.buscarOdontologoporID(id);
            Odontologo odontologoARetornar=odontologo.get(); // Entontes registras el resultado del opcional en un odontologo comun usando get por el optional.
            model.addAttribute("especialidad","Paciente");
            model.addAttribute("nombre",odontologoARetornar.getNombre());
            model.addAttribute("apellido",odontologoARetornar.getApellido());
        return "index";
    }


}
