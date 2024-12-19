package dailyPlanner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Timer;
import javax.swing.*; 
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;


public class TasksSection implements ActionListener {
	
	public JFrame tasksFrame; //all components of the static tasksFrame JFrame
	private JPanel  tTitle, tasksScreen;
	private JLabel tTitle2;
	private DefaultTableModel tasksModel;
	private JTable tasksTable;
	private JPopupMenu rc;
	private JMenuItem editTask, deleteTask;
	private JButton adtButton, tnButton;
	private JScrollPane tasksScrollPane;
	
	private JFrame addFrame; //all components of the addFrame JFrame
	private JPanel addTitle, addScreen;
	private JLabel ex, addTitle1, tk, st;
	private JTextField entrtsk;
	private JFormattedTextField entrtm;
	private JButton addButton, cnclButton; //addFrame buttons (except for cnclButton, it is also used in the edtFrame)
	
	private JFrame edtFrame; //all components of the edtFrame JFrame
	private JPanel edtScreen, edtTitle;
	private JButton updtButton; 
	private JFormattedTextField edttm;

	//stuff
	private SimpleDateFormat ft = new SimpleDateFormat("hh:mm aa");
	
	//making notification
	private String datay;
	private Timer timer;
	
	//different fonts used throughout the program
	public Font titleFont = new Font("Lucida Handwriting", Font.PLAIN, 70);              
	public Font clickHereFont = new Font("Lucida Handwriting", Font.PLAIN, 18);
	public Font stitleFont = new Font("Lucida Handwriting", Font.PLAIN, 50);
	public Font smplFont = new Font("Times New Roman",Font.PLAIN, 17);
	
	
	
	public TasksSection() {
		
	}
	{ 
	
	tasksScreen = new JPanel();
	tasksScreen.setBackground(new Color(4, 102, 69));
	tasksScreen.setBounds(0,0,1000,700);
	tasksScreen.setLayout(null);

	tnButton = new JButton("<");
	tnButton.setFont(clickHereFont);
	tnButton.setForeground(Color.white);
	tnButton.setBounds(5,5,50,50);
	tnButton.setFocusPainted(false);
	tnButton.setBackground(new Color(63, 194, 131));
	tnButton.addActionListener(this);
	
	rc = new JPopupMenu();
	deleteTask = new JMenuItem("delete");
	deleteTask.addActionListener(this);
	editTask = new JMenuItem("edit");
	editTask.addActionListener(this);
	
	tasksModel = new DefaultTableModel();
	tasksTable = new JTable(tasksModel);
	tasksTable.setEnabled(false);
	tasksModel.addColumn("TASK TO DO");
	tasksModel.addColumn("REMIND AT");
	tasksTable.getColumnModel().getColumn(0).setPreferredWidth(800);
	tasksTable.getColumnModel().getColumn(1).setPreferredWidth(140);
	tasksScrollPane = new JScrollPane(tasksTable);
	tasksTable.setFillsViewportHeight(true);
	tasksTable.addMouseListener(new MouseAdapter()      
    {

        public void mouseReleased(MouseEvent e)
        {
            if (e.isPopupTrigger())
            {
                JTable source = (JTable)e.getSource();
                int row = source.rowAtPoint(e.getPoint());
                int column = source.columnAtPoint(e.getPoint());

                if (!source.isRowSelected(row))
                    source.changeSelection(row, column, false, false);

                rc.show(e.getComponent(),e.getX(),e.getY());
            }
        }
    });;
    
	tasksScrollPane.setBounds(50, 180, 880, 500);

	GridBagLayout tlayout = new GridBagLayout();
	tTitle = new JPanel(); //JPanel for the title of the frame "Tasks"
	tTitle.setBackground(new Color(4, 102, 69));
	tTitle.setBounds(20,50,650,90);
	tTitle.setLayout(tlayout);
	
	tTitle2 = new JLabel("TASKS");
	tTitle2.setForeground(Color.white);
	tTitle2.setFont(stitleFont);
	tTitle.add(tTitle2);
	
	adtButton = new JButton("+");
	adtButton.setBackground(new Color(4, 102, 69));
	adtButton.setForeground(Color.white);
    adtButton.setFocusPainted(false);
    adtButton.setBorderPainted(false);
	adtButton.setFont(stitleFont);
	adtButton.addActionListener(this);
	
	GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.weightx = 1;
    gbc.anchor = GridBagConstraints.PAGE_START;
    tTitle.add(tTitle2, gbc);
    gbc.weightx = 0;
    gbc.gridx++;
    tTitle.add(adtButton, gbc);
    
	tasksFrame = new JFrame();
	tasksFrame.setSize(1000,700);
	tasksFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	tasksFrame.getContentPane().setBackground(new Color(4, 102, 69));
	tasksFrame.setResizable(false);
	tasksFrame.setLocationRelativeTo(null);
	tasksFrame.setLayout(null);
	
    rc.add(deleteTask);
    rc.add(editTask);
	tasksScreen.add(tnButton);
	tasksScreen.add(tTitle);
	tasksScreen.add(tasksScrollPane);
	tasksFrame.add(tasksScreen);
}
	
	
	private void addTask() { //add task user interface
		
		addFrame = new JFrame(); 
		addFrame.setSize(1000,700);
		addFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addFrame.getContentPane().setBackground(new Color(4, 102, 69));
		addFrame.setResizable(false);
		addFrame.setLocationRelativeTo(null);
		addFrame.setLayout(null);
		
		addScreen = new JPanel();
		addScreen.setBackground(new Color(195, 200, 199));
		addScreen.setBounds(50,150,900,450);
		addScreen.setLayout(null);
		
		addTitle = new JPanel();
		addTitle.setBackground(new Color(4, 102, 69));
		addTitle.setBounds(250,50,400,100);
		
		addTitle1 = new JLabel("ADD TASK");
		addTitle1.setBackground(new Color(4, 102, 69));
		addTitle1.setForeground(Color.white);
		addTitle1.setFont(stitleFont); //reusing fonts
		addTitle.add(addTitle1);
		
		tk = new JLabel("task");
		tk.setFont(clickHereFont);
		tk.setBackground(new Color(195, 200, 199));
		tk.setBounds(140,50,100,40);

		entrtsk = new JTextField(10); //enter task 
		entrtsk.setFont(smplFont);
		entrtsk.setBounds(200,50,500,40);
		
		st = new JLabel("remind at"); 
		st.setFont(clickHereFont);
		st.setBackground(new Color(195, 200, 199));
		st.setBounds(80, 150, 200, 40);
		
		entrtm = new JFormattedTextField(ft); //enter reminder time
		entrtm.setFont(smplFont);
		entrtm.setBounds(200,150,300,40);
		
		ex = new JLabel("(ex: 10:00 AM)"); //example time input
		ex.setFont(clickHereFont);
		ex.setBackground(new Color(195, 200, 199));
		ex.setForeground(Color.gray);
		ex.setBounds(550, 150, 200, 40);
		
		addButton = new JButton("ADD");
		addButton.setBackground(new Color(0, 160, 100));
		addButton.setFont(clickHereFont);
		addButton.setForeground(Color.white);
		addButton.setBounds(650,360,200,50);
		addButton.setFocusPainted(false);
		addButton.addActionListener(this);
		
		cnclButton = new JButton("CANCEL");
		cnclButton.setBackground(Color.red);
		cnclButton.setFont(clickHereFont);
		cnclButton.setForeground(Color.white);
		cnclButton.setBounds(50, 360, 200, 50);
		cnclButton.setFocusPainted(false);
		cnclButton.addActionListener(this);
		
		addScreen.add(tk);
		addScreen.add(entrtsk);
		addScreen.add(st);
		addScreen.add(entrtm);
		addScreen.add(ex);
		addScreen.add(addButton);
		addScreen.add(cnclButton);
		addFrame.add(addTitle);
		addFrame.add(addScreen);
		addFrame.setVisible(true);
	}
	
	private void edt() { //task edit user interface
		JLabel edtTitle1;
		
		edtFrame = new JFrame(); 
		edtFrame.setSize(1000,700);
		edtFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		edtFrame.getContentPane().setBackground(new Color(4, 102, 69));
		edtFrame.setResizable(false);
		edtFrame.setLocationRelativeTo(null);
		edtFrame.setLayout(null);
		
		edtScreen = new JPanel();
		edtScreen.setBackground(new Color(195, 200, 199));
		edtScreen.setBounds(50,150,900,450);
		edtScreen.setLayout(null);
		
		edtTitle = new JPanel();
		edtTitle.setBackground(new Color(4, 102, 69));
		edtTitle.setBounds(250,50,400,100);
		
		edtTitle1 = new JLabel("EDIT TASK");
		edtTitle1.setBackground(new Color(4, 102, 69));
		edtTitle1.setForeground(Color.white);
		edtTitle1.setFont(stitleFont);
		edtTitle.add(edtTitle1);
		
		
		edttm = new JFormattedTextField(ft);
		edttm.setFont(smplFont);
		edttm.setBounds(200,150,300,40);
		
		updtButton = new JButton("UPDATE");
		updtButton.setBackground(new Color(0, 160, 100));
		updtButton.setFont(clickHereFont);
		updtButton.setForeground(Color.white);
		updtButton.setBounds(650,360,200,50);
		updtButton.setFocusPainted(false);
		updtButton.addActionListener(this);
		
		edtScreen.add(tk);
		edtScreen.add(entrtsk);
		edtScreen.add(st);
		edtScreen.add(entrtm);
		edtScreen.add(ex);
		edtScreen.add(updtButton);
		edtScreen.add(cnclButton);
		edtFrame.add(edtTitle);
		edtFrame.add(edtScreen);
		edtFrame.setVisible(true);
	}
	
	
	private void makeSchedule(Timer t)  {  //creates the reminder 
		//xx:xx xx
		try {
			timer = t;
			Calendar calendar = Calendar.getInstance();
			int apm1; 
			String data = entrtm.getText();
			String hr = data.substring(0, 2);
			int hr1 = Integer.parseInt(hr);
			String mnte = data.substring(3, 5);
			int mnte1 = Integer.parseInt(mnte);
			String apm = data.substring(6, 8);
			
			if (apm.equalsIgnoreCase("AM")) {
				apm1 = 0;
			}
			else  {
				apm1 = 1;
			}
			if (hr1 == 12) {
				hr1 = 0;
			}
			calendar.set(Calendar.AM_PM, apm1);
			calendar.set(Calendar.HOUR, hr1);
			calendar.set(Calendar.MINUTE, mnte1);
			calendar.set(Calendar.SECOND, 0);
			
			Date rmndTime = calendar.getTime();
			datay = entrtsk.getText();
			
			timer.schedule((new Notif(datay)), rmndTime);
			
			addFrame.dispose();
			
		} catch(NumberFormatException x) {
			JOptionPane.showMessageDialog(new JFrame(), "please follow the example", "error", JOptionPane.ERROR_MESSAGE);
			tasksModel.removeRow(tasksTable.getRowCount()-1);
			
		}catch(StringIndexOutOfBoundsException x) {
			JOptionPane.showMessageDialog(new JFrame(), "please follow the example", "error", JOptionPane.ERROR_MESSAGE);
			tasksModel.removeRow(tasksTable.getRowCount()-1);
		}
		
		
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == adtButton) {
			addTask();
		}
		else if(e.getSource() == tnButton) {
			tasksFrame.setVisible(false);
			
		}
		else if (e.getSource() == addButton) {
			String data1 = entrtsk.getText(); //task 
	    	String data2 = entrtm.getText(); //reminder time

	    	Object[] row = { data1, data2 }; //creates row: Object array
	    	tasksModel.addRow(row);
	    	
			Timer timr = new Timer();
			makeSchedule(timr); 
			//calls the makeSchedule(Timer t) method and passes timr as a parameter
	    	
		}
		else if (e.getSource() == cnclButton) {
			JFrame frm = (JFrame) SwingUtilities.getRoot(cnclButton);
			//gets the frame from which the button was clicked
			if(frm == addFrame) {
				addFrame.dispose();
			}
			else {
				edtFrame.dispose();
			}
		}
		else if (e.getSource() == editTask) {
			edt();
		}
		else if (e.getSource() == updtButton) {
			   timer.cancel(); //cancels old reminder timer
               int i = tasksTable.getSelectedRow();
               tasksModel.setValueAt(entrtsk.getText(), i, 0); //updates the table
               tasksModel.setValueAt(entrtm.getText(), i, 1);
               Timer timer  = new Timer(); //new timer
               makeSchedule(timer); //new schedule created by calling the makeSchedule() method
               edtFrame.dispose();    
		}
		else if (e.getSource() == deleteTask) {
			tasksModel.removeRow(tasksTable.getSelectedRow());
	    	timer.cancel();
		}
	}
}
