import javax.swing.BorderFactory;

import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.flat.text.textarea.alignment.FlatVerticalAlignment;

/**
 * summary : this panel contains single hired employee infomation
 * details : it has 4 contents name, hpNum, type, pay to show
 *         : get input from FireEmployeeFrame and show
 * @author 김형수
 *
 */

@SuppressWarnings("serial")
class SingleEmployeeView extends FlatPanel {
//	17.10.23 수정ㄱ
	
//	생성되는 인스턴스마다 다른 input을 가지고 있을 것이고 패널이 생성된 뒤에 값을 셋팅해주고 가져오기 위해서 멤버변수로...
	private FlatLabel name = createInputNameLabel();
	private FlatLabel mpNum = createInputHpNumLabel();
	private FlatLabel type = createInputTypeLabel();
	private FlatLabel pay = createInputPayLabel();
	
	private static GraphicTheme graphicTheme = new GraphicTheme();
	
	SingleEmployeeView() {
		
		setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
		setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		
		add(createNameAndMpNumView(), createCommonConstraints(1));
		
		add(createTypeAndPayView(), createCommonConstraints(1));
		
		}
	
		///////////////////related methods//////////////////////
	
		void setEmployeeName(String inputName) {
			name.setText(inputName);
		}
		
		void setEmployeeMpNum(String inputMpNum) {
			mpNum.setText(inputMpNum);
		}
		
		void setEmployeeType(String inputType) {
			type.setText(inputType);
		}
		
		void setEmployeePay(String inputPay) {
			pay.setText(inputPay);
		}
		
		String getMpNum() {
			return mpNum.getText();
		}
		
		////////////////////common constraints/////////////////////////
		
		private LinearConstraints createCommonConstraints(int weight) {
			return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
		}
		
		/////////////////////gui////////////////////////
		
		private FlatPanel createNameAndMpNumView() {
			FlatPanel nameHpNumPanel = new FlatPanel(new LinearLayout(0));
			
			nameHpNumPanel.add(createNameLabel(), createCommonConstraints(2));
			nameHpNumPanel.add(name, createCommonConstraints(2));
			
			nameHpNumPanel.add(createHpNumLabel(), createCommonConstraints(3));
			nameHpNumPanel.add(mpNum, createCommonConstraints(4));
			
			return nameHpNumPanel;
		}
		
		private FlatLabel createNameLabel() {
			FlatLabel nameLabel = new FlatLabel("이름 :");
			nameLabel.setFont(graphicTheme.getBoldFont15pt());
			nameLabel.setHorizontalAlignment(FlatHorizontalAlignment.RIGHT);
			nameLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
			nameLabel.setBackground(graphicTheme.getDarkBrownColor());
			return nameLabel;
		}
		
		private FlatLabel createInputNameLabel() {
			FlatLabel inputNameLabel = new FlatLabel();
			inputNameLabel.setFont(graphicTheme.getPlainFont15pt());
			inputNameLabel.setHorizontalAlignment(FlatHorizontalAlignment.LEFT);
			inputNameLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
			inputNameLabel.setBackground(graphicTheme.getDarkBrownColor());
			return inputNameLabel;
		}
		
		private FlatLabel createHpNumLabel() {
			FlatLabel hpNumLabel = new FlatLabel("핸드폰번호 :");
			hpNumLabel.setFont(graphicTheme.getBoldFont15pt());
			hpNumLabel.setHorizontalAlignment(FlatHorizontalAlignment.RIGHT);
			hpNumLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
			hpNumLabel.setBackground(graphicTheme.getDarkBrownColor());
			return hpNumLabel;
		}
		
		private FlatLabel createInputHpNumLabel() {
			FlatLabel inputHpNumLabel = new FlatLabel();
			inputHpNumLabel.setFont(graphicTheme.getPlainFont15pt());
			inputHpNumLabel.setHorizontalAlignment(FlatHorizontalAlignment.LEFT);
			inputHpNumLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
			inputHpNumLabel.setBackground(graphicTheme.getDarkBrownColor());
			return inputHpNumLabel;
		}
		
		//////////////////////////////////////////////////////////////
		
		private FlatPanel createTypeAndPayView() {
			FlatPanel typeAndPayPanel = new FlatPanel(new LinearLayout(0));
			typeAndPayPanel.add(createTypeLabel(), createCommonConstraints(1));
			typeAndPayPanel.add(type, createCommonConstraints(1));
			
			typeAndPayPanel.add(createPayLabel(), createCommonConstraints(1));
			typeAndPayPanel.add(pay, createCommonConstraints(1));
			return typeAndPayPanel;
		}
		
		private FlatLabel createTypeLabel() {
			FlatLabel typeLabel = new FlatLabel("고용형태 :");
			typeLabel.setFont(graphicTheme.getBoldFont15pt());
			typeLabel.setHorizontalAlignment(FlatHorizontalAlignment.RIGHT);
			typeLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
			typeLabel.setBackground(graphicTheme.getDarkBrownColor());
			return typeLabel;
		}
		
		private FlatLabel createInputTypeLabel() {
			FlatLabel inputTypeLabel = new FlatLabel();
			inputTypeLabel.setFont(graphicTheme.getPlainFont15pt());
			inputTypeLabel.setHorizontalAlignment(FlatHorizontalAlignment.LEFT);
			inputTypeLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
			inputTypeLabel.setBackground(graphicTheme.getDarkBrownColor());
			return inputTypeLabel;
		}
		
		private FlatLabel createPayLabel() {
			FlatLabel payLabel = new FlatLabel("급여 :");
			payLabel.setFont(graphicTheme.getBoldFont15pt());
			payLabel.setHorizontalAlignment(FlatHorizontalAlignment.RIGHT);
			payLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
			payLabel.setBackground(graphicTheme.getDarkBrownColor());
			return payLabel;
		}
		
		private FlatLabel createInputPayLabel() {
			FlatLabel inputPayLabel = new FlatLabel();
			inputPayLabel.setFont(graphicTheme.getPlainFont15pt());
			inputPayLabel.setHorizontalAlignment(FlatHorizontalAlignment.LEFT);
			inputPayLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
			inputPayLabel.setBackground(graphicTheme.getDarkBrownColor());
			return inputPayLabel;
		}
}
