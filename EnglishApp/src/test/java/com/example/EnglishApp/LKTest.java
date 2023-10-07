package com.example.EnglishApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class LKTest {
    @Autowired
    private JacksonTester<UserLK> json;

    @Test
    public void cashCardSerializationTest() throws IOException {
        UserLK cashCard = new UserLK("Name", 10, 20);
        //assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(cashCard)).hasJsonPathStringValue("@.nick");
        assertThat(json.write(cashCard)).extractingJsonPathStringValue("@.nick")
                .isEqualTo("Name");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.date");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.date")
             .isEqualTo(10);
    }
}
