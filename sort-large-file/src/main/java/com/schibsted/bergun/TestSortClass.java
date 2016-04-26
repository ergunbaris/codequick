package com.schibsted.bergun;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TestSortClass
	{
		public static void main(String[] args) throws IOException
			{
				List<String> lines = new ArrayList<>();
				try (Scanner scanner = new Scanner(Paths.get(args[0])))
					{

						while (scanner.hasNextLine())
							{
								String line = scanner.nextLine();

								lines.add(line);

							}
					}
				String[] array = lines.toArray(new String[0]);
				Arrays.sort(array);
				String sortFileName = args[0] + SortLargeFileHelper.getProcessId()
						+ "_sort";
				try (PrintWriter pw = new PrintWriter(
						new FileWriter(sortFileName, true)))
					{
						for (String s : array)
							{
								pw.println(s);
							}

					}
			}
	}
