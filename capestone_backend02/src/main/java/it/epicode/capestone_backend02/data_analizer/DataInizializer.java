package it.epicode.capestone_backend02.data_analizer;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.epicode.capestone_backend02.pasto.PastoEntity;
import it.epicode.capestone_backend02.pasto.PastoRepository;
import it.epicode.capestone_backend02.piano_settimanale.PianoSettimanaleDTO;
import it.epicode.capestone_backend02.piano_settimanale.PianoSettimanaleEntity;
import it.epicode.capestone_backend02.piano_settimanale.PianoSettimanaleRepository;
import it.epicode.capestone_backend02.ricetta.Ricetta;
import it.epicode.capestone_backend02.ricetta.RicettaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

@Component
public class DataInizializer implements CommandLineRunner {

    @Autowired
    private PianoSettimanaleRepository pianoSettimanaleRepository;

    @Autowired
    private PastoRepository pastoRepository;

    @Autowired
    private RicettaRepository ricettaRepository;

    @Override
    public void run (String... args) throws Exception {
        if (pianoSettimanaleRepository.count() == 0) {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = getClass().getResourceAsStream("/pianoSettimanale.json");

            if (is == null) {
                throw new RuntimeException("File not found");
            }
            List <PianoSettimanaleDTO> pianiSettimanali = Arrays.asList(mapper.readValue(is, PianoSettimanaleDTO[].class));

            for ( PianoSettimanaleDTO dto : pianiSettimanali ) {

                PastoEntity pasto = new PastoEntity();
                pasto.setNome(dto.getPasto().getNome());
                pasto.setImgUrl(dto.getPasto().getImgUrl());
                pasto.setRicetta(dto.getPasto().getRicetta());

                Ricetta ricetta = new Ricetta();
                ricetta.setNome(dto.getPasto().getRicetta().getNome());
                ricetta.setIngredienti(dto.getPasto().getRicetta().getIngredienti());
                ricetta.setProcedimento(dto.getPasto().getRicetta().getProcedimento());

                pasto.setRicetta(ricetta);
                ricettaRepository.save(ricetta);
                pastoRepository.save(pasto);

                PianoSettimanaleEntity piano = new PianoSettimanaleEntity();
                piano.setGiorno(dto.getGiorno());
                piano.setCategoriaPasto(dto.getCategoriaPasto());
                piano.setPasto(pasto);
                piano.setPreferenza(dto.getPreferenza());
                pianoSettimanaleRepository.save(piano);
            }
        }
    }


}
