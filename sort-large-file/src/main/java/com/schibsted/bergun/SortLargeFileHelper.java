package com.schibsted.bergun;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import edu.princeton.cs.algorithms.Queue;

public class SortLargeFileHelper
	{

		public static final String FILE_EXTENSION_DELIMITER = ".";

		public static final String SUB_FILE_INDEX_SEPARATOR = "_";

		public static final short AVERAGE_LINE_W_ASSUMPTION = 32;

		public static final short AVERAGE_STRING_MEM_ASSUMPTION = 2 * 5;
		// keep the line length short so that we have a large memory
		// to fit in desired amount of bytes. 5 * 2 Bytes

		public static final byte STRING_MEM_OVERHEAD_ASSUMPTION = 40;

		private static final byte QUICKSTRING_MEM_MARGIN_FACTOR = 4;

		private static final byte GC_FACTOR = 2; // Leave some space for Garbage
																							// Collection to catch up

		private static final int SCANNER_BUFFER_SIZE = 1024;

		public static final int MIN_SUPPORTED_AVAILABLE_HEAP_SIZE = 4194304;

		private static final String FILE_NOT_FOUND = "%s file does not exist!";

		public static long calculateAvailableHeapSize(long heapSize)
			{
				long quick3wayStrSortMemorySize = QUICKSTRING_MEM_MARGIN_FACTOR
						* (((long) (Math.log(heapSize) / Math.log(2)))
								+ AVERAGE_LINE_W_ASSUMPTION);
				long availableHeapSize = heapSize - quick3wayStrSortMemorySize
						- SCANNER_BUFFER_SIZE;
				availableHeapSize = availableHeapSize / GC_FACTOR;
				if (availableHeapSize < MIN_SUPPORTED_AVAILABLE_HEAP_SIZE)
					{
						throw new HeapSizeNotSupportedException(
								String.format(HeapSizeNotSupportedException.ERROR_MSG,
															MIN_SUPPORTED_AVAILABLE_HEAP_SIZE,
															availableHeapSize));
					}
				return availableHeapSize;
			}

		public static int calculateExpectedMergeBufferCount(long filesize,
																												long availableHeapSize)
			{
				int javaOverHeads = (STRING_MEM_OVERHEAD_ASSUMPTION
						+ AVERAGE_STRING_MEM_ASSUMPTION) / AVERAGE_STRING_MEM_ASSUMPTION;
				int expectedSubFileCount = (int) Math
						.ceil((filesize * javaOverHeads) / availableHeapSize) + 1; // +1 for
																																				// small
																																				// subfile
																																				// differences

				return expectedSubFileCount + 1; // take into account output buffer
			}

		public static long getCurrentHeapSize()
			{
				return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage()
						.getCommitted()
						- (2 * ManagementFactory.getMemoryMXBean().getHeapMemoryUsage()
								.getUsed());
			}

		public static void checkIfFileExists(Path path) throws FileNotFoundException
			{
				if (!Files.exists(path))
					{
						throw new FileNotFoundException(String.format(FILE_NOT_FOUND,
																													path.getFileName()));
					}
			}

		public static void writeBufferToSubFile(String[] lines,
																						int hi,
																						long ioBufferSize,
																						String subFileName)
				throws FileNotFoundException
			{
				PrintWriter writer = null;
				try
					{
						int subSortedFileCount = 0;
						writer = new PrintWriter(
								SortLargeFileHelper.generateSubFileName(subFileName,
																												subSortedFileCount));
						long bytesRead = 0;
						for (int i = 0; i < hi; i++)
							{
								writer.println(lines[i]);
								bytesRead += SortLargeFileHelper.STRING_MEM_OVERHEAD_ASSUMPTION
										+ lines[i].length() * 2;
								if (bytesRead >= ioBufferSize)
									{

										bytesRead = 0;
										subSortedFileCount++;
										writer.close();
										writer.flush();
										writer = new PrintWriter(
												SortLargeFileHelper.generateSubFileName(subFileName,
																																subSortedFileCount));
									}
							}
					} finally
					{
						if (null != writer)
							{
								writer.close();
								writer.flush();
							}
					}

			}

		public static void writeBufferToSubFile(Queue<String> buffer,
																						String subFileName)
				throws FileNotFoundException
			{
				try (PrintWriter writer = new PrintWriter(subFileName))
					{
						while (!buffer.isEmpty())
							{
								writer.println(buffer.dequeue());
							}
					}
			}

		public static String generateSubFileName(	String inputFileName,
																							int subFileIndex)
			{

				StringBuilder builder = new StringBuilder(inputFileName);
				int index = builder.indexOf(FILE_EXTENSION_DELIMITER);
				if (index != -1)
					{
						builder.replace(index,
														index + 1,
														SUB_FILE_INDEX_SEPARATOR + subFileIndex
																+ FILE_EXTENSION_DELIMITER);
					} else
					{
						builder.append(SUB_FILE_INDEX_SEPARATOR + subFileIndex);
					}
				return builder.toString();

			}

		public static void cleanUpSubFiles(	String filename,
																				int subFileCount,
																				int subSortFileCount)
				throws IOException
			{
				for (int i = 0; i < subFileCount; i++)
					{
						for (int j = 0; j < subSortFileCount; j++)
							{
								Path inputFilePath = Paths
										.get(generateSubFileName(	generateSubFileName(filename,
																																	i),
																							j));
								Files.deleteIfExists(inputFilePath);
							}
					}

			}
	}
