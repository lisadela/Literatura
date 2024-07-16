package com.alura.literatura.dto;

import com.alura.literatura.model.Categoria;

public record LibroDto(Long id,
                       String titulo,
                       Integer totalTemporadas,
                       Double evaluacion,
                       String poster,
                       Categoria genero,
                       String actores,
                       String sinopsis){
}
