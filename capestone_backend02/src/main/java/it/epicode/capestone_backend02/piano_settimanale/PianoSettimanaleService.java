package it.epicode.capestone_backend02.piano_settimanale;

import it.epicode.capestone_backend02.auth.AppUser;
import it.epicode.capestone_backend02.auth.AppUserRepository;
import it.epicode.capestone_backend02.pasto.PastoEntity;
import it.epicode.capestone_backend02.pasto.PastoRepository;
import it.epicode.capestone_backend02.preferenza_alimentare.PreferenzaAlimentare;
import it.epicode.capestone_backend02.ricetta.Ricetta;
import it.epicode.capestone_backend02.ricetta.RicettaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;



@Service
@RequiredArgsConstructor
public class PianoSettimanaleService {

    private final PianoSettimanaleRepository repository;
    private final PastoRepository pastoRepository;
    private final RicettaRepository ricettaRepository;

    private final AppUserRepository appUserRepository;


    public List<PianoSettimanaleDTO> getAllPiani() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PianoSettimanaleDTO creaPiano(PianoSettimanaleDTO dto) {
        PianoSettimanaleEntity entity = toEntity(dto);
        entity.setUtente(getUtenteAutenticato());
        repository.save(entity);
        return toDTO(repository.save(entity));
    }

    public List<PianoSettimanaleDTO> getPianiUtenteAutenticato() {
        AppUser utente = getUtenteAutenticato();
        return repository.findByUtente(utente)
                .stream()
                .map(piano -> new PianoSettimanaleDTO(
                        piano.getId(),
                        piano.getGiorno(),
                        piano.getCategoriaPasto(),
                        piano.getPasto(),
                        piano.getPreferenza()
                ))
                .collect(Collectors.toList());
    }
    //ottieni piano da preferenza
    public List<PianoSettimanaleDTO> getPianiByPreferenza(PreferenzaAlimentare preferenza) {
        return repository.findByPreferenza(preferenza).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PianoSettimanaleDTO modificaPiano(Long id, PianoSettimanaleDTO dto) {
        PianoSettimanaleEntity existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Piano non trovato"));

        existing.setGiorno(dto.getGiorno());
        existing.setCategoriaPasto(dto.getCategoriaPasto());
        existing.setPasto(pastoRepository.findByNomeIgnoreCase(dto.getPasto().getNome())
                .orElseThrow(() -> new EntityNotFoundException("Pasto non trovato")));

        return toDTO(repository.save(existing));
    }

    public void cancellaPiano(Long id) {
        repository.deleteById(id);
    }



    public void salvaPiano(List<PianoSettimanaleDTO> dtoList) {
        for (PianoSettimanaleDTO dto : dtoList) {

            Ricetta ricetta = dto.getPasto().getRicetta();
            Ricetta ricettaSalvata = ricettaRepository.save(ricetta);

            PastoEntity pasto = new PastoEntity();
            pasto.setNome(dto.getPasto().getNome());
            pasto.setImgUrl(dto.getPasto().getImgUrl());
//            pasto.setPreferenza(dto.getPasto().getPreferenza());
            pasto.setRicetta(ricettaSalvata);
            PastoEntity pastoSalvato = pastoRepository.save(pasto);

            PianoSettimanaleEntity entity = new PianoSettimanaleEntity();
            entity.setGiorno(dto.getGiorno());
            entity.setCategoriaPasto(dto.getCategoriaPasto());
            entity.setPasto(pastoSalvato);

            repository.save(entity);
        }
    }


    // Mapping Entity → DTO
    private PianoSettimanaleDTO toDTO(PianoSettimanaleEntity entity) {
        PianoSettimanaleDTO dto = new PianoSettimanaleDTO();
        dto.setId(entity.getId());
        dto.setGiorno(entity.getGiorno());
        dto.setCategoriaPasto(entity.getCategoriaPasto());
        dto.setPasto(entity.getPasto());
        return dto;
    }

    // Mapping DTO → Entity (usato solo in creaPiano e modificaPiano)
    private PianoSettimanaleEntity toEntity(PianoSettimanaleDTO dto) {
        PianoSettimanaleEntity entity = new PianoSettimanaleEntity();
        entity.setGiorno(dto.getGiorno());
        entity.setCategoriaPasto(dto.getCategoriaPasto());
        entity.setPasto(pastoRepository.findByNomeIgnoreCase(dto.getPasto().getNome())
                .orElseThrow(() -> new EntityNotFoundException("Pasto non trovato")));
        return entity;
    }

    private AppUser getUtenteAutenticato() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
    }
}
