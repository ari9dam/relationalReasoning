package edu.asu.dl.rel.entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;

public class Table {
	int width;
	int height;
	
	
	private Tower[][] table;
	
	private List<Tower> towers = null;
	
	public Table(int width, int height){
		this.width = width;
		this.height = height;
		this.table = new Tower[width][height];
		this.towers = new LinkedList<Tower>();
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Tower[][] getTable() {
		return table;
	}

	public void set(int x, int y, Tower val) {
		
		if(x>=width || y>=height){
			throw new IllegalArgumentException("position is outside table: "+x+","+y);
		}
		
		this.table[x][y] = val;
		this.towers.add(val);
		
	}
	
	public String toText(Double frac){
		List<String> story = new LinkedList<String>();
		int offset = 0;
		for(int i=0;i< width;i++){
			for(int j=0;j<height;j++){
				if(table[i][j]==null)
					continue;
				Tower tower = table[i][j];
				Entry<String, Integer> entry = tower.toText(frac,offset);
				story.add(entry.getKey());
				offset = entry.getValue();				
			}
		}
		
		for(Tower t1: towers){
			for(Tower t2: towers){
				if(t1==t2) continue;
				
				Entry<String, Integer> entry = t1.toText(frac,t2,offset);
				story.add(entry.getKey());
				offset = entry.getValue();	
			}
		}
		
		return StringUtils.join(story,'\n');
	}
}
