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
 * summary : This will show member customers and provide function to manage
 * member customer details : It will show member customers by list : And will
 * provide function to delete member customer by two ways : primary order( 1.
 * type hpNum, 2. select on list ) arguments : customer = ctm : Management =
 * Mgmt : mobile phone number = mpNum : present member = pm
 * 
 * @author ������
 * 
 */

public class ClientManageFrame {
	// ���� 17.10.22
	// ���� 17.10.25
	private FlatFrame frame = createFrame();
	private FlatTextField mpNumInputField = createMpNumInputField();
	private FlatListView<SingleClientView> clientList = createClientList();

	private static GraphicTheme graphicTheme = new GraphicTheme();
	private RelatedPersonFileManagement rpfm;
	private int selectedIndex = -1;

	public ClientManageFrame(RelatedPersonFileManagement rpfm) {
		this.rpfm = rpfm;

		frame.getContainer().add(createClientListView(), createCommonConstraints(7));

		frame.getContainer().add(createDropOutClientInputView(), createCommonConstraints(3));

		frame.getContainer().add(createManageBtnPanel(), createCommonConstraints(2));

		validateClientCount();

		frame.show();
	}

	// common method
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}

	private FlatDialog.Builder createCommonBuilder() {
		return new FlatDialog.Builder().setTitle("�˸�").setLocationRelativeTo(frame.getJFrame());
	}

	////////////////////////////// Gui method start //////////////////////////////
	// frame create(setting)
	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();

		frame.setTitle("�� ����");
		frame.setSize(frameSM.dip2px(200), frameSM.dip2px(240));
		frame.setLocationOnScreenCenter();
		frame.setWindowExit(false);
		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));

		return frame;
	}

	// client list view create(setting) - contains ClientListInfoLabel, clientList
	private FlatPanel createClientListView() {
		FlatPanel clientPanel = new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));

		clientPanel.add(createClientListInfoLabel(), createCommonConstraints(1));

		clientPanel.add(clientList.getComponent(), createCommonConstraints(6));

		clientList.setOnSelectionListener(getListenerToSetSelectedIndex());

		return clientPanel;
	}

	private FlatLabel createClientListInfoLabel() {
		FlatLabel clientListInfoLabel = new FlatLabel("�� ��Ȳ");

		clientListInfoLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		clientListInfoLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		clientListInfoLabel.setBackground(graphicTheme.getBasicBrownColor());
		clientListInfoLabel.setOpaque(true);
		clientListInfoLabel.setFont(graphicTheme.getBoldFont20pt());

		return clientListInfoLabel;
	}

	private FlatListView<SingleClientView> createClientList() {
		FlatListView<SingleClientView> clientList = new FlatListView<SingleClientView>();

		clientList.setBackgroundColor(graphicTheme.getDarkBrownColor());
		clientList.getScroller().setScrollBarColor(graphicTheme.getDarkerBrownColor());
		clientList.setSelectionColor(graphicTheme.getDarkerBrownColor());
		clientList.setSingleSelectionMode(true);
		clientList.getScroller().setScrollTrackColor(graphicTheme.getLightBrownColor());
		clientList.setDivider(graphicTheme.getDividerColor(), 1);

		return clientList;
	}

	// drop out client input view create(setting), contains mpNumInputField
	private FlatPanel createDropOutClientInputView() {
		FlatPanel dropOutClientPanel = new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
		FlatPanel mpNumPanel = createMpNumPanel();

		dropOutClientPanel.add(createDropOutClientInfoLabel(), createCommonConstraints(2));
		dropOutClientPanel.add(mpNumPanel, createCommonConstraints(3));

		mpNumPanel.add(createMpNumFormPanel(), createCommonConstraints(2));
		mpNumPanel.add(mpNumInputField, createCommonConstraints(4));

		return dropOutClientPanel;
	}

	private FlatLabel createDropOutClientInfoLabel() {
		FlatLabel dropOutClientInfoLabel = new FlatLabel("�� Ż��");

		dropOutClientInfoLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		dropOutClientInfoLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		dropOutClientInfoLabel.setBackground(graphicTheme.getBasicBrownColor());
		dropOutClientInfoLabel.setOpaque(true);
		dropOutClientInfoLabel.setFont(graphicTheme.getBoldFont20pt());

		return dropOutClientInfoLabel;
	}

	private FlatPanel createMpNumPanel() {
		return new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
	}

	private FlatPanel createMpNumFormPanel() {
		FlatPanel mpNumFormPanel = new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));

		mpNumFormPanel.add(createMpNumLabel(), createCommonConstraints(3));
		mpNumFormPanel.add(createMpNumGuideLabel(), createCommonConstraints(2));

		return mpNumFormPanel;
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

	private FlatTextField createMpNumInputField() {
		FlatTextField mpNumInputField = new FlatTextField(false);

		mpNumInputField.setBackground(graphicTheme.getLightBrownColor());
		mpNumInputField.setFont(graphicTheme.getPlainFont20pt());

		return mpNumInputField;
	}

	// manage button panel create, contains delete, exit buttons
	private FlatPanel createManageBtnPanel() {
		FlatPanel manageBtnPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));

		manageBtnPanel.add(createDeleteBtn(), createCommonConstraints(1));
		manageBtnPanel.add(createExitBtn(), createCommonConstraints(1));

		return manageBtnPanel;
	}

	private FlatButton createDeleteBtn() {
		FlatButton deleteBtn = new FlatButton("Ż��Ȯ��");

		deleteBtn.setBackground(graphicTheme.getSkyBlueColor());
		deleteBtn.setOnClickListener(getListenerToDeleteClient());

		return deleteBtn;
	}

	private FlatButton createExitBtn() {
		FlatButton exitBtn = new FlatButton("���");

		exitBtn.setBackground(graphicTheme.getPinkColor());
		exitBtn.setOnClickListener(getListenerToExitFrame());

		return exitBtn;
	}

	//////////////////////// EventListener and related methods start
	//////////////////////// ///////////////////////////////

	// related person file management�� ��������� ���� �����Ͱ� ����ִ� client array list���� ��
	// �ϳ��ϳ���
	// ������ �޾ƿͼ� clientList�� �־��ش�.
	private void setClientOnList() {
		int clientArrayListSize = rpfm.getClientArrayList().size();

		for (int index = 0; index < clientArrayListSize; index++) {
			SingleClientView area = new SingleClientView();
			area.setClientNameLabel(rpfm.getClientArrayList().get(index).getName());
			area.setClientMpNumLabel(rpfm.getClientArrayList().get(index).getMpNum());
			clientList.addItem(area);
		}

		clientList.getScroller().scrollByValue(0);
	}

	// Listener set on clientList, To Set Selected Index
	private OnSelectionListener<SingleClientView> getListenerToSetSelectedIndex() {
		return new OnSelectionListener<SingleClientView>() {
			@Override
			public void onSelection(int beginIndex, int endIndex, List<SingleClientView> selectionList) {
				selectedIndex = beginIndex;
			}
		};
	}

	private OnClickListener getListenerToDeleteClient() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				dropOutClient();
			}
		};
	}

	private void dropOutClient() {
		String inputMpNum = mpNumInputField.getText();

		if (inputMpNumChecker(inputMpNum)) {

			deleteByInputNum(inputMpNum);

		} else if (selectedIndex != -1) {

			deleteBySelectedIndex(selectedIndex);

		} else {
			showDeleteWayIncorrectDialog();
		}
	}

	private boolean inputMpNumChecker(String inputMpNum) {
		return inputMpNum.matches("010-\\d\\d\\d\\d-\\d\\d\\d\\d");
	}

	private void deleteByInputNum(String inputMpNum) {
		if (rpfm.deleteClientInFile(inputMpNum)) {

			showDeleteClientByInputSuccessDialog();

			deleteOnClientList();

		} else {
			showInputMpNumMismatchDialog();
		}
	}

	private void deleteBySelectedIndex(int selectedIndex) {
		rpfm.deleteClientInFile(clientList.getItem(selectedIndex).getClientMpNumLabel());

		showDeleteClientBySelectSuccessDialog();

		deleteOnClientList();
	}

	private void deleteOnClientList() {
		applyChange();

		validateClientCount();
	}

	private void applyChange() {
		mpNumInputField.clear();
		if (clientList.getItemSize() == 0) {
			return;
		} else {
			clientList.removeItems(0, clientList.getItemSize() - 1);
			selectedIndex = -1;
		}
	}

	// ���� �ִ��� Ȯ���ϰ� ���� ������ ���ٴ� ���̾�α׸� ����ְ� ������ ������ ������ ����Ʈ�� �־��ش�
	private void validateClientCount() {
		if (rpfm.getClientFileEmpty()) {
			showClientNotExistDialog();
		} else {
			setClientOnList();
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

	////////////////////////////// Dialog methods//////////////////////////////

	private void showDeleteClientByInputSuccessDialog() {
		createCommonBuilder().setContent("�Էµ� ���� Ż�� �����Ͽ����ϴ�.").build().show();
	}

	private void showDeleteClientBySelectSuccessDialog() {
		createCommonBuilder().setContent("������ ���� Ż�� �����Ͽ����ϴ�.").build().show();
	}

	private void showInputMpNumMismatchDialog() {
		createCommonBuilder().setContent("�ڵ��� ��ȣ�� �ٽ� Ȯ���ϼ���.").build().show();
	}

	private void showDeleteWayIncorrectDialog() {
		createCommonBuilder().setContent("Ż���� ���� �����ϰų�" + System.lineSeparator() + "�ڵ��� ��ȣ ����� �ٽ� Ȯ�����ּ���.").build()
				.show();
	}

	private void showClientNotExistDialog() {
		createCommonBuilder().setContent("���Ե� ���� �����ϴ�.").build().show();
	}
}
