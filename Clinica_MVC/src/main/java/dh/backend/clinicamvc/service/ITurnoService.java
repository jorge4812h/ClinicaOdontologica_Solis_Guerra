package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.dto.request.TurnoRequestDTO;
import dh.backend.clinicamvc.dto.response.TurnoResponseDTO;
import dh.backend.clinicamvc.exception.BadRequestException;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {

    TurnoResponseDTO registrarTurno(TurnoRequestDTO turnoRequestDTO) throws BadRequestException;
    TurnoResponseDTO buscarTurnoPorId(Integer id);
    List<TurnoResponseDTO> buscarTodosTurnos();
    void actualizarTurno(Integer id, TurnoRequestDTO turnoRequestDTO);
    void eliminarTurno(Integer id);

    // HQL
    List<TurnoResponseDTO> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate);
}
