package it.epicode.capestone_backend02.piano_settimanale;

import it.epicode.capestone_backend02.auth.AppUser;

import it.epicode.capestone_backend02.preferenza_alimentare.PreferenzaAlimentare;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PianoSettimanaleRepository extends  JpaRepository<PianoSettimanaleEntity, Long> {
  List <PianoSettimanaleEntity> findByPreferenza(PreferenzaAlimentare preferenza);
  List<PianoSettimanaleEntity> findByUtente(AppUser utente);
  void deleteByUtente(AppUser utente);

}
