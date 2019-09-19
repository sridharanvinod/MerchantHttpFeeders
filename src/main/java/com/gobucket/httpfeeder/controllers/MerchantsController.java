package com.gobucket.httpfeeder.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.PollableChannel;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gobucket.dynamodb.repositories.models.coupon.Coupon;
import com.gobucket.httpfeeder.services.MerchantFeederServices;
import com.gobucket.models.HttpResponse;

@RequestMapping("/gobasket")
@RestController
public class MerchantsController {
	@Autowired MerchantFeederServices merchantFeederServices;
	@Autowired 
	@Qualifier("http.feeder.request.channel")
	MessageChannel httpFeedRequestChannel;
	
	@Autowired 
	@Qualifier("http.feeder.response.channel")
	PollableChannel httpFeedResponseChannel;
	
	@GetMapping(value="/feeds/merchant1", produces = "application/json")
	public @ResponseBody HttpResponse triggerHttpFeeder() {
		MessagingTemplate mt = new MessagingTemplate();
		mt.send(httpFeedRequestChannel, new GenericMessage<>("merchant1"));
		Message<?> message = mt.receive(httpFeedResponseChannel);
		HttpResponse objHttpResponse = new HttpResponse(HttpStatus.OK.value(), HttpStatus.OK.name(),message.getHeaders().toString(), message.getPayload());
		return objHttpResponse;
	}
	@GetMapping(value="/coupons/id/{couponId}")
	public @ResponseBody Coupon getCoupon(@PathVariable Long couponId) {
		Optional<Coupon> coupon = merchantFeederServices.getCoupon(couponId);
		if(coupon.isPresent()) {
			return coupon.get();
		}
		return null;
	}
	@GetMapping(value="/coupons/brand/{brand}")
	public @ResponseBody List<Coupon> getCoupon(@PathVariable String brand) {
		return merchantFeederServices.getCoupon(brand);
		
	}
}