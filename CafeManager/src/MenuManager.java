import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MenuManager {
	private File drinkFile = new File("Drink.txt");
	private File foodFile = new File("Food.txt");

	private String[] drinkContents;
	private String[] foodContents;

	MenuManager() {
		try {
			FileInputStream drinkInputStream = new FileInputStream(drinkFile);
			FileInputStream foodInputStream = new FileInputStream(foodFile);

			byte[] drinkInputBuffer = new byte[(int) drinkFile.length()];
			byte[] foodInputBuffer = new byte[(int) foodFile.length()];
			drinkInputStream.read(drinkInputBuffer);
			foodInputStream.read(foodInputBuffer);
			
			drinkContents = new String(drinkInputBuffer).split(System.lineSeparator());
			foodContents = new String(foodInputBuffer).split(System.lineSeparator());

			drinkInputStream.close();
			foodInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	String[] getDrinkContents() {
		return drinkContents;
	}
	
	String[] getFoodContents() {
		return foodContents;
	}
	
	static String[] contentSpliter(String selectedContent) {
		String[] splitedContent = selectedContent.split("/");
		return splitedContent;
	}
}
