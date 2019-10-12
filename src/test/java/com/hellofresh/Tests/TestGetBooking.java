package com.hellofresh.Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.Matchers.equalTo;


public class TestGetBooking {

    @Test
    @Category(TestGetBooking.class)
    public void testGetBookingsWithValidBookingId() {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.get("https://automationintesting.online/booking/{id}", 76);

        Assert.assertEquals(response.getStatusCode(), 200);
        response.then().body("bookingid", equalTo(76));
        response.then().body("roomid", equalTo(526));
        response.then().body("firstname", equalTo("xxxasss"));
        response.then().body("lastname", equalTo("zzxssc"));
        response.then().body("bookingdates.checkin", equalTo("2019-10-10"));
        response.then().body("bookingdates.checkout", equalTo("2019-10-12"));
    }

    @Test
    @Category(TestGetBooking.class)
    public void testGetBookingsWithInvalidBookingId() {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        Response response = request.get("https://automationintesting.online/booking/{id}", 1);

        Assert.assertEquals(response.getStatusCode(), 404);
    }

}
