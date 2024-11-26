package backend.crm_consultancy.repository;

import backend.crm_consultancy.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, String> {
}
