import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

class OrderHistory {

	private static File stock = new File("Stock.txt");

	private ArrayList<String[]> orderHistory;
	private ArrayList<Stock> stockItemsStockList;
	private ArrayList<String[]> stockItemsStringList;

	OrderHistory() {
		try {
			orderHistory = new ArrayList<>();
			stockItemsStockList = new ArrayList<>();
			stockItemsStringList = new ArrayList<>();
			FileInputStream fis = new FileInputStream(stock);
			byte[] buffer = new byte[(int) stock.length()];
			fis.read(buffer);
			String totalContent = new String(buffer);
			String[] singleContent = totalContent.split(System.lineSeparator());
			for (String temp : singleContent) {
				stockItemsStringList.add(temp.split("/"));
			}

			for (String[] temp2 : stockItemsStringList) {
				stockItemsStockList.add(new Stock(temp2[0], Integer.parseInt(temp2[1])));
			}
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void addOrderHistory(String buyer, String totalPrice, String purchaseContent) {
		String[] temp = new String[3];
		temp[0] = buyer;
		temp[1] = totalPrice;
		temp[2] = purchaseContent;
		orderHistory.add(temp);
	}

	ArrayList<String[]> getOrderHistory() {
		return orderHistory;
	}

	ArrayList<Stock> getStockItems() {
		return stockItemsStockList;
	}

	int getStockItemsSize() {
		return stockItemsStockList.size();
	}

	Stock getStockItemsByIndex(int index) {
		return stockItemsStockList.get(index);
	}

	void saveStock() {
		try {
			FileOutputStream fos = new FileOutputStream(stock);
			String outputString = "";
			int stockItemsSize = getStockItemsSize();
			for (int i = 0; i < stockItemsSize; i++) {
				if (i == (stockItemsSize - 1)) {
					outputString += stockItemsStockList.get(i).getName() + "/"
							+ stockItemsStockList.get(i).getAmount();
				} else {
					outputString += stockItemsStockList.get(i).getName() + "/"
							+ stockItemsStockList.get(i).getAmount() + System.lineSeparator();
				}
			}
			fos.write(outputString.getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	String getSelectedOrderHistory(int selectedIndex) {
		return "구매자 : " + orderHistory.get(selectedIndex)[0] + System.lineSeparator()
		+ "금액 : " + orderHistory.get(selectedIndex)[1] + "￦" + System.lineSeparator()
		+ "상세내역 : " + orderHistory.get(selectedIndex)[2];
	}
}
