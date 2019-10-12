package com.hellofresh.Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.equalTo;


public class TestCreateBooking {

    @Test
    @Category(TestCreateBooking.class)
    public void testCreateBookingWithValidDataSet() {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        JSONObject dates = new JSONObject();
        dates.put("checkin","2019-08-22");
        dates.put("checkout", "2019-08-23");
        json.put("bookingid",74);
        json.put("roomid",526);
        json.put("firstname","asss");
        json.put("lastname","xssc");
        json.put("depositpaid","true");
        json.put("bookingdates", new JSONObject(dates));

        request.body(json.toJSONString());
        Response response = request.post("https://automationintesting.online/booking/");

        Assert.assertEquals(response.getStatusCode(), 201);
        response.then().body("booking.firstname", equalTo("asss"));
        response.then().body("booking.lastname", equalTo("xssc"));
        response.then().body("booking.bookingdates.checkin", equalTo("2019-08-20"));
        response.then().body("booking.bookingdates.checkout", equalTo("2019-08-21"));
    }

    @Test
    @Category(TestCreateBooking.class)
    public void testCreateBookingWithAlreadyExistingDates() {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        JSONObject dates = new JSONObject();
        dates.put("checkin","2019-12-22");
        dates.put("checkout", "2019-12-24");
        json.put("bookingid",74);
        json.put("roomid",526);
        json.put("firstname","asss");
        json.put("lastname","xssc");
        json.put("depositpaid","true");
        json.put("bookingdates", new JSONObject(dates));

        request.body(json.toJSONString());
        Response response = request.post("https://automationintesting.online/booking/");

        Assert.assertEquals(response.getStatusCode(), 409);
    }

    @Test
    @Category(TestCreateBooking.class)
    public void testCreateBookingWhenCheckinDateIsGreaterThanCheckoutDate() {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        JSONObject dates = new JSONObject();
        dates.put("checkin","2019-12-26");
        dates.put("checkout", "2019-12-24");
        json.put("bookingid",74);
        json.put("roomid",526);
        json.put("firstname","asss");
        json.put("lastname","xssc");
        json.put("depositpaid","true");
        json.put("bookingdates", new JSONObject(dates));

        request.body(json.toJSONString());
        Response response = request.post("https://automationintesting.online/booking/");

        Assert.assertEquals(response.getStatusCode(), 409);
    }

    @Test
    public void testCreateBookingWhenFieldsAreMissing() {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();
        json.put("bookingid",74);
        json.put("roomid",526);
        json.put("firstname","asss");
        json.put("lastname","xssc");
        json.put("depositpaid","true");

        request.body(json.toJSONString());
        Response response = request.post("https://automationintesting.online/booking/");

        Assert.assertEquals(response.getStatusCode(), 500);
    }

    @Test
    public void testCreateBookingWhenThereAreNoFields() {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");

        JSONObject json = new JSONObject();

        request.body(json.toJSONString());
        Response response = request.post("https://automationintesting.online/booking/");

        Assert.assertEquals(response.getStatusCode(), 400);
    }
}
