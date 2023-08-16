
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for PostalService complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PostalService"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/PTC.BusinessLayer.Core}Entity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DestOrganizationCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Details" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom}PostalService.ServiceDetails" minOccurs="0"/&gt;
 *         &lt;element name="MailCategoryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MailClassCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OrigOrganizationCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ServiceId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid" minOccurs="0"/&gt;
 *         &lt;element name="ServiceNm" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "PostalService", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", propOrder = {
    "destOrganizationCd",
    "details",
    "mailCategoryCd",
    "mailClassCd",
    "origOrganizationCd",
    "serviceId",
    "serviceNm",
    "validFrom",
    "validTo"
})
public class PostalService
    extends Entity
{

    @XmlElementRef(name = "DestOrganizationCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> destOrganizationCd;
    @XmlElementRef(name = "Details", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<PostalServiceServiceDetails> details;
    @XmlElementRef(name = "MailCategoryCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> mailCategoryCd;
    @XmlElementRef(name = "MailClassCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> mailClassCd;
    @XmlElementRef(name = "OrigOrganizationCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> origOrganizationCd;
    @XmlElementRef(name = "ServiceId", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> serviceId;
    @XmlElementRef(name = "ServiceNm", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<String> serviceNm;
    @XmlElementRef(name = "ValidFrom", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> validFrom;
    @XmlElementRef(name = "ValidTo", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Ecom", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> validTo;

    /**
     * Gets the value of the destOrganizationCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getDestOrganizationCd() {
        return destOrganizationCd;
    }

    /**
     * Sets the value of the destOrganizationCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setDestOrganizationCd(JAXBElement<String> value) {
        this.destOrganizationCd = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link PostalServiceServiceDetails }{@code >}
     *     
     */
    public JAXBElement<PostalServiceServiceDetails> getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link PostalServiceServiceDetails }{@code >}
     *     
     */
    public void setDetails(JAXBElement<PostalServiceServiceDetails> value) {
        this.details = value;
    }

    /**
     * Gets the value of the mailCategoryCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMailCategoryCd() {
        return mailCategoryCd;
    }

    /**
     * Sets the value of the mailCategoryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMailCategoryCd(JAXBElement<String> value) {
        this.mailCategoryCd = value;
    }

    /**
     * Gets the value of the mailClassCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getMailClassCd() {
        return mailClassCd;
    }

    /**
     * Sets the value of the mailClassCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setMailClassCd(JAXBElement<String> value) {
        this.mailClassCd = value;
    }

    /**
     * Gets the value of the origOrganizationCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrigOrganizationCd() {
        return origOrganizationCd;
    }

    /**
     * Sets the value of the origOrganizationCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrigOrganizationCd(JAXBElement<String> value) {
        this.origOrganizationCd = value;
    }

    /**
     * Gets the value of the serviceId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServiceId() {
        return serviceId;
    }

    /**
     * Sets the value of the serviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServiceId(JAXBElement<String> value) {
        this.serviceId = value;
    }

    /**
     * Gets the value of the serviceNm property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServiceNm() {
        return serviceNm;
    }

    /**
     * Sets the value of the serviceNm property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServiceNm(JAXBElement<String> value) {
        this.serviceNm = value;
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
