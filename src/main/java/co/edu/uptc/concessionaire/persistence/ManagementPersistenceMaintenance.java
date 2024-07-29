package co.edu.uptc.concessionaire.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import co.edu.uptc.concessionaire.constants.CommonConstants;
import co.edu.uptc.concessionaire.model.Maintenance;

public class ManagementPersistenceMaintenance extends FilePlain {
	private Map <String, Maintenance> maintenanceMap;
	
	public void dumpFilePlain(String path) {
		List<Object> maintenances = this.maintenanceMap.values().stream().collect(Collectors.toList());
		List<String> records = new ArrayList<>();
		 for(Object maintenance : maintenances){
			 StringBuilder contentMaintenance = new StringBuilder();
			 contentMaintenance.append(((Maintenance) maintenance).getPlate()).append(CommonConstants.SEMI_COLON);
			 contentMaintenance.append(((Maintenance) maintenance).getBrand()).append(CommonConstants.SEMI_COLON);
			 contentMaintenance.append(((Maintenance) maintenance).getDate()).append(CommonConstants.SEMI_COLON);
			 
			 records.add(contentMaintenance.toString());
		 }
		 this.writer(path, records);
	}
	
	public void loadFilePlain(String path) {
		List<String> contentInLine = this.reader(path);
		//System.out.println(contentInLine.size());
		for(String row: contentInLine) {
			//System.out.println(row);
			StringTokenizer tokens = new StringTokenizer(row, CommonConstants.SEMI_COLON);
			while(tokens.hasMoreElements()){
				String plate = tokens.nextToken();
				String brand = tokens.nextToken();
				String date = tokens.nextToken();
				maintenanceMap.put(plate,new Maintenance(plate,brand,date));
			}
		}
	}

	public Map<String, Maintenance> getMaintenanceMap() {
		return maintenanceMap;
	}

	public void setMaintenanceMap(Map<String, Maintenance> maintenanceMap) {
		this.maintenanceMap = maintenanceMap;
	}
	
}
