package DAO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Model.Screen;

import com.alibaba.fastjson.JSON;

public class getScreen {
	String[] asiles;
	ArrayList<Screen> screenList = new ArrayList<Screen>();
	
	public void getScreens(){
	}
	
	//pick out asiles
	public Screen getScreenLayout(String screenName){
		this.screenList = getScreenList();
		Screen result = null;
		for (int i = 0;i<screenList.size();i++){
//			System.out.println(screenList.get(i).getScreenName());
			if (screenName.equals(screenList.get(i).getScreenName())){
				result = screenList.get(i);
			}
		}
		return result;
	}
	
	public ArrayList<Screen> getScreenList(){
		InputStream is=this.getClass().getResourceAsStream("/movies/screen.txt");   
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		
//		File file = new File(".//src//movies//screen.txt");
//		BufferedReader reader = null;
		String input;
		Screen screen;
		
//		try {
//			reader = new BufferedReader(new FileReader(file));
//		} catch (FileNotFoundException e1) {
//			e1.printStackTrace();
//		}
		
		try {
			while((input=reader.readLine())!=null){	
				screen = JSON.parseObject(input,Screen.class);
				screen.setSeatList(screen.getRows(),screen.getColumns());
				screen.setAsiles();
				screenList.add(screen);
//				String[] str = screen.getAsiles();
			}
			reader.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return screenList;
	}
}
