package hello;

import org.springframework.web.client.RestTemplate;

public class Application {

    public static void main(String args[]) {
        RestTemplate restTemplate = new RestTemplate();
        Page page = restTemplate.getForObject("http://localhost:8080/greeting", Page.class);
        System.out.println("Id:    " + page.getId());
        System.out.println("Content:   " + page.getContent());
    }

}