package io.geekidea.boot.framework.runner;

import com.github.yitter.contract.IdGeneratorOptions;
import com.github.yitter.idgen.YitIdHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author geekidea
 * @date 2023/11/23
 **/
@Slf4j
@Component
public class YitIdRunner implements ApplicationRunner {

    @Value("${workerId:0}")
    private short workerId;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        IdGeneratorOptions options = new IdGeneratorOptions(workerId);
        YitIdHelper.setIdGenerator(options);
        log.info("YitId配置初始化完成，workerId：" + workerId);
    }

}
