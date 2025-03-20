package com.itbulls.cunha.services.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.itbulls.cunha.services.AffiliateMarketingService;

@Service
public class DefaultAffiliateMarketingService implements AffiliateMarketingService {

	@Override
	public String generateUniquePartnerCode() {
		String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder referralCode = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 6; i++) {
			referralCode.append(letters.charAt(random.nextInt(26)));
		}

		return referralCode.toString();
	}

}
