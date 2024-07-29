package co.edu.uptc.concessionaire.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import co.edu.uptc.concessionaire.model.User;

public class ManagementPersistenceUser {

	
	private ArrayList<User> users = new ArrayList<User>();
	
	public void dumpFileSerializate(String path) {
		try (FileOutputStream fileOut= new FileOutputStream("C:/Users/USER/Documents/eclipse-Workspace/Concessionaire/src/main/resources/data/" + path);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)){
			out.writeObject(this.users);
		}
		catch (IOException i) {
			i.printStackTrace();
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void loadFileSerializate(String path){
		try (FileInputStream fileIn= new FileInputStream("C:/Users/USER/Documents/eclipse-Workspace/Concessionaire/src/main/resources/data/" + path);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			this.users=(ArrayList<User>) in.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}

	public ArrayList<User> getUsers() {
		
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}
