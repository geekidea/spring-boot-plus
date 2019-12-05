package io.geekidea.springbootplus.openfeign;

import io.geekidea.springbootplus.foobar.entity.Film;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "micro-skeleton1", path = "/film")
public interface FilmStore {

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    void addFile(@RequestBody Film film);
}
