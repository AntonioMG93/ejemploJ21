package edifactwsradiopolis.dto;

import java.util.ArrayList;

public class DestinatarioComercioExt {
	private String numRegIdTrib;
	private String nombre;
	private ArrayList<DomicilioDestComercioExt> domDestinatario=new ArrayList<DomicilioDestComercioExt>();
	public String getNumRegIdTrib() {
		return numRegIdTrib;
	}
	public void setNumRegIdTrib(String numRegIdTrib) {
		this.numRegIdTrib = numRegIdTrib;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ArrayList<DomicilioDestComercioExt> getDomDestinatario() {
		return domDestinatario;
	}
	public void setDomDestinatario(ArrayList<DomicilioDestComercioExt> domDestinatario) {
		this.domDestinatario = domDestinatario;
	}

}
