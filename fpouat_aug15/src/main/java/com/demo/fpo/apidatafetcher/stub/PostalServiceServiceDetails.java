
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PostalService.ServiceDetails complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PostalService.ServiceDetails"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DestCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DestZoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MailSubclasses" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/&gt;
 *         &lt;element name="Options" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom}PostalService.ServiceDetails.ServiceOptions" minOccurs="0"/&gt;
 *         &lt;element name="OrigCountry" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OrigZoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PhysicalLimits" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom}PostalService.ServiceDetails.ServicePhysicalLimits" minOccurs="0"/&gt;
 *         &lt;element name="Rates" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom}ArrayOfPostalService.ServiceDetails.ServiceRate" minOccurs="0"/&gt;
 *         &lt;element name="Standards" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom}ArrayOfPostalService.ServiceDetails.ServiceStandards" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PostalService.ServiceDetails", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", propOrder = {
    "destCountry",
    "destZoneNumber",
    "mailSubclasses",
    "options",
    "origCountry",
    "origZoneNumber",
    "physicalLimits",
    "rates",
    "standards"
})
public class PostalServiceServiceDetails {

    @XmlElementRef(name = "DestCountry", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> destCountry;
    @XmlElementRef(name = "DestZoneNumber", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> destZoneNumber;
    @XmlElementRef(name = "MailSubclasses", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> mailSubclasses;
    @XmlElementRef(name = "Options", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<PostalServiceServiceDetailsServiceOptions> options;
    @XmlElementRef(name = "OrigCountry", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> origCountry;
    @XmlElementRef(name = "OrigZoneNumber", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> origZoneNumber;
    @XmlElementRef(name = "PhysicalLimits", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<PostalServiceServiceDetailsServicePhysicalLimits> physicalLimits;
    @XmlElementRef(name = "Rates", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfPostalServiceServiceDetailsServiceRate> rates;
    @XmlElementRef(name = "Standards", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfPostalServiceServiceDetailsServiceStandards> standards;

    /**
     * Gets the value of the destCountry property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDestCountry() {
        return destCountry;
    }

    /**
     * Sets the value of the destCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDestCountry(JAXBElement<String> value) {
        this.destCountry = value;
    }

    /**
     * Gets the value of the destZoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDestZoneNumber() {
        return destZoneNumber;
    }

    /**
     * Sets the value of the destZoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDestZoneNumber(JAXBElement<String> value) {
        this.destZoneNumber = value;
    }

    /**
     * Gets the value of the mailSubclasses property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getMailSubclasses() {
        return mailSubclasses;
    }

    /**
     * Sets the value of the mailSubclasses property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setMailSubclasses(JAXBElement<ArrayOfstring> value) {
        this.mailSubclasses = value;
    }

    /**
     * Gets the value of the options property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServiceOptions }{@code >}
     *     
     */
    public JAXBElement<PostalServiceServiceDetailsServiceOptions> getOptions() {
        return options;
    }

    /**
     * Sets the value of the options property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServiceOptions }{@code >}
     *     
     */
    public void setOptions(JAXBElement<PostalServiceServiceDetailsServiceOptions> value) {
        this.options = value;
    }

    /**
     * Gets the value of the origCountry property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrigCountry() {
        return origCountry;
    }

    /**
     * Sets the value of the origCountry property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrigCountry(JAXBElement<String> value) {
        this.origCountry = value;
    }

    /**
     * Gets the value of the origZoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrigZoneNumber() {
        return origZoneNumber;
    }

    /**
     * Sets the value of the origZoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrigZoneNumber(JAXBElement<String> value) {
        this.origZoneNumber = value;
    }

    /**
     * Gets the value of the physicalLimits property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServicePhysicalLimits }{@code >}
     *     
     */
    public JAXBElement<PostalServiceServiceDetailsServicePhysicalLimits> getPhysicalLimits() {
        return physicalLimits;
    }

    /**
     * Sets the value of the physicalLimits property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PostalServiceServiceDetailsServicePhysicalLimits }{@code >}
     *     
     */
    public void setPhysicalLimits(JAXBElement<PostalServiceServiceDetailsServicePhysicalLimits> value) {
        this.physicalLimits = value;
    }

    /**
     * Gets the value of the rates property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceRate }{@code >}
     *     
     */
    public JAXBElement<ArrayOfPostalServiceServiceDetailsServiceRate> getRates() {
        return rates;
    }

    /**
     * Sets the value of the rates property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceRate }{@code >}
     *     
     */
    public void setRates(JAXBElement<ArrayOfPostalServiceServiceDetailsServiceRate> value) {
        this.rates = value;
    }

    /**
     * Gets the value of the standards property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceStandards }{@code >}
     *     
     */
    public JAXBElement<ArrayOfPostalServiceServiceDetailsServiceStandards> getStandards() {
        return standards;
    }

    /**
     * Sets the value of the standards property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfPostalServiceServiceDetailsServiceStandards }{@code >}
     *     
     */
    public void setStandards(JAXBElement<ArrayOfPostalServiceServiceDetailsServiceStandards> value) {
        this.standards = value;
    }

}
