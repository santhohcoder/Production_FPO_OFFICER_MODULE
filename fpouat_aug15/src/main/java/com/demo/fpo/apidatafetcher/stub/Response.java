
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Response complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Response"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}CDSObject"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Data" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}Response.ResponseData" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Response", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", propOrder = {
    "data"
})
public class Response
    extends CDSObject
{

    @XmlElementRef(name = "Data", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<ResponseResponseData> data;

    /**
     * Gets the value of the data property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ResponseResponseData }{@code >}
     *     
     */
    public JAXBElement<ResponseResponseData> getData() {
        return data;
    }

    /**
     * Sets the value of the data property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ResponseResponseData }{@code >}
     *     
     */
    public void setData(JAXBElement<ResponseResponseData> value) {
        this.data = value;
    }

}
