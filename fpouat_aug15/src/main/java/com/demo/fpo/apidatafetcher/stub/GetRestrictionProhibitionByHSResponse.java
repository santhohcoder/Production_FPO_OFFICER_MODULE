
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
 *         &lt;element name="GetRestrictionProhibitionByHSResult" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management}ArrayOfRestrictionProhibition" minOccurs="0"/&gt;
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
    "getRestrictionProhibitionByHSResult"
})
@XmlRootElement(name = "GetRestrictionProhibitionByHSResponse")
public class GetRestrictionProhibitionByHSResponse {

    @XmlElementRef(name = "GetRestrictionProhibitionByHSResult", namespace = "http://tempuri.org/", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfRestrictionProhibition> getRestrictionProhibitionByHSResult;

    /**
     * Gets the value of the getRestrictionProhibitionByHSResult property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRestrictionProhibition }{@code >}
     *     
     */
    public JAXBElement<ArrayOfRestrictionProhibition> getGetRestrictionProhibitionByHSResult() {
        return getRestrictionProhibitionByHSResult;
    }

    /**
     * Sets the value of the getRestrictionProhibitionByHSResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfRestrictionProhibition }{@code >}
     *     
     */
    public void setGetRestrictionProhibitionByHSResult(JAXBElement<ArrayOfRestrictionProhibition> value) {
        this.getRestrictionProhibitionByHSResult = value;
    }

}
