class Person {
	private String name;
	private String mpNum;
	
	Person(String name, String phoneNum) {
		this.name = name;
		this.mpNum = phoneNum;
	}
	
	Person(String[] personInfo) {
		this(personInfo[0], personInfo[1]);
	}

	boolean isValidate() {
		return validatePhoneNum(mpNum);
	}
	
	private boolean validatePhoneNum(String phoneNum) {
		char[] data = phoneNum.toCharArray();
		if(!(data[0]=='0' && data[1]=='1' && data[2]=='0' && data[3]=='-' && data[8]=='-')) {
			return false;
		}
		
		for(int index = 4; index < 13; index++) {
			if(index==8) {
				continue;
			}
			
			if(!isNumber(data[index])) {
				return false;
			}
		}
		
		return true;
	}
	
	private boolean isNumber(char c) {
		return 47 < c && c < 58; 
	}
	
	public String toString() {
		return  "이름           : " + name + System.lineSeparator() +
				"핸드폰 번호 : " + mpNum;
	}
	
	String getName() {
		return name;
	}
	
	String getMpNum() {
		return mpNum;
	}
	
	String getFormedData() {
		return getName() + "/" + getMpNum();
	}
}
