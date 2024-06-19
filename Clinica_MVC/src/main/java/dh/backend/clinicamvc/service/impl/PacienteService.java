package dh.backend.clinicamvc.service.impl;


import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.service.IPacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService implements IPacienteService {
    private IPacienteRepository pacienteRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);

    public PacienteService(IPacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public Paciente registrarPaciente(Paciente paciente) throws BadRequestException {
        if (paciente.getNombre() ==null || paciente.getApellido() == null) {
            LOGGER.error("Paciente no pudo ser registrado");
            throw new BadRequestException("{\"message\": \"Paciente no pudo ser registrado\"}");
        } else {
            Paciente pacienteRegistrado= pacienteRepository.save(paciente);
            LOGGER.info("Paciente Registrado");
            return pacienteRegistrado;
        }
    }

    public Optional<Paciente> buscarPorId(Integer id) throws ResourceNotFoundException {
        LOGGER.info("Busqueda de paciente...");
        Optional<Paciente> pacienteARetornar=pacienteRepository.findById(id);
        if (pacienteARetornar.isEmpty()) {
            LOGGER.error("Paciente no encontrado.");
            throw new ResourceNotFoundException("{\"message\": \"Paciente no encontrado\"}");
        } else {
            LOGGER.info("Paciente encontrado");
            return pacienteARetornar;
        }

    }


    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public void actualizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional=buscarPorId(paciente.getId());
        if (pacienteOptional.isEmpty()) {
            LOGGER.info("Paciente no pudo ser actualizado.");
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        } else {
            LOGGER.info("Paciente actualizado.");
            pacienteRepository.save(paciente);
        }
    }
    // Metodo eliminar Paciente que puede lanzar una excepcion
    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = buscarPorId(id);
        if (pacienteOptional.isPresent()) {
            pacienteRepository.deleteById(id);
            LOGGER.info("Paciente Eliminado");
        } else {
            //Accion de lanzamiento de Excepcion.
            LOGGER.error("Paciente no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
    }
}
