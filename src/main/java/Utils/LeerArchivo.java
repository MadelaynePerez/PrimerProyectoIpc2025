/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author DELL
 */
public class LeerArchivo {

    public ArrayList <String> Leer(HttpServletRequest request) throws IOException, ServletException {
        // processRequest(request, response);
        Part filePart = request.getPart("file");
        ArrayList<String>lineas= new ArrayList<String>();
        // Asegurarse de que se ha subido un archivo
        if (filePart == null || filePart.getSize() == 0) {
           
            return lineas;
        }
        // Leer el archivo línea por línea sin guardarlo en el servidor
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(filePart.getInputStream()))) {
            String line;
           
            while ((line = reader.readLine()) != null) {       
                lineas.add(line);
            }
            
        } catch (Exception e) {
         
        }
        
        return lineas;
    }
}
