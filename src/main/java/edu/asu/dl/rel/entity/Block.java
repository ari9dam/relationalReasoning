package edu.asu.dl.rel.entity;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Block {
	private String name;
	private int id;
	private static List<String> names = null;
	
	static {
		try {
			names = FileUtils.readLines(new File("src\\main\\resources\\colors.txt")
					,Charset.forName("ASCII"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			
	public Block(int id) {
		super();
		this.id = id;
	}

	public String getName() {
		return names.get(id)+" block";
	}

	public int getId() {
		
		return id;
	}
		
}
