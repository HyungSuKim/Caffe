import javax.swing.BorderFactory;

import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.flat.text.textarea.alignment.FlatVerticalAlignment;

/**
 * summary : show single member customer info for adding MgmtFrame's List
 * details : show info by getting input(name / hpNum) from MgmtFrame arguments :
 * handphone number = hpNum
 * 
 * @author 김형수
 *
 */

@SuppressWarnings("serial")
public class SingleClientView extends FlatPanel {
	// 17.10.23 수정
	private static GraphicTheme graphicTheme = new GraphicTheme();
	private FlatLabel clientNameLabel = createClientNameLabel();
	private FlatLabel clientMpNumLabel = createClientMpNumLabel();

	SingleClientView() {

		setBackground(graphicTheme.getDarkBrownColor());
		setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
		setLayout(new LinearLayout(0));

		addClientNameView();

		addClientMpNumView();

	}
	
//	common constraints

	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}
	
//	gui

	private void addClientNameView() {
		add(createNameLabel(), createCommonConstraints(2));
		add(clientNameLabel, createCommonConstraints(2));
	}

	private FlatLabel createNameLabel() {
		FlatLabel nameLabel = new FlatLabel("이름 :");
		nameLabel.setFont(graphicTheme.getBoldFont15pt());
		nameLabel.setHorizontalAlignment(FlatHorizontalAlignment.RIGHT);
		nameLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		nameLabel.setBackground(graphicTheme.getDarkBrownColor());
		return nameLabel;
	}

	private FlatLabel createClientNameLabel() {
		FlatLabel clientNameLabel = new FlatLabel();
		clientNameLabel.setFont(graphicTheme.getPlainFont15pt());
		clientNameLabel.setHorizontalAlignment(FlatHorizontalAlignment.LEFT);
		clientNameLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		clientNameLabel.setBackground(graphicTheme.getDarkBrownColor());
		return clientNameLabel;
	}

	private void addClientMpNumView() {
		add(createMpNumLabel(), createCommonConstraints(3));
		add(clientMpNumLabel, createCommonConstraints(4));
	}

	private FlatLabel createMpNumLabel() {
		FlatLabel mpNumLabel = new FlatLabel("핸드폰번호 :");
		mpNumLabel.setFont(graphicTheme.getBoldFont15pt());
		mpNumLabel.setHorizontalAlignment(FlatHorizontalAlignment.RIGHT);
		mpNumLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		mpNumLabel.setBackground(graphicTheme.getDarkBrownColor());
		return mpNumLabel;
	}

	private FlatLabel createClientMpNumLabel() {
		FlatLabel clientMpNumLabel = new FlatLabel();
		clientMpNumLabel.setFont(graphicTheme.getPlainFont15pt());
		clientMpNumLabel.setHorizontalAlignment(FlatHorizontalAlignment.LEFT);
		clientMpNumLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		clientMpNumLabel.setBackground(graphicTheme.getDarkBrownColor());
		return clientMpNumLabel;
	}
	
//	related methods

	void setClientNameLabel(String clientName) {
		clientNameLabel.setText(clientName);
	}

	void setClientMpNumLabel(String clientMpNum) {
		clientMpNumLabel.setText(clientMpNum);
	}

	String getClientMpNumLabel() {
		return clientMpNumLabel.getText();
	}
}
