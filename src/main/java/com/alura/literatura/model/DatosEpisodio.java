package com.alura.literatura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosEpisodio(
                            @JsonAlias("Title") String titulo,
                            @JsonAlias("Episode")Integer numeroEpisodio,
                            @JsonAlias("imdbRating")String evaluacion,
                            @JsonAlias("Released")String fechaDeLanzamiento
) {
}
