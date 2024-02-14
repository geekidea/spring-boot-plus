package io.geekidea.boot.framework.mybatis;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.github.yitter.idgen.YitIdHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author geekidea
 * @date 2023/11/23
 **/
@Slf4j
@Component
public class YitIdGenerator implements IdentifierGenerator {

    @Override
    public Number nextId(Object entity) {
        return YitIdHelper.nextId();
    }

}
