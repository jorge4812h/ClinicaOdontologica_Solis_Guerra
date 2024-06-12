package dh.backend.clinicamvc.test;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoTest {

    public static Logger LOGGER= LoggerFactory.getLogger(OdontologoTest.class);


    private OdontologoService odontologoService;
    private Odontologo odontologo;

    @BeforeEach
    void SetUp(){
        odontologo=new Odontologo();
        odontologo.setApellido("Limaña");
        odontologo.setNombre("Pepito");
        odontologo.setMatricula("12345");
    }

    @Test
    @DisplayName("Registro de Odontologo")
    void Test1(){
        Odontologo odontologoRegistrado=odontologoService.registrarOdontologo(odontologo);
        assertNotNull(odontologoRegistrado);
    }

    @Test
    @DisplayName("Testo listado de Odontologos")

    void Test2(){

        List<Odontologo> odontologos=odontologoService.listarOdontologos();

        assertTrue(!odontologos.isEmpty());
    }
    @Test
    @DisplayName("Testo busqueda de Odontologos por ID")

    void TestBusquedaID(){
        Integer id=1;
        Optional<Odontologo> odontologoEncontrado=odontologoService.buscarOdontologoporID(id);
        Odontologo odontologoARetornar=odontologoEncontrado.get();

        assertEquals(id,odontologoARetornar.getId());
    }
}
