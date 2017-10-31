import java.awt.Component;

import javax.swing.BorderFactory;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.OnClickListener;
import com.mommoo.flat.frame.FlatFrame;
import com.mommoo.flat.image.FlatImagePanel;
import com.mommoo.flat.image.ImageOption;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.util.ScreenManager;

/**
 * summary : Which main frame for Cafe Program
 *         : 카페 프로그램의 주요 창
 * details : It has two function for employee and customer which implement by buttons
 *         : When button clicked it shows Customer Menu or Employee Menu
 * arguments : employee = emp
 *           : customer = ctm
 *           : Button = Btn
 * @author 김형수
 *
 */


class MainFrame {
//	수정 17.10.22
	private static GraphicTheme graphicTheme = new GraphicTheme();
	
		MainFrame()	{
			OrderHistory orderHistory = new OrderHistory();
			FlatFrame frame = createFrame();
			
			frame.getContainer().add(createMainTitleView(), createCommonConstraints(2));
			
			frame.getContainer().add(createEmployeeBtn(orderHistory), createCommonConstraints(1));
			
			frame.getContainer().add(createCustomerBtn(orderHistory), createCommonConstraints(1));
			
			frame.show();
			}
		
		////////////////common method///////////////////
		
		private LinearConstraints createCommonConstraints(int weight) {
			return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
		}
		
		///////////////////Gui starts//////////////////////
//		create and setting frame
		private FlatFrame createFrame() {
			FlatFrame frame = new FlatFrame();
			ScreenManager frameSM = ScreenManager.getInstance();
			frame.setTitle("카페 INTRO");
			frame.setSize(frameSM.dip2px(300), frameSM.dip2px(400));
			frame.setLocationOnScreenCenter();
			frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));
			frame.setTitleBarColor(graphicTheme.getTitleBarColor());
			frame.setResizable(true);
			frame.setWindowExit(true);
			frame.setIconImage(graphicTheme.getCoffeeImage());
			frame.setProcessIconImage(graphicTheme.getCoffeeImage());
			return frame;
		}
		
		/////////////////////////////////////////////////////////////////
//		create and setting main title view, contains image panel and label
		private FlatImagePanel createMainTitleView() {
			@SuppressWarnings("serial")
			FlatImagePanel mainTitlePanel = new FlatImagePanel() {
				protected boolean isPaintingOrigin() { return true; }
			};
			mainTitlePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			mainTitlePanel.setImage(graphicTheme.getCoffeeImage(), ImageOption.MATCH_PARENT);
			mainTitlePanel.setLayout(new LinearLayout());
			mainTitlePanel.setAlpha(0.5f);
			mainTitlePanel.add(createMainTitleLabel(), new LinearConstraints().setWeight(1).setLinearSpace(LinearSpace.WRAP_CENTER_CONTENT));
			return mainTitlePanel;
		}		

		private FlatLabel createMainTitleLabel() {
			FlatLabel mainTitleLabel = new FlatLabel(" ☆ 카페 매니저 ☆ ");
			mainTitleLabel.setFont(graphicTheme.getMainLabelFont());
			mainTitleLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
			return mainTitleLabel;
		}
		
		/////////////////////////////////////////////////////////////////
//		create and setting employee button it has listener to appear employee menu
		private FlatButton createEmployeeBtn(OrderHistory orderHistory) {
			FlatButton employeeBtn = new FlatButton("직원 메뉴");
			employeeBtn.setBackground(graphicTheme.getPinkColor());
			employeeBtn.setFont(graphicTheme.getMainButtonFont());
			employeeBtn.setOnClickListener(getListenerToAppearEmpMenu(orderHistory));
			return employeeBtn;
		}
		
		/////////////////////////////////////////////////////////////////
//		create and setting customer button it has listener to appear customer menu		
		private FlatButton createCustomerBtn(OrderHistory orderHistory) {
			FlatButton customerBtn = new FlatButton("손님 메뉴");
			customerBtn.setBackground(graphicTheme.getSkyBlueColor());
			customerBtn.setFont(graphicTheme.getMainButtonFont());
			customerBtn.setOnClickListener(getListenerToAppearCtmMenu(orderHistory));
			return customerBtn;
		}
		
//		//////////////////////Listener////////////////////////////////////
		
		private OnClickListener getListenerToAppearEmpMenu(OrderHistory orderHistory) {
			return new OnClickListener() {
				@Override
				public void onClick(Component component) {
					new EmployeeMenuFrame(orderHistory);
				}
			};
		}
		
		private OnClickListener getListenerToAppearCtmMenu(OrderHistory orderHistory) {
			return new OnClickListener() {
				@Override
				public void onClick(Component component) {
					new CustomerMenuFrame(orderHistory);
				}
			};
		}
	}
