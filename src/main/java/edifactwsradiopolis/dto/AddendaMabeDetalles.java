package edifactwsradiopolis.dto;

import java.util.ArrayList;

public class AddendaMabeDetalles {
        private ArrayList<AddendaMabeDetalles> listMabeDetalle = new ArrayList<AddendaMabeDetalles>();
	private String precioSinIVA;
        private String precuiConIVA;
        private String importeSinIVA;
        private String importeConIVA;
        private String unidad;

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }
    public String getPrecioSinIVA() {
        return precioSinIVA;
    }

    public void setPrecioSinIVA(String precioSinIVA) {
        this.precioSinIVA = precioSinIVA;
    }

    public String getPrecuiConIVA() {
        return precuiConIVA;
    }

    public void setPrecuiConIVA(String precuiConIVA) {
        this.precuiConIVA = precuiConIVA;
    }

    public String getImporteSinIVA() {
        return importeSinIVA;
    }

    public void setImporteSinIVA(String importeSinIVA) {
        this.importeSinIVA = importeSinIVA;
    }

    public String getImporteConIVA() {
        return importeConIVA;
    }

    public void setImporteConIVA(String importeConIVA) {
        this.importeConIVA = importeConIVA;
    }

    public ArrayList<AddendaMabeDetalles> getListMabeDetalle() {
        return listMabeDetalle;
    }

    public void setListMabeDetalle(ArrayList<AddendaMabeDetalles> listMabeDetalle) {
        this.listMabeDetalle = listMabeDetalle;
    }

    
    
    



}
