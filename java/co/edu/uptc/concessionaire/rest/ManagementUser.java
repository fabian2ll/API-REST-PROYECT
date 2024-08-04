package co.edu.uptc.concessionaire.rest;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	@Path("/getUsers")
	@Produces( { MediaType.APPLICATION_JSON } )
	public List<User> getMaintenances(){
		return managementPersistenceUser.getUsers();
	}
	
	@GET
	@Path("/validateUser")
	@Produces({MediaType.APPLICATION_JSON})
	public Boolean validateUser(@QueryParam("name") String nameUser,
			@QueryParam("password") String password) {
		managementPersistenceUser.setUsers(new ArrayList<User>());
		managementPersistenceUser.loadFileSerializate("users.ser");
		System.out.println(managementPersistenceUser.getUsers());
		System.out.println(nameUser+ " "+ password);
		User userDTO = new User(nameUser, password);
	
		User usuarioEncontrado = null;
		try {
			usuarioEncontrado = managementListUtils.findObjectBinary(userDTO, "name", "password");
		} catch (NoSuchFieldException | IllegalAccessException e) {
			e.printStackTrace();
		}
		System.out.println(usuarioEncontrado);
		return !Objects.isNull(usuarioEncontrado);	}
	
	@POST
	@Path("/createUser")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public User createMaintenance(User user) {
		System.out.println(user);
		managementPersistenceUser.setUsers(new ArrayList<User>());
		managementPersistenceUser.loadFileSerializate("users.ser");
		if(managementPersistenceUser.getUsers().add(user) != false ) {
			System.out.println("sube al archivo");
			
			managementPersistenceUser.dumpFileSerializate("users.ser");
			managementListUtils.setListObjects(managementPersistenceUser.getUsers());

			return user;
		}
		return null;
	}
	
	
	@DELETE
	@Path("/deleteUser")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public User deleteUser(@QueryParam("name") String plate) {
		
		for(User user : managementPersistenceUser.getUsers()) {
			if(user.getName().equals(plate)) {
				managementPersistenceUser.getUsers().remove(user);
				managementPersistenceUser.dumpFileSerializate("users.ser");	
				managementListUtils.setListObjects(managementPersistenceUser.getUsers());
				return user;
			}
		}
		
		
		
		return null;
	}
	
	@PUT
	@Path("/updateUserAttribute")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public User updateBookAttribute(User bookDTO) {
		for(User book: managementPersistenceUser.getUsers()) {
			if(book.getName().equals(bookDTO.getName())) {
				if(!Objects.isNull(bookDTO.getName())) {
					book.setName(bookDTO.getName());
				}
				
				if(!Objects.isNull(bookDTO.getName())) {
					book.setName(bookDTO.getName());
				}
				
				if(!Objects.isNull(bookDTO.getPassword())) {
					book.setPassword(bookDTO.getPassword());
				}
				
				
				managementPersistenceUser.dumpFileSerializate("users.ser");
				return bookDTO;
			}
		}
		return null;
	}
}
