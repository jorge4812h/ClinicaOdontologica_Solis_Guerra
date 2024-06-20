package dh.backend.clinicamvc.repository;

import dh.backend.clinicamvc.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Entidad, tipo de dato de PK
public interface IPacienteRepository extends JpaRepository<Paciente,Integer> {
    @Query("Select p from Paciente p where p.DNI = ?1")
    List<Paciente> buscarPorDni(String DNI);

    @Query("SELECT p FROM Paciente p WHERE LOWER(p.domicilio.provincia) LIKE LOWER(CONCAT('%', :provincia, '%'))")
    List<Paciente> buscarPorProvinciaLike(@Param("provincia") String provincia);

}
