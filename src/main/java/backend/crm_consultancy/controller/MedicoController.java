package backend.crm_consultancy.controller;

import backend.crm_consultancy.dto.MedicoDto;
import backend.crm_consultancy.model.Medico;
import backend.crm_consultancy.repository.MedicoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    public class MedicoController {

        @Autowired
        private MedicoRepository medicoRepository;

        @PostMapping("/medicos")
        public ResponseEntity<Medico> createMedico(@RequestBody MedicoDto medicoDto) {
            Medico medico = new Medico();
            BeanUtils.copyProperties(medicoDto, medico);

            Medico savedMedico = medicoRepository.save(medico);

            return ResponseEntity.status(HttpStatus.CREATED).body(savedMedico);
        }

        @GetMapping("/medicos")
        public ResponseEntity<List<Medico>> getAllMedicos() {
            List<Medico> allMedicos = medicoRepository.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(allMedicos);
        }

        @GetMapping("/medicos/{crm}")
        public ResponseEntity<Object> getMedicoByCRM(@PathVariable String crm) {
            Optional<Medico> foundMedico = medicoRepository.findById(crm);
            if (foundMedico.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico not found!");
            }
            return ResponseEntity.status(HttpStatus.OK).body(foundMedico.get());
        }

        @DeleteMapping("/medicos/{crm}")
        public ResponseEntity<String> deleteMedicoByCRM(@PathVariable String crm) {
            Optional<Medico> foundMedico = medicoRepository.findById(crm);
            if (foundMedico.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico not found!");
            }
            medicoRepository.delete(foundMedico.get());
            return ResponseEntity.status(HttpStatus.OK).body("Medico deleted successfully!");
        }

        @PutMapping("/medicos/{crm}")
        public ResponseEntity<Object> updateMedicoByCRM(@RequestBody MedicoDto medicoDto,
                                                        @PathVariable String crm) {
            Optional<Medico> foundMedico = medicoRepository.findById(crm);
            if (foundMedico.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medico not found!");
            }

            Medico medico = foundMedico.get();
            BeanUtils.copyProperties(medicoDto, medico);
            return ResponseEntity.status(HttpStatus.OK).body(medicoRepository.save(medico));
        }
}
