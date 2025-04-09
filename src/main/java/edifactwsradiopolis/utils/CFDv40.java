package edifactwsradiopolis.utils;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import mx.gob.sat.cfd4_0.Comprobante;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CFDv40 {

	private static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	private static final String BASE_CONTEXT = "mx.gob.sat.cfd4_0";

	private final static Joiner JOINER = Joiner.on(':');

	private static Map<List<String>, JAXBContext> contextMap = new HashMap<>();
	private final JAXBContext context;

	public static final ImmutableMap<String, String> PREFIXES = ImmutableMap.of(
			"http://www.w3.org/2001/XMLSchema-instance", "xsi", "http://www.sat.gob.mx/cfd/4", "cfdi",
			"http://www.sat.gob.mx/TimbreFiscalDigital", "tfd");

	private final Map<String, String> localPrefixes = Maps.newHashMap(PREFIXES);

	final Comprobante document;
	final String complementosC;

	public CFDv40(String complmentos,InputStream in, String... contexts) throws Exception {
		this.context = getContext(contexts);
		this.document = load(in);
		this.complementosC = complmentos;
	}

	public CFDv40(String complmentos,Comprobante comprobante, String... contexts) throws Exception {
		this.context = getContext(contexts);
		this.document = copy(comprobante);
		this.complementosC = complmentos;
	}

	public void addNamespace(String uri, String prefix) {
		localPrefixes.put(uri, prefix);
	}

	public String guardar() throws Exception {
		String retorno = "";
		Marshaller m = context.createMarshaller();
		m.setProperty("org.glassfish.jaxb.namespacePrefixMapper", new NamespacePrefixMapperImpl(localPrefixes));
		m.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		m.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, getSchemaLocation());
		StringWriter sw = new StringWriter();
		sw.write(XML_HEADER);
		m.marshal(document, sw);
		retorno = sw.toString();
		return retorno;
	}

//	En este método se agregan los esquemas de forma automática. 
//	Se deben de agregar cada uno de los esquemas de los complementos que se utilicen.
	private String getSchemaLocation() throws Exception {
		List<String> contexts = new ArrayList<>();
		String schema = "http://www.sat.gob.mx/cfd/4 http://www.sat.gob.mx/sitio_internet/cfd/4/cfdv40.xsd";
if (document != null && document.getComplemento() != null) {
				
					if (complementosC.equalsIgnoreCase("detallista")) {
						if (!schema.contains("http://www.sat.gob.mx/detallista http://www.sat.gob.mx/sitio_internet/cfd/detallista/detallista.xsd")) {
							schema += " http://www.sat.gob.mx/detallista http://www.sat.gob.mx/sitio_internet/cfd/detallista/detallista.xsd";
						}
					} else {

					}
					if (!contexts.isEmpty()) {
						getContext(contexts.toArray(new String[contexts.size()]));
					}
			}
		return schema;
	}

	// Defensive deep-copy
	private Comprobante copy(Comprobante comprobante) throws Exception {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.newDocument();
		Marshaller m = context.createMarshaller();
		m.marshal(comprobante, doc);
		Unmarshaller u = context.createUnmarshaller();
		return (Comprobante) u.unmarshal(doc);
	}

	private static JAXBContext getContext(String[] contexts) throws Exception {
		List<String> ctx = Lists.asList(BASE_CONTEXT, contexts);
		if (!contextMap.containsKey(ctx)) {
			JAXBContext context = JAXBContext.newInstance(JOINER.join(ctx));
			contextMap.put(ctx, context);
		}
		return contextMap.get(ctx);
	}

	private static Comprobante load(InputStream source, String... contexts) throws Exception {
		JAXBContext context = getContext(contexts);
		try {
			Unmarshaller u = context.createUnmarshaller();
			return (Comprobante) u.unmarshal(source);
		} finally {
			source.close();
		}
	}

}
