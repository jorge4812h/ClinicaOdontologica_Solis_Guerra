package dh.backend.clinicamvc.repository;

import dh.backend.clinicamvc.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

                                                        //Entidad, tipo de dato de PK
public interface IPacienteRepository extends JpaRepository<Paciente,Integer> {
}
