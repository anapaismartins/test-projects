package com.hackerrank.anokas4;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LuckyPurchase {

	private static Logger logger = LoggerFactory.getLogger(LuckyPurchase.class);

	private long getValue(String price) {
		logger.debug("[ENTER] getValue");
		logger.debug("[ARGS.] price {}", price);
		long returnValue = -1;
		if (price.length() % 2 == 0) {
			int numberOfSevens = price.length() - price.replace("7", "").length();
			int numberOfFours = price.length() - price.replace("4", "").length();
			if (numberOfSevens + numberOfFours == price.length() && numberOfSevens == numberOfFours) {
				returnValue = Long.valueOf(price);
			}
		}
		logger.debug("[OUT] getValue");
		return returnValue;
	}

	public String getMinimum(Scanner sc) {
		logger.debug("[ENTER] getMinimum");
		long minimumPrice = -1;
		String laptopBrand = null;
		int m = sc.nextInt();
		sc.nextLine();
		for (int i = 0; i < m; i++) {
			String[] nextLineValues = sc.nextLine().split(" ");
			long value = getValue(nextLineValues[1]);
			if (value != -1 && (minimumPrice == -1 || value < minimumPrice)) {
				laptopBrand = nextLineValues[0];
				minimumPrice = value;
			}
		}
		logger.debug("[OUT] getMinimum");
		return laptopBrand != null ? laptopBrand : "-1";
	}
}
