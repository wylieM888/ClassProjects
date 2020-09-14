import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.NullPointerException;

abstract class Library {

	private static String[] engList;
	private static String[] spanWords;
	private static String[] gerWords;
	private static String[] freWords;
	private static String[] latWords;
	private static ArrayList<Integer> diction;
	private static ArrayList<String[]> otherLang; 
	private static int numOfLanguages;
	private static ArrayList<String[]> allLang;
	private static int numWords;
	private static int totalNumWords;
	
	public static String[] getEnglishList() {
		return engList;
	}
	
	public static int getNumWords() {
		return numWords;
	}
	
	public static String[] getSpanishWords() {
		return spanWords;
	}
	
	public static String[] getGermanWords() {
		return gerWords;
	}
	
	public static String[] getFrenchWords() {
		return freWords;
	}
	
	public static String[] getLatinWords() {
		return latWords;
	}
	
	public static ArrayList<String[]> getAllLanguages(){
		return allLang;
	}
	
	public static ArrayList<Integer> getDictionary(){
		return diction;
	}
	
	public static int getTotalNumWords() {
		return totalNumWords;
	}
	
	//1 = Spanish, 2 = German, 3 = French, 4 = Latin
	public static void getLangFile(int langNum) throws IOException {
		try {
		if (langNum == 1) {
			numOfLanguages++;
			String canonicalPath = new File(".").getCanonicalPath();
			canonicalPath = canonicalPath + "\\Spanish_Words.txt";
			FileInputStream bookFileInStream = new FileInputStream(canonicalPath);
			Scanner spanScan = new Scanner(bookFileInStream); 
			int x = 0;
			
			while(spanScan.hasNextLine()) {
				String p = spanScan.nextLine();
				x++;
				totalNumWords++;
			}
			
			spanWords = new String[x];
			
			bookFileInStream.close();
			spanScan.close();
			bookFileInStream = new FileInputStream(canonicalPath);
			spanScan = new Scanner(bookFileInStream);
			
			for (int i = 0; i < spanWords.length; i++) {
				spanWords[i] = spanScan.nextLine();
			}
			
			boolean langTest = testLangFile(spanWords);
			
			otherLang.add(spanWords);
			
			if (langTest == true) {
				allLang.add(spanWords);
				diction.add(langNum);
				TestWordGenerator.orderOfLanguages.add("Spanish");
			}
			else {
				System.out.println("The number of Spanish words in the file don't match the English file");
			}
		}
		
		else if (langNum == 2) {
			numOfLanguages++;
			String canonicalPath = new File(".").getCanonicalPath();
			canonicalPath = canonicalPath + "\\German_Words.txt";
			FileInputStream bookFileInStream = new FileInputStream(canonicalPath);
			Scanner gerScan = new Scanner(bookFileInStream); 
			int x = 0;
			
			while(gerScan.hasNextLine()) {
				String p = gerScan.nextLine();
				x++;
				totalNumWords++;
			}
			
			gerWords = new String[x];

			bookFileInStream.close();
			gerScan.close();
			bookFileInStream = new FileInputStream(canonicalPath);
			gerScan = new Scanner(bookFileInStream);
			
			for (int i = 0; i < gerWords.length; i++) {
				gerWords[i] = gerScan.nextLine();
			}
			
			boolean langTest = testLangFile(gerWords);
			
			otherLang.add(gerWords);
			
			if (langTest == true) {
				allLang.add(gerWords);
				diction.add(langNum);
				TestWordGenerator.orderOfLanguages.add("German");
			}
			else {
				System.out.println("The number of German words in the file don't match the English file");
			}
		}
		
		else if (langNum == 3) {
			numOfLanguages++;
			String canonicalPath = new File(".").getCanonicalPath();
			canonicalPath = canonicalPath + "\\French_Words.txt";
			FileInputStream bookFileInStream = new FileInputStream(canonicalPath);
			Scanner freScan = new Scanner(bookFileInStream); 
			int x = 0;
			
			while(freScan.hasNextLine()) {
				String p = freScan.nextLine();
				x++;
				totalNumWords++;
			}
			
			freWords = new String[x];

			bookFileInStream.close();
			freScan.close();
			bookFileInStream = new FileInputStream(canonicalPath);
			freScan = new Scanner(bookFileInStream);
			
			for (int i = 0; i < freWords.length; i++) {
				freWords[i] = freScan.nextLine();
			}
			
			otherLang.add(freWords);
			
			boolean langTest = testLangFile(freWords);
			
			if (langTest == true) {
				allLang.add(freWords);
				diction.add(langNum);
				TestWordGenerator.orderOfLanguages.add("French");
			}
			else {
				System.out.println("The number of French words in the file don't match the English file");
			}
		}
		
		else if (langNum == 4) {
			numOfLanguages++;
			String canonicalPath = new File(".").getCanonicalPath();
			canonicalPath = canonicalPath + "\\Latin_Words.txt";
			FileInputStream bookFileInStream = new FileInputStream(canonicalPath);
			Scanner latScan = new Scanner(bookFileInStream); 
			int x = 0;
			
			while(latScan.hasNextLine()) {
				String p = latScan.nextLine();
				x++;
				totalNumWords++;
			}
			
			latWords = new String[x];

			bookFileInStream.close();
			latScan.close();
			bookFileInStream = new FileInputStream(canonicalPath);
			latScan = new Scanner(bookFileInStream);
			
			for (int i = 0; i < latWords.length; i++) {
				latWords[i] = latScan.nextLine();
			}
			
			boolean langTest = testLangFile(latWords);
			
			otherLang.add(latWords);
			
			if (langTest == true) {
				allLang.add(latWords);
				diction.add(langNum);
				TestWordGenerator.orderOfLanguages.add("Latin");
			}
			else {
				System.out.println("The number of Latin words in the file don't match the English file");
			}
		}
		
		else {
			System.out.println("The number you used could not be used to retrieve a file.");
		}
		}catch(Exception FileNotFoundException) { 
			System.out.println("You need to make sure to import the foreign language files into your package folder.");
		}
	}

	public static int getNumOfLanguages() {
		return numOfLanguages;
	}
	
	public static boolean testLangFile(String[] test) {
		return (numWords == test.length);
	}
	
	public static ArrayList<String[]> getOtherLang(){
		return otherLang;
	}
	
	public static void setEngLanguage() throws IOException {
		try {
		numWords = 0;
		diction = new ArrayList<Integer>();
		otherLang = new ArrayList<String[]>();
		String canonicalPath = new File(".").getCanonicalPath();
		canonicalPath = canonicalPath + "\\English_Words.txt";
		FileInputStream bookFileInStream = new FileInputStream(canonicalPath);
		Scanner engScan = new Scanner(bookFileInStream); 
		
		while(engScan.hasNextLine()) {
			String x = engScan.nextLine();
			numWords++;
		}

		engList = new String[numWords];

		bookFileInStream.close();
		engScan.close();
		bookFileInStream = new FileInputStream(canonicalPath);
		engScan = new Scanner(bookFileInStream);

		for (int i = 0; i < engList.length; i++) {
			engList[i] = engScan.nextLine();
		}

		allLang = new ArrayList<String[]>();
		allLang.add(engList);
		totalNumWords = numWords;
		}catch(Exception FileNotFoundException) {
			System.out.println("You need to make sure to import the English language file into your package folder.");
		}
	}
	
}
