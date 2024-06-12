package dh.backend.clinicamvc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoResponseDTO {
    Integer id;
    private OdontologoResponseDTO odontologo;
    private PacienteResponseDTO paciente;
}
