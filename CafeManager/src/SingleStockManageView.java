import java.awt.Component;
import java.awt.Window;

import javax.swing.SwingUtilities;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.component.OnClickListener;
import com.mommoo.flat.frame.FlatDialog;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.flat.text.textarea.alignment.FlatVerticalAlignment;
import com.mommoo.flat.text.textfield.FlatTextField;

/**
 * summary : this contains single stock infomation
 * details : this panel contains single stock's name, consumption, remain amount
 *         : and it has function to add(buy) stock
 * arguments : management = mgmt
 * @author 김형수
 *
 */

@SuppressWarnings("serial")
public class SingleStockManageView extends FlatPanel {
//	access modifier fixed
//	stockNameLabel/stockConsumptionLabel/stockRemainLabel에 접근은 있으나
//	setText()와 같은 메서드를 이용해서 접근하므로 괜찮다고 판단.
//	판단 미스... setText 메서드를 이용하더라도 private접근 제어자가 설정된 변수의 내용을 변화시키므로 불가능
//	해결방안은 default를 통해 패키지 내에서 모두 접근이 가능하게 하던가 protected나 public과 같이 더 넓은 범위로 접근범위를 확장시키거나
//  메서드를 만들어서 내가 원하는 방식으로 입력 될 수 있게 해주는것...
//	stockConsumptionLabel/stockRemainLabel은 int자료형을 받아 문자열로 셋팅을 해주는 법칙이 필요하다고생각해서 메서드이용으로 결정
	private FlatLabel stockName = createNameLabel();
	private FlatLabel stockConsumption = createConsumptionLabel();
	private FlatLabel stockRemain = createRemainLabel();
	private FlatTextField stockAdditionField = createAdditionField();
	private FlatButton stockAdditionBtn = createAdditionBtn();
	
	private static GraphicTheme graphicTheme = new GraphicTheme();
	
	SingleStockManageView(int index, OrderHistory orderHistory) { 
		
		setLayout(new LinearLayout(Orientation.HORIZONTAL, 0));
		
		add(stockName, createCommonConstraints(2));
		add(stockConsumption, createCommonConstraints(1));
		add(stockRemain, createCommonConstraints(1));
		add(stockAdditionField, createCommonConstraints(1));
		add(stockAdditionBtn, createCommonConstraints(1));
		
		stockAdditionBtn.setOnClickListener(getListenerToStockAddition(index, orderHistory));
	}
	
	/////////////////////related methods///////////////////////
	
	void setStockName(String inputName) {
		stockName.setText(inputName);
	}
	void setStockConsumption(int inputConsumption) {
		stockConsumption.setText(Integer.toString(inputConsumption));
	}
	void setStockRemain(int inputRemain) {
		stockRemain.setText(Integer.toString(inputRemain));
	}
	
	////////////////////////common constraints////////////////////////////////
	
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}

	/////////////////////////gui/////////////////////////////////
	
	private FlatLabel createNameLabel() {
		stockName = new FlatLabel("");
		
		stockName.setFont(graphicTheme.getBoldFont15pt());
		stockName.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		stockName.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		stockName.setBackground(graphicTheme.getDarkBrownColor());
		
		return stockName;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private FlatLabel createConsumptionLabel() {
		stockConsumption = new FlatLabel("");
		
		stockConsumption.setFont(graphicTheme.getPlainFont15pt());
		stockConsumption.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		stockConsumption.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		stockConsumption.setBackground(graphicTheme.getBasicBrownColor());
		
		return stockConsumption;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private FlatLabel createRemainLabel() {
		stockRemain = new FlatLabel("");
		
		stockRemain.setFont(graphicTheme.getBoldFont15pt());
		stockRemain.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		stockRemain.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		stockRemain.setBackground(graphicTheme.getDarkBrownColor());
		
		return stockRemain;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private FlatTextField createAdditionField() {
		stockAdditionField = new FlatTextField(false);
		
		stockAdditionField.setBackground(graphicTheme.getBasicBrownColor());
		stockAdditionField.setFont(graphicTheme.getPlainFont15pt());
		
		return stockAdditionField;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private FlatButton createAdditionBtn() {
		stockAdditionBtn = new FlatButton("추가");
		
		stockAdditionBtn.setBackground(graphicTheme.getDarkBrownColor());
		
		return stockAdditionBtn;
	}
	
	//////////////////////////Listener and related method//////////////////////
	private OnClickListener getListenerToStockAddition(int index, OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				
				stockAddition(index, orderHistory);
				
			}
		};
	}
	
	private void stockAddition(int index, OrderHistory orderHistory) {
		if(!stockAdditionField.getText().equals("")&&(stockAdditionField.getText().matches("\\d")
				||stockAdditionField.getText().matches("\\d\\d")||stockAdditionField.getText().matches("\\d\\d\\d"))) {
			
			orderHistory.getStockItemsByIndex(index).increase(Integer.parseInt(stockAdditionField.getText()));
			
			stockRemain.setText(Integer.toString(orderHistory.getStockItemsByIndex(index).getAmount()));
			
			stockAdditionField.clear();
			
			showStockAdditionSuccessDialog();
		} else {	}
	}
	
	/////////////////////////dialog///////////////////

	private void showStockAdditionSuccessDialog() {
		Window win = SwingUtilities.getWindowAncestor(stockAdditionBtn);
		
		new FlatDialog.Builder()
		.setTitle("알림")
		.setContent("추가 성공."+System.lineSeparator()+"마지막에 저장 버튼을 꼭 눌러주세요.")
		.setLocationRelativeTo(win)
		.build()
		.show();
	}
}

