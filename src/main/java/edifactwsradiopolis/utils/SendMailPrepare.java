package edifactwsradiopolis.utils;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.sql.DataSource;

public class SendMailPrepare {
//	private DataSource dsConnect;
//	private ConexionDS conexionDS;	
	private String ds_cfdi=Utils.ds_cfdi;
	
//	public void enviaCorreoCFDI()
//	{
//	final String username="orodriguez@edifact.com.mx";
//        final String password="";
//        Properties prop=new Properties();
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.host", "mail.edifact.com.mx");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(prop,
//      new javax.mail.Authenticator() {
//        protected PasswordAuthentication getPasswordAuthentication() {
//            return new PasswordAuthentication(username, password);
//      }
//    });
//      try {
//             String body="Dear Renish Khunt Welcome";
//             String htmlBody = "<strong>This is an HTML Message</strong>";
//             String textBody = "This is a Text Message.";
//             Message message = new MimeMessage(session);
//             message.setFrom(new InternetAddress("orodriguez@edifact.com.mx"));
//             message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("orodriguez@edifact.com.mx"));
//             message.setSubject("Testing Subject");
//             MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
//             mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
//             mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
//             mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
//             mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
//             mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
//             CommandMap.setDefaultCommandMap(mc);
//             message.setText(htmlBody);
//             message.setContent(textBody, "text/html");
//             Transport.send(message);
//
//             System.out.println("Done");
//
//      	} catch (MessagingException e) {
//      		e.printStackTrace();
//      	}
//	}

	public void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachFiles)
            throws Exception {
        // sets SMTP server properties
//		System.out.println("TO SEND3 "+toAddress);
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
        properties.put("mail.smtp.ssl.trust", host);
        //properties.put("mail.debug", "true");
 //       System.out.println("Cargando sesion de correo");
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
  //      System.out.println("Paso autenticator");
        Session session = Session.getInstance(properties, auth);
     
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
     
 //       System.out.println("Agregando sesion");
  //      System.out.println("Usuario "+toAddress);
        /*String correos;
        String array[]=toAddress.split(";");
       
        for(int i=0; i<array.length; i++){
			 correos=array[i];
			 System.out.println("Email al que se esta tratando de enviar ->"+correos);
			 if(correos.isEmpty()){
				 System.out.println("El correo esta vacio");
				 
			 }else{*/
				 msg.setFrom(new InternetAddress(userName,"Greiner Assistec S.A. de C.V."));
	             
	             //InternetAddress[] toAddresses = { new InternetAddress(correos) };
				 //InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
				/* String[] valor = correo.split(";");
					String correos="";
					for(int i=0; i<valor.length; i++){
						if(!valor[i].equals("")){
							correos+=" "+valor[i].trim();
						}
					}*/
				 String recipient = toAddress;
				 String[] recipientList = recipient.split(";");
				 
				 InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
				 int counter = 0;
				 for (String recipiente : recipientList) {
					 if(!recipiente.equals("")){
						 recipientAddress[counter] = new InternetAddress(recipiente.trim());
					     counter++;
					 }
				     
				 }
//	             System.out.println(recipientAddress);
				 //msg.setRecipients(Message.RecipientType.TO, toAddresses);
	             msg.setRecipients(Message.RecipientType.TO, recipientAddress);
	             msg.setSubject(subject);
	             msg.setSentDate(new Date());
	             msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
	      
	             // creates message part
	             MimeBodyPart messageBodyPart = new MimeBodyPart();
	             messageBodyPart.setContent(message, "text/html");
//	             System.out.println("Body part cargada");
	             MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	             mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
	             mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
	             mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
	             mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
	             mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
	             CommandMap.setDefaultCommandMap(mc);
	      
	             // creates multi-part
	             Multipart multipart = new MimeMultipart();
	             multipart.addBodyPart(messageBodyPart);
//	             System.out.println("Multipart iniciado");
	             // adds attachments
	             if (attachFiles != null && attachFiles.length > 0) {
	                 for (String filePath : attachFiles) {
	                     MimeBodyPart attachPart = new MimeBodyPart();
//	                     System.out.println("Attach archivos seleccionados");
	                     try {
	                     	if(filePath!=null)
	                     	{
	                     		if(!filePath.trim().equals(""))
	                     		{                			
	                     			attachPart.attachFile(filePath);
	                     		}
	                     	}
	                         
	                     } catch (IOException ex) {
	                     	System.out.println("Error al agregar los archivos para el correo "+ex.getCause().getCause().getMessage());
	                     }
	      
	                     multipart.addBodyPart(attachPart);
	                 }
	             }
	      
	             // sets the multi-part as e-mail's content
//	             System.out.println("Agregando archivos al correo");
	             msg.setContent(multipart);
	//             System.out.println("Archivos agregados correo");
	             // sends the e-mail
//	             System.out.println("Asignando correo a transport");
	             Transport.send(msg);
	//             System.out.println("Correo enviado");
	             
	             
			 //}
            	
        //}
        
 
    }
	
	public void enviarPreparaCorreo(String uuid, String correo, String nomReceptor,String serie, String folio, String tipoC)throws Exception
	{
		//System.out.println(correo);
		String host = Utils.getParamValue("mail_host");//"mail.edifact.com.mx";
        String port = Utils.getParamValue("mail_port"); //"587";
        String mailFrom = Utils.getParamValue("mail_usuario");//"orodriguez@edifact.com.mx";
        String password = Utils.getParamValue("mail_password");//"";
 //       System.out.println("TO SEND1 "+correo);
        // message info
        String tipoD="";
            if (tipoC.equalsIgnoreCase("P")) {
                tipoD="Pago";
            } else {
                tipoD="Factura";
            }
        
        String mailTo = correo;
        /*String subject = "Ha recibido un CFDI 3.2 de \"Sea Cargo Logistics\"";
        String message = "<html xmlns='http://www.w3.org/1999/xhtml'>"+
        				 "<head>"+
        				 "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
        				 "<title>EDIFACT MX Envio de correos automaticos</title>"+
        				 "<style type='text/css'>"+
        				 ".style3 {font-family: Arial, Helvetica, sans-serif}"+
        				 ".style4 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; }"+
        				 ".style5 {font-family: Arial, Helvetica, sans-serif; color: #006699;font-weight: bold;}"+
        				 ".style6 {font-family: Arial, Helvetica, sans-serif; font-size: 14px; }"+
        				 ".style7 {font-family: Arial, Helvetica, sans-serif; font-size: 12px}"+
        				 ".style8 {font-family: Arial, Helvetica, sans-serif; font-size: 10px}"+
        				 "</style>"+
        				 "</head>"+
        				 "<body>"+
        				 "<p class='style4'>Estimado Cliente: </p>"+
        				 "<b class='style6'> Este es un correo generado de forma automatica, por favor no responder.</b><br />"+
						 "<b class='style6'>Usted est&aacute; recibiendo un CFDI Versi&oacute;n 3.2 (Factura Electr&oacute;nica) de </b><br />"+
						 "<b class='style5'>EDIFACTMX, S.A. DE C.V.</b><br /><br />"+
						 "<b class='style7'>Otros servicios relacionados para el cumplimiento de obligaciones fiscales.</b><br /><br />"+
						 "<a href='http://www.validaxml.com/' class='style7'>Utilice nuestro servicio para validar comprobantes fiscales.</a><br /><br />"+
						 "<a href='http://www.pacnomina.com/' class='style7'>Software disponible para emitir recibos de nomina.</a><br /><br />"+
						 "<a href='http://www.epyme2500.com/' class='style7'>Software con facturacion ilimitada por solo $999 pesos mas iva.</a><br /><br />"+
						 "<b class='style4'>Si usted desea que llegue el comprobante fiscal por internet CFDI a otro correo electr&oacute;nico notifiquelo a:</b><br /><br />"+
						 "<b class='style7'>activaciones@edifact.com.mx</b><br /><br />"+
						 "<b class='style7'>Saludos cordiales.</b><br /><br />"+
						 "<b class='style8'>EDIFACTMX, S.A. DE C.V.</b><br />"+
						 "<b class='style8'>AMORES, 707 PISO 6, COL. DEL VALLE</b><br />"+
						 "<b class='style8'>Tel. 53121000</b><br />"+
						 "<b class='style8'>BENITO JUAREZ, CIUDAD DE MEXICO, MEXICO, C.P.:03100</b>";
         */
         String subject="";
            if (serie!=null) {
                 subject = "CFDI Recibo Electronico de "+tipoD+" "+serie+"-"+folio+" de "+" Greiner Assistec S.A. de C.V.";
            } else {
                 subject = "CFDI Recibo Electronico de "+tipoD+" "+folio+" de "+" Greiner Assistec S.A. de C.V.";
            }
        String message = "<html xmlns='http://www.w3.org/1999/xhtml'>"+
        				 "<head>"+
        				 "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
        				 "<title>EdiFactMX Envio de correos automaticos</title>"+
        				 "<style type='text/css'>"+
        				 ".style3 {font-family: Arial, Helvetica, sans-serif}"+
        				 ".style4 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; }"+
        				 ".style5 {font-family: Arial, Helvetica, sans-serif; color: #006699;font-weight: bold;}"+
        				 ".style6 {font-family: Arial, Helvetica, sans-serif; font-size: 14px; }"+
        				 ".style7 {font-family: Arial, Helvetica, sans-serif; font-size: 12px}"+
        				 ".style8 {font-family: Arial, Helvetica, sans-serif; font-size: 10px}"+
        				 "</style>"+
        				 "</head>"+
        				 "<body>"+
        				 "<p class='style4'>Estimado Cliente "+nomReceptor+": </p>"+
        				 "<br />"+
						 "<b class='style6'>Sírvase encontrar en anexo su Recibo Electrónico de "+tipoD+".  </b><br />"+
						 "<b class='style5'>Agradecemos de antemano su pago. Gracias por hacer negocios con Greiner Assistec S.A. de C.V.</b><br /><br />"+
						 "<br />"+
//						 "<p class='style4'>Dear Customer "+nomReceptor+": </p>"+
//        				 "<br />"+
//						 "<b class='style6'>Your invoice is attached.</b><br />"+
//						 "<b class='style5'>Please remit payment at your earliest convenience. Thank you for doing Business with Sea Cargo Logistics!</b><br /><br />"+
//						 "<br />"+
						 "<b class='style7'>Saludos.</b><br /><br />";
         
        // attachments
        String nombreA="";
            if (serie!=null) {
                nombreA=serie+folio;
            } else {
                nombreA=folio;
            }
        String[] attachFiles = new String[2];
        attachFiles[0] = Utils.getParamValue("path_base")+nombreA+".xml";
//        System.out.println("Archivo xml "+Utils.getParamValue("path_base")+nombreA+".xml");
        attachFiles[1] = Utils.getParamValue("path_base")+nombreA+" .pdf";
//        System.out.println("Archivo pdf "+Utils.getParamValue("path_base")+nombreA+".pdf");
        //attachFiles[2] = "e:/Test/Video.mp4";
 
        try {
//        	System.out.println("Intentando enviar correos");
//        	System.out.println("TO SEND2 "+mailTo);
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message, attachFiles);
            //adminDatos.actualizaEnvioCorreo(uuid);
//            System.out.println("El correo fue enviado a "+mailTo);
        } catch (Exception ex) {
            System.out.println("Error al enviar el correo ");
            ex.printStackTrace();
        }
	}
	
	/*public void enviarPreparaCorreoCancelacion(String uuid, String correo, String folio, String serie)throws Exception
	{
		adminDatos = new AdministraDatosDao();
		String host = EdifactUtils.getParametro("mail_host");//"mail.edifact.com.mx";
        String port = EdifactUtils.getParametro("mail_port"); //"587";
        String mailFrom = EdifactUtils.getParametro("mail_usuario");//"orodriguez@edifact.com.mx";
        String password = EdifactUtils.getParametro("mail_password");//"";
        System.out.println("CFDI CANCELDO TO SEND1 "+correo);
        // message info
        String mailTo = correo;
        String subject = "Correo de cancelaci�n de UNIVERSIDAD INSURGENTES ";     													

        String message = "<html xmlns='http://www.w3.org/1999/xhtml'>"+
        				 "<head>"+
        				 "<meta http-equiv='Content-Type' content='text/html; charset=utf-8' />"+
        				 "<title>Untitled Document</title>"+
        				 "<style type='text/css'>"+
        				 ".style3 {font-family: Arial, Helvetica, sans-serif}"+
        				 ".style4 {font-family: Arial, Helvetica, sans-serif; font-weight: bold; }"+
        				 ".style5 {font-family: Arial, Helvetica, sans-serif; color: #006699;font-weight: bold;}"+
        				 ".style6 {font-family: Arial, Helvetica, sans-serif; font-size: 14px; }"+
        				 ".style7 {font-family: Arial, Helvetica, sans-serif; font-size: 12px}"+
        				 ".style8 {font-family: Arial, Helvetica, sans-serif; font-size: 10px}"+
        				 "</style>"+
        				 "</head>"+
        				 "<body>"+
        				 "<p class='style4'>Estimado Cliente: </p>"+

						"<b class='style6'>Este mensaje es para notificar la cancelaci&oacute;n del comprobante fiscal "+serie+"-"+folio+"  con CFDI "+uuid+" </b><br />"+
						"<b class='style5'>EDIFACTMX, S.A. DE C.V.</b><br /><br />"+
						"<b class='style7'>Otros servicios relacionados para el cumplimiento de obligaciones fiscales.</b><br /><br />"+
						"<a href='http://www.validaxml.com/' class='style7'>Utilice nuestro servicio para validar comprobantes fiscales.</a><br /><br />"+
						"<a href='http://www.pacnomina.com/' class='style7'>Software disponible para emitir recibos de nomina.</a><br /><br />"+
						"<a href='http://www.epyme2500.com/' class='style7'>Software con facturacion ilimitada por solo $999 pesos mas iva.</a><br /><br />"+
						"<b class='style4'>Si usted desea que llegue el comprobante fiscal por internet CFDI a otro correo electr&oacute;nico notifiquelo a:</b><br /><br />"+
						"<b class='style7'>activaciones@edifact.com.mx</b><br /><br />"+
						"<b class='style7'>Saludos cordiales.</b><br /><br />"+
						"<b class='style8'>EDIFACTMX, S.A. DE C.V.</b><br />"+
						"<b class='style8'>MANZANILLO, 17 PISO 3, COL. ROMA</b><br />"+
						"<b class='style8'>Tel. 53121000</b><br />"+
						"<b class='style8'>CUAUHTEMOC, DISTRITO FEDERAL, MEXICO, C.P.:06700</b>";
 
        // attachments
        String[] attachFiles = new String[1];
        attachFiles[0] = EdifactUtils.getParametro("path_cfdi_send_cancel")+"can_"+uuid+".pdf";
        System.out.println("Archivo de cfdi cancelado pdf "+EdifactUtils.getParametro("path_cfdi_send_cancel")+"can_"+uuid+".pdf");
        //attachFiles[2] = "e:/Test/Video.mp4";
 
        try {
        	System.out.println("Intentando enviar correos de cancelacion");
        	System.out.println("TO SEND2 "+mailTo);
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message, attachFiles);
            adminDatos.actualizaEnvioCorreoCancelacion(uuid);
            System.out.println("El correo de cancelacion fue enviado a "+mailTo);
        } catch (Exception ex) {
            System.out.println("Error al enviar el correo de cancelacion ");
            ex.printStackTrace();
        }
	}*/
	
}
