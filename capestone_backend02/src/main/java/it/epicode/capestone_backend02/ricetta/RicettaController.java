package it.epicode.capestone_backend02.ricetta;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/ricetta")
@RequiredArgsConstructor
public class RicettaController {

    @Autowired
    private final RicettaService ricettaService;

    // GET - Tutte le ricette
    @GetMapping
    public List<RicettaDTO> getAllRicette() {
        return ricettaService.getAllRicette();
    }

    // GET - Ricetta per nome
    @GetMapping("/nome")
    public RicettaDTO getRicettaByNome(@RequestParam String nome) {
        return ricettaService.getRicettaByNome(nome);
    }


    // GET - Ricetta associata a un pasto
    @GetMapping("/pasto/{pastoId}")
    public RicettaDTO getRicettaByPastoId(@PathVariable Long pastoId) {
        return ricettaService.getRicettaByPastoId(pastoId);
    }

    // GET - Ricette dell'utente autenticato
    @GetMapping("/mie")
    public List<RicettaDTO> getMieRicette() {
        return ricettaService.getRicetteUtenteAutenticato();
    }
}
