package edu.asu.dl.rel.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.asu.dl.rel.entity.Block;
import edu.asu.dl.rel.entity.Table;
import edu.asu.dl.rel.entity.Tower;

public class RandomTableGenerator {
	public RandomTableGenerator(){
		
	}
	
	public Table generate(int towers, int maxHeight, int tableDim){
		Table table = new Table(tableDim,tableDim);
		
	    Random random = new Random();
	    
	    Random height = new Random();
	    
	    int id=1;
	    for (int i = 0; i < towers ; i++){
	       int val = random.nextInt(tableDim*tableDim);
	       
	       int x = val/tableDim;
	       int y = val%tableDim;
	       int towerHeight = height.nextInt(maxHeight-1)+2;
	       
	       List<Block> blocks = new ArrayList<Block>(towerHeight);
	       for(int j=1;j<=towerHeight;j++){
	    	   Block b = new Block(id);
	    	   id++;
	    	   blocks.add(b);
	       }
	       table.set(x,y,new Tower("Tower " + (i+1), blocks));
	    }
	   
		return table;
	}
}
