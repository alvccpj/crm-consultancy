package backend.crm_consultancy.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "especialidade_db")
public class Especialidade {
        @Id
        private String especialidade;
}
