package com.vc.jj.scan;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 解决Jackson 差8小时的问题
 * @author L.cm
 */
@Component("jacksonObjectMapper")
public class JacksonObjectMapper<O> extends ObjectMapper {

    private static final long serialVersionUID = 4288193147502386170L;


    private static final Locale CHINA = Locale.CHINA;
    
    public JacksonObjectMapper() {
        this.setLocale(CHINA);
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd", CHINA));
        this.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        //objectMapper.setSerializationInclusion(Include.NON_EMPTY);
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object arg0, JsonGenerator arg1, SerializerProvider arg2) throws IOException, JsonProcessingException {
                arg1.writeString("");
            }
         });
    }

}
