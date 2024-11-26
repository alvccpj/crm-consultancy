package backend.crm_consultancy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "medico_db")
@Data
public class Medico {
    @Id
    private String crm;

    private String nome;

    private String email;
}
