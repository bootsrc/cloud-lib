package io.github.bootsrc.cloud.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @auther zzyy
 * @create 2020-02-23 15:01
 */
@RestController
@Slf4j
@RequestMapping("")
public class OrderController {
    @Resource
    private RestTemplate restTemplate;

    @Value("${service-url.payment}")
    private String serverURL;

    @GetMapping(value = "/getPayment/{id}")
    public String getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(serverURL + "/payment/getPayment/" + id, String.class);
    }

}
