package io.geekidea.springbootplus.foobar.service;

import io.geekidea.springbootplus.foobar.entity.Film;

public interface FilemRepository {
    void saveFilm(Film film);

    Film findFilmByFilmName(String filmName);

    long updateFilm(Film film);
}
