package com.sidorovich.pavel.lw8.service.impl;

import com.sidorovich.pavel.lw8.exception.EntityNotFoundException;
import com.sidorovich.pavel.lw8.model.City;
import com.sidorovich.pavel.lw8.model.User;
import com.sidorovich.pavel.lw8.service.UserService;
import com.sidorovich.pavel.lw8.service.XmlDbSynchronizerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@ActiveProfiles({ "dev" })
@SpringBootTest
class IntegrationTest {

    private static final String INPUT_XML_PATH =
            "D:\\BSUIR\\6 semester\\RIS\\Labwork8\\src\\main\\resources\\input.xml";
    private static final String INPUT_JSON_PATH =
            "D:\\BSUIR\\6 semester\\RIS\\Labwork8\\src\\main\\resources\\input.json";
    private static final String OUTPUT_XML_PATH =
            "D:\\BSUIR\\6 semester\\RIS\\Labwork8\\src\\main\\resources\\output.xml";
    private static final String OUTPUT_JSON_PATH =
            "D:\\BSUIR\\6 semester\\RIS\\Labwork8\\src\\main\\resources\\output.json";
    private static final String UPDATE_XML_PATH =
            "D:\\BSUIR\\6 semester\\RIS\\Labwork8\\src\\main\\resources\\update.xml";

    private final UserService userService;
    private final XmlParserServiceImpl xmlParserService;
    private final JsonParserServiceImpl jsonParserService;
    private final XmlDbSynchronizerService synchronizerService;

    @Autowired
    public IntegrationTest(UserService userService,
                           XmlParserServiceImpl xmlParserService,
                           JsonParserServiceImpl jsonParserService,
                           XmlDbSynchronizerService synchronizerService) {
        this.userService = userService;
        this.xmlParserService = xmlParserService;
        this.jsonParserService = jsonParserService;
        this.synchronizerService = synchronizerService;
    }

    @Test
    void synchronize() {
        final List<User> users = xmlParserService.findAll(INPUT_XML_PATH);
        users.forEach(userService::save);
        final List<User> beforeUpdate = userService.findAll();
        synchronizerService.synchronize(UPDATE_XML_PATH);
        final List<User> afterUpdate = userService.findAll();
        final User firstUser = userService.findById(1L);

        assertEquals(5, beforeUpdate.size());
        assertEquals(5, afterUpdate.size());

        assertEquals("AA11AA", firstUser.getIdentificationNumber());
        assertEquals("Borisov", firstUser.getCity().getCityName());
        assertEquals("62-59-14", firstUser.getHomeTelephone());
        assertEquals("GG45TT", userService.findAll().get(afterUpdate.size() - 1).getIdentificationNumber());
        assertThrows(EntityNotFoundException.class, () -> userService.findById(3));
    }

    @Test
    void convertXmlToDatabaseEntities() {
        List<User> users = xmlParserService.findAll(INPUT_XML_PATH);

        users.forEach(userService::save);
        int actual = userService.findAll().size();

        assertEquals(users.size(), actual);
    }

    @Test
    void convertJsonToDatabaseEntities() {
        List<User> users = jsonParserService.findAll(INPUT_JSON_PATH);

        users.forEach(userService::save);
        int actual = userService.findAll().size();

        assertEquals(users.size(), actual);
    }

    public static Stream<Arguments> usersProvider() {
        return Stream.of(Arguments.of(List.of(
                User.builder()
                    .firstName("Pavel")
                    .isMale(true)
                    .city(new City("Minsk"))
                    .homeTelephone("93-15-62")
                    .identificationNumber("FG14GW")
                    .build(),
                User.builder()
                    .firstName("Ivan")
                    .isMale(true)
                    .city(new City("Borisov"))
                    .homeTelephone("92-45-41")
                    .identificationNumber("AG15GW")
                    .build(),
                User.builder()
                    .firstName("Stanislau")
                    .isMale(true)
                    .city(new City("Vitebsk"))
                    .homeTelephone("76-27-45")
                    .identificationNumber("AF16GW")
                    .build(),
                User.builder()
                    .firstName("Svetlana")
                    .isMale(false)
                    .city(new City("Brest"))
                    .homeTelephone("90-41-11")
                    .build(),
                User.builder()
                    .firstName("Ekaterina")
                    .isMale(false)
                    .city(new City("Malorita"))
                    .identificationNumber("AG55TW")
                    .build()
        )));
    }

    @ParameterizedTest
    @MethodSource("usersProvider")
    void convertDatabaseToXmlEntities(List<User> users) {
        users.forEach(userService::save);
        List<User> createdUsers = userService.findAll();
        xmlParserService.saveAll(createdUsers, OUTPUT_XML_PATH);

        assertEquals(users.size(), createdUsers.size());
    }

    @ParameterizedTest
    @MethodSource("usersProvider")
    void convertDatabaseToJsonEntities(List<User> users) {
        users.forEach(userService::save);
        List<User> createdUsers = userService.findAll();
        jsonParserService.saveAll(createdUsers, OUTPUT_JSON_PATH);

        assertEquals(users.size(), createdUsers.size());
    }

}