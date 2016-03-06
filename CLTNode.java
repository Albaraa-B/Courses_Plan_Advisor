import java.util.LinkedList;

public class CLTNode {
	private String courseId;
	private String courseTitle;
	private boolean finished = false;
	private int difficultyLevel;
	private int creditHours;
	private boolean labWork;
	protected int level;
	private int minimum = 0;
	LinkedList<CLTNode> preq = new LinkedList<CLTNode>();    //List of prerequisite courses
	LinkedList<CLTNode> coreq = new LinkedList<CLTNode>();   //List of corequisite courses
	LinkedList<CLTNode> op = new LinkedList<CLTNode>();      //List of opened courses  (After finishing this course)
	
	public CLTNode(String courseId){
		this.courseId = courseId;
		coreq.clear();
		preq.clear();
		op.clear();
		if(courseId.matches("\\w+1+\\d\\d"))
			level = 1;
		else if(courseId.matches("\\w+2+\\d\\d"))
			level = 2;
		else if(courseId.matches("\\w+3+\\d\\d"))
			level = 3;
		if(courseId.matches("\\w+4+\\d\\d") || courseId.matches("\\w+xxx"))
			level = 4;
	
	}
	
	public CLTNode(String courseId,int creditHours,boolean labWork){
		this(courseId);
		this.setCreditHours(creditHours);
		this.labWork = labWork;
	}
	
	public void addPreq(CLTNode course){
		this.preq.add(course);
		course.op.add(this);
	}
	
	
	
	public void addCoreq(CLTNode course){
		this.coreq.add(course);
		//course.coreq.add(this);
	}
	
	public void addOp(CLTNode course){
		this.op.add(course);
		course.preq.add(this);
	}
	
	public void addToFinished(){
		CLT.finished.add(this);
		this.finished = true;
	}
	
	public void setDiffLevel(int difficultyLevel){
		this.setDifficultyLevel(difficultyLevel);
	}
	
	public void setCourseId(String courseId){
		this.courseId = courseId;
	}
	
	public String getCourseId(){
		return this.courseId;
	}
	

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public boolean isFinished() {
		return finished;
	}

	public int getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public boolean hasLabWork(){
		return this.labWork;
	}
	
	public void setLabWork(boolean lbw){
		this.labWork = lbw;
	}
	
	public String info(){
		return String.format("|%s,%b,%d,L:%d|", this.courseId,this.labWork,this.creditHours,this.level);
	}

	public int getMinimum() {
		return minimum;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	
	public void setPriorety(int level){
		this.level = level;
		if(level == 0)
			for(CLTNode t : this.preq)
				t.setPriorety(level);
	}

}
