package edifactwsradiopolis.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Locale;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
//import com.itextpdf.text.Font.FontFamily;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfGState;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import edifactwsradiopolis.dao.QueryAdministrador;
import edifactwsradiopolis.dto.BeanGeneral;
import edifactwsradiopolis.dto.ComprobanteParseDto;

public class PDFMaker_Timbrado  extends Thread {
	//private static PdfWriter pdfWriter = null;
		private static final int BLACK = 0xFF000000;
	    private static final int WHITE = 0xFFFFFFFF;
	    private static final int RED = 0xFF000000;
	    private static final Font bfBold7 = new Font(Font.HELVETICA, 7, Font.BOLD, new Color(0, 0, 0));
	    private static final Font wfBold8 = new Font(Font.HELVETICA, 8, Font.BOLD, new Color(0, 0, 0));
	    private static final Font bf8 = new Font(Font.HELVETICA, 8); 
	    private static final Font bf7 = new Font(Font.HELVETICA, 7);
	    private static final Font wfNota = new Font(Font.HELVETICA, 8, Font.BOLD, new Color(0, 0, 0));
	    private static Image logowarner=null;
	    private  ComprobanteParseDto detalleFact = new ComprobanteParseDto();
	    //private  Color cgris = new Color(192, 18, 18, 100);
	    private final  Color cgris = new Color(217, 217, 217, 100);
	    private String signoMoneda="";
	    private final QueryAdministrador admin=new QueryAdministrador();
	   
	    public String createPDF (String pathFilePDF, ComprobanteParseDto detalleF, BeanGeneral compr)throws Exception{
	    	  //datosFiscales=df;
	    	//Thread.sleep(2000);
	    	 // comprobanteG = comp;
	    	  //emisorDto=emiDto; 
	    	  //params=parametros;
	    	  detalleFact=detalleF;
	    	  //listDetalleFact=detalleF.getlist

			  //Document doc = new Document(PageSize.A4, 21, 21, 200, 285);
	    	  //Document doc = new Document(PageSize.A4, 21, 15, 195, 275);
	    	  
	    	  //Se valida el tipo de formato YC2, para la posicion del body
	    	  Document doc =null;
//	    	  if(detalleFact.getFormatoPDF().equals("YC2")){
	    		  doc = new Document(PageSize.A4, 22, 22, 228, 220);
//	    	  }else{
//	    		  doc = new Document(PageSize.A4, 22, 22, 295, 275);
//	    		  
//    		  }
			  PdfWriter docWriter = null;		
			  //DecimalFormat df = new DecimalFormat("0.00");
			 
			  //try {
			   
			   //special font sizes
			    
			  //Asignaci�n, signo dependiendo de la moneda.
			  if(compr.getComprobante().getMoneda().toString().equals("MXN")){
				  signoMoneda="$";
			  }
			  if(compr.getComprobante().getMoneda().toString().equals("USD")){
				  signoMoneda="USD";
			  }
			  if(compr.getComprobante().getMoneda().toString().equals("EUR")){
				  signoMoneda="EUR";
			  }
			   
			   
				   String completePath=pathFilePDF;
				   String nombrepdf="";
				   //Validación para la generación, de la proforma.
//				   if(detalleF.getFormatoPDF().equals("YC7")){
//					   nombrepdf=detalleF.getSerie()+detalleF.getFolio();
//				   }else{
//					   nombrepdf = detalleF.getUUID();
//				   }
                                            if (compr.getComprobante().getSerie()!=null) {
                                                nombrepdf = compr.getComprobante().getSerie()+compr.getComprobante().getFolio();
                                            } else {
                                                nombrepdf = compr.getComprobante().getFolio();
                                            }
				  
				   docWriter = PdfWriter.getInstance(doc , new FileOutputStream(Utils.getParamValue("path_cfdi_make") +nombrepdf+".pdf"));
				   docWriter.setLinearPageMode();
				   docWriter.setFullCompression();
				   //docWriter.setBoxSize("art", new Rectangle(36, 54, 0, 788));
				   
				   //document header attributes
				   doc.addAuthor("Edifact MX");
				   doc.addCreationDate();
				   doc.addProducer() ;
				   doc.addCreator("Antonio Morales");
				   doc.addTitle("Comprobante Fiscal Digital por Internet");
				   //doc.setPageSize(PageSize.LETTER);
				   
				   //open document
				   doc.open();
				 
				   
				   
				   //insert column headings
				   /*insertCell(tablaDetalle, "CANTIDAD", Element.ALIGN_RIGHT, 1, bfBold12);
				   insertCell(tablaDetalle, "UNIDAD", Element.ALIGN_LEFT, 1, bfBold12);
				   insertCell(tablaDetalle, "DESCRIPCION", Element.ALIGN_LEFT, 1, bfBold12);
				   insertCell(tablaDetalle, "IMPORTE", Element.ALIGN_RIGHT, 1, bfBold12);
				   tablaDetalle.setHeaderRows(1);*/
					    
			    
				 
				   //add the PDF table to the paragraph 
				   //paragraph.add(table);
				   // add the paragraph to the document
//                                 HeaderFooter event = new HeaderFooter(creteTableFooter(detalleFact),creteTableHeader(detalleFact));
				   HeaderFooter event = new HeaderFooter(creteTableFooter(detalleFact,compr),creteTableHeader(detalleFact,compr));
				   docWriter.setPageEvent(event);
				   //doc.add(createInfoFiscal(comp));
				   doc.add(createDetalleTable(detalleF,compr));

			   if (doc != null){
			    //close the document
			    doc.close();
			   }
			   if (docWriter != null){
			    //close the writer
			    docWriter.close();
			   }
			   
			   //Asignacion, del conteo de paginas.
			   manipulatePdf(Utils.getParamValue("path_cfdi_make")+nombrepdf+".pdf",completePath+nombrepdf+" .pdf");
			  String pdfbase64="";
			   File originalFile = new File(completePath+nombrepdf+" .pdf");
	            FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
	            byte[] bytes = new byte[(int)originalFile.length()];
	            fileInputStreamReader.read(bytes);
	            pdfbase64 = new String(Base64Coder.encode(bytes));
	            
	            return pdfbase64;
			  //}
			 }
		  //Creacion del conteo de paginas.
	    public void manipulatePdf(String src, String dest) throws IOException, DocumentException {
	        PdfReader reader = new PdfReader(src);
	        int n = reader.getNumberOfPages();
	        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
	        PdfContentByte pagecontent;
	        for (int i = 0; i < n; ) {
	            pagecontent = stamper.getOverContent(++i);
	            ColumnText.showTextAligned(pagecontent, Element.ALIGN_RIGHT,//horizontal/vertical
	                    new Phrase(String.format("Página %s de %s", i, n),wfNota), 570,20, 0);
	        }
	        stamper.close();
	        reader.close();
	    }
            
            public void manipulatePdfWatermark(String src, String dest) throws IOException, DocumentException {
//		System.out.println("Archivo origen: " + src);
//		System.out.println("Archivo destino: " + dest);
		PdfReader reader = new PdfReader(src);
		int n = reader.getNumberOfPages();
		PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(dest));
		// text watermark
		Font f = new Font(Font.HELVETICA, 30);
		Phrase p = new Phrase("CANCELADO", f);
		// image watermark
		
		Image img =  Image.getInstance(Utils.getParamValue("path_logo")+"cancelado.png");
		
		//com.itextpdf.text.Image img =  com.itextpdf.text.Image.getInstance("C:\\edifactmx_pavisa\\design\\logo\\cancelado.png");
		
		float w = (float) 346.0;//img.getScaledWidth();
		float h = (float) 546.0;//img.getScaledHeight();
//		System.out.println("W = " + w +" H = "+ h);
		// transparency
		PdfGState gs1 = new PdfGState();
		gs1.setFillOpacity(0.5f);
		// properties
		PdfContentByte over;
		Rectangle pagesize;
		float x, y;
		// loop over every page
		for (int i = 1; i <= n; i++) {
			pagesize = reader.getPageSizeWithRotation(i);
			x = (pagesize.getLeft() + pagesize.getRight()) / 2;
			y = (pagesize.getTop() + pagesize.getBottom()) / 2;
			over = stamper.getOverContent(i);
			over.saveState();
			over.setGState(gs1);
//			if (i % 2 == 1)
//				com.itextpdf.text.pdf.ColumnText.showTextAligned(over, Element.ALIGN_CENTER, p, x, y, 0);
//			else
				over.addImage(img, w, 0, 0, h, x - (w / 2), y - (h / 2));
			over.restoreState();
		}
		stamper.close();
		reader.close();
	}

	 private PdfPTable createDetalleTable(ComprobanteParseDto detalleFactura,BeanGeneral compr) throws Exception
	 {
		//Bloque, Formato numerico Eje. 45,444.00
            NumberFormat nf = NumberFormat.getInstance();
            Locale loc = new Locale("es","MX");
            // Ahora con un locale distinto
    		nf = NumberFormat.getInstance(loc);
	    Font bfBold12 = new Font(Font.TIMES_ROMAN, 12, Font.BOLD); 
	    Font bf12 = new Font(Font.TIMES_ROMAN, 10);
	    //float[] columnWidthsDetalle = {2.5f, 2f, 3f, 5f, 2f,2f};
	    float[] columnWidthsDetalle = {6f, 1.5f, 1.5f, 1.5f, 3f, 2f, 2f};
	    PdfPTable tablaDetalle = new PdfPTable(columnWidthsDetalle);
	    //tablaDetalle.setWidthPercentage(f);
	    PdfPTable tablaBorderDetalle = new PdfPTable(1);
	    tablaBorderDetalle.setWidthPercentage(96f);
	    
	    PdfPCell cellparte = null;
//	    insertCell(tablaDetalle, "Invoice details", Element.ALIGN_LEFT, 6, wfBold8, true);
	    insertCell(tablaDetalle, "Descripción", Element.ALIGN_CENTER, 1, wfBold8, true);
            insertCell(tablaDetalle, "Clave U.M.", Element.ALIGN_CENTER, 1, wfBold8, true);
	    insertCell(tablaDetalle, "U. Medida", Element.ALIGN_CENTER, 1, wfBold8, true);
	    insertCell(tablaDetalle, "Cantidad", Element.ALIGN_CENTER, 1, wfBold8, true);
	    insertCell(tablaDetalle, "Clave Producto", Element.ALIGN_CENTER, 1, wfBold8, true);
	    insertCell(tablaDetalle, "Precio Unidad", Element.ALIGN_CENTER, 1, wfBold8, true);
	    insertCell(tablaDetalle, "Importe", Element.ALIGN_CENTER, 1, wfBold8, true);

	    tablaDetalle.setHeaderRows(1);
	    PdfPTable table = new PdfPTable(4);    
		
	    insertCell(tablaDetalle, "______________________________________________________________________________________________________________________", Element.ALIGN_RIGHT, 7, bf8, false);	
		
		 
		 for(int i=0; i<compr.getComprobante().getConceptos().getConcepto().size(); i++)
		 {	
   
                     insertCell(tablaDetalle, compr.getComprobante().getConceptos().getConcepto().get(i).getDescripcion() , Element.ALIGN_LEFT, 1, bf8, false);
                     insertCell(tablaDetalle, compr.getComprobante().getConceptos().getConcepto().get(i).getClaveUnidad(), Element.ALIGN_CENTER, 1, bf8, false);
                     insertCell(tablaDetalle, compr.getComprobante().getConceptos().getConcepto().get(i).getUnidad(), Element.ALIGN_CENTER, 1, bf8, false);
                     insertCell(tablaDetalle, compr.getComprobante().getConceptos().getConcepto().get(i).getCantidad().toString(), Element.ALIGN_CENTER, 1, bf8, false);
                     insertCell(tablaDetalle, compr.getComprobante().getConceptos().getConcepto().get(i).getNoIdentificacion(), Element.ALIGN_CENTER, 1, bf8, false);
                     insertCell(tablaDetalle,String.format("%,.2f",compr.getComprobante().getConceptos().getConcepto().get(i).getValorUnitario()), Element.ALIGN_CENTER, 1, bf8, false);       
		//Formato, importe.
		     insertCell(tablaDetalle, String.format("%,.2f",compr.getComprobante().getConceptos().getConcepto().get(i).getImporte()), Element.ALIGN_RIGHT, 1, bf8, false);	 
		 }
		 insertCell(tablaDetalle, "______________________________________________________________________________________________________________________", Element.ALIGN_RIGHT, 7, bf8, false);
		 //TOTALES
		 String tmoneda="";
	        
        	if(!(compr.getComprobante().getMoneda().toString()).equals(""))
        	{
        		if(compr.getComprobante().getMoneda().toString().equals("USD"))
        		{
        			tmoneda = "USD";
        		}
                        else if(compr.getComprobante().getMoneda().toString().equals("EUR"))
        		{
        			tmoneda = "EUR";
        		}else{
                           tmoneda = "MXN"; 
                        }
        	}
        	else
        	{
        		tmoneda = "MXN";
        	}
        	String numeroLetra="";

	        	 numeroLetra=ConvierteNumeroLetra.convertNumberToLetter(compr.getComprobante().getTotal().toString(),compr.getComprobante().getMoneda().toString());
        	 
                     float[] columnWidths = {6f, 1f, 2f};
		     PdfPTable table_header_datosfactura = new PdfPTable(columnWidths);
                     
                     float[] columnWidthsRel = {2f, 2f, 2f,6f};
		     PdfPTable table_relacionados = new PdfPTable(columnWidthsRel);
                     
                     float[] columnWidthsTotales = {4f, 4f};
		     PdfPTable table_totales = new PdfPTable(columnWidthsTotales);
                     
                     float[] columnWidthsImpLetra = {6f};
		     PdfPTable table_impLetra = new PdfPTable(columnWidthsImpLetra);
                     
                     float[] columnWidthsFiscal = {1.5f, 2f, 1f, 1.5f, 3.5f};
		     PdfPTable table_fiscal = new PdfPTable(columnWidthsFiscal);
		     
		     PdfPCell cell = null;
                     
                     
                     //Tabla relacionados para NC
                     if (compr.getComprobante().getCfdiRelacionados()!= null) {
                         for (int i = 0; i < compr.getComprobante().getCfdiRelacionados().size(); i++) {
                             
                         
                cell = new PdfPCell(new Phrase("Tipo Relación:",bfBold7));
                cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
                cell.setBackgroundColor(cgris);
	        table_relacionados.addCell(cell);
                cell = new PdfPCell(new Phrase(compr.getComprobante().getCfdiRelacionados().get(i).getTipoRelacion(),bf8));
                cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
	        table_relacionados.addCell(cell);
                cell = new PdfPCell(new Phrase("",bf8));
                cell.setColspan(2);
                cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
                cell.setBorder(Rectangle.NO_BORDER);
	        table_relacionados.addCell(cell);
                cell = new PdfPCell(new Phrase("UUID Relacionado:",bfBold7));
                cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
                cell.setBackgroundColor(cgris);
	        table_relacionados.addCell(cell);
                cell = new PdfPCell(new Phrase(compr.getComprobante().getCfdiRelacionados().get(i).getCfdiRelacionado().get(0).getUUID(),bf8));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
                cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
	        table_relacionados.addCell(cell);
                cell = new PdfPCell(new Phrase("",bf8));
                cell.setBorder(Rectangle.NO_BORDER);
	        table_relacionados.addCell(cell);
                cell = new PdfPCell(new Phrase("",bf8));
                cell.setColspan(4);
                cell.setBorder(Rectangle.NO_BORDER);
	        table_relacionados.addCell(cell);
                
                         }
                     }
                     
                     
                     //Tabla totales
                String subtotal=String.format("%,.2f",compr.getComprobante().getSubTotal());
                cell = new PdfPCell(new Phrase("Subtotal:",bfBold7));
                cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
                cell.setBackgroundColor(cgris);
	        table_totales.addCell(cell);
                cell = new PdfPCell(new Phrase(subtotal,bf8));
                cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
	        table_totales.addCell(cell);
                
                if (compr.getComprobante().getImpuestos() != null) {
                    if (compr.getComprobante().getImpuestos().getTotalImpuestosTrasladados() != null) {
                     String tasa=compr.getComprobante().getImpuestos().getTraslados().getTraslado().get(0).getTasaOCuota().toString();
                    if (tasa.equals("0.160000")){
                        tasa="16%";
                    } else if (tasa.equals("0.080000")){
                        tasa="08%";
                    } else if (tasa.equals("0.000000")){
                        tasa="0%";
                    } else {
                        tasa=compr.getComprobante().getImpuestos().getTraslados().getTraslado().get(0).getTasaOCuota()+"%";
                    }
                   
                cell = new PdfPCell(new Phrase("Impuesto: "+compr.getComprobante().getImpuestos().getTraslados().getTraslado().get(0).getImpuesto()+" - IVA: "+tasa,bfBold7));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
                cell.setBackgroundColor(cgris);
//	        cell.setBorder(Rectangle.NO_BORDER);
	        table_totales.addCell(cell);  
                
                String ivaST=String.format("%,.2f",compr.getComprobante().getImpuestos().getTotalImpuestosTrasladados());
	        cell = new PdfPCell(new Phrase(ivaST,bf8));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
//	        cell.setBorder(Rectangle.NO_BORDER);
	        table_totales.addCell(cell);
                    }
                    
                    if (compr.getComprobante().getImpuestos().getTotalImpuestosRetenidos() != null) {
                   
                cell = new PdfPCell(new Phrase("Retención:",bfBold7));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
                cell.setBackgroundColor(cgris);
//	        cell.setBorder(Rectangle.NO_BORDER);
	        table_totales.addCell(cell);  
                
                String retST=String.format("%,.2f",compr.getComprobante().getImpuestos().getTotalImpuestosRetenidos());
	        cell = new PdfPCell(new Phrase(retST,bf8));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
//	        cell.setBorder(Rectangle.NO_BORDER);
	        table_totales.addCell(cell);
                    }
             } 
                cell = new PdfPCell(new Phrase("Total ",bfBold7));
//	        cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(cgris);
	        cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
	        table_totales.addCell(cell);
	        //Formato, de importe.
                String totalS=String.format("%,.2f",compr.getComprobante().getTotal());
	        //cell = new PdfPCell(new Phrase( String.format(signoMoneda+"%,.2f",totalT),bf8));
	        cell = new PdfPCell(new Phrase(totalS,bf8));
//                cell = new PdfPCell(new Phrase(nf.format(totalT),bf8));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
	        table_totales.addCell(cell);   
                  
                //Tabla importe letra
                cell = new PdfPCell(new Phrase("IMPORTE CON LETRA:",bfBold7));
	        cell.setBorder(Rectangle.LEFT | Rectangle.TOP | Rectangle.RIGHT);
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
	        table_impLetra.addCell(cell);
	        cell = new PdfPCell(new Phrase(numeroLetra,bf8));
	        cell.setBorder(Rectangle.LEFT | Rectangle.BOTTOM | Rectangle.RIGHT);
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
	        table_impLetra.addCell(cell);
                cell = new PdfPCell(new Phrase("",bf8));
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
	        table_impLetra.addCell(cell);
                     
                 //Tabla datos fiscales

                        String fPago="";
                        switch (compr.getComprobante().getFormaPago()) 
                       {
                        case"01":  fPago = "01-Efectivo";
                                 break;
                        case"02":  fPago = "02-Cheque nominativo";
                                 break;
                        case "03":  fPago = "03-Transferencia electrónica de fondos";
                                 break;
                        case "04":  fPago = "04-Tarjeta de crédito";
                                 break;
                        case "05":  fPago = "05-Monedero electrónico";
                                 break;
                        case"06":  fPago = "06-Dinero electrónico";
                                 break;
                        case "08":  fPago = "08-Vales de despensa";
                                 break;
                        case"12":  fPago = "12-Dación en pago";
                                 break;
                        case"13":  fPago = "13-Pago por subrogación";
                                 break;
                        case "14":  fPago = "14-Pago por consignación";
                                 break;
                        case "15":  fPago = "15-Condonación";
                                 break;
                        case "17":  fPago = "17-Compensación";
                                 break;
                        case"23":  fPago = "23-Novación";
                                 break;
                        case"24":  fPago = "24-Confusión";
                                 break;
                        case "25":  fPago = "25-Remisión de deuda";
                                 break;
                        case "26":  fPago = "26-Prescripción o caducidad";
                                 break;
                        case "27":  fPago = "27-A satisfacción del acreedor";
                                 break;
                        case"28":  fPago = "28-Tarjeta de débito";
                                 break;
                        case"29":  fPago = "29-Tarjeta de servicios";
                                 break;
                        case "30":  fPago = "30-Aplicación de anticipos";
                                 break;
                        case "31":  fPago = "31-Intermediario pagos";
                                 break;
                        case "99":  fPago = "99-Por definir";
                                 break;
                        default: fPago = "";
                                 break;
                    }
	        
                        String mPago="";
                        if (compr.getComprobante().getMetodoPago().toString().equalsIgnoreCase("PUE")) {
                            mPago = "PUE-Pago en una sola exhibición";
                        } else {
                            mPago = "PPD-Pago en parcialidades o diferido";
                        }

                 
                 
                 
                cell = new PdfPCell(new Phrase("Certificado SAT",bfBold7));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                cell.setBackgroundColor(cgris);
	        table_fiscal.addCell(cell);
	        cell = new PdfPCell(new Phrase(detalleFactura.getNoCertificadoSAT(),bf8));
                cell.setBorder(Rectangle.NO_BORDER);
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
	        table_fiscal.addCell(cell);
                cell = new PdfPCell(new Phrase("",bf8));
                cell.setBorder(Rectangle.NO_BORDER);
	        table_fiscal.addCell(cell);
                cell = new PdfPCell(new Phrase("Método de pago",bfBold7));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                cell.setBackgroundColor(cgris);
	        table_fiscal.addCell(cell);
	        cell = new PdfPCell(new Phrase(mPago,bf8));
                cell.setBorder(Rectangle.NO_BORDER);
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
	        table_fiscal.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Fecha Timbrado",bfBold7));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                cell.setBackgroundColor(cgris);
	        table_fiscal.addCell(cell);
	        cell = new PdfPCell(new Phrase(detalleFactura.getFechaTimbrado(),bf8));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
	        table_fiscal.addCell(cell);
                cell = new PdfPCell(new Phrase("",bf8));
                cell.setBorder(Rectangle.NO_BORDER);
	        table_fiscal.addCell(cell);
                cell = new PdfPCell(new Phrase("Forma de pago",bfBold7));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                cell.setBackgroundColor(cgris);
	        table_fiscal.addCell(cell);
	        cell = new PdfPCell(new Phrase(fPago,bf8));
                cell.setBorder(Rectangle.NO_BORDER);
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
	        table_fiscal.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Moneda",bfBold7));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                cell.setBackgroundColor(cgris);
	        table_fiscal.addCell(cell);
	        cell = new PdfPCell(new Phrase(compr.getComprobante().getMoneda().toString(),bf8));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
	        table_fiscal.addCell(cell);
                cell = new PdfPCell(new Phrase("",bf8));
                cell.setBorder(Rectangle.NO_BORDER);
	        table_fiscal.addCell(cell);
                cell = new PdfPCell(new Phrase("Tipo de cambio",bfBold7));
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
                cell.setBackgroundColor(cgris);
	        table_fiscal.addCell(cell);
	        cell = new PdfPCell(new Phrase(compr.getComprobante().getTipoCambio().toString(),bf8));
                cell.setBorder(Rectangle.NO_BORDER);
	        cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
	        table_fiscal.addCell(cell);
                
                cell = new PdfPCell(new Phrase("",wfNota));
	        cell.setColspan(5);
                cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER); 
	        table_fiscal.addCell(cell);
                
                cell = new PdfPCell(new Phrase("ESTE DOCUMENTO ES UNA REPRESENTACIÓN IMPRESA DE UN CFDI",wfNota));
	        cell.setColspan(5);
                cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER); 
	        table_fiscal.addCell(cell);
                
                cell = new PdfPCell(new Phrase("EL REGISTRO DE ESTE DOCUMENTO PUEDE SER VERIFICADO EN LA PÁGINA DE INTERNET DEL SAT",bfBold7));
	        cell.setColspan(5);
                cell.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER); 
	        table_fiscal.addCell(cell);
                
		     //TABLA DE datos factura
		cell = new PdfPCell(new Phrase(" ",bf8));
	        cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
	        table_header_datosfactura.addCell(cell);
                if (compr.getComprobante().getCfdiRelacionados()!= null) {
                cell = new PdfPCell(table_relacionados);
                cell.setColspan(3);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_header_datosfactura.addCell(cell);
                }
                
                
                
                
                
	        cell = new PdfPCell(table_impLetra);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_header_datosfactura.addCell(cell);
                
                cell = new PdfPCell(new Phrase(" ",bf8));
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_header_datosfactura.addCell(cell);        
                
                cell = new PdfPCell(table_totales);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_header_datosfactura.addCell(cell);
                
	        cell = new PdfPCell(new Phrase(" ",bf8));
	        cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
	        table_header_datosfactura.addCell(cell);
                
                cell = new PdfPCell(table_fiscal);
                cell.setColspan(3);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_header_datosfactura.addCell(cell);
                
                cell = new PdfPCell(new Phrase(" ",bf8));
	        cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(3);
	        table_header_datosfactura.addCell(cell);
	        
		 cell = new PdfPCell(tablaDetalle);
		 cell.setBorder(Rectangle.NO_BORDER);
		 tablaBorderDetalle.addCell(cell);
                 
                 
                 ///////////////////////////////////////
               //Asignación de los 8 ultimos digitos, del selloCFDI.
				   String varSello=detalleFactura.getSelloCFD();
				   int resta=varSello.length()-8;
				   String resultSello=varSello.substring(resta);
				   if(!(resultSello).equals("")){
					   detalleFactura.setSelloQr(resultSello);
				   }   
                 
                 
                 ByteArrayOutputStream baos = new ByteArrayOutputStream();

	    	BufferedImage bi = null;
			Image image = null;
	    	//try {   		
			
				//bi = createQRCode("?re="+comprobanteG.getEmisor().getRfc()+"&rr="+comprobanteG.getReceptor().getRfc()+"&tt="+comprobanteG.getTotal()+"&id="+detalleFact.getUuid(),"UTF-8", 103, 103);   	
                                bi = createQRCode("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?id="+detalleFactura.getUUID()+"&re="+Utils.nullToString(compr.getComprobante().getEmisor().getRfc())+"&rr="+Utils.nullToString(compr.getComprobante().getReceptor().getRfc())+"&tt="+compr.getComprobante().getTotal()+"&fe="+detalleFactura.getSelloQr(),"UTF-8", 131, 131);
//				bi = createQRCode("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?id="+detalleFactura.getUUID()+"&re="+Utils.nullToString(compr.getComprobante().getEmisor().getRfc())+"&rr="+Utils.nullToString(compr.getComprobante().getReceptor().getRfc())+"&tt="+compr.getComprobante().getTotal()+"&fe="+detalleFactura.getSelloQr()+"","UTF-8", 131, 131);
				ImageIO.write(bi, "png", baos);	
				baos.flush();		
				byte[] imageInByte = baos.toByteArray();    	
				baos.close();		
				image = Image.getInstance(imageInByte);
	    	/*}
	    	catch(Exception e){
	    		System.out.println(e.getMessage());
	    	}*/

	    	String cadena_original="||1.1|"+detalleFactura.getUUID()+"|"+detalleFactura.getFechaTimbrado()+"|EDI101020E99|"+detalleFactura.getSelloCFD()+"|"+detalleFactura.getNoCertificadoSAT()+"||";
	        
	    	String[] datossello = {"Cadena Original del Complemento de Certificación Digital del SAT:",cadena_original,"Sello Digital del CFDI:",detalleFactura.getSelloCFD(), "Sello Digital del SAT:",detalleFactura.getSelloSAT()};
	    	//Rectangle rect = writer.getBoxSize("art");
	    	float[] columnDatosInfo = {7f};
	        PdfPTable table_footer_main = new PdfPTable(columnDatosInfo);
	        
	        table_footer_main.setTotalWidth(523);
	        
	    	float[] columnWidthsF = {2f,5.5f};
	        PdfPTable table_footer = new PdfPTable(columnWidthsF);
	        float[] columnWidthsadress = {3f,3f,2f};
	        PdfPTable table_footeradress = new PdfPTable(columnWidthsadress);
//	        PdfPCell cell=null;
	        
	        PdfPTable table_datos_sello = new CreateTable().generaTabla(datossello, 1, 6, 623);
	      
	        table_datos_sello.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                
                
                
                
                cell=  new PdfPCell(image);
	        //cell.addElement(image);
	        cell.setBorder(Rectangle.NO_BORDER); 
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_footer.addCell(cell);
	        
	        cell = new PdfPCell(table_datos_sello);
	        //cell.setBackgroundColor(Color.LIGHT_GRAY);
	        cell.setBorder(Rectangle.NO_BORDER); 
	        table_footer.addCell(cell);
                String regimen="";
     
	        cell = new PdfPCell(table_footer);
	        //cell.setBackgroundColor(Color.LIGHT_GRAY);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_footer_main.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("",wfNota));
	        //cell.setBackgroundColor(Color.LIGHT_GRAY);
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_footer_main.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("",wfNota));
	        //cell.setBackgroundColor(Color.LIGHT_GRAY);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_footer_main.addCell(cell);
	        Chunk chunk1 =  new  Chunk (Utils.charXMLtoString("")) ; 
	        chunk1.setUnderline( 1.5f, - 1 ) ; 
	        Chunk chunk2 =  new  Chunk (Utils.charXMLtoString("")) ; 
	        chunk2.setUnderline( 1.5f, - 1 ) ; 
	        cell = new PdfPCell(new Phrase(chunk1.toString()+"\n\n"+chunk2.toString(),wfNota));
	        //cell.setBackgroundColor(Color.LIGHT_GRAY);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_footer_main.addCell(cell);
                 
                 
                 
     //////////////////////////////////////////////////////            
                 
                 
		 
		cell = new PdfPCell(table_header_datosfactura);
	        cell.setBorder(Rectangle.NO_BORDER);
	        tablaBorderDetalle.addCell(cell);
                
//                cell = new PdfPCell(table_footer_main);
//	        cell.setBorder(Rectangle.NO_BORDER);
//	        tablaBorderDetalle.addCell(cell);

                 return tablaBorderDetalle;
	 }
	  
	 public void insertCell(PdfPTable tablaDetalle, String text, int align, int colspan, Font font, Boolean borderline){
	  
	  
      //Font smallfont = new Font(Font.HELVETICA,  8, Font.NORMAL);

	  //create a new cell with the specified Text and Font
	  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
	  
	  if(!borderline)
	  {
		 cell.setBorder(Rectangle.NO_BORDER);			 
	  }
	  else
	  {
		  //ff0000
		  cell.setBackgroundColor(cgris);
		  cell.setBorderColor(cgris);
	  }
	  //set the cell alignment
	  cell.setHorizontalAlignment(align);
	  //set the cell column span in case you want to merge two or more cells
	  cell.setColspan(colspan);
	  //in case there is no text and you wan to create an empty row
	  if(text.trim().equalsIgnoreCase("")){
	   cell.setMinimumHeight(10f);
	  }
	  //add the call to the table
	  
	  tablaDetalle.addCell(cell);
	   
	 }
	 
	 
	 private PdfPTable creteTableHeader(ComprobanteParseDto comp,BeanGeneral compr)throws Exception
	 {
		//Bloque, Formato numerico Eje. 45,444.00
            NumberFormat nf = NumberFormat.getInstance();
            Locale loc = new Locale("es","MX");
            // Ahora con un locale distinto
    		nf = NumberFormat.getInstance(loc);
		    
	        PdfPTable table_main = new PdfPTable(1);
	        table_main.setTotalWidth(523);	
                
                float[] columnWidthEmisor = {20f};
	        PdfPTable table_emisor = new PdfPTable(columnWidthEmisor);
                
                float[] columnWidthReceptor = {60f};
	        PdfPTable table_receptor = new PdfPTable(columnWidthReceptor);
                
                float[] columnWidthReceptor3 = {5f,8f};
	        PdfPTable table_receptor3 = new PdfPTable(columnWidthReceptor3);
                
                float[] columnWidthTC = {20f};
	        PdfPTable table_TC = new PdfPTable(columnWidthTC);
	        
	        float[] columnWidthsPAC = {4f,3f,9f};
	        PdfPTable table_Pac = new PdfPTable(columnWidthsPAC);
	        
	        float[] columnWidthleft = {5f};
	        PdfPTable table_left_header = new PdfPTable(columnWidthleft);
	        
	        float[] columnWidthRight = {4f,6f};
	        PdfPTable table_right_header = new PdfPTable(columnWidthRight);
	        
	        float[] columnWidthsTitulo = {40f, 60f, 25f};
	        PdfPTable table_titulo = new PdfPTable(columnWidthsTitulo);
                
	        PdfPCell cell = null;
	        
	        try {
				logowarner = Image.getInstance(Utils.getParamValue("path_logo")+"logoR.png");
				//logowarner = Image.getInstance(EdifactUtils.getParametro("path_logo")+detalleFact.getEmisor().getRfc()+".png");
				logowarner.scaleAbsolute(160f, 50f);
				logowarner.setBorder(0);
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
                                
                //Tabla emmisor
                cell = new PdfPCell(new Phrase(compr.getComprobante().getEmisor().getNombre(),bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_emisor.addCell(cell);
                cell = new PdfPCell(new Phrase("RFC: "+compr.getComprobante().getEmisor().getRfc(),bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_emisor.addCell(cell);
                cell = new PdfPCell(new Phrase("Calzada de tlalpan No 3000 Col. Espartaco",bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_emisor.addCell(cell);
                cell = new PdfPCell(new Phrase("C.P. 04870 Coyoacan CDMX",bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_emisor.addCell(cell);
                cell = new PdfPCell(new Phrase("Tel: "+compr.getEmisor().getTelefono(),bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_emisor.addCell(cell);
                cell = new PdfPCell(new Phrase("CSD: "+comp.getNoCertificado(),bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_emisor.addCell(cell);
                cell = new PdfPCell(new Phrase("Régimen Fiscal: 601 - General de Ley Personas Morales",bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_emisor.addCell(cell);
                
                //tabla tipo comrpobante
                String tco="";
                String tcoD="";
                if (compr.getComprobante().getTipoDeComprobante().toString().equalsIgnoreCase("I")) {
                 tco="FACTURA";
                 tcoD= "I -Ingreso";
             } else if (compr.getComprobante().getTipoDeComprobante().toString().equalsIgnoreCase("E")){
                 tco="NOTA DE CRÉDITO";
                 tcoD="E - Egreso";
             }
                cell = new PdfPCell(new Phrase("\n",bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
//                cell.setBackgroundColor(cgris);
	        table_TC.addCell(cell);
                cell = new PdfPCell(new Phrase(tco,bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//	        cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(cgris);
	        table_TC.addCell(cell);
                cell = new PdfPCell(new Phrase(Utils.nullToString(compr.getComprobante().getSerie())+compr.getComprobante().getFolio(),bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//	        cell.setBorder(Rectangle.NO_BORDER);
	        table_TC.addCell(cell);               
                cell = new PdfPCell(new Phrase("\n\n",bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_TC.addCell(cell);
                cell = new PdfPCell(new Phrase("Tipo de Comprobante",bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(cgris);
//	        cell.setBorder(Rectangle.NO_BORDER);
	        table_TC.addCell(cell);
                cell = new PdfPCell(new Phrase(tcoD,bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
//	        cell.setBorder(Rectangle.NO_BORDER);
	        table_TC.addCell(cell);  
                cell = new PdfPCell(new Phrase("\n\n",bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_TC.addCell(cell);  
                
	        
                
                
                
                
                String uso="";
                        switch (compr.getComprobante().getReceptor().getUsoCFDI().toString()) 
                       {
                        case "G_01":  uso = "G01-Adquisición de mercancias";
                                 break;
                        case "G_02":  uso = "G02-Devoluciones, descuentos o bonificaciones";
                                 break;
                        case "G_03":  uso = "G03-Gastos en general";
                                 break;
                        case "I_01":  uso = "I01-Construcciones";
                                 break;
                        case "I_02":  uso = "I02-Mobilario y equipo de oficina por inversiones";
                                 break;
                        case "I_03":  uso = "I03-Equipo de transporte";
                                 break;
                        case "I_04":  uso = "I04-Equipo de computo y accesorios";
                                 break;
                        case "I_05":  uso = "I05-Dados, troqueles, moldes, matrices y herramental";
                                 break;
                        case "I_06":  uso = "I06-Comunicaciones telefónicas";
                                 break;
                        case "I_07":  uso = "I07-Comunicaciones satelitales";
                                 break;
                        case "I_08":  uso = "I08-Otra maquinaria y equipo";
                                 break;
                        case "D_01":  uso = "D01-Honorarios médicos, dentales y gastos hospitalarios.";
                                 break;
                        case "D_02":  uso = "D02-Gastos médicos por incapacidad o discapacidad";
                                 break;
                        case "D_03":  uso = "D03-Gastos funerales.";
                                 break;
                        case "D_04":  uso = "D04-Donativos";
                                 break;
                        case "D_05":  uso = "D05-Intereses reales efectivamente pagados por créditos hipotecarios (casa habitación).";
                                 break;
                        case "D_06":  uso = "D06-Aportaciones voluntarias al SAR.";
                                 break;
                        case "D_07":  uso = "D07-Primas por seguros de gastos médicos.";
                                 break;
                        case "D_08":  uso = "D08-Gastos de transportación escolar obligatoria.";
                                 break;
                        case "D_09":  uso = "D09-Depósitos en cuentas para el ahorro, primas que tengan como base planes de pensiones.";
                                 break;
                        case "D_10":  uso = "D10-Pagos por servicios educativos (colegiaturas)";
                                 break;
                        case "S_01":  uso = "S01-Sin efectos fiscales";
                                 break;         
                        case "P_01":  uso = "P01-Por definir";
                                 break;
                        default: uso = "";
                                 break;
                    }
                        
                        
                //Tabla receptor

                
                
                cell = new PdfPCell(new Phrase("Cliente: "+compr.getComprobante().getReceptor().getNombre(),bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(2);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_receptor3.addCell(cell);
                cell = new PdfPCell(new Phrase(compr.getReceptor().getCalle()+" "+compr.getReceptor().getNumExterior() + " "+Utils.nullToString(compr.getReceptor().getEdificio()),bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_receptor3.addCell(cell);
                cell = new PdfPCell(new Phrase("Régimen Fiscal: "+ compr.getReceptor().getRegimenFiscalReceptor(),bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_receptor3.addCell(cell);
                cell = new PdfPCell(new Phrase("COL. "+compr.getReceptor().getColonia()+", "+compr.getReceptor().getCiudad(),bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_receptor3.addCell(cell);
                cell = new PdfPCell(new Phrase("UsoCFDI: "+uso,bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_receptor3.addCell(cell);
                cell = new PdfPCell(new Phrase(compr.getReceptor().getEstado()+", "+ compr.getReceptor().getPais(),bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
	        table_receptor3.addCell(cell);
                cell = new PdfPCell(new Phrase("C.P."+compr.getReceptor().getCp(),bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER);
                cell.setColspan(2);
	        table_receptor3.addCell(cell);
                cell = new PdfPCell(new Phrase("RFC: "+compr.getComprobante().getReceptor().getRfc(),bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_receptor3.addCell(cell);
                cell = new PdfPCell(new Phrase("Nro. Reg. Tributario: "+Utils.nullToString(compr.getComprobante().getReceptor().getNumRegIdTrib()),bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_receptor3.addCell(cell);
                cell = new PdfPCell(new Phrase("Lugar de Expedición: "+compr.getComprobante().getLugarExpedicion(),bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(2);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_receptor3.addCell(cell);
                cell = new PdfPCell(new Phrase(" ",bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(2);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_receptor3.addCell(cell);

                //table_receptor Global
                cell = new PdfPCell(new Phrase("Versión CFDI:"+compr.getComprobante().getVersion(),bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_receptor.addCell(cell);
                cell = new PdfPCell(table_receptor3);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
//	        cell.setBorder(Rectangle.NO_BORDER);
	        table_receptor.addCell(cell);
                
                //Table_Pac
                cell = new PdfPCell(new Phrase("PROVEEDOR SERVICIO EMISIÓN (PSECFDI)",bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setColspan(2);                
                cell.setBackgroundColor(cgris);
	        table_Pac.addCell(cell);
                cell = new PdfPCell(new Phrase("",bfBold7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(Rectangle.NO_BORDER);
	        table_Pac.addCell(cell);
                cell = new PdfPCell(new Phrase("NOMBRE: EdiFactMx SA de CV",bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table_Pac.addCell(cell);
                cell = new PdfPCell(new Phrase("RFC: EDI101020E99",bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table_Pac.addCell(cell);
                cell = new PdfPCell(new Phrase("UUID del Timbrado: "+comp.getUUID(),bf7));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        table_Pac.addCell(cell);
                
	        
                
                cell = new PdfPCell(new Phrase(""));
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
	        cell.setColspan(3);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_titulo.addCell(cell);
                cell = new PdfPCell(logowarner);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_titulo.addCell(cell);
	        cell = new PdfPCell(table_emisor);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_titulo.addCell(cell);
	        cell = new PdfPCell(table_TC);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
	        cell.setColspan(5);
	        table_titulo.addCell(cell);
	        cell = new PdfPCell(new Phrase("",bf7));
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        table_titulo.addCell(cell);
                cell = new PdfPCell(table_receptor);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        table_titulo.addCell(cell);
                cell = new PdfPCell(table_Pac);
	        cell.setBorder(Rectangle.NO_BORDER);
	        cell.setColspan(3);
	        table_titulo.addCell(cell);
        
	        cell = new PdfPCell(table_titulo);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_main.addCell(cell);
	        
//	        cell = new PdfPCell(table_split_header);
//	        cell.setBorder(Rectangle.NO_BORDER);
//	        table_main.addCell(cell);

	        
	        return table_main;
	 }
	 
	 
	 private PdfPTable creteTableFooter(ComprobanteParseDto comp,BeanGeneral compr)throws Exception
	 {
		 
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

	    	BufferedImage bi = null;
			Image image = null;
	    	//try {   		
			
				//bi = createQRCode("?re="+comprobanteG.getEmisor().getRfc()+"&rr="+comprobanteG.getReceptor().getRfc()+"&tt="+comprobanteG.getTotal()+"&id="+detalleFact.getUuid(),"UTF-8", 103, 103);   	
				bi = createQRCode("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?id="+comp.getUUID()+"&re="+Utils.nullToString(compr.getComprobante().getEmisor().getRfc())+"&rr="+Utils.nullToString(compr.getComprobante().getReceptor().getRfc())+"&tt="+compr.getComprobante().getTotal()+"&fe="+comp.getSelloQr()+"","UTF-8", 131, 131);
				ImageIO.write(bi, "png", baos);	
				baos.flush();		
				byte[] imageInByte = baos.toByteArray();    	
				baos.close();		
				image = Image.getInstance(imageInByte);
	    	/*}
	    	catch(Exception e){
	    		System.out.println(e.getMessage());
	    	}*/

	    	String cadena_original="||1.1|"+comp.getUUID()+"|"+comp.getFechaTimbrado()+"|EDI101020E99|"+comp.getSelloCFD()+"|"+comp.getNoCertificadoSAT()+"||";
	        
	    	String[] datossello = {"Cadena Original del Complemento de Certificación Digital del SAT:",cadena_original,"Sello Digital del CFDI:",comp.getSelloCFD(), "Sello Digital del SAT:",comp.getSelloSAT()};
	    	//Rectangle rect = writer.getBoxSize("art");
	    	float[] columnDatosInfo = {7f};
	        PdfPTable table_footer_main = new PdfPTable(columnDatosInfo);
	        
	        table_footer_main.setTotalWidth(523);
	        
	    	float[] columnWidthsF = {2f,5.5f};
	        PdfPTable table_footer = new PdfPTable(columnWidthsF);
	        float[] columnWidthsadress = {3f,3f,2f};
	        PdfPTable table_footeradress = new PdfPTable(columnWidthsadress);
	        PdfPCell cell=null;
	        
	        PdfPTable table_datos_sello = new CreateTable().generaTabla(datossello, 1, 6, 623);
	      
	        table_datos_sello.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
                
                cell=  new PdfPCell(image);
	        //cell.addElement(image);
	        cell.setBorder(Rectangle.NO_BORDER); 
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        table_footer.addCell(cell);
	        
	        cell = new PdfPCell(table_datos_sello);
	        //cell.setBackgroundColor(Color.LIGHT_GRAY);
	        cell.setBorder(Rectangle.NO_BORDER); 
	        table_footer.addCell(cell);
                String regimen="";
     
	        cell = new PdfPCell(table_footer);
	        //cell.setBackgroundColor(Color.LIGHT_GRAY);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_footer_main.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("",wfNota));
	        //cell.setBackgroundColor(Color.LIGHT_GRAY);
	        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_footer_main.addCell(cell);
	        
	        cell = new PdfPCell(new Phrase("",wfNota));
	        //cell.setBackgroundColor(Color.LIGHT_GRAY);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_footer_main.addCell(cell);
	        Chunk chunk1 =  new  Chunk (Utils.charXMLtoString("")) ; 
	        chunk1.setUnderline( 1.5f, - 1 ) ; 
	        Chunk chunk2 =  new  Chunk (Utils.charXMLtoString("")) ; 
	        chunk2.setUnderline( 1.5f, - 1 ) ; 
	        cell = new PdfPCell(new Phrase(chunk1.toString()+"\n\n"+chunk2.toString(),wfNota));
	        //cell.setBackgroundColor(Color.LIGHT_GRAY);
	        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell.setBorder(Rectangle.NO_BORDER);
	        table_footer_main.addCell(cell);

	        return table_footer_main;
	 }
	 
	 private BufferedImage createQRCode(String qrCodeData, String charset,
	            int qrCodeheight, int qrCodewidth) throws Exception,
	            IOException, com.google.zxing.WriterException {
	        // Generate BitMatrix
	        BitMatrix matrix = new MultiFormatWriter()
	                .encode(new String(qrCodeData.getBytes(charset), charset),
	                        BarcodeFormat.QR_CODE, qrCodewidth, qrCodeheight);
	        int width = matrix.getWidth();
	        int height = matrix.getHeight();
	        // Converting BitMatrix to Buffered Image
	        BufferedImage image = new BufferedImage(width, height,
	                BufferedImage.TYPE_INT_ARGB);
	        for (int x = 0; x < width; x++) {
	            for (int y = 0; y < height; y++) {
	                image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
	            }
	        }
//	        System.out.println("qr code image was generated for "+qrCodeData );
	        return image;
	    }

	    public class Watermark extends PdfPageEventHelper {
	    	PdfTemplate totalT;
	        String header;  
	    	
		        
		        public void onOpenDocument(PdfWriter writer, Document document) {
		            totalT = writer.getDirectContent().createTemplate(20, 16);
		        }
		        public void onCloseDocument(PdfWriter writer, Document document) {

		            ColumnText.showTextAligned(totalT, Element.ALIGN_TOP,
		                    new Phrase(String.valueOf(writer.getPageNumber() - 1)),
		                    2, 2, 0);
		        }
	 
	        protected Phrase watermark = new Phrase("");        
	        
	        @Override
	        public void onEndPage(PdfWriter writer, Document document) {
	            try {
	                PdfContentByte canvas = writer.getDirectContentUnder();
	                ColumnText.showTextAligned(canvas, Element.ALIGN_CENTER, watermark, 298, 421, 45);

	                PdfPTable table = new PdfPTable(2);
	                PdfPTable tableNumber = new PdfPTable(1);

	                table.setWidths(new int[]{24, 24});
	                tableNumber.setWidths(new int[]{24});
	                table.setTotalWidth(527);
	                tableNumber.setTotalWidth(527);
	                table.setLockedWidth(true);
	                tableNumber.setLockedWidth(true);
	                table.getDefaultCell().setFixedHeight(20);
	                tableNumber.getDefaultCell().setFixedHeight(20);
	                table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
	                table.addCell(header);
	                table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	                tableNumber.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	                table.addCell(new Phrase(String.format("Página %d de", writer.getPageNumber())));
	                /*PdfPCell cell = new PdfPCell(Image.getInstance(totalT));
	                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	                cell.disableBorderSide(Rectangle.BOX);
	                tableNumber.addCell(cell);*/
	                
	                table.writeSelectedRows(0, 23, -220, 32, writer.getDirectContent());
	                tableNumber.writeSelectedRows(0, 80, -202, 36.0f, writer.getDirectContent());

	            } catch (Exception e) {
	                e.printStackTrace();
	            }

	        }

	       
	    }
	    
	    
	        public class Cabecero extends PdfPageEventHelper {
	        
	        PdfTemplate totalT;
	        String header; 
	        private PdfPTable general;
	        
	        public void setGeneral(PdfPTable gen){
	            this.general = gen;
	        }

	        public void onStartPage(PdfWriter writer, Document document) {
	            try{
	                PdfPTable tableGlobal = new PdfPTable(1);
	                tableGlobal.addCell(general);
	                ColumnText column = new ColumnText(writer.getDirectContent());
	                column.addElement(tableGlobal);
	                column.setSimpleColumn(-40, 830, 625, 10);
	                column.go();
	            }catch(Exception e){
	                e.printStackTrace();
	            }
	        }

	    }
    
}

