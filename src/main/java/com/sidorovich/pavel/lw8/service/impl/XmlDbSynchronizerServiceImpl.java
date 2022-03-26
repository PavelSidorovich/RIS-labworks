package com.sidorovich.pavel.lw8.service.impl;

import com.sidorovich.pavel.lw8.exception.EntityNotFoundException;
import com.sidorovich.pavel.lw8.model.User;
import com.sidorovich.pavel.lw8.service.UserService;
import com.sidorovich.pavel.lw8.service.XmlDbSynchronizerService;
import com.sidorovich.pavel.lw8.service.XmlParserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class XmlDbSynchronizerServiceImpl implements XmlDbSynchronizerService {

    private final UserService userService;
    private final XmlParserService<User> xmlParserService;

    @Override
    public void synchronize(String filename) {
        final List<User> usersFromXml = xmlParserService.findAll(filename);
        final List<User> usersFromDb = userService.findAll();
        final List<Long> xmlIds = getIds(usersFromXml);
        final List<Long> dbIds = getIds(usersFromDb);

        dbIds.removeAll(xmlIds);
        dbIds.forEach(userService::delete);

        for (User user : usersFromXml) {
            try {
                userService.findById(user.getId());
                userService.update(user);
            } catch (Exception ex) {
                userService.save(user);
            }
        }
    }

    private List<Long> getIds(List<User> usersFromXml) {
        return usersFromXml.stream()
                           .map(User::getId)
                           .collect(Collectors.toList());
    }

}
