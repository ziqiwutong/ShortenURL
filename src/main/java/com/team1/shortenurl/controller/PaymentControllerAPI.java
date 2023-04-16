package com.team1.shortenurl.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.team1.shortenurl.entity.CustomerData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

public class PaymentControllerAPI {
    @RequestMapping("/testPay")
    public CustomerData test(@RequestBody CustomerData data) throws StripeException {
        Stripe.apiKey = "sk_test_51MxbpCHbnp0twtPHd8b8U1v5B581rb64whdE2XQVB1vdNXjNigUGNmyKEDyYPw8cIw9zzivQrq1VpoHjaCs8NF5T00R28MrpaR";

        Map<String, Object> params = new HashMap<>();
        params.put("name", data.getName());
        params.put("email", data.getEmail());

        Customer customer = Customer.create(params);
        data.setCustomerId(customer.getId());

        return data;
    }
}
