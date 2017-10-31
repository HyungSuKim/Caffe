import java.awt.Component;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.OnClickListener;
import com.mommoo.flat.frame.FlatFrame;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;
import com.mommoo.util.ScreenManager;

/**
 * summary : It contains what customer can do in options
 *         : �մ��� �� �� �ִ� ū ������ �������� ���� ���ش�.
 * details : It has 3 options to select
 *         : member order, nonmember order, member join
 *         : member will provide function to check is member
 *         : nonmember order will provide function which nonmember to order menu
 *         : member join will provide function nonmember to join member 
 * arguments : employee = emp
 *           : customer = ctm
 *           : Button = Btn
 *           : management = mgmt        
 * @author ������
 *
 */

class CustomerMenuFrame {
	private static GraphicTheme graphicTheme = new GraphicTheme();
	private RelatedPersonFileManagement rpfm = new RelatedPersonFileManagement();
// 17. 10. 23. ����
	CustomerMenuFrame(OrderHistory orderHistory) {
		FlatFrame frame = createFrame();

		frame.getContainer().add(createClientOrderButton(orderHistory), createCommonConstraints(1));
		
		frame.getContainer().add(createCustomerOrderButton(orderHistory), createCommonConstraints(1));
		
		frame.getContainer().add(createMemberJoinButton(), createCommonConstraints(1));
		
		frame.show();
	}
	
	///////////Common Method//////////////

	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}
	
	///////////////// Gui starts //////////////////////
	///////////Frame create(setting)///////////////
	
	private FlatFrame createFrame() {
		FlatFrame customerMenuFrame = new FlatFrame();
		ScreenManager customerMenuSM = ScreenManager.getInstance();

		customerMenuFrame.setTitle("�մ� �޴�");
		customerMenuFrame.setSize(customerMenuSM.dip2px(200), customerMenuSM.dip2px(240));
		customerMenuFrame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		customerMenuFrame.setIconImage(graphicTheme.getCoffeeImage());
		customerMenuFrame.setProcessIconImage(graphicTheme.getCoffeeImage());
		customerMenuFrame.setTitleBarColor(graphicTheme.getTitleBarColor());
		customerMenuFrame.setLocationOnScreenCenter();
		customerMenuFrame.setWindowExit(false);

		return customerMenuFrame;
	}
	
	private FlatButton createClientOrderButton(OrderHistory orderHistory) {
		FlatButton clientOrderButton = new FlatButton("ȸ�� �ֹ�");
		clientOrderButton.setBackground(graphicTheme.getBasicBrownColor());
		clientOrderButton.setOnClickListener(getListenerToAppearCheckMember(orderHistory));
		return clientOrderButton;
	}
	
	private FlatButton createCustomerOrderButton(OrderHistory orderHistory) {
		FlatButton customerOrderButton = new FlatButton("��ȸ�� �ֹ�");
		customerOrderButton.setBackground(graphicTheme.getPinkColor());
		customerOrderButton.setOnClickListener(getListenerToAppearOrderFrame(orderHistory));
		return customerOrderButton;
	}
	
	private FlatButton createMemberJoinButton() {
		FlatButton joinMemberButton = new FlatButton("ȸ�� ����");
		joinMemberButton.setBackground(graphicTheme.getSkyBlueColor());
		joinMemberButton.setOnClickListener(getListenerToAppearMemberJoin());
		return joinMemberButton;
	}
	
//	/////////////////////Listener starts///////////////////////////////
	
//	������ Ȯ�� �� ���ִ� â�� ����ش�
	private OnClickListener getListenerToAppearCheckMember(OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				new ClientCheckFrame(orderHistory, rpfm);
			}
		};
	}

//	�մ��� �ֹ�â�� ����ش�
	private OnClickListener getListenerToAppearOrderFrame(OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				new OrderMenuFrame("", orderHistory);
			}
		};
	}
	
//	ȸ������ â�� ����ش�
	private OnClickListener getListenerToAppearMemberJoin() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				new JoinMemberFrame(rpfm);
			}
		};
	}
}
