import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Stack;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;

public class GUI extends JFrame implements MouseListener{
	protected JPanel main_panel = new JPanel();
	protected JPanel info_panel = new JPanel();
	protected JMenuBar mb = new JMenuBar();
	protected JMenuItem swap = new JMenuItem("Swap");
	private static CLT clt;
	
	public GUI(CLT clt){
		super("Course Plan Advisor 0.1");
		this.clt = clt;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		main_panel.setLayout(new GridLayout(0,1));
		setJMenuBar(mb);
		mb.add(swap);
		swap.addActionListener(mb_lis);
		add(main_panel);
		info_panel.setBackground(Color.WHITE);
		info_panel.setLayout(new GridLayout(0,1));
		add(info_panel,BorderLayout.EAST);
		getem();
		pack();
		setVisible(true);
		//main_panel.s
		
	}
	
	public void getTerms(CLT clt){
		for(Term t : clt.term_list){
			System.out.println("                   S:  "+t.getName());
			JPanel term_panel = new JPanel();
			JLabel term_name = new JLabel(t.getName());
			JPanel courses_panel = new JPanel();
			courses_panel.setLayout(new GridLayout(1,0));
			JScrollPane courses_scroller = new JScrollPane(courses_panel);
			int i = 0;
			for(CLTNode temp : t.term_courses){
				System.out.println("C: "+temp.getCourseId()+" S: "+t.getName());
				JPanel course_panel = new JPanel();
				if(i%2 == 0)
					course_panel.setBackground(Color.GRAY);
				else
					course_panel.setBackground(Color.WHITE);
				JLabel course_name = new JLabel(temp.getCourseId());
				JLabel course_credit = new JLabel(""+temp.getCreditHours());
				course_panel.add(course_name);
				course_panel.add(course_credit);
				courses_panel.add(course_panel);
				i++;
			}
			term_panel.setLayout(new GridLayout(2,1));
			term_panel.add(term_name);
			term_panel.add(courses_scroller);
			main_panel.add(term_panel);
		}
	}
	
	
	MouseListener mouse_lis = new MouseListener(){

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			JComponent temp = (JComponent) e.getComponent();
			if(  ((JComponent) e.getComponent()).getBorder() != null )
				((JComponent) e.getComponent()).setBorder(null);
			else
				((JComponent) e.getComponent()).setBorder(new LineBorder(Color.BLUE,1));
			if(((JLabel) temp).getText() != null){
				String course = (((JLabel) e.getComponent().getComponentAt(e.getPoint())).getText());
				System.out.println(course);
				JLabel courseID = new JLabel(course);
				info_panel.add(courseID);
				GUI.this.pack();
			}
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	};
	
	public void getem(){
		int i = 0;
		for(Stack<CLTNode> s : clt.courses_stack_list){
			System.out.println(s.size()+"");
			
			Term term = new Term(""+i,s);
			System.out.println("                   S:  "+term.getName());
			JPanel term_panel = new JPanel();
			term_panel.setBorder(new LineBorder(Color.black,1));
			JLabel term_name = new JLabel("Term: "+term.getName()+"   Courses: "+term.course_count+"   Credits: "+term.credit+"   Labs: "+term.labs);
			term_panel.setBackground(Color.WHITE);
			JPanel courses_panel = new JPanel();
			//courses_panel.addMouseListener(mouse_lis);
			courses_panel.setLayout(new GridLayout(1,0));
			JScrollPane courses_scroller = new JScrollPane(courses_panel);
			int j = 0;
			for(CLTNode temp : term.term_courses){
				//System.out.println("C: "+temp.getCourseId()+" S: "+term.getName());
				JPanel course_panel = new JPanel();
				if(j%2 == 0)
					course_panel.setBackground(Color.GRAY);
				else
					course_panel.setBackground(Color.WHITE);
				course_panel.addMouseListener(mouse_lis);
				JLabel course_name = new JLabel(temp.getCourseId());
				JLabel course_credit = new JLabel(""+temp.getCreditHours());
				course_panel.add(course_name);
				course_panel.add(course_credit);
				courses_panel.add(course_panel);
				j++;
			}
			term_panel.setLayout(new GridLayout(2,1));
			term_panel.add(term_name);
			term_panel.add(courses_scroller);
			main_panel.add(term_panel);
			i++;
			clt.term_list.add(term);
			
		}
	}
	
	ActionListener mb_lis = new ActionListener(){
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == swap){
				String course = JOptionPane.showInputDialog(null, "Enter the Target Course:", "Change the Location of a Course", JOptionPane.INFORMATION_MESSAGE);
				String termString = JOptionPane.showInputDialog(null, "Enter the Number of the Destination Term:", "Change the Location of a Course", JOptionPane.INFORMATION_MESSAGE);
				if( termString.matches("\\d") && !termString.isEmpty() ){
					int term = Integer.parseInt(termString);
					clt.swapCourse(course,term);
					System.out.println(clt.courses_stack_list.size());
					GUI.this.main_panel.removeAll();
					GUI.this.pack();
					updateTerms();
					GUI.this.pack();
					
					
				}
				
			}
		}
	};
	
	public void updateTerms(){
		int i = 0;
		for(Term s : clt.term_list){
			System.out.println(s.courses.size()+"");
			Term term = s;
			System.out.println("                   S:  "+term.getName());
			JPanel term_panel = new JPanel();
			term_panel.setBorder(new LineBorder(Color.black,1));
			
			JLabel term_name = new JLabel("Term: "+term.getName()+"   Courses: "+term.course_count+"   Credits: "+term.credit+"   Labs: "+term.labs);
			term_panel.setBackground(Color.WHITE);
			JPanel courses_panel = new JPanel();
			//courses_panel.addMouseListener(mouse_lis);
			courses_panel.setLayout(new GridLayout(1,0));
			JScrollPane courses_scroller = new JScrollPane(courses_panel);
			int j = 0;
			for(CLTNode temp : term.term_courses){
				//System.out.println("C: "+temp.getCourseId()+" S: "+term.getName());
				JPanel course_panel = new JPanel();
				if(j%2 == 0)
					course_panel.setBackground(Color.GRAY);
				else
					course_panel.setBackground(Color.WHITE);
				course_panel.addMouseListener(mouse_lis);
				JLabel course_name = new JLabel(temp.getCourseId());
				JLabel course_credit = new JLabel(""+temp.getCreditHours());
				course_panel.add(course_name);
				course_panel.add(course_credit);
				courses_panel.add(course_panel);
				j++;
			}
			term_panel.setLayout(new GridLayout(2,1));
			term_panel.add(term_name);
			term_panel.add(courses_scroller);
			main_panel.add(term_panel);
			i++;
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
