package co.edu.uptc.concessionaire.rest;

import java.util.Objects;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


import co.edu.uptc.concessionaire.model.User;
import co.edu.uptc.concessionaire.persistence.ManagementPersistenceUser;
import co.edu.uptc.concessionaire.utils.ManagementListUtils;




@Path("/ManagementUser")
public class ManagementUser {
	
	public static ManagementPersistenceUser managementPersistenceUser = new ManagementPersistenceUser();
	public static ManagementListUtils<User> managementListUtils;

	
	static {
		
		managementPersistenceUser.loadFileSerializate("users.ser");
		
		
		managementListUtils = new ManagementListUtils<User>(
				managementPersistenceUser.getUsers());
		try {
			/* Asignamos el nombre del atributo por los atributos que deseamos ordenar */
			managementListUtils.sortList("name", "password");
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			System.out.println("No se encontró el nombre del atributo en la clase");
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/validateUser")
	@Produces({MediaType.APPLICATION_JSON})
	public Boolean validateUser(@QueryParam("name") String nameUser,
			@QueryParam("password") String password) {
		User userDTO = new User(nameUser, password);
		User usuarioEncontrado = null;
		try {
			usuarioEncontrado = managementListUtils.findObjectBinary(userDTO, "name", "password");
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return !Objects.isNull(usuarioEncontrado);
	}
	
	@POST
	@Path("/createUser")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public User createMaintenance(User user) {
		if(managementPersistenceUser.getUsers().add(user) != false) {
			managementPersistenceUser.dumpFileSerializate("users.ser");
			return user;
		}
		return null;
	}
	
	
	@DELETE
	@Path("/deleteUser")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public User deleteUser(@QueryParam("name") String plate) {
		
		User u = new User();
		for (User user : managementPersistenceUser.getUsers()) {
			if (user.getName().equals(plate)) {
				u= user;
			}
		}
		if(u != null) {
			managementPersistenceUser.getUsers().remove(u);
			
			managementPersistenceUser.dumpFileSerializate("users.ser");
		}
		return u;
	}
}
