package com.example.EnglishApp;

import java.io.IOException;
import java.sql.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
public class LKTest {
    @Autowired
    private JacksonTester<UserLK> json;
    
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void cashCardSerializationTest() throws IOException {
    

        UserLK cashCard = new UserLK("Name", 1000, 3);

        //assertThat(json.write(cashCard)).isStrictlyEqualToJson("expected.json");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.nick");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.nick")
                .isEqualTo("Name");
        assertThat(json.write(cashCard)).hasJsonPathNumberValue("@.learntWords");
        assertThat(json.write(cashCard)).extractingJsonPathNumberValue("@.learntWords")
             .isEqualTo(3);
    }

    @Test
    public void cashCardDeserializationTest() throws IOException {
        String expected = """
                {
                    "nick":"Name",
                    "date":1000,
                    "learntWords":3
                }
                """;
        assertThat(json.parse(expected))
                .isEqualTo(new UserLK("Name", 1000, 3));
        assertThat(json.parseObject(expected).nick()).isEqualTo("Name");
        assertThat(json.parseObject(expected).date()).isEqualTo(1000);
}
    @Test
    void shouldReturnACashCardWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate.getForEntity("/Name", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        Number id = documentContext.read("$.nick");
        assertThat(id).isEqualTo("Name");
    }
    @Test
    void shouldNotReturnACashCardWithAnUnknownId() {
        ResponseEntity<String> response = restTemplate.getForEntity("/some", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isBlank();
    }
}
