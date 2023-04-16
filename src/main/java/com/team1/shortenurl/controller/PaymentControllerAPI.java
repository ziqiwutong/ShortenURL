package com.team1.shortenurl.controller;

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

    @RequestMapping("/pay")
    @ResponseBody
    public Map<String,String>  pay(HttpServletRequest httpRequest, HttpServletResponse httpResponse){
        Map<String,String> resultMap = new HashMap<>();
        try {
            Stripe.apiKey = "sk_test_51MxbpCHbnp0twtPHd8b8U1v5B581rb64whdE2XQVB1vdNXjNigUGNmyKEDyYPw8cIw9zzivQrq1VpoHjaCs8NF5T00R28MrpaR";
            Map<String, Object> params = new HashMap<String, Object>();
            ArrayList<String> paymentMethodTypes = new ArrayList<>();
            paymentMethodTypes.add("card");
            params.put("payment_method_types", paymentMethodTypes);
            ArrayList<HashMap<String, Object>> lineItems = new ArrayList<>();
            HashMap<String, Object> lineItem = new HashMap<String, Object>();
            lineItem.put("name", "胡鹏飞测试商品");
            lineItem.put("description", "这是一个测试单描述");
            lineItem.put("amount", 500); // 支付金额
            lineItem.put("currency", "usd"); //支付币种
            lineItem.put("quantity", 1);
            lineItems.add(lineItem);
            params.put("line_items", lineItems);
            //TODO 必须使用https 返回的回调地址
            String uuid = UUID.randomUUID().toString();
            params.put("client_reference_id", uuid);//业务系统唯一标识 即订单唯一编号
            log.info("uuid:{}");
            params.put("success_url", URLUtils.getBaseURl(httpRequest)+"/paySuccess");// 支付成功跳转页面
            params.put("cancel_url",  URLUtils.getBaseURl(httpRequest)+"/payError");// 支付失败跳转页面
            Session session = Session.create(params);
            String sessionId = session.getId();
            log.info("sessionId :{}",session.getId());
            resultMap.put("sessionId",sessionId);
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
