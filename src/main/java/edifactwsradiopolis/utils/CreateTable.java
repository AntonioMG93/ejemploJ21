package edifactwsradiopolis.utils;

import java.awt.Color;

import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;

public class CreateTable {
	private static final int BLUE_O = 0x0482bd;
	private static Color cblueo = new Color(BLUE_O);
	private static Font blueoBold8 = new Font(Font.HELVETICA, 8, Font.BOLD, cblueo);
	private static Font bfBoldFooter = new Font(Font.HELVETICA, 8, Font.BOLD, new Color(0, 0, 0)); 
	public PdfPTable generaTabla(String []datos, int columnas, int filas, int ancho)
	{
		/**
    	 * Tabla footer datos de sello y qrcode
    	 */
		int filas_totales=columnas*filas;
        PdfPTable table = new PdfPTable(columnas);
        table.setTotalWidth(ancho);
        
        PdfPCell cell = new PdfPCell();
        for(int i=0; i<(columnas*filas); i++)
        {
        	if (i%2==0)
        	{
        		Font smallfont = new Font(Font.HELVETICA,  8, Font.BOLD);        		
        		cell = new PdfPCell(new Phrase(datos[i],bfBoldFooter));
        		//cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        		
        	}
        	else
        	{
        		Font smallfont = new Font(Font.HELVETICA,  8, Font.NORMAL);
        		cell = new PdfPCell(new Phrase(datos[i],smallfont));
        		//cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        	}
        	//cell.setBackgroundColor(Color.LIGHT_GRAY);
        	//cell.setColspan(filas);
        	cell.setBorder(Rectangle.NO_BORDER);
        	table.addCell(cell);
        }
        
        return table;
	}
}

