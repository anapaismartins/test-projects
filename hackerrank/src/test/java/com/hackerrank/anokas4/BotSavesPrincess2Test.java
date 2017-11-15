package com.hackerrank.anokas4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BotSavesPrincess2Test {

	BotSavesPrincess2 testSubject;

	@BeforeMethod
	public void initTestVariables() {
		testSubject = new BotSavesPrincess2();
	}

	@DataProvider(name = "BotSavesPrincess2TestDataProvider")
	public Object[][] getFilesNamesInDirectory() throws IOException {
		List<String> filesInFolder = Files.walk(Paths.get("src/test/resources/BotSavesPrincess2"))
				.filter(Files::isRegularFile).map(Path::toAbsolutePath).map(Path::toString)
				.collect(Collectors.toList());
		Map<String, String> data = new HashMap<String, String>();
		for (String file : filesInFolder) {
			if (file.endsWith("_result.txt")) {
				String testFileName = file.replace("_result.txt", ".txt");
				Scanner sc = new Scanner(new File(file));
				String nextMove = sc.nextLine();
				sc.close();
				data.put(testFileName, nextMove);
			}
		}
		return transformMapToObjectArray(data);
	}

	private Object[][] transformMapToObjectArray(Map<String, String> data) {
		Object[][] returnValue = new Object[data.size()][2];
		int index = 0;
		for (Map.Entry<String, String> entry : data.entrySet()) {
			returnValue[index][0] = entry.getKey();
			returnValue[index++][1] = entry.getValue();
		}
		return returnValue;
	}

	@Test(dataProvider = "BotSavesPrincess2TestDataProvider", enabled = true)
	public void testBotSavesPrincess(String testFileName, String expectedMove) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(testFileName));
		String move = testSubject.getNextMove(sc);
		Assert.assertEquals(move, expectedMove);
		sc.close();
	}
}
