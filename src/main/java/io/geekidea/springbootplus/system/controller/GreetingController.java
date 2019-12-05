/*
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.system.controller;

import io.geekidea.springbootplus.common.api.ApiResult;
import io.geekidea.springbootplus.foobar.entity.Film;
import io.geekidea.springbootplus.openfeign.FilmStore;
import io.geekidea.springbootplus.openfeign.TestApi;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Greeting Somebody Controller
 *
 * @author geekidea
 * @date 2019-11-29
 **/
@Slf4j
@Api("greeting somebody")
@RestController
@RequestMapping("/greeting")
public class GreetingController {

    @Resource
    FilmStore filmStore;

    @Resource
    TestApi testApi;

    @RequestMapping(value = "/{name}", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "Greeting Somebody", notes = "Greeting Somebody", response = String.class)
    public void print(HttpServletResponse response, @PathVariable String name) throws IOException {
        log.debug("Greeting Somebody...");
        response.getWriter().print(testApi.testApi());
    }

    @RequestMapping(value = "/addFilm", method = {RequestMethod.GET, RequestMethod.POST})
    @ApiOperation(value = "Greeting addFilm", notes = "Greeting addFilm", response = String.class)
    public ApiResult<Boolean> fileCust(HttpServletResponse response, @RequestBody Film film) throws IOException {
        log.debug("Greeting addFilm...");
        filmStore.addFile(film);
        return ApiResult.ok();
    }
}
