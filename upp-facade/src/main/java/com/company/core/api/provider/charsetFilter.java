package com.company.core.api.provider;

import org.jboss.resteasy.plugins.providers.ProviderHelper;
import org.jboss.resteasy.util.NoContent;
import org.jboss.resteasy.util.TypeConverter;
import org.springframework.stereotype.Component;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @Author: gaobaozong
 * @Description: TODO 描述类的作用
 * @Date: Created in 2018/1/8 - 17:45
 * @Version: V1.0-SNAPSHOT
 */
@Provider
@Component
public class charsetFilter implements MessageBodyReader, MessageBodyWriter
{
    public boolean isReadable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType)
    {
        // StringTextStar should pick up strings
        return !String.class.equals(type) && TypeConverter.isConvertable(type);
    }

    public Object readFrom(Class type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, InputStream entityStream) throws IOException, WebApplicationException
    {
        InputStream delegate = NoContent.noContentCheck(httpHeaders, entityStream);
        String value = ProviderHelper.readString(delegate, mediaType);
        return TypeConverter.getType(type, value);
    }

    public boolean isWriteable(Class type, Type genericType, Annotation[] annotations, MediaType mediaType)
    {
        // StringTextStar should pick up strings
        return !String.class.equals(type) && !type.isArray();
    }

    public long getSize(Object o, Class type, Type genericType, Annotation[] annotations, MediaType mediaType)
    {
        String charset = mediaType.getParameters().get("charset");
        if (charset != null)
            try
            {
                return o.toString().getBytes(charset).length;
            } catch (UnsupportedEncodingException e)
            {
                // Use default encoding.
            }
        return o.toString().getBytes(StandardCharsets.UTF_8).length;
    }

    public void writeTo(Object o, Class type, Type genericType, Annotation[] annotations, MediaType mediaType, MultivaluedMap httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException
    {
        String charset = mediaType.getParameters().get("charset");
        if (charset == null) entityStream.write(o.toString().getBytes(StandardCharsets.UTF_8));
        else entityStream.write(o.toString().getBytes(charset));
    }
}
