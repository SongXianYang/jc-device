package com.jc.local.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

@Configuration
public class ResponseConverterConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        //创建fastjson消息转换器
        FastJsonHttpMessageConverter fastJsonConverter = new FastJsonHttpMessageConverter();
        //创建配置类
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        //修改配置返回内容的过滤
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullListAsEmpty,
                SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullBooleanAsFalse);

        fastJsonConverter.setFastJsonConfig(fastJsonConfig);

        //添加StringHttpMessageConverter，解决中文乱码问题
        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8);
        List<MediaType> mediaTypes = Collections.singletonList(MediaType.APPLICATION_JSON_UTF8);
        stringConverter.setSupportedMediaTypes(mediaTypes);

        //讲fastJson添加到消息转换列表
        converters.add(fastJsonConverter);
        converters.add(stringConverter);
    }
}
