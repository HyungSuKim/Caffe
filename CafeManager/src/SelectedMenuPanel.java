import java.awt.Component;

import javax.swing.BorderFactory;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.component.OnClickListener;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.flat.text.textarea.alignment.FlatVerticalAlignment;



@SuppressWarnings("serial")
public class SelectedMenuPanel extends FlatPanel {
//	17.10.24. ¼öÁ¤
//	access modifier fixed
	private OrderMenuFrame om;
	
	private int counter = 1;
	private String cup = "¸Ó±×ÀÜ";
	private String hc = "ÇÖ";
	private String shot = "¾øÀ½";
	
	private FlatLabel nameLabel;
	
	private FlatLabel countLabel = createCountLabel();
	private FlatButton upCountBtn = createUpCountBtn();
	private FlatButton downCountBtn = createDownCountBtn();
	
	private FlatLabel cupLabel = createCupLabel();
	private FlatButton selectCupBtn = createSelectCupBtn();
	
	private FlatLabel hcLabel = createHcLabel();
	private FlatButton choiceHcBtn = createHcChoiceBtn();
	
	private FlatLabel shotLabel = createShotLabel();
	private FlatButton addShotBtn = createAddShotBtn();
	
	private FlatLabel priceLabel;
	
	private String initName;
	private int initPrice = 0;
	private int variablePrice = 0;
	private int COMvariablePrice = 0;
	
	private static GraphicTheme graphicTheme = new GraphicTheme();
	
	public SelectedMenuPanel(String inputName, String inputPrice, int type) {
		initName = inputName;
		initPrice = Integer.parseInt(inputPrice);
		variablePrice = Integer.parseInt(inputPrice);

		nameLabel = createNameLabel();
		priceLabel = createPriceLabel();
		
		setLayout(new LinearLayout(Orientation.HORIZONTAL, 0));
		setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
		
		add(nameLabel, createCommonConstraints(7));
		
		add(countLabel, createCommonConstraints(2));
		add(upCountBtn, createCommonConstraints(1));
		upCountBtn.setOnClickListener(getListenerToUpCount());
		add(downCountBtn, createCommonConstraints(1));
		downCountBtn.setOnClickListener(getListenerToDownCount());
		
		add(cupLabel, createCommonConstraints(3));
		add(selectCupBtn, createCommonConstraints(1));
		selectCupBtn.setOnClickListener(getListenerToSelectCup());
		
		add(hcLabel, createCommonConstraints(3));
		add(choiceHcBtn, createCommonConstraints(1));
		choiceHcBtn.setOnClickListener(getListenerToHcChoice());
		
		add(shotLabel, createCommonConstraints(2));
		add(addShotBtn, createCommonConstraints(1));
		addShotBtn.setOnClickListener(getListenerToAddShot());
		
		add(priceLabel, createCommonConstraints(3));
		
		if(type == 2) {
			
			hcLabel.setText("");
			choiceHcBtn.setEnabled(false);
			choiceHcBtn.setText("");
			choiceHcBtn.setBackground(graphicTheme.getBasicBrownColor());
			
			shotLabel.setText("");
			addShotBtn.setEnabled(false);
			addShotBtn.setText("");
			addShotBtn.setBackground(graphicTheme.getBasicBrownColor());
			
		} else if(type == 3) {
			
			cupLabel.setText("");
			selectCupBtn.setEnabled(false);
			selectCupBtn.setText("");
			selectCupBtn.setBackground(graphicTheme.getBasicBrownColor());
			
			hcLabel.setText("");
			choiceHcBtn.setEnabled(false);
			choiceHcBtn.setText("");
			choiceHcBtn.setBackground(graphicTheme.getBasicBrownColor());
			
			shotLabel.setText("");
			addShotBtn.setEnabled(false);
			addShotBtn.setText("");
			addShotBtn.setBackground(graphicTheme.getBasicBrownColor());
			
		}
	}
	
	///////////////////////////////////////////////////////////////////
	
	void setOnOMPriceLabel(OrderMenuFrame om) {
		om.setVariablePriceOfSCP(this.COMvariablePrice);		
	}
	
	void setOrderMenuFrameOnSCP(OrderMenuFrame om) {
		this.om = om;
	}
	
	String getInitName() {
		return initName;
	}
	
	int getCounter() {
		return counter;
	}
	
	String getCup() {
		return cup;
	}
	
	int getVariablePrice() {
		return variablePrice;
	}
	
	void setCOMvariablePrice(int inputFromOMF) {
		COMvariablePrice = inputFromOMF;
	}
	
	/////////////////////common method////////////////////////
	
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}	
	
	/////////////////gui starts/////////////////////////
	
	private FlatLabel createNameLabel() {
		FlatLabel nameLabel = new FlatLabel();
		
		nameLabel.setBackground(graphicTheme.getBasicBrownColor());
		nameLabel.setText(initName);
		nameLabel.setFont(graphicTheme.getBoldFont15pt());
		
		return nameLabel;
	}
	
	///////////////////////////////////////////////////////////////////
	private FlatLabel createCountLabel() {
		FlatLabel countLabel = new FlatLabel(""+counter);
		
		countLabel.setBackground(graphicTheme.getDarkBrownColor());
		countLabel.setFont(graphicTheme.getBoldFont15pt());
		countLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		countLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		
		return countLabel;
	}
	
	private FlatButton createUpCountBtn() {
		FlatButton upCountBtn = new FlatButton("¡â");
		upCountBtn.setBackground(graphicTheme.getBasicBrownColor());
		return upCountBtn;
	}

	private FlatButton createDownCountBtn() {
		FlatButton downCountBtn = new FlatButton("¡ä");
		downCountBtn.setBackground(graphicTheme.getDarkBrownColor());
		return downCountBtn;
	}

	///////////////////////////////////////////////////////////////////
	
	private FlatLabel createCupLabel() {
		FlatLabel cupLabel = new FlatLabel(cup);
		
		cupLabel.setBackground(graphicTheme.getBasicBrownColor());
		cupLabel.setFont(graphicTheme.getBoldFont15pt());
		cupLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		cupLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		
		return cupLabel;
	}
	
	private FlatButton createSelectCupBtn() {
		FlatButton selectCupBtn = new FlatButton("¡ä");
		
		selectCupBtn.setBackground(graphicTheme.getDarkBrownColor());
		
		return selectCupBtn;
	}

	///////////////////////////////////////////////////////////////////
	
	private FlatLabel createHcLabel() {
		FlatLabel hcLabel = new FlatLabel(hc);
		
		hcLabel.setBackground(graphicTheme.getBasicBrownColor());
		hcLabel.setFont(graphicTheme.getBoldFont15pt());
		hcLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		hcLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		
		return hcLabel;
	}
	
	private FlatButton createHcChoiceBtn() {
		FlatButton hcChoiceBtn = new FlatButton("¡ä");
		
		hcChoiceBtn.setBackground(graphicTheme.getDarkBrownColor());
		
		return hcChoiceBtn;
	}

	///////////////////////////////////////////////////////////////////
	
	private FlatLabel createShotLabel() {
		FlatLabel shotLabel = new FlatLabel(shot);
		
		shotLabel.setBackground(graphicTheme.getBasicBrownColor());
		shotLabel.setFont(graphicTheme.getBoldFont15pt());
		shotLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		shotLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		
		return shotLabel;
	}
	
	private FlatButton createAddShotBtn() {
		FlatButton addShotBtn = new FlatButton("¡ä");
		addShotBtn.setBackground(graphicTheme.getDarkBrownColor());
		return addShotBtn;
	}
	
	private FlatLabel createPriceLabel() {
		FlatLabel priceLabel = new FlatLabel();
		priceLabel.setBackground(graphicTheme.getBasicBrownColor());
		priceLabel.setFont(graphicTheme.getBoldFont15pt());
		priceLabel.setText(initPrice + "£Ü");
		priceLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		priceLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		return priceLabel;
	}
	
	////////////////////Listener//////////////////////

	private OnClickListener getListenerToUpCount() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				counter++;
				countLabel.setText(""+counter);
				variablePrice += initPrice;
				priceLabel.setText(Integer.toString(variablePrice) + "£Ü");
				COMvariablePrice += initPrice;
				setOnOMPriceLabel(om);
			}
		};
	}
	
	private OnClickListener getListenerToDownCount() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				if(counter!=1) {
					counter--;
					countLabel.setText(""+counter);
					variablePrice -= initPrice;
					priceLabel.setText(Integer.toString(variablePrice) + "£Ü");
					COMvariablePrice -= initPrice;
					setOnOMPriceLabel(om);
				}
			}
		};
	}
	
	private OnClickListener getListenerToSelectCup() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				if(cup.equals("¸Ó±×ÀÜ")) {
					cup = "ÀÏÈ¸¿ë";
				} else {
					cup = "¸Ó±×ÀÜ";
				}
				cupLabel.setText(cup);
			}
		};
	}
	
	private OnClickListener getListenerToHcChoice() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				if(hc.equals("ÇÖ")) {
					hc = "¾ÆÀÌ½º";
				} else {
					hc = "ÇÖ";
				}
				hcLabel.setText(hc);
			}
		};
	}
	
	private OnClickListener getListenerToAddShot() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				if(shot.equals("¾øÀ½")) {
					shot = "Ãß°¡";
				} else {
					shot = "¾øÀ½";
				}
				shotLabel.setText(shot);
			}
		};
	}
	
}