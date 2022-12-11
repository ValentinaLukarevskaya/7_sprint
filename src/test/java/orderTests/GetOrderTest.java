package orderTests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import steps.OrderSteps;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;

public class GetOrderTest {
    @Test
    @DisplayName("Получение списка заказов")
    @Description("Ожидаемый код ответа: 200")
    public void getListOfOrder() {
        Response listOfOrder = OrderSteps.getOrderList();
        listOfOrder.then()
                .statusCode(200)
                .and()
                .body("orders", hasSize(greaterThan(0)));
    }
}
