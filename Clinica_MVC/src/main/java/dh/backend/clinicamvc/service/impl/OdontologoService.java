package dh.backend.clinicamvc.service.impl;

import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.exception.BadRequestException;
import dh.backend.clinicamvc.exception.ResourceNotFoundException;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.service.IOdontologoService;
import lombok.extern.java.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService implements IOdontologoService {
    private IOdontologoRepository odontologoRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class);

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
    public Optional<Odontologo> buscarOdontologoporID(Integer id) throws ResourceNotFoundException {
        LOGGER.info("Busqueda de Odontologo.");
        Optional<Odontologo> odontologo=odontologoRepository.findById(id); //Busco Odontologo y lo guardo en optional
        if (odontologo.isEmpty()) { //Si odontologoARetornar existe...
            LOGGER.error("Odontologo no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"Odontologo no encontrado\"}");
        } else {
            LOGGER.info("Odontologo encontrado");
            return odontologo;
        }
    }

    //Optional: Guarda dato si existe, y si no, guarda un nulo.

    @Override
    public void actualizarOdontologo(Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoOptional=buscarOdontologoporID(odontologo.getId());
        if (odontologoOptional.isPresent()) { //Si odontologoARetornar existe...
            LOGGER.info("Odontologo actualizado");
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
            throw new ResourceNotFoundException("{\"message\": \"Odontologo no encontrado\"}");
        }
    }

    //Metodos HQL
    @Override
    public List<Odontologo> buscarporApellido(String apellido) throws ResourceNotFoundException {
        List<Odontologo> odontologoEncontrado= odontologoRepository.buscarporApellido(apellido);
        if (odontologoEncontrado.isEmpty()) {
            LOGGER.error("Odontologo no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"Odontologo no encontrado\"}");
        } else {
            LOGGER.info("Odontologo encontrado");
            return odontologoEncontrado;
        }
    }

    @Override
    public List<Odontologo> buscarporNombre(String nombre) throws ResourceNotFoundException {
        List<Odontologo> odontologoEncontrado= odontologoRepository.findByNombreLike(nombre);
        if (odontologoEncontrado.isEmpty()) {
            LOGGER.error("Odontologo no encontrado");
            throw new ResourceNotFoundException("{\"message\": \"Odontologo no encontrado\"}");
        } else {
            LOGGER.info("Odontologo encontrado");
            return odontologoEncontrado;
        }
    }

}



