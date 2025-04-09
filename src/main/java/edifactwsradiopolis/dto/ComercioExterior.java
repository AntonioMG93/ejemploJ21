package edifactwsradiopolis.dto;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ComercioExterior {
	private String motivoTraslado;
	private String claveDePedimento;
	private String certificadoOrigen;
	private String numCertificadoOrigen;
	private String numExportadorCon;
	private String incoterm;
	private String subDivision;
	private String observaciones;
	private BigDecimal tipoCambioUSD;
	private BigDecimal totalUSD;
	private EmisorComercioExt emisorComercio;
	private ArrayList<PropietarioComercioExt>  listPropietario = new ArrayList<PropietarioComercioExt>();
	private ReceptorComercioExt receptorComercio;
	private ArrayList<DestinatarioComercioExt> listDestinatario = new ArrayList<DestinatarioComercioExt>();
	private ArrayList<MercanciaComercion> listMercancia = new ArrayList<MercanciaComercion>();
	private String version;
        private String tipoOperacion;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
        
        
        
        
        
        public String getMotivoTraslado() {
		return motivoTraslado;
	}
	public void setMotivoTraslado(String motivoTraslado) {
		this.motivoTraslado = motivoTraslado;
	}
	public String getClaveDePedimento() {
		return claveDePedimento;
	}
	public void setClaveDePedimento(String claveDePedimento) {
		this.claveDePedimento = claveDePedimento;
	}
	public String getCertificadoOrigen() {
		return certificadoOrigen;
	}
	public void setCertificadoOrigen(String certificadoOrigen) {
		this.certificadoOrigen = certificadoOrigen;
	}
	public String getNumCertificadoOrigen() {
		return numCertificadoOrigen;
	}
	public void setNumCertificadoOrigen(String numCertificadoOrigen) {
		this.numCertificadoOrigen = numCertificadoOrigen;
	}
	public String getNumExportadorCon() {
		return numExportadorCon;
	}
	public void setNumExportadorCon(String numExportadorCon) {
		this.numExportadorCon = numExportadorCon;
	}
	public String getIncoterm() {
		return incoterm;
	}
	public void setIncoterm(String incoterm) {
		this.incoterm = incoterm;
	}
	public String getSubDivision() {
		return subDivision;
	}
	public void setSubDivision(String subDivision) {
		this.subDivision = subDivision;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public BigDecimal getTipoCambioUSD() {
		return tipoCambioUSD;
	}
	public void setTipoCambioUSD(BigDecimal tipoCambioUSD) {
		this.tipoCambioUSD = tipoCambioUSD;
	}
	public BigDecimal getTotalUSD() {
		return totalUSD;
	}
	public void setTotalUSD(BigDecimal totalUSD) {
		this.totalUSD = totalUSD;
	}
	public EmisorComercioExt getEmisorComercio() {
		return emisorComercio;
	}
	public void setEmisorComercio(EmisorComercioExt emisorComercio) {
		this.emisorComercio = emisorComercio;
	}
	public ArrayList<PropietarioComercioExt> getListPropietario() {
		return listPropietario;
	}
	public void setListPropietario(ArrayList<PropietarioComercioExt> listPropietario) {
		this.listPropietario = listPropietario;
	}
	public ReceptorComercioExt getReceptorComercio() {
		return receptorComercio;
	}
	public void setReceptorComercio(ReceptorComercioExt receptorComercio) {
		this.receptorComercio = receptorComercio;
	}
	public ArrayList<DestinatarioComercioExt> getListDestinatario() {
		return listDestinatario;
	}
	public void setListDestinatario(ArrayList<DestinatarioComercioExt> listDestinatario) {
		this.listDestinatario = listDestinatario;
	}
	public ArrayList<MercanciaComercion> getListMercancia() {
		return listMercancia;
	}
	public void setListMercancia(ArrayList<MercanciaComercion> listMercancia) {
		this.listMercancia = listMercancia;
	}
	
}
