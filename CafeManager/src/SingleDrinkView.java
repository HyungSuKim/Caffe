import javax.swing.BorderFactory;

import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.flat.text.textarea.alignment.FlatVerticalAlignment;

/**
 * summary : this panel contains single drink menu item
 * details : this panel works for OrderMenuFrame by containing single drink menu item
 *         : on OrderMenuFrame this panel add to drinkMenuList
 *         : it contains item name and price
 * @author ±èÇü¼ö
 *
 */

@SuppressWarnings("serial")
class SingleDrinkView extends FlatPanel {
//	17.10.23 ¼öÁ¤
	private FlatLabel name = createNameLabel();
	private FlatLabel price = createPriceLabel();
	
	private static GraphicTheme graphicTheme = new GraphicTheme();
	
	SingleDrinkView() {
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setOpaque(true);
		setBackground(graphicTheme.getDarkBrownColor());
		setLayout(new LinearLayout(Orientation.HORIZONTAL,0));
		
		add(name, createCommonConstraints(3));
		
		add(price, createCommonConstraints(1));
	}
	///////////////////////////related methods///////////////////////////////////
	
	void setSingleDrink(String drinkName, String drinkPrice) {
		name.setText(drinkName);
		price.setText(drinkPrice);
	}
	
	String getDrinkName() {
		return name.getText();
	}
	
	String getDrinkPrice() {
		return price.getText();
	}
	
	///////////////////////////common method///////////////////////////////////
	
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}
		
	/////////////////////////gui/////////////////////////////////////
	
	private FlatLabel createNameLabel() {
		FlatLabel menuNameLabel = new FlatLabel();
		menuNameLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		menuNameLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		menuNameLabel.setFont(graphicTheme.getPlainFont15pt());
		menuNameLabel.setBackground(graphicTheme.getDarkBrownColor());
		return menuNameLabel;
	}
	
	//////////////////////////////////////////////////////////////
	
	private FlatLabel createPriceLabel() {
		FlatLabel menuPriceLabel = new FlatLabel();
		menuPriceLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		menuPriceLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		menuPriceLabel.setFont(graphicTheme.getBoldFont15pt());
		menuPriceLabel.setBackground(graphicTheme.getDarkBrownColor());
		return menuPriceLabel;
	}
}
