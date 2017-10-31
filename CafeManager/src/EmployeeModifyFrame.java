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
 * summary : in this frame you can change type and pay of hired employee details
 * : get employee info by input hpNum : and can change type or pay in arguments
 * arguments : argument = arg : employee = emp : mobile phone number = mpNum :
 * button = btn
 * 
 * @author 김형수
 *
 */

class EmployeeModifyFrame {
	// 17.10.23 수정
	private FlatFrame frame = createFrame();

	private FlatLabel currentType = createCurrnetType();
	private FlatLabel currentPay = createCurrnetPay();

	private FlatTextField mpNumField = createMpNumField();
	private FlatTextField typeField = createTypeField();
	private FlatTextField payField = createPayField();

	private static GraphicTheme graphicTheme = new GraphicTheme();
	private RelatedPersonFileManagement rpfm;

	EmployeeModifyFrame(RelatedPersonFileManagement rpfm) {
		this.rpfm = rpfm;
		employeeExistCheck();

		FlatButton getEmployeeInfoBtn = createGetEmployeeInfoBtn();

		frame.getContainer().add(createMpNumInputPanel(getEmployeeInfoBtn), createCommonConstraints(1));
		getEmployeeInfoBtn.setOnClickListener(getListenerToGetEmployee());

		frame.getContainer().add(createEmployeeTypeField(), createCommonConstraints(1));

		frame.getContainer().add(createEmployeePayField(), createCommonConstraints(1));

		frame.getContainer().add(createButtonPanel(), createCommonConstraints(1));

		frame.show();
	}
	///////////////////// Common method////////////////////////

	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}

	private FlatDialog.Builder createCommonBuilder() {
		return new FlatDialog.Builder().setTitle("알림").setLocationRelativeTo(frame.getJFrame());
	}

	///////////////////////// Gui start///////////////////////////
	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();

		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		frame.setTitle("직원 형태/급여변경");
		frame.setSize(frameSM.dip2px(250), frameSM.dip2px(185));
		frame.setLocationOnScreenCenter();
		frame.setWindowExit(false);
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));

		return frame;
	}

	///////////////////////////////////////////////////////////////////////////

	private FlatPanel createMpNumInputPanel(FlatButton getEmployeeInfoBtn) {
		FlatPanel mpNumPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));

		FlatPanel hpNumLabelPanel = createHpNumLabelPanel();

		mpNumPanel.add(hpNumLabelPanel, createCommonConstraints(1));
		hpNumLabelPanel.add(createMpNumLabel(), createCommonConstraints(3));
		hpNumLabelPanel.add(createMpNumGuideLabel(), createCommonConstraints(2));

		mpNumPanel.add(mpNumField, createCommonConstraints(2));

		mpNumPanel.add(getEmployeeInfoBtn, createCommonConstraints(1));

		return mpNumPanel;
	}

	private FlatPanel createHpNumLabelPanel() {
		return new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
	}

	private FlatLabel createMpNumLabel() {
		FlatLabel mpNumLabel = new FlatLabel("핸드폰 번호");

		mpNumLabel.setFont(graphicTheme.getBoldFont15pt());
		mpNumLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		mpNumLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		mpNumLabel.setBackground(graphicTheme.getDarkBrownColor());

		return mpNumLabel;
	}

	private FlatLabel createMpNumGuideLabel() {
		FlatLabel mpNumGuideLabel = new FlatLabel("(양식 : 010-0000-0000)");

		mpNumGuideLabel.setFont(graphicTheme.getPlainFont10pt());
		mpNumGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		mpNumGuideLabel.setBackground(graphicTheme.getDarkBrownColor());

		return mpNumGuideLabel;
	}

	private FlatTextField createMpNumField() {
		FlatTextField hpNumField = new FlatTextField(false);
		hpNumField.setBackground(graphicTheme.getBasicBrownColor());
		hpNumField.setFont(graphicTheme.getPlainFont15pt());
		return hpNumField;
	}

	private FlatButton createGetEmployeeInfoBtn() {
		FlatButton getEmployeeInfoBtn = new FlatButton("불러오기");
		getEmployeeInfoBtn.setBackground(graphicTheme.getDarkBrownColor());
		return getEmployeeInfoBtn;
	}
	
	///////////////////////////////////////////////////////////////////////////

	private FlatPanel createEmployeeTypeField() {
		FlatPanel typePanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatPanel presentTypeLabelPanel = createPresentTypeLabelPanel();
		FlatPanel changeTypeLabelPanel = createChangeTypeLabelPanel();

		typePanel.add(presentTypeLabelPanel, createCommonConstraints(1));
		presentTypeLabelPanel.add(createCurrentTypeLabel(), createCommonConstraints(3));
		presentTypeLabelPanel.add(createCurrentTypeGuideLabel(), createCommonConstraints(2));
		typePanel.add(currentType, createCommonConstraints(1));

		typePanel.add(changeTypeLabelPanel, createCommonConstraints(1));
		changeTypeLabelPanel.add(createChangeTypeLabel(), createCommonConstraints(3));
		changeTypeLabelPanel.add(createChangeTypeGuideLabel(), createCommonConstraints(2));
		typePanel.add(typeField, createCommonConstraints(1));

		return typePanel;
	}

	private FlatPanel createPresentTypeLabelPanel() {
		return new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
	}

	private FlatPanel createChangeTypeLabelPanel() {
		return new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
	}

	private FlatLabel createCurrentTypeLabel() {
		FlatLabel currentTypeLabel = new FlatLabel("근무형태");

		currentTypeLabel.setFont(graphicTheme.getBoldFont15pt());
		currentTypeLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		currentTypeLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		currentTypeLabel.setBackground(graphicTheme.getBasicBrownColor());

		return currentTypeLabel;
	}

	private FlatLabel createCurrentTypeGuideLabel() {
		FlatLabel currentTypeGuideLabel = new FlatLabel("(양식 : 알바 / 직원 )");

		currentTypeGuideLabel.setFont(graphicTheme.getPlainFont10pt());
		currentTypeGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		currentTypeGuideLabel.setBackground(graphicTheme.getBasicBrownColor());

		return currentTypeGuideLabel;
	}

	private FlatLabel createCurrnetType() {
		FlatLabel presentTypeContent = new FlatLabel();

		presentTypeContent.setBackground(graphicTheme.getLightBrownColor());
		presentTypeContent.setFont(graphicTheme.getBoldFont15pt());
		presentTypeContent.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		presentTypeContent.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);

		return presentTypeContent;
	}

	private FlatLabel createChangeTypeLabel() {
		FlatLabel changeTypeLabel = new FlatLabel("변경형태");

		changeTypeLabel.setFont(graphicTheme.getBoldFont15pt());
		changeTypeLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		changeTypeLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		changeTypeLabel.setBackground(graphicTheme.getBasicBrownColor());

		return changeTypeLabel;
	}

	private FlatLabel createChangeTypeGuideLabel() {
		FlatLabel changeTypeGuideLabel = new FlatLabel("(기존 유지 : 공백)");

		changeTypeGuideLabel.setFont(graphicTheme.getPlainFont10pt());
		changeTypeGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		changeTypeGuideLabel.setBackground(graphicTheme.getBasicBrownColor());

		return changeTypeGuideLabel;
	}

	private FlatTextField createTypeField() {
		FlatTextField typeField = new FlatTextField(false);

		typeField.setBackground(graphicTheme.getLightBrownColor());
		typeField.setFont(graphicTheme.getPlainFont15pt());

		return typeField;
	}

	///////////////////////////////////////////////////////////////////////////

	private FlatPanel createEmployeePayField() {
		FlatPanel payPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatPanel presentPayLabelPanel = createPresentPayLabelPanel();
		FlatPanel changePayLabelPanel = createChangePayLabelPanel();

		payPanel.add(presentPayLabelPanel, createCommonConstraints(1));
		presentPayLabelPanel.add(createCurrentPayLabel(), createCommonConstraints(2));
		presentPayLabelPanel.add(createCurrentPayGuideLabel1(), createCommonConstraints(1));
		presentPayLabelPanel.add(createCurrentPayGuideLabel2(), createCommonConstraints(1));
		payPanel.add(currentPay, createCommonConstraints(1));

		payPanel.add(changePayLabelPanel, createCommonConstraints(1));
		changePayLabelPanel.add(createChangePayLabel(), createCommonConstraints(3));
		changePayLabelPanel.add(createChangePayGuideLabel(), createCommonConstraints(2));
		payPanel.add(payField, createCommonConstraints(1));

		return payPanel;
	}

	private FlatPanel createPresentPayLabelPanel() {
		return new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
	}

	private FlatPanel createChangePayLabelPanel() {
		return new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
	}

	private FlatLabel createCurrentPayLabel() {
		FlatLabel currentPayLabel = new FlatLabel("급여");

		currentPayLabel.setFont(graphicTheme.getBoldFont15pt());
		currentPayLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		currentPayLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		currentPayLabel.setBackground(graphicTheme.getDarkBrownColor());

		return currentPayLabel;
	}

	private FlatLabel createCurrentPayGuideLabel1() {
		FlatLabel presentPayGuideLabel1 = new FlatLabel("(단위 : 만원)");

		presentPayGuideLabel1.setFont(graphicTheme.getPlainFont10pt());
		presentPayGuideLabel1.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		presentPayGuideLabel1.setBackground(graphicTheme.getDarkBrownColor());

		return presentPayGuideLabel1;
	}

	private FlatLabel createCurrentPayGuideLabel2() {
		FlatLabel currentPayGuideLabel2 = new FlatLabel("(양식 : 00 ~ 000)");

		currentPayGuideLabel2.setFont(graphicTheme.getPlainFont10pt());
		currentPayGuideLabel2.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		currentPayGuideLabel2.setBackground(graphicTheme.getDarkBrownColor());

		return currentPayGuideLabel2;
	}

	private FlatLabel createCurrnetPay() {
		FlatLabel presentPayContent = new FlatLabel();
		presentPayContent.setBackground(graphicTheme.getBasicBrownColor());
		presentPayContent.setFont(graphicTheme.getBoldFont15pt());
		presentPayContent.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		presentPayContent.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		return presentPayContent;
	}

	private FlatLabel createChangePayLabel() {
		FlatLabel changePayLabel = new FlatLabel("변경급여");

		changePayLabel.setFont(graphicTheme.getBoldFont15pt());
		changePayLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		changePayLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		changePayLabel.setBackground(graphicTheme.getDarkBrownColor());

		return changePayLabel;
	}

	private FlatLabel createChangePayGuideLabel() {
		FlatLabel changePayGuideLabel = new FlatLabel("(기존 유지 : 공백)");

		changePayGuideLabel.setFont(graphicTheme.getPlainFont10pt());
		changePayGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		changePayGuideLabel.setBackground(graphicTheme.getDarkBrownColor());

		return changePayGuideLabel;
	}

	private FlatTextField createPayField() {
		FlatTextField payField = new FlatTextField(false);
		payField.setBackground(graphicTheme.getBasicBrownColor());
		payField.setFont(graphicTheme.getPlainFont15pt());
		return payField;
	}

	///////////////////////////////////////////////////////////////////////////

	private FlatPanel createButtonPanel() {
		FlatPanel buttonPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatButton modifyButton = createModifyButton();
		FlatButton exitButton = createExitButton();

		buttonPanel.add(modifyButton, createCommonConstraints(1));
		modifyButton.setOnClickListener(getListenerToModify());

		buttonPanel.add(exitButton, createCommonConstraints(1));
		exitButton.setOnClickListener(getListenerToExit());

		return buttonPanel;
	}

	private FlatButton createModifyButton() {
		FlatButton changeButton = new FlatButton("확인");
		changeButton.setBackground(graphicTheme.getSkyBlueColor());
		return changeButton;
	}
	
	private FlatButton createExitButton() {
		FlatButton exitButton = new FlatButton("취소");
		exitButton.setBackground(graphicTheme.getPinkColor());
		return exitButton;
	}
	
	//////////////////Listener and related methods/////////////////////////
	
	private OnClickListener getListenerToGetEmployee() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				getEmployeeByInputMpNum();
			}
		};
	}
	

	private void getEmployeeByInputMpNum() {
		String mpNum = mpNumField.getText();

		if (rpfm.getEmployeeHashMap().containsKey(mpNum)) {
			Employee employee = rpfm.getEmployeeHashMap().get(mpNum);
			currentType.setText(employee.getType());

			currentPay.setText(employee.getPay());

		} else {
			showEmployeeNotFoundDialog();
		}

	}

	private OnClickListener getListenerToModify() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				employeeModifiy();
			}
		};
	}
	
	private void employeeModifiy() {

		String mpNum = mpNumField.getText();
		String type = typeField.getText();
		String pay = payField.getText();

		if (typeField.getText().equals("") && payField.getText().equals("")) {

			showModifyWayIncorrectDialog();

		} else {
			employeeModifyMethod(mpNum, type, pay);
		}
	}
	
	private void employeeModifyMethod(String mpNum, String type, String pay) {
		if ((type.equals("알바") || type.equals("직원")) && (pay.matches("\\d\\d") || pay.matches("\\d\\d\\d"))) {

			rpfm.modifyEmployeeInFile(mpNum, type, pay);

		} else if (type.equals("") && (pay.matches("\\d\\d") || pay.matches("\\d\\d\\d"))) {

			rpfm.modifyEmployeeInFile(mpNum, currentType.getText(), pay);

		} else if ((type.equals("알바") || type.equals("직원")) && pay.matches("")) {

			rpfm.modifyEmployeeInFile(mpNum, type, currentPay.getText());

		}
		
		modifySuccess();
	}
	
	private void modifySuccess() {
		
		showModifySuccessDialog();
		initialize();
		
	}

	private void initialize() {
		currentType.setText("");
		currentPay.setText("");
		mpNumField.clear();
		typeField.clear();
		payField.clear();
	}

	private void employeeExistCheck() {
		if (rpfm.getEmployeeFileEmpty()) {
			showEmployeeNotExistDialog();
		}
	}

	private OnClickListener getListenerToExit() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				frame.hide();
			}
		};
	}

	//////////////////Dialogs///////////////////////

	private void showEmployeeNotExistDialog() {
		createCommonBuilder().setContent("고용된 직원이 없습니다." + System.lineSeparator() + "직원을 먼저 고용해주세요.").build().show();
	}

	private void showModifyWayIncorrectDialog() {
		createCommonBuilder().setContent("수정할 직원 정보를 불러오지 않았거나" + System.lineSeparator() + "두 항목이 비어있습니다.").build()
				.show();
	}

	private void showModifySuccessDialog() {
		createCommonBuilder().setContent("수정 성공.").build().show();
	}

	private void showEmployeeNotFoundDialog() {
		createCommonBuilder().setContent("없는 직원입니다.").build().show();
	}

}
