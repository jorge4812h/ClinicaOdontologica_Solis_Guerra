package dh.backend.clinicamvc.service;

import dh.backend.clinicamvc.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {

    Odontologo registrarOdontologo(Odontologo odontologo);

    List<Odontologo> listarOdontologos();

    Optional<Odontologo> buscarOdontologoporID(Integer id);

    void actualizarOdontologo(Odontologo odontologo);
    void eliminarOdontologo(Integer id);

    List<Odontologo> buscarporApellido(String apellido);

    List<Odontologo> buscarporNombre(String nombre);
}
