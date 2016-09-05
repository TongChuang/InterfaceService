package com.zcw.webservice.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zcw on 2016/8/25.
 */
public class JsonDateFormateFull extends JsonSerializer<Date> {

    /**
     * Jackson支持日期字符串格式
     * "yyyy-MM-dd'T'HH:mm:ss.SSSZ" "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
     * "EEE, dd MMM yyyy HH:mm:ss zzz" "yyyy-MM-dd"
     */
    @Override
    public void serialize(Date value, JsonGenerator jgen,
                          SerializerProvider provider) throws
            IOException, JsonProcessingException {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        String formattedDate = formatter.format(value);
        jgen.writeString(formattedDate);

    }

}
