import java.util.Collections;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;

public class CLT {
	CLTNode root;
	protected Stack<CLTNode> courses = new Stack<CLTNode>();
	protected LinkedList<CLTNode> finished = new LinkedList<CLTNode>();
	protected LinkedList<Term> term_list = new LinkedList<Term>();
	protected LinkedList<Stack<CLTNode>> courses_stack_list = new LinkedList<Stack<CLTNode>>();
	protected int terms = 0;
	protected int years = 0;
	
	public CLT(){
		root = null;
	}
	
	public CLT(String major){
		root = new CLTNode(major);
	}
	
	public void printTree(CLTNode temp){
		System.out.printf("%n%n%s:%n",temp.getCourseId());
		System.out.printf("Prerequisite: ");
		for(CLTNode pre: temp.preq){
			System.out.printf("%s, ",pre.getCourseId());
		}
		for(CLTNode course:temp.op){
			printTree(course);
		}
		
	}
	
	public int getTotal(){
		int credit = 0;
		for(CLTNode course : finished)
			credit += course.getCreditHours();
		return credit;
	}
	
	public void generateAPlan(int diff){
		if(diff == 1){
			CLTNode temp = root;
			int credit = 0;
			int numOfLabs = 0;
			
			for(CLTNode course : root.op){
				if(course.isFinished() == false){
					if(credit <= 12 && numOfLabs <= 2){
						courses.push(course);
						credit += course.getCreditHours();
						if(course.hasLabWork()) numOfLabs++;
					}else
						break;
				}else{
					for(CLTNode opcourse : course.op){
						if(opcourse.isFinished() == false){
							boolean ready = true;
							for(CLTNode preq : opcourse.preq){
								if(preq.isFinished())
									continue;
								else{
									ready = false;
									break;
								}
							}
								if(ready){
									if(credit <= 12 && numOfLabs <= 2){
										courses.push(opcourse);
										credit += opcourse.getCreditHours();
										if(opcourse.hasLabWork()) numOfLabs++;
								}
							}
						}else{
							//System.out.printf("%n////To-Do: Implement better Tree Traverse Algorithm%n");
							CLTNode tmp = findAnOpenCourse(opcourse);
							if(credit <= 12 && numOfLabs <= 2){
									if(!courses.contains(tmp)){
										courses.push(tmp);
										credit += tmp.getCreditHours();
										if(tmp.hasLabWork()) numOfLabs++;
								}else{
									//System.out.printf("%nStarted seeing duplicates!%n");
									continue;
								}
						}
						}
					}
				}
			}
			
			
			System.out.printf("%nCredit hours: %d, Labs: %d,%n", credit,numOfLabs);
			
			try{
				while(courses.size() >= 0){
					temp = courses.pop();
					temp.addToFinished(this);
					System.out.printf("%s, ",temp.info());
					
				}
			}catch(EmptyStackException e){
				
			}
		}
	}
	
	public CLTNode findAnOpenCourse(CLTNode temp){
		CLTNode openCourse = root;
		for(CLTNode course : temp.op){
			if(course.isFinished())
				return findAnOpenCourse(course);
			else{
				if(preqCheck(course)){ 
					openCourse = course;
					break;
				}else{
					for(CLTNode preqCourse : course.preq){
						if(preqCheck(preqCourse)){ 
							openCourse = preqCourse;
							break;
							}
					}
				}
			}
		}
		if(openCourse != root)
			return openCourse;
		else
			return findAnOpenCourse(root);
		
		/*
		for(CLTNode course: temp.op){
			boolean isReady = true;
			if(!course.isFinished()){
				for(CLTNode preq : course.preq){
					if(preq.isFinished())
						continue;
					else{
						isReady = false;
						break;
					}
				}
				if(isReady){ 
					course.addToFinished();
					return course;
				}
					
				}else
					continue;
		}
		for(CLTNode preqCourse: temp.preq){
			return findAnOpenCourse(preqCourse);
		}
		return findAnOpenCourse(root);
		*/
	}
	
	public static boolean preqCheck(CLTNode course){
		boolean ready = true;
		for(CLTNode preqCourse : course.preq){
			if(!preqCourse.isFinished()){
				ready = false;
				break;
			}
		}
		return ready;
	}
	
	public CLTNode getMeAPreq(CLTNode temp){
		if(!temp.isFinished()){
			for(CLTNode preq : temp.preq){
				if(!preq.isFinished())
					if(preqCheck(preq)) return preq;
					else return getMeAPreq(preq);
				else continue;
			}
		}
		return root;
	}
	
	
	public CLTNode getMeACourse(CLTNode course){
		if(course.isFinished()){
			for(CLTNode openedcourse : course.op){
				
			}
		}else{
			if(preqCheck(course)) return course;
			else
				return getMeAPreq(course);
		}
		return getMeACourse(root);
	}
	
	public  boolean coreqCheck(CLTNode course){
		boolean taken = true;
		if(!course.op.isEmpty())
			for(CLTNode coreq : course.op){
				if(!finished.contains(coreq)){
					taken = false;
				}else{
					if(courses.contains(coreq)){
						taken = true;
					}
				}
			}
		else
			taken = true;
		return taken;
	}
	
	public void plan(){
		Collections.sort(finished, sortByLevel);
		
		if(!finished.isEmpty()){
			int credit = 0;
			int numOfLabs = 0;
			
				for(CLTNode course : finished){
					for(CLTNode opcourse : course.op){
						if(!finished.contains(opcourse)){
							boolean preqready = preqCheck(opcourse);
							boolean coreqready = coreqCheck(opcourse);
							
							if(preqready  && credit <= 16 && numOfLabs <= 3 && opcourse.getMinimum() <= getTotal()){
								if(!courses.contains(opcourse)){ 
									courses.push(opcourse);
									credit += opcourse.getCreditHours();
									if(opcourse.hasLabWork()) numOfLabs++;
									}
								
							}
						}
					}
				}
			
			
				CLTNode temp;
				if(courses.size() > 0)
					System.out.printf("%nCredit hours: %d, Labs: %d,%n", credit,numOfLabs);
				//else
					//System.out.println("No More Courses!");
//				if(terms % 3 != 0)
//					term_list.add(new Term(""+terms,courses,true));
//				else
//					term_list.add(new Term(""+terms,courses,true));
				Stack<CLTNode> stack = new Stack<CLTNode>();
				
				//stack = courses;
				try{
				while(courses.size() > 0){
					temp = courses.pop();
					temp.addToFinished(this);
					stack.push(temp);
					System.out.printf("%s, ",temp.info());
					
				}
				if(stack.size() > 0){
					courses_stack_list.add(stack);
				}
				System.out.println("Stack: "+ stack.size());
				courses.clear();
				terms++;
				if(terms %2 == 0)
					years++;
			}catch(EmptyStackException e){
				System.out.println("BOOO!, Exception");
			}
			
		}
	}
	static Comparator<CLTNode> sortByLevel = new Comparator<CLTNode>(){

		public int compare(CLTNode o1, CLTNode o2) {
			if(o1.level > o2.level)
				return 1;
			else if(o1.level < o2.level)
				return -1;
			else
				return 0;
		}

		
		
	};
	
	
	public boolean swapCourse(String course, int term){
			boolean found = false;
			CLTNode temp = null;
			System.out.println("Terms: "+term_list.size());
			for(int i = 0; i < term_list.size(); i++){
				if(found)
					break;
				for(CLTNode t : term_list.get(i).term_courses){
					System.out.println(t.getCourseId());
					if(t.getCourseId().equalsIgnoreCase(course)){
						temp = t;
						term_list.get(i).term_courses.remove(t);
						term_list.get(i).credit -= t.getCreditHours();
						term_list.get(i).labs -= t.hasLabWork()? 1:0;
						term_list.get(i).course_count--;
						found = true;
						break;
					}
				}
				
				
			}
			System.out.println("-----------------------------------------------------------");
			if(found){
				term_list.get(term).term_courses.add(temp);
				term_list.get(term).credit += temp.getCreditHours();
				term_list.get(term).labs += temp.hasLabWork()? 1:0;
				term_list.get(term).course_count++;
				for(CLTNode t : term_list.get(term).term_courses)
					System.out.println(t.getCourseId());
				return true;
			}else
				return false;
		
		
	}
	
	

}
