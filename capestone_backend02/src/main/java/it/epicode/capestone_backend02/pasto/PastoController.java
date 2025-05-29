package it.epicode.capestone_backend02.pasto;

import it.epicode.capestone_backend02.categoria_pasto.CategoriaPasto;
import it.epicode.capestone_backend02.preferenza_alimentare.PreferenzaAlimentare;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/pasti")
@RequiredArgsConstructor
public class PastoController {

    @Autowired
    private final PastoService pastoService;

    // GET - Tutti i pasti
    @GetMapping
    public List<PastoDTO> getAllPasti() {
        return pastoService.getAllPasti();
    }

//    // GET - Pasti per preferenza
//    @GetMapping("/preferenza")
//    public List<PastoDTO> getPastiByPreferenza(@RequestParam PreferenzaAlimentare preferenza) {
//        return pastoService.getPastiByPreferenza(preferenza);
//    }

//    // GET - Pasti per categoria
//    @GetMapping("/categoria")
//    public List<PastoDTO> getPastiByCategoria(@RequestParam CategoriaPasto categoria) {
//        return pastoService.getPastiByCategoria(categoria);
//    }

    // POST - Crea pasto
    @PostMapping
    public PastoDTO createPasto(@RequestBody PastoDTO pastoDTO) {
        return pastoService.createPasto(pastoDTO);
    }

    // PUT - Modifica pasto
    @PutMapping("/{id}")
    public PastoDTO updatePasto(@PathVariable Long id, @RequestBody PastoDTO pastoDTO) {
        return pastoService.updatePasto(id, pastoDTO);
    }

    // DELETE - Cancella pasto
    @DeleteMapping("/{id}")
    public void deletePasto(@PathVariable Long id) {
        pastoService.deletePasto(id);
    }

}
