import java.awt.Component;
import java.util.List;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.component.OnClickListener;
import com.mommoo.flat.frame.FlatDialog;
import com.mommoo.flat.frame.FlatFrame;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;
import com.mommoo.flat.list.FlatListView;
import com.mommoo.flat.list.listener.OnSelectionListener;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.flat.text.textarea.alignment.FlatVerticalAlignment;
import com.mommoo.flat.text.textfield.FlatTextField;
import com.mommoo.util.ScreenManager;

/**
 * summary : this provide present employee list and fire employee function
 * details : user can fire employee by input hpNum and single selection on list
 * : it has priority at fire employee as input hpNum arguments : argument = arg
 * : employee = emp : button = btn
 * 
 * @author 김형수
 *
 */

class EmployeeFireFrame {
	// 17.10.23 수정
	// 17.10.25 수정
	private FlatFrame frame = createFrame();

	private FlatListView<SingleEmployeeView> employeeList = createEmployeeList();
	private FlatTextField mpNumField = createMpNumField();

	private int selectedIndex = -1;

	private RelatedPersonFileManagement rpfm;
	private static GraphicTheme graphicTheme = new GraphicTheme();

	EmployeeFireFrame(RelatedPersonFileManagement rpfm) {
		this.rpfm = rpfm;

		frame.getContainer().add(createEmployeeListView(), createCommonConstraints(7));

		validateEmployeeCount();
		employeeList.setOnSelectionListener(getListenerToSetSelectedIndex());

		frame.getContainer().add(createFireEmployeeInputView(), createCommonConstraints(3));

		frame.getContainer().add(createButtonPanel(), createCommonConstraints(2));

		frame.show();
	}

	/////////// Common Methods////////////

	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}

	private FlatDialog.Builder createCommonBuilder() {
		return new FlatDialog.Builder().setTitle("알림").setLocationRelativeTo(frame.getJFrame());
	}

	////////////////////// Gui starts //////////////////////////////////
	/////////// frame create(setting)/////////////

	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();

		frame.setTitle("직원 현황/해고");
		frame.setSize(frameSM.dip2px(200), frameSM.dip2px(240));
		frame.setLocationOnScreenCenter();
		frame.setWindowExit(false);
		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));

		return frame;
	}

	////////////////////////////////////////////////////////////////////////////////////////////

	private FlatPanel createEmployeeListView() {
		FlatPanel employeePanel = new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));

		employeePanel.add(createListInfoLabel(), createCommonConstraints(1));
		employeePanel.add(employeeList.getComponent(), createCommonConstraints(6));

		return employeePanel;
	}

	private FlatLabel createListInfoLabel() {
		FlatLabel listInfoLabel = new FlatLabel("직원 현황");

		listInfoLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		listInfoLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		listInfoLabel.setBackground(graphicTheme.getBasicBrownColor());
		listInfoLabel.setOpaque(true);
		listInfoLabel.setFont(graphicTheme.getBoldFont20pt());

		return listInfoLabel;
	}

	private FlatListView<SingleEmployeeView> createEmployeeList() {
		FlatListView<SingleEmployeeView> presentEmployeeList = new FlatListView<SingleEmployeeView>();

		presentEmployeeList.setBackgroundColor(graphicTheme.getDarkBrownColor());
		presentEmployeeList.getScroller().setScrollBarColor(graphicTheme.getDarkerBrownColor());
		presentEmployeeList.setSelectionColor(graphicTheme.getDarkerBrownColor());
		presentEmployeeList.setSingleSelectionMode(true);
		presentEmployeeList.getScroller().setScrollTrackColor(graphicTheme.getLightBrownColor());
		presentEmployeeList.setDivider(graphicTheme.getDividerColor(), 1);

		return presentEmployeeList;
	}

	private FlatPanel createFireEmployeeInputView() {
		FlatPanel inputView = new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
		FlatPanel mpNumPanel = createMpNumPanel();
		FlatPanel mpNumGuidePanel = createMpNumGuidePanel();

		inputView.add(infoLabel(), createCommonConstraints(2));
		inputView.add(mpNumPanel, createCommonConstraints(3));
		mpNumPanel.add(mpNumGuidePanel, createCommonConstraints(2));
		mpNumGuidePanel.add(createMpNumLabel(), createCommonConstraints(3));
		mpNumGuidePanel.add(createMpNumGuideLabel(), createCommonConstraints(2));
		mpNumPanel.add(mpNumField, createCommonConstraints(3));

		return inputView;
	}

	private FlatPanel createMpNumPanel() {
		return new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
	}

	private FlatPanel createMpNumGuidePanel() {
		return new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
	}

	private FlatLabel infoLabel() {
		FlatLabel infoLabel = new FlatLabel("직원 해고");

		infoLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		infoLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		infoLabel.setBackground(graphicTheme.getBasicBrownColor());
		infoLabel.setOpaque(true);
		infoLabel.setFont(graphicTheme.getBoldFont20pt());

		return infoLabel;
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
		FlatTextField mpNumField = new FlatTextField(false);

		mpNumField.setBackground(graphicTheme.getLightBrownColor());
		mpNumField.setFont(graphicTheme.getPlainFont20pt());

		return mpNumField;
	}

	private FlatPanel createButtonPanel() {
		FlatPanel buttonPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatButton fireButton = createFireButton();
		FlatButton exitButton = createExitButton();

		buttonPanel.add(fireButton, createCommonConstraints(1));
		fireButton.setOnClickListener(getListenerToFireEmployee());
		buttonPanel.add(exitButton, createCommonConstraints(1));
		exitButton.setOnClickListener(getListenerToExitFrame());

		return buttonPanel;
	}

	private FlatButton createFireButton() {
		FlatButton fireButton = new FlatButton("해고하기");
		fireButton.setBackground(graphicTheme.getSkyBlueColor());

		return fireButton;
	}

	private FlatButton createExitButton() {
		FlatButton exitButton = new FlatButton("취소");

		exitButton.setBackground(graphicTheme.getPinkColor());
		return exitButton;
	}

	/////////////////// Listener and related methods//////////////////////////
	
	private void setEmployeeOnList() {

		int employeeArrayListSize = rpfm.getEmployeeArrayList().size();

		for (int index = 0; index < employeeArrayListSize; index++) {

			SingleEmployeeView area = new SingleEmployeeView();
			area.setEmployeeName(((Employee) rpfm.getEmployeeArrayList().get(index)).getName());
			area.setEmployeeMpNum(((Employee) rpfm.getEmployeeArrayList().get(index)).getMpNum());
			area.setEmployeeType(((Employee) rpfm.getEmployeeArrayList().get(index)).getType());
			area.setEmployeePay(((Employee) rpfm.getEmployeeArrayList().get(index)).getPay());

			employeeList.addItem(area);

		}
		employeeList.getScroller().scrollByValue(0);
	}

	private OnSelectionListener<SingleEmployeeView> getListenerToSetSelectedIndex() {
		return new OnSelectionListener<SingleEmployeeView>() {
			@Override
			public void onSelection(int beginIndex, int endIndex, List<SingleEmployeeView> selectionList) {
				selectedIndex = beginIndex;
			}
		};
	}

	private OnClickListener getListenerToFireEmployee() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				fireEmployee();
			}
		};
	}
	
	private void fireEmployee() {

		String inputMpNum = mpNumField.getText();

		if (inputMpNumChecker(inputMpNum)) {
			fireByInputNum(inputMpNum);
			
		} else if (selectedIndex != -1) {
			fireBySelectedIndex(selectedIndex);
			
		} else {
			showFireWayIncorrectDialog();
		}
	}

	private boolean inputMpNumChecker(String inputMpNum) {
		return inputMpNum.matches("010-\\d\\d\\d\\d-\\d\\d\\d\\d");
	}

	private void fireByInputNum(String inputMpNum) {
		if (rpfm.deleteEmployeeInFile(inputMpNum)) {

			showFireEmployeeByInputSuccessDialog();
			deleteOnEmployeeList();

		} else {
			showInputMpNumMismatchDialog();
		}
	}

	private void fireBySelectedIndex(int selectedIndex) {
		rpfm.deleteEmployeeInFile(employeeList.getItem(selectedIndex).getMpNum());

		showFireEmployeeBySelectSuccess();

		deleteOnEmployeeList();
	}

	private void deleteOnEmployeeList() {
		applyChange();
		validateEmployeeCount();
	}

	private void applyChange() {
		mpNumField.clear();
		if (employeeList.getItemSize() == 0) {
			return;
		} else {
			employeeList.removeItems(0, employeeList.getItemSize() - 1);
			selectedIndex = -1;
		}
	}
	
	private void validateEmployeeCount() {
		if (rpfm.getEmployeeFileEmpty()) {
			showEmployeeNotExistDialog();
		} else {
			setEmployeeOnList();
		}
	}

	private OnClickListener getListenerToExitFrame() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				frame.hide();
			}
		};
	}

	/////////////////// Dialogs start//////////////////////////
	private void showInputMpNumMismatchDialog() {
		createCommonBuilder().setContent("정보를 다시 확인하세요.").build().show();
	}

	private void showFireEmployeeByInputSuccessDialog() {
		createCommonBuilder().setContent("입력된 직원의 해고에 성공하였습니다.").build().show();
	}

	private void showFireEmployeeBySelectSuccess() {
		createCommonBuilder().setContent("선택된 직원의 해고에 성공하였습니다.").build().show();
	}

	private void showFireWayIncorrectDialog() {
		createCommonBuilder().setContent("탈퇴할 멤버를 선택하거나" + System.lineSeparator() + "핸드폰 번호 양식을 다시 확인해주세요.").build()
				.show();
	}

	private void showEmployeeNotExistDialog() {
		createCommonBuilder().setContent("고용된 직원이 없습니다." + System.lineSeparator() + "직원을 먼저 고용해주세요.").build().show();
	}

}
