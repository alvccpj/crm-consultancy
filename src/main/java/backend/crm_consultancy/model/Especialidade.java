package backend.crm_consultancy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "especialidade_db")
@Data
public class Especialidade {
        @Id
        private String nome;
}
