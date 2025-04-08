package com.travelapp.api.mappers.modelmapper;

import org.modelmapper.Conditions;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean("defaultModelMapper")
    public ModelMapper defaultModelMapper() {
        return new ModelMapper();
    }

    @Bean("skipNullModelMapper")
    public ModelMapper skipNullModelMapper() {
        ModelMapper mapper = new ModelMapper();
        // Configure to skip null values for partial updates
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

}
