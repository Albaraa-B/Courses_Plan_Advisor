import java.util.Collections;
import java.util.Comparator;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Stack;

public class CLT {
	CLTNode root;
	static Stack<CLTNode> courses = new Stack<CLTNode>();
	static LinkedList<CLTNode> finished = new LinkedList<CLTNode>();
	
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
	
	public static int getTotal(){
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
					temp.addToFinished();
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
	
	public static boolean coreqCheck(CLTNode course){
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
	
	public static void plan(){
		Collections.sort(finished, sortByLevel);;
		if(!finished.isEmpty()){
			int credit = 0;
			int numOfLabs = 0;
			
				for(CLTNode course : finished){
					for(CLTNode opcourse : course.op){
						if(!finished.contains(opcourse)){
							boolean preqready = preqCheck(opcourse);
							boolean coreqready = coreqCheck(opcourse);
							
							if(preqready  && credit <= 15 && numOfLabs <= 3 && opcourse.getMinimum() <= CLT.getTotal()){
								if(!courses.contains(opcourse)){ 
									courses.push(opcourse);
									credit += opcourse.getCreditHours();
									if(opcourse.hasLabWork()) numOfLabs++;
									}
								
							}
						}
					}
				}
			
			try{
				CLTNode temp;
				System.out.printf("%nCredit hours: %d, Labs: %d,%n", credit,numOfLabs);
				while(courses.size() >= 0){
					temp = courses.pop();
					temp.addToFinished();
					System.out.printf("%s, ",temp.info());
					
				}
				courses.clear();
			}catch(EmptyStackException e){
				
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
	
	

}
