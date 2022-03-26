package com.sidorovich.pavel.lw9.hateoas;

import com.sidorovich.pavel.lw9.controller.CityController;
import com.sidorovich.pavel.lw9.dto.UserDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserAssembler
        implements RepresentationModelAssembler<UserDto, EntityModel<UserDto>> {

    private static final String TAGS_REL = "tags";
    private static final String TAG_REL = "tag";
    private static final int LIMIT = 10;
    private static final int OFFSET = 0;

    @Override
    public EntityModel<UserDto> toModel(UserDto user) {
        return EntityModel.of(
                user
//                WebMvcLinkBuilder.linkTo(methodOn(UserController.class).findById(user.getId())).withSelfRel()
        );
    }

    @Override
    public CollectionModel<EntityModel<UserDto>> toCollectionModel(Iterable<? extends UserDto> users) {
        List<EntityModel<UserDto>> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(EntityModel.of(
                user,
                WebMvcLinkBuilder.linkTo(methodOn(CityController.class).findById(user.getId())).withRel(TAG_REL))
        ));
        return CollectionModel.of(
                userDtos,
                linkTo(methodOn(CityController.class).findAll(LIMIT, OFFSET)).withRel(TAGS_REL)
        );
    }

}
