package com.team1.shortenurl.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @RequestMapping(value = "/payment")
@RestController
public class PaymentController {

    @Value("$(stripe.apikey)")
    String stripeKey;


}
