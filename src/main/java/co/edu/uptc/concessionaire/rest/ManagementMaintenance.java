package co.edu.uptc.concessionaire.rest;

import java.util.ArrayList;
import java.util.List;
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

import co.edu.uptc.concessionaire.model.Maintenance;
import co.edu.uptc.concessionaire.model.Motorcycle;
import co.edu.uptc.concessionaire.model.User;
import co.edu.uptc.concessionaire.persistence.ManagementPersistenceMaintenance;
import co.edu.uptc.concessionaire.persistence.ManagementPersistenceMotorcycle;
import co.edu.uptc.concessionaire.utils.ManagementListUtils;


@Path("/ManagementMaintenance")
public class ManagementMaintenance {
	public static ManagementPersistenceMaintenance managementPersistenceMaintenance = new ManagementPersistenceMaintenance();
	public static ManagementPersistenceMotorcycle managementMotor= new ManagementPersistenceMotorcycle();
	static {
		managementPersistenceMaintenance.loadFilePlain("/data/maintenance.txt");
		managementMotor.loadFilePlain("/data/concessionaire.txt");
		
	}
	
	@GET
	@Path("/getMaintenances")
	@Produces( { MediaType.APPLICATION_JSON } )
	public List<Object> getMaintenances(){
		return managementPersistenceMaintenance.getMaintenanceMap().values().stream().collect(Collectors.toList());
	}
	
	
	@GET
	@Path("/validateMaintenance")
	@Produces({MediaType.APPLICATION_JSON})
	public Boolean validateUser(@QueryParam("plate") String plate) {
		System.out.println(plate);
		//managementMotor.loadFilePlain("concessionaire.txt");
		System.out.println(managementMotor.getMapMotorcycle());
		if (managementMotor.getMapMotorcycle().containsKey(plate)) {
				return true;
				
			}
		
		return false;
	}
	
	@GET
	@Path("/getMaintenanceByPlate")
	@Produces( { MediaType.APPLICATION_JSON } )
	public Maintenance getMaintenanceByPlate(@QueryParam("plate") String plate){
		for(Object maintenance: managementPersistenceMaintenance.getMaintenanceMap().values().stream().collect(Collectors.toList())) {
			if(((Maintenance) maintenance).getPlate().equals(plate)) {
				return (Maintenance) maintenance;
			}
		}
		return null;
	}
	
	
	@POST
	@Path("/createMaintenance")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Maintenance createMaintenance(Maintenance maintenance) {
		if(managementPersistenceMaintenance.getMaintenanceMap().put(maintenance.getPlate(), maintenance) != null) {
			managementPersistenceMaintenance.dumpFilePlain("maintenance.txt");
			return maintenance;
		}
		return null;
	}
	
	@PUT
	@Path("/updateMaintenance")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Maintenance updateBook(Maintenance maintenances) {
		for(Object maintenance: managementPersistenceMaintenance.getMaintenanceMap().values().stream().collect(Collectors.toList())) {
			if(((Maintenance) maintenance).getPlate().equals(maintenances.getPlate())) {
				((Maintenance) maintenance).setPlate(maintenances.getPlate());
				((Maintenance) maintenance).setBrand(maintenances.getBrand());
				((Maintenance) maintenance).setDate(maintenances.getDate());
			
				
				managementPersistenceMaintenance.dumpFilePlain("maintenance.txt");
				return maintenances;
			}
		}
		return null;
	}
	
	@PUT
	@Path("/updateMaintenanceAttribute")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Maintenance updateMaintenanceAttribute(Maintenance maintenances) {
		for(Object maintenance: managementPersistenceMaintenance.getMaintenanceMap().values().stream().collect(Collectors.toList())) {
			if(((Maintenance) maintenance).getPlate().equals(maintenances.getPlate())) {
				if(!Objects.isNull(maintenances.getPlate())) {
					((Maintenance) maintenance).setPlate(maintenances.getPlate());
				}
				
				if(!Objects.isNull(maintenances.getBrand())) {
					((Maintenance) maintenance).setBrand(maintenances.getBrand());
				}
				
				if(!Objects.isNull(maintenances.getDate())) {
					((Maintenance) maintenance).setDate(maintenances.getDate());
				}
				
				
				managementPersistenceMaintenance.dumpFilePlain("maintenance.txt");
				return maintenances;
			}
		}
		return null;
	}
	
	
	@DELETE
	@Path("/deleteMaintenances")
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	public Maintenance deleteMaintenance(@QueryParam("plate") String plate) {
		Maintenance maintenance = this.getMaintenanceByPlate(plate);
		if(maintenance != null) {
			managementPersistenceMaintenance.getMaintenanceMap().remove(maintenance.getPlate());
			System.out.println(managementPersistenceMaintenance.getMaintenanceMap());
			managementPersistenceMaintenance.dumpFilePlain("maintenance.txt");
		}
		return maintenance;
	}
	
	
}

