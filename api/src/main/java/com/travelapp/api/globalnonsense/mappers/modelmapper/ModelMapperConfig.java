package com.travelapp.api.globalnonsense.mappers.modelmapper;

import com.travelapp.api.itineraries.DTO.Other.ItinerariesOtherReadDTO;
import com.travelapp.api.users.DTO.UsersReadSelfDTO;
import com.travelapp.api.users.entity.Users;

import org.modelmapper.Conditions;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class ModelMapperConfig {
    @Bean("defaultModelMapper")
    public ModelMapper defaultModelMapper() {
        return new ModelMapper();
    }

    @Bean("skipNullModelMapper")
    public ModelMapper skipNullModelMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setPropertyCondition(Conditions.isNotNull());
        return mapper;
    }

    @Bean("userReadSelfMapper")
    public ModelMapper userReadSelfMapper() {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE)
                .setMatchingStrategy(MatchingStrategies.STRICT);

        // 1) Never map the two filtered lists in the first pass
        mapper.typeMap(Users.class, UsersReadSelfDTO.class)
                .addMappings(m -> {
                    m.skip(UsersReadSelfDTO::setItineraries);
                    m.skip(UsersReadSelfDTO::setMyTrips);
                })
                // 2) After mapping the simple fields, populate exactly those two lists:
                .setPostConverter(ctx -> {
                    Users src = ctx.getSource();
                    UsersReadSelfDTO dst = ctx.getDestination();
                    dst.setItineraries(src.getItineraries().stream()
                            .map(it -> mapper.map(it, ItinerariesOtherReadDTO.class))
                            .collect(Collectors.toList()));
                    dst.setMyTrips(src.getMyTrips().stream()
                            .map(it -> mapper.map(it, ItinerariesOtherReadDTO.class))
                            .collect(Collectors.toList()));
                    return dst;
                });

        return mapper;
    }
}
