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
 * summary : It contains what employee can do in huge category options
 *         : 직원이 할 수 있는 큰 범위의 선택지를 제공 해준다.
 * details : It has 3 options to select
 *         : cafe management, employee management, customer management
 *         : cafe management will provide cafe management menu to manage cafe
 *         : employee management will provide employee management menu to manage employee
 *         : customer management will provide function to manage customer
 * arguments : employee = emp
 *           : customer = ctm
 *           : Button = Btn
 *           : management = mgmt        
 * @author 김형수
 *
 */

class EmployeeMenuFrame {
//	수정 17.10.22
	private static GraphicTheme graphicTheme = new GraphicTheme();
	private static RelatedPersonFileManagement rpfm = new RelatedPersonFileManagement();
	
	EmployeeMenuFrame(OrderHistory orderHistory) {
		FlatFrame frame = createFrame();
		
		frame.getContainer().add(createManagementMenuBtn(orderHistory), createCommonConstraints(1));
		
		frame.getContainer().add(createEmployeeMgmtMenuBtn(), createCommonConstraints(1));
		
		frame.getContainer().add(createClientManageBtn(), createCommonConstraints(1));
		
		frame.show();
	}
	
	////////////////common method/////////////////////
	
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}
	
	////////////////Gui starts///////////////////////////
	//////////////create and setting frame//////////////////
	
	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();
		
		frame.setTitle("직원 메뉴");
		frame.setSize(frameSM.dip2px(200), frameSM.dip2px(240));
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		frame.setLocationOnScreenCenter();
		frame.setWindowExit(false);
		
		return frame;
	}
	
	///////////////////////////////////////////////////////////////////////
//	create and setting (cafe)management menu button, it has listener to appear (cafe)management menu
	private FlatButton createManagementMenuBtn(OrderHistory orderHistory) {
		FlatButton caffeeMgmtMenuButton = new FlatButton("카페 관리");
		caffeeMgmtMenuButton.setBackground(graphicTheme.getBasicBrownColor());
		caffeeMgmtMenuButton.setOnClickListener(getListenerToAppearCafeMgmtMenu(orderHistory));
		return caffeeMgmtMenuButton;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
//	create and setting employee management menu button, it has listener to appear employee management menu	
	private FlatButton createEmployeeMgmtMenuBtn() {
		FlatButton employeeMgmtMenuButton = new FlatButton("직원 관리");
		employeeMgmtMenuButton.setBackground(graphicTheme.getPinkColor());
		employeeMgmtMenuButton.setOnClickListener(getListenerToAppearEmployeeMgmtMenu());
		return employeeMgmtMenuButton;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
//	create and setting client management menu button, it has listener to appear client manage	
	private FlatButton createClientManageBtn() {
		FlatButton clientManageButton = new FlatButton("고객 관리");
		clientManageButton.setBackground(graphicTheme.getSkyBlueColor());
		clientManageButton.setOnClickListener(getListenerToAppearClientManage());
		return clientManageButton;
	}
	
	////////////////Listener starts///////////////////////////
	private OnClickListener getListenerToAppearCafeMgmtMenu(OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component arg0) {
				new ManagementMenuFrame(orderHistory);
			}			
		};
	}
	
	private OnClickListener getListenerToAppearEmployeeMgmtMenu() {
		return new OnClickListener() {
			@Override
			public void onClick(Component arg0) {
				new EmployeeMgmtMenuFrame(rpfm);
			}
		};
	}

	private OnClickListener getListenerToAppearClientManage() {
		return new OnClickListener() {
			@Override
			public void onClick(Component arg0) {
				new ClientManageFrame(rpfm);
			}
		};
	}
}
