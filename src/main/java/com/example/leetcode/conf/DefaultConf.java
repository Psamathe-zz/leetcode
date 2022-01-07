package com.example.leetcode.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultConf {

    @Value("${test.zui}")
    String zz;

    @Bean
    public void init(){
        System.out.println(zz);
    }
}
