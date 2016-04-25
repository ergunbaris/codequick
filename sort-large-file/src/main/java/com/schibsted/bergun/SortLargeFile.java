package com.schibsted.bergun;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.princeton.cs.algorithms.Queue;

/**
 * @author bergun
 *
 */
public class SortLargeFile
	{
		private static final String USAGE = String.format("%nUsage: java "
				+ "com.schibsted.bergun.SortLargeFile <input_file_path> %n"
				+ "Hint: Use -Xmx to limit heap memory size");

		private final static String FILE_ENCODING = "UTF-8";

		private final static String SORTED_FILE_SUFFIX = "_sorted";

		public static void main(String... args) throws IOException
			{
				if (args.length != 1)
					{
						throw new IllegalArgumentException(USAGE);
					}
				// NOTE: For this exercise I am not going to require an output file path
				// and concentrate on algorithmic challenge.
				SortLargeFile.sort(args[0]);
			}

		public static void sort(String fileName) throws IOException
			{
				Path inputFilePath = Paths.get(fileName);
				SortLargeFileHelper.checkIfFileExists(inputFilePath);
				int subFileCount = 0;
				int expectedMergeBufferCount = 0;
				try
					{

						long heapSize = SortLargeFileHelper.getCurrentHeapSize();
						long availableHeapSize = SortLargeFileHelper
								.calculateAvailableHeapSize(heapSize);

						// TODO remove this debug message
						System.out.printf("heap size=%d availableHeapSize =%d%n",
															heapSize,
															availableHeapSize);
						long fileSize = Files.size(inputFilePath);

						expectedMergeBufferCount = SortLargeFileHelper
								.calculateExpectedMergeBufferCount(	fileSize,
																										availableHeapSize);

						subFileCount = sortFileInChunks(inputFilePath,
																						availableHeapSize,
																						expectedMergeBufferCount);

						long bufferSize = availableHeapSize / (subFileCount + 1);

						nWayMergeSubFiles(inputFilePath,
															subFileCount,
															availableHeapSize);

					} finally
					{
						// SortLargeFileHelper.cleanUpSubFiles(inputFilePath.toString(),
						// subFileCount,
						// expectedMergeBufferCount);
					}

			}

		private static void nWayMergeSubFiles(Path inputFilePath,
																					int subFileCount,
																					long availableHeapSize)
			{
				int subBufferCount = subFileCount;
				long bufferSize = availableHeapSize / (subBufferCount + 1); // + output
																																		// buffer
				// Since bufferSize will be around GB values this math expression
				// I expect to be fitting in a integer value.
				int strBufferSize = (int) (bufferSize
						/ (long) SortLargeFileHelper.AVERAGE_LINE_W_ASSUMPTION);

				List<Queue<String>> inputBuffers = new ArrayList<>();
				Queue<String> outputBuffers = new Queue<>();
				for (int fileIndex = 0; fileIndex < subFileCount; fileIndex++)
					{
						inputBuffers.add(new Queue<>());

					}

			}

		private static int sortFileInChunks(Path inputFilePath,
																				long availableHeapSize,
																				int expectedMergeBufferCount)
				throws IOException
			{
				int subFileCount = 0;
				try (Scanner sc = new Scanner(inputFilePath, FILE_ENCODING))
					{
						int stringArraySize = (int) (availableHeapSize
								/ (SortLargeFileHelper.AVERAGE_STRING_MEM_ASSUMPTION
										+ SortLargeFileHelper.STRING_MEM_OVERHEAD_ASSUMPTION));
						System.out.println(stringArraySize);

						String[] buffer = new String[stringArraySize];
						int bytesRead = 0;
						while (sc.hasNextLine())
							{
								int index = 0;
								for (index = 0; index < buffer.length; index++)
									{
										if (!sc.hasNextLine() || bytesRead >= availableHeapSize)
											{
												System.out.println(bytesRead);
												break;
											}

										buffer[index] = sc.nextLine();
										bytesRead += SortLargeFileHelper.STRING_MEM_OVERHEAD_ASSUMPTION
												+ buffer[index].length() * 2;

									}
								bytesRead = 0;

								Quick3stringModified.sort(buffer,
																					index);
								long ioBufferSize = availableHeapSize
										/ expectedMergeBufferCount;
								System.out.println(expectedMergeBufferCount);
								SortLargeFileHelper.writeBufferToSubFile(	buffer,
																													index,
																													ioBufferSize,
																													SortLargeFileHelper
																															.generateSubFileName(	inputFilePath
																																	.toString(),
																																										subFileCount));
								// TODO remove this debug message
								System.out.println(subFileCount++);
							}
					}
				return subFileCount;
			}

	}
