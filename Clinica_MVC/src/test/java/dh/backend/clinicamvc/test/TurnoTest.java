package dh.backend.clinicamvc.test;

import dh.backend.clinicamvc.dto.request.TurnoRequestDTO;
import dh.backend.clinicamvc.dto.response.TurnoResponseDTO;
import dh.backend.clinicamvc.entity.Domicilio;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import dh.backend.clinicamvc.service.impl.PacienteService;
import dh.backend.clinicamvc.service.impl.TurnoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TurnoTest{
    //Pendiente elaborar Test de Turnos (libre eleccion).

    @Autowired
    private TurnoService turnoService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private PacienteService pacienteService;

    private Turno turno;

    Paciente paciente=new Paciente();
    @BeforeEach
    void Setup() throws BadRequestException {


        paciente.setApellido("Cosme");
        paciente.setNombre("Menganito");
        paciente.setDNI("12345678");
        paciente.setApellido("Menganito");
        paciente.setFechaIngreso(LocalDate.parse("2024-05-15"));
        Domicilio domicilio=new Domicilio();
        domicilio.setCalle("Ca. Prueba");
        domicilio.setLocalidad("Springfield");
        domicilio.setNumero(123);
        domicilio.setProvincia("California");
        paciente.setDomicilio(domicilio);
        pacienteService.registrarPaciente(paciente);

        Odontologo odontologo=new Odontologo();
        odontologo.setApellido("Limaña");
        odontologo.setNombre("Pepito");
        odontologo.setMatricula("12345");
        odontologoService.registrarOdontologo(odontologo);
    }



    @Test
    @DisplayName("Testeo registro de turno")

    void TestRegistroTurno() throws BadRequestException {
        TurnoRequestDTO turnoRequestDTO=new TurnoRequestDTO();
        turnoRequestDTO.setPaciente_id(paciente.getId());
        turnoRequestDTO.setOdontologo_id(1);
        turnoRequestDTO.setFecha(LocalDate.parse("2024-05-23"));

        TurnoResponseDTO turnoADevolver = turnoService.registrarTurno(turnoRequestDTO);

        assertNotNull(1);

    }
}
