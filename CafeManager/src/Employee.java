/////////////������ ���, �ذ�, ��Ȳ�� Ȯ���ϱ����� ����Ŭ����////////////////
class Employee extends Person {
//	access modifier fixed
	private String type;
	private String pay;
	
	Employee(String name, String mpNum, String type, String pay) {
		super(name, mpNum);
		this.type = type;
		this.pay = pay; 
	}
	
	Employee(String[] hireEmployeeInfo) {
		this(hireEmployeeInfo[0], hireEmployeeInfo[1], hireEmployeeInfo[2], hireEmployeeInfo[3]);
	}
	
	void setType(String type) {
		this.type = type;
	}
	
	void setPay(String pay) {
		this.pay = pay;
	}
	
	String getType() {
		return type;
	}

	String getPay() {
		return pay;
	}

	String getFormedData() {
		return super.getFormedData() + "/" + getType() + "/" + getPay();
	}
	
	@Override
	boolean isValidate() {
		return super.isValidate() && validateType(type) && validatePay(pay);
	}
	
	private boolean validateType(String type) {
		return type.equals("�˹�") || type.equals("����");
	}
	
	private boolean validatePay(String pay) {
		try {
			return 10 <= Integer.parseInt(pay) && Integer.parseInt(pay) <= 999;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String toString() {
			return super.toString() + System.lineSeparator() +
					"�ٹ� ���� : " + this.type + System.lineSeparator() +
					"�޿�        : " + this.pay;
	}
}
