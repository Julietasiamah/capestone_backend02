package it.epicode.capestone_backend02.pasto;

import it.epicode.capestone_backend02.categoria_pasto.CategoriaPasto;
import it.epicode.capestone_backend02.preferenza_alimentare.PreferenzaAlimentare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PastoRepository extends JpaRepository<PastoEntity, Long> {
//    List<PastoEntity> findByCategoria(CategoriaPasto categoria);
//    List<PastoEntity> findByPreferenza(PreferenzaAlimentare preferenza);
    //trova per nome ignorando maiuscole e minuscole
    Optional<PastoEntity> findByNomeIgnoreCase(String nome);


}
