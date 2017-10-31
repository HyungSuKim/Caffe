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
 * summary : it contains single sell history
 * details : it contains single sell history to add SellHistory list
 *         : single sell history contains buyer and purchase price
 * @author ±èÇü¼ö
 *
 */
@SuppressWarnings("serial")
public class SingleSellHistoryView extends FlatPanel{
//	17.10.24 ¼öÁ¤
	
	private static GraphicTheme graphicTheme = new GraphicTheme();
	
	SingleSellHistoryView(String buyer, String price) {
		
		setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
		setLayout(new LinearLayout(Orientation.HORIZONTAL, 0));
		
		add(createBuyerView(buyer), createCommonConstraints());
		
		add(createPriceView(price), createCommonConstraints());
	}
	
	////////////////////common constraints/////////////////////////////
	
	private LinearConstraints createCommonConstraints() {
		return new LinearConstraints().setWeight(1).setLinearSpace(LinearSpace.MATCH_PARENT);
	}

	/////////////////////////////////////////////////////////////////////
	
	private FlatLabel createBuyerView(String buyer) {
		FlatLabel buyerLabel = new FlatLabel();
		
		buyerLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		buyerLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		buyerLabel.setFont(graphicTheme.getBoldFont15pt());
		buyerLabel.setBackground(graphicTheme.getDarkBrownColor());
		buyerLabel.setText(buyer);
		
		return buyerLabel;
	}
	
	/////////////////////////////////////////////////////////////////////
	
	private FlatLabel createPriceView(String price) {
		FlatLabel priceLabel = new FlatLabel();
		
		priceLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		priceLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		priceLabel.setFont(graphicTheme.getBoldFont15pt());
		priceLabel.setBackground(graphicTheme.getDarkBrownColor());
		priceLabel.setText(price + "£Ü");
		
		return priceLabel;
	}
}
