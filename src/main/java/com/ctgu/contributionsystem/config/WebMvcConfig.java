package com.ctgu.contributionsystem.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * WebMvcConfig
 *
 * @author chase
 * @date 2019/10/15 0015
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置fastjson解析器
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullStringAsEmpty
        );
        fastConverter.setFastJsonConfig(fastJsonConfig);
        MediaType jsonUtf8 = MediaType.APPLICATION_JSON_UTF8;
        List<MediaType> medias = new ArrayList<>();
        medias.add(jsonUtf8);
        fastConverter.setSupportedMediaTypes(medias);
        //SpringBoot 2.0.1版本中加载WebMvcConfigurer的顺序发生了变动，
        // 故需使用converters.add(0, converter);
        // 指定FastJsonHttpMessageConverter在converters内的顺序，
        // 否则在SpringBoot 2.0.1及之后的版本中将优先使用Jackson处理
        converters.add(0, fastConverter);
    }
}
