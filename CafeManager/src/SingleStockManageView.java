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
 * @author ������
 *
 */

@SuppressWarnings("serial")
public class SingleStockManageView extends FlatPanel {
//	access modifier fixed
//	stockNameLabel/stockConsumptionLabel/stockRemainLabel�� ������ ������
//	setText()�� ���� �޼��带 �̿��ؼ� �����ϹǷ� �����ٰ� �Ǵ�.
//	�Ǵ� �̽�... setText �޼��带 �̿��ϴ��� private���� �����ڰ� ������ ������ ������ ��ȭ��Ű�Ƿ� �Ұ���
//	�ذ����� default�� ���� ��Ű�� ������ ��� ������ �����ϰ� �ϴ��� protected�� public�� ���� �� ���� ������ ���ٹ����� Ȯ���Ű�ų�
//  �޼��带 ���� ���� ���ϴ� ������� �Է� �� �� �ְ� ���ִ°�...
//	stockConsumptionLabel/stockRemainLabel�� int�ڷ����� �޾� ���ڿ��� ������ ���ִ� ��Ģ�� �ʿ��ϴٰ�����ؼ� �޼����̿����� ����
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
		stockAdditionBtn = new FlatButton("�߰�");
		
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
		.setTitle("�˸�")
		.setContent("�߰� ����."+System.lineSeparator()+"�������� ���� ��ư�� �� �����ּ���.")
		.setLocationRelativeTo(win)
		.build()
		.show();
	}
}

