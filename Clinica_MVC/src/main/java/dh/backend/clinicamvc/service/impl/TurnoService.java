package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.dto.request.TurnoRequestDTO;
import dh.backend.clinicamvc.dto.response.OdontologoResponseDTO;
import dh.backend.clinicamvc.dto.response.PacienteResponseDTO;
import dh.backend.clinicamvc.dto.response.TurnoResponseDTO;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.repository.ITurnoRepository;
import dh.backend.clinicamvc.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService implements ITurnoService {

    private ITurnoRepository turnoRepository;
    private IPacienteRepository pacienteRepository;
    private IOdontologoRepository odontologoRepository;
    private ModelMapper modelMapper;

    private static Logger LOGGER= LoggerFactory.getLogger(TurnoService.class);

    public TurnoService(ITurnoRepository turnoRepository, IPacienteRepository pacienteRepository, IOdontologoRepository odontologoRepository, ModelMapper modelMapper) {
        this.turnoRepository = turnoRepository;
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoResponseDTO registrarTurno(TurnoRequestDTO turnoRequestDTO) throws BadRequestException {
        Optional<Paciente> paciente=pacienteRepository.findById(turnoRequestDTO.getPaciente_id());
        Optional<Odontologo> odontologo=odontologoRepository.findById(turnoRequestDTO.getOdontologo_id());
        Turno turnoARegistrar=new Turno();
        Turno turnoGuardado=null;
        TurnoResponseDTO turnoADevolver=null;
        if (paciente.isPresent() && odontologo.isPresent()) {

            turnoARegistrar.setPaciente(paciente.get());
            turnoARegistrar.setOdontologo(odontologo.get());
            turnoARegistrar.setFecha(turnoRequestDTO.getFecha());
            turnoGuardado=turnoRepository.save(turnoARegistrar);
            turnoADevolver=mapToResponseDto(turnoGuardado);
            LOGGER.info("Turno registrado");
            return turnoADevolver;
        } else {
            LOGGER.error("Paciente u odontologo no existe");
            throw new BadRequestException("{\"message\": \"paciente u odontologo no existe\"}");
        }

    }

    @Override
    public TurnoResponseDTO buscarTurnoPorId(Integer id) {
        Optional<Turno> turnoEncontrado=turnoRepository.findById(id);
        if (turnoEncontrado.isPresent()) {
            TurnoResponseDTO turnoADevolver=mapToResponseDto(turnoEncontrado.get());
            LOGGER.info("el turno es " + turnoADevolver);
            return turnoADevolver;

        }
        LOGGER.error("turno no encontrado");
        return null;
    }

    @Override
    public List<TurnoResponseDTO> buscarTodosTurnos() {
        List<Turno> turnos=turnoRepository.findAll();
        List<TurnoResponseDTO> turnosADevolver=new ArrayList<>();

        for (Turno turno: turnos) {
            turnosADevolver.add(mapToResponseDto(turno));
        }
        LOGGER.info("los turnos son: " + turnosADevolver);
        return turnosADevolver;
    }

    @Override
    public void actualizarTurno(Integer id, TurnoRequestDTO turnoRequestDTO) {

        //Para actualizar el turno primero debemos obtener el paciente y odontologo.
        Optional<Paciente> paciente=pacienteRepository.findById(turnoRequestDTO.getPaciente_id());
        Optional<Odontologo> odontologo=odontologoRepository.findById(turnoRequestDTO.getOdontologo_id());

        // como DTO no contempla id, lo agregamos como variable de ingreso en el metodo
        Optional<Turno> turno=turnoRepository.findById(id);

        // Turno que se va a actualizar en BD.
        Turno turnoActalizado=new Turno();
        if (paciente.isPresent() && odontologo.isPresent() && turno.isPresent()) {
            // Seteamos los valores del turno a actualizar

            turnoActalizado.setPaciente(paciente.get());
            turnoActalizado.setOdontologo(odontologo.get());
            turnoActalizado.setFecha(turnoRequestDTO.getFecha());
            turnoActalizado.setId(id);

            LOGGER.info("El turno ha sido actualizado");
            turnoRepository.save(turnoActalizado);
        }

    }

    @Override
    public void eliminarTurno(Integer id) throws ResourceNotFoundException {
        if (turnoRepository.existsById(id)) {
            LOGGER.info("turno eliminado");
            turnoRepository.deleteById(id);

        }else {
            LOGGER.error("turno no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"turno no encontrado\"}");
        }
    }

    @Override
    public List<TurnoResponseDTO> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
        List<Turno> listadoTurnos = turnoRepository.buscarTurnoEntreFechas(startDate, endDate);
        List<TurnoResponseDTO> listadoARetornar = new ArrayList<>();
        TurnoResponseDTO turnoAuxiliar = null;
        for (Turno turno: listadoTurnos){
            turnoAuxiliar = mapToResponseDto(turno);
            listadoARetornar.add(turnoAuxiliar);
        }
        LOGGER.info("Los turnos entre las fechas" + startDate + "y" + endDate + "son: " + listadoARetornar);
        return listadoARetornar;
    }


    // metodo que mapea turno en turnoResponseDto
    private TurnoResponseDTO mapToResponseDto(Turno turno){
        TurnoResponseDTO turnoResponseDto = modelMapper.map(turno, TurnoResponseDTO.class);
        turnoResponseDto.setOdontologo(modelMapper.map(turno.getOdontologo(), OdontologoResponseDTO.class));
        turnoResponseDto.setPaciente(modelMapper.map(turno.getPaciente(), PacienteResponseDTO.class));
        return  turnoResponseDto;
    }


}
