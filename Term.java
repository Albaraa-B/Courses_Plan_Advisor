import java.util.LinkedList;
import java.util.Stack;

public class Term extends CLT{
	 protected LinkedList<CLTNode> term_courses = new LinkedList<CLTNode>();
	private String term = "";
	protected int max_credit = 19;
	protected int credit = 0;
	protected int labs = 0;
	protected boolean summer;
	protected int course_count = 0;
	
	public Term(String name,Stack<CLTNode> courses , boolean summer){
		this.term = name;
		this.summer = summer;
		
		for(int i = 0 ; i < courses.size(); i++){
			CLTNode temp = (CLTNode) courses.pop();
			credit += temp.getCreditHours();
			labs += temp.hasLabWork()? 1:0;
			term_courses.add(temp);
			System.out.println("T: "+ getName() + "  Added: "+ temp.getCourseId());
		}
	}
	
	public Term(String name,Stack<CLTNode> courses){
		this.term = name;
		this.summer = false;
		Stack<CLTNode> term_courses_stack = courses;
		System.out.println("               "+term_courses_stack.size()+"    ");
		int size = term_courses_stack.size();
		for(int i = 0 ; i < size ; i++){
			CLTNode temp = (CLTNode) term_courses_stack.pop();
			credit += temp.getCreditHours();
			labs += temp.hasLabWork()? 1:0;
			course_count++;
			term_courses.add(temp);
			System.out.println(i+"  T: "+ getName() + "  Added: "+ temp.getCourseId());
		}
	}
	
	public void update(){
		for(CLTNode temp : term_courses){
			credit += temp.getCreditHours();
			labs += temp.hasLabWork()? 1:0;
			term_courses.add(temp);
			System.out.println("T: "+ getName() + "  Added: "+ temp.getCourseId());
		}
	}
	
	
	
	public String getName(){
		return this.term;
	}

}
