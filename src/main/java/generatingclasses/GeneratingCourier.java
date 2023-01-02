package generatingclasses;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.Courier;

public class GeneratingCourier {
    public static Courier getNewCourier() {
        return new Courier(RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
    }

    public static Courier getNewCourierWithFirstNameNull() {
        return new Courier(RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                null);
    }

    public static Courier getNewCourierWithLoginNull() {
        return new Courier(null,
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
    }

    public static Courier getNewCourierWithPasswordNull() {
        return new Courier(RandomStringUtils.randomAlphabetic(10),
                null,
                RandomStringUtils.randomAlphabetic(10));
    }
}
