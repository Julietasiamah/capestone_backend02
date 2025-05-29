package it.epicode.capestone_backend02.pasto;

import it.epicode.capestone_backend02.preferenza_alimentare.PreferenzaAlimentare;
import it.epicode.capestone_backend02.ricetta.Ricetta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pasti")
public class PastoEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String imgUrl;

    @OneToOne
    private Ricetta ricetta;
}
