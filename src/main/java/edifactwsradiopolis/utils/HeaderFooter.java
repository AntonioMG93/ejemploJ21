package edifactwsradiopolis.utils;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;


public class HeaderFooter extends PdfPageEventHelper {
		private static final int BLACK = 0xFF000000;
	    private static final int WHITE = 0xFFFFFFFF;
	    
	    PdfPTable table_header = null;
	    PdfPTable table_footer = null;
	    
            public HeaderFooter(PdfPTable table_footer_, PdfPTable table_header_)
//	    public HeaderFooter( PdfPTable table_header_)
	    {
	    	table_footer = table_footer_;
	    	table_header = table_header_;
	    }

	    public void onEndPage (PdfWriter writer, Document document) {
	        
	        
	        switch(writer.getPageNumber() % 2) {
	            case 0:
	                /*ColumnText.showTextAligned(writer.getDirectContent(),
	                    Element.ALIGN_RIGHT, new Phrase("even header"),
	                    rect.getRight(), rect.getTop(), 0);*/
	                break;
	            case 1:
	                /*ColumnText.showTextAligned(writer.getDirectContent(),
	                    Element.ALIGN_LEFT, new Phrase("odd header"),
	                    rect.getLeft(), rect.getTop(), 0);*/
	                break;
	        }
	        
	        try
	        {
	        	table_header.writeSelectedRows(0, -1, 36, 840, writer.getDirectContent());
	        	table_footer.writeSelectedRows(0, -1, 36, 200, writer.getDirectContent());
	        }
	        catch(Exception e){
	        		System.out.println(e.getMessage());
	        }
	        
	        /*ColumnText.showTextAligned(writer.getDirectContent(),
	                Element.ALIGN_CENTER, new Phrase(table_footer),
	                (rect.getLeft() + rect.getRight()) / 2, rect.getBottom() - 18, 0);*/
	    }


}
