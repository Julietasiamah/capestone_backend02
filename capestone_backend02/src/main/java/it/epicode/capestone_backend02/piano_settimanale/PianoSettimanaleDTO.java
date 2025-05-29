package it.epicode.capestone_backend02.piano_settimanale;

import it.epicode.capestone_backend02.categoria_pasto.CategoriaPasto;
import it.epicode.capestone_backend02.giorni_settimana.GiorniSettimana;
import it.epicode.capestone_backend02.pasto.PastoEntity;
import it.epicode.capestone_backend02.preferenza_alimentare.PreferenzaAlimentare;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PianoSettimanaleDTO {
    private Long id;
    private GiorniSettimana giorno;
    private CategoriaPasto categoriaPasto;
    private PastoEntity pasto;
    private PreferenzaAlimentare preferenza;


}
