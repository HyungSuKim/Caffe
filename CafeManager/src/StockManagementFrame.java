import java.awt.Color;
import java.awt.Component;

import javax.swing.border.EmptyBorder;

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
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.flat.text.textarea.alignment.FlatVerticalAlignment;
import com.mommoo.util.ScreenManager;
/**
 * summary : this frame can check all stock and manage it
 * details : this frame will show all stock items
 *         : also single stock's consumtion and remain
 *         : and you can add amount to stock items
 * arguments : management = mgmt
 *           : button = btn
 *           : argument = arg
 * @author 김형수
 *
 */
public class StockManagementFrame {
//	17.10.24 수정
	private FlatFrame frame = createFrame();
	private FlatListView<SingleStockManageView> stockList = createStockList();	
	private static GraphicTheme graphicTheme = new GraphicTheme();
	FlatButton refreshConsumptionBtn;
	FlatButton saveAdditionBtn;
	
	StockManagementFrame(OrderHistory orderHistory) {

		frame.getContainer().add(createInformationView(), createCommonConstraints(1));
		
		frame.getContainer().add(createStockManageListView(), createCommonConstraints(5));
		setStockManageListOnView(orderHistory);
		
		frame.getContainer().add(createButtonPanel(), createCommonConstraints(1));
		refreshConsumptionBtn.setOnClickListener(getListenerToRefreshItemsOnList(orderHistory));
		saveAdditionBtn.setOnClickListener(getListenerToSaveStock(orderHistory));
		
		
		frame.show();
	}
	
	//////////////////////////common constraint///////////////////////////////
	
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}
	
	///////////////////////////gui/////////////////////////////////
	
	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();
		
		frame.setTitle("재고 관리");
		frame.setSize(frameSM.dip2px(200), frameSM.dip2px(240));
		frame.setLocationOnScreenCenter();
		frame.setWindowExit(false);
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		
		return frame;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createInformationView() {
		FlatPanel labelPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatPanel additionLabelPanel = createStockAdditionPanel();
		
		labelPanel.setBorder(new EmptyBorder(0, 0, 0, 5));
		labelPanel.setBackground(graphicTheme.getDarkBrownColor());
		labelPanel.setOpaque(true);
		
		labelPanel.add(createNameLabel(), createCommonConstraints(2));
		labelPanel.add(createConsumptionLabel(), createCommonConstraints(1));
		labelPanel.add(createRemainLabel(), createCommonConstraints(1));

		labelPanel.add(additionLabelPanel, createCommonConstraints(2));
		additionLabelPanel.add(createAdditionLabel(), createCommonConstraints(3));
		additionLabelPanel.add(createAdditionGuideLabel(), createCommonConstraints(2));
		
		return labelPanel;
	}
	
	private FlatPanel createStockAdditionPanel() {
		return new FlatPanel(new LinearLayout(Orientation.VERTICAL,0));
	}
	
	private FlatLabel createNameLabel() {
		FlatLabel nameLabel = new FlatLabel("재고명");
		
		nameLabel.setFont(graphicTheme.getBoldFont15pt());
		nameLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		nameLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		nameLabel.setBackground(graphicTheme.getBasicBrownColor());
		
		return nameLabel;
	}
	
	private FlatLabel createConsumptionLabel() {
		FlatLabel consumptionLabel = new FlatLabel("소모량");
		
		consumptionLabel.setFont(graphicTheme.getBoldFont15pt());
		consumptionLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		consumptionLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		consumptionLabel.setBackground(graphicTheme.getDarkBrownColor());
		
		return consumptionLabel;
	}
	
	private FlatLabel createRemainLabel() {
		FlatLabel remainLabel = new FlatLabel("잔여량");
		
		remainLabel.setFont(graphicTheme.getBoldFont15pt());
		remainLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		remainLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		remainLabel.setBackground(graphicTheme.getBasicBrownColor());
		
		return remainLabel;
	}
	
	private FlatLabel createAdditionLabel() {
		FlatLabel additionLabel = new FlatLabel("추가량");
		
		additionLabel.setBackground(graphicTheme.getDarkBrownColor());
		additionLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		additionLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		additionLabel.setFont(graphicTheme.getBoldFont15pt());
		
		return additionLabel;
	}
	
	private FlatLabel createAdditionGuideLabel() {
		FlatLabel additionGuideLabel = new FlatLabel("(추가범위 : 0~999)");
		
		additionGuideLabel.setBackground(graphicTheme.getDarkBrownColor());
		additionGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		additionGuideLabel.setFont(graphicTheme.getPlainFont10pt());
		
		return additionGuideLabel;
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createStockManageListView() {
		FlatPanel stockMgmtListPanel = new FlatPanel(new LinearLayout(0));
		
		stockMgmtListPanel.setBackground(graphicTheme.getDarkBrownColor());
		stockMgmtListPanel.setOpaque(true);
		stockMgmtListPanel.add(stockList.getComponent(), createCommonConstraints(1));
		
		return stockMgmtListPanel;
	}
	
	private FlatListView<SingleStockManageView> createStockList() {
		FlatListView<SingleStockManageView> stockList = new FlatListView<>();
		
		stockList.setBackgroundColor(graphicTheme.getDarkBrownColor());
		stockList.getScroller().setScrollBarColor(graphicTheme.getDarkerBrownColor());
		stockList.setSelectionColor(new Color(0, 0, 0, 0));
		stockList.setSingleSelectionMode(true);
		stockList.getScroller().setScrollTrackColor(graphicTheme.getLightBrownColor());
		stockList.setDivider(graphicTheme.getDividerColor(), 1);
		
		return stockList;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createButtonPanel() {
		FlatPanel stockMgmtBtnPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		refreshConsumptionBtn = createRefreshConsumptionBtn();
		saveAdditionBtn = createSaveStockBtn();
		FlatButton exitBtn = createExitBtn();
		
		stockMgmtBtnPanel.add(refreshConsumptionBtn, createCommonConstraints(3));
		stockMgmtBtnPanel.add(saveAdditionBtn, createCommonConstraints(2));
		stockMgmtBtnPanel.add(exitBtn, createCommonConstraints(2));
		exitBtn.setOnClickListener(getListenerToExitFrame());
		
		return stockMgmtBtnPanel;
	}
	
	private FlatButton createRefreshConsumptionBtn() {
		FlatButton refreshConsumptionBtn = new FlatButton("소모량 갱신");
		refreshConsumptionBtn.setBackground(graphicTheme.getBasicBrownColor());
		return refreshConsumptionBtn;
	}
	
	private FlatButton createSaveStockBtn() {
		FlatButton saveStockBtn = new FlatButton("저장");
		saveStockBtn.setBackground(graphicTheme.getSkyBlueColor());
		return saveStockBtn;
	}
	
	private FlatButton createExitBtn() {
		FlatButton exitBtn = new FlatButton("취소");
		exitBtn.setBackground(graphicTheme.getPinkColor());
		return exitBtn;
	}
	
	////////////////////Listener and related methods///////////////////////////////////
	private void setStockManageListOnView(OrderHistory orderHistory) {
		int stockListLength = orderHistory.getStockItemsSize();
		for(int index = 0; index < stockListLength; index++) {
			SingleStockManageView area = new SingleStockManageView(index, orderHistory);
			
			area.setStockName(orderHistory.getStockItemsByIndex(index).getName());
			area.setStockRemain(orderHistory.getStockItemsByIndex(index).getAmount());
			area.setStockConsumption(orderHistory.getStockItemsByIndex(index).getConsumtion());
			
			stockList.addItem(area);
		}
	}
	
	private OnClickListener getListenerToRefreshItemsOnList(OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				refreshItemsOnStockList(orderHistory);
			}
		};
	}
	
	private void refreshItemsOnStockList(OrderHistory orderHistory) {
		stockList.removeItems(0, orderHistory.getStockItemsSize()-1);
		
		setStockManageListOnView(orderHistory);
	}
	
	private OnClickListener getListenerToSaveStock(OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
					orderHistory.saveStock();
					showSaveSuccessDialog();
			}
		};
	}
	
	private OnClickListener getListenerToExitFrame() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				frame.hide();
			}
		};
	}

////////////////////////dialog//////////////////////////////////////////
	
	private void showSaveSuccessDialog() {
		new FlatDialog.Builder()
		.setTitle("알림")
		.setContent("저장 성공")
		.setLocationRelativeTo(frame.getJFrame())
		.build()
		.show();
	}

}
