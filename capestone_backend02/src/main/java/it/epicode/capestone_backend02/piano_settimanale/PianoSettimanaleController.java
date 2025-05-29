package it.epicode.capestone_backend02.piano_settimanale;


import it.epicode.capestone_backend02.preferenza_alimentare.PreferenzaAlimentare;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/piano_settimanale")
@RequiredArgsConstructor
public class PianoSettimanaleController {
    @Autowired
    private final PianoSettimanaleService pianoSettimanaleService;

    //metodo per visualizzare tutti i piani
    @GetMapping
    public ResponseEntity<List<PianoSettimanaleDTO>> getAllPiani() {
        return ResponseEntity.ok(pianoSettimanaleService.getAllPiani());
    }

    //metodo per visualizzare un piano in base alla preferenza alimentare
    @GetMapping("/preferenza/{diet}")
    public ResponseEntity <List<PianoSettimanaleDTO>> getPianoByPreferenza(
            @PathVariable ("diet") PreferenzaAlimentare preferenza){
        return ResponseEntity.ok(pianoSettimanaleService.getPianiByPreferenza(preferenza));
    }

    //metodo per modificare piano
    @PutMapping ("/{id}")
    public ResponseEntity <PianoSettimanaleDTO> modificaPiano (@PathVariable Long id, @RequestBody PianoSettimanaleDTO pianoSettimanale){
        return ResponseEntity.ok(pianoSettimanaleService.modificaPiano(id, pianoSettimanale));
    }

    //metodo per salvare piano
    @PostMapping
    public ResponseEntity<PianoSettimanaleDTO> creaPiano(@RequestBody PianoSettimanaleDTO dto) {
        return ResponseEntity.ok(pianoSettimanaleService.creaPiano(dto));
    }

    //metodo per cancellare piano
    @DeleteMapping ("/{id}")
    public ResponseEntity <Void> cancellaPiano (@PathVariable Long id){
        pianoSettimanaleService.cancellaPiano(id);
        return ResponseEntity.noContent().build();
    }

    // GET - Piani dell'utente autenticato
    @GetMapping("/miei")
    public ResponseEntity<List<PianoSettimanaleDTO>> getMieiPiani() {
        return ResponseEntity.ok(pianoSettimanaleService.getPianiUtenteAutenticato());
    }


}
