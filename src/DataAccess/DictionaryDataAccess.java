package DataAccess;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryDataAccess {

	private static ArrayList<String> words;

	public DictionaryDataAccess() {
		initialize();
	}

	private void initialize() {
		words = new ArrayList<String>();

		//Add code here to read in the dictionary file.

	}

 
	public static boolean validateWordInDictionary(String word) {
		//This needs to be implemented.
		return true;
	}

}