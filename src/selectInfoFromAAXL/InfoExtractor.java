package selectInfoFromAAXL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
*@author Li Yaonan
*@date 2017/10/10
*@description This program is to select info from given systemModel file describing 
*the components and the connection component
*/
public class InfoExtractor{
    String softwareFile;
    String hardwareFile;
    String destinationDirectory;
    Document doc;
//    software and hardware are independent files
//    srcFile should be changed
    public InfoExtractor(String hardwareFile, String softwareFile, String destinationDirectory){
    	this.hardwareFile = hardwareFile;
    	this.softwareFile = softwareFile;
        this.destinationDirectory = destinationDirectory;
    }
// <componentInstance name="politpole1" category="device">
//   </componentInstance>
    public void infoExtract(){
        /**phase 1: to extract the component info from srcFile
        *    use SAXReader to extract info
        *  phase 2: to write the info into the desinationDirectory via SAXReader
        */
		String outputDirectory = this.destinationDirectory;
		File softwareFile = new File(this.softwareFile);
		File hardwareFile = new File(this.hardwareFile);
		
		FileInputStream softwareStream = null;
		FileInputStream hardwareStream = null;
		File componentFile = new File(outputDirectory+"/component.info");
		File connectionFile = new File(outputDirectory+"/connection.info");
		FileOutputStream componentOs = null;
		FileOutputStream connectionOs = null;
		
		try{
			componentOs = new FileOutputStream(componentFile);
			connectionOs = new FileOutputStream(connectionFile);
			PrintWriter componentPw = new PrintWriter(componentOs);
			PrintWriter connectionPw = new PrintWriter(connectionOs);
			
			softwareStream = new FileInputStream(softwareFile);
			hardwareStream = new FileInputStream(hardwareFile);
			//========================================hardware println
			SAXReader hardwareReader = new SAXReader();
			Document hardwareDoc = hardwareReader.read(softwareStream);
			org.dom4j.Element hardRootElement = hardwareDoc.getRootElement();
			List<org.dom4j.Element> hardElementsLikeComponents = hardRootElement.elements();
			for (org.dom4j.Element itemLikeComponents : hardElementsLikeComponents) {
				if ("components".equals(itemLikeComponents.getName()) ) {
					List<org.dom4j.Element> elementsLikeComponent = itemLikeComponents.elements();
					for (org.dom4j.Element itemLikeComponent : elementsLikeComponent) {
						String cpntName = itemLikeComponent.attribute("name").getValue();
						componentPw.println(cpntName);
					}
				}else if ("connections".equals(itemLikeComponents.getName()) ) {
					List<org.dom4j.Element> elementsLikeConnection = itemLikeComponents.elements();
					for (org.dom4j.Element itemLikeConn : elementsLikeConnection) {
						String cnnName = itemLikeConn.attribute("name").getValue();
						connectionPw.println(cnnName);
					}
				}
			}
			componentOs.flush();
			componentPw.flush();
			connectionOs.flush();
			connectionPw.flush();
			
			
			
			
			//========================================software println
			SAXReader softwareReader = new SAXReader();
			Document softwareDoc = softwareReader.read(softwareStream);
			org.dom4j.Element softRootElement = softwareDoc.getRootElement();
			List<org.dom4j.Element> softElementsLikeComponents = softRootElement.elements();
			for (org.dom4j.Element itemLikeComponents : softElementsLikeComponents) {
				if ("components".equals(itemLikeComponents.getName()) ) {
					List<org.dom4j.Element> elementsLikeComponent = itemLikeComponents.elements();
					for (org.dom4j.Element itemLikeComponent : elementsLikeComponent) {
						String cpntName = itemLikeComponent.attribute("name").getValue();
						componentPw.println(cpntName);
					}
				}else if ("connections".equals(itemLikeComponents.getName()) ) {
					List<org.dom4j.Element> elementsLikeConnection = itemLikeComponents.elements();
					for (org.dom4j.Element itemLikeConn : elementsLikeConnection) {
						String cnnName = itemLikeConn.attribute("name").getValue();
						connectionPw.println(cnnName);
					}
				}
			}
			componentOs.flush();
			componentPw.flush();
			connectionOs.flush();
			connectionPw.flush();
			
			
			
		}catch(IOException ioe){
			ioe.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			if(softwareStream != null && componentOs!=null && connectionOs!=null){
				try {
					softwareStream.close();
					hardwareStream.close();
					componentOs.close();
					connectionOs.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
    	

   
    
}