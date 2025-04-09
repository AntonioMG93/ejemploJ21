package edifactwsradiopolis.dto;

import java.math.BigDecimal;
import java.math.BigInteger;

public class DoctoRelacionado {
	private String idDocumento;
	private String serie;
	private String folio;
	private String monedaDr;
	private BigDecimal tipoCambioDr;
	private String metodoDePagoDr;
	private BigInteger numParcialidad;
	private BigDecimal impSaldoAnt;
	private BigDecimal impPagado;
	private BigDecimal impoSaldoInsoluto;
	public BigDecimal getImpSaldoAnt() {
		return impSaldoAnt;
	}
	public void setImpSaldoAnt(BigDecimal impSaldoAnt) {
		this.impSaldoAnt = impSaldoAnt;
	}
	public BigDecimal getImpPagado() {
		return impPagado;
	}
	public void setImpPagado(BigDecimal impPagado) {
		this.impPagado = impPagado;
	}
	public BigDecimal getImpoSaldoInsoluto() {
		return impoSaldoInsoluto;
	}
	public void setImpoSaldoInsoluto(BigDecimal impoSaldoInsoluto) {
		this.impoSaldoInsoluto = impoSaldoInsoluto;
	}
	public String getIdDocumento() {
		return idDocumento;
	}
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getFolio() {
		return folio;
	}
	public void setFolio(String folio) {
		this.folio = folio;
	}
	public String getMonedaDr() {
		return monedaDr;
	}
	public void setMonedaDr(String monedaDr) {
		this.monedaDr = monedaDr;
	}
	public BigDecimal getTipoCambioDr() {
		return tipoCambioDr;
	}
	public void setTipoCambioDr(BigDecimal tipoCambioDr) {
		this.tipoCambioDr = tipoCambioDr; 
	}
	public String getMetodoDePagoDr() {
		return metodoDePagoDr;
	}
	public void setMetodoDePagoDr(String metodoDePagoDr) { 
		this.metodoDePagoDr = metodoDePagoDr;
	}
	public BigInteger getNumParcialidad() {
		return numParcialidad;
	}
	public void setNumParcialidad(BigInteger numParcialidad) {
		this.numParcialidad = numParcialidad;
	}
	
}
