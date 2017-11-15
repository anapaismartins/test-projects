package com.hackerrank.anokas4;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurfaceArea {
	
	private static Logger logger = LoggerFactory.getLogger(SurfaceArea.class);

	private long getSurfaceArea(int[][] heights){
		logger.debug("[ENTER] getSurfaceArea");
		long surfaceArea = 0;
		for(int i=0;i<heights.length;i++){
			for(int j=0;j<heights[i].length;j++){
				//bottom and top
				surfaceArea+=2;
				if(i==0){
					surfaceArea+=heights[i][j];
				}
				if(i==heights.length-1){
					surfaceArea+=heights[i][j];
				}
				if(j==0){
					surfaceArea+=heights[i][j];
				}
				if(j==heights[i].length-1){
					surfaceArea+=heights[i][j];
				}
				if(j>0){
					surfaceArea+=Math.abs(heights[i][j]-heights[i][j-1]);
				}
				if(i>0){
					surfaceArea+=Math.abs(heights[i][j]-heights[i-1][j]);
				}
			}
		}
		logger.debug("[OUT] getSurfaceArea");
		return surfaceArea;
	}
	public long solve(Scanner sc) {
		logger.debug("[ENTER] solve");
		int rows = sc.nextInt();
		int columns = sc.nextInt();
		int[][] heights = new int[rows][columns];
		for(int i=0;i<rows;i++){
			for(int j=0;j<columns;j++){
				heights[i][j] = sc.nextInt();
			}
		}
		logger.debug("[OUT] solve");
		return getSurfaceArea(heights);
	}
}
