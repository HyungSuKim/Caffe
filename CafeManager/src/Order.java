import java.util.HashSet;

class Order {
	private String[] name;
	private String[] cup;
	private int[] count;
	private OrderHistory orderHistory;
	private int orderLength;
	private int stockItemsSize;
	private boolean result = true;
	private HashSet<String> hashSet = new HashSet<String>();
	private String resultInfo = "";

	Order(String[] name, String[] cup, int[] count, OrderHistory orderHistory) {
		this.name = name;
		this.cup = cup;
		this.count = count;
		this.orderHistory = orderHistory;
		orderLength = name.length;
		stockItemsSize = orderHistory.getStockItemsSize();
		makeOrder();
	}

	private void makeOrder() {
		for(int index = 0; index < orderLength; index++) {
			if (cup[index].equals("일회용")) {
				
				setConsumtionOnceCup(count[index]);
				
			}
			
			if (name[index].equals("에스프레소") || name[index].equals("아메리카노")) {

				setConsumtionCoffeeBean(count[index]);

			} else if (name[index].equals("카푸치노") || name[index].equals("카페라떼")) {

				setConsumtionCoffeeBean(count[index]);

				setConsumtionMilk(count[index]);

			} else if (name[index].equals("바닐라라떼")) {

				setConsumtionCoffeeBean(count[index]);

				setConsumtionMilk(count[index]);

				setConsumtionVanilla(count[index]);

			} else if (name[index].equals("카라멜카페모카")) {

				setConsumtionCoffeeBean(count[index]);

				setConsumtionCaramel(count[index]);

				setConsumtionMoca(count[index]);

			} else if (name[index].equals("화이트초코카페모카")) {

				setConsumtionCoffeeBean(count[index]);

				setConsumtionMilk(count[index]);

				setConsumtionChoco(count[index]);

				setConsumtionMoca(count[index]);

			} else if (name[index].equals("카라멜마끼아또")) {

				setConsumtionCoffeeBean(count[index]);

				setConsumtionCaramel(count[index]);

			} else if (name[index].equals("녹차라떼")) {

				setConsumtionGreenTea(count[index]);

				setConsumtionMilk(count[index]);

			} else if (name[index].equals("고구마라떼")) {

				setConsumtionSweetPotato(count[index]);

				setConsumtionMilk(count[index]);

			} else if (name[index].equals("핫초코")) {

				setConsumtionChoco(count[index]);

				setConsumtionMilk(count[index]);

			} else if (name[index].equals("민트초코")) {
				setConsumtionMint(count[index]);

				setConsumtionChoco(count[index]);
			} else {
				setStockItemConsumtion(name[index], count[index]);
			}
		}
		setResultInfo();
	}

	private void setStockItemConsumtion(String name, int count) {
		String[] splitedName = name.split(" ");
		int stockItemsSize = orderHistory.getStockItemsSize();
		int splitedNameLength = splitedName.length;
		for (int index = 0; index < splitedNameLength; index++) {
			for (int jIndex = 0; jIndex < stockItemsSize; jIndex++) {
				if (orderHistory.getStockItemsByIndex(jIndex).getName().equals(splitedName[index])
						&&!orderHistory.getStockItemsByIndex(jIndex).use(count)) {
					result = false;
					hashSet.add(orderHistory.getStockItemsByIndex(jIndex).getStockInfo());
				}
			}
		}
	}

	private void setConsumtionOnceCup(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("일회용 컵")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionCoffeeBean(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("커피원두")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionMilk(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("우유")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionCaramel(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("카라멜")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionVanilla(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("바닐라")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionMoca(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("모카")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionChoco(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("초코")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionGreenTea(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("녹차")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionSweetPotato(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("고구마")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionMint(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
		if (orderHistory.getStockItemsByIndex(index).getName().equals("민트")
				&&!orderHistory.getStockItemsByIndex(index).use(count)) {
			result = false;
			hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}
	
	private void setResultInfo() {
		if(!hashSet.isEmpty()) {
			for(String content : hashSet) {
				resultInfo += content + System.lineSeparator();
			}
		}
	}
	
	boolean getResult() {
		return result;
	}
	
	String getResultInfo() {
		return resultInfo;
	}
}
