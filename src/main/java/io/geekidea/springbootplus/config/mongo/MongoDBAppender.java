/**
 * Copyright 2019-2029 geekidea(https://github.com/geekidea)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.geekidea.springbootplus.config.mongo;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import com.mongodb.BasicDBObject;
import io.geekidea.springbootplus.util.SpringContextUtil;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 * @auth geekidea
 * @date 2019-05-14
 **/
public class MongoDBAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    private String source;

    @Override
    protected void append(ILoggingEvent eventObject) {
        MongoTemplate mongoTemplate = SpringContextUtil.getBean(MongoTemplate.class);

        BasicDBObject doc = new BasicDBObject();
        long timeStamp = eventObject.getTimeStamp();
        String level = eventObject.getLevel().toString();


        doc.append("source",source);
        doc.append("date",new Date(timeStamp));
        doc.append("level",level);
        doc.append("thread",eventObject.getThreadName());
        doc.append("logger",eventObject.getLoggerName());
        doc.append("message",eventObject.getFormattedMessage());

        if ("ERROR".equalsIgnoreCase(level)){
            doc.append("args",eventObject.getArgumentArray());
            doc.append("exClassName",eventObject.getThrowableProxy().getClassName());
            doc.append("exMessage",eventObject.getThrowableProxy().getMessage());
        }

        if (MapUtils.isNotEmpty(eventObject.getMDCPropertyMap())){
            doc.append("mdc", eventObject.getMDCPropertyMap());
        }

        mongoTemplate.insert(doc,"log");
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
