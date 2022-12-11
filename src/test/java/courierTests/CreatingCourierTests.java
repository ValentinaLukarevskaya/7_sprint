package courierTests;

import generatingClasses.GeneratingCourier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import pojo.CreatingCourier;
import steps.CourierSteps;

import static org.hamcrest.core.IsEqual.equalTo;

@DisplayName("Создание  курьера")
public class CreatingCourierTests {

    public static final String REGISTER_ERROR_400 = "Недостаточно данных для создания учетной записи";
    public static final String REGISTER_ERROR_409 = "Этот логин уже используется";

    @Test
    @DisplayName("Создание нового курьера с корректными данными")
    @Description("Ожидаемый код ответа: 201")
    public void creatingCourierWithRightValueParams() {
        CreatingCourier request = GeneratingCourier.getNewCourier();
        Response response = CourierSteps.createCourier(request);

        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание нового курьера с корректными данными, без поля 'firstName")
    @Description("Ожидаемый код ответа: 201")
    public void createCourierWithoutFirstName() {
        CreatingCourier request = GeneratingCourier.getNewCourierWithFirstNameNull();
        Response response = CourierSteps.createCourier(request);

        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));
    }

    @Test
    @DisplayName("Создание нового курьера с корректными данными, без поля 'login")
    @Description("Ожидаемый код ответа: 400")
    public void createCourierWithoutLogin() {
        CreatingCourier request = GeneratingCourier.getNewCourierWithLoginNull();
        Response response = CourierSteps.createCourier(request);

        response.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_400));
    }

    @Test
    @DisplayName("Создание нового курьера с корректными данными, без поля 'password")
    @Description("Ожидаемый код ответа: 400")
    public void creatingNewCourierWithoutPassword() {
        CreatingCourier request = GeneratingCourier.getNewCourierWithPasswordNull();
        Response response = CourierSteps.createCourier(request);

        response.then()
                .statusCode(400)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_400));
    }

    @Test
    @DisplayName("Создание двух идентичных курьеров")
    @Description("Ожидаемый код ответа: 409")
    public void createTwoEqualCourier() {
        CreatingCourier request = GeneratingCourier.getNewCourierWithFirstNameNull();
        Response response = CourierSteps.createCourier(request);

        response.then()
                .statusCode(201)
                .and()
                .assertThat().body("ok", equalTo(true));

        Response errorResponse = CourierSteps.createCourier(request);
        errorResponse.then()
                .statusCode(409)
                .and()
                .assertThat().body("message", equalTo(REGISTER_ERROR_409));
    }
}
