package com.hackerrank.anokas4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BotSavesPrincessTest {

	BotSavesPrincess testSubject;

	@BeforeMethod
	public void initTestVariables() {
		testSubject = new BotSavesPrincess();
	}

	@DataProvider(name = "botSavesPrincessTestDataProvider")
	public String[] getFilesNamesInDirectory() throws IOException {
		List<String> filesInFolder = Files.walk(Paths.get("src/test/resources/BotSavesPrincess"))
				.filter(Files::isRegularFile).map(Path::toAbsolutePath).map(Path::toString)
				.collect(Collectors.toList()).stream().filter(name -> !name.endsWith("_results.txt")).collect(Collectors.toList());
		return filesInFolder.toArray(new String[filesInFolder.size()]);
	}

	@Test(dataProvider = "botSavesPrincessTestDataProvider", enabled = true)
	public void testBotSavesPrincess(String testFileName) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(testFileName));
		List<String> movesList = testSubject.getMoves(sc);
		String[] moves = movesList.toArray(new String[movesList.size()]);
		sc.close();
		sc = new Scanner(new File(testFileName.replace(".txt","_results.txt")));
		if (sc.hasNextLine()) {
			String[] expectedmoves = sc.nextLine().split(",");
			Assert.assertTrue(Arrays.deepEquals(expectedmoves, moves));
		}
		sc.close();
	}

}
