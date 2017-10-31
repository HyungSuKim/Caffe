import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

class RelatedPersonFileManagement {
	// 수정 17.10.22
	// 수정 17.10.25
	// 인스턴스를 너무 많이 생성한다 현재 11개... 게다가 생성할때마다 파일입출력을 실행하고 있다
	// 그래서 파라미터로 값을 넘겨주더라도 2개로 줄인다... customermenuframe, employeemenuframe에서만 생성
	private static File employeeFile = new File("Employee.txt");
	private static File clientFile = new File("Client.txt");

	private HashMap<String, Person> clientHashMap = new HashMap<>();
	private HashMap<String, Employee> employeeHashMap = new HashMap<>();

	RelatedPersonFileManagement() {
		createNewFileIfAbsent();

		for (String singleEmployeeInfo : getPersonArrayFromFile(employeeFile)) {
			Employee employee = new Employee(singleEmployeeInfo.split("/"));
			employeeHashMap.put(employee.getMpNum(), employee);
		}

		for (String singleClientInfo : getPersonArrayFromFile(clientFile)) {
			Person client = new Person(singleClientInfo.split("/"));
			clientHashMap.put(client.getMpNum(), client);
		}
	}
	


	private void createNewFileIfAbsent() {
		try {
			if (employeeFile.exists() == false) {
				employeeFile.createNewFile();
			}

			if (clientFile.exists() == false) {
				clientFile.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	private String[] getPersonArrayFromFile(File file) {
		String wholePerson = "";
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			fis.read(buffer);
			fis.close();
			wholePerson = new String(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return wholePerson.split(System.lineSeparator());
	}

	boolean getEmployeeFileEmpty() {
		if(employeeHashMap.size()==0) {
			return true;
		}
		return false;
	}
	
	private String getWholeEmployeeFormedData() {
		String wholeEmployeeFormedData = "";
		ArrayList<Employee> employeeArrayList = getEmployeeArrayList();
		int dataSize = employeeArrayList.size();

		for (int index = 0; index < dataSize; index++) {
			if (index == dataSize - 1) {
				wholeEmployeeFormedData += employeeArrayList.get(index).getFormedData();
			} else {
				wholeEmployeeFormedData += employeeArrayList.get(index).getFormedData() + System.lineSeparator();
			}
		}

		return wholeEmployeeFormedData;
	}

	ArrayList<Employee> getEmployeeArrayList() {
		ArrayList<Employee> employee = new ArrayList<Employee>();
		Iterator<String> it = employeeHashMap.keySet().iterator();
		while (it.hasNext()) {
			employee.add(employeeHashMap.get(it.next()));
		}
		return employee;
	}
	
	void storeEmployeeInFile(Employee employee) {
		try {
			FileOutputStream fos = new FileOutputStream(employeeFile, true);

			if (getEmployeeFileEmpty()) {
				fos.write((employee.getFormedData()).getBytes());
			} else {
				fos.write((System.lineSeparator() + employee.getFormedData()).getBytes());
			}
			employeeHashMap.put(employee.getMpNum(), employee);
			fos.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private void writeWholeEmployeeFormedData() {
		try {
			FileOutputStream fos = new FileOutputStream(employeeFile);
			fos.write(getWholeEmployeeFormedData().getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	boolean deleteEmployeeInFile(String inputMpNum) {
		boolean result = employeeHashMap.remove(inputMpNum, employeeHashMap.get(inputMpNum));

		writeWholeEmployeeFormedData();

		return result;
	}

	void modifyEmployeeInFile(String inputMpNum, String inputType, String inputPay) {

		Employee employee = employeeHashMap.get(inputMpNum);
		employee.setType(inputType);
		employee.setPay(inputPay);

		writeWholeEmployeeFormedData();
	}

	//////////////////////////////////////////////////////////////////////////
	boolean getClientFileEmpty() {
		if(clientHashMap.size()==0) {
			return true;
		}
		return false;
	}
	
	ArrayList<Person> getClientArrayList() {
		ArrayList<Person> client = new ArrayList<Person>();
		Iterator<String> it = clientHashMap.keySet().iterator();
		
		while (it.hasNext()) {
			client.add(clientHashMap.get(it.next()));
		}
		
		return client;
	}

	void storeCientInFile(Person client) {
		try {
			FileOutputStream fos = new FileOutputStream(clientFile, true);

			if (getClientFileEmpty()) {
				fos.write((client.getFormedData()).getBytes());
			} else {
				fos.write((System.lineSeparator() + client.getFormedData()).getBytes());
			}
			clientHashMap.put(client.getMpNum(), client);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getWholeClientFormedData() {
		String wholeClientFormedData = "";
		ArrayList<Person> clientArrayList = getClientArrayList();
		int dataSize = clientArrayList.size();

		for (int index = 0; index < dataSize; index++) {
			if (index == dataSize - 1) {
				wholeClientFormedData += clientArrayList.get(index).getFormedData();
			} else {
				wholeClientFormedData += clientArrayList.get(index).getFormedData() + System.lineSeparator();
			}
		}

		return wholeClientFormedData;
	}

	boolean deleteClientInFile(String inputMpNum) {
		boolean result = clientHashMap.remove(inputMpNum, clientHashMap.get(inputMpNum));

		writeWholeClientFormedData();

		return result;
	}

	private void writeWholeClientFormedData() {
		try {
			FileOutputStream fos = new FileOutputStream(clientFile);
			fos.write(getWholeClientFormedData().getBytes());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	HashMap<String, Person> getClientHashMap() {
		return clientHashMap;
	}
	
	HashMap<String, Employee> getEmployeeHashMap() {
		return employeeHashMap;
	}
}
