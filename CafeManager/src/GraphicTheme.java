import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import com.mommoo.util.FontManager;

class GraphicTheme {
	private static final Image COFFEE_IMAGE = Toolkit.getDefaultToolkit().getImage("C:\\Users\\±èÇü¼ö\\eclipse-workspace\\CafeManager\\lib\\mainTitle.jpg");
	
	private static final Color TITLE_BAR_COLOR = new Color(185, 155, 115);
	
	private static final Color DIVIDER_COLOR = new Color(126, 105, 77);
	
	private static final Color DARKER_BROWN_COLOR = new Color(102, 93, 18);
	private static final Color DARK_BROWN_COLOR = new Color(190, 160, 120);
	private static final Color BASIC_BROWN_COLOR = new Color(210, 180, 140);
	private static final Color LIGHT_BROWN_COLOR = new Color(225, 195, 155);
	
	private static final Color PINK_COLOR = Color.PINK;
	private static final Color SKY_BLUE_COLOR = new Color(135, 206, 250);
	
	private static final Font MAIN_LABEL_FONT = FontManager.getNanumGothicFont(Font.BOLD, 50);
	private static final Font MAIN_BUTTON_FONT = FontManager.getNanumGothicFont(Font.BOLD, 40);
	
	private static final Font PLAIN_FONT_20PT = FontManager.getNanumGothicFont(Font.PLAIN, 20);
	private static final Font PLAIN_FONT_15PT = FontManager.getNanumGothicFont(Font.PLAIN, 15);
	private static final Font PLAIN_FONT_10PT = FontManager.getNanumGothicFont(Font.PLAIN, 10);
	private static final Font BOLD_FONT_20PT = FontManager.getNanumGothicFont(Font.BOLD, 20);
	private static final Font BOLD_FONT_15PT = FontManager.getNanumGothicFont(Font.BOLD, 15);
	
	Image getCoffeeImage() {
		return COFFEE_IMAGE;
	}
	
	Color getTitleBarColor() {
		return TITLE_BAR_COLOR;
	}
	
	Color getDividerColor() {
		return DIVIDER_COLOR;
	}
	
	Color getDarkerBrownColor() {
		return DARKER_BROWN_COLOR;
	}
	
	Color getDarkBrownColor() {
		return DARK_BROWN_COLOR;
	}
	
	Color getBasicBrownColor() {
		return BASIC_BROWN_COLOR;
	}
	
	Color getLightBrownColor() {
		return LIGHT_BROWN_COLOR;
	}

	Color getPinkColor() {
		return PINK_COLOR;
	}
	
	Color getSkyBlueColor() {
		return SKY_BLUE_COLOR;
	}
	
	Font getMainLabelFont() {
		return MAIN_LABEL_FONT;
	}
	
	Font getMainButtonFont() {
		return MAIN_BUTTON_FONT;
	}
	
	Font getPlainFont20pt() {
		return PLAIN_FONT_20PT;
	}
	
	Font getPlainFont15pt() {
		return PLAIN_FONT_15PT;
	}
	
	Font getPlainFont10pt() {
		return PLAIN_FONT_10PT;
	}
	
	Font getBoldFont15pt() {
		return BOLD_FONT_15PT;
	}
	
	Font getBoldFont20pt() {
		return BOLD_FONT_20PT;
	}
}
