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

        if (paciente.getApellido() ==null || paciente.getApellido() == null) {

            throw new BadRequestException("{\"message\": \"paciente no pudo ser registrado\"}");
        } else {
            LOGGER.info("Registro de Paciente");
            Paciente pacienteRegistrado= pacienteRepository.save(paciente);
            return pacienteRegistrado;
        }

    }

    public Optional<Paciente> buscarPorId(Integer id) {
        LOGGER.info("Paciente encontrado: ");
        return pacienteRepository.findById(id);
    }

    public List<Paciente> buscarTodos() {
        return pacienteRepository.findAll();
    }

    @Override
    public void actualizarPaciente(Paciente paciente) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional=buscarPorId(paciente.getId());
        if (pacienteOptional.isPresent()) {
            pacienteRepository.save(paciente);
        } else {
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }


    }

    // Metodo eliminar Paciente que puede lanzar una excepcion
    @Override
    public void eliminarPaciente(Integer id) throws ResourceNotFoundException {
        Optional<Paciente> pacienteOptional = buscarPorId(id);
        if (pacienteOptional.isPresent()) {
            pacienteRepository.deleteById(id);
        } else {
            //Accion de lanzamiento de Excepcion.
            throw new ResourceNotFoundException("{\"message\": \"paciente no encontrado\"}");
        }
    }

}
