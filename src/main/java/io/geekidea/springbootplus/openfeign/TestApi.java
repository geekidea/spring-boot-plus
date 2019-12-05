package io.geekidea.springbootplus.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "demo-service", url = "localhost:8888", path = "/hello")
public interface TestApi {
    @GetMapping("/world")
    String testApi();
}
