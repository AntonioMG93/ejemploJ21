package edifactwsradiopolis.utils;

import java.util.Map;

import org.glassfish.jaxb.runtime.marshaller.NamespacePrefixMapper;

public class NamespacePrefixMapperImpl extends org.glassfish.jaxb.runtime.marshaller.NamespacePrefixMapper {
	private final Map<String, String> map;

	public NamespacePrefixMapperImpl(Map<String, String> map) {
		this.map = map;
	}

	public String getPreferredPrefix(String namespaceUri, String suggestion, boolean requirePrefix) {
		String value = map.get(namespaceUri);
		return (value != null) ? value : suggestion;
	}

	public String[] getPreDeclaredNamespaceUris() {
		return new String[0];
	}
}
