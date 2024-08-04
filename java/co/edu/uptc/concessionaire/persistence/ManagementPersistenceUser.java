package co.edu.uptc.concessionaire.persistence;

import java.io.EOFException;
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
	        System.err.println("Archivo no encontrado: " + e.getMessage());
	    } catch (EOFException e) {
	        System.err.println("Fin inesperado del archivo: " + e.getMessage());
	    } catch (IOException e) {
	        System.err.println("Error de entrada/salida: " + e.getMessage());
	    } catch (ClassNotFoundException e) {
	        System.err.println("Clase no encontrada: " + e.getMessage());
	    }
				
		
	}

	public ArrayList<User> getUsers() {
		
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}
}
