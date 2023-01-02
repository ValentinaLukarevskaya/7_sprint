package steps;

import config.ConfigForScooter;
import io.qameta.allure.Step;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.Courier;
import pojo.LoginCourier;

import static io.restassured.RestAssured.given;

public class CourierSteps {
    public static final RequestSpecification REQUEST_SPECIFICATION = new RequestSpecBuilder()
            .setBaseUri(ConfigForScooter.BASE_URL)
            .setBasePath("/courier")
            .setContentType(ContentType.JSON)
            .build();

    @Step("Создание курьера")
    public static Response createCourier(Courier body) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(body)
                .when()
                .post();
    }

    @Step("Логин курьера в системе")
    public static Response loginCourier(LoginCourier body) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .body(body)
                .when()
                .post("/login");
    }

    @Step("Удаление курьера")
    public static Response deleteCourier(int id) {
        return given()
                .spec(REQUEST_SPECIFICATION)
                .log().all()
                .body(id)
                .when()
                .log().all()
                .delete("/" + id);
    }
}
