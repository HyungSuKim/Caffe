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
 * summary : It contains employee management's 3 options
 *         : 직원 관리의 3가지 옵션들을 제공해준다.
 * details : It has 3 options to select
 *         : employee type pay change, hire employee, present employee / fire employee
 *         : employee type pay change will provide function to change employee's type, pay
 *         : hire employee will provide function to hire employee
 *         : fire employee will provide function to fire employee
 * arguments : employee = emp
 *           : customer = ctm
 *           : Button = Btn        
 * @author 김형수
 *
 */

class EmployeeMgmtMenuFrame {
// 수정 17.10.22
	private static GraphicTheme graphicTheme = new GraphicTheme();
	private RelatedPersonFileManagement rpfm;
	EmployeeMgmtMenuFrame(RelatedPersonFileManagement rpfm) {
		this.rpfm = rpfm;
		FlatFrame frame = createFrame();

		frame.getContainer().add(createEmployeeModifyBtn(), createCommonConstraints(1));
		
		frame.getContainer().add(createEmployeeHireBtn(), createCommonConstraints(1));
		
		frame.getContainer().add(createEmployeeFireBtn(), createCommonConstraints(1));
		
		frame.show();
	}
	
	////////////////////////Common methods////////////////////////////////
	
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}
	
	////////////////////////Gui starts//////////////////////////////
	
	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();
		
		frame.setTitle("직원 관리 메뉴");
		frame.setSize(frameSM.dip2px(200), frameSM.dip2px(240));
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		frame.setLocationOnScreenCenter();
		frame.setWindowExit(false);
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		
		return frame;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	private FlatButton createEmployeeModifyBtn(){
		FlatButton modifyEmployeeBtn = new FlatButton("직원 형태/급여변경");
		
		modifyEmployeeBtn.setBackground(graphicTheme.getBasicBrownColor());
		modifyEmployeeBtn.setOnClickListener(getListenerToModifyEmployee());
		
		return modifyEmployeeBtn;
	}

	
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	private FlatButton createEmployeeHireBtn(){
		FlatButton hireEmployeeBtn = new FlatButton("직원 고용");
		
		hireEmployeeBtn.setBackground(graphicTheme.getPinkColor());
		hireEmployeeBtn.setOnClickListener(getListenerToHireEmployee());
		
		return hireEmployeeBtn;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
		
	private FlatButton createEmployeeFireBtn() {
		FlatButton fireEmployeeBtn = new FlatButton("직원 현황/해고");
		
		fireEmployeeBtn.setBackground(graphicTheme.getSkyBlueColor());
		fireEmployeeBtn.setOnClickListener(getListenerToFireEmployee());
		
		return fireEmployeeBtn;
	}
	
	////////////////////////////Listener starts/////////////////////////////////
	private OnClickListener getListenerToModifyEmployee() {
		return new OnClickListener() {
			@Override
			public void onClick(Component arg0) {
				new EmployeeModifyFrame(rpfm);
			}
		};
	}
	
	private OnClickListener getListenerToHireEmployee() {
		return new OnClickListener() {
			@Override
			public void onClick(Component arg0) {
				new EmployeeHireFrame(rpfm);
			}
		};
	}
	
	private OnClickListener getListenerToFireEmployee() {
		return new OnClickListener() {
			@Override
			public void onClick(Component arg0) {
				new EmployeeFireFrame(rpfm);
			}
		};
	}
}
