package edu.asu.dl.rel.entity;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class Tower {
	private List<Block> tower;
	private String id;
	
	public Tower(String id, List<Block> blocks){
		this.tower = new LinkedList<Block>();
		this.tower.addAll(blocks);
		this.id = id;
	}
		
	public Map.Entry<String, Integer> toText(Double frac, int offset){
		List<String> story = new ArrayList<String>(tower.size());
		for(int i=1;i<tower.size();i++){
			story.add("The " + tower.get(i).getName() 
					+ " is on top of the "+ tower.get(i-1).getName() +".");
		}
		
		ArrayList<Integer> perm = new ArrayList<Integer>();
		for(int i=0;i<story.size();i++)
			perm.add(i+1);
		Collections.shuffle(perm);
		
		shuffle(story, perm);
		
		List<String> questions = new ArrayList<String>();
		for(int i=0;i<tower.size();i++){
			for(int j=0;j<tower.size();j++){
				if(i==j){
					continue;
				}
				
				String question = "Is the " + tower.get(i).getName() + " above the " 
				+ tower.get(j).getName() + "? " +(i>j?"\tYes\t":"\tNo\t") 
				+ (i>j?range(j,i,perm,offset):range(i,j,perm,offset));
				questions.add(question);
			}
		}
		
		Collections.shuffle(questions);
		int toIndex = (int)Math.ceil(questions.size()*frac);
		story.addAll(questions.subList(0, toIndex));
		
		for(int i=0;i<story.size();i++){
			story.set(i, (offset+i+1)+" "+ story.get(i));
		}
		
		return new AbstractMap.SimpleImmutableEntry(StringUtils.join(story,'\n'), offset+ story.size()+1);	
	}
	
	private void shuffle(List<String> story, ArrayList<Integer> perm){
		String [] nstory = new String[story.size()];
		
		for(int i=0;i<story.size();i++){
			nstory[perm.get(i)-1] = story.get(i);
		}
		
		story.clear();
		story.addAll(Arrays.asList(nstory));
	}
	private String range(int b1, int b2, ArrayList<Integer> perm, int offset){
		List<Integer> list = new LinkedList<Integer>();
		for(int i=b1+1;i<=b2;i++){
			list.add((perm.get(i-1)+ offset));
		}
		Collections.sort(list);
		return StringUtils.join(list,',');
	}
	

	public String getId() {
		return id;
	}

	public Map.Entry<String, Integer> toText(Double frac, Tower t2, int offset) {
		List<String> story = new ArrayList<String>(tower.size());
		offset ++;
		for(int i=0;i<tower.size();i++){
			for(int j=0;j<t2.tower.size();j++){
				if(i==j){
					continue;
				}
				
				String question = (offset) +" Is the " + tower.get(i).getName() + " above the " 
				+ t2.tower.get(j).getName() + "? " +"\tNo\t1000" ;
				story.add(question);
				offset++;
			}
		}
		
		for(int i=0;i<t2.tower.size();i++){
			for(int j=0;j<tower.size();j++){
				if(i==j){
					continue;
				}
				
				String question = (offset) +" Is the " + t2.tower.get(i).getName() + " above the " 
				+ tower.get(j).getName() + "? " +"\tNo\t1000" ;
				story.add(question);
				offset++;
			}
		}
		
		
		Collections.shuffle(story);
		int toIndex = (int)Math.ceil(story.size()*frac);
		return new AbstractMap.SimpleImmutableEntry<String, Integer>(StringUtils.join(story.subList(0, toIndex),'\n'), 
				offset);	
	}

}
