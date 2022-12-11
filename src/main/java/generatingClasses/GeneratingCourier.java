package generatingClasses;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.CreatingCourier;

public class GeneratingCourier {
    public static CreatingCourier getNewCourier() {
        return new CreatingCourier(RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
    }

    public static CreatingCourier getNewCourierWithFirstNameNull() {
        return new CreatingCourier(RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10),
                null);
    }

    public static CreatingCourier getNewCourierWithLoginNull() {
        return new CreatingCourier(null,
                RandomStringUtils.randomAlphabetic(10),
                RandomStringUtils.randomAlphabetic(10));
    }

    public static CreatingCourier getNewCourierWithPasswordNull() {
        return new CreatingCourier(RandomStringUtils.randomAlphabetic(10),
                null,
                RandomStringUtils.randomAlphabetic(10));
    }
}
