

public class Stock {
//	access modifier fixed
	private String name;
	private int amount;
	private int consumtion;
	private String stockInfo;
	
	Stock(String name, int amount) {
		this.name = name;
		this.amount = amount;
		consumtion = 0;
		stockInfo = name + "포함 제품 주문가능 수량 / " + amount + "개";
	}
	
	boolean use(int count) {
		if(amount < count) {
			return false;
		} else {
			amount -= count;
			consumtion += count;
			stockInfo = name + "포함 제품 주문가능 수량 / " + amount + "개";
			return true;
		}
	}
	
	void increase(int count) {
		if(count > 0) {
			amount += count;
		}
	}

	String getName() {
		return name;
	}
	
	int getAmount() {
		return amount;
	}
	
	int getConsumtion() {
		return consumtion;
	}
	
	String getStockInfo() {
		return stockInfo;
	}
}
