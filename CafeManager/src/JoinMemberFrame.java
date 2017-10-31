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

public class JoinMemberFrame {
	// 수정 17.10.22
	private FlatFrame frame = createFrame();

	private FlatTextField nameField = createNameField();
	private FlatTextField mpNumField = createMpNumField();

	private RelatedPersonFileManagement rpfm;
	private static GraphicTheme graphicTheme = new GraphicTheme();

	public JoinMemberFrame(RelatedPersonFileManagement rpfm) {
		this.rpfm = rpfm;

		frame.getContainer().add(createNameInputView(), createCommonLinearConstraints(1));

		frame.getContainer().add(createMpNumInputView(), createCommonLinearConstraints(1));

		frame.getContainer().add(createButtonPanel(), createCommonLinearConstraints(1));

		frame.show();
	}
	///////////////////// Common methods/////////////////////////

	private LinearConstraints createCommonLinearConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}

	private FlatDialog.Builder createCommonDialogBuilder() {
		return new FlatDialog.Builder().setTitle("알림").setLocationRelativeTo(frame.getJFrame());
	}

	////////////////// Gui starts/////////////////////////

	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();

		frame.setLocationOnScreenCenter();
		frame.setWindowExit(false);
		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		frame.setTitle("회원 가입");
		frame.setSize(frameSM.dip2px(250), frameSM.dip2px(150));
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));

		return frame;
	}

	///////////////////////////////////////////////////////////////////////

	private FlatPanel createNameInputView() {
		FlatPanel namePanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));

		namePanel.add(createNameLabel(), createCommonLinearConstraints(2));
		namePanel.add(nameField, createCommonLinearConstraints(4));

		return namePanel;
	}

	private FlatLabel createNameLabel() {
		FlatLabel nameLabel = new FlatLabel("이름");

		nameLabel.setFont(graphicTheme.getBoldFont15pt());
		nameLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		nameLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		nameLabel.setBackground(graphicTheme.getBasicBrownColor());

		return nameLabel;
	}

	private FlatTextField createNameField() {
		FlatTextField nameTextField = new FlatTextField(false);

		nameTextField.setBackground(graphicTheme.getLightBrownColor());
		nameTextField.setFont(graphicTheme.getPlainFont15pt());

		return nameTextField;
	}

	///////////////////////////////////////////////////////////////////////

	private FlatPanel createMpNumInputView() {
		FlatPanel mpNumPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatPanel mpNumLabelPanel = createMpNumLabelPanel();

		mpNumPanel.add(mpNumLabelPanel, createCommonLinearConstraints(2));
		mpNumLabelPanel.add(createMpNumLabel(), createCommonLinearConstraints(3));
		mpNumLabelPanel.add(createMpNumGuideLabel(), createCommonLinearConstraints(2));
		mpNumPanel.add(mpNumField, createCommonLinearConstraints(4));

		return mpNumPanel;
	}

	private FlatPanel createMpNumLabelPanel() {
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
		FlatTextField mpNumTextField = new FlatTextField(false);

		mpNumTextField.setBackground(graphicTheme.getBasicBrownColor());
		mpNumTextField.setFont(graphicTheme.getPlainFont15pt());

		return mpNumTextField;
	}

	///////////////////////////////////////////////////////////////////////

	private FlatPanel createButtonPanel() {
		FlatPanel joinMemberBtnPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatButton joinBtn = createJoinBtn();
		FlatButton exitBtn = createExitBtn();

		joinMemberBtnPanel.add(joinBtn, createCommonLinearConstraints(1));
		joinBtn.setOnClickListener(getListenerToJoinClient());

		joinMemberBtnPanel.add(exitBtn, createCommonLinearConstraints(1));
		exitBtn.setOnClickListener(getListenerToExitFrame());

		return joinMemberBtnPanel;
	}

	private FlatButton createJoinBtn() {
		FlatButton joinButton = new FlatButton("확인");
		joinButton.setBackground(graphicTheme.getSkyBlueColor());
		return joinButton;
	}

	private FlatButton createExitBtn() {
		FlatButton exitButton = new FlatButton("취소");
		exitButton.setBackground(graphicTheme.getPinkColor());
		return exitButton;
	}

	//////////////////// Listener and Related Methods//////////////////////
	private OnClickListener getListenerToJoinClient() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				joinMemeber();
			}
		};
	}

	private void joinMemeber() {
		String name = nameField.getText();
		String mpNum = mpNumField.getText();

		if (rpfm.getClientHashMap().containsKey(mpNum)) {

			showAlreadyMemberDialog();

		} else if (name.equals("") || mpNum.equals("")) {

			showJoinWayIncorrectDialog();

		} else {
			Person client = new Person(name, mpNum);

			if (client.isValidate()) {

				rpfm.storeCientInFile(client);

				showJoinSuccessDialog();

			} else {
				showInputMpNumMismatchDialog();
			}
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

	////////////////////// Dialogs//////////////////////////

	private void showAlreadyMemberDialog() {
		createCommonDialogBuilder().setContent("이미 가입된 고객입니다.").build().show();
	}

	private void showInputMpNumMismatchDialog() {
		createCommonDialogBuilder().setContent("핸드폰 번호 양식을 다시 확인해주세요.").build().show();
	}

	private void showJoinWayIncorrectDialog() {
		createCommonDialogBuilder().setContent("잘 못된 입력입니다.").build().show();
	}

	private void showJoinSuccessDialog() {
		createCommonDialogBuilder().setContent("가입 완료.").setOnClickListener(getListenerToExitFrame()).build().show();
	}
	
}
