package edifactwsradiopolis.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import edifactwsradiopolis.dto.ReturnValidaTagNameDto;

public class XMLParseUtils {
	public XMLParseUtils() {
        super();
    }

    public static Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
            return doc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static public String convertDocumentToString(Document doc) {
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

    static public String getValueNodeByQualf(NodeList nodes, String parentNode, String childNode, String qualf) {
        String fpago = "";

        for (int i = 0; i < nodes.getLength(); i++) {
            Element element = (Element) nodes.item(i);
            String qualif = getValueToTagName(element, parentNode);

            if (qualif.equals(qualf)) {
                fpago = getValueToTagName(element, childNode);
            }
        }

        return fpago;
    }

    public static String getValueToTagName(Element element, String tag) {

        ReturnValidaTagNameDto valida = new ReturnValidaTagNameDto();
        try {
            NodeList name = element.getElementsByTagName(tag);
            Element line = (Element) name.item(0);
            valida.setValor(getCharacterDataFromElement(line).trim());
            valida.setMensaje("SUCCESS");
        } catch (Exception e) {
            valida.setValor("");
            valida.setMensaje("FAULT");
            valida.setMensaje(e.getMessage());
            System.out.println(e.getStackTrace());
        }
        return valida.getValor();
    }

    public static String getCharacterDataFromElement(Element e) throws Exception {
        Node child = e.getFirstChild();
        if (child instanceof CharacterData) {
            CharacterData cd = (CharacterData) child;
            return cd.getData();
        }
        return "";
    }

    public static Date getFechaActual() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        int seg = cal.get(Calendar.SECOND);
        //int min = 0;
        //int seg = 0;
        Date date = new GregorianCalendar(year, month, day, hour, min, seg).getTime();
        return date;
    }
    
    /*public static Date convertStringToDate(String input)  {
        String pattern = "yyyy-MM-dd'T'HH:mm:ssZ";
        DateTimeFormatte formatter = DateTimeFormat.forPattern(pattern);
        DateTime dateTime = formatter.parseDateTime(input);
        return dateTime.toDate();
        //printout shows: Thu Aug 15 17:00:48 EDT 2013
    }*/
}
