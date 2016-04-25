package com.schibsted.bergun;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SortLargeFileHelperTest
	{

		@Test(dataProvider = "getAvailableHeapSizeData",
				dataProviderClass = SortLargeFileHelperTest.class)
		public void shouldCalculateAvailableHeapSize(long heapSize)
			{
				long availableHeapSize = SortLargeFileHelper
						.calculateAvailableHeapSize(heapSize);
				Assert
						.assertTrue(availableHeapSize > SortLargeFileHelper.MIN_SUPPORTED_AVAILABLE_HEAP_SIZE);
			}

		@Test(dataProvider = "getAvailableHeapSizeExceptionData",
				dataProviderClass = SortLargeFileHelperTest.class,
				expectedExceptions = HeapSizeNotSupportedException.class)
		public void shouldThrowHeapSizeExceptionOnCalculateAvailableHeapSize(long heapSize)
			{
				SortLargeFileHelper.calculateAvailableHeapSize(heapSize);
			}

		@Test(dataProvider = "getSubFileWritingData",
				dataProviderClass = SortLargeFileHelperTest.class)

		public void shouldWriteArrayToSubFile(String[] lines,
																					String fileName,
																					int elementPerFile,
																					int expectedSubFileCount,
																					int expectedSubSortFileCount,
																					String expectedTestStringPrefix)
				throws IOException
			{
				// TODO too long test case berakdown
				int elementCount = 0;
				int subFileIndex = 0;
				long ioBufSize = 1000000;

				List<String> bufLines = new ArrayList<>();
				for (String line : lines)
					{
						bufLines.add(line);
						elementCount++;
						if (elementCount == elementPerFile)
							{
								String outFileName = SortLargeFileHelper
										.generateSubFileName(	fileName,
																					subFileIndex);
								SortLargeFileHelper.writeBufferToSubFile(	(String[]) bufLines
										.toArray(new String[0]),
																													bufLines.size(),
																													ioBufSize,
																													outFileName);
								bufLines = new ArrayList<>();
								elementCount = 0;
								subFileIndex++;
							}
					}

				for (int i = 0; i < expectedSubFileCount; i++)
					{
						Path path = Paths
								.get(SortLargeFileHelper
										.generateSubFileName(	SortLargeFileHelper
												.generateSubFileName(	fileName,
																							i),
																					0));
						Assert.assertTrue(Files.exists(path));
						String[] buffer = new String[elementPerFile];
						int bufferIndex = 0;
						try (Scanner scanner = new Scanner(path.toFile()))
							{
								while (scanner.hasNextLine())
									{
										buffer[bufferIndex] = scanner.nextLine();
										bufferIndex++;
									}
							}
						for (String line : buffer)
							{
								Assert.assertEquals(line,
																		expectedTestStringPrefix + i);
							}
					}
				Path path = Paths.get(SortLargeFileHelper.generateSubFileName(fileName,
																																			expectedSubFileCount));
				Assert.assertFalse(Files.exists(path));

				SortLargeFileHelper.cleanUpSubFiles(fileName,
																						expectedSubFileCount,
																						expectedSubSortFileCount);

				for (int i = 0; i < expectedSubFileCount; i++)
					{
						Path path1 = Paths
								.get(SortLargeFileHelper
										.generateSubFileName(	SortLargeFileHelper
												.generateSubFileName(	fileName,
																							i),
																					0));
						Assert.assertFalse(	Files.exists(path1),
																path1.toString());
					}
			}

		@DataProvider
		public static Object[][] getSubFileWritingData()
			{
				String[] lines =
					{ "test0", "test1", "test2", "test3" };
				return new Object[][]
					{
							{ lines, "output", 1, 4, 1, "test" } };
			}

		@DataProvider
		public static Object[][] getAvailableHeapSizeData()
			{
				// heapSize
				return new Object[][]
					{
							{ 9_000_000L },
							{ 12_000_000L },
							{ 16_000_000L } };
			}

		@DataProvider
		public static Object[][] getAvailableHeapSizeExceptionData()
			{
				// heapSize
				return new Object[][]
					{
							{ 1_000_000L },
							{ 2_000_000L },
							{ 4_000_000L } };
			}

	}
