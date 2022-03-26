package com.sidorovich.pavel.lw9.controller;

import com.sidorovich.pavel.lw9.dto.CityDto;
import com.sidorovich.pavel.lw9.hateoas.CityAssembler;
import com.sidorovich.pavel.lw9.service.CityService;
import com.sidorovich.pavel.lw9.util.PageRequestFactoryService;
import lombok.AllArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/cities", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Order(value = 1)
public class CityController {

    private final CityService cityService;
    private final CityAssembler cityAssembler;
    private final PageRequestFactoryService pageRequestFactory;

    @GetMapping
    public CollectionModel<EntityModel<CityDto>> findAll(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        return cityAssembler.toCollectionModel(
                cityService.findAll(pageRequestFactory.pageable(page, size))
        );
    }

    @GetMapping("/{id}")
    public EntityModel<CityDto> findById(@PathVariable long id) {
        return cityAssembler.toModel(cityService.findById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public EntityModel<CityDto> create(@Valid @RequestBody CityDto city) {
        return cityAssembler.toModel(cityService.create(city));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        cityService.delete(id);
    }

}
