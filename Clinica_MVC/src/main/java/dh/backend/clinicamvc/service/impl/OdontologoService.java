package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Odontologo registrarOdontologo(Odontologo odontologo) throws BadRequestException {
        if (odontologo.getNombre() ==null || odontologo.getApellido() == null) {
            throw new BadRequestException("{\"message\": \"Odontologo no pudo ser registrado\"}");
        } else {
            Odontologo odontologoRegistrado=odontologoRepository.save(odontologo);
            return odontologoRegistrado;
        }

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
    public void actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional=buscarOdontologoporID(odontologo.getId());
        if (odontologoOptional.isPresent()) { //Si odontologoARetornar existe...
            odontologoRepository.save(odontologo);// Entontes registras el resultado del opcional en un odontologo comun usando get por el optional
        } else {
            throw new ResourceNotFoundException("{\"message\": \"Odontologo no encontrado\"}");
        }
         //Aqui NO actualizamos, solo reemplazamos la data de odontologo.
    }

    @Override
    public void eliminarOdontologo(Integer id) throws ResourceNotFoundException {
        Optional<Odontologo>  odontologoOptional = buscarOdontologoporID(id);
        if (odontologoOptional.isPresent()) {
            odontologoRepository.deleteById(id);
        }else {
            throw new ResourceNotFoundException("{\"message\": \"Odontologo no encontrad\"}");
        }
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



