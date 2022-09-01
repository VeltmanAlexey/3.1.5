package com.veltman.Resttemplate.RestAPI;

import com.veltman.Resttemplate.Model.User;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Communication {

    private final RestTemplate restTemplate;
    private final HttpHeaders headers;
    private final String URL = "http://94.198.50.185:7081/api/users";
    private String result = "";

    @Autowired
    public Communication(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        headers.set("Cookie", getAllUser().stream().collect(Collectors.joining(";")));
    }


    public List<String> getAllUser() {
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.GET,
                entity, String.class);

        return responseEntity.getHeaders().get("Set-Cookie");
    }

    public void saveUser(User user) {
        HttpEntity<User> entity = new HttpEntity<User>(user, headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.POST, entity, String.class);

        result += responseEntity.getBody();

    }

    public void updateUser(User user) {
        HttpEntity<User> entity = new HttpEntity<User>(user,headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(URL,
                HttpMethod.PUT, entity, String.class);

        result += responseEntity.getBody();

    }

    public void deleteUser(long id) {
        HttpEntity<User> entity = new HttpEntity<User>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL + "/"+ id,
                HttpMethod.DELETE, entity, String.class);

        result += responseEntity.getBody();

        System.out.println(result.length());

    }


}

