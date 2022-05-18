import Configuration.AppProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import static io.restassured.RestAssured.given;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.System.getProperty;

public class BaseTest {
    private static AppProperties appProperties;
    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    @BeforeSuite
    static void beforeAll() {
        appProperties = AppProperties.getInstance();
    }

    @BeforeMethod
    public void setup() {
        requestSpecification =
                given()
                        .log()
                        .all()
                        .baseUri(getProperty("baseUrl"))
                        .basePath(getProperty("weatherUrl"))
                        .header("name", getProperty("name"))
                        .header("age", getProperty("age"))
                        .param("q", getProperty("city"))
                        .param("appid", "b1b15e88fa797225412429c1c50c122a1");
        responseSpecification = RestAssured.expect();
        responseSpecification
                .log()
                .all()
                .time(Matchers.lessThan(parseLong(getProperty("responseTime"))))
                .contentType(ContentType.JSON)
                .statusCode(parseInt(getProperty("statusCode")))
        ;
    }
}
