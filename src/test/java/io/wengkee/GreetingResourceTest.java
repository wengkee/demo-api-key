package io.wengkee;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class GreetingResourceTest {

    private static RequestSpecification rs;

    @BeforeEach
    void AuthSetup(){
        RequestSpecBuilder rb = new RequestSpecBuilder();
        rb.addHeader("x-api-key", "test-key");
        rs = rb.build();
    }

    @Test
    void testHelloEndpoint() {
        given()
          .when().spec(rs).get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello from Weng Kee"));
    }

    @Test
    void testChineseEndpoint() {
        given()
                .when().spec(rs).get("/hello/chinese")
                .then()
                .statusCode(200)
                .body(is("你好，我是 Weng Kee!"));
    }

    @Test
    void testFilter() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(401);
    }

}