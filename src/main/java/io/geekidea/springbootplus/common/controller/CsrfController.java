package io.geekidea.springbootplus.common.controller;

import io.geekidea.springbootplus.util.UUIDUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * CSRF
 *
 * @author geekidea
 * @date 2019/12/10
 **/
@RestController
public class CsrfController {

    @RequestMapping(value = "/csrf", method = {RequestMethod.GET, RequestMethod.POST})
    public String csrf() {
        return UUIDUtil.getUuid();
    }

}
