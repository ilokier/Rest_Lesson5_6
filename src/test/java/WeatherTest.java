import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import static java.lang.System.getProperty;
import static org.hamcrest.Matchers.is;

public class WeatherTest extends BaseTest {
    @Test
    public void shouldGiveProperCityWeather() {
        given(getRequestSpecyfiaction(getProperty("weatherUrl")))
        .when()
                .get()
        .then()
                .spec(getResponseSpecyfication(parseInt(getProperty("successCodeForGet"))))
                .body("main.temp", is(parseFloat(getProperty("temperature"))))
                .body("name", is(getProperty("cityName")))
                .body("sys.country", is(getProperty("countryCode")));
           }

}
