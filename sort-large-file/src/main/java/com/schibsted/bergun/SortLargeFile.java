package com.schibsted.bergun;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.princeton.cs.algorithms.MinPQ;
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

						Path outputFilePath = SortLargeFileHelper
								.generateOutputFilePath(inputFilePath);

						nWayMergeSubFiles(inputFilePath,
															outputFilePath,
															bufferSize,
															subFileCount,
															expectedMergeBufferCount);

					} finally
					{
						 SortLargeFileHelper.cleanUpSubFiles(inputFilePath.toString(),
						 subFileCount,
						 expectedMergeBufferCount);
					}

			}

		private static void nWayMergeSubFiles(Path inputFilePath,
																					Path outputFilePath,
																					long bufferSize,
																					int subFileCount,
																					int subSortedFileCount)
				throws IOException
			{
				short[] sortedFileIndex = new short[subFileCount];
				List<Queue<String>> inputBuffers = new ArrayList<>();
				Queue<String> outputBuffers = new Queue<>();
				for (int i = 0; i < subFileCount; i++)
					{
						inputBuffers.add(	i,
															new Queue<>());
						Path path = Paths
								.get(SortLargeFileHelper
										.generateSubFileName(	SortLargeFileHelper
												.generateSubFileName(	inputFilePath.toString(),
																							i),
																					sortedFileIndex[i]));
						SortLargeFileHelper.readFileToQueueBuffer(path,
																											inputBuffers.get(i));
						sortedFileIndex[i]++;
					}

				long bytesRead = 0;
				Queue<String> queue = nWayCompare(inputFilePath,
																					inputBuffers,
																					sortedFileIndex,
																					subSortedFileCount);
				while (true)
					{
						if (queue == null)
							{
								SortLargeFileHelper.writeQueueBufferToFile(	outputBuffers,
																														outputFilePath
																																.toString());
								break;
							}
						String line = queue.dequeue();
						outputBuffers.enqueue(line);
						bytesRead += SortLargeFileHelper.STRING_MEM_OVERHEAD_ASSUMPTION
								+ line.length() * 2;
						if (bytesRead >= bufferSize)
							{
								SortLargeFileHelper.writeQueueBufferToFile(	outputBuffers,
																														outputFilePath
																																.toString());
								bytesRead = 0;
							}
						queue = nWayCompare(inputFilePath,
																inputBuffers,
																sortedFileIndex,
																subSortedFileCount);
					}

			}

		private static Queue<String> nWayCompare(	Path inputFilePath,
																							List<Queue<String>> inputBuffers,
																							short[] sortedFileIndex,
																							int subSortedFileCount)
				throws IOException
			{
				MinPQ<HeapPeekElement> minPq = new MinPQ<>();
				for (int i = 0; i < inputBuffers.size(); i++)
					{
						if (inputBuffers.get(i).isEmpty())
							{
								if (sortedFileIndex[i] >= subSortedFileCount)
									{
										continue;
									} else
									{
										Path path = Paths.get(SortLargeFileHelper
												.generateSubFileName(	SortLargeFileHelper
														.generateSubFileName(	inputFilePath.toString(),
																									i),
																							sortedFileIndex[i]));
										sortedFileIndex[i]++;
										if (!Files.exists(path))
											{

												continue;
											}
										SortLargeFileHelper.readFileToQueueBuffer(path,
																															inputBuffers
																																	.get(i));
									}
							}
						HeapPeekElement elem = new HeapPeekElement(i,
								inputBuffers.get(i).peek());
						minPq.insert(elem);
					}
				if (minPq.isEmpty())
					{
						return null;
					}
				HeapPeekElement min = minPq.min();
				return inputBuffers.get(min.subFileIndex);
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

						String[] buffer = new String[stringArraySize];
						int bytesRead = 0;
						while (sc.hasNextLine())
							{
								int index = 0;
								for (index = 0; index < buffer.length; index++)
									{
										if (!sc.hasNextLine() || bytesRead >= availableHeapSize)
											{
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
								SortLargeFileHelper.writeBufferToSubFile(	buffer,
																													index,
																													ioBufferSize,
																													SortLargeFileHelper
																															.generateSubFileName(	inputFilePath
																																	.toString(),
																																										subFileCount));
								subFileCount++;
							}
					}
				return subFileCount;
			}

		private static class HeapPeekElement implements Comparable<HeapPeekElement>
			{
				private final int subFileIndex;
				private final String line;

				public HeapPeekElement(	int subFileIndex,
																String line)
					{

						this.subFileIndex = subFileIndex;
						this.line = line;
					}

				@Override
				public int hashCode()
					{
						final int prime = 31;
						int result = 1;
						result = prime * result + ((line == null) ? 0
								: line.hashCode());
						return result;
					}

				@Override
				public boolean equals(Object obj)
					{
						if (this == obj)
							return true;
						if (obj == null)
							return false;
						if (getClass() != obj.getClass())
							return false;
						HeapPeekElement other = (HeapPeekElement) obj;
						if (line == null)
							{
								if (other.line != null)
									return false;
							} else if (!line.equals(other.line))
							return false;
						return true;
					}

				@Override
				public int compareTo(HeapPeekElement o)
					{
						return this.line.compareTo(o.line);
					}

			}

	}
