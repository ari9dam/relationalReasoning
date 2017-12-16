package edu.asu.dl.rel.data;

import edu.asu.dl.rel.entity.Answers;
import edu.asu.dl.rel.entity.Table;

public class BlocksWorld {
	private int towers;
	private int maxHeight;
	private int tableDim;
	private Table table;
	
	public BlocksWorld(int towers, int maxHeight, int tableDim) {
		super();
		this.towers = towers;
		this.maxHeight = maxHeight;
		this.tableDim = tableDim;
		
		RandomTableGenerator rg = new RandomTableGenerator();
		this.table = rg.generate(towers, maxHeight, tableDim);
	}
	
	public String toASP(){
		return "";
	}
	
	public Answers solve(String program){
		return null;
	}
	
	public String toStory(){
		String asp = this.toASP();
		Answers answer = this.solve(asp);
		
		/**
		 * Generate world description
		 */
		
		/***
		 * Generate questions
		 */
		return "";
	}
	
}
