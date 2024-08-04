package co.edu.uptc.concessionaire.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import co.edu.uptc.concessionaire.constants.CommonConstants;
import co.edu.uptc.concessionaire.model.Motorcycle;


public class ManagementPersistenceMotorcycle extends FilePlain{
	
	private Map<String, Motorcycle> MapMotorcycle = new HashMap<String, Motorcycle>();
	
	

	public Map<String, Motorcycle> getMapMotorcycle() {
		return MapMotorcycle;
	}

	public void setMapMotorcycle(Map<String, Motorcycle> mapMotorcycle) {
		MapMotorcycle = mapMotorcycle;
	}

	public void dumpFilePlain(String path) {
		List<Object> motorcycles = this.MapMotorcycle.values().stream().collect(Collectors.toList());
		//List<Motorcycle> motorcycles = this.MapMotorcycle.values().stream().collect(Collectors.toList());
	
		List<String> records = new ArrayList<>();
		 for(Object motor : motorcycles){
			 StringBuilder contentMotorcycle = new StringBuilder();
			 contentMotorcycle.append(((Motorcycle) motor).getBrand()).append(CommonConstants.SEMI_COLON);
			 contentMotorcycle.append(((Motorcycle) motor).getCylinder()).append(CommonConstants.SEMI_COLON);
			 contentMotorcycle.append(((Motorcycle) motor).getPlate()).append(CommonConstants.SEMI_COLON);
			 contentMotorcycle.append(((Motorcycle) motor).getModel()).append(CommonConstants.SEMI_COLON);
			 contentMotorcycle.append(((Motorcycle) motor).getColor()).append(CommonConstants.SEMI_COLON);
			 contentMotorcycle.append(((Motorcycle) motor).getOwner()).append(CommonConstants.SEMI_COLON);
			 records.add(contentMotorcycle.toString());
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
				String brand = tokens.nextToken();
				String cylinder = tokens.nextToken();
				String plate = tokens.nextToken();
				String model = tokens.nextToken();
				String color = tokens.nextToken();
				String owner = tokens.nextToken();
				MapMotorcycle.put(plate,new Motorcycle(brand,cylinder,plate,model,color,owner));
			}
		}
	}
	
}
