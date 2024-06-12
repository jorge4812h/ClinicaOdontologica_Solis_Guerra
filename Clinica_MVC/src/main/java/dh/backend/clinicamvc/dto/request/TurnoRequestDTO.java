package dh.backend.clinicamvc.dto.request;
import lombok.*;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TurnoRequestDTO {
    private Integer paciente_id;
    private Integer odontologo_id;
    private LocalDate fecha;

}
