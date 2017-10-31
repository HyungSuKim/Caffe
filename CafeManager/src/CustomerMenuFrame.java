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
 *         : 손님이 할 수 있는 큰 범위의 선택지를 제공 해준다.
 * details : It has 3 options to select
 *         : member order, nonmember order, member join
 *         : member will provide function to check is member
 *         : nonmember order will provide function which nonmember to order menu
 *         : member join will provide function nonmember to join member 
 * arguments : employee = emp
 *           : customer = ctm
 *           : Button = Btn
 *           : management = mgmt        
 * @author 김형수
 *
 */

class CustomerMenuFrame {
	private static GraphicTheme graphicTheme = new GraphicTheme();
	private RelatedPersonFileManagement rpfm = new RelatedPersonFileManagement();
// 17. 10. 23. 수정
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

		customerMenuFrame.setTitle("손님 메뉴");
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
		FlatButton clientOrderButton = new FlatButton("회원 주문");
		clientOrderButton.setBackground(graphicTheme.getBasicBrownColor());
		clientOrderButton.setOnClickListener(getListenerToAppearCheckMember(orderHistory));
		return clientOrderButton;
	}
	
	private FlatButton createCustomerOrderButton(OrderHistory orderHistory) {
		FlatButton customerOrderButton = new FlatButton("비회원 주문");
		customerOrderButton.setBackground(graphicTheme.getPinkColor());
		customerOrderButton.setOnClickListener(getListenerToAppearOrderFrame(orderHistory));
		return customerOrderButton;
	}
	
	private FlatButton createMemberJoinButton() {
		FlatButton joinMemberButton = new FlatButton("회원 가입");
		joinMemberButton.setBackground(graphicTheme.getSkyBlueColor());
		joinMemberButton.setOnClickListener(getListenerToAppearMemberJoin());
		return joinMemberButton;
	}
	
//	/////////////////////Listener starts///////////////////////////////
	
//	고객인지 확인 할 수있는 창을 띄워준다
	private OnClickListener getListenerToAppearCheckMember(OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				new ClientCheckFrame(orderHistory, rpfm);
			}
		};
	}

//	손님의 주문창을 띄워준다
	private OnClickListener getListenerToAppearOrderFrame(OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				new OrderMenuFrame("", orderHistory);
			}
		};
	}
	
//	회원가입 창을 띄워준다
	private OnClickListener getListenerToAppearMemberJoin() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				new JoinMemberFrame(rpfm);
			}
		};
	}
}
