/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edifactwsradiopolis.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.ssl.PKCS8Key;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import edifactwsradiopolis.crypto.CRSACpt;
import edifactwsradiopolis.crypto.RSAKPair;
import edifactwsradiopolis.dto.BeanGeneral;
import edifactwsradiopolis.dto.ComprobanteParseDto;
import edifactwsradiopolis.dto.FormatosVo;
import edifactwsradiopolis.dto.ParametrosVo;
import edifactwsradiopolis.dto.ResponseChangeInfoDto;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import mx.gob.sat.cfd4_0.Comprobante;




/**
 *
 * @author zamud
 */
public class Utils {
    
	/*public static final String CONNECTION_FACTORY_JNDI_NAME = "eis/sap/sstqas";
	public static String ds_c9000="jdbc/MysqlSSTC9000QASDS";
	public static String ds_cfdi="jdbc/MysqlSSTCFDIQASDS";*/
	
	/*public static final String CONNECTION_FACTORY_JNDI_NAME = "eis/sap/sstdes";
	public static String ds_c9000="jdbc/MysqlSSTC9000DESDS";
	public static String ds_cfdi="jdbc/MysqlSSTCFDIDESDS";*/
	private static final Charset UTF_8 = Charset.forName("UTF-8");
        private static final Charset ISO = Charset.forName("ISO-8859-1");
	public static final String CONNECTION_FACTORY_JNDI_NAME = "eis/sap/sstpro";
	//public static String ds_c9000="jdbc/MysqlSSTC9000DS";
	public static String ds_cfdi="jdbc/MyslqProaDS";
	
	public static ArrayList<ParametrosVo> parametrosVos;
	
	public static ArrayList<FormatosVo> formatosVos;

    public static String pdfBase64(String src, String nombreArchivo) throws IOException{
    	java.io.File file = new java.io.File(src + nombreArchivo + " .pdf");
		java.io.FileInputStream fis = new java.io.FileInputStream(file);
		byte[] buff = new byte[(int) file.length()];
		fis.read(buff);
		String base64 = Base64Coder.encodeLines(buff);
		return base64.replaceAll("\n", "");
    }
	
    
    public static XMLGregorianCalendar stringXMLGregorian (String fecha) throws ParseException, DatatypeConfigurationException{
    	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
		GregorianCalendar c1 = new GregorianCalendar();
		c1.setTime(formatoDelTexto.parse(fecha));
		XMLGregorianCalendar date22 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c1);
		String f1=date22.toString();
		String [] fec1= f1.split("T");
		String valor1=fec1[0];
		XMLGregorianCalendar date1 = DatatypeFactory.newInstance().newXMLGregorianCalendar(valor1);
    	return date1;
    }
    
	public String normaliza(String input) {
        if (input == null) {
            return "";
        }

//        input = input.replaceAll("�?", "&193;");
//        input = input.replaceAll("É", "&201;");
//        input = input.replaceAll("�?", "&205;");
//        input = input.replaceAll("Ó", "&211;");
//        input = input.replaceAll("Ú", "&218;");
//        input = input.replaceAll("Ñ", "&209;");
//
//        input = input.replaceAll("á", "&225;");
//        input = input.replaceAll("é", "&233;");
//        input = input.replaceAll("í", "&237;");
//        input = input.replaceAll("ó", "&243;");
//        input = input.replaceAll("ú", "&250;");
//        input = input.replaceAll("ñ", "&241;");
//        input = input.replaceAll("\\?", "&63;");
//        input = input.replaceAll("\\¿", "&191;");
//        input = input.replaceAll("/", "&47;");
//        input = input.replaceAll(":", "&58;");
        input = input.replaceAll("<!\\[CDATA\\[", "");
        input = input.replaceAll("]]>", "");
        input = input.replaceAll("&, &, &, &.>", "");
        input = input.replaceAll("&", "&amp;");
//        input = input.replaceAll("\\&", "&amp;");
        return input;

    }
	
	public  void DatosEmisorLlave() throws Exception {
      
        byte[] decodeBase64Cer = null;
        byte[] decodeBase64Llav = null;
        String contrasena = null;
        String query = "";
        /*DateLlave = connection.exec(query);
        while(DateLlave.next()){
            decodeBase64Cer = DateLlave.getBytes("cer");
            decodeBase64Llav = DateLlave.getBytes("llave");
            contrasena = DateLlave.getString("contrasenia");
        }*/
        //obtenemos la informacion del certificado
        CertificateFactory cf = CertificateFactory.getInstance("X509");
        X509Certificate cert = (X509Certificate) cf.generateCertificate(new ByteArrayInputStream(decodeBase64Cer));
        //obtenemos el numero de certificado
        byte[] byteArray= cert.getSerialNumber().toByteArray();
        String value = new String(byteArray);
        //obtenemos el certificado en base 64
        Base64 encoder=new Base64();
        String psB64Certificate = encoder.encodeToString(cert.getEncoded());
        //pasamos los datos obtenidos del emisor
        //this.nocertificado = value;
        //this.certificado = psB64Certificate.replaceAll("\n", "");
        //this.cert = decodeBase64Cer;
        //this.key = decodeBase64Llav;
        //this.pass = contrasena;
        //this.llave = loadLlave(decodeBase64Llav, contrasena);       
       // connection.disconnect();
    }
    
    //private PrivateKey loadLlave(byte[] llave, String pass) {
        /*try{
            InputStream bis = new ByteArrayInputStream(llave);
            PKCS8Key key = new PKCS8Key(bis, pass.toCharArray());
            PKCS8EncodedKeySpec privKeySpec = new PKCS8EncodedKeySpec(key.getDecryptedBytes());
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey llavePrivada = kf.generatePrivate(privKeySpec);
            return llavePrivada;
        } catch(GeneralSecurityException | IOException ex){
            log.error("No es posible obtener la Llave privada. Error " + ex.getMessage());
            
            return null;
        }*/
    //}
	
	public static X509Certificate getX509Certificate(final File certificateFile) throws Exception
	{
		FileInputStream is = null;
		
		try
		{
			is=new FileInputStream(certificateFile);
			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			return (X509Certificate) cf.generateCertificate(is);
		}
		finally{
			if(is==null)
			{
				is.close();
			}
		}		
	}
	
	public static String getCertificadoBase64(final X509Certificate cert) throws Exception
	{
		byte[] bytes = cert.getEncoded();
	    Base64 b64 = new Base64(-1);
	    String certStr = b64.encodeToString(bytes);
	    return certStr;
		//return new String(Base64Coder.encode(cert.getEncoded()));
	}
	
	public static String getNoCertificado(final X509Certificate cert)
	{
		BigInteger serial = cert.getSerialNumber();
		byte[] sArr = serial.toByteArray();
		StringBuffer buffer = new StringBuffer();
		
		for(int i = 0; i<sArr.length; i++)
		{
			buffer.append((char) sArr[i]);
		}
		return buffer.toString();
	}
	
	public static String generarCadenaOriginal(final String xml, String xsltfile) throws Exception
	{
		StreamSource streamSource = new StreamSource(xsltfile);		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer xsltTransformer = transformerFactory.newTransformer(streamSource);
		
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		xsltTransformer.transform(new StreamSource(new StringReader(xml)), new StreamResult(outputStream));
		return outputStream.toString();
	}
	
	public static PrivateKey getPrivateKey(final File keyFile, final String password) throws Exception
	{
		FileInputStream in = new FileInputStream(keyFile);
		PKCS8Key pkcs8Key = new PKCS8Key(in,password.toCharArray());
		byte[] decripted = pkcs8Key.getDecryptedBytes();
		PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decripted);
		PrivateKey pk = null;
		
		if(pkcs8Key.isDSA())
		{
			pk = KeyFactory.getInstance("DSA").generatePrivate(spec);
		}
		else if(pkcs8Key.isRSA())
		{
			pk = KeyFactory.getInstance("RSA").generatePrivate(spec);
		}
		
		pk = pkcs8Key.getPrivateKey();
		return pk;
	}
	
	public static String generarSelloDigital(final PrivateKey key, final String cadenaOriginal) throws Exception
	{
		Signature sign = Signature.getInstance("SHA1withRSA");
		sign.initSign(key, new SecureRandom());
		sign.update(cadenaOriginal.getBytes());
		byte[] signature = sign.sign();
		Base64 b64 = new Base64(-1);
	    return b64.encodeToString(signature);
		//return new String(Base64Coder.encode(signature));
	}
	
	public static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
        DocumentBuilder builder;  
        try 
        {  
            builder = factory.newDocumentBuilder();  
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) ); 
            return doc;
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return null;
    }   
	
	public static String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
         
        return null;
    }
	
	public static String nullToString(String dato)
    {
    	String res="";
    	if(dato==null)
    	{
    		res="";
    	}
    	else if(dato.trim()=="null")
    	{
    		res="";
    	}
    	else
    	{
    		res=dato.trim();
    	}
    	
    	//String escape=StringEscapeUtils.escapeXml(res);
    	return res;
    }
	
	
	public static String getParamValue(String key)
	{
		String valor="";
		try
		{
			for(int i=0; i<parametrosVos.size(); i++)
			{
				if(key.equals(parametrosVos.get(i).getParametro_llave()))
				{
					valor = parametrosVos.get(i).getParametro_valor();
					break;
				}			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			valor="";
		}
		return valor;
	}
	
	public static Comprobante xmlToClass (String xml) throws UnsupportedEncodingException {
		Comprobante pomprobante= null;
	
		try {
			//File file = new File("C:\\edifactmx_cancelaciones\\archivos\\2019-04-12_00ad5faa-de44-4598-8990-4b5411ac9814_01\\00ad5faa-de44-4598-8990-4b5411ac9814.xml");
			JAXBContext jc = JAXBContext.newInstance(Comprobante.class);
			Unmarshaller u = jc.createUnmarshaller();

			//File f = new File("product.xml");
			StringReader sr = new StringReader(xml);
			pomprobante = (Comprobante) u.unmarshal(sr);

			//System.out.println(pomprobante.getFolio());
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	return pomprobante;
}
	
	public static String charXMLtoString(String dato)
    {
    	String res="";
    	if(dato==null)
    	{
    		res="";
    	}
    	else if(dato.trim()=="null")
    	{
    		res="";
    	}
    	else
    	{
    		res=dato.trim();
    	}
    	
    	//String escape=StringEscapeUtils.unescapeXml(res);
    	return res;
    }
	
	public static String getFormatoValue(String key)
	{
		String valor="";
		try
		{
			for(int i=0; i<formatosVos.size(); i++)
			{
				if(key.equals(formatosVos.get(i).getFormato_clave()))
				{
					valor = formatosVos.get(i).getFormato_descripcion();
					break;
				}			
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			valor="";
		}
		return valor;
	}
	
	public static String ponerAlias(String texto, String toolIn)
    {
    	byte[] raw;
        String encryptedString;
        SecretKeySpec skeySpec;
        byte[] encryptText = texto.getBytes();
        Cipher cipher;

        try {
            raw = Base64.decodeBase64(toolIn);
            skeySpec = new SecretKeySpec(raw, "AES");
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            encryptedString = Base64.encodeBase64String(cipher.doFinal(encryptText));
        } 
        catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        return encryptedString;
    }
    
    public static String quitarAlias(String textocrypt, String toolOut)
    {    
    	Cipher cipher;
        String encryptedString;
        byte[] encryptText = null;
        byte[] raw;
        SecretKeySpec skeySpec;

        try {
            raw = Base64.decodeBase64(toolOut);
            skeySpec = new SecretKeySpec(raw, "AES");
            encryptText = Base64.decodeBase64(textocrypt);
            cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            encryptedString = new String(cipher.doFinal(encryptText));

        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
        return encryptedString;
    }
    
    public synchronized static String formatDate(String pattern, java.util.Date date) {
    	//patern yyyy-MM-dd'T'HH:mm:ss.SSS
    	String format = new SimpleDateFormat(pattern, Locale.ENGLISH).format(date);

        return format;
    }
    
    public synchronized static String formatDateLocale(java.util.Date date, String locale) {
    	
    	String output = "";
    	
    	if(locale.equals("ES"))
    	{
    		SimpleDateFormat formateador = new SimpleDateFormat("dd'/'MM'/'yyyy", new Locale("ES"));
    		output = formateador.format(date);
       	}
    	else
    	{
    		SimpleDateFormat formateador = new SimpleDateFormat("dd'/'MM'/'yyyy", new Locale("US"));
    		output = formateador.format(date);    	
    	}
        return output;
    }
    
    public static String getDecimalFormat(String numero, Boolean locale)
    {
    	String resultado = "";
        
    	Locale prefPaisMX = new Locale("es", "MX");        
        DecimalFormat myFormatterImporte = (DecimalFormat) NumberFormat.getInstance(prefPaisMX);        

    	
    	if(locale)
    	{
    		myFormatterImporte.applyPattern("###,###,###,###.00");
    		BigDecimal nd= new BigDecimal(numero);
   		 	//DecimalFormat df = new DecimalFormat("#.##");      
   		 	//resultado = df.format(nd);
   		 	resultado=myFormatterImporte.format(nd);   
    	}
    	else
    	{
    		 myFormatterImporte.applyPattern("#.00");
    		 BigDecimal nd= new BigDecimal(numero);
    		 //DecimalFormat df = new DecimalFormat("#.##");      
    		 //resultado = df.format(nd);
    		 resultado=myFormatterImporte.format(nd);
    	}
    	    	
    	     
        return resultado;
    }
    
    public static Date stringToDate(String fecha)
    {
    	SimpleDateFormat formatter;
    	formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    	String oldDate = "";
    	Date date = null;
		try {
			//oldDate.substring(0,19);
			date = formatter.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//date = new Date();
		}
    	return date;
    }
    
    public static Date stringToDatePatern(String fecha, String patern_in, String patern_out)
    {
    	Date datein = null;
    	Date dateout = null;
    	
    	try
    	{
	    	SimpleDateFormat format1 = new SimpleDateFormat(patern_in); //"dd/MM/yyyy"
	    	SimpleDateFormat format2 = new SimpleDateFormat(patern_out); //"yyyy-MM-dd'T'HH:mm:ss"
	    	datein = format1.parse(fecha);
	    	String fecha_salida= format2.format(datein);
	    	dateout = format2.parse(fecha_salida);
    	}
    	catch(Exception e){}
    	return dateout;
    }
    
    public static String getNodeAttributeValue(String xmlfile, String tagName, String attr)
    {
        String valorxml = "";
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document resultado=builder.parse(new ByteArrayInputStream(xmlfile.getBytes()));

            //Node Comprobante = resultado.getElementsByTagNameNS("http://www.sat.gob.mx/cfd/3","Comprobante").item(0);
            //NamedNodeMap attributesC = Comprobante.getAttributes();
            //total = attributesC.getNamedItem("total").getTextContent(); 

            NodeList nodesReceptor = resultado.getElementsByTagName(tagName);
//            System.out.print("Nodos: "+nodesReceptor.getLength());

            for (int i = 0; i < nodesReceptor.getLength(); i++) {
                Element element = (Element) nodesReceptor.item(i);
                valorxml = element.getAttribute(attr);
//                System.out.print("Atributo: "+valorxml);
            }

            } catch (Exception ex) {
                valorxml=buscaAtributoValue(xmlfile,attr,tagName);
                System.out.println(ex.getMessage());
            } 

        return valorxml;
    }
    
    public static String buscaAtributoValue(String archivo, String palabra, String filapalabra)
    {
        String resultado="";
        String receptor = "";
        String emisor = "";
        
        if(filapalabra.equals("cfdi:Emisor"))
        {
            emisor = StringUtils.substringBetween(archivo,"<cfdi:Emisor",">");            
            resultado=StringUtils.substringBetween(emisor,palabra+"=\"","\"");
        }
        else if(filapalabra.equals("cfdi:Receptor"))
        {
            receptor = StringUtils.substringBetween(archivo,"<cfdi:Receptor",">");
            resultado=StringUtils.substringBetween(receptor,palabra+"=\"","\"");
        }
        else
        {
            resultado=StringUtils.substringBetween(archivo,palabra+"=\"","\"");
        }
        
        return resultado;
    }
    
    public static String splitComplemento(String archivo)
    {    	
    	String resultado = null;
    	String resultado_l[] = null;
    	String resultado_r[] = null;
    	
    	if(archivo != null)
    	{
    		if(archivo.indexOf("</Complementos>")!=-1)
    		{
	    		try{ 
		    		resultado_l = archivo.split("<Complementos>");
		    		resultado_r = archivo.split("</Complementos>");
		    		resultado = resultado_l[0]+resultado_r[1];
		    	}
		    	catch(Exception ex){
		    		resultado = "";
		    	}
    		} 
    		else
    		{
    			resultado = archivo;
    		}
    	}
    	else
    	{
    		resultado = archivo;
    	}
    	return resultado;
    }
    
    public static ResponseChangeInfoDto strinfToolChange(String word, String prkpathName, String pukPathName) throws Exception {
    	String transformation = "RSA/ECB/PKCS1Padding";
        String encoding = "UTF-8";
    	ResponseChangeInfoDto changeinfo = new ResponseChangeInfoDto();
	    try {	  
	        RSAKPair rsaKeyPair = new RSAKPair(2048);
	        rsaKeyPair.toFileSystem(prkpathName, pukPathName);	
	        CRSACpt rsaCipher = new CRSACpt();	        
	        String decrypted = rsaCipher.decrypt(word, prkpathName, transformation, encoding);
	        changeinfo.setTexto(decrypted);
	        changeinfo.setCodigo("100");
	    } catch(Exception exception) {
	    	changeinfo.setTexto("");
	        changeinfo.setCodigo("JE100");
	        changeinfo.setDescripcion("The testEncryptDecryptWithKeyPairFiles() test failed because: " + exception.getMessage());
	    }
	    return changeinfo;
	}
    
    public static String customFormat(String pattern, String value ) {
    	String output = "0.00";
    	
    	try
    	{
    		DecimalFormat decFormat = new DecimalFormat(pattern);
    		double dvalor = Double.parseDouble(value.trim());
    		output = decFormat.format(dvalor);
    	}
    	catch(Exception ex){
    		System.out.println(ex.getMessage());
    	}
    	
        return output;
    }
    
    public static BigDecimal stringtoBigDecimal(String number)
    {
    	BigDecimal res = new BigDecimal("0.0");
    	
    	try
    	{
    		res=new BigDecimal(number);
    	}
    	catch(Exception e){
    		System.out.println("Error al cambiar string por big decimal. ");
    	}
    	
		return res;
    }
    
    public static String bigDecimalToString(BigDecimal number)
    {
    	String res = "0.0";
    	
    	try
    	{
    		res=number.toString();
    	}
    	catch(Exception e){
    		System.out.println("Error al cambiar Bigdecimal por String ");
    	}
    	
		return res;
    }
    
     public static String writeXMLFile(String xmlString, String xmlName, ComprobanteParseDto comprobanteParseDto, BeanGeneral compr){
    	BufferedWriter out = null;
    	
        try {
        	String xml[]= new String[2];
        	String xmlTimbrado="";
        	out = new BufferedWriter(new FileWriter(xmlName));
            String addenda="";
            
            if (compr.getMabeCabecero() != null) {
                xml = xmlString.split("</cfdi:Complemento>");
                
                String tipoD ="";
                if (compr.getComprobante().getTipoDeComprobante().toString().equalsIgnoreCase("I")) {
                    tipoD = "FACTURA";
                } else if(compr.getComprobante().getTipoDeComprobante().equals("E")){
                    tipoD = "NOTA DE CREDITO";
                }
                String[] fecha = compr.getComprobante().getFecha().toString().split("T");
                
                
                
            	addenda+="</cfdi:Complemento>"
                        + "<cfdi:Addenda>"
            			+"<mabe:Factura xmlns:mabe=\"http://recepcionfe.mabempresa.com/cfd/addenda/v1\" referencia1=\""+compr.getMabeCabecero().getReferencia1()+"\" referencia2=\""+compr.getMabeCabecero().getReferencia2()+"\" ordenCompra=\""+compr.getMabeCabecero().getOrdenCompra()+"\" fecha=\""+fecha[0]+"\" folio=\""+compr.getComprobante().getFolio()+"\" tipoDocumento=\""+tipoD+"\" version=\"1.0\" xsi:schemaLocation=\"http://recepcionfe.mabempresa.com/cfd/addenda/v1 http://recepcionfe.mabempresa.com/cfd/addenda/v1/mabev1.xsd\">"
            			+"<mabe:Moneda importeConLetra=\""+compr.getMabeCabecero().getImporteLetra()+"\" tipoCambio=\""+compr.getComprobante().getTipoCambio()+"\" tipoMoneda=\""+compr.getComprobante().getMoneda()+"\"/>"
            			+"<mabe:Proveedor codigo=\""+compr.getMabeCabecero().getCodigo()+"\"/>"
            			+"<mabe:Entrega plantaEntrega=\""+compr.getMabeCabecero().getPlantaEntrega()+"\"/>"
            			+"<mabe:Detalles>";         	
            	for(int i=0; i < compr.getMabeDetalles().size(); i++){
                        
            		addenda+= "<mabe:Detalle cantidad=\""+compr.getComprobante().getConceptos().getConcepto().get(i).getCantidad()+"\" codigoArticulo=\""+compr.getComprobante().getConceptos().getConcepto().get(i).getClaveProdServ()+"\" descripcion=\""+compr.getComprobante().getConceptos().getConcepto().get(i).getDescripcion()+"\" importeConIva=\""+compr.getMabeDetalles().get(i).getImporteConIVA()+"\" importeSinIva=\""+compr.getMabeDetalles().get(i).getImporteSinIVA()+"\" noLineaArticulo=\""+(i+1)+"\" precioConIva=\""+compr.getMabeDetalles().get(i).getPrecuiConIVA()+"\" precioSinIva=\""+compr.getMabeDetalles().get(i).getPrecioSinIVA()+"\" unidad=\""+compr.getMabeDetalles().get(i).getUnidad()+"\"/>";
            	}
                addenda+="</mabe:Detalles>"
                        +"<mabe:Subtotal importe=\""+compr.getComprobante().getSubTotal()+"\"/>"
            			+"<mabe:Traslados>";
                for (int j = 0; j < compr.getComprobante().getImpuestos().getTraslados().getTraslado().size(); j++) {
                    addenda+= "<mabe:Traslado importe=\""+compr.getComprobante().getImpuestos().getTraslados().getTraslado().get(j).getImporte()+"\" tasa=\""+compr.getComprobante().getImpuestos().getTraslados().getTraslado().get(j).getTasaOCuota()+"\" tipo=\"IVA\"/>";
                }
                    addenda+="</mabe:Traslados>"
                                
                                +"<mabe:Total importe=\""+compr.getComprobante().getTotal()+"\"/>"
                                +"</mabe:Factura>"
            			+"</cfdi:Addenda>";
            	
                xmlTimbrado=xml[0]+addenda+xml[1];
                
                out.write(xmlTimbrado);
            
            out.close();
            xmlString = xmlTimbrado;
            }else if (compr.getAddendaSanofi()!= null) {
                xml = xmlString.split("</cfdi:Complemento>");
                
            	addenda+="</cfdi:Complemento>"
                        + "<cfdi:Addenda xsi:schemaLocation=\"https://mexico.sanofi.com/schemas https://mexico.sanofi.com/schemas/sanofi.xsd\" xmlns:sanofi=\"https://mexico.sanofi.com/schemas\">"
            			+"<Complementosanofi>"
            			+"<sanofi:NUM_ORDEN>"+compr.getAddendaSanofi().getOrdenCompra()+"</sanofi:NUM_ORDEN>"
            			+"<sanofi:CORREO_SANOFI>"+compr.getAddendaSanofi().getCorreo()+"</sanofi:CORREO_SANOFI>"
            			+"</Complementosanofi>"
            			+"</cfdi:Addenda>";
            	
                xmlTimbrado=xml[0]+addenda+xml[1];
                
                out.write(xmlTimbrado);
            
            out.close();
            xmlString = xmlTimbrado;
            } else {
                out.write(xmlString);
            
            out.close();
            
            }
 
                
                
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Problema en addenda Mabe - "+e.getMessage());
        }
        return xmlString;
    }
     
     public static String cambiaDetallista(Comprobante comprobante, String datos, BeanGeneral compr) {
    	
    	String complemento_nuevo = "";
    	String complementoD = "";
        String complementoD2 = "";
    	String xmlcomplemento[] = datos.split("<detallista:detallista ");
    	String complxml[] = datos.split("</detallista:detallista>");
    	
        String entityType = "";
                if (compr.getComprobante().getTipoDeComprobante().toString().equalsIgnoreCase("I")) {
                    entityType = "INVOICE";
                }else if(compr.getComprobante().getTipoDeComprobante().toString().equalsIgnoreCase("E")){
                    entityType = "CREDIT_NOTE";
                }
    	
    	complementoD="<detallista:detallista contentVersion=\"1.3.1\" documentStatus=\"ORIGINAL\" documentStructureVersion=\"AMC8.1\" type=\"SimpleInvoiceType\">"+
        "<detallista:requestForPaymentIdentification>"+
          "<detallista:entityType>"+entityType+"</detallista:entityType>"+
        "</detallista:requestForPaymentIdentification>"+
        "<detallista:specialInstruction code=\"ZZZ\">"+
          "<detallista:text>"+compr.getDetallistaCabecero().getImporteLetra()+"</detallista:text>"+
        "</detallista:specialInstruction>"+
        "<detallista:orderIdentification>"+
          "<detallista:referenceIdentification type=\"ON\">"+compr.getDetallistaCabecero().getOrdenCompra()+"</detallista:referenceIdentification>"+
                "<detallista:ReferenceDate>"+compr.getDetallistaCabecero().getFechaContraRecibo()+"</detallista:ReferenceDate>"+
        "</detallista:orderIdentification>"+
        "<detallista:AdditionalInformation>"+
          "<detallista:referenceIdentification type=\"ACE\">"+compr.getDetallistaCabecero().getNumeroRemision()+"</detallista:referenceIdentification>"+
        "</detallista:AdditionalInformation>"+
        "<detallista:DeliveryNote>"+
          "<detallista:referenceIdentification>"+compr.getDetallistaCabecero().getContraRecibo()+"</detallista:referenceIdentification>"+
          "<detallista:ReferenceDate>"+compr.getDetallistaCabecero().getFechaContraRecibo()+"</detallista:ReferenceDate>"+    
        "</detallista:DeliveryNote>"+
        "<detallista:buyer>"+
          "<detallista:gln>"+compr.getDetallistaCabecero().getGlnComprador()+"</detallista:gln>"+
            "<detallista:contactInformation>"+
                "<detallista:personOrDepartmentName>"+
                    "<detallista:text>"+compr.getDetallistaCabecero().getContactoCompras()+"</detallista:text>"+
                "</detallista:personOrDepartmentName>"+
            "</detallista:contactInformation>"+
        "</detallista:buyer>"+
        "<detallista:seller>"+
          "<detallista:gln>"+compr.getDetallistaCabecero().getGlnProveedor()+"</detallista:gln>"+
          "<detallista:alternatePartyIdentification type=\"SELLER_ASSIGNED_IDENTIFIER_FOR_A_PARTY\">"+compr.getDetallistaCabecero().getIdentificacionSecundaria()+"</detallista:alternatePartyIdentification>"+
        "</detallista:seller>"+
        "<detallista:allowanceCharge settlementType=\"OFF_INVOICE\" allowanceChargeType=\"ALLOWANCE_GLOBAL\">"+
            "<detallista:specialServicesType>AJ</detallista:specialServicesType>"+
		"<detallista:monetaryAmountOrPercentage>"+
                    "<detallista:rate base=\"INVOICE_VALUE\">"+
                        "<detallista:percentage>"+compr.getDetallistaCabecero().getPorcentaje()+"</detallista:percentage>"+
                    "</detallista:rate>"+
                "</detallista:monetaryAmountOrPercentage>"+
        "</detallista:allowanceCharge>";
        
         String Line="";
         String Line2="";
         
//         if(compr.getDetallistaItems() != null){
             
              for(int i = 0; i < compr.getDetallistaItems().size(); i++){
                   
                Line2="<detallista:lineItem type=\"ALLOWANCE_GLOBAL\" number=\""+(i+1)+"\">"+
                        "<detallista:tradeItemIdentification>"+
			"<detallista:gtin>"+compr.getDetallistaItems().get(i).getEan()+"</detallista:gtin>"+
                        "</detallista:tradeItemIdentification>"+
                        "<detallista:alternateTradeItemIdentification type=\"BUYER_ASSIGNED\">"+compr.getDetallistaItems().get(i).getSku()+"</detallista:alternateTradeItemIdentification>"+
                        "<detallista:tradeItemDescriptionInformation language=\"ES\">"+
                            "<detallista:longText>"+compr.getComprobante().getConceptos().getConcepto().get(i).getDescripcion()+"</detallista:longText>"+
			"</detallista:tradeItemDescriptionInformation>"+
                        "<detallista:invoicedQuantity unitOfMeasure=\""+compr.getComprobante().getConceptos().getConcepto().get(i).getClaveUnidad()+"\">"+compr.getComprobante().getConceptos().getConcepto().get(i).getCantidad()+"</detallista:invoicedQuantity>"+
                        "<detallista:grossPrice>"+
                            "<detallista:Amount>"+compr.getDetallistaItems().get(i).getGrossPriceAmount()+"</detallista:Amount>"+
			"</detallista:grossPrice>"+
                        "<detallista:netPrice>"+
                            "<detallista:Amount>"+compr.getDetallistaItems().get(i).getNetPriceAmount()+"</detallista:Amount>"+
			"</detallista:netPrice>"+
                        "<detallista:totalLineAmount>"+
                            "<detallista:grossAmount>"+
				"<detallista:Amount>"+compr.getDetallistaItems().get(i).getTotalLineAmount()+"</detallista:Amount>"+
                            "</detallista:grossAmount>"+
                            "<detallista:netAmount>"+
				"<detallista:Amount>"+compr.getDetallistaItems().get(i).getTotalLineAMountNeto()+"</detallista:Amount>"+
                            "</detallista:netAmount>"+
			"</detallista:totalLineAmount>"+
                      "</detallista:lineItem>";
                Line = Line + Line2;
                
                        
            }
         
//         }else{
//              for(int i = 0; i < dataCE.getLineItem().size(); i++){
//                   
//                Line2="<detallista:lineItem type=\""+dataCE.getLineItem().get(i).getLineItemType()+"\" number=\""+dataCE.getLineItem().get(i).getLineItemNumer()+"\">"+
//                        "<detallista:tradeItemIdentification>"+
//			"<detallista:gtin>"+dataCE.getLineItem().get(i).getLineItemGtin()+"</detallista:gtin>"+
//                        "</detallista:tradeItemIdentification>"+
//                        "<detallista:tradeItemDescriptionInformation language=\""+dataCE.getLineItem().get(i).getTradeItemDescriptionType()+"\">"+
//                            "<detallista:longText>"+dataCE.getLineItem().get(i).getTradeItemDescription()+"</detallista:longText>"+
//			"</detallista:tradeItemDescriptionInformation>"+
//                        "<detallista:invoicedQuantity unitOfMeasure=\""+dataCE.getLineItem().get(i).getInvoicedquantityUnitof()+"\">"+dataCE.getLineItem().get(i).getInvoicedquantity()+"</detallista:invoicedQuantity>"+
//                        "<detallista:grossPrice>"+
//                            "<detallista:Amount>"+dataCE.getLineItem().get(i).getGrossPriceAmount()+"</detallista:Amount>"+
//			"</detallista:grossPrice>"+
//                        "<detallista:netPrice>"+
//                            "<detallista:Amount>"+dataCE.getLineItem().get(i).getNetpriceAmount()+"</detallista:Amount>"+
//			"</detallista:netPrice>"+
//                        "<detallista:totalLineAmount>"+
//                            "<detallista:grossAmount>"+
//				"<detallista:Amount>"+dataCE.getLineItem().get(i).getTotalLineGrossAmount()+"</detallista:Amount>"+
//                            "</detallista:grossAmount>"+
//                            "<detallista:netAmount>"+
//				"<detallista:Amount>"+dataCE.getLineItem().get(i).getTotalLineNetAmount()+"</detallista:Amount>"+
//                            "</detallista:netAmount>"+
//			"</detallista:totalLineAmount>"+
//                      "</detallista:lineItem>";
//                Line = Line + Line2;
//                
//                        
//            }
//         
//         }
                   
            complementoD2="<detallista:totalAmount>"+
                            "<detallista:Amount>"+compr.getDetallistaCabecero().getTotalAmount()+"</detallista:Amount>"+
                          "</detallista:totalAmount>"+
                          "<detallista:TotalAllowanceCharge allowanceOrChargeType=\"ALLOWANCE\">"+
                            "<detallista:specialServicesType>AJ</detallista:specialServicesType>"+
                                "<detallista:Amount>"+compr.getDetallistaCabecero().getMontoTotalDescuentos()+"</detallista:Amount>"+
                            "</detallista:TotalAllowanceCharge></detallista:detallista>";
            
            
    	complemento_nuevo = xmlcomplemento[0]+complementoD+Line+complementoD2+complxml[1];
    	
    	return complemento_nuevo;
    }
     
}
