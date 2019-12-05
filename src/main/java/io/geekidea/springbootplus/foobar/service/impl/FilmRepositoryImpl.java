package io.geekidea.springbootplus.foobar.service.impl;

import com.mongodb.client.result.UpdateResult;
import io.geekidea.springbootplus.foobar.entity.Film;
import io.geekidea.springbootplus.foobar.service.FilemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


@Service
public class FilmRepositoryImpl implements FilemRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveFilm(Film film) {
        mongoTemplate.save(film);
    }

    @Override
    public Film findFilmByFilmName(String filmName) {
        Query query = new Query(Criteria.where("fileName").is(filmName));
        Film film = mongoTemplate.findOne(query, Film.class);
        return film;
    }

    @Override
    public long updateFilm(Film film) {
        Query query = new Query(Criteria.where("idn").is(film.getIdn()));
        Update update = new Update().set("fileName", film.getFilmName()).set("filmSer", film.getFilmSer());
        UpdateResult result = mongoTemplate.updateFirst(query, update, Film.class);
        if (result != null) {
            return result.getMatchedCount();
        } else {
            return 0;
        }
    }
}
