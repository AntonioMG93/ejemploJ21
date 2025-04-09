/**
 * RespuestaTimbrado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package edifactwsradiopolis.timbra;

public class RespuestaTimbrado  implements java.io.Serializable {
    private java.lang.String documentoTimbrado;

    private java.lang.String codigoResultado;

    private java.lang.String codigoDescripcion;

    public RespuestaTimbrado() {
    }

    public RespuestaTimbrado(
           java.lang.String documentoTimbrado,
           java.lang.String codigoResultado,
           java.lang.String codigoDescripcion) {
           this.documentoTimbrado = documentoTimbrado;
           this.codigoResultado = codigoResultado;
           this.codigoDescripcion = codigoDescripcion;
    }


    /**
     * Gets the documentoTimbrado value for this RespuestaTimbrado.
     * 
     * @return documentoTimbrado
     */
    public java.lang.String getDocumentoTimbrado() {
        return documentoTimbrado;
    }


    /**
     * Sets the documentoTimbrado value for this RespuestaTimbrado.
     * 
     * @param documentoTimbrado
     */
    public void setDocumentoTimbrado(java.lang.String documentoTimbrado) {
        this.documentoTimbrado = documentoTimbrado;
    }


    /**
     * Gets the codigoResultado value for this RespuestaTimbrado.
     * 
     * @return codigoResultado
     */
    public java.lang.String getCodigoResultado() {
        return codigoResultado;
    }


    /**
     * Sets the codigoResultado value for this RespuestaTimbrado.
     * 
     * @param codigoResultado
     */
    public void setCodigoResultado(java.lang.String codigoResultado) {
        this.codigoResultado = codigoResultado;
    }


    /**
     * Gets the codigoDescripcion value for this RespuestaTimbrado.
     * 
     * @return codigoDescripcion
     */
    public java.lang.String getCodigoDescripcion() {
        return codigoDescripcion;
    }


    /**
     * Sets the codigoDescripcion value for this RespuestaTimbrado.
     * 
     * @param codigoDescripcion
     */
    public void setCodigoDescripcion(java.lang.String codigoDescripcion) {
        this.codigoDescripcion = codigoDescripcion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaTimbrado)) return false;
        RespuestaTimbrado other = (RespuestaTimbrado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentoTimbrado==null && other.getDocumentoTimbrado()==null) || 
             (this.documentoTimbrado!=null &&
              this.documentoTimbrado.equals(other.getDocumentoTimbrado()))) &&
            ((this.codigoResultado==null && other.getCodigoResultado()==null) || 
             (this.codigoResultado!=null &&
              this.codigoResultado.equals(other.getCodigoResultado()))) &&
            ((this.codigoDescripcion==null && other.getCodigoDescripcion()==null) || 
             (this.codigoDescripcion!=null &&
              this.codigoDescripcion.equals(other.getCodigoDescripcion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDocumentoTimbrado() != null) {
            _hashCode += getDocumentoTimbrado().hashCode();
        }
        if (getCodigoResultado() != null) {
            _hashCode += getCodigoResultado().hashCode();
        }
        if (getCodigoDescripcion() != null) {
            _hashCode += getCodigoDescripcion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaTimbrado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:respuestaTimbrado", "respuestaTimbrado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoTimbrado");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:respuestaTimbrado", "documentoTimbrado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoResultado");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:respuestaTimbrado", "codigoResultado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoDescripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("urn:respuestaTimbrado", "codigoDescripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
