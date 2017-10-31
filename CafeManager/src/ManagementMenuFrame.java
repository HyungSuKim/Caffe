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
 * summary : It contains cafe management's 3 options : ī�� ������ 3���� �ɼǵ��� �������ش�.
 * details : It has 3 options to select : operating record, stock management,
 * sell history : operating record will provide function to write and look
 * operating records : stock management will provide function to manage stocks :
 * sell history will provide function to check sell history arguments : employee
 * = emp : customer = ctm : Button = Btn
 * 
 * @author ������
 *
 */

class ManagementMenuFrame {
	// 17. 10. 23 ����
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
		frame.setTitle("ī�� ���� �޴�");
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
		FlatButton operatingRecordBtn = new FlatButton("� ����");
		operatingRecordBtn.setBackground(graphicTheme.getBasicBrownColor());
		operatingRecordBtn.setOnClickListener(getListenerToAppearOperatingRecord());
		return operatingRecordBtn;
	}

	/////////////////////////////////////////////////////////////////

	private FlatButton createStockManagementBtn(OrderHistory orderHistory) {
		FlatButton stockManagementBtn = new FlatButton("��� ����");
		stockManagementBtn.setBackground(graphicTheme.getPinkColor());
		stockManagementBtn.setOnClickListener(getListenerToAppearStockManagement(orderHistory));
		return stockManagementBtn;
	}
	
	/////////////////////////////////////////////////////////////////

	private FlatButton createSellHistoryBtn(OrderHistory orderHistory) {
		FlatButton sellHistoryBtn = new FlatButton("�Ǹ� ����");
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
