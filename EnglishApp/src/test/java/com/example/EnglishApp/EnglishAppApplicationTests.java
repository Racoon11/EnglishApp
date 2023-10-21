package com.example.EnglishApp;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;


import net.minidev.json.JSONArray;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EnglishAppApplicationTests {
    @Autowired
    TestRestTemplate restTemplate;

    @Test
    void shouldReturnACashCardWhenDataIsSaved() {
        ResponseEntity<String> response = restTemplate
                    .withBasicAuth("sarah1", "abc123")
                    .getForEntity("/LK/99", String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

		DocumentContext documentContext = JsonPath.parse(response.getBody());
		Number id = documentContext.read("$.id");
		assertThat(id).isNotNull();
    }
	@Test
	void shouldNotReturnACashCardWithAnUnknownId() {
		ResponseEntity<String> response = restTemplate
                    .withBasicAuth("sarah1", "abc123") 
                    .getForEntity("/LK/1000", String.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
		assertThat(response.getBody()).isBlank();
	}

    @DirtiesContext
	@Test
    void shouldCreateANewLK(){
        UserLK newCashCard = new UserLK(null, 250.00, null);
        ResponseEntity<Void> createResponse = restTemplate
                .withBasicAuth("sarah1", "abc123") 
                .postForEntity(
            "/LK", newCashCard, Void.class);
        
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        URI locationOfNewCashCard = createResponse.getHeaders().getLocation();
        ResponseEntity<String> getResponse = restTemplate
                .withBasicAuth("sarah1", "abc123") 
                .getForEntity(
            locationOfNewCashCard, String.class
        );
        
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        

    }
    @Test
    void shouldReturnAllCashCardsWhenListIsRequested() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("sarah1", "abc123") 
                .getForEntity("/LK", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    void shouldReturnAllCashCardsWhenListIsRequested1() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("sarah1", "abc123") 
                .getForEntity("/LK", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
   
        DocumentContext documentContext = JsonPath.parse(response.getBody());
        int cashCardCount = documentContext.read("$.length()");
        assertThat(cashCardCount).isEqualTo(3);
   
        JSONArray ids = documentContext.read("$..id");
        assertThat(ids).containsExactlyInAnyOrder(99, 100, 101);
   
        JSONArray amounts = documentContext.read("$..amount");
        assertThat(amounts).containsExactlyInAnyOrder(123.45, 1.0, 150.00);
    }
    @Test
    void shouldReturnAPageOfCashCards() {
        ResponseEntity<String> response = restTemplate
                .withBasicAuth("sarah1", "abc123") 
                .getForEntity("/LK?page=0&size=1", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        DocumentContext documentContext = JsonPath.parse(response.getBody());
        JSONArray page = documentContext.read("$[*]");
        assertThat(page.size()).isEqualTo(1);
    }
    @Test
    void shouldNotReturnACashCardWhenUsingBadCredentials() {
        ResponseEntity<String> response = restTemplate
        .withBasicAuth("BAD-USER", "abc123")
        .getForEntity("/LK/99", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);

        response = restTemplate
        .withBasicAuth("sarah1", "BAD-PASSWORD")
        .getForEntity("/LK/99", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
    @Test
    void shouldRejectUsersWhoAreNotCardOwners() {
        ResponseEntity<String> response = restTemplate
        .withBasicAuth("hank-owns-no-cards", "qrs456")
        .getForEntity("/LK/99", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FORBIDDEN);
    }
    @Test
    void shouldNotAllowAccessToCashCardsTheyDoNotOwn() {
        ResponseEntity<String> response = restTemplate
        .withBasicAuth("sarah1", "abc123")
        .getForEntity("/LK/102", String.class); // kumar2's data
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    @DirtiesContext
    void shouldUpdateLK(){
        UserLK cashCardUpdate = new UserLK(null, 19.99, null);
        HttpEntity<UserLK> request = new HttpEntity<>(cashCardUpdate);
        ResponseEntity<Void> response = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .exchange("/LK/99", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);


        ResponseEntity<String> response2 = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .getForEntity("/LK/99", String.class);
        
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
        DocumentContext documentContext = JsonPath.parse(response2.getBody());
        
        Double amount = documentContext.read("$.amount");

        assertThat(amount).isEqualTo(19.99);

    }
    @Test
    void updateCashCardThatDoesNotExist(){
        UserLK cashCardUpdate = new UserLK(null, 19.99, null);
        HttpEntity<UserLK> request = new HttpEntity<>(cashCardUpdate);
        ResponseEntity<Void> response = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .exchange("/LK/12333", HttpMethod.PUT, request, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }


    @Test
    @DirtiesContext
    void shouldDeleteAnExistingCashCard() {

        ResponseEntity<Void> response = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .exchange("/LK/99", HttpMethod.DELETE, null, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        
        ResponseEntity<String> getResponse = restTemplate
            .withBasicAuth("sarah1", "abc123")
            .getForEntity("/LK/99", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
}
        @Test
        void shouldNotDeleteACashCardThatDoesNotExist() {
                ResponseEntity<Void> deleteResponse = restTemplate
                        .withBasicAuth("sarah1", "abc123")
                        .exchange("/LK/99999", HttpMethod.DELETE, null, Void.class);
                assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
        @Test
    void shouldNotAllowDeletionOfCashCardsTheyDoNotOwn() {
        ResponseEntity<Void> deleteResponse = restTemplate
                .withBasicAuth("sarah1", "abc123")
                .exchange("/LK/102", HttpMethod.DELETE, null, Void.class);
        assertThat(deleteResponse.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        ResponseEntity<String> getResponse = restTemplate
                .withBasicAuth("kumar2", "xyz789")
                .getForEntity("/LK/102", String.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

    
}

}
