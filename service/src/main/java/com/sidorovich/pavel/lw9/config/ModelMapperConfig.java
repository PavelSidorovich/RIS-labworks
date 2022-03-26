package com.sidorovich.pavel.lw9.config;

import com.sidorovich.pavel.lw9.dto.UserDto;
import com.sidorovich.pavel.lw9.model.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration()
                   .setSkipNullEnabled(true)
                   .setFieldAccessLevel(AccessLevel.PRIVATE);

        return modelMapper;
    }

    @Bean
    public ModelMapper userUpdateMapper() {
        final ModelMapper modelMapper = modelMapper();

        modelMapper.addMappings(skipModifiedFieldsMap());

        return modelMapper;
    }

    private PropertyMap<UserDto, UserModel> skipModifiedFieldsMap() {
        return new PropertyMap<>() {
            protected void configure() {
                skip().setCity(null);
            }
        };
    }

}
