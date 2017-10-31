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
 * summary : It contains cafe management's 3 options : 카페 관리의 3가지 옵션들을 제공해준다.
 * details : It has 3 options to select : operating record, stock management,
 * sell history : operating record will provide function to write and look
 * operating records : stock management will provide function to manage stocks :
 * sell history will provide function to check sell history arguments : employee
 * = emp : customer = ctm : Button = Btn
 * 
 * @author 김형수
 *
 */

class ManagementMenuFrame {
	// 17. 10. 23 수정
	private static GraphicTheme graphicTheme = new GraphicTheme();

	ManagementMenuFrame(OrderHistory orderHistory) {
		FlatFrame frame = createFrame();

		frame.getContainer().add(createOperatingRecordBtn(), createCommonConstraints(1));

		frame.getContainer().add(createStockManagementBtn(orderHistory), createCommonConstraints(1));

		frame.getContainer().add(createSellHistoryBtn(orderHistory), createCommonConstraints(1));

		frame.show();
	}

	///////////////////// Common method/////////////////////

	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}

	//////////////////// Gui starts///////////////////////////////

	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();
		frame.setTitle("카페 관리 메뉴");
		frame.setSize(frameSM.dip2px(200), frameSM.dip2px(240));
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		frame.setLocationOnScreenCenter();
		frame.setWindowExit(false);
		return frame;
	}

	/////////////////////////////////////////////////////////////////

	private FlatButton createOperatingRecordBtn() {
		FlatButton operatingRecordBtn = new FlatButton("운영 일지");
		operatingRecordBtn.setBackground(graphicTheme.getBasicBrownColor());
		operatingRecordBtn.setOnClickListener(getListenerToAppearOperatingRecord());
		return operatingRecordBtn;
	}

	/////////////////////////////////////////////////////////////////

	private FlatButton createStockManagementBtn(OrderHistory orderHistory) {
		FlatButton stockManagementBtn = new FlatButton("재고 관리");
		stockManagementBtn.setBackground(graphicTheme.getPinkColor());
		stockManagementBtn.setOnClickListener(getListenerToAppearStockManagement(orderHistory));
		return stockManagementBtn;
	}
	
	/////////////////////////////////////////////////////////////////

	private FlatButton createSellHistoryBtn(OrderHistory orderHistory) {
		FlatButton sellHistoryBtn = new FlatButton("판매 내역");
		sellHistoryBtn.setBackground(graphicTheme.getSkyBlueColor());
		sellHistoryBtn.setOnClickListener(getListenerToAppearSellHistory(orderHistory));
		return sellHistoryBtn;
	}
	
	///////////////////////////Listener////////////////////////

	private OnClickListener getListenerToAppearOperatingRecord() {
		return new OnClickListener() {
			@Override
			public void onClick(Component arg0) {
				new OperatingRecordFrame();
			}
		};
	}

	private OnClickListener getListenerToAppearStockManagement(OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component arg0) {
				new StockManagementFrame(orderHistory);
			}
		};
	}

	private OnClickListener getListenerToAppearSellHistory(OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component arg0) {
				new SellHistoryFrame(orderHistory);
			}
		};
	}
}
