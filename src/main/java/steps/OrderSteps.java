package steps;

import config.ConfigForScooter;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.CreateOrder;

import static io.restassured.RestAssured.given;

public class OrderSteps {
    public static final RequestSpecification REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri(ConfigForScooter.BASE_URL)
            .setBasePath("/orders")
            .setContentType(ContentType.JSON)
            .build();

    @Step("Создание заказа")
    public static Response createOrder(CreateOrder body) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(body)
                .when()
                .post();
    }

    @Step("Получение списка заказов")
    public static Response getOrderList() {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .when()
                .get();
    }
}
