package dh.backend.clinicamvc.repository;

import dh.backend.clinicamvc.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOdontologoRepository extends JpaRepository<Odontologo,Integer> {
    // HQL: Realizamos las query
    //Seleccionamos desde la entidad Odontologo todos los odontologos cuyo apellido sean =?
    @Query("Select o from Odontologo o where o.apellido = ?1")
    List<Odontologo> buscarporApellido(String apellido);//apellido=?1 - ? significa parametro y 1 el N° de parametro que va a recibir

    //Este es otro metodo que nos permite personalizar y normalizar la busqueda - :nombre significa una variable que vendra por parametro
    // y el Param señala como se va a llamar la variable
    @Query("Select o from Odontologo o where LOWER(o.nombre) LIKE LOWER(CONCAT('%',:nombre,'%'))")
    List<Odontologo> findByNombreLike (@Param("nombre") String nombre);
}
