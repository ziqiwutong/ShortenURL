package com.team1.shortenurl.controller;

import com.alibaba.fastjson.JSONObject;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
import com.stripe.model.checkout.Session;
import com.team1.shortenurl.entity.CustomerData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin
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

    @RequestMapping("/testPayPage")
    public void testPage(@RequestBody String json){
        System.out.println("success");
    }

    @RequestMapping("/pay")
    @ResponseBody
    public String pay(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        JSONObject res = new JSONObject();
        try {
            Stripe.apiKey = "sk_test_51MxbpCHbnp0twtPHd8b8U1v5B581rb64whdE2XQVB1vdNXjNigUGNmyKEDyYPw8cIw9zzivQrq1VpoHjaCs8NF5T00R28MrpaR";
            Map<String, Object> params = new HashMap<String, Object>();
            ArrayList<String> paymentMethodTypes = new ArrayList<>();
            paymentMethodTypes.add("card");
            params.put("payment_method_types", paymentMethodTypes);
            ArrayList<HashMap<String, Object>> lineItems = new ArrayList<>();
            HashMap<String, Object> lineItem = new HashMap<String, Object>();
            lineItem.put("name", "sslt1premium");
            lineItem.put("description", "This is a test");
            lineItem.put("amount", 100); // 支付金额
            lineItem.put("currency", "usd"); //支付币种
            lineItem.put("quantity", 1);
            lineItems.add(lineItem);
            params.put("line_items", lineItems);
            //TODO 必须使用https 返回的回调地址
            String uuid = UUID.randomUUID().toString();
            params.put("client_reference_id", uuid);//业务系统唯一标识 即订单唯一编号
            //log.info("uuid:{}");
            res.put("uuid", uuid);
            //params.put("success_url", URLUtils.getBaseURl(httpRequest)+"/paySuccess");// 支付成功跳转页面
            //params.put("cancel_url",  URLUtils.getBaseURl(httpRequest)+"/payError");// 支付失败跳转页面
            params.put("success_url", "https://sslt1.herokuapp.com/testPayPage");// 支付成功跳转页面
            params.put("cancel_url",  "https://sslt1.herokuapp.com/testPayPage");// 支付失败跳转页面
            Session session = Session.create(params);
            String sessionId = session.getId();
            //log.info("sessionId :{}",session.getId());
            res.put("sessionId", sessionId);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return res.toJSONString();
    }
}
