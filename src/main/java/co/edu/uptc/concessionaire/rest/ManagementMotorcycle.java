package co.edu.uptc.concessionaire.rest;


import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import co.edu.uptc.concessionaire.model.Motorcycle;
import co.edu.uptc.concessionaire.persistence.ManagementPersistenceMotorcycle;




	
	@Path("/ManagementMotorcycle")
	public class ManagementMotorcycle {
		public static ManagementPersistenceMotorcycle managementPersistenceMotorcycle = new ManagementPersistenceMotorcycle();
		
		static {
			managementPersistenceMotorcycle.loadFilePlain("/data/concessionaire.txt");
		}
		
		@GET
		@Path("/getMotorcycles")
		@Produces( { MediaType.APPLICATION_JSON } )
		public List<Object> getMotorcycles(){
			return managementPersistenceMotorcycle.getMapMotorcycle().values().stream().collect(Collectors.toList());
		}
		
		@GET
		@Path("/getMotorcycleByPlate")
		@Produces( { MediaType.APPLICATION_JSON } )
		public Motorcycle getMotorcycleByPlate(@QueryParam("plate") String plate){
			for(Object motorcycle: managementPersistenceMotorcycle.getMapMotorcycle().values().stream().collect(Collectors.toList())) {
				if(((Motorcycle) motorcycle).getPlate().equals(plate)) {
					return (Motorcycle) motorcycle;
				}
			}
			return null;
		}
		
		
		@POST
		@Path("/createMotorcycle")
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON })
		public Motorcycle createMotorcycle(Motorcycle motorcycle) {
			if(managementPersistenceMotorcycle.getMapMotorcycle().put(motorcycle.getPlate(), motorcycle) != null) {
				managementPersistenceMotorcycle.dumpFilePlain("concessionaire.txt");
				return motorcycle;
			}
			return null;
		}
		
		@PUT
		@Path("/updateMotorcycle")
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON })
		public Motorcycle updateBook(Motorcycle motorcycles) {
			for(Object motorcycle: managementPersistenceMotorcycle.getMapMotorcycle().values().stream().collect(Collectors.toList())) {
				if(((Motorcycle) motorcycle).getPlate().equals(motorcycles.getPlate())) {
					((Motorcycle) motorcycle).setPlate(motorcycles.getPlate());
					((Motorcycle) motorcycle).setBrand(motorcycles.getBrand());
					((Motorcycle) motorcycle).setCylinder(motorcycles.getCylinder());
					((Motorcycle) motorcycle).setModel(motorcycles.getModel());
					((Motorcycle) motorcycle).setOwner(motorcycles.getOwner());
					((Motorcycle) motorcycle).setColor(motorcycles.getColor());
					
					managementPersistenceMotorcycle.dumpFilePlain("concessionaire.txt");
					return motorcycles;
				}
			}
			return null;
		}
		
		@PUT
		@Path("/updateMotorcycleAttribute")
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON })
		public Motorcycle updateBookAttribute(Motorcycle motorcycles) {
			for(Object motor: managementPersistenceMotorcycle.getMapMotorcycle().values().stream().collect(Collectors.toList())) {
				if(((Motorcycle) motor).getPlate().equals(motorcycles.getPlate())) {
					if(!Objects.isNull(motorcycles.getPlate())) {
						((Motorcycle) motor).setPlate(motorcycles.getPlate());
					}
					
					if(!Objects.isNull(motorcycles.getBrand())) {
						((Motorcycle) motor).setBrand(motorcycles.getBrand());
					}
					
					if(!Objects.isNull(motorcycles.getCylinder())) {
						((Motorcycle) motor).setCylinder(motorcycles.getCylinder());
					}
					
					if(!Objects.isNull(motorcycles.getOwner())) {
						((Motorcycle) motor).setOwner(motorcycles.getOwner());
					}
					
					if(!Objects.isNull(motorcycles.getModel())) {
						((Motorcycle) motor).setModel(motorcycles.getModel());
					}
					
					if(!Objects.isNull(motorcycles.getColor())) {
						((Motorcycle) motor).setColor(motorcycles.getColor());
					}
					managementPersistenceMotorcycle.dumpFilePlain("concessionaire.txt");
					return motorcycles;
				}
			}
			return null;
		}
		
		
		@DELETE
		@Path("/deleteMotorcycles")
		@Produces({ MediaType.APPLICATION_JSON })
		@Consumes({ MediaType.APPLICATION_JSON })
		public Motorcycle deleteMotorcycle(@QueryParam("plate") String plate) {
			Motorcycle motor = this.getMotorcycleByPlate(plate);
			if(motor != null) {
				managementPersistenceMotorcycle.getMapMotorcycle().remove(motor.getPlate());
				System.out.println(managementPersistenceMotorcycle.getMapMotorcycle());
				managementPersistenceMotorcycle.dumpFilePlain("concessionaire.txt");
			}
			return motor;
		}
		
		
	}

