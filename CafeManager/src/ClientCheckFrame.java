import java.awt.Component;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.component.OnClickListener;
import com.mommoo.flat.frame.FlatDialog;
import com.mommoo.flat.frame.FlatFrame;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.flat.text.textarea.alignment.FlatVerticalAlignment;
import com.mommoo.flat.text.textfield.FlatTextField;
import com.mommoo.util.ScreenManager;

/**
 * summary : It check is member or not 
 *         : ������� �ƴ��� Ȯ���Ѵ�.
 * details : getListenerToClientCheck() is most important method
 *         : it 
 *         : 
 * arguments : hand phone number = hpNum 
 * @author ������
 *
 */

public class ClientCheckFrame {
//	17.10.23 ����
	private OrderHistory orderHistory;
	
	private FlatFrame frame = createFrame();
	private FlatTextField mpNumField = createMpNumField();
	private FlatButton checkButton = createCheckButton();
	
	private RelatedPersonFileManagement rpfm;
	private static GraphicTheme graphicTheme = new GraphicTheme();

	ClientCheckFrame(OrderHistory orderHistory, RelatedPersonFileManagement rpfm) {
		this.rpfm = rpfm;
		this.orderHistory = orderHistory;

		frame.getContainer().add(createMpNumInputView(), createCommonConstraints(1));
		
		frame.getContainer().add(createButtonPanel(), createCommonConstraints(1));
		checkButton.setOnClickListener(getListenerToCheckIsClient());
		
		frame.show();
	}
	
	//////////Common methods////////////
	
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}
	
	private FlatDialog.Builder createCommonBulider() {
		return new FlatDialog.Builder()
		.setTitle("�˸�")
		.setLocationRelativeTo(frame.getJFrame());
	}
	
	///////////////////////////////////Gui start///////////////////////////////////
	///////////Frame create(setting)////////////
	
	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();
		
		frame.setTitle("ȸ�� Ȯ��");
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		frame.setSize(frameSM.dip2px(250), frameSM.dip2px(100));
		frame.setWindowExit(false);
		frame.setLocationOnScreenCenter();
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		
		return frame;
	}
	
	///////////MpNumInputView create(setting) - contain 2 panels//////////////
	
	private FlatPanel createMpNumInputView() {
		FlatPanel mpNumPanel = new FlatPanel(new LinearLayout(0));
		FlatPanel mpNumLabelPanel = createMpNumLabelPanel();
		
		mpNumPanel.add(mpNumLabelPanel, createCommonConstraints(2));
		mpNumLabelPanel.add(createMpNumLabel(), createCommonConstraints(3));
		mpNumLabelPanel.add(createMpNumGuideLabel(), createCommonConstraints(2));
		mpNumPanel.add(mpNumField, createCommonConstraints(4));
		
		return mpNumPanel;
	}
	
	private FlatPanel createMpNumLabelPanel() {
		return new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
	}
	
	private FlatLabel createMpNumLabel() {
		FlatLabel mpNumLabel = new FlatLabel("�ڵ��� ��ȣ");
		
		mpNumLabel.setFont(graphicTheme.getBoldFont15pt());
		mpNumLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		mpNumLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		mpNumLabel.setBackground(graphicTheme.getDarkBrownColor());
		
		return mpNumLabel;
	}
	
	private FlatLabel createMpNumGuideLabel() {
		FlatLabel mpNumGuideLabel = new FlatLabel("(��� : 010-0000-0000)");
		mpNumGuideLabel.setFont(graphicTheme.getPlainFont10pt());
		mpNumGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		mpNumGuideLabel.setBackground(graphicTheme.getDarkBrownColor());
		return mpNumGuideLabel;
	}
	
	private FlatTextField createMpNumField() {
		FlatTextField mpNumField = new FlatTextField(false);
		mpNumField.setBackground(graphicTheme.getLightBrownColor());
		mpNumField.setFont(graphicTheme.getPlainFont15pt());
		return mpNumField;
	}
	
	/////////////ButtonPanel create(setting) - contain 2 buttons//////////////
	
	private FlatPanel createButtonPanel() {
		FlatPanel buttonPanel = new FlatPanel(new LinearLayout(0));
		FlatButton exitButton = createExitButton();
		
		buttonPanel.add(checkButton, createCommonConstraints(1));
		buttonPanel.add(exitButton, createCommonConstraints(1));
		exitButton.setOnClickListener(getListenerToExitFrame());
		return buttonPanel;
	}
	
	private FlatButton createCheckButton() {
		FlatButton okButton = new FlatButton("Ȯ��");
		okButton.setBackground(graphicTheme.getSkyBlueColor());
		return okButton;
	}
	
	private FlatButton createExitButton() {
		FlatButton cancelButton = new FlatButton("���");
		cancelButton.setBackground(graphicTheme.getPinkColor());
		return cancelButton;
	}
	
	///////////////////////Listener and related methods//////////////////////////////////
	
//	������ �ƴ��� üũ�Ѵ�
	private OnClickListener getListenerToCheckIsClient() {
		return new OnClickListener() {			
			@Override
			public void onClick(Component component) {
				String mpNum = mpNumField.getText();
				
				if (rpfm.getClientHashMap().containsKey(mpNum)) {
					showClientCheckSuccessDialog(mpNum);
				} else {
					showClientCheckFailDialog();
				}
			}
		};
	}
	
//	���� �ֹ�â�� ����ְ� ������ �������� �ݴ´�
	private OnClickListener getListenerToShowOrderMenuFrame(String mpNum) {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				new OrderMenuFrame(mpNum, orderHistory);
				frame.hide();
			}
		};
	}
	
//	������ ������
	private OnClickListener getListenerToExitFrame() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				frame.hide();
			}
		};
	}
	
	//////////////////////Dialog starts/////////////////////////////
//	�� ������ �����Ѵٸ� ���� �ֹ�â�� ����ش�
	private void showClientCheckSuccessDialog(String mpNum) {
		createCommonBulider()
		.setContent("ȸ�� ������ �����Ͽ����ϴ�.")
		.setOnClickListener(getListenerToShowOrderMenuFrame(mpNum))
		.build()
		.show();
	}

	private void showClientCheckFailDialog() {
		createCommonBulider()
		.setContent("ȸ�� ���� ����. �ٽ� �õ����ּ���.")
		.build()
		.show();
	}
}
