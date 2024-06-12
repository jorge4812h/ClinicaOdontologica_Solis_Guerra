package dh.backend.clinicamvc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDTO {
    private Integer id;
    private String apellido;
    private String nombre;
    private String DNI;
}
