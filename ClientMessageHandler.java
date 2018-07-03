package pa2_client;



import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/** 
 * ClientMessageHandler.java 
 * 
 * Version: 
 *     $1.0$ 
 * 
 * Revisions: 
 *     $initial$ 
 *     
 * This program implements client side SOAP message handler. It sends the ID of client 
 * and operation number to server.
 *
 * @author	Vaibhavi Awghate
 */
public class ClientMessageHandler implements SOAPHandler<SOAPMessageContext> {
    
    public boolean handleMessage(SOAPMessageContext messageContext) {
        //SOAPMessage msg = messageContext.getMessage();
        boolean outboundProperty = (boolean)messageContext.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if(outboundProperty){
            SOAPMessage message = messageContext.getMessage();
            try{
                SOAPEnvelope envelope = messageContext.getMessage().getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();
                if(header == null){
                    header = envelope.addHeader();
                }
                SOAPElement usernameToken = header.addChildElement("UsernameToken","wsse","http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd");
                SOAPElement username = usernameToken.addChildElement("Username", "wsse");
                username.addTextNode("myUserName");
                //client id being sent to server
                SOAPElement client_id = usernameToken.addChildElement("CID", "wsse");
                client_id.addTextNode(PA2_Client.client_id);
                //operation number being sent to server
                SOAPElement operation_no = usernameToken.addChildElement("opno", "wsse");
                operation_no.addTextNode(PA2_Client.operation_no);
                
            } catch (SOAPException ex) {
                Logger.getLogger(ClientMessageHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
               SOAPMessage message = messageContext.getMessage();
        }
        return outboundProperty;
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