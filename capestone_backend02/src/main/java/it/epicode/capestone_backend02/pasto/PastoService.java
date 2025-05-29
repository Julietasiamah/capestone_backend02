package it.epicode.capestone_backend02.pasto;

import it.epicode.capestone_backend02.categoria_pasto.CategoriaPasto;
import it.epicode.capestone_backend02.preferenza_alimentare.PreferenzaAlimentare;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PastoService {

    @Autowired
    private final PastoRepository pastoRepository;

    // Ottieni tutti i pasti
    public List<PastoDTO> getAllPasti() {
        return pastoRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

//    // Ottieni tutti i pasti per categoria
//    public List<PastoDTO> getPastiByCategoria(CategoriaPasto categoria) {
//        return pastoRepository.findByCategoria(categoria).stream().map(this::toDTO).collect(Collectors.toList());
//    }

    // Ottieni tutti i pasti per preferenza
//    public List<PastoDTO> getPastiByPreferenza(PreferenzaAlimentare preferenza) {
//        return pastoRepository.findByPreferenza(preferenza).stream().map(this::toDTO).collect(Collectors.toList());
//    }

    // Ottieni un pasto per nome
    public PastoDTO getPastoByNome(String nome) {
        return toDTO(pastoRepository.findByNomeIgnoreCase(nome).orElseThrow(() -> new EntityNotFoundException("Pasto non trovato")));
    }

    // Crea nuovo pasto
    public PastoDTO createPasto(PastoDTO pastoDTO) {
        PastoEntity pasto = toEntity(pastoDTO);
        pastoRepository.save(pasto);
        return toDTO(pasto);
    }

    // Modifica pasto
    public PastoDTO updatePasto(Long id, PastoDTO pastoDTO) {
        PastoEntity pasto = pastoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pasto non trovato"));
        pasto.setNome(pastoDTO.getNome());
        pasto.setImgUrl(pastoDTO.getImgUrl());
        pasto.setRicetta(pastoDTO.getRicetta());
        pastoRepository.save(pasto);
        return toDTO(pasto);
    }
    public void deletePasto(Long id) {
        pastoRepository.deleteById(id);
    }

    // Mapping Entity → DTO
    private PastoDTO toDTO(PastoEntity entity) {
        PastoDTO dto = new PastoDTO();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setImgUrl(entity.getImgUrl());
        dto.setRicetta(entity.getRicetta());
        return dto;
    }

    // Mapping DTO → Entity
    private PastoEntity toEntity(PastoDTO dto) {
        PastoEntity entity = new PastoEntity();
        entity.setNome(dto.getNome());
        entity.setImgUrl(dto.getImgUrl());
        entity.setRicetta(dto.getRicetta());
        return entity;
    }
}

