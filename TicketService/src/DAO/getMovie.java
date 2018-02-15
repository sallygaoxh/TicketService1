package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Model.Movie;
import Model.Screen;

import com.alibaba.fastjson.JSON;

public class getMovie {
	ArrayList<String> movieNameList = new ArrayList<String>();
	ArrayList<String[]> timeList = new ArrayList<String[]>();
	ArrayList<Movie> movieArrange = new ArrayList<Movie>();
	public getMovie(){
		
	}
	
	public ArrayList<String> getMovieNameList(){
		InputStream is=this.getClass().getResourceAsStream("/movies/movie.txt");   
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String input;
		String[] info;
		
		try {
			while((input=reader.readLine())!=null){	
				info = input.split(";");
				movieNameList.add(info[0]);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movieNameList;
	}
	
	public ArrayList<String[]> getTimeList(String movieName){
//		File file = new File(".//src//movies//arrangement.txt");
		InputStream is=this.getClass().getResourceAsStream("/movies/arrangement.txt");   
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		String input;
		String[] info;
		
		try {
			while((input=reader.readLine())!=null){	
				info = input.split(",");
				if(info[1].equals(movieName)){
					String[] output = new String[2];
					output[0] = info[0];
					output[1] = info[2];
					timeList.add(output);
					System.out.println(output[0]+output[1]);
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return timeList;
	}
	
	public ArrayList<Movie> getMovieArrangement(){
//		File file = new File(".//src//movies//arrangement.txt");
		InputStream is=this.getClass().getResourceAsStream("/movies/arrangement.txt");   
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
		String input;
		String[] info;

		try {
			while((input=reader.readLine())!=null){	
				info = input.split(",");
				for (int i = 0;i<this.movieNameList.size();i++){
					if(info[1].equals(movieNameList.get(i))){
						Movie movie = new Movie(info[1],info[2],info[0]);
						this.movieArrange.add(movie);
					}
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return movieArrange;
	}
}
