package it.epicode.capestone_backend02.ricetta;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RicettaDTO {
    private String nome;
    private String ingredienti;
    private String procedimento;

}
