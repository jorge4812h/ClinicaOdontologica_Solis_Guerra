package dh.backend.clinicamvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "odontologos")
public class Odontologo {
    @Id // Indicamos que este atrbuto es ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_Increment
    private Integer id;
    private String nombre;
    private String apellido;
    private String matricula;

    @OneToMany(mappedBy = "odontologo", cascade = CascadeType.ALL) // Para mapear el atributo en la otra tabla
    @JsonIgnore
    private Set<Turno> turnoSet=new HashSet<>();

}
