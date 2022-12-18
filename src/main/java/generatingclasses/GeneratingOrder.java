package generatingclasses;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.Order;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

public class GeneratingOrder {
    public static Order getNewOrder(List<String> color) {
        Order createOrder = new Order();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        createOrder.setFirstName(RandomStringUtils.randomAlphabetic(10));
        createOrder.setLastName(RandomStringUtils.randomAlphabetic(10));
        createOrder.setAddress(RandomStringUtils.randomAlphabetic(10));
        createOrder.setPhone(RandomStringUtils.randomAlphabetic(10));
        createOrder.setDeliveryDate(LocalDate.now().format(formatter));
        createOrder.setComment(RandomStringUtils.randomAlphabetic(10));
        createOrder.setRentTime(new Random().nextInt(19));
        createOrder.setColor(color);
        return createOrder;
    }
}
