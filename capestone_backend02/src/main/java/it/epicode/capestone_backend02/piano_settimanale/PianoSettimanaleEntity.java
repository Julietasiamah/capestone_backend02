package it.epicode.capestone_backend02.piano_settimanale;

import it.epicode.capestone_backend02.auth.AppUser;

import it.epicode.capestone_backend02.categoria_pasto.CategoriaPasto;
import it.epicode.capestone_backend02.giorni_settimana.GiorniSettimana;
import it.epicode.capestone_backend02.pasto.PastoEntity;
import it.epicode.capestone_backend02.preferenza_alimentare.PreferenzaAlimentare;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "piani_settimanali")
public class PianoSettimanaleEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated (EnumType.STRING)
    private GiorniSettimana giorno;

    @Enumerated (EnumType.STRING)
    private CategoriaPasto categoriaPasto;


    @ManyToOne
    @JoinColumn(name = "pasto_id")
    private PastoEntity pasto;

  @Enumerated(EnumType.STRING)
   private PreferenzaAlimentare preferenza;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private AppUser utente;









}
