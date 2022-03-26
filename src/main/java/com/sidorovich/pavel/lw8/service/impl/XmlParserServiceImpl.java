package com.sidorovich.pavel.lw8.service.impl;

import com.sidorovich.pavel.lw8.model.User;
import com.sidorovich.pavel.lw8.model.Users;
import com.sidorovich.pavel.lw8.service.XmlParserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class XmlParserServiceImpl implements XmlParserService<User> {

    private static final String SCHEMA_PATH =
            "D:\\BSUIR\\6 semester\\RIS\\Labwork8\\src\\main\\resources\\schema.xsd";

    private final Schema schema;

    public XmlParserServiceImpl() throws SAXException {
        final SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        this.schema = sf.newSchema(new File(SCHEMA_PATH));
    }

    @Override
    public List<User> findAll(String filename) {
        try {
            final JAXBContext jc = JAXBContext.newInstance(Users.class);
            final Unmarshaller unmarshaller = jc.createUnmarshaller();
            unmarshaller.setSchema(schema);
            final File input = new File(filename);

            final Users users = (Users) unmarshaller.unmarshal(input);

            return users.getUsers();
        } catch (JAXBException ex) {
            log.error("XML unmarshalling error occurred", ex);
        }
        return Collections.emptyList();
    }

    @Override
    public void saveAll(List<User> users, String filename) {
        try {
            final JAXBContext jc = JAXBContext.newInstance(Users.class);
            final Marshaller marshaller = jc.createMarshaller();
            final File output = new File(filename);

            marshaller.setSchema(schema);
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new Users(users), output);
        } catch (JAXBException ex) {
            log.error("XML marshalling error occurred", ex);
        }
    }

}
