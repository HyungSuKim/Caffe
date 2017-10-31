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
 * summary : this panel contains single food menu item
 * details : this panel works for OrderMenuFrame by containing single food menu item
 *         : on OrderMenuFrame this panel add to foodMenuList
 *         : it contains item name and price
 * @author ±èÇü¼ö
 *
 */

@SuppressWarnings("serial")
class SingleFoodView extends FlatPanel {
//	access modifier fixed
	private FlatLabel name = createFoodNameLabel();
	private FlatLabel price = createFoodPriceLabel();
	
	private static GraphicTheme graphicTheme = new GraphicTheme();
	
	SingleFoodView() {
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
		setOpaque(true);
		setBackground(graphicTheme.getDarkBrownColor());
		setLayout(new LinearLayout(Orientation.HORIZONTAL,0));
		
		add(name, createCommonConstraints(3));
		
		add(price, createCommonConstraints(1));
	}
	
	//////////////////related methods/////////////////////////
	
	void setSingleFood(String foodName, String foodPrice) {
		name.setText(foodName);
		price.setText(foodPrice);
	}
	
	String getFoodName() {
		return name.getText();
	}
	
	String getFoodPrice() {
		return price.getText();
	}
	
	//////////////////////common constraints////////////////////////////////
	
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}

	/////////////////////////gui///////////////////////////
	
	private FlatLabel createFoodNameLabel() {
		FlatLabel foodNameLabel = new FlatLabel();
		foodNameLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		foodNameLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		foodNameLabel.setFont(graphicTheme.getPlainFont15pt());
		foodNameLabel.setBackground(graphicTheme.getDarkBrownColor());
		return foodNameLabel;
	}
	
	///////////////////////////////////////////////////////////////////
	
	private FlatLabel createFoodPriceLabel() {
		FlatLabel foodPriceLabel = new FlatLabel();
		foodPriceLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		foodPriceLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		foodPriceLabel.setFont(graphicTheme.getBoldFont15pt());
		foodPriceLabel.setBackground(graphicTheme.getDarkBrownColor());
		return foodPriceLabel;
	}
}