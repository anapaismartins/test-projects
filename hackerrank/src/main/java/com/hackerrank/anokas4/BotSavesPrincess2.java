package com.hackerrank.anokas4;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotSavesPrincess2 {
	private static Logger logger = LoggerFactory.getLogger(BotSavesPrincess2.class);

	private String displayNextMove(int[] princessPosition, int[] botPosition) {
		logger.debug("[ENTER] displayPathtoPrincess");
		logger.debug("[ARGS.] princessPosition {}, botPosition {}", princessPosition, botPosition);
		String nextMove = null;
		int horizontalDiff = Math.abs(princessPosition[1] - botPosition[1]);
		int verticalDiff = Math.abs(princessPosition[0] - botPosition[0]);
		if (horizontalDiff == 0) {
			nextMove = princessPosition[0] > botPosition[0] ? "DOWN" : "UP";
		} else if (verticalDiff == 0) {
			nextMove =  princessPosition[1] > botPosition[1] ? "RIGHT" : "LEFT";
		} else {
			double diffGoingVertical = Math.sqrt(Math.pow(horizontalDiff, 2) + Math.pow(verticalDiff - 1, 2));
			double diffGoingHorizontal = Math.sqrt(Math.pow(horizontalDiff - 1, 2) + Math.pow(verticalDiff, 2));
			if (diffGoingVertical < diffGoingHorizontal) {
				nextMove =  princessPosition[0] > botPosition[0] ? "DOWN" : "UP";
			} else {
				nextMove =  princessPosition[1] > botPosition[1] ? "RIGHT" : "LEFT";
			}
		}
		logger.debug("[OUT] displayPathtoPrincess");
		return nextMove;
	}

	public String getNextMove(Scanner sc) {
		logger.debug("[ENTER] getNextMove");
		int m = sc.nextInt();
		sc.nextLine();
		sc.nextLine();
		int princessPosition[] = new int[0], botPosition[] = new int[0];
		for (int i = 0; i < m; i++) {
			String nextLine = sc.nextLine();
			int indexM = nextLine.indexOf("m");
			if (indexM >= 0) {
				botPosition = new int[] { i, indexM };
			}
			int indexP = nextLine.indexOf("p");
			if (indexP >= 0) {
				princessPosition = new int[] { i, indexP };
			}
		}
		logger.debug("[OUT] getNextMove");
		return displayNextMove(princessPosition, botPosition);
	}
}
