package it.epicode.capestone_backend02.ricetta;

import it.epicode.capestone_backend02.auth.AppUser;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RicettaRepository extends JpaRepository<Ricetta, Long> {
    Optional<Ricetta> findByNome(String nome);
    List<Ricetta> findByUtente(AppUser utente);
}
