package courierTests;

import generatingclasses.GeneratingCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Test;
import pojo.Courier;
import pojo.LoginCourier;
import steps.CourierSteps;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static steps.CourierSteps.REQUEST_SPECIFICATION;
import static steps.CourierSteps.deleteCourier;

@DisplayName("Логин  курьера")
public class LoginCourierTests {

    int id;

    @Test
    @DisplayName("Авторизация курьера")
    @Description("Ожидаемый код ответа: 200")
    public void loginCourierWithAllValidParams() {
        Courier request = GeneratingCourier.getNewCourier();
        Response createResponse = CourierSteps.createCourier(request);

        createResponse.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = CourierSteps.loginCourier((new LoginCourier(request.getLogin(), request.getPassword())));

        id = loginResponse.then()
                .statusCode(200)
                .and()
                .assertThat().body("id", notNullValue())
                .extract().path("id");
    }

    @Test
    @DisplayName("Авторизация несуществующего курьера")
    @Description("Ожидаемый код ответа: 404")
    public void loginNonExistentCourier() {
        Response loginResponse = CourierSteps.loginCourier(new LoginCourier(
                RandomStringUtils.randomAlphabetic(10), RandomStringUtils.randomAlphabetic(10)));

        loginResponse.then()
                .statusCode(404)
                .and()
                .assertThat().body("message", equalTo("Учетная запись не найдена"));

    }

    @Test
    @DisplayName("Авторизация курьера с неправильным логином")
    @Description("Ожидаемый код ответа: 400")
    public void loginCourierWithWrongLogin() {
        Courier request = GeneratingCourier.getNewCourier();
        Response createResponse = CourierSteps.createCourier(request);

        createResponse.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = CourierSteps.loginCourier((new LoginCourier(null, request.getPassword())));

        loginResponse.then()
                .statusCode(400)
                .and()
                .assertThat()
                .body("message", equalTo("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Авторизация курьера с неправильным паролем")
    @Description("Ожидаемый код ответа: 400")
    public void loginCourierWithWrongPassword() {
        Courier request = GeneratingCourier.getNewCourier();
        Response createResponse = CourierSteps.createCourier(request);

        createResponse.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response loginResponse = CourierSteps.loginCourier((new LoginCourier(request.getLogin(), null)));

        loginResponse.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo("Недостаточно данных для входа"));
    }

    @After
    public void tearDown() {
            if (id != 0) deleteCourier(id);
    }

}
