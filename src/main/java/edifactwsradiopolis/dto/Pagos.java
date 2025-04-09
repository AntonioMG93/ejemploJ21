package edifactwsradiopolis.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

public class Pagos {
	private Date fechaPago;
	private String fechaPago_pdf;
	private String formaDePagoP;
	private String modenaP;
	private BigDecimal tipoCambioP;
	private BigDecimal monto;
	private String numOperacion;
	private String rfcEmisorCtaOrd;
	private String nomBancoOrdExt;
	private String ctaOrdenante;
	private String rfcEmisorCtaBen;
	private String ctaBeneficiario;
	private String tipoCadPago;
	private String certPago;
	private String cadPago;
	private String selloPago;
	private ArrayList<DoctoRelacionado> listDocRelacionado = new ArrayList<DoctoRelacionado>();

	public String getFechaPago_pdf() {
		return fechaPago_pdf;
	}
	public void setFechaPago_pdf(String fechaPago_pdf) {
		this.fechaPago_pdf = fechaPago_pdf;
	}
	public Date getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}
	public String getFormaDePagoP() {
		return formaDePagoP;
	}
	public void setFormaDePagoP(String formaDePagoP) {
		this.formaDePagoP = formaDePagoP;
	}
	public String getModenaP() {
		return modenaP;
	}
	public void setModenaP(String modenaP) {
		this.modenaP = modenaP;
	}
	public BigDecimal getTipoCambioP() {
		return tipoCambioP;
	}
	public void setTipoCambioP(BigDecimal tipoCambioP) {
		this.tipoCambioP = tipoCambioP;
	}
	public BigDecimal getMonto() {
		return monto;
	}
	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}
	public String getNumOperacion() {
		return numOperacion;
	}
	public void setNumOperacion(String numOperacion) {
		this.numOperacion = numOperacion;
	}
	public String getRfcEmisorCtaOrd() {
		return rfcEmisorCtaOrd;
	}
	public void setRfcEmisorCtaOrd(String rfcEmisorCtaOrd) {
		this.rfcEmisorCtaOrd = rfcEmisorCtaOrd;
	}
	public String getNomBancoOrdExt() {
		return nomBancoOrdExt;
	}
	public void setNomBancoOrdExt(String nomBancoOrdExt) {
		this.nomBancoOrdExt = nomBancoOrdExt;
	}
	public String getCtaOrdenante() {
		return ctaOrdenante;
	}
	public void setCtaOrdenante(String ctaOrdenante) {
		this.ctaOrdenante = ctaOrdenante;
	}
	public String getRfcEmisorCtaBen() {
		return rfcEmisorCtaBen;
	}
	public void setRfcEmisorCtaBen(String rfcEmisorCtaBen) {
		this.rfcEmisorCtaBen = rfcEmisorCtaBen;
	}
	public String getCtaBeneficiario() {
		return ctaBeneficiario;
	}
	public void setCtaBeneficiario(String ctaBeneficiario) {
		this.ctaBeneficiario = ctaBeneficiario;
	}
	public String getTipoCadPago() {
		return tipoCadPago;
	}
	public void setTipoCadPago(String tipoCadPago) {
		this.tipoCadPago = tipoCadPago;
	}
	public String getCertPago() {
		return certPago;
	}
	public void setCertPago(String certPago) {
		this.certPago = certPago;
	}
	public String getCadPago() {
		return cadPago;
	}
	public void setCadPago(String cadPago) {
		this.cadPago = cadPago;
	}
	public String getSelloPago() {
		return selloPago;
	}
	public void setSelloPago(String selloPago) {
		this.selloPago = selloPago;
	}
	public ArrayList<DoctoRelacionado> getListDocRelacionado() {
		return listDocRelacionado;
	}
	public void setListDocRelacionado(ArrayList<DoctoRelacionado> listDocRelacionado) {
		this.listDocRelacionado = listDocRelacionado;
	}
}
