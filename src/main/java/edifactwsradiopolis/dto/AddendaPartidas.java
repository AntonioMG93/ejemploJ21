package edifactwsradiopolis.dto;

import java.util.ArrayList;

public class AddendaPartidas {
	private String noDelivery;
	private String fDelivery;
	private String pOCliente; 
	private String ASCI; 
	
	private ArrayList<AddendaConcepto> listSanofiDetalle = new ArrayList<AddendaConcepto>();
	
	public ArrayList<AddendaConcepto> getListSanofiDetalle() {
		return listSanofiDetalle;
	}
	public void setListSanofiDetalle(ArrayList<AddendaConcepto> listSanofiDetalle) {
		this.listSanofiDetalle = listSanofiDetalle;
	} 
	public String getNoDelivery() {
		return noDelivery;
	}
	public void setNoDelivery(String noDelivery) {
		this.noDelivery = noDelivery;
	}
	public String getfDelivery() {
		return fDelivery;
	}
	public void setfDelivery(String fDelivery) {
		this.fDelivery = fDelivery;
	}
	public String getpOCliente() {
		return pOCliente;
	}
	public void setpOCliente(String pOCliente) {
		this.pOCliente = pOCliente;
	}
	public String getASCI() {
		return ASCI;
	}
	public void setASCI(String aSCI) {
		ASCI = aSCI;
	}


}
