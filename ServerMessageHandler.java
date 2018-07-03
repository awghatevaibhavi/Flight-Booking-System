/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pa2_server;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.*;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import org.w3c.dom.NodeList;

/** 
 * ServerMessageHandler.java 
 * 
 * Version: 
 *     $1.0$ 
 * 
 * Revisions: 
 *     $initial$ 
 *     
 * This program implements SOAP message handler. It checks the sequence of operations 
 * being invoked by client. It throws error if the second operation is invoked first by client
 * instead of first operation.
 *
 * @author	Vaibhavi Awghate
 */
public class ServerMessageHandler implements SOAPHandler<SOAPMessageContext> {
    static ArrayList <String> record = new ArrayList<String>();
    public boolean handleMessage(SOAPMessageContext messageContext) {
        String outProperty = SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY;
        boolean outgoing = (boolean)messageContext.get(outProperty);
        SOAPMessage msg = messageContext.getMessage();
        String username = "";
        String client_id = "";
        String operation_no = "";
        
        try{
            if(outgoing){
                msg.writeTo(new FileOutputStream("/Users/Vaibhavi/responseMessage.txt"));
            }
            else{
                SOAPHeader header = msg.getSOAPHeader();
                Iterator it = header.examineAllHeaderElements();
                while(it.hasNext()){
                    SOAPHeaderElement e = (SOAPHeaderElement)it.next();
                    NodeList nl = e.getElementsByTagName("wsse:Username");
                    for(int i = 0; i < nl.getLength();i++){
                        username+=nl.item(i).getTextContent();
                    }
                    //client id sent by client
                    nl = e.getElementsByTagName("wsse:CID");
                    for(int i = 0; i < nl.getLength();i++){
                        client_id+=nl.item(i).getTextContent();
                    }
                    //operation number invoked by client
                    nl = e.getElementsByTagName("wsse:opno");
                    for(int i = 0; i < nl.getLength();i++){
                        operation_no+=nl.item(i).getTextContent();
                    }
                }
                
                //checking the sequence of operations
                if (client_id.equals("C1")){
                    //checking if second operation is invoked first
                    if(operation_no.equals("2")){
                        if(ServerMessageHandler.record.isEmpty()){
                            throw new SOAPException();
                        }
                    }
                    /*if first operation is invoked first, it is added to arraylist to
                    * keep track of operations invoked
                    */
                    if(operation_no.equals("1"))
                        ServerMessageHandler.record.add(operation_no);
                }
                //FileOutputStream f  = 
                FileOutputStream f = new FileOutputStream("/Users/Vaibhavi/username.txt");
                byte data[]=username.getBytes();
                f.write(data);
                msg.writeTo(new FileOutputStream("/Users/Vaibhavi/inputMessage.txt"));
            }
                
            
        } catch (SOAPException ex) {
            throw new RuntimeException();
        } catch (IOException ex) {
            throw new RuntimeException();
        }
        return true;
    }
    
    public Set<QName> getHeaders() {
        return Collections.EMPTY_SET;
    }
    
    public boolean handleFault(SOAPMessageContext messageContext) {
        return true;
    }
    
    public void close(MessageContext context) {
    }
    
}
