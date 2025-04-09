/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edifactwsradiopolis.dto;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 *
 * @author Antonio
 */
public class MercanciaComercion {
     private String noIdentificacion; 
	private String fraccionAracelaria;
	private BigDecimal cantidadAduana;
	private String unidadAduana;
	private BigDecimal valorUnitarioAduana;
	private BigDecimal valorDolares;
	private ArrayList<DescripcionesEspMercanciaComerExt> descEspecificas = new ArrayList<DescripcionesEspMercanciaComerExt>();
	public String getNoIdentificacion() {
		return noIdentificacion;
	}
	public void setNoIdentificacion(String noIdentificacion) {
		this.noIdentificacion = noIdentificacion;
	}
	public String getFraccionAracelaria() {
		return fraccionAracelaria;
	}
	public void setFraccionAracelaria(String fraccionAracelaria) {
		this.fraccionAracelaria = fraccionAracelaria;
	}
	public BigDecimal getCantidadAduana() {
		return cantidadAduana;
	}
	public void setCantidadAduana(BigDecimal cantidadAduana) {
		this.cantidadAduana = cantidadAduana;
	}
	public String getUnidadAduana() {
		return unidadAduana;
	}
	public void setUnidadAduana(String unidadAduana) {
		this.unidadAduana = unidadAduana;
	}
	public BigDecimal getValorUnitarioAduana() {
		return valorUnitarioAduana;
	}
	public void setValorUnitarioAduana(BigDecimal valorUnitarioAduana) {
		this.valorUnitarioAduana = valorUnitarioAduana;
	}
	public BigDecimal getValorDolares() {
		return valorDolares;
	}
	public void setValorDolares(BigDecimal valorDolares) {
		this.valorDolares = valorDolares;
	}
	public ArrayList<DescripcionesEspMercanciaComerExt> getDescEspecificas() {
		return descEspecificas;
	}
	public void setDescEspecificas(ArrayList<DescripcionesEspMercanciaComerExt> descEspecificas) {
		this.descEspecificas = descEspecificas;
	}
}
