package co.edu.uptc.concessionaire.persistence;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import co.edu.uptc.concessionaire.constants.CommonConstants;



public class FilePlain {
	
	public FilePlain() {
	}
	
	/**
	 * <b>Descripción: </b> Método encargado de leer el archivo agregando el carácter de salto de línea
	 * @author jcharris
	*/
	public String readFile(String rutaNombre) {
		StringBuilder contenido = new StringBuilder();
        try {
        	InputStream inputStream = getClass().getResourceAsStream(rutaNombre);
        	System.out.println(inputStream);
        	if(inputStream == null) {
        		System.out.println("No encontró el archivo");
        		return "";
        	}
        	InputStreamReader input =  new InputStreamReader(inputStream);
            BufferedReader br = new BufferedReader(input);
            String linea;
            while ((linea = br.readLine()) != null) {
                contenido.append(linea).append(CommonConstants.LINE_BREAK);
            }
            br.close();
        } catch (IOException e) {
        	e.printStackTrace();
            System.out.println("Se presentó un error al leer el archivo específicado");
        }
        return contenido.toString();
	}
	/**
	 * <b>Descripción: </b> Método encargado de escribir en el archivo sobreescribiendo el contennido
	 * @author jcharris
	*/
	public void writeFile(String nombreArchivo, String content) {
		String rutaAbsoluta = 
				"C:/Users/USER/Documents/eclipse-Workspace/Concessionaire/src/main/resources/data/" + nombreArchivo;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaAbsoluta))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * <b>Descripción:</b> Método encargado de la lectura y organización de las líneas encontradas en el fichero<br>
	 * @author jcharris
	 */
	protected List<String> reader(String rutaNombre){
		List<String> output = new ArrayList<>();
		String rutaAbsoluta = 
				"C:/Users/USER/Documents/eclipse-Workspace/Concessionaire/src/main/resources/data/" + rutaNombre;
		StringTokenizer tokens = new StringTokenizer(this.readFile(rutaNombre), 
				CommonConstants.LINE_BREAK);
		while (tokens.hasMoreElements()) {
			output.add(tokens.nextToken());	
		}
		return output;
	}
	
	/**
	 * <b>Descripción:</b> Método encargado de la escritura en el fichero<br>
	 * @author jcharris
	 */
	protected void writer(String rutaNombre, List<String> file){
		StringBuilder strContent = new StringBuilder();
		for(String record: file) {
			strContent.append(record).append(CommonConstants.LINE_BREAK);
		}
		writeFile(rutaNombre, strContent.toString());
	}
}

