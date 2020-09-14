import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public abstract class TestWordGenerator {



	public static String testWord, correctAnswer, providedLanguage, testLanguage;
	public static ArrayList<String> orderOfLanguages;
	public static ArrayList<String> usedWords;

	//Math.random() * (max - min + 1) + min
	public static void setTestWords() {

		boolean testIfUsed = false;

		int randomEngOrOther = 0, randomLangNum = 0, randomWord = 0;

		//while (testIfUsed == false) {

		randomLangNum = (int)(Math.random() * ((Library.getOtherLang().size() - 1) - 1 + 1) + 1);
		if (Library.getOtherLang().size() == 1) {
			randomLangNum = 0;


			randomEngOrOther = (int)(Math.random() * (2 - 1 + 1) + 1);
			randomWord = (int)(Math.random() * ((Library.getEnglishList().length - 1) - 1 + 1) + 1);
		//	testIfUsed = testIfWordUsed(randomWord, randomLangNum);
		}
		//}
		
		if (randomEngOrOther == 1) {

			providedLanguage = "English";
			testWord = Library.getEnglishList()[randomWord];

			testLanguage = orderOfLanguages.get(randomLangNum);
			correctAnswer = Library.getOtherLang().get(randomLangNum)[randomWord]; 


		}

		else if (randomEngOrOther == 2) {

			providedLanguage = orderOfLanguages.get(randomLangNum);
			testWord = Library.getOtherLang().get(randomLangNum)[randomWord]; 

			testLanguage =  "English";
			correctAnswer = Library.getEnglishList()[randomWord];

		}
		usedWords.add(testWord);
	}



	public static String getTestWord() {
		return testWord;
	}

	public static String getCorrectAnswer() {
		return correctAnswer;
	}

	public static String getProvidedLanguage() {
		return providedLanguage;
	}

	public static String getTestLanguage() {
		return testLanguage;
	}

	public static ArrayList<String> getOrderOfLanguages() {
		return orderOfLanguages;
	}

	public static void initializeArrayLists() {
		orderOfLanguages = new ArrayList<String>(0);
		usedWords = new ArrayList<String>(4);
	}

	public static boolean testIfWordUsed(int randomWord, int randomLangNum) {

		for (int i = 0; i < usedWords.size(); i++) {
			if (usedWords.get(i).equalsIgnoreCase(Library.getEnglishList()[randomWord]) || (usedWords.get(i).equalsIgnoreCase(Library.getOtherLang().get(randomLangNum)[randomWord]))) {
				System.out.println("The list contains the words.");
				return false;
			}
		}
		return true;

	}

}
