package it.epicode.capestone_backend02.ricetta;

import it.epicode.capestone_backend02.auth.AppUser;
import it.epicode.capestone_backend02.auth.AppUserRepository;
import it.epicode.capestone_backend02.pasto.PastoEntity;
import it.epicode.capestone_backend02.pasto.PastoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;



@Service
@RequiredArgsConstructor
public class RicettaService {

    @Autowired
    private final RicettaRepository ricettaRepository;

    private final PastoRepository pastoRepository;

    private final AppUserRepository appUserRepository;





    public List<RicettaDTO> getAllRicette() {
        return ricettaRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public RicettaDTO getRicettaByNome(String nome) {
        Ricetta ricetta = ricettaRepository.findByNome(nome)
                .orElseThrow(() -> new EntityNotFoundException("Ricetta non trovata"));
        return toDTO(ricetta);
    }

    public RicettaDTO creRicetta(RicettaDTO dto) {
        Ricetta ricetta = toEntity(dto);
        ricetta.setUtente(getUtenteAutenticato());
        ricettaRepository.save(ricetta);
        return toDTO(ricettaRepository.save(ricetta));

    }

    public List<RicettaDTO> getRicetteUtenteAutenticato() {
        AppUser utente = getUtenteAutenticato();
        return ricettaRepository.findByUtente(utente)
                .stream()
                .map(ricetta -> new RicettaDTO(
                        ricetta.getNome(),
                        ricetta.getIngredienti(),
                        ricetta.getProcedimento()
                ))
                .collect(Collectors.toList());
    }

    public RicettaDTO getRicettaByPastoId(Long pastoId) {
        PastoEntity pasto = pastoRepository.findById(pastoId)
                .orElseThrow(() -> new EntityNotFoundException("Pasto non trovato"));

        Ricetta ricetta = pasto.getRicetta();
        if (ricetta == null) {
            throw new EntityNotFoundException("Ricetta non associata a questo pasto");
        }
        return toDTO(ricetta);
    }

    private RicettaDTO toDTO(Ricetta entity) {
        RicettaDTO dto = new RicettaDTO();
        dto.setNome(entity.getNome());
        dto.setIngredienti(entity.getIngredienti());
        dto.setProcedimento(entity.getProcedimento());
        return dto;
    }

    private Ricetta toEntity(RicettaDTO dto) {
        Ricetta entity = new Ricetta();
        entity.setNome(dto.getNome());
        entity.setIngredienti(dto.getIngredienti());
        entity.setProcedimento(dto.getProcedimento());
        return entity;
    }

    private AppUser getUtenteAutenticato() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();
        return appUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato"));
    }

    //

}
