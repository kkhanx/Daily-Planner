package dailyPlanner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;  //ActionListener class: responsible for handling action events
import java.time.LocalDate;

public class Gui implements ActionListener  {

	
	private JFrame frame; //all components of the static frame JFrame
	private JPanel title, open, date; //JPanels are "lightweight" containers
	private JPanel openScreen;
	private JLabel titleName, date1; //JLabels are display areas for short Strings and/or images
	private JButton openButton; 
	
	private JPanel choicePanel; //all components on the choicePanel JPanel
	private JButton tasksButton, notesButton, homeButton;

	TasksSection taskObj = new TasksSection();
	NotesSection notesObj = new NotesSection();

	
	
	public Gui() {
		
	}
	
	public void firstScreen() {
		//opening/home screen
			openScreen = new JPanel();
			openScreen.setBounds(0,0,1000,700);
			openScreen.setBackground(new Color(4,102,69));
			openScreen.setLayout(null);
		
			date = new JPanel();
			date.setBounds(350,150,250,50); //.setBounds(int x, int y, int width, int height)
			date.setBackground(new Color(14, 21, 7)); //.setBackground changes the colour of the background
			date1 = new JLabel("Today is   "+ (LocalDate.now()).toString() + "    ");
			date1.setForeground(new Color(135, 233, 169)); //.setForeground() changes the colour of the font 
			date1.setFont(taskObj.clickHereFont);
			
			title = new JPanel();
			title.setBounds(170,250,650,120);  
			title.setBackground(new Color(135, 233, 169)); //colours are chosen using new Color() and the RGB numbers of the desired colour
			
			titleName = new JLabel("Daily Planner");
			titleName.setForeground(new Color(14, 21, 7));
			titleName.setFont(taskObj.titleFont); 
			
			open = new JPanel();
			open.setBounds(400,420,150,75);
			open.setBackground(new Color(4, 102, 69));
			
			openButton = new JButton("CLICK HERE");
			openButton.setBounds(300,420,200,100);
			openButton.setBackground(new Color(14, 21, 7));
			openButton.setForeground(new Color(135, 233, 169));
			openButton.setFocusPainted(false);
			openButton.setFont(taskObj.clickHereFont);
			openButton.addActionListener(this); 
			
			frame = new JFrame();
			frame.setSize(1000,700);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //when the 'x' is pressed, program will be exited
			frame.getContentPane().setBackground(new Color(4, 102, 69)); //getContentPane: retrieves the content pane layer (which you can add objects on)
			frame.setResizable(false); //it wont be possible to change the size of the frame 
			frame.setLayout(null); //using no layout managers
			frame.setLocationRelativeTo(null);
			
			date.add(date1); //add the JLabels/JButtons to the corresponding JPanels, then add the JPanels onto the frame
			title.add(titleName);
			open.add(openButton);
			openScreen.add(date);
			openScreen.add(title);
			openScreen.add(open);
			frame.add(openScreen);
			frame.setVisible(true);
			
		}


		private void choice() { //choice screen gui (choose between tasks, notes, or home)
			
			choicePanel = new JPanel();
			choicePanel.setBounds(80,50,800,555);
			choicePanel.setBackground(new Color(135, 233, 169));
			choicePanel.setLayout(new GridLayout(1,2)); //the GridLayout(int rows, int columns) is a layout manager that arranges the components in a container in a rectangular grid
			
			ImageIcon icon = new ImageIcon("home3.jpg");
			homeButton = new JButton();
			homeButton.setIcon(icon);
			homeButton.setBackground(new Color(4, 102, 69));
			homeButton.setBounds(10, 10, 40, 40);
			homeButton.setFocusPainted(false);
			homeButton.addActionListener(this);
			
			tasksButton = new JButton("TASKS");
			tasksButton.setBackground(new Color (6, 122, 82));
			tasksButton.setFont(taskObj.titleFont); //reused the "titleFont" font 
			tasksButton.setForeground(Color.white);
			tasksButton.setFocusPainted(false);
			tasksButton.addActionListener(this);
			
			notesButton = new JButton("NOTES");
			notesButton.setBackground(new Color(63, 194, 131));
			notesButton.setFont(taskObj.titleFont);  
			notesButton.setForeground(Color.white);
			notesButton.setFocusPainted(false);
			notesButton.addActionListener(this);
			
			choicePanel.add(tasksButton);     //add the buttons to the choice panel, then add the choice panel to the frame
			choicePanel.add(notesButton);
			frame.add(homeButton);
			frame.add(choicePanel);

		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == openButton) {//.getSource() : returns the object on which the event occurred
				openScreen.setVisible(false);
				choice(); 
			}
			else if (e.getSource() == homeButton ) {
				choicePanel.setVisible(false);
				homeButton.setVisible(false);
				openScreen.setVisible(true);
			}

			else if (e.getSource() == tasksButton) {
				taskObj.tasksFrame.setVisible(true);
			}
			else  {
				notesObj.notesFrame.setVisible(true);
			}
			
		}
}
