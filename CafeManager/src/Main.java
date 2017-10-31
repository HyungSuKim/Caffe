import javax.swing.SwingUtilities;
/**
 * summary : Which excute program(Caffee Manager)
 *         : ī�� ���α׷��� ��������ִ� Ŭ����
 * details : To excute program it contains main method
 *         : ���α׷��� �����Ű�� ���� ���� �޼��带 ������ �ִ�.
 *         : And main method creates MainFrame Class instance to show program's Main
 *         : �׸��� ���� �޼���� ���α׷���  ����ȭ�鸦 ��Ÿ�������� ���������� Ŭ������ �ν��Ͻ��� ������ش�.
 *         : new MainFrame(); �� ���� ����ȭ���� ����ش� 
 * argument : employee = emp
 *          : customer = ctm 
 * @author ������
 *
 */
class Main {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		});
	}
}