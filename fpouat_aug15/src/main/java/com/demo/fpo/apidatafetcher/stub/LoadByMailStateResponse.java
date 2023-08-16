
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="LoadByMailStateResult" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}ArrayOfCDSView" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "loadByMailStateResult"
})
@XmlRootElement(name = "LoadByMailStateResponse")
public class LoadByMailStateResponse {

    @XmlElementRef(name = "LoadByMailStateResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfCDSView> loadByMailStateResult;

    /**
     * Gets the value of the loadByMailStateResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     *     
     */
    public JAXBElement<ArrayOfCDSView> getLoadByMailStateResult() {
        return loadByMailStateResult;
    }

    /**
     * Sets the value of the loadByMailStateResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfCDSView }{@code >}
     *     
     */
    public void setLoadByMailStateResult(JAXBElement<ArrayOfCDSView> value) {
        this.loadByMailStateResult = value;
    }

}
