package io.geekidea.springbootplus.foobar.controller;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.foobar.entity.Film;
import io.geekidea.springbootplus.foobar.service.FilemRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/film")
@Api("Film API")
public class FilmDetailsController {


    @Autowired
    private FilemRepository filemRepository;

    /**
     * 添加 Film
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加Film对象", notes = "添加Film", response = ApiResult.class)
    public ApiResult<Boolean> addFooBar(@Valid @RequestBody Film film) throws Exception {
        filemRepository.saveFilm(film);
        return ApiResult.ok();
    }
}
