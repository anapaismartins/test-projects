package com.hackerrank.anokas4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TripleRecursionTest {
	TripleRecursion testSubject;

	@BeforeMethod
	public void initTestVariables() {
		testSubject = new TripleRecursion();
	}

	@DataProvider(name = "TripleRecursionTestDataProvider")
	public String[] getFilesNamesInDirectory() throws IOException {
		List<String> filesInFolder = Files.walk(Paths.get("src/test/resources/TripleRecursion"))
				.filter(Files::isRegularFile).map(Path::toAbsolutePath).map(Path::toString).collect(Collectors.toList())
				.stream().filter(name -> !name.endsWith("_results.txt")).collect(Collectors.toList());
		return filesInFolder.toArray(new String[filesInFolder.size()]);
	}

	@Test(dataProvider = "TripleRecursionTestDataProvider", enabled = true)
	public void testBotSavesPrincess(String testFileName) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(testFileName));
		int[][] results = testSubject.solve(sc);
		sc.close();
		sc = new Scanner(new File(testFileName.replace(".txt", "_results.txt")));
		int index = 0;
		int[][] expectedResults = null;
		while (sc.hasNextLine()) {
			String[] values = sc.nextLine().split(" ");
			if (index == 0) {
				expectedResults = new int[values.length][values.length];
			}
			for (int i = 0; i < values.length; i++) {
				expectedResults[index][i] = Integer.parseInt(values[i]);
			}
			index++;
		}
		Assert.assertEquals(results.length, expectedResults.length);
		for (int i = 0; i < results.length; i++) {
			Assert.assertEquals(results[i].length, expectedResults[i].length);
			for (int j = 0; j < results[i].length; j++) {
				Assert.assertEquals(results[i][j], expectedResults[i][j]);
			}
		}
		sc.close();
	}
}
