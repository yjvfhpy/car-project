package com.saturn.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * json注解使用的日期格式
 * 
 * @author tajo
 */
public class JsonDateSerializer
    extends JsonSerializer<Date>
{
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyy年MM月dd日" );
   
    @Override
    public void serialize( Date date, JsonGenerator gen, SerializerProvider provider )
        throws IOException, JsonProcessingException
    {
        String formattedDate = dateFormat.format( date );
        gen.writeString( formattedDate );
    }
}
