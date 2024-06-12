package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;

    public OdontologoService(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    public IOdontologoRepository getOdontologoRepository() {
        return odontologoRepository;
    }

    public void setOdontologoRepository(IOdontologoRepository odontologoRepository) {
        this.odontologoRepository = odontologoRepository;
    }

    @Override
    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        return odontologoRepository.findAll();
    }

    @Override //Este metodo va a devolver un Optional de tipo Odontologo
    public Optional<Odontologo> buscarOdontologoporID(Integer id) {
        return odontologoRepository.findById(id);
    }

    //Optional: Guarda dato si existe, y si no, guarda un nulo.

    @Override
    public void actualizarOdontologo(Odontologo odontologo) {
        odontologoRepository.save(odontologo); //Aqui NO actualizamos, solo reemplazamos la data de odontologo.
    }

    @Override
    public void eliminarOdontologo(Integer id) {
        odontologoRepository.deleteById(id);
    }

    //Metodos HQL
    @Override
    public List<Odontologo> buscarporApellido(String apellido) {
        return odontologoRepository.buscarporApellido(apellido);
    }

    @Override
    public List<Odontologo> buscarporNombre(String nombre) {
        return odontologoRepository.findByNombreLike(nombre);
    }

}



