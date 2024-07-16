package com.alura.literatura.service;

import com.alura.literatura.dto.EpisodioDto;
import com.alura.literatura.dto.LibroDto;
import com.alura.literatura.model.Categoria;
import com.alura.literatura.model.Libro;
import com.alura.literatura.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LibroService {
    @Autowired
    private LibroRepository repository;

    public List<LibroDto> obtenerTodasLasSeries() {
        return convierteDatos(repository.findAll());
    }

    public List<LibroDto> obtenerTop5() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }
    public List<LibroDto> obtenerLanzamientosMasRecientes(){
        return convierteDatos(repository.lanzamientosMasRecientes());
    }
    public List<LibroDto> convierteDatos(List<Libro> serie){
        return serie.stream()
                .map(s -> new LibroDto(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(),
                        s.getGenero(), s.getActores(), s.getSinopsis()))
                .collect(Collectors.toList());
    }

    public LibroDto obtenerPorId(Long id) {
        Optional<Libro> serie = repository.findById(id);
        if (serie.isPresent()){
            Libro s = serie.get();
            return new LibroDto(s.getId(), s.getTitulo(), s.getTotalTemporadas(), s.getEvaluacion(), s.getPoster(),
                    s.getGenero(), s.getActores(), s.getSinopsis());
        }
        return null;
    }

    public List<EpisodioDto> obtenerTodasLasTemporadas(Long id) {
        Optional<Libro> serie = repository.findById(id);
        if (serie.isPresent()){
            Libro s = serie.get();
            return s.getEpisodios().stream().map(e -> new EpisodioDto(e.getTemporada(),e.getTitulo(),
                    e.getNumeroEpisodio())).collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDto> obtenerTemporadasPorNumero(Long id, Long numeroTemporada) {
        return repository.obtenerTemporadasPorNumero(id,numeroTemporada).stream()
                .map(e -> new EpisodioDto(e.getTemporada(),e.getTitulo(),
                        e.getNumeroEpisodio())).collect(Collectors.toList());
    }

    public List<LibroDto> obtenerSeriesPorCategoria(String nombreGenero) {
        Categoria categoria = Categoria.fromEspanol(nombreGenero);
        return convierteDatos(repository.findByGenero(categoria));
    }

    public List<EpisodioDto> obtenerTopEpisodios(Long id) {
        var serie = repository.findById(id).get();
        return repository.topEpisodiosPorSerie(serie)
                .stream()
                .map(e -> new EpisodioDto(e.getTemporada(),e.getTitulo(),
                        e.getNumeroEpisodio()))
                .collect(Collectors.toList());
    }
}
