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
        UserLK cashCard = new UserLK(99L, 10.0, "sarah1");
        //assertThat(json.write(cashCard)).isStrictlyEqualToJson( "C:\\Users\\Cake\\EnglishApp\\EnglishApp\\src\\test\\java\\com\\example\\EnglishApp\\expected.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.id");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.id")
                .isEqualTo(99);
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.amount");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.amount")
             .isEqualTo(10.0);
    }
    @Test
    public void cashCardDeserializationTest() throws IOException {
        String expected = """
                {
                    "id":99,
                    "amount":10.0,
                    "owner":"sarah1"
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new UserLK(99L, 10.0, "sarah1"));
        assertThat(json.parseObject(expected).amount()).isEqualTo(10.0);
        assertThat(json.parseObject(expected).id()).isEqualTo(99);
    }
}
