import java.awt.Component;
import java.time.LocalTime;
import java.util.List;

import javax.swing.BorderFactory;

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
 * summary : you can select order menu(coffee/drink/food) and purchase it
 * details : it has 2 list view to show menu(1. coffee/drink, 2. food)
 *         : you can select items by double click on menu
 *         : if you select items those selected items will be add on another list
 *         : you can change count, cup, hot&cold, add shot and you can see price of item
 *         : also you can show total price of items you selected
 *         : and if you purchase a dialog will show that you selected
 * arguments : HotCold = HC
 * @author 김형수
 *
 */
//17.10.26. 수정
public class OrderMenuFrame {
	private final int COFFEE = 1;
	private final int DRINK = 2;
	private final int FOOD = 3;
	
	private FlatFrame frame = createFrame();

	private FlatLabel priceTotalLabel = createPriceTotalLabel();
	
	private FlatListView<SingleDrinkView> drinkMenuList = createDrinkMenuList();
	private FlatListView<SingleFoodView> foodMenuList = createFoodMenuList();
	private FlatListView<SelectedMenuPanel> selectedMenuList = createSelectedMenuList();
	
	private FlatButton resetOrderBtn = createResetOrderBtn();
	private FlatButton menuOrderBtn = createMenuOrderBtn();

	private String usingMember = "";
	private int variablePrice = 0;
	private int index = -1;
	
	private static MenuManager menuManager = new MenuManager();
	private static GraphicTheme graphicTheme = new GraphicTheme();
	
	private OrderHistory orderHistory;
	
	OrderMenuFrame(String usingMember, OrderHistory orderHistory) {
		this.usingMember = usingMember;
		this.orderHistory = orderHistory;
		
		if(usingMember.equals(""))
			this.usingMember = "비회원주문";
		
		frame.getContainer().add(createMenuInfoView(), createCommonConstraints(2));

		
		frame.getContainer().add(createMenuListPanel(), createCommonConstraints(8));
		setDrinkMenuOnList();
		setFoodMenuOnList();
		drinkMenuList.setOnSelectionListener(getListenerToAddDrinkOnSelectedMenu());
		foodMenuList.setOnSelectionListener(getListenerToAddFoodOnSelectedMenu());
		
		
		frame.getContainer().add(createSelectedMenuPanel(), createCommonConstraints(10));
		selectedMenuList.setOnSelectionListener(getListenerToRemoveOneSelectedMenu());
		
		
		frame.getContainer().add(createButtonPanel(), createCommonConstraints(3));
		resetOrderBtn.setOnClickListener(getListenerToResetOrder());
		menuOrderBtn.setOnClickListener(getListenerToOrder());
		
		frame.show();
	}
	//////////////////////////////////////////////////////////////////////////////////////
	
	void setVariablePriceOfSCP(int scpVariablePrice) {
		priceTotalLabel.setText(scpVariablePrice+"￦");
		variablePrice = scpVariablePrice;
	}
	
	/////////////////////////Common methods/////////////////////////
	
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}
	
	private FlatDialog.Builder createCommonBuilder() {
		return new FlatDialog.Builder()
		.setLocationRelativeTo(frame.getJFrame());
	}
	
	//////////////////////////Gui starts///////////////////////////////////
	
	private FlatFrame createFrame() {
		FlatFrame frame = new FlatFrame();
		ScreenManager frameSM = ScreenManager.getInstance();
		
		frame.setTitle("주문하기");
		frame.setLocationOnScreenCenter();
		frame.setSize(frameSM.dip2px(300), frameSM.dip2px(400));
		frame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		frame.setResizable(true);
		frame.setIconImage(graphicTheme.getCoffeeImage());
		frame.setProcessIconImage(graphicTheme.getCoffeeImage());
		frame.setTitleBarColor(graphicTheme.getTitleBarColor());
		
		return frame;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////	
	
	private FlatPanel createMenuInfoView() {
		FlatPanel menuInfoPanel = new FlatPanel(new LinearLayout(0));
		
		menuInfoPanel.add(createDrinkMenuLabel(), createCommonConstraints(1));
		menuInfoPanel.add(createFoodMenuLabel(), createCommonConstraints(1));
		
		return menuInfoPanel;
	}
	
	private FlatLabel createDrinkMenuLabel() {
		FlatLabel drinkMenuLabel = new FlatLabel("Drink Menu");
		
		drinkMenuLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		drinkMenuLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		drinkMenuLabel.setBackground(graphicTheme.getBasicBrownColor());
		drinkMenuLabel.setOpaque(true);
		drinkMenuLabel.setFont(graphicTheme.getBoldFont20pt());
		
		return drinkMenuLabel;
	}
	
	private FlatLabel createFoodMenuLabel() {
		FlatLabel foodMenuLabel = new FlatLabel("Food Menu");
		foodMenuLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		foodMenuLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		foodMenuLabel.setBackground(graphicTheme.getBasicBrownColor());
		foodMenuLabel.setOpaque(true);
		foodMenuLabel.setFont(graphicTheme.getBoldFont20pt());
		return foodMenuLabel;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////	
	
	private FlatPanel createMenuListPanel() {
		FlatPanel listMenuPanel = new FlatPanel(new LinearLayout(0));
		listMenuPanel.add(drinkMenuList.getComponent(), createCommonConstraints(1));
		listMenuPanel.add(foodMenuList.getComponent(), createCommonConstraints(1));
		return listMenuPanel;
	}
	
	private FlatListView<SingleDrinkView> createDrinkMenuList() {
		FlatListView<SingleDrinkView> drinkMenuList = new FlatListView<>();
		
		drinkMenuList.getScroller().setScrollBarColor(graphicTheme.getDarkerBrownColor());
		drinkMenuList.setSelectionColor(graphicTheme.getDarkerBrownColor());
		drinkMenuList.setSingleSelectionMode(true);
		drinkMenuList.getScroller().setScrollTrackColor(graphicTheme.getBasicBrownColor());
		drinkMenuList.getScroller().scrollByValue(0);
		drinkMenuList.setDivider(graphicTheme.getDividerColor(), 1);
		
		return drinkMenuList;
	}
	
	private FlatListView<SingleFoodView> createFoodMenuList() {
		FlatListView<SingleFoodView> foodMenuList = new FlatListView<>();
		
		foodMenuList.getScroller().setScrollBarColor(graphicTheme.getDarkerBrownColor());
		foodMenuList.setSelectionColor(graphicTheme.getDarkerBrownColor());
		foodMenuList.setSingleSelectionMode(true);
		foodMenuList.getScroller().setScrollTrackColor(graphicTheme.getBasicBrownColor());
		foodMenuList.getScroller().scrollByValue(0);
		foodMenuList.setDivider(graphicTheme.getDividerColor(), 1);
		
		return foodMenuList;
	}

	//////////////////////////////////////////////////////////////////////////////////////	
	
	private FlatPanel createSelectedMenuPanel() {
		FlatPanel selectedMenuPanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL));
		FlatPanel inSelectedMenuPanel = createInSelectedMenuPanel();
		
		selectedMenuPanel.setBackground(graphicTheme.getBasicBrownColor());
		selectedMenuPanel.setOpaque(true);
		selectedMenuPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		selectedMenuPanel.add(inSelectedMenuPanel, createCommonConstraints(1));		
		inSelectedMenuPanel.add(createSelectedMenuGuidePanel(), createCommonConstraints(1));
		inSelectedMenuPanel.add(selectedMenuList.getComponent(), createCommonConstraints(7));
		inSelectedMenuPanel.add(createTotalPricePanel(), createCommonConstraints(1));
		
		return selectedMenuPanel;
	}
	
	private FlatPanel createInSelectedMenuPanel() {
		FlatPanel inSelectedMenuPanel = new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
		
		inSelectedMenuPanel.setBackground(graphicTheme.getDarkBrownColor());
		inSelectedMenuPanel.setOpaque(true);
		
		return inSelectedMenuPanel;
	}
	
	private FlatPanel createSelectedMenuGuidePanel() {
		FlatPanel selectedMenuGuidePanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL));
		
		selectedMenuGuidePanel.add(createNameGuideLabel(), createCommonConstraints(7));
		selectedMenuGuidePanel.add(createCountGuideLabel(), createCommonConstraints(4));
		selectedMenuGuidePanel.add(createCupGuideLabel(), createCommonConstraints(4));
		selectedMenuGuidePanel.add(createHCGuideLabel(), createCommonConstraints(4));
		selectedMenuGuidePanel.add(createShotGuideLabel(), createCommonConstraints(3));
		selectedMenuGuidePanel.add(createPriceGuideLabel(), createCommonConstraints(3));
		
		return selectedMenuGuidePanel;
	}
	
	private FlatLabel createNameGuideLabel() {
		FlatLabel nameGuideLabel = new FlatLabel("선택한상품");
		
		nameGuideLabel.setFont(graphicTheme.getBoldFont15pt());
		nameGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		nameGuideLabel.setBackground(graphicTheme.getDarkBrownColor());
		
		return nameGuideLabel;
	}
	
	private FlatLabel createCountGuideLabel() {
		FlatLabel countGuideLabel = new FlatLabel("수량");
		
		countGuideLabel.setFont(graphicTheme.getBoldFont15pt());
		countGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		countGuideLabel.setBackground(graphicTheme.getDarkBrownColor());
		
		return countGuideLabel;
	}
	
	private FlatLabel createCupGuideLabel() {
		FlatLabel cupGuideLabel = new FlatLabel("잔종류");
		
		cupGuideLabel.setFont(graphicTheme.getBoldFont15pt());
		cupGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		cupGuideLabel.setBackground(graphicTheme.getDarkBrownColor());
		
		return cupGuideLabel;
	}
	
	private FlatLabel createHCGuideLabel() {
		FlatLabel hcGuideLabel = new FlatLabel("Hot/Cold");
		
		hcGuideLabel.setFont(graphicTheme.getBoldFont15pt());
		hcGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		hcGuideLabel.setBackground(graphicTheme.getDarkBrownColor());
		
		return hcGuideLabel;
	}
	
	private FlatLabel createShotGuideLabel() {
		FlatLabel shotGuideLabel = new FlatLabel("샷추가");
		
		shotGuideLabel.setFont(graphicTheme.getBoldFont15pt());
		shotGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		shotGuideLabel.setBackground(graphicTheme.getDarkBrownColor());
		
		return shotGuideLabel;
	}
	
	private FlatLabel createPriceGuideLabel() {
		FlatLabel priceGuideLabel = new FlatLabel("가격");
		
		priceGuideLabel.setFont(graphicTheme.getBoldFont15pt());
		priceGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		priceGuideLabel.setBackground(graphicTheme.getDarkBrownColor());
		
		return priceGuideLabel;
	}
	
	private FlatListView<SelectedMenuPanel> createSelectedMenuList() {
		FlatListView<SelectedMenuPanel> selectedMenuList = new FlatListView<>();
		
		selectedMenuList.setSingleSelectionMode(true);
		selectedMenuList.setBackgroundColor(graphicTheme.getDarkBrownColor());
		selectedMenuList.getScroller().setScrollTrackColor(graphicTheme.getBasicBrownColor());
		selectedMenuList.getScroller().setScrollBarColor(graphicTheme.getDarkerBrownColor());
		selectedMenuList.setSelectionColor(graphicTheme.getDarkerBrownColor());
		selectedMenuList.setDivider(graphicTheme.getDividerColor(), 1);
		
		return selectedMenuList;
	}
	
	private FlatPanel createTotalPricePanel() {
		FlatPanel totalPricePanel = new FlatPanel(new LinearLayout(Orientation.HORIZONTAL));
		
		totalPricePanel.add(createTotalPriceGuideLabel(), createCommonConstraints(3));
		totalPricePanel.add(priceTotalLabel, createCommonConstraints(1));
		
		return totalPricePanel;
	}
	
	private FlatLabel createTotalPriceGuideLabel() {
		FlatLabel totalPriceGuideLabel = new FlatLabel("총금액 :");
		
		totalPriceGuideLabel.setFont(graphicTheme.getBoldFont20pt());
		totalPriceGuideLabel.setHorizontalAlignment(FlatHorizontalAlignment.RIGHT);
		totalPriceGuideLabel.setBackground(graphicTheme.getDarkBrownColor());
		
		return totalPriceGuideLabel;
	}
	
	private FlatLabel createPriceTotalLabel() {
		FlatLabel priceTotalLabel = new FlatLabel();
		
		priceTotalLabel.setFont(graphicTheme.getBoldFont20pt());
		priceTotalLabel.setBackground(graphicTheme.getDarkBrownColor());
		
		return priceTotalLabel;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createButtonPanel() {
		FlatPanel buttonPanel = new FlatPanel(new LinearLayout(0));
		FlatButton exitBtn = createExitOrderMenuBtn();
		
		buttonPanel.add(resetOrderBtn, createCommonConstraints(1));
		buttonPanel.add(menuOrderBtn, createCommonConstraints(1));
		buttonPanel.add(exitBtn, createCommonConstraints(1));
		exitBtn.setOnClickListener(getListenerToExitOrderMenuFrame());
		
		return buttonPanel;
	}
	
	private FlatButton createResetOrderBtn() {
		FlatButton resetOrderBtn = new FlatButton("초기화");
		
		resetOrderBtn.setBackground(graphicTheme.getBasicBrownColor());
		
		return resetOrderBtn;
	}
	
	private FlatButton createMenuOrderBtn() {
		FlatButton menuOrderBtn = new FlatButton("주문하기");
		
		menuOrderBtn.setBackground(graphicTheme.getSkyBlueColor());
		
		return menuOrderBtn;
	}
	
	private FlatButton createExitOrderMenuBtn() {
		FlatButton exitOrderMenuBtn = new FlatButton("취소");
		
		exitOrderMenuBtn.setBackground(graphicTheme.getPinkColor());
		
		return exitOrderMenuBtn;
	}
	
/////////////////////////Listener and related methods////////////////////////////
	
	private OnClickListener getListenerToResetOrder() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				variablePrice = 0;
				
				priceTotalLabel.setText(Integer.toString(variablePrice) + "￦");
				
				selectedMenuList.removeItems(0, selectedMenuList.getItemSize()-1);
			}
		};
	}
	
	private OnClickListener getListenerToOrder() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				String orderListContent = "";
				String stockNotAvailable = "";
				int selectedMenuListSize = selectedMenuList.getItemSize();
				
				String[] name = new String[selectedMenuListSize];
				String[] cup = new String[selectedMenuListSize];
				int[] count = new int[selectedMenuListSize];
				
				boolean stockAvailable = true;

				for(int index = 0; index < selectedMenuListSize; index++) {
					orderListContent += getOrderListSingleContent(index);
					name[index] = selectedMenuList.getItem(index).getInitName();
					cup[index] = selectedMenuList.getItem(index).getCup();
					count[index] = selectedMenuList.getItem(index).getCounter();
				}
				
				
				Order order = new Order(name, cup, count, orderHistory);
				if(!order.getResult()) {
					stockAvailable = false;
					stockNotAvailable += order.getResultInfo();
				}
				
				
				if(stockAvailable == true) {
					showOrderSuccessDialog(orderListContent);
				} else if(stockAvailable == false) {
					showUnuseableStockExistDialog(stockNotAvailable);
					stockAvailable = true;
					stockNotAvailable = "";
				}
			}
		};
	}
	
	private void applyOrder(String orderListContent) {
		orderHistory.addOrderHistory(OrderMenuFrame.this.usingMember
				, Integer.toString(variablePrice), orderListContent);
		
		variablePrice = 0;
		priceTotalLabel.setText(Integer.toString(variablePrice) + "￦");
		
		selectedMenuList.removeItems(0, selectedMenuList.getItemSize()-1);
	}
	
	private String getOrderListSingleContent(int index) {
		return selectedMenuList.getItem(index).getInitName() + " : "
				+ selectedMenuList.getItem(index).getCounter() + "개"
				+ System.lineSeparator();
	}
	

	private OnClickListener getListenerToExitOrderMenuFrame() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				frame.hide();
			}
		};
	}
	
	
	private void setDrinkMenuOnList() {
		for (String drinkContent : menuManager.getDrinkContents()) {
			String[] temp = drinkContent.split("/");
			
			SingleDrinkView area = new SingleDrinkView();
			area.setSingleDrink(temp[0], temp[1]);
            drinkMenuList.addItem(area);
        }
	}
	
	private void setFoodMenuOnList() {
		for (String foodContent : menuManager.getFoodContents()) {
			String[] temp = foodContent.split("/");
			
			SingleFoodView area = new SingleFoodView();
			area.setSingleFood(temp[0], temp[1]);
            foodMenuList.addItem(area);
		}
	}
	
	private OnSelectionListener<SingleDrinkView> getListenerToAddDrinkOnSelectedMenu() {
		return new OnSelectionListener<SingleDrinkView>() {
			private int firstClick = 0;
			private int previousIndex = -1;
			@Override
			public void onSelection(int beginIndex, int endIndex, List<SingleDrinkView> selectionList) {
				LocalTime startTime = LocalTime.now();
				
				if(previousIndex != beginIndex) {				
					firstClick = startTime.getSecond();
					previousIndex = beginIndex;
					
				} else {
					if(firstClick == startTime.getSecond()) {
						previousIndex = -1;
						addSelectedMenuFromDrinkMenu(beginIndex);
					}
				}
			}
		};
	}
	
	private OnSelectionListener<SingleFoodView> getListenerToAddFoodOnSelectedMenu() {
		return new OnSelectionListener<SingleFoodView>() {
			private int firstClick = 0;
			private int previousIndex = -1;
			@Override
			public void onSelection(int beginIndex, int endIndex, List<SingleFoodView> selectionList) {
				LocalTime startTime = LocalTime.now();
				
				if(previousIndex != beginIndex) {
					firstClick = startTime.getSecond();
					previousIndex = beginIndex;
					
				} else {
					if(firstClick==startTime.getSecond()) {
						previousIndex = -1;
						addSelectedMenuFromFoodMenu(beginIndex);
					}
				}
			}
		};
	}
	
	private void addSelectedMenuFromDrinkMenu(int beginIndex) {
		String selectedMenu = drinkMenuList.getItem(beginIndex).getDrinkName()+"/"+drinkMenuList.getItem(beginIndex).getDrinkPrice();
		
		String productName = MenuManager.contentSpliter(selectedMenu)[0];
		
		if(productName.contains("프레스치노")||productName.contains("쥬스")
				||productName.contains("에이드")||productName.contains("스무디")
				||productName.contains("아이스")) {
			
			setOnSelectedMenuList(selectedMenu, DRINK);
		} else {
			
			setOnSelectedMenuList(selectedMenu, COFFEE);
		}
	}
	
	private void addSelectedMenuFromFoodMenu(int beginIndex) {
		String selectedMenu = foodMenuList.getItem(beginIndex).getFoodName()+"/"+foodMenuList.getItem(beginIndex).getFoodPrice();
		
		setOnSelectedMenuList(selectedMenu, FOOD);
	}
	
	private void setOnSelectedMenuList(String selectedMenu, int kind) {
		SelectedMenuPanel selectedItem = new SelectedMenuPanel(MenuManager.contentSpliter(selectedMenu)[0],
				MenuManager.contentSpliter(selectedMenu)[1], kind);
		
		setOnSelectedCoffePanel(selectedItem);
		selectedMenuList.addItem(selectedItem);
		
		variablePrice += Integer.parseInt(MenuManager.contentSpliter(selectedMenu)[1]);
		priceTotalLabel.setText(Integer.toString(variablePrice)+"￦");
	}
	
	void setOnSelectedCoffePanel(SelectedMenuPanel cof) {
		cof.setOrderMenuFrameOnSCP(this);
	}
	
	private OnSelectionListener<SelectedMenuPanel> getListenerToRemoveOneSelectedMenu() {
		return new OnSelectionListener<SelectedMenuPanel>() {
			private int firstClick = 0;
			private int previousIndex = -1;
			@Override
			public void onSelection(int beginIndex, int endIndex, List<SelectedMenuPanel> selectionList) {
				LocalTime startTime = LocalTime.now();
				
				if(previousIndex != beginIndex) {				
					firstClick = startTime.getSecond();
					previousIndex = beginIndex;
					index = beginIndex;
					setVariablePrice();
					
				} else {
					if(firstClick == startTime.getSecond()) {
						previousIndex = -1;
						removeSelectedItemOnList(beginIndex);
					}
				}
			}
		};
	}
	
	private void removeSelectedItemOnList(int beginIndex) {
		variablePrice -= selectedMenuList.getItem(beginIndex).getVariablePrice();
		
		priceTotalLabel.setText(Integer.toString(variablePrice) + "￦");
		
		selectedMenuList.removeItem(beginIndex);
	}
	
	void setVariablePrice() {
		variablePrice = 0;
		
		for(int i=0; i<selectedMenuList.getItemSize(); i++) {
			variablePrice += selectedMenuList.getItem(i).getVariablePrice();
		}
		
		selectedMenuList.getItem(index).setCOMvariablePrice(variablePrice);
		index = -1;
	}
	
//////////////////////////////////Dialog////////////////////////////////////
	
	private void showUnuseableStockExistDialog(String stockNotAvailable) {
		createCommonBuilder()
		.setTitle("알림")
		.setContent("주문 중 재고가 부족한 상품이 있습니다."+System.lineSeparator()
		+"아래의 내용을 참고해서 조정해주세요."+System.lineSeparator()+System.lineSeparator()
		+stockNotAvailable)
		.setDialogWidth(500)
		.build()
		.show();
	}

	private void showCustomerOrderDialog(String orderListContent) {
		createCommonBuilder()
		.setTitle("비회원 주문내역")
		.setContent(orderListContent + "총금액 : " + variablePrice + "￦")
		.build()
		.show();
	}
	
	private void showClientOrderDialog(String orderListContent, int memberVariablePrice) {
		createCommonBuilder()
		.setTitle("회원 주문내역")
		.setContent(orderListContent + "총금액 : " + variablePrice + "￦" + System.lineSeparator()
		+ OrderMenuFrame.this.usingMember +"회원 할인(10%)적용 후" + "총금액 : " + memberVariablePrice
		+ "￦")
		.build()
		.show();
	}
	
	private void showOrderSuccessDialog(String orderListContent) {
		if(OrderMenuFrame.this.usingMember.equals("비회원주문")) {
			showCustomerOrderDialog(orderListContent);
			
			applyOrder(orderListContent);

		} else {
			int memberVariablePrice = (int) (variablePrice*0.9);
			
			showClientOrderDialog(orderListContent, memberVariablePrice);
			
			variablePrice = memberVariablePrice;
			
			applyOrder(orderListContent);
		}
	}
}