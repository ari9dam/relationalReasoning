package edu.asu.dl.rel.data;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import edu.asu.dl.rel.entity.Table;

public class DataGenerator {

	public static void main(String[] args) throws IOException {
		
		int numberOfSamples  = 100;
		double annotationDensity = 0.3;
		
		int max_height = 5;
		int numTowers = 2;
		List<String> story = new LinkedList<String>();
		RandomTableGenerator rg = new RandomTableGenerator();
		
		List<String> story_test = new LinkedList<String>();
		
		// only one towers
		for(int i=0;i<32;i++){
			Table table = rg.generate(numTowers, max_height, 2);
			story.add(table.toText(annotationDensity));
			story_test.add(table.toText(1.0));
		}
		
		/*for(int i=20;i<60;i++){
			Table table = rg.generate(1, 20, 2);
			story.add(table.toText(annotationDensity));
		}
		
		for(int i=60;i<numberOfSamples;i++){
			Table table = rg.generate(1, 50, 2);
			story.add(table.toText(annotationDensity));
		}*/
		
		String train = "task21_blocksWorld_training_"+numTowers+"_"+max_height+".txt";
		String test = "task21_blocksWorld_test_"+numTowers+"_"+max_height+".txt";
		
		FileUtils.writeStringToFile(new File(train), StringUtils.join(story,"\n"));
		FileUtils.writeStringToFile(new File(test), StringUtils.join(story_test,"\n"));

	}

}
