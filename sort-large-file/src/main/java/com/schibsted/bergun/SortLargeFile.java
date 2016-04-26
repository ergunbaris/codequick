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

		public static void main(String... args) throws IOException
			{
				if (args.length != 1)
					{
						throw new IllegalArgumentException(USAGE);
					}
				// NOTE: For this exercise I am not going to require an output file path
				// and concentrate on algorithmic challenge.
				long start = System.nanoTime();
				SortLargeFile.sort(args[0]);
				long end = System.nanoTime();
				System.err.printf("duration=%d", (end - start)/1_000_000);
			}

		public static void sort(String fileName) throws IOException
			{
				Path inputFilePath = Paths.get(fileName);
				SortLargeFileHelper.checkIfFileExists(inputFilePath);
				int sortedSubFileCount = 0;
				int sortedSubChunkFileCount = 0;
				try
					{

						long heapSize = SortLargeFileHelper.getCurrentHeapSize();
						long availableHeapSize = SortLargeFileHelper
								.calculateAvailableHeapSize(heapSize);

						long inputFileSize = Files.size(inputFilePath);

						sortedSubChunkFileCount = SortLargeFileHelper
								.calculateMergeBufferCount(	inputFileSize,
																						availableHeapSize);

						long bufferSize = availableHeapSize / sortedSubChunkFileCount;

						sortedSubFileCount = sortFileInChunks(inputFilePath,
																									availableHeapSize,
																									bufferSize);

						Path outputFilePath = SortLargeFileHelper
								.generateOutputFilePath(inputFilePath);

						nWayMergeSubFiles(inputFilePath,
															outputFilePath,
															bufferSize,
															sortedSubFileCount,
															sortedSubChunkFileCount);

					} finally
					{
						SortLargeFileHelper.cleanUpSubFiles(inputFilePath.toString(),
																								sortedSubFileCount,
																								sortedSubChunkFileCount);
					}

			}

		private static void nWayMergeSubFiles(Path inputFilePath,
																					Path outputFilePath,
																					long bufferSize,
																					int sortedSubFileCount,
																					int sortedSubChunkFileCount)
				throws IOException
			{
				short[] sortedSubFileChunkIndices = new short[sortedSubFileCount];
				List<Queue<String>> inputBuffers = new ArrayList<>();
				Queue<String> outputBuffer = new Queue<>();

				initializeInputBuffers(	inputBuffers,
																inputFilePath,
																sortedSubFileChunkIndices,
																sortedSubFileCount);

				long bytesRead = 0;
				MinPQ<HeapPeekElement> minPq = new MinPQ<>();
				initializeMinPq(inputBuffers,
												minPq);

				while (true)
					{
						int inputBufferIndex = nWayCompare(minPq);
						if (inputBufferIndex == -1)
							{
								SortLargeFileHelper.writeQueueBufferToFile(	outputBuffer,
																														outputFilePath
																																.toString());
								break;
							}
						Queue<String> queue = inputBuffers.get(inputBufferIndex);
						String line = queue.dequeue();
						boolean queueFull = !queue.isEmpty();
						if (!queueFull)
							{
								queueFull = refillIfBufferEmpty(queue,
																								inputFilePath,
																								sortedSubFileChunkIndices,
																								sortedSubChunkFileCount,
																								inputBufferIndex);
							}
						if (queueFull)
							{
								minPq.insert(new HeapPeekElement(inputBufferIndex,
										queue.peek()));
							}
						outputBuffer.enqueue(line);
						bytesRead += SortLargeFileHelper.STRING_MEM_OVERHEAD_ASSUMPTION
								+ line.length() * SortLargeFileHelper.TWO_BYTES;
						if (bytesRead >= bufferSize)
							{
								SortLargeFileHelper.writeQueueBufferToFile(	outputBuffer,
																														outputFilePath
																																.toString());
								bytesRead = 0;
							}

					}

			}

		private static void initializeMinPq(List<Queue<String>> inputBuffers,
																				MinPQ<HeapPeekElement> minPq)
			{
				for (int i = 0; i < inputBuffers.size(); i++)
					{
						HeapPeekElement elem = new HeapPeekElement(i,
								inputBuffers.get(i).peek());						
						minPq.insert(elem);
					}
			}

		private static void initializeInputBuffers(	List<Queue<String>> inputBuffers,
																								Path inputFilePath,
																								short[] sortedSubFileChunkIndices,
																								int sortedSubChunkFileCount)
				throws IOException
			{
				for (int i = 0; i < sortedSubChunkFileCount; i++)
					{
						inputBuffers.add(	i,
															new Queue<>());
						String sortedSubFileName = SortLargeFileHelper
								.generateSubFileName(	inputFilePath.toString(),
																			i);
						String sortedSubChunkFileName = SortLargeFileHelper
								.generateSubFileName(	sortedSubFileName,
																			sortedSubFileChunkIndices[i]);
						Path sortedSubChunkFilePath = Paths.get(sortedSubChunkFileName);
						SortLargeFileHelper.readFileToQueueBuffer(sortedSubChunkFilePath,
																											inputBuffers.get(i));
						sortedSubFileChunkIndices[i]++;
					}
			}

		private static int nWayCompare(MinPQ<HeapPeekElement> minPq)
				throws IOException
			{
				if (minPq.isEmpty())
					{
						return -1;
					}
				HeapPeekElement min = minPq.delMin();				
				return min.subFileIndex;
			}

		private static boolean refillIfBufferEmpty(	Queue<String> inputBuffer,
																								Path inputFilePath,
																								short[] sortedSubFileChunkIndices,
																								int sortedSubChunkFileCount,
																								int inputBufferIndex)
				throws IOException
			{
				if (inputBuffer.isEmpty())
					{
						if (sortedSubFileChunkIndices[inputBufferIndex] >= sortedSubChunkFileCount)
							{
								return false;
							} else
							{
								String sortedSubFileName = SortLargeFileHelper
										.generateSubFileName(	inputFilePath.toString(),
																					inputBufferIndex);
								String sortedSubChunkFileName = SortLargeFileHelper
										.generateSubFileName(	sortedSubFileName,
																					sortedSubFileChunkIndices[inputBufferIndex]);
								Path path = Paths.get(sortedSubChunkFileName);
								sortedSubFileChunkIndices[inputBufferIndex]++;
								if (!Files.exists(path))
									{
										return false;
									}
								SortLargeFileHelper.readFileToQueueBuffer(path,
																													inputBuffer);
								return true;
							}
					}
				return false;
			}

		private static int sortFileInChunks(Path inputFilePath,
																				long availableHeapSize,
																				long bufferSize)
				throws IOException
			{
				int sortedSubFileCount = 0;
				try (Scanner scanner = new Scanner(inputFilePath,
						SortLargeFileHelper.FILE_ENCODING))
					{
						int stringArraySize = (int) (availableHeapSize
								/ (SortLargeFileHelper.AVERAGE_STRING_MEM_ASSUMPTION
										+ SortLargeFileHelper.STRING_MEM_OVERHEAD_ASSUMPTION));

						String[] buffer = new String[stringArraySize];
						int bytesRead = 0;
						while (scanner.hasNextLine())
							{
								int index = 0;
								for (index = 0; index < buffer.length; index++)
									{
										if (!scanner.hasNextLine()
												|| bytesRead >= availableHeapSize)
											{
												break;
											}

										buffer[index] = scanner.nextLine();
										bytesRead += SortLargeFileHelper.STRING_MEM_OVERHEAD_ASSUMPTION
												+ buffer[index].length()
														* SortLargeFileHelper.TWO_BYTES;

									}
								bytesRead = 0;
								// Time complexitiy N to NwlogR Memory complexity W + logN
								Quick3stringModified.sort(buffer,
																					index);
								String sortedSubFileName = SortLargeFileHelper
										.generateSubFileName(	inputFilePath.toString(),
																					sortedSubFileCount);
								SortLargeFileHelper.writeBufferToSubFile(	buffer,
																													index,
																													bufferSize,
																													sortedSubFileName);
								sortedSubFileCount++;
							}
					}
				return sortedSubFileCount;
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
