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

public class SurfaceAreaTest {

	SurfaceArea testSubject;

	@BeforeMethod
	public void initTestVariables() {
		testSubject = new SurfaceArea();
	}

	@DataProvider(name = "SurfaceAreaTestsDataProvider")
	public Object[][] getFilesNamesInDirectory() throws IOException {
		List<String> filesInFolder = Files.walk(Paths.get("src/test/resources/SurfaceArea"))
				.filter(Files::isRegularFile).map(Path::toAbsolutePath).map(Path::toString)
				.collect(Collectors.toList());
		Map<String,Long> data = new HashMap<String,Long>();
		for(String file : filesInFolder){
			if(file.endsWith("_result.txt")){
				String testFileName = file.replace("_result.txt", ".txt");
				Scanner sc = new Scanner(new File(file));
				Long result = sc.nextLong();
				sc.close();
				data.put(testFileName, result);
			}
		}
		return transformMapToObjectArray(data);
	}
	
	private Object[][] transformMapToObjectArray(Map<String,Long> data){
		Object[][] returnValue = new Object[data.size()][2];
		int index = 0;
		for(Map.Entry<String, Long> entry : data.entrySet()){
			returnValue[index][0] = entry.getKey();
			returnValue[index++][1] = entry.getValue();
		}
		return returnValue;
	}

	@Test(dataProvider = "SurfaceAreaTestsDataProvider", enabled=true)
	public void testSurfaceArea(String testFileName, Long expectedValue) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(testFileName));
		Long value = testSubject.solve(sc);
		Assert.assertEquals(expectedValue, value);
		sc.close();
	}
}
