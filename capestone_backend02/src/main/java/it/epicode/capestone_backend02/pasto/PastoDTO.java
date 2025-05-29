package it.epicode.capestone_backend02.pasto;

import it.epicode.capestone_backend02.ricetta.Ricetta;
import lombok.Data;

@Data
public class PastoDTO {
    private Long id;
    private String nome;
    private String imgUrl;
    private Ricetta ricetta;

}
