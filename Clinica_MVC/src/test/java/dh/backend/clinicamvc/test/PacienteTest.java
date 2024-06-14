package dh.backend.clinicamvc.test;

import dh.backend.clinicamvc.entity.Domicilio;
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.service.impl.PacienteService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PacienteTest {

    private static Logger LOGGER = LoggerFactory.getLogger(PacienteTest.class);

    @Autowired
    private PacienteService pacienteService;

    private Paciente paciente;
    @BeforeEach
    void Setup() {
        paciente=new Paciente();
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
    }

    @Test
    @DisplayName("Testeo ingreso de paciente")

    void TestPacienteGuardado() throws BadRequestException {
        Paciente pacienteRegistrado=pacienteService.registrarPaciente(paciente);
        assertNotNull(pacienteRegistrado);
    }

    @Test
    @DisplayName("Testeo busqueda de paciente por id")

    void TestBusquedaID(){
        Integer id=1;
        Optional<Paciente> pacienteOptional=pacienteService.buscarPorId(id);
        Paciente paciente1=pacienteOptional.get();

        assertEquals(id,paciente1.getId());
    }

}
