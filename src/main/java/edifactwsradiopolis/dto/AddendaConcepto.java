package edifactwsradiopolis.dto;

public class AddendaConcepto {
	private String noDelivery;
	private String fDelivery;
	private String pOCliente; 
	private String ASCI;
        private String materialCliente;
        private String ASN;
        private String valorUnitario;

    public String getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(String valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getASN() {
        return ASN;
    }

    public void setASN(String ASN) {
        this.ASN = ASN;
    }

    public String getMaterialCliente() {
        return materialCliente;
    }

    public void setMaterialCliente(String materialCliente) {
        this.materialCliente = materialCliente;
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
