package io.geekidea.springbootplus.foobar.service.impl;

import io.geekidea.springbootplus.common.dao.MongoDbDao;
import io.geekidea.springbootplus.foobar.entity.Film;
import org.springframework.stereotype.Repository;


@Repository
public class FilmRepository extends MongoDbDao<Film> {

//    @Autowired
//    private MongoTemplate mongoTemplate;
//
//    @Override
//    public void saveFilm(Film film) {
//        mongoTemplate.save(film);
//    }
//
//    @Override
//    public Film findFilmByFilmName(String filmName) {
//        Query query = new Query(Criteria.where("fileName").is(filmName));
//        Film film = mongoTemplate.findOne(query, Film.class);
//        return film;
//    }
//
//    @Override
//    public long updateFilm(Film film) {
//        Query query = new Query(Criteria.where("idn").is(film.getIdn()));
//        Update update = new Update().set("fileName", film.getFilmName()).set("filmSer", film.getFilmSer());
//        UpdateResult result = mongoTemplate.updateFirst(query, update, Film.class);
//        if (result != null) {
//            return result.getMatchedCount();
//        } else {
//            return 0;
//        }
//    }

    @Override
    protected Class<Film> getEntityClass() {
        return Film.class;
    }
}
