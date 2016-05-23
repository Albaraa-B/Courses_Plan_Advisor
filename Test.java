import java.util.Scanner;
import java.util.Stack;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CLT swe = new CLT("SWE");
		
		/*
		swe.root.addOp(new CLTNode("Math101"));
		swe.root.addOp(new CLTNode("Phys101"));
		CLTNode course = swe.root.op.get(0);
		course.addOp(new CLTNode("Math102"));
		
		CLTNode math202 = new CLTNode("Math202");
		CLTNode math301 = new CLTNode("Math301");
		CLTNode phys102 = new CLTNode("Phys102");
		phys102.addPreq(swe.root.op.get(1));
		phys102.addCoreq(math202);
		math202.addPreq(swe.root.op.get(0).op.get(0));
		math202.addOp(math301);
		math301.addOp(new CLTNode("SWE399"));
		math301.addOp(new CLTNode("SWE417"));
		
		swe.printTree(swe.root);
		//System.out.printf("%s%n", swe.root.op.get(1).op.get(1).getCourseId());
		
		*/
		swe.root.addToFinished(swe);
		CLTNode CHEM101 = new CLTNode("CHEM101",4,true);
		CLTNode PHYS101 = new CLTNode("PHYS101",4,true);
		CLTNode PHYS102 = new CLTNode("PHYS102",4,true);
		CLTNode MATH101 = new CLTNode("Math101",4,false);
		CLTNode MATH102 = new CLTNode("MATH102",4,false);
	    CLTNode MATH201 = new CLTNode("MATH201",3,false);
	    CLTNode STAT319 = new CLTNode("STAT319",3,true);
	    CLTNode IAS101 = new CLTNode("IAS101",2,false);
	    CLTNode IAS111 = new CLTNode("IAS111",2,false);
	    CLTNode IAS201 = new CLTNode("IAS201",2,false);
	    CLTNode IAS212 = new CLTNode("IAS212",2,false);
	    CLTNode IAS301 = new CLTNode("IAS301",2,false);
	    CLTNode IAS322 = new CLTNode("IAS322",2,false);
	    CLTNode ENGL101 = new CLTNode("ENGL101",3,false);
	    CLTNode ENGL102 = new CLTNode("ENGL102",3,false);
	    CLTNode ENGL214 = new CLTNode("ENGL214",3,false);
	    CLTNode ISE307 = new CLTNode("ISE307",3,false);
	    CLTNode PE101 = new CLTNode("PE101",1,false);
	    CLTNode PE102 = new CLTNode("PE102",1,false);
	    CLTNode SWE205 = new CLTNode("SWE205",3,false);
	    CLTNode SWE215 = new CLTNode("SWE215",3,true);
	    CLTNode SWE312 = new CLTNode("SWE312",3,false);
	    CLTNode SWE316 = new CLTNode("SWE316",3,false);
	    CLTNode SWE326 = new CLTNode("SWE326",3,false);
	    CLTNode SWE363 = new CLTNode("SWE363",3,false);
	    CLTNode SWE387 = new CLTNode("SWE387",3,false);
	    CLTNode SWE399 = new CLTNode("SWE399",0,false);
	    CLTNode SWE417 = new CLTNode("SWE417",3,true);
	    CLTNode SWE418 = new CLTNode("SWE418",2,true);
	    CLTNode ICS102 = new CLTNode("ICS102",3,true);
	    CLTNode ICS201 = new CLTNode("ICS201",4,true);
	    CLTNode ICS202 = new CLTNode("ICS202",4,true);
	    CLTNode ICS233 = new CLTNode("ICS233",4,true);
	    CLTNode ICS253 = new CLTNode("ICS253",3,false);
	    CLTNode ICS254 = new CLTNode("ICS254",3,false);
	    CLTNode ICS324 = new CLTNode("ICS324",4,true);
	    CLTNode ICS343 = new CLTNode("ICS343",4,true);
	    CLTNode ICS353 = new CLTNode("ICS353",3,false);
	    CLTNode ICS431 = new CLTNode("ICS431",4,true);
	    CLTNode COE202 = new CLTNode("COE202",3,false);
	    CLTNode ICSxx1 = new CLTNode("ICSxxx",3,false);
	    CLTNode ICSxx2 = new CLTNode("ICSxxx",3,false);
	    CLTNode CSxxx = new CLTNode("ICSxxx",3,false);
	    CLTNode XExx1 = new CLTNode("XExxx",3,false); 
	    CLTNode XExx2 = new CLTNode("XExxx",3,false);
	    
	    ICSxx1.setMinimum(100);
	    ICSxx2.setMinimum(100);
	    SWE387.setMinimum(65);
	    SWE363.setMinimum(65);
	    CSxxx.setMinimum(100);
	    XExx1.setMinimum(65);
	    XExx2.setMinimum(65);
//	    Stack<CLTNode> s153Stack = new Stack<CLTNode>();
//	    s153Stack.push(ISE307);
//	    s153Stack.push(IAS322);
	    //Term s153 = new Term("153",s153Stack);
//	    swe.courses_stack_list.add(s153Stack);
//	    Stack<CLTNode> s163Stack = new Stack<CLTNode>();
//	    s163Stack.push(SWE399);
	    
	    //Term s163 = new Term("163",s163Stack);
	    
	   // MATH101.addToFinished();
	    //Courses relations
	    //General
	    	//Freshman
		    swe.root.addOp(MATH101);
		    swe.root.addOp(PHYS101);
		    swe.root.addOp(IAS101);
		    swe.root.addOp(IAS111);
		    swe.root.addOp(PE101);
		    swe.root.addOp(CHEM101);
		    swe.root.addOp(ENGL101);
		    PHYS101.addCoreq(MATH101);
		    PHYS101.addOp(PHYS102);
		    MATH101.addOp(MATH102);
		    MATH102.addOp(MATH201);
		    PE101.addOp(PE102);
		    ENGL101.addOp(ENGL102);
		    ENGL102.addOp(ENGL214);
		    PHYS102.addCoreq(MATH102);
		    PHYS102.addOp(COE202);
		    IAS101.addOp(IAS201);
		    IAS111.addOp(IAS212);
		    //Sophomore
		    MATH201.addOp(STAT319);
		    IAS201.addOp(IAS301);
		    IAS212.addOp(IAS322);
		    //ENGL214.addOp(SWE399);
		    //Junior
		    swe.root.addOp(ISE307);
		    //Senior
		    swe.root.addOp(XExx1);
		    swe.root.addOp(XExx2);
	    
	    //Core
	    swe.root.addOp(ICS102);
	    swe.root.addOp(SWE363);
	    ICS102.addOp(ICS201);
	    ICS102.addOp(ICS253);
	    ICS102.addOp(SWE205);
	    
	    ICS253.addOp(ICS254);
	    ICS253.addOp(ICS353);
	    
	    SWE205.addOp(SWE215);
	    SWE205.addOp(SWE312);
	    
	    ICS201.addOp(ICS202);
	    ICS201.addOp(ICS233);
	    ICS201.addOp(ICS343);
	    
	    COE202.addOp(ICS233);
	    
	    SWE215.addOp(SWE316);
	    SWE215.addOp(SWE326);
	    
	    ICS202.addOp(SWE316);
	    ICS202.addOp(ICS353);
	    ICS202.addOp(ICS324);
	    
	    ICS233.addOp(ICS431);
	    
	    SWE316.addOp(SWE417);
	    
	    SWE312.addOp(SWE326);
	    
	    swe.root.addOp(SWE387);
	    
	    SWE326.addOp(SWE418);
	    
	    //SWE363.addOp(SWE399);
	    
	    SWE417.addOp(SWE418);
	    
	    swe.root.addOp(ICSxx1);
	    swe.root.addOp(ICSxx2);
	    swe.root.addOp(CSxxx);
	    
	    //swe.printTree(swe.root);
	    /*
	    swe.generateAPlan(1);
	    
	    System.out.println();
	    swe.generateAPlan(1);
	    
	    System.out.println();
	    swe.generateAPlan(1);
	    
	    System.out.println();
	    swe.generateAPlan(1);
	    
	    System.out.println();
	    swe.generateAPlan(1);
	    
	    System.out.println();
	    swe.generateAPlan(1);
	    
	    System.out.println();
	    swe.generateAPlan(1);
	    
	    System.out.println();
	    swe.generateAPlan(1);
	    
	    System.out.println();
	    swe.generateAPlan(1);
	    
	    System.out.println();
	    swe.generateAPlan(1);
	    */
	    //My Plan
//	    MATH101.addToFinished(swe);
//	    MATH102.addToFinished(swe);
//	    PHYS101.addToFinished(swe);
//	    PHYS102.addToFinished(swe);
//	    CHEM101.addToFinished(swe);
//	    PE101.addToFinished(swe);
//	    ENGL101.addToFinished(swe);
//	    IAS101.addToFinished(swe);
//	    ICS102.addToFinished(swe);
//	    PE102.addToFinished(swe);
//	    PHYS102.addToFinished(swe);
//	    ENGL102.addToFinished(swe);
//	    IAS111.addToFinished(swe);
//	    MATH201.addToFinished(swe);
//	    ICS253.addToFinished(swe);
//	    SWE205.addToFinished(swe);
//	    ICS201.addToFinished(swe);
//	    COE202.addToFinished(swe);
//	    STAT319.addToFinished(swe);
//	    ENGL214.addToFinished(swe);
//	    XExx1.addToFinished(swe);
//	    XExx2.addToFinished(swe);
//	    IAS212.addToFinished(swe);
//	    IAS301.addToFinished(swe);
//	    IAS201.addToFinished(swe); 
//	    ICS202.addToFinished(swe);
//	    ICS233.addToFinished(swe);
//	    ICS254.addToFinished(swe);
//	    SWE215.addToFinished(swe);
//	    
//	    ISE307.addToFinished(swe);
//	    IAS322.addToFinished(swe);
//	    
//	    
//	    SWE316.setPriorety(0);
//	    SWE387.setPriorety(0);
//	    SWE417.setPriorety(0);
//	    SWE418.setPriorety(0);
	    
	    
	    swe.plan();
	    System.out.println();
	    swe.plan();
	    System.out.println();
	    //swe.courses_stack_list.add(s163Stack);
	    swe.plan();
	    System.out.println();
	    swe.plan();
	    System.out.println();
	    swe.plan();
	    System.out.println();
	    swe.plan();
	    System.out.println();
	    swe.plan();
	    System.out.println();
	    swe.plan();
	    System.out.println();
	    swe.plan();
	    System.out.println();
	    swe.plan();
	    System.out.println();
	    
	    System.out.println(swe.getTotal());
	    Scanner s = new Scanner(System.in);
//	    System.out.println("Menu:");
//	    System.out.printf("1. Swap Courses%n%nchoice > ");
//	    int choice = s.nextInt();
	    s.close();
	    
	    
	    new GUI(swe);
	    
	    
	   /* for(CLTNode t : CLT.finished){
	    	System.out.printf("%s%n", t.info());
	    }*/
	    
	}

}
