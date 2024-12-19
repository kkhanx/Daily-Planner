package dailyPlanner;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class NotesSection implements ActionListener {
	public JFrame notesFrame; //all components of the static notesFrame JFrame
	private JPanel nTitle;
	private JLabel  ntesTitle;
	private JTable notesTable;
	private DefaultTableModel notesModel;
	private JScrollPane notesScrollPane;
	private JButton adnButton, tnButton;
	private JPopupMenu nte;
	private JMenuItem editNote, deleteNote;
	
	private JFrame addNoteFrame; //all components of the addNoteFrame JFrame
	private JButton addNoteButton, cnclButton;
	private JPanel addNoteTitle, addNoteScreen;
	private JLabel addNoteTitle1, nt;
	private JTextField entrnte;

	private JFrame edtNoteFrame;
	private JLabel edtTitle;
	private JPanel edtNoteScreen, edtNoteTitle; 
	private JButton updtNoteButton;
	
	TasksSection obj1 = new TasksSection();
	
	public NotesSection() {
		
	}
	
{
		
		adnButton = new JButton("+");
		adnButton.setBackground(new Color(4, 102, 69));
		adnButton.setForeground(Color.white);
        adnButton.setFocusPainted(false);
        adnButton.setBorderPainted(false);
		adnButton.setFont(obj1.stitleFont);
		adnButton.addActionListener(this);
		
		GridBagLayout nlayout = new GridBagLayout();
		nTitle = new JPanel(); //JPanel for the title of the frame "ntes"
		nTitle.setBackground(new Color(4, 102, 69));
		nTitle.setBounds(20,50,650,90);
		nTitle.setLayout(nlayout);
		
		ntesTitle = new JLabel("NOTES");
		ntesTitle.setFont(obj1.stitleFont);
		ntesTitle.setForeground(Color.white);
		
		tnButton = new JButton("<");
		tnButton.setFont(obj1.clickHereFont);
		tnButton.setForeground(Color.white);
		tnButton.setBounds(5,5,50,50);
		tnButton.setFocusPainted(false);
		tnButton.setBackground(new Color(63, 194, 131));
		tnButton.addActionListener(this);
		
		GridBagConstraints gbc = new GridBagConstraints();  //same gbc constraints as the task title (in the tasksSection class)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.anchor = GridBagConstraints.PAGE_START;
        nTitle.add(ntesTitle, gbc);
        gbc.weightx = 0;
        gbc.gridx++;
        nTitle.add(adnButton, gbc);
		
        nte = new JPopupMenu();
        editNote = new JMenuItem("edit");
        editNote.addActionListener(this);
        deleteNote = new JMenuItem("delete");
        deleteNote.addActionListener(this);
		
		notesModel = new DefaultTableModel();
		notesTable = new JTable(notesModel);
		notesTable.setEnabled(false);
		notesModel.addColumn("NOTES");
		notesScrollPane = new JScrollPane(notesTable);
		notesTable.setFillsViewportHeight(true);
		notesTable.addMouseListener(new MouseAdapter()      
        { //mouselistener method 

            public void mouseReleased(MouseEvent e)
            {
                if (e.isPopupTrigger())
                {
                    JTable source = (JTable)e.getSource();
                    int row = source.rowAtPoint(e.getPoint());
                    int column = source.columnAtPoint(e.getPoint());

                    if (!source.isRowSelected(row))
                        source.changeSelection(row, column, false, false);

                    nte.show(e.getComponent(),e.getX(),e.getY());
                }
                else {
                	
                }
            }
        });;
        
		notesFrame = new JFrame();
		notesFrame.setSize(1000,700);
		notesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		notesFrame.getContentPane().setBackground(new Color(4, 102, 69));
		notesFrame.setResizable(false);
		notesFrame.setLocationRelativeTo(null);
		notesFrame.setLayout(null);
		
		notesScrollPane.setBounds(50, 180, 880, 500);
		notesFrame.add(notesScrollPane);
		notesFrame.add(tnButton);
		nte.add(deleteNote);
		nte.add(editNote);
		notesFrame.add(nTitle);
	}
	
	private void addNote() {
		
		addNoteScreen = new JPanel();
		addNoteScreen.setBackground(new Color(195, 200, 199));
		addNoteScreen.setBounds(50,150,900,450);
		addNoteScreen.setLayout(null);
		
		addNoteTitle = new JPanel();
		addNoteTitle.setBackground(new Color(4, 102, 69));
		addNoteTitle.setBounds(250,50,400,100);
		
		addNoteTitle1 = new JLabel("ADD NOTE");
		addNoteTitle1.setBackground(new Color(4, 102, 69));
		addNoteTitle1.setForeground(Color.white);
		addNoteTitle1.setFont(obj1.stitleFont);
		addNoteTitle.add(addNoteTitle1);
		
		nt = new JLabel("note");
		nt.setFont(obj1.clickHereFont);
		nt.setBackground(new Color(195, 200, 199));
		nt.setBounds(140,150,100,40);

		entrnte = new JTextField(10);
		entrnte.setFont(obj1.smplFont);
		entrnte.setBounds(200,150,500,40);
		
		addNoteButton = new JButton("ADD");
		addNoteButton.setBackground(new Color(0, 160, 100));
		addNoteButton.setFont(obj1.clickHereFont);
		addNoteButton.setForeground(Color.white);
		addNoteButton.setBounds(650,360,200,50);
		addNoteButton.setFocusPainted(false);
		addNoteButton.addActionListener(new ButtonListener());
		
		cnclButton = new JButton("CANCEL");
		cnclButton.setBackground(Color.red);
		cnclButton.setFont(obj1.clickHereFont);
		cnclButton.setForeground(Color.white);
		cnclButton.setBounds(50, 360, 200, 50);
		cnclButton.setFocusPainted(false);
		cnclButton.addActionListener(this);
		
		addNoteFrame = new JFrame(); 
		addNoteFrame.setSize(1000,700);
		addNoteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addNoteFrame.getContentPane().setBackground(new Color(4, 102, 69));
		addNoteFrame.setResizable(false);
		addNoteFrame.setLocationRelativeTo(null);
		addNoteFrame.setLayout(null);
		
		addNoteScreen.add(nt);
		addNoteScreen.add(entrnte);
		addNoteScreen.add(addNoteButton);
		addNoteScreen.add(cnclButton);
		addNoteFrame.add(addNoteTitle);
		addNoteFrame.add(addNoteScreen);
		addNoteFrame.setVisible(true);
		
	}
	private void edtNote() {
		
		edtNoteScreen = new JPanel();
		edtNoteScreen.setBackground(new Color(195, 200, 199));
		edtNoteScreen.setBounds(50,150,900,450);
		edtNoteScreen.setLayout(null);
		
		edtNoteTitle = new JPanel();
		edtNoteTitle.setBackground(new Color(4, 102, 69));
		edtNoteTitle.setBounds(250,50,400,100);
		
		edtTitle = new JLabel("EDIT NOTE");
		edtTitle.setBackground(new Color(4, 102, 69));
		edtTitle.setForeground(Color.white);
		edtTitle.setFont(obj1.stitleFont);
		edtNoteTitle.add(edtTitle);

		updtNoteButton = new JButton("UPDATE");
		updtNoteButton.setBackground(new Color(0, 160, 100));
		updtNoteButton.setFont(obj1.clickHereFont);
		updtNoteButton.setForeground(Color.white);
		updtNoteButton.setBounds(650,360,200,50);
		updtNoteButton.setFocusPainted(false);
		updtNoteButton.addActionListener(this);
		
		edtNoteFrame = new JFrame(); 
		edtNoteFrame.setSize(1000,700);
		edtNoteFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		edtNoteFrame.getContentPane().setBackground(new Color(4, 102, 69));
		edtNoteFrame.setResizable(false);
		edtNoteFrame.setLocationRelativeTo(null);
		edtNoteFrame.setLayout(null);
		
		edtNoteScreen.add(nt);
		edtNoteScreen.add(entrnte);
		edtNoteScreen.add(updtNoteButton);
		edtNoteScreen.add(cnclButton);
		edtNoteFrame.add(edtNoteTitle);
		edtNoteFrame.add(edtNoteScreen);
		edtNoteFrame.setVisible(true);

	}
	
	public class ButtonListener implements ActionListener  {

		@Override
		public void actionPerformed(ActionEvent e) { //displays the task/reminder on JTable 
		    if(e.getSource() == addNoteButton) {
			    String data1 = entrnte.getText();
			    
			    String[] row = { data1 };
			    notesModel.addRow(row); 

			    addNoteFrame.dispose();
		    }
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == adnButton) {
			addNote();
		}
		else if(e.getSource() == editNote) {
			edtNote();
		}
		else if(e.getSource() == tnButton) {
			notesFrame.setVisible(false);
			
			}
		
		else if(e.getSource() == deleteNote) {
			notesModel.removeRow(notesTable.getSelectedRow());
		}
		else if(e.getSource() == updtNoteButton) {
			int j = notesTable.getSelectedRow();
			notesModel.setValueAt(entrnte.getText(), j, 0);
			edtNoteFrame.dispose();
		}
		else if(e.getSource() == cnclButton) {
			JFrame frm = (JFrame) SwingUtilities.getRoot(cnclButton);
			if(frm == edtNoteFrame) {
				edtNoteFrame.dispose();
			}
			else {
				addNoteFrame.dispose();
			}
		}

	}
}
