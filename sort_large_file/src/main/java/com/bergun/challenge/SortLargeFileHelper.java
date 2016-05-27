package com.bergun.challenge;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import edu.princeton.cs.algorithms.Queue;

public class SortLargeFileHelper {

    public static final String FILE_EXTENSION_DELIMITER = ".";

    public static final String SUB_FILE_INDEX_SEPARATOR = "_";

    private final static String SORTED_FILE_SUFFIX = "sorted";

    public static final short AVERAGE_LINE_W_ASSUMPTION = 32;

    public static final short AVERAGE_STRING_MEM_ASSUMPTION = 2 * 5;
    // keep the line length short so that we have a large memory
    // to fit in desired amount of bytes. 5 * 2 Bytes

    public static final byte STRING_MEM_OVERHEAD_ASSUMPTION = 40;

    private static final byte QUICKSTRING_MEM_MARGIN_FACTOR = 4;

    private static final byte GC_FACTOR = 2; // Leave some space for Garbage
					     // Collection to catch up

    private static final int SCANNER_BUFFER_SIZE = 1024;

    public static final int TWO_BYTES = 2;

    public static final int MIN_SUPPORTED_AVAILABLE_HEAP_SIZE = 4194304;

    public final static String FILE_ENCODING = "UTF-8";

    private static final String FILE_NOT_FOUND = "%s file does not exist!";

    public static long calculateAvailableHeapSize(long heapSize) {
	long quick3wayStrSortMemorySize = QUICKSTRING_MEM_MARGIN_FACTOR
		* (((long) (Math.log(heapSize) / Math.log(2))) + AVERAGE_LINE_W_ASSUMPTION);
	long availableHeapSize = heapSize - quick3wayStrSortMemorySize - SCANNER_BUFFER_SIZE;
	availableHeapSize = availableHeapSize / GC_FACTOR;
	if (availableHeapSize < MIN_SUPPORTED_AVAILABLE_HEAP_SIZE) {
	    throw new HeapSizeNotSupportedException(String.format(HeapSizeNotSupportedException.ERROR_MSG,
		    MIN_SUPPORTED_AVAILABLE_HEAP_SIZE, availableHeapSize));
	}
	return availableHeapSize;
    }

    public static int calculateMergeBufferCount(long filesize, long availableHeapSize) {
	int javaOverHeads = (STRING_MEM_OVERHEAD_ASSUMPTION + AVERAGE_STRING_MEM_ASSUMPTION)
		/ AVERAGE_STRING_MEM_ASSUMPTION;
	int expectedInputBufferCount = (int) Math.ceil((double) (filesize * javaOverHeads) / (double) availableHeapSize)
		+ 1; // +1 for small subfile differences
	int outputBufferCount = 1;
	return expectedInputBufferCount + outputBufferCount;
    }

    public static long getCurrentHeapSize() {
	return ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getCommitted()
		- (2 * ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getUsed());
    }

    public static String getProcessId() {
	return ManagementFactory.getRuntimeMXBean().getName();
    }

    public static void checkIfFileExists(Path path) throws FileNotFoundException {
	if (!Files.exists(path)) {
	    throw new FileNotFoundException(String.format(FILE_NOT_FOUND, path.getFileName()));
	}
    }

    public static void writeBufferToSubFile(String[] lines, int hi, long bufferSize, String sortedSubFileName)
	    throws FileNotFoundException, UnsupportedEncodingException {
	PrintWriter writer = null;
	try {
	    int sortedSubFileChunkCount = 0;
	    writer = new PrintWriter(
		    SortLargeFileHelper.generateSubFileName(sortedSubFileName, sortedSubFileChunkCount), FILE_ENCODING);
	    long bytesRead = 0;
	    for (int i = 0; i < hi; i++) {
		writer.println(lines[i]);
		bytesRead += SortLargeFileHelper.STRING_MEM_OVERHEAD_ASSUMPTION + lines[i].length() * TWO_BYTES;
		if (bytesRead >= bufferSize) {

		    bytesRead = 0;
		    sortedSubFileChunkCount++;
		    writer.flush();
		    writer.close();		    
		    writer = new PrintWriter(
			    SortLargeFileHelper.generateSubFileName(sortedSubFileName, sortedSubFileChunkCount));
		}
	    }
	} finally {
	    if (null != writer) {
		writer.flush();
		writer.close();		
	    }
	}

    }

    public static void writeQueueBufferToFile(Queue<String> buffer, String fileName) throws IOException {
	try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
	    while (!buffer.isEmpty()) {
		writer.println((buffer.dequeue()));
	    }
	}
    }

    public static void readFileToQueueBuffer(Path path, Queue<String> buffer) throws IOException

    {
	try (Scanner scanner = new Scanner(path)) {
	    while (scanner.hasNextLine()) {
		String line = scanner.nextLine();

		buffer.enqueue(line);
	    }
	}

    }

    public static String generateSubFileName(String inputFileName, int subFileIndex) {

	StringBuilder builder = new StringBuilder(inputFileName);
	int index = builder.indexOf(FILE_EXTENSION_DELIMITER);
	if (index != -1) {
	    builder.replace(index, index + 1, SUB_FILE_INDEX_SEPARATOR + subFileIndex + FILE_EXTENSION_DELIMITER);
	} else {
	    builder.append(SUB_FILE_INDEX_SEPARATOR + subFileIndex);
	}
	return builder.toString();

    }

    public static Path generateOutputFilePath(Path inputFilePath) {

	StringBuilder builder = new StringBuilder(inputFilePath.toString());

	builder.append(SUB_FILE_INDEX_SEPARATOR + getProcessId() + SUB_FILE_INDEX_SEPARATOR + SORTED_FILE_SUFFIX);

	return Paths.get(builder.toString());

    }

    public static void cleanUpSubFiles(String filename, int sortedSubFileCount, int sortedSubChunkFileCount)
	    throws IOException {
	for (int i = 0; i < sortedSubFileCount; i++) {
	    for (int j = 0; j < sortedSubChunkFileCount; j++) {
		String sortedSubFileName = generateSubFileName(filename, i);
		String sortedSubChunkFileName = generateSubFileName(sortedSubFileName, j);
		Path sortedSubChunkFilePath = Paths.get(sortedSubChunkFileName);
		Files.deleteIfExists(sortedSubChunkFilePath);
	    }
	}

    }
}
