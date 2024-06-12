package dh.backend.clinicamvc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "pacientes")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String apellido;
    private String nombre;
    private String DNI;
    private LocalDate fechaIngreso;

    @OneToOne(cascade = CascadeType.ALL) // Realiza todas las operaciones y se carga automaticamente.
    @JoinColumn(name = "domicilio_id") // Le damos nombre a la columna en BD - Es opcional
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)// Indicamos que este one to many estara mapeado por atributo de otra tabla, es decir, cambio en una tabla aplica en las otras que tenga relacion.
    @JsonIgnore
    private Set<Turno> turnoSet=new HashSet<>();
    // Usamos Set porque va a tener muchos turnos: 1 paciente tiene muchos turnos y ademas, evita que tengamos duplicados.

}
