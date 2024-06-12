package dh.backend.clinicamvc.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "turnos")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    Paciente paciente;

    @ManyToOne (cascade = CascadeType.ALL)
    Odontologo odontologo;

    LocalDate fecha;

}
