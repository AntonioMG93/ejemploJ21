package edifactwsradiopolis.dto;

public class AddendaDetallistaItem {
	private String ean;
	private String sku;
        private String grossPriceAmount;
        private String netPriceAmount;
        private String totalLineAmount;
        private String totalLineAMountNeto;

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getGrossPriceAmount() {
        return grossPriceAmount;
    }

    public void setGrossPriceAmount(String grossPriceAmount) {
        this.grossPriceAmount = grossPriceAmount;
    }

    public String getNetPriceAmount() {
        return netPriceAmount;
    }

    public void setNetPriceAmount(String netPriceAmount) {
        this.netPriceAmount = netPriceAmount;
    }

    public String getTotalLineAmount() {
        return totalLineAmount;
    }

    public void setTotalLineAmount(String totalLineAmount) {
        this.totalLineAmount = totalLineAmount;
    }

    public String getTotalLineAMountNeto() {
        return totalLineAMountNeto;
    }

    public void setTotalLineAMountNeto(String totalLineAMountNeto) {
        this.totalLineAMountNeto = totalLineAMountNeto;
    }
        
	
}
