import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.border.EmptyBorder;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.component.FlatScrollPane;
import com.mommoo.flat.component.OnClickListener;
import com.mommoo.flat.frame.FlatFrame;
import com.mommoo.flat.layout.linear.LinearLayout;
import com.mommoo.flat.layout.linear.Orientation;
import com.mommoo.flat.layout.linear.constraints.LinearConstraints;
import com.mommoo.flat.layout.linear.constraints.LinearSpace;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.FlatTextArea;
import com.mommoo.flat.text.textarea.alignment.FlatHorizontalAlignment;
import com.mommoo.flat.text.textarea.alignment.FlatVerticalAlignment;
import com.mommoo.util.ScreenManager;

/**
 * summary : can write and fix operating record by yy/mm/dd
 * details : user can read daily record by double click date on calendar
 *         : and can add contents by write on right textarea
 *         : and can fix by click fix btn
 * arguments : calendar = cal
 * @author 김형수
 *
 */
// 17.10.26 수정
class OperatingRecordFrame {
	private Calendar cal = Calendar.getInstance();
	private int currentYear = cal.get(Calendar.YEAR);
	private int currentMonth = cal.get(Calendar.MONTH) + 1;

	private Calendar beforeMonthEndDay = Calendar.getInstance();
	private Calendar currentMonthStartDay = Calendar.getInstance();
	private Calendar currentMonthEndDay = Calendar.getInstance();
	private Calendar afterMonthStartDay = Calendar.getInstance();

	private FlatFrame frame = createFrame();
	
	private FlatPanel dayView = createDayPanel();
	private FlatPanel calendarPanel = createCalendarPanel();
	private FlatPanel[] weekPanel = createWeekPanel();
	
	private FlatButton backwardMonthBtn = createBackwardMonthBtn();
	private FlatButton forwardMonthBtn = createForwardMonthBtn();

	private ArrayList<FlatLabel> daysOnCalendar = createDaysOnCalendar();
	private FlatLabel monthLabel = createMonthLabel();
	private FlatLabel[][] dayLabel = createDayLabel();

	private FlatTextArea existingContentArea = createExistingContentArea();
	private FlatTextArea additionContentArea = createAdditionContentArea();
	
	private FlatButton fixExistingContentBtn = createFixExistingContentBtn();
	private FlatButton applyAdditionContentBtn = createApplyAdditionContentBtn();

	private static File dir;
	private static File file;

	private static GraphicTheme graphicTheme = new GraphicTheme();

	private int firstClick = 0;
	private int secondClick = 0;

	OperatingRecordFrame() {

		frame.getContainer().add(createMonthPanel(), createCommonConstraints(1));
		backwardMonthBtn.setOnClickListener(getListenerToBackwardMonth());
		forwardMonthBtn.setOnClickListener(getListenerToForwardMonth());
		
		frame.getContainer().add(dayView, createCommonConstraints(1));
		setDay();
		
		frame.getContainer().add(calendarPanel, createCommonConstraints(7));
		setCalendar();
		drawCalendar();
		
		frame.getContainer().add(createTextAreaInfoView(), createCommonConstraints(1));
		
		frame.getContainer().add(createTextAreaPanel(), createCommonConstraints(5));

		frame.getContainer().add(createButtonPanel(), createCommonConstraints(2));
		fixExistingContentBtn.setOnClickListener(getListenerToFixExistingContent());
		applyAdditionContentBtn.setOnClickListener(getListenerToApplyAdditionContent());

		frame.show();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	private LinearConstraints createCommonConstraints(int weight) {
		return new LinearConstraints().setWeight(weight).setLinearSpace(LinearSpace.MATCH_PARENT);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	private FlatFrame createFrame() {
		FlatFrame operatingRecordFrame = new FlatFrame();
		ScreenManager operatingRecordSM = ScreenManager.getInstance();
		operatingRecordFrame.setTitle("운영 일지");
		operatingRecordFrame.setLocationOnScreenCenter();
		operatingRecordFrame.setSize(operatingRecordSM.dip2px(300), operatingRecordSM.dip2px(400));
		operatingRecordFrame.getContainer().setLayout(new LinearLayout(Orientation.VERTICAL, 0));
		operatingRecordFrame.setResizable(true);
		operatingRecordFrame.setIconImage(graphicTheme.getCoffeeImage());
		operatingRecordFrame.setProcessIconImage(graphicTheme.getCoffeeImage());
		operatingRecordFrame.setTitleBarColor(graphicTheme.getTitleBarColor());
		return operatingRecordFrame;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createMonthPanel() {
		FlatPanel monthPanel = new FlatPanel(new LinearLayout(0));

		monthPanel.setBackground(graphicTheme.getBasicBrownColor());
		monthPanel.setOpaque(true);
		monthPanel.add(backwardMonthBtn, createCommonConstraints(1));
		monthPanel.add(monthLabel, createCommonConstraints(2));
		monthPanel.add(forwardMonthBtn, createCommonConstraints(1));
		
		return monthPanel;
	}
	
	private FlatButton createBackwardMonthBtn() {
		FlatButton backwardMonthBtn = new FlatButton("◀");
		
		backwardMonthBtn.setBackground(graphicTheme.getLightBrownColor());
		backwardMonthBtn.setForeground(graphicTheme.getDarkerBrownColor());
		backwardMonthBtn.setFont(graphicTheme.getPlainFont15pt());
		
		return backwardMonthBtn;
	}
	
	private OnClickListener getListenerToBackwardMonth() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				--currentMonth;
				if (currentMonth == 0) {
					currentMonth = 12;
					--currentYear;
				}
				drawCalendar();
				monthLabel.setText(currentYear + "년 " + currentMonth + "월");
			}
		};
	}
	
	private FlatLabel createMonthLabel() {
		FlatLabel monthLabel = new FlatLabel();
		
		monthLabel.setBackground(graphicTheme.getBasicBrownColor());
		monthLabel.setFont(graphicTheme.getBoldFont20pt());
		monthLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		monthLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		monthLabel.setText(currentYear + "년 " + currentMonth + "월");
		
		return monthLabel;
	}
	
	private FlatButton createForwardMonthBtn() {
		FlatButton forwardMonthBtn = new FlatButton("▶");
		
		forwardMonthBtn.setBackground(graphicTheme.getLightBrownColor());
		forwardMonthBtn.setForeground(graphicTheme.getDarkerBrownColor());
		forwardMonthBtn.setFont(graphicTheme.getPlainFont15pt());
		
		return forwardMonthBtn;
	}
	
	private OnClickListener getListenerToForwardMonth() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				++currentMonth;
				if (currentMonth == 13) {
					currentMonth = 1;
					++currentYear;
				}
				drawCalendar();
				monthLabel.setText(currentYear + "년 " + currentMonth + "월");
			}
		};
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createDayPanel() {
		return new FlatPanel(new LinearLayout(0));
	}
	
	private void setDay() {
		for (FlatLabel day : daysOnCalendar) {
			dayView.add(day, createCommonConstraints(1));
		}
	}
	
	private ArrayList<FlatLabel> createDaysOnCalendar() {
		ArrayList<FlatLabel> dayLabelList = new ArrayList<FlatLabel>(7);
		dayLabelList.add(new FlatLabel("일"));
		dayLabelList.add(new FlatLabel("월"));
		dayLabelList.add(new FlatLabel("화"));
		dayLabelList.add(new FlatLabel("수"));
		dayLabelList.add(new FlatLabel("목"));
		dayLabelList.add(new FlatLabel("금"));
		dayLabelList.add(new FlatLabel("토"));
		
		for (FlatLabel day : dayLabelList) {
			day.setBackground(graphicTheme.getDarkBrownColor());
			day.setFont(graphicTheme.getBoldFont20pt());
			day.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
			day.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		}
		return dayLabelList;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////

	private FlatPanel createCalendarPanel() {
		return new FlatPanel(new LinearLayout(Orientation.VERTICAL, 0));
	}
	
	private void setCalendar() {
		for (int i = 0; i < 6; i++) {
			calendarPanel.add(weekPanel[i], createCommonConstraints(1));
			for (int j = 0; j < 7; j++) {
				weekPanel[i].add(dayLabel[i][j], createCommonConstraints(1));
			}
		}
	}
	
	private FlatPanel[] createWeekPanel() {
		FlatPanel[] weekPanel = new FlatPanel[6];
		for (int i = 0; i < 6; i++) {
			weekPanel[i] = new FlatPanel(new LinearLayout(1));
			weekPanel[i].setBackground(graphicTheme.getDarkBrownColor());
			weekPanel[i].setOpaque(true);
			weekPanel[i].setBorder(new EmptyBorder(1, 1, 1, 1));
		}
		return weekPanel;
	}
	private FlatLabel[][] createDayLabel() {
		FlatLabel[][] dayLabel = new FlatLabel[6][7];
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				dayLabel[i][j] = new FlatLabel();
				dayLabel[i][j].setBackground(graphicTheme.getBasicBrownColor());
				dayLabel[i][j].setFont(graphicTheme.getPlainFont15pt());
			}
		}
		return dayLabel;
	}
	
	//추가
	private void drawCalendar() {
		beforeMonthEndDay.set(currentYear, currentMonth - 2, 1);
		beforeMonthEndDay.set(Calendar.DATE, beforeMonthEndDay.getActualMaximum(Calendar.DATE));
		currentMonthStartDay.set(currentYear, currentMonth - 1, 1);
		currentMonthEndDay.set(currentYear, currentMonth - 1, currentMonthStartDay.getActualMaximum(Calendar.DATE));
		afterMonthStartDay.set(currentYear, currentMonth, 1);

		for (int week = 0; week < 6; week++) {
			
			for (int day = 0; day < 7; day++) {
				setDayLabel(week, day);
			}
		}
	}
//	추가
	private void setDayLabel(int week, int day) {
		int counter = 0;
		
		if (week == 0 && currentMonthStartDay.get(Calendar.DAY_OF_WEEK) > day + 1) {
			dayLabel[week][day].setText(
			(beforeMonthEndDay.get(Calendar.MONTH) + 1) + "." + (beforeMonthEndDay.get(Calendar.DATE)
			- currentMonthStartDay.get(Calendar.DAY_OF_WEEK) + 2 + day));
			
			dayLabel[week][day].setBackground(graphicTheme.getBasicBrownColor());
			dayLabel[week][day].setOnClickListener(getListenerToReadContent(week, day));
			
		} else if (currentMonthStartDay.before(currentMonthEndDay)
				|| currentMonthStartDay.equals(currentMonthEndDay)) {
			
			dayLabel[week][day].setText(Integer.toString(currentMonthStartDay.get(Calendar.DATE)));
			dayLabel[week][day].setBackground(graphicTheme.getLightBrownColor());
			
			currentMonthStartDay.add(Calendar.DATE, 1);
			dayLabel[week][day].setOnClickListener(getListenerToReadContent(week, day));
			
		} else {
			dayLabel[week][day].setText((afterMonthStartDay.get(Calendar.MONTH) + 1) + "."
					+ (afterMonthStartDay.get(Calendar.DATE) + counter));
			dayLabel[week][day].setBackground(graphicTheme.getBasicBrownColor());
			
			counter++;
			dayLabel[week][day].setOnClickListener(getListenerToReadContent(week, day));
			
		}
	}
	//추가
	private OnClickListener getListenerToReadContent(int week, int day) {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				LocalTime startTime = LocalTime.now();
				if (firstClick == 0) {
					firstClick = startTime.getSecond();
					} else {
					secondClick = startTime.getSecond();
					if (firstClick == secondClick) {
						readExistingContentMethod(week, day);
						firstClick = 0;
						secondClick = 0;
					} else {
						firstClick = 0;
						secondClick = 0;
					}
				}
			}
		};
	}
	//추가
	private void readExistingContentMethod(int tempWeek, int tempDay) {
		String text = dayLabel[tempWeek][tempDay].getText();
		if(text.contains("\\.")) {
			readExistingContent(currentYear + "." + text);
		} else {
			readExistingContent(currentYear + "." + currentMonth + "."	+ text);
		}
	}

	private void readExistingContent(String input) {
		String[] dirNameTemp = input.split("\\.");
		String dirName = dirNameTemp[0] + "." + dirNameTemp[1];
		dir = new File("C:\\Users\\김형수\\eclipse-workspace\\Caffee\\OperatingRecord\\" + dirName);
		dir.mkdirs();
		file = new File(
				"C:\\Users\\김형수\\eclipse-workspace\\Caffee\\OperatingRecord\\" + dirName + "\\" + input + ".txt");
		try {
			if (file.exists() == false) {
				file.createNewFile();
			}
			FileInputStream fis = new FileInputStream(file);
			byte[] buffer = new byte[(int) file.length()];
			fis.read(buffer);
			existingContentArea.setText(new String(buffer));
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		additionContentArea.setEditable(true);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createTextAreaInfoView() {
		FlatPanel infoTextAreaPanel = new FlatPanel(new LinearLayout(0));

		infoTextAreaPanel.add(createExistingContentLabel(), createCommonConstraints(1));
		infoTextAreaPanel.add(createAdditionContentLabel(), createCommonConstraints(1));
		
		return infoTextAreaPanel;
	}
	
	private FlatLabel createExistingContentLabel() {
		FlatLabel existingContentLabel = new FlatLabel("기존 입력 내용");
		
		existingContentLabel.setBackground(graphicTheme.getBasicBrownColor());
		existingContentLabel.setFont(graphicTheme.getBoldFont15pt());
		existingContentLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		existingContentLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		
		return existingContentLabel;
	}
	
	private FlatLabel createAdditionContentLabel() {
		FlatLabel additionContentLabel = new FlatLabel("추가 입력 내용");
		
		additionContentLabel.setBackground(graphicTheme.getLightBrownColor());
		additionContentLabel.setFont(graphicTheme.getBoldFont15pt());
		additionContentLabel.setVerticalAlignment(FlatVerticalAlignment.CENTER);
		additionContentLabel.setHorizontalAlignment(FlatHorizontalAlignment.CENTER);
		
		return additionContentLabel;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createTextAreaPanel() {
		FlatPanel textAreaPanel = new FlatPanel(new LinearLayout(0));
		FlatScrollPane scrollExistingContentArea = createScrollExistingContentArea();
		
		textAreaPanel.add(scrollExistingContentArea, createCommonConstraints(13));
		textAreaPanel.add(additionContentArea, createCommonConstraints(12));
		
		return textAreaPanel;
	}
	
	private FlatScrollPane createScrollExistingContentArea() {
		FlatScrollPane scrollExistingContentArea = new FlatScrollPane(existingContentArea);
		
		scrollExistingContentArea.setVerticalScrollTrackColor(graphicTheme.getBasicBrownColor());
		scrollExistingContentArea.setThemeColor(graphicTheme.getDarkBrownColor());
		scrollExistingContentArea.setOpaque(true);
		
		return scrollExistingContentArea;
	}
	
	private FlatTextArea createExistingContentArea() {
		FlatTextArea existingContentArea = new FlatTextArea();
		existingContentArea.setBackground(graphicTheme.getLightBrownColor());
		existingContentArea.setFont(graphicTheme.getPlainFont15pt());
		existingContentArea.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
		existingContentArea.setEditable(false);
		return existingContentArea;
	}
	
	private FlatTextArea createAdditionContentArea() {
		FlatTextArea additionContentArea = new FlatTextArea();
		
		additionContentArea.setBackground(graphicTheme.getBasicBrownColor());
		additionContentArea.setFont(graphicTheme.getPlainFont15pt());
		additionContentArea.setBorder(BorderFactory.createEmptyBorder(0, 15, 0, 0));
		additionContentArea.setEditable(false);
		
		return additionContentArea;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	private FlatPanel createButtonPanel() {
		FlatPanel buttonPanel = new FlatPanel(new LinearLayout(0));
		FlatButton exitBtn = createExitBtn();
		
		buttonPanel.add(fixExistingContentBtn, createCommonConstraints(1));
		buttonPanel.add(applyAdditionContentBtn, createCommonConstraints(1));
		buttonPanel.add(exitBtn, createCommonConstraints(1));
		exitBtn.setOnClickListener(getListenerToExitFrame());
		
		return buttonPanel;
	}
	
	private FlatButton createFixExistingContentBtn() {
		FlatButton fixExistingContentBtn = new FlatButton("기존입력수정");
		
		fixExistingContentBtn.setBackground(graphicTheme.getBasicBrownColor());
		
		return fixExistingContentBtn;
	}
	
	private OnClickListener getListenerToFixExistingContent() {
		return new OnClickListener() {
			boolean editable = false;
			
			@Override
			public void onClick(Component component) {
				if (fixExistingContent() == true) {
					existingContentArea.setEditable(true);
					
					if (editable == false) {
						fixExistingContentBtn.setText("수정완료");
						editable = true;
						
					} else if (fixExistingContentBtn.getText().equals("수정완료")) {
						fixExistingContent();
						existingContentArea.setEditable(false);
						fixExistingContentBtn.setText("기존입력수정");
						editable = false;
						
					}
				}
			}
		};
	}

	private boolean fixExistingContent() {
		String temp = existingContentArea.getText();
		
		if (temp.equals("")) {
			return false;
			
		} else {
			try {
				FileOutputStream fos = new FileOutputStream(file);
				fos.write(temp.getBytes());
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return true;
		}
	}
	
	private FlatButton createApplyAdditionContentBtn() {
		FlatButton applyAdditionContentBtn = new FlatButton("추가입력적용");
		
		applyAdditionContentBtn.setBackground(graphicTheme.getSkyBlueColor());
		
		return applyAdditionContentBtn;
	}
	
	private OnClickListener getListenerToApplyAdditionContent() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				if (additionContent() == true) {
					additionContentArea.setText("");
				}
			}
		};
	}

	private boolean additionContent() {
		String temp = additionContentArea.getText() + System.lineSeparator();
		
		if (temp.equals(System.lineSeparator())) {
			return false;
			
		} else {
			try {
				FileOutputStream fos = new FileOutputStream(file, true);
				fos.write(temp.getBytes());
				fos.close();
				FileInputStream fis = new FileInputStream(file);
				byte[] buffer = new byte[(int) file.length()];
				fis.read(buffer);
				existingContentArea.setText(new String(buffer));
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return true;
		}
	}
	
	private FlatButton createExitBtn() {
		FlatButton exitBtn = new FlatButton("취소");
		
		exitBtn.setBackground(graphicTheme.getPinkColor());
		
		return exitBtn;
	}
	
	private OnClickListener getListenerToExitFrame() {
		return new OnClickListener() {
			@Override
			public void onClick(Component component) {
				frame.hide();
			}
		};
	}
}
