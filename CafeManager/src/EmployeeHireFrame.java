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
 * summary : it provides function to hire employee details : it has 4 fields to
 * hire employee. (name/hp/type/pay) : hp/type/pay must be input as arguments :
 * then check employee file by hp to prevent duplicate employee arguments :
 * argument = arg : button = btn
 * 
 * @author 김형수
 *
 */

class EmployeeHireFrame {
	// 1. 프레임 종료시까지 생성되어있어야하고 / 2. 프레임의 각종 정보창을 dialog로 띄워줄때 접근이 잦으므로 멤버변수로...
	// 17.10.23. 수정
	private FlatFrame frame = createFrame();

	private FlatTextField nameField = createNameField();
	private FlatTextField mpNumField = createHpNumField();
	private FlatTextField typeField = createTypeField();
	private FlatTextField payField = createPayField();

	private RelatedPersonFileManagement rpfm;
	private static GraphicTheme graphicTheme = new GraphicTheme();

	EmployeeHireFrame(RelatedPersonFileManagement rpfm) {
		this.rpfm = rpfm;

		frame.getContainer().add(createNameInputView(), createCommonConstraints(1));

		frame.getContainer().add(createMpNumInputView(), createCommonConstraints(1));

		frame.getContainer().add(createTypeInputView(), createCommonConstraints(1));

		frame.getContainer().add(createPayInputView(), createCommonConstraints(1));

		frame.getContainer().add(createButtonPanel(), createCommonConstraints(1));

		frame.show();
	}

	///////////////////////Common methods/////////////////////////

	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}

	private FlatDialog.Builder createCommonBuilder() {
		return new FlatDialog.Builder().setTitle("알림").setLocationRelativeTo(frame.getJFrame());
	}

	///////////////////Gui starts/////////////////////

	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();

		frame.setTitle("직원 고용");
		frame.setLocationOnScreenCenter();
		frame.setWindowExit(false);
		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setSize(frameSM.dip2px(250), frameSM.dip2px(230));
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));

		return frame;
	}

	////////////////////////////////////////////////////////////////////

	private FlatPanel createNameInputView() {
		FlatPanel namePanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));

		namePanel.add(createNameLabel(), createCommonConstraints(2));
		namePanel.add(nameField, createCommonConstraints(4));

		return namePanel;
	}

	private FlatLabel createNameLabel() {
		FlatLabel nameLabel = new FlatLabel("이름");
		nameLabel.setBackground(graphicTheme.getBasicBrownColor());
		nameLabel.setFont(graphicTheme.getBoldFont15pt());
		nameLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		nameLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		return nameLabel;
	}

	private FlatTextField createNameField() {
		FlatTextField nameField = new FlatTextField(false);
		nameField.setBackground(graphicTheme.getLightBrownColor());
		nameField.setFont(graphicTheme.getPlainFont15pt());
		return nameField;
	}

	////////////////////////////////////////////////////////////////////

	private FlatPanel createMpNumInputView() {
		FlatPanel mpNumPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatPanel mpNumGuidePanel = createMpNumGuidePanel();

		mpNumPanel.add(mpNumGuidePanel, createCommonConstraints(2));
		mpNumGuidePanel.add(createMpNumLabel(), createCommonConstraints(3));
		mpNumGuidePanel.add(createMpNumGuideLabel(), createCommonConstraints(2));
		mpNumPanel.add(mpNumField, createCommonConstraints(4));

		return mpNumPanel;
	}

	private FlatPanel createMpNumGuidePanel() {
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

	private FlatTextField createHpNumField() {
		FlatTextField hpNumField = new FlatTextField(false);

		hpNumField.setBackground(graphicTheme.getBasicBrownColor());
		hpNumField.setFont(graphicTheme.getPlainFont15pt());

		return hpNumField;
	}

	////////////////////////////////////////////////////////////////////

	private FlatPanel createTypeInputView() {
		FlatPanel typePanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatPanel typeGuidePanel = createTypeGuidePanel();

		typePanel.add(typeGuidePanel, createCommonConstraints(2));
		typeGuidePanel.add(createTypeLabel(), createCommonConstraints(3));
		typeGuidePanel.add(createTypeGuideLabel(), createCommonConstraints(2));
		typePanel.add(typeField, createCommonConstraints(4));

		return typePanel;
	}

	private FlatPanel createTypeGuidePanel() {
		return new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
	}

	private FlatLabel createTypeLabel() {
		FlatLabel typeLabel = new FlatLabel("근무형태");

		typeLabel.setFont(graphicTheme.getBoldFont15pt());
		typeLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		typeLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		typeLabel.setBackground(graphicTheme.getBasicBrownColor());

		return typeLabel;
	}

	private FlatLabel createTypeGuideLabel() {
		FlatLabel typeGuideLabel = new FlatLabel("(양식 : 알바 / 직원 )");

		typeGuideLabel.setFont(graphicTheme.getPlainFont10pt());
		typeGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		typeGuideLabel.setBackground(graphicTheme.getBasicBrownColor());

		return typeGuideLabel;
	}

	private FlatTextField createTypeField() {
		FlatTextField typeField = new FlatTextField(false);

		typeField.setBackground(graphicTheme.getLightBrownColor());
		typeField.setFont(graphicTheme.getPlainFont15pt());

		return typeField;
	}

	////////////////////////////////////////////////////////////////////

	private FlatPanel createPayInputView() {
		FlatPanel payPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatPanel payGuidePanel = createPayGuidePanel();
		payPanel.add(payGuidePanel, createCommonConstraints(2));
		payGuidePanel.add(createPayLabel(), createCommonConstraints(3));
		payGuidePanel.add(createPayGuideLabel(), createCommonConstraints(2));
		payPanel.add(payField, createCommonConstraints(4));

		return payPanel;
	}

	private FlatPanel createPayGuidePanel() {
		return new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
	}

	private FlatLabel createPayLabel() {
		FlatLabel payLabel = new FlatLabel("급여");

		payLabel.setFont(graphicTheme.getBoldFont15pt());
		payLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		payLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		payLabel.setBackground(graphicTheme.getDarkBrownColor());

		return payLabel;
	}

	private FlatLabel createPayGuideLabel() {
		FlatLabel payGuideLabel = new FlatLabel("(단위 : 만원 / 양식 : 00 ~ 000)");

		payGuideLabel.setFont(graphicTheme.getPlainFont10pt());
		payGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		payGuideLabel.setBackground(graphicTheme.getDarkBrownColor());

		return payGuideLabel;
	}

	private FlatTextField createPayField() {
		FlatTextField payField = new FlatTextField(false);

		payField.setBackground(graphicTheme.getBasicBrownColor());
		payField.setFont(graphicTheme.getPlainFont15pt());

		return payField;
	}

	////////////////////////////////////////////////////////////////////

	private FlatPanel createButtonPanel() {
		FlatPanel buttonPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatButton hireBtn = createHireButton();
		FlatButton exitBtn = createExitButton();

		buttonPanel.add(hireBtn, createCommonConstraints(1));
		hireBtn.setOnClickListener(getListenerToHireEmployee());

		buttonPanel.add(exitBtn, createCommonConstraints(1));
		exitBtn.setOnClickListener(getListenerToExitFrame());

		return buttonPanel;
	}

	private FlatButton createHireButton() {
		FlatButton hireBtn = new FlatButton("고용하기");
		hireBtn.setBackground(graphicTheme.getSkyBlueColor());
		return hireBtn;
	}
	
	private FlatButton createExitButton() {
		FlatButton exitBtn = new FlatButton("취소");
		exitBtn.setBackground(graphicTheme.getPinkColor());
		return exitBtn;
	}
	
	////////////////////Listener and related methods//////////////////////////

	private OnClickListener getListenerToHireEmployee() {
		return new OnClickListener() {
			@Override
			public void onClick(Component arg0) {
				hireEmployee();
			}
		};
	}
	
	private void hireEmployee() {

		if (isExistEmptyTextField()) {
			showEmptyFieldExistDialog();
			return;
		}
		
		Employee employee = createEmployee();
		
		if (employee.isValidate()) {
			hireEmployeeMethod(employee);
		} else {
			showHireWayIncorrectDialog();
		}
		
	}
	
	private boolean isExistEmptyTextField() {
		return nameField.getText().equals("") || mpNumField.getText().equals("") || typeField.getText().equals("")
				|| payField.getText().equals("");
	}
	
	private Employee createEmployee() {
		return new Employee(nameField.getText(), mpNumField.getText(), typeField.getText(), payField.getText());
	}
	
	private void hireEmployeeMethod(Employee employee) {
		if(!checkAlreadyHired(employee)) {
			rpfm.storeEmployeeInFile(employee);
			showHireSuccessDialog(employee);

			clearField();
		}
	}
	
	private boolean checkAlreadyHired(Employee employee) {
		if (!rpfm.getEmployeeFileEmpty()&&rpfm.getEmployeeHashMap().containsKey(employee.getMpNum())) {
			showAlreadyHiredDialog();
			clearField();
			return true;
		}
		return false;
	}

	private void clearField() {
		nameField.clear();
		mpNumField.clear();
		typeField.clear();
		payField.clear();
	}

	private OnClickListener getListenerToExitFrame() {
		return new OnClickListener() {
			@Override
			public void onClick(Component arg0) {
				frame.hide();
			}
		};
	}
	
	///////////////////Dialogs strat/////////////////////////////
	
	private void showHireSuccessDialog(Employee employee) {
		createCommonBuilder().setContent(employee.toString() + System.lineSeparator() + "고용 완료.").build().show();
	}

	private void showEmptyFieldExistDialog() {
		createCommonBuilder().setContent("빈 내용이 있습니다. 다시 확인해주세요.").build().show();
	}

	private void showAlreadyHiredDialog() {
		createCommonBuilder().setContent("이미 고용된 직원입니다").build().show();
	}

	private void showHireWayIncorrectDialog() {
		createCommonBuilder().setContent("양식에 맞지 않습니다. 다시 확인해주세요.").build().show();
	}
}
