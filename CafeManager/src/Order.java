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
			if (cup[index].equals("��ȸ��")) {
				
				setConsumtionOnceCup(count[index]);
				
			}
			
			if (name[index].equals("����������") || name[index].equals("�Ƹ޸�ī��")) {

				setConsumtionCoffeeBean(count[index]);

			} else if (name[index].equals("īǪġ��") || name[index].equals("ī���")) {

				setConsumtionCoffeeBean(count[index]);

				setConsumtionMilk(count[index]);

			} else if (name[index].equals("�ٴҶ��")) {

				setConsumtionCoffeeBean(count[index]);

				setConsumtionMilk(count[index]);

				setConsumtionVanilla(count[index]);

			} else if (name[index].equals("ī���ī���ī")) {

				setConsumtionCoffeeBean(count[index]);

				setConsumtionCaramel(count[index]);

				setConsumtionMoca(count[index]);

			} else if (name[index].equals("ȭ��Ʈ����ī���ī")) {

				setConsumtionCoffeeBean(count[index]);

				setConsumtionMilk(count[index]);

				setConsumtionChoco(count[index]);

				setConsumtionMoca(count[index]);

			} else if (name[index].equals("ī��Ḷ���ƶ�")) {

				setConsumtionCoffeeBean(count[index]);

				setConsumtionCaramel(count[index]);

			} else if (name[index].equals("������")) {

				setConsumtionGreenTea(count[index]);

				setConsumtionMilk(count[index]);

			} else if (name[index].equals("������")) {

				setConsumtionSweetPotato(count[index]);

				setConsumtionMilk(count[index]);

			} else if (name[index].equals("������")) {

				setConsumtionChoco(count[index]);

				setConsumtionMilk(count[index]);

			} else if (name[index].equals("��Ʈ����")) {
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
			if (orderHistory.getStockItemsByIndex(index).getName().equals("��ȸ�� ��")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionCoffeeBean(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("Ŀ�ǿ���")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionMilk(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("����")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionCaramel(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("ī���")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionVanilla(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("�ٴҶ�")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionMoca(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("��ī")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionChoco(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("����")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionGreenTea(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("����")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionSweetPotato(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
			if (orderHistory.getStockItemsByIndex(index).getName().equals("����")
					&&!orderHistory.getStockItemsByIndex(index).use(count)) {
				result = false;
				hashSet.add(orderHistory.getStockItemsByIndex(index).getStockInfo());
			}
		}
	}

	private void setConsumtionMint(int count) {
		for (int index = 0; index < stockItemsSize; index++) {
		if (orderHistory.getStockItemsByIndex(index).getName().equals("��Ʈ")
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
