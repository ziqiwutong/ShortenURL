package com.team1.shortenurl.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.team1.shortenurl.entity.CustomerData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class PaymentControllerAPI {

    @Value("$(stripe.apikey)")
    String stripeKey;
    @RequestMapping("/testPay")
    public CustomerData test(@RequestBody CustomerData data) throws StripeException {
        Stripe.apiKey = stripeKey;

        Map<String, Object> params = new HashMap<>();
        params.put("name", data.getName());
        params.put("email", data.getEmail());

        Customer customer = Customer.create(params);
        data.setCustomerId(customer.getId());

        return data;
    }
}
