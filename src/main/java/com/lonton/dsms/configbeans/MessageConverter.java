package com.lonton.dsms.configbeans;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

@Configuration
public class MessageConverter {

	@Bean
	public HttpMessageConverter<?> fastJsonHttpMessageConverter() {
        // FastJsonHttpMessageConverter
        FastJsonHttpMessageConverter fastjsonMessageConverter = new FastJsonHttpMessageConverter();
        // supportedMediaTypes
        List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
        supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        supportedMediaTypes.add(MediaType.APPLICATION_JSON);
        fastjsonMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
        // fastJsonConfig
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        SerializeConfig serializeConfig = fastJsonConfig.getSerializeConfig();
        serializeConfig.put(java.util.Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
        fastJsonConfig.setSerializeConfig(serializeConfig);
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, 
        		SerializerFeature.WriteMapNullValue);
        //
        fastjsonMessageConverter.setFastJsonConfig(fastJsonConfig);
        return fastjsonMessageConverter;
    }
	
	
}
