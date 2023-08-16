
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for MarketedGood complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MarketedGood"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/PTC.BusinessLayer.Core}Entity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Details" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom}MarketedGood.MarketedGoodDetails" minOccurs="0"/&gt;
 *         &lt;element name="ReferenceId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="SellerOrganizationCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ValidFrom" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="ValidTo" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketedGood", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", propOrder = {
    "description",
    "details",
    "referenceId",
    "sellerOrganizationCd",
    "validFrom",
    "validTo"
})
public class MarketedGood
    extends Entity
{

    @XmlElementRef(name = "Description", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> description;
    @XmlElementRef(name = "Details", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<MarketedGoodMarketedGoodDetails> details;
    @XmlElementRef(name = "ReferenceId", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> referenceId;
    @XmlElementRef(name = "SellerOrganizationCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> sellerOrganizationCd;
    @XmlElementRef(name = "ValidFrom", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> validFrom;
    @XmlElementRef(name = "ValidTo", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> validTo;

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDescription(JAXBElement<String> value) {
        this.description = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link MarketedGoodMarketedGoodDetails }{@code >}
     *     
     */
    public JAXBElement<MarketedGoodMarketedGoodDetails> getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link MarketedGoodMarketedGoodDetails }{@code >}
     *     
     */
    public void setDetails(JAXBElement<MarketedGoodMarketedGoodDetails> value) {
        this.details = value;
    }

    /**
     * Gets the value of the referenceId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getReferenceId() {
        return referenceId;
    }

    /**
     * Sets the value of the referenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setReferenceId(JAXBElement<Integer> value) {
        this.referenceId = value;
    }

    /**
     * Gets the value of the sellerOrganizationCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSellerOrganizationCd() {
        return sellerOrganizationCd;
    }

    /**
     * Sets the value of the sellerOrganizationCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSellerOrganizationCd(JAXBElement<String> value) {
        this.sellerOrganizationCd = value;
    }

    /**
     * Gets the value of the validFrom property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getValidFrom() {
        return validFrom;
    }

    /**
     * Sets the value of the validFrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setValidFrom(JAXBElement<XMLGregorianCalendar> value) {
        this.validFrom = value;
    }

    /**
     * Gets the value of the validTo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getValidTo() {
        return validTo;
    }

    /**
     * Sets the value of the validTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setValidTo(JAXBElement<XMLGregorianCalendar> value) {
        this.validTo = value;
    }

}
