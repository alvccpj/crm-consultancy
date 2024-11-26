package backend.crm_consultancy.controller;

import backend.crm_consultancy.dto.EspecialidadeDto;
import backend.crm_consultancy.model.Especialidade;
import backend.crm_consultancy.repository.EspecialidadeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EspecialidadeController {

    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    @PostMapping("/especialidades")
    public ResponseEntity<Especialidade> createEspecialidade(@RequestBody EspecialidadeDto especialidadeDto) {
        Especialidade especialidade = new Especialidade();
        BeanUtils.copyProperties(especialidadeDto, especialidade);

        Especialidade savedEspecialidade = especialidadeRepository.save(especialidade);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedEspecialidade);
    }

    @GetMapping("/especialidades")
    public ResponseEntity<List<Especialidade>> getAllEspecialidades() {
        List<Especialidade> allEspecialidades = especialidadeRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(allEspecialidades);
    }

    @GetMapping("/especialidades/{nome}")
    public ResponseEntity<Object> getEspecialidadeByNome(@PathVariable String nome) {
        Optional<Especialidade> foundEspecialidade = especialidadeRepository.findById(nome);
        if (foundEspecialidade.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade not found!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(foundEspecialidade.get());
    }

    @DeleteMapping("/especialidades/{nome}")
    public ResponseEntity<String> deleteEspecialidadeByNome(@PathVariable String nome) {
        Optional<Especialidade> foundEspecialidade = especialidadeRepository.findById(nome);
        if (foundEspecialidade.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade not found!");
        }
        especialidadeRepository.delete(foundEspecialidade.get());
        return ResponseEntity.status(HttpStatus.OK).body("Especialidade deleted successfully!");
    }

    @PutMapping("/especialidades/{nome}")
    public ResponseEntity<Object> updateEspecialidadeByNome(@RequestBody EspecialidadeDto especialidadeDto,
                                                            @PathVariable String nome) {
        Optional<Especialidade> foundEspecialidade = especialidadeRepository.findById(nome);
        if (foundEspecialidade.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Especialidade not found!");
        }

        Especialidade especialidade = foundEspecialidade.get();
        BeanUtils.copyProperties(especialidadeDto, especialidade);
        return ResponseEntity.status(HttpStatus.OK).body(especialidadeRepository.save(especialidade));
    }
}
