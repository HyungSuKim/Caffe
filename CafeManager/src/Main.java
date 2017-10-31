import javax.swing.SwingUtilities;
/**
 * summary : Which excute program(Caffee Manager)
 *         : 카페 프로그램을 실행시켜주는 클래스
 * details : To excute program it contains main method
 *         : 프로그램을 실행시키기 위해 메인 메서드를 가지고 있다.
 *         : And main method creates MainFrame Class instance to show program's Main
 *         : 그리고 메인 메서드는 프로그램의  메인화면를 나타내기위해 메인프레임 클래스의 인스턴스를 만들어준다.
 *         : new MainFrame(); 을 통해 메인화면을 띄워준다 
 * argument : employee = emp
 *          : customer = ctm 
 * @author 김형수
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