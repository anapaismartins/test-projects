package com.hackerrank.anokas4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BotSavesPrincess {
	
	private static Logger logger = LoggerFactory.getLogger(BotSavesPrincess.class);

	private List<String> displayPathtoPrincess(int[] princessPosition, int[] botPosition) {
		logger.debug("[ENTER] displayPathtoPrincess");
		logger.debug("[ARGS.] princessPosition {}, botPosition {}",Arrays.toString(princessPosition), Arrays.toString(botPosition));
		List<String> moves = new ArrayList<String>();
		int diff = Math.abs(princessPosition[0] - botPosition[0]);
		if (diff > 0) {
			String movement = princessPosition[0] > botPosition[0] ? "DOWN" : "UP";
			for (int i = 0; i < diff; i++) {
				moves.add(movement);
			}
		}
		diff = Math.abs(princessPosition[1] - botPosition[1]);
		if (diff > 0) {
			String movement = princessPosition[1] > botPosition[1] ? "RIGHT" : "LEFT";
			for (int i = 0; i < diff; i++) {
				moves.add(movement);
			}
		}
		logger.debug("[OUT] displayPathtoPrincess");
		return moves;
	}

	public List<String> getMoves(Scanner sc) {
		logger.debug("[ENTER] getMoves");
		int m = sc.nextInt();
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
		logger.debug("[OUT] getMoves");
		return displayPathtoPrincess(princessPosition, botPosition);
	}

}
