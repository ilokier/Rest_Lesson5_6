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
    @BeforeSuite
    static void beforeAll() {
        appProperties = AppProperties.getInstance();
    }
   public RequestSpecification getRequestSpecyfiaction(String basePath){
       RequestSpecification requestSpecification =
               given()
                       .log()
                       .all()
                       .baseUri(getProperty("baseUrl"))
                       .basePath(basePath)
                       .header("name", getProperty("name"))
                       .header("age", getProperty("age"))
                       .param("q", getProperty("city"))
                       .param("appid", getProperty("token"));
       return requestSpecification;
   }
   public ResponseSpecification getResponseSpecyfication(int statuscode){
       ResponseSpecification responseSpecification = RestAssured.expect();
       responseSpecification
               .log()
               .all()
               .time(Matchers.lessThan(parseLong(getProperty("responseTime"))))
               .contentType(ContentType.JSON)
               .statusCode(statuscode);
       return responseSpecification;
   }

}
