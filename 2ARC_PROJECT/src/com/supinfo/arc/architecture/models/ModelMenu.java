 package com.supinfo.arc.architecture.models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 
 * Idees a rajouter
 * partie graphique network admin
 * @author Asus
 *
 */


public class ModelMenu{
	private String name;
	private String psw;
	private boolean verif;
	private Connection conn = DatabaseConnector.conn();
	private Scanner keyboard = new Scanner(System.in);
	private String configuration = "configuration";
	private String originalConfiguration = "originalConfiguration";
	
	public void welcome() throws Exception {
		boolean continueM = true;
		
		System.out.println("Welcome to the Cap2019 Server");
		System.out.println();
		System.out.println("Charging configuration...");
		loading();
		System.out.println();
		System.out.print("Login : ");
		name = keyboard.next();
		System.out.print("Password : ");
		psw = keyboard.next();
		
		while(continueM){
			verif = checkAccount(name, psw);
			if(verif)
				continueM = false;
			else {
				System.out.println("Authentification Failed, try again : ");
				System.out.println();
				System.out.print("Login : ");
				name = keyboard.next();
				System.out.print("Password : ");
				psw = keyboard.next();
				verif = checkAccount(name, psw);
			}
		}
		if(verif) {
			int id = -99;
			String profession = null;
			boolean program = true;
			int choice = 0;
			char answer;
			String nameF;
			
			/* pour afficher son nom avec la premiere lettre majuscule et le reste minuscule*/
			nameF = name;
			nameF = nameF.toLowerCase();
			char[] lettres = name.toCharArray();
			String nameL = nameF.replace(lettres[0], Character.toUpperCase(lettres[0]));
			
			System.out.println();
			System.out.println("Welcome " + nameL);
			System.out.println();
			
			PreparedStatement prepStmtObj = conn.prepareStatement("SELECT * FROM employees WHERE emp_name = ?");
			prepStmtObj.setString(1, name);
			ResultSet resultSetObj = prepStmtObj.executeQuery();
			while(resultSetObj.next()) {
	        	 id = resultSetObj.getInt("id");
	        	 profession = resultSetObj.getString("emp_profession");
	        } 
			
			
			while(program) {
				System.out.println("1 - Show my colleagues");
				System.out.println("2 - Show my equipments");
				System.out.println("3 - Show available rooms");
				System.out.println("4 - Show how many documents can I print");
				System.out.println("5 - Print documents");
				System.out.println("6 - Simulate at");
				System.out.println("7 - Messaging");
				System.out.println();
				System.out.println("Choose what you want to do : ");
				System.out.println();
				choice = keyboard.nextInt();
				switch(choice) {
					case 1:
						showColleagues(id, profession);
						break;
					case 2:
						showEquipments(id);
						break;
					case 3:
						showRooms(id);
						break;
					case 4:
						showDocuments(profession);
						break;
					case 5:
						printDocuments(profession);
						break;
					case 6:
						simulateAt();
						break;
					case 7:
						messaging();
						break;
					
					default:
						break;
				}
				System.out.println("Do you want to continue Y/N");
				answer = keyboard.next().charAt(0);
				if(Character.toLowerCase(answer) != 'y') {
					program = false;
				}
			}
			
			System.out.println("Bye");
		}
	}
	
	/**
	 * Fonction qui va charger au demarrage de l'application les elements par defaut
	 * @throws Exception
	 */
	public void loading() throws Exception{
		Thread th1 = new Thread();
		th1.sleep(7000);
		File file = new File(originalConfiguration);
		File file2 = new File(configuration);
		
		try {
			Files.copy(file.toPath(), file2.toPath(), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public boolean checkAccount(String username, String password) throws SQLException {
		PreparedStatement prepStmtObj = conn.prepareStatement("SELECT * FROM employees WHERE lower(emp_name) = ?");
		prepStmtObj.setString(1, username.toLowerCase());
		ResultSet resultSetObj = prepStmtObj.executeQuery();
        
        while(resultSetObj.next()) {
        	if(resultSetObj.getString("emp_psw").equals(password))
        		return true;
        	else
        		return false;
        }
        return false;
    }
	
	public void showColleagues(int id, String profession) throws SQLException{
		PreparedStatement prepStmtObj = conn.prepareStatement("SELECT * FROM employees where id != ?");
		prepStmtObj.setInt(1, id);
		ResultSet resultSetObj = prepStmtObj.executeQuery();
        
		System.out.println("List of your colleagues : ");
        while(resultSetObj.next()) {
        	String prof = resultSetObj.getString("emp_profession");
	        if (prof.equals(profession)) {
	        	System.out.println(resultSetObj.getString("emp_name") + " " + resultSetObj.getString("emp_profession"));
	        }
        }  
	}
	
	public void showEquipments(int id) throws SQLException{
		PreparedStatement prepStmtObj = conn.prepareStatement("SELECT * FROM used_eqp_emp, equipments WHERE used_eqp_emp.emp_id = ? AND equipments.id = used_eqp_emp.eqp_id");
		prepStmtObj.setInt(1, id);
		ResultSet resultSetObj = prepStmtObj.executeQuery();
        
		System.out.println("List of your equipments : ");
        while(resultSetObj.next()) {
	        System.out.println(resultSetObj.getString("equipment_type"));
        } 
	}
	
	public void showRooms(int id) throws SQLException{
		char answer;
		String nameRoom;
		int date;
		List<String> lists = new ArrayList<String>();
		PreparedStatement prepStmtObj = conn.prepareStatement("SELECT * FROM ROOMS WHERE available = 1");
		ResultSet resultSetObj = prepStmtObj.executeQuery();
		
		System.out.println("List of all rooms : ");
		while(resultSetObj.next()) {
			System.out.println(resultSetObj.getString("room_type"));
			lists.add(resultSetObj.getString("room_type"));
		}
		System.out.println("Do you want to book a room ? Y/N");
		answer = keyboard.next().charAt(0);
		if(Character.toLowerCase(answer) == 'y') {
			keyboard.nextLine();
			System.out.println("Please enter the name of the room that you want to book");
			nameRoom = keyboard.nextLine();
			if(lists.contains(nameRoom)) {
				String time_slot[] = {"8am - 10am","10am - 12am","2pm - 4pm"};
				System.out.println("1-time slot: 8am - 10am");
				System.out.println("2-time slot: 10am - 12am");
				System.out.println("3-time slot: 2pm - 4pm");
				System.out.println("Please select your time slot");
				date = keyboard.nextInt();
				PreparedStatement prepStmtObj2 = conn.prepareStatement("SELECT * FROM rooms WHERE room_type = ?");
				prepStmtObj2.setString(1, nameRoom);
				ResultSet resultSetObj2 = prepStmtObj2.executeQuery();
				while(resultSetObj2.next()) {
					int idRoomChoosed = resultSetObj2.getInt("id");
					PreparedStatement prep3 = conn.prepareStatement("INSERT INTO meeting_rooms VALUES(NULL, ?, ?, ?)");
					prep3.setInt(1, idRoomChoosed);
					prep3.setInt(2, id);
					prep3.setString(3, time_slot[date]);
					prep3.executeUpdate();
					
					PreparedStatement prep4 = conn.prepareStatement("UPDATE rooms set available=0 WHERE id=?");
					prep4.setInt(1, idRoomChoosed);
					prep4.executeUpdate();
				}
				System.out.println("Booking successfull");
			}else {
				System.out.println("Incorrect name's room");
			}
		}
	}

	public void showDocuments(String profession) throws Exception{
		String ligne;
		BufferedReader fichier = new BufferedReader(new FileReader(configuration));
		ArrayList<String> list = new ArrayList();

		while((ligne=fichier.readLine()) != null) {
			list.add(ligne);
		}
		
		for(int i = 0; i < list.size(); i++) {
			String[] spliter = list.get(i).split(":");
			if(profession.equals(spliter[0])) {
				System.out.println("Number of documents " + spliter[1]);
			}
		}
		
		fichier.close();
	}
	
	public int getNumberDocuments(String profession) throws Exception{
		String ligne;
		int nbDocs = 0;
		BufferedReader fichier = new BufferedReader(new FileReader(configuration));
		ArrayList<String> list = new ArrayList<String>();

		while((ligne=fichier.readLine()) != null) {
			list.add(ligne);
		}
		
		for(int i = 0; i < list.size(); i++) {
			String[] spliter = list.get(i).split(":");
			if(profession.equals(spliter[0])) {
				nbDocs = Integer.parseInt(spliter[1]);
			}
		}
		
		fichier.close();
		
		return nbDocs;
	}
	
	public void decrementingPage(String profession, int nb) throws Exception{
		List<String> lines = new ArrayList<String>();
		String line = null;
		File f1 = new File(configuration);
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            if (line.contains(profession)) {
            	String[] spliter = line.split(":");
            	int newValue = Integer.parseInt(spliter[1]) - nb;
                line = line.replace(spliter[1], String.valueOf(newValue));
            }
            lines.add(line + "\n");
        }
        fr.close();
        br.close();

        FileWriter fw = new FileWriter(f1);
        BufferedWriter out = new BufferedWriter(fw);
        for(String s : lines) {
             out.write(s);
        }
        out.flush();
        out.close();
	}
	
	public void printDocuments(String profession) throws Exception{
		try {
			int nbDocs = getNumberDocuments(profession);
			//Connexion au serveur
			Socket s = new Socket("localhost", 5555);
			
			//IO
			InputStream is = s.getInputStream();
			OutputStream os = s.getOutputStream();
			
			//envoi d'un nombre au serveur
			Scanner clavier = new Scanner(System.in);
			System.out.print("How many documents do you want to print : ");
			int nb = clavier.nextInt();
			if(nb <= nbDocs) {
				os.write(nb);
				//decrementer le nb de pages en fonction du nb d'impression
				decrementingPage(profession, nb);
			}else {
				try {
					System.out.println("You don't have paper anymore for printing");
					s.close();
				}catch(Exception e1) {
					
				}
			}
			
			//lecture de la reponse du serveur
			int resp = is.read();
			if(resp == 1)
				System.out.println("Success");
			else
				System.out.println("ERROR");
			
			//fermeture socket
			s.close();
		} catch (Exception e) {
			//e.printStackTrace();
		} 
	}
	
	public void simulateAt() {
		System.out.println("1 - PC");
		System.out.println("2 - Door");
		System.out.println("3 - Server");
		System.out.println("What do you want to simulate ?");
		int choice = keyboard.nextInt();
		switch(choice) {
			case 1:
				System.out.println("Please choose a simulation time");
				String time = keyboard.next();
				if(time.equals("7:45am")) {
					System.out.println("Opening sessions");
				}else if(time.equals("4:30pm")) {
					System.out.println("Closing sessions");
				}else {
					System.out.println("Stand by");
				}
				
				break;
			case 2:
				System.out.println("Please choose a simulation time");
				System.out.print("Hour : ");
				int hour = keyboard.nextInt();
				System.out.println("Minute");
				int minute = keyboard.nextInt();
				if(minute >= 45 && minute <= 59 && hour == 7) {
					System.out.println("The door authorized opening");
				}else if(minute >= 30 && minute <= 45 && hour == 4) {
					System.out.println("The door are authorized opening");
				}else {
					System.out.println("The door are closed and cannot open it");
				}
				break;
			case 3:
				System.out.println("Please choose a simulation time");
				String time2 = keyboard.next();
				if(time2.equals("4:30pm")) {
					System.out.println("Backup of all PCs");
				}else {
					System.out.println("Server is turned off");
				}
				break;
			default:
				break;
		}
	}
	
	public void messaging() throws SQLException {
		System.out.println("Welcome to the Cap2019 Messaging");
		System.out.println("1 - Check my email");
		System.out.println("2 - Send a message");
		int mess = keyboard.nextInt();
		if(mess == 2) {
			System.out.println("From " +  name + "@cap2019.com");
			System.out.print("To : ");
			String dest = keyboard.next();
			String[] destSplit = dest.split("@");
	
			//on verifie d'abord si l'uilisateur existe
			PreparedStatement prepStmtObj = conn.prepareStatement("SELECT * FROM employees WHERE emp_name = ?");
			prepStmtObj.setString(1, destSplit[0]);
			ResultSet resultSetObj = prepStmtObj.executeQuery();
	        
	        while(resultSetObj.next()) {
	        	System.out.print("Object : ");
	    		String object = keyboard.nextLine();
	    		keyboard.nextLine();
	    		System.out.println("Message : ");
	    		String message = keyboard.nextLine();
	    		
	    		PreparedStatement prep3 = conn.prepareStatement("INSERT INTO messaging VALUES(NULL, ?, ?, ?, ?, NOW())");
				prep3.setString(1, name);
				prep3.setString(2, destSplit[0]);
				prep3.setString(3, object);
				prep3.setString(4, message);
				prep3.executeUpdate();
				
				System.out.println("Your message has been successfully sent");
	        } 
		}else if(mess == 1) {
			PreparedStatement prepStmtObj = conn.prepareStatement("SELECT * FROM messaging WHERE name_dest = ? ORDER BY date DESC");
			prepStmtObj.setString(1, name);
			ResultSet resultSetObj = prepStmtObj.executeQuery();
	        
	        while(resultSetObj.next()) {
	        	System.out.println("From " + resultSetObj.getString("name_exp") + "@cap2019.com");
	        	System.out.println("The " + resultSetObj.getString("date"));
	        	System.out.println("Object : " +  resultSetObj.getString("object"));
	        	System.out.println("Message : " + resultSetObj.getString("message"));
	        	System.out.println("--------------------------------");
	        }
		}
	}
}
