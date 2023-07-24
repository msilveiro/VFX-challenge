package bookingChallenge;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import io.restassured.RestAssured;

import static junit.framework.Assert.assertEquals;

public class BookingTest {


    public Response createBooking(String firstname) {
        //create body
        JSONObject body = new JSONObject();
        body.put("firstname", firstname);
        body.put("lastname", "");
        body.put("totalprice", 100);
        body.put("depositpaid", true);

        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");
        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", "extra needs");


        final Response response =
                RestAssured.given()
                        .contentType(ContentType.JSON)
                        .body(body.toString())
                        .when()
                        .post("https://restful-booker.herokuapp.com/booking")
                        .then()
                        .statusCode(200)
                        .extract().response();

        //response.prettyPrint();
        return response;
    }

    @Test
    public void validateCustomerBooking() {
        final Response booking = createBooking("");

        assertEquals("Unexpected status code", 200, booking.getStatusCode());
    }

    @Test
    public void validateCostumerFirstName() {

        final String bookingId = createBooking("John").jsonPath().getString("bookingid");

        final Response response =
                RestAssured.given()
                        .pathParam("id", bookingId)
                        .when()
                        .get("https://restful-booker.herokuapp.com/booking/{id}")
                        .then()
                        .statusCode(200)
                        .extract().response();

        //response.prettyPrint();

        assertEquals("Unexpected firstname", "John", response.jsonPath().getString("firstname"));
    }
}