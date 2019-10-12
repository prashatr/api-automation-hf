package com.hellofresh.Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;


public class TestGetBookings {

    @Test
    @Category(TestGetBookings.class)
    public void testGetBookingsWithAtleastTwoBookingsReturned() {

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.queryParam("roomid", 526);

        Response response = request.get("https://automationintesting.online/booking/");

        int totalNumberOfBookings = response.then().extract().response().jsonPath().getList("bookings.bookingid").size();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertTrue(totalNumberOfBookings > 1);
    }

    @Test
    @Category(TestGetBookings.class)
    public void testGetBookingsWithOnlyOneBookingReturned() {   //Failing Test Scenarios

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.queryParam("roomid", 5676);

        Response response = request.get("https://automationintesting.online/booking/");

        int totalNumberOfBookings = response.then().extract().response().jsonPath().getList("bookings.bookingid").size();

        Assert.assertEquals(response.getStatusCode(), 200);   // Error Code should not be 200
        Assert.assertFalse(totalNumberOfBookings > 1);
    }

    @Test
    @Category(TestGetBookings.class)
    public void testGetBookingsWithNoBookingReturned() {   //Failing Test Scenarios

        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.queryParam("roomid", 90);

        Response response = request.get("https://automationintesting.online/booking/");

        int totalNumberOfBookings = response.then().extract().response().jsonPath().getList("bookings.bookingid").size();

        Assert.assertEquals(response.getStatusCode(), 200);    // Error Code should not be 200
        Assert.assertEquals(totalNumberOfBookings, 0);
    }

}
