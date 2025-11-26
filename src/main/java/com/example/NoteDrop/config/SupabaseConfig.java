package com.example.NoteDrop.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SupabaseConfig {
    @Value("${supabase.url}")
    private String supabaseUrl;

    @Value("${supabase.apiKey}")
    private String apiKey;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public String getSupabaseUrl() {
        return supabaseUrl;
    }

    public String getApiKey() {
        return apiKey;
    }
}
