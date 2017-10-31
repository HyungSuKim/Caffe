import java.awt.Component;
import java.util.List;

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
import com.mommoo.flat.list.listener.OnSelectionListener;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.flat.text.textarea.alignment.FlatVerticalAlignment;
import com.mommoo.util.ScreenManager;

/**
 * summary : it shows cafe's sell history
 * details : this frame shows cafe's sell history since program runs
 *         : it show buyer and purchase prices as a summary list
 *         : as we click refresh button we can get recent purchase more
 *         : and if we click item on list and click detail button we can look details about purchase content
 *         : what kind of stuff they bought and counts and total price
 *         : we show member customer as hpNum and nonmember to "비회원 주문"
 * arguments : button = btn
 * @author 김형수
 *
 */

public class SellHistoryFrame {
// 17. 10. 24 수정
	private static GraphicTheme graphicTheme = new GraphicTheme();
	private static FlatLabel totalPriceContentLabel = createTotalPriceContentLabel();
	private static int selectedIndex = -1;
	private static int previousOrderHistorySize = 0;
	private static int totalPrice = 0;
	
	private FlatFrame frame = createFrame();
	private FlatListView<SingleSellHistoryView> sellHistoryList = createSellHistoryList();
	FlatButton refreshBtn = createRefreshBtn();
	FlatButton detailBtn = createDetailBtn();
	
	SellHistoryFrame(OrderHistory orderHistory) {
		previousOrderHistorySize = orderHistory.getOrderHistory().size();
		
		frame.getContainer().add(createListInfoView(), createCommonConstraints(1));
				
		frame.getContainer().add(createSellHistoryListView(), createCommonConstraints(4));
		sellHistoryList.setOnSelectionListener(getListenerToSetSelectIndex());
		setSellHistoryOnList(orderHistory);
		
		frame.getContainer().add(createTotalSellPriceView(), createCommonConstraints(1));
		
		frame.getContainer().add(createButtonPanel(), createCommonConstraints(1));
		refreshBtn.setOnClickListener(getListenerToRefreshSellHistoryList(orderHistory));
		detailBtn.setOnClickListener(getListenerToGetDetailOrderHistory(orderHistory));

		frame.show();
	}
	
	//////////////////////Common methods/////////////////////////
	
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}
	
	private FlatDialog.Builder createCommonBuilder() {
		return new FlatDialog.Builder()
		.setLocationRelativeTo(frame.getJFrame());
	}
	
	///////////////////////gui starts///////////////////////////
	
	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();
		
		frame.setTitle("판매내역");
		frame.setSize(frameSM.dip2px(200), frameSM.dip2px(240));
		frame.setLocationOnScreenCenter();
		frame.setWindowExit(false);
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		
		return frame;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createListInfoView() {
		FlatPanel listInfoPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		
		listInfoPanel.add(createPurchasePeopleLabel(), createCommonConstraints(1));
		listInfoPanel.add(createPurchasePriceLabel(), createCommonConstraints(1));
		
		return listInfoPanel;
	}
	
	private FlatLabel createPurchasePeopleLabel() {
		FlatLabel purchasePeopleLabel = new FlatLabel("구매자");
		
		purchasePeopleLabel.setBackground(graphicTheme.getBasicBrownColor());
		purchasePeopleLabel.setOpaque(true);
		purchasePeopleLabel.setFont(graphicTheme.getBoldFont20pt());
		purchasePeopleLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		purchasePeopleLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		
		return purchasePeopleLabel;
	}
	
	private FlatLabel createPurchasePriceLabel() {
		FlatLabel purchaseAmountLabel = new FlatLabel("금액");
		
		purchaseAmountLabel.setBackground(graphicTheme.getBasicBrownColor());
		purchaseAmountLabel.setOpaque(true);
		purchaseAmountLabel.setFont(graphicTheme.getBoldFont20pt());
		purchaseAmountLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		purchaseAmountLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		
		return purchaseAmountLabel;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createSellHistoryListView() {
		FlatPanel sellHistoryListPanel = new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
		
		sellHistoryListPanel.setBackground(graphicTheme.getDarkBrownColor());
		sellHistoryListPanel.setOpaque(true);
		sellHistoryListPanel.add(sellHistoryList.getComponent(), createCommonConstraints(1));
		
		return sellHistoryListPanel;
	}
	
	private FlatListView<SingleSellHistoryView> createSellHistoryList() {
		FlatListView<SingleSellHistoryView> sellHistoryList = new FlatListView<SingleSellHistoryView>();
		
		sellHistoryList.setBackgroundColor(graphicTheme.getDarkBrownColor());
		sellHistoryList.getScroller().setScrollBarColor(graphicTheme.getDarkerBrownColor());
		sellHistoryList.setSelectionColor(graphicTheme.getDarkBrownColor());
		sellHistoryList.setSingleSelectionMode(true);
		sellHistoryList.getScroller().setScrollTrackColor(graphicTheme.getLightBrownColor());
		sellHistoryList.setDivider(graphicTheme.getDividerColor(), 1);
		
		return sellHistoryList;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createTotalSellPriceView() {
		FlatPanel sellTotalPricePanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		
		sellTotalPricePanel.add(createTotalPriceLabel(), createCommonConstraints(1));
		sellTotalPricePanel.add(totalPriceContentLabel, createCommonConstraints(1));

		return sellTotalPricePanel;
	}
	
	private FlatLabel createTotalPriceLabel() {
		FlatLabel totalPriceLabel = new FlatLabel("총금액 :");
		
		totalPriceLabel.setBackground(graphicTheme.getBasicBrownColor());
		totalPriceLabel.setOpaque(true);
		totalPriceLabel.setFont(graphicTheme.getBoldFont20pt());
		totalPriceLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		totalPriceLabel.setHorizontalAlignment(FlatHorizontalAlignment.RIGHT);
		
		return totalPriceLabel;
	}
	
	private static FlatLabel createTotalPriceContentLabel() {
		FlatLabel totalPriceContentLabel = new FlatLabel("");
		
		totalPriceContentLabel.setBackground(graphicTheme.getBasicBrownColor());
		totalPriceContentLabel.setOpaque(true);
		totalPriceContentLabel.setFont(graphicTheme.getBoldFont20pt());
		totalPriceContentLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		totalPriceContentLabel.setHorizontalAlignment(FlatHorizontalAlignment.LEFT);
		
		return totalPriceContentLabel;
	}
	
	///////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createButtonPanel() {
		FlatPanel sellHistoryBtnPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL, 0));
		FlatButton exitBtn = createExitBtn();
		
		sellHistoryBtnPanel.add(refreshBtn, createCommonConstraints(1));
		sellHistoryBtnPanel.add(detailBtn, createCommonConstraints(1));
		sellHistoryBtnPanel.add(exitBtn, createCommonConstraints(1));
		exitBtn.setOnClickListener(getListenerToExitSellHistoryFrame());
		
		return sellHistoryBtnPanel;
	}
	
	private FlatButton createRefreshBtn() {
		FlatButton refreshBtn = new FlatButton("갱신");
		refreshBtn.setBackground(graphicTheme.getDarkBrownColor());
		return refreshBtn;
	}
	
	private FlatButton createDetailBtn() {
		FlatButton detailBtn = new FlatButton("상세내역");
		detailBtn.setBackground(graphicTheme.getSkyBlueColor());
		return detailBtn;
	}
	
	private FlatButton createExitBtn() {
		FlatButton exitBtn = new FlatButton("취소");
		exitBtn.setBackground(graphicTheme.getPinkColor());
		return exitBtn;
	}
	
	//////////////////Listener and related methods//////////////////////
	
	private OnClickListener getListenerToRefreshSellHistoryList(OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				if(previousOrderHistorySize!=0) {
					sellHistoryList.removeItems(0, previousOrderHistorySize-1);
					setSellHistoryOnList(orderHistory);
				} else {
					setSellHistoryOnList(orderHistory);
				}
			}
		};
	}
	
	private OnSelectionListener<SingleSellHistoryView> getListenerToSetSelectIndex() {
		return new OnSelectionListener<SingleSellHistoryView>() {
			@Override
			public void onSelection(int beginIndex, int endIndex, List<SingleSellHistoryView> selectionList) {
				selectedIndex = beginIndex;
			}
		};
	}

	private void setSellHistoryOnList(OrderHistory orderHistory) {
		totalPrice = 0;
		previousOrderHistorySize = orderHistory.getOrderHistory().size();
		
		for(int index = 0; index < previousOrderHistorySize; index++) {
		    SingleSellHistoryView area = new SingleSellHistoryView(orderHistory.getOrderHistory().get(index)[0], orderHistory.getOrderHistory().get(index)[1]);
		    sellHistoryList.addItem(area);
		    totalPrice += Integer.parseInt(orderHistory.getOrderHistory().get(index)[1]);
		}
		
		totalPriceContentLabel.setText(Integer.toString(totalPrice)+"￦");
		sellHistoryList.getScroller().scrollByValue(0);
	}

	
	private OnClickListener getListenerToGetDetailOrderHistory(OrderHistory orderHistory) {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				getDetailOrderHistory(orderHistory);
			}
		};
	}
	
	private void getDetailOrderHistory(OrderHistory orderHistory) {
		if(selectedIndex != -1) {
			showDetailOrderHistory(orderHistory);
			selectedIndex = -1;
		} else {
			showSelectionRequired();
		}
	}
	
	private void showDetailOrderHistory(OrderHistory orderHistory) {
		createCommonBuilder()
		.setTitle("상세내역")
		.setContent(orderHistory.getSelectedOrderHistory(selectedIndex))
		.build()
		.show();
	}
	
	private void showSelectionRequired() {
		createCommonBuilder()
		.setTitle("알림")
		.setContent("확인하고자 하는 내역을 선택해주세요.")
		.build()
		.show();
	}

	private OnClickListener getListenerToExitSellHistoryFrame() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				frame.hide();
			}
		};
	}
}
