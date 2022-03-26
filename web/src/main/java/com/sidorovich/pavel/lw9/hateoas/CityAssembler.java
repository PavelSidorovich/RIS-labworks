package com.sidorovich.pavel.lw9.hateoas;

import com.sidorovich.pavel.lw9.controller.CityController;
import com.sidorovich.pavel.lw9.dto.CityDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CityAssembler implements RepresentationModelAssembler<CityDto, EntityModel<CityDto>> {

    private static final String TAGS_REL = "cities";
    private static final int LIMIT = 10;
    private static final int OFFSET = 0;

    @Override
    public EntityModel<CityDto> toModel(CityDto city) {
        return EntityModel.of(
                city,
                WebMvcLinkBuilder.linkTo(methodOn(CityController.class).findById(city.getId())).withSelfRel(),
                linkTo(methodOn(CityController.class).findAll(LIMIT, OFFSET)).withRel(TAGS_REL)
        );
    }

    @Override
    public CollectionModel<EntityModel<CityDto>> toCollectionModel(Iterable<? extends CityDto> cities) {
        List<EntityModel<CityDto>> cityDtos = new ArrayList<>();
        cities.forEach(city -> cityDtos.add(EntityModel.of(
                city,
                linkTo(methodOn(CityController.class).findById(city.getId())).withSelfRel())
        ));
        return CollectionModel.of(
                cityDtos,
                linkTo(methodOn(CityController.class).findAll(LIMIT, OFFSET)).withSelfRel()
        );
    }

}
