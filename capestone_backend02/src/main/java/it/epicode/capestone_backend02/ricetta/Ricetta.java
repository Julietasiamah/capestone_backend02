package it.epicode.capestone_backend02.ricetta;



import it.epicode.capestone_backend02.auth.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ricette")
public class Ricetta {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(columnDefinition = "TEXT")
    private String ingredienti;
    @Column(columnDefinition = "TEXT")
    private String procedimento;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    private AppUser utente;


}
