package com.sidorovich.pavel.lw9.controller;

import com.sidorovich.pavel.lw9.dto.UserDto;
import com.sidorovich.pavel.lw9.hateoas.UserAssembler;
import com.sidorovich.pavel.lw9.service.UserService;
import com.sidorovich.pavel.lw9.util.PageRequestFactoryService;
import com.sidorovich.pavel.lw9.validator.CreateValidationGroup;
import com.sidorovich.pavel.lw9.validator.UpdateValidationGroup;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserAssembler userAssembler;
    private final PageRequestFactoryService paginationFactory;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CollectionModel<EntityModel<UserDto>> createGroup(
            @Validated({ CreateValidationGroup.class })
            @RequestBody List<UserDto> users) {
        return userAssembler.toCollectionModel(
                users.stream()
                     .map(userService::create)
                     .collect(Collectors.toList())
        );
    }

    @GetMapping
    public CollectionModel<EntityModel<UserDto>> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        return userAssembler.toCollectionModel(
                userService.findAll(paginationFactory.pageable(page, size))
        );
    }

    @GetMapping(value = "/{ids}")
    public CollectionModel<EntityModel<UserDto>> findByIds(@PathVariable List<Integer> ids) {
        return userAssembler.toCollectionModel(
                ids.stream()
                   .map(userService::findById)
                   .collect(Collectors.toList())
        );
    }

    @PatchMapping
    public CollectionModel<EntityModel<UserDto>> updateGroup(
            @Validated({ UpdateValidationGroup.class })
            @RequestBody List<UserDto> users) {
        return userAssembler.toCollectionModel(
                users.stream()
                     .map(userService::update)
                     .collect(Collectors.toList())
        );
    }

    @DeleteMapping(value = "/{ids}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable List<Long> ids) {
        ids.forEach(userService::delete);
    }

}
