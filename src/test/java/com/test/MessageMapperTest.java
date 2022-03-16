package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

@SpringBootTest
@EnableConfigurationProperties
@ContextConfiguration(classes = { Config.class})
public class MessageMapperTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private MessageMapper mapper;

    String json = "[1]";
    List<Integer> data = List.of(1);

    @Test
    void testToMessage() {
        Message message = mapper.toMessage(new MessageDTO(json));
        assert message.getData().equals(data);
    }

    @Test
    void testToDTO() {
        MessageDTO message = mapper.toDTO(new Message(data));
        assert message.getJson().equals(json);
    }
}
