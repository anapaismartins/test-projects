package com.hackerrank.anokas4;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TripleRecursion {
	
	private static Logger logger = LoggerFactory.getLogger(TripleRecursion.class);

	public int[][] solve(Scanner sc) {
		logger.debug("[ENTER] solve");
		int n = sc.nextInt();
		int m = sc.nextInt();
		int k = sc.nextInt();
		int[][] result = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) {
					if (i == 0) {
						result[i][j] = m;
					} else {
						result[i][j] = result[i - 1][j - 1] + k;
					}
				} else if (i > j) {
					result[i][j] = result[i - 1][j] - 1;
				} else {
					result[i][j] = result[i][j - 1] - 1;
				}
			}
		}
		logger.debug("[OUT] solve");
		return result;
	}
}
