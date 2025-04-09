package edifactwsradiopolis.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DetalleComprobanteXMLParseDto {
	private String moneda;
	private String descripcion;
    private BigDecimal cantidad;
    private String unidad_medida;
    private BigDecimal precio;
    private BigDecimal importe;
    private String noIdentificacion;
    private BigDecimal valorUnitario;
    private String totImporte;
    
    //Atributos v3.3
    private String claveProdServ;
    private String claveUnidad;
    private ArrayList<TrasladoConcepto> listTrasladoConcepto = new ArrayList<TrasladoConcepto>();
    private ArrayList<RetencionConcepto> listRetencionConcepto = new ArrayList<RetencionConcepto>();
    private BigDecimal descuento;
    private String numeroOrden;
    private String nombrePaciente;
    private String ordenVenta;
    private String fechaOrdenVenta;
    private String codigoMaterial;
    
	public String getCodigoMaterial() {
		return codigoMaterial;
	}
	public void setCodigoMaterial(String codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	public String getNumeroOrden() {
		return numeroOrden;
	}

	public void setNumeroOrden(String numeroOrden) {
		this.numeroOrden = numeroOrden;
	}

	public String getNombrePaciente() {
		return nombrePaciente;
	}

	public void setNombrePaciente(String nombrePaciente) {
		this.nombrePaciente = nombrePaciente;
	}

	public String getOrdenVenta() {
		return ordenVenta;
	}

	public void setOrdenVenta(String ordenVenta) {
		this.ordenVenta = ordenVenta;
	}

	public String getFechaOrdenVenta() {
		return fechaOrdenVenta;
	}

	public void setFechaOrdenVenta(String fechaOrdenVenta) {
		this.fechaOrdenVenta = fechaOrdenVenta;
	}

	public BigDecimal getDescuento() {
		return descuento;
	}

	public void setDescuento(BigDecimal descuento) {
		this.descuento = descuento;
	}

	public ArrayList<TrasladoConcepto> getListTrasladoConcepto() {
		return listTrasladoConcepto;
	}

	public void setListTrasladoConcepto(ArrayList<TrasladoConcepto> listTrasladoConcepto) {
		this.listTrasladoConcepto = listTrasladoConcepto;
	}

	public ArrayList<RetencionConcepto> getListRetencionConcepto() {
		return listRetencionConcepto;
	}

	public void setListRetencionConcepto(ArrayList<RetencionConcepto> listRetencionConcepto) {
		this.listRetencionConcepto = listRetencionConcepto;
	}

	public String getClaveUnidad() {
		return claveUnidad;
	}

	public void setClaveUnidad(String claveUnidad) {
		this.claveUnidad = claveUnidad;
	}

	public String getClaveProdServ() {
		return claveProdServ;
	}

	public void setClaveProdServ(String claveProdServ) {
		this.claveProdServ = claveProdServ;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitrio) {
		this.valorUnitario = valorUnitrio;
	}	

    public String getNoIdentificacion() {
		return noIdentificacion;
	}

	public void setNoIdentificacion(String noIdentificacion) {
		this.noIdentificacion = noIdentificacion;
	}

	/**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the cantidad
     */
    public BigDecimal getCantidad() {
        return cantidad;
    }

    /**
     * @param cantidad the cantidad to set
     */
    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * @return the unidad_medida
     */
    public String getUnidad_medida() {
        return unidad_medida;
    }

    /**
     * @param unidad_medida the unidad_medida to set
     */
    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    /**
     * @return the precio
     */
    public BigDecimal getPrecio() {
        return precio;
    }

    /**
     * @param precio the precio to set
     */
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    /**
     * @return the importe
     */
    public BigDecimal getImporte() {
        return importe;
    }

    /**
     * @param importe the importe to set
     */
    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public String getTotImporte() {
		return totImporte;
	}

	public void setTotImporte(String totImporte) {
		this.totImporte = totImporte;
	}

}
