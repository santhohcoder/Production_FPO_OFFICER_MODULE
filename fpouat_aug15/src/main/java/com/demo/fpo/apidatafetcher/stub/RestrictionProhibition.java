
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RestrictionProhibition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RestrictionProhibition"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/PTC.BusinessLayer.Core}Entity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="AdditionalDocFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FromHSCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="HSCodeList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="LanguageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OrganizationCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RestrictedCountries" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RestrictionNote" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ToHSCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="WebsiteInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RestrictionProhibition", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", propOrder = {
    "additionalDocFileName",
    "description",
    "fromHSCode",
    "hsCodeList",
    "languageCd",
    "organizationCd",
    "pCategory",
    "restrictedCountries",
    "restrictionNote",
    "toHSCode",
    "websiteInfo"
})
public class RestrictionProhibition
    extends Entity
{

    @XmlElementRef(name = "AdditionalDocFileName", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", type = JAXBElement.class, required = false)
    protected JAXBElement<String> additionalDocFileName;
    @XmlElementRef(name = "Description", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", type = JAXBElement.class, required = false)
    protected JAXBElement<String> description;
    @XmlElementRef(name = "FromHSCode", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", type = JAXBElement.class, required = false)
    protected JAXBElement<String> fromHSCode;
    @XmlElementRef(name = "HSCodeList", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", type = JAXBElement.class, required = false)
    protected JAXBElement<String> hsCodeList;
    @XmlElementRef(name = "LanguageCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", type = JAXBElement.class, required = false)
    protected JAXBElement<String> languageCd;
    @XmlElementRef(name = "OrganizationCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", type = JAXBElement.class, required = false)
    protected JAXBElement<String> organizationCd;
    @XmlElementRef(name = "PCategory", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pCategory;
    @XmlElementRef(name = "RestrictedCountries", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", type = JAXBElement.class, required = false)
    protected JAXBElement<String> restrictedCountries;
    @XmlElementRef(name = "RestrictionNote", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", type = JAXBElement.class, required = false)
    protected JAXBElement<String> restrictionNote;
    @XmlElementRef(name = "ToHSCode", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", type = JAXBElement.class, required = false)
    protected JAXBElement<String> toHSCode;
    @XmlElementRef(name = "WebsiteInfo", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", type = JAXBElement.class, required = false)
    protected JAXBElement<String> websiteInfo;

    /**
     * Gets the value of the additionalDocFileName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getAdditionalDocFileName() {
        return additionalDocFileName;
    }

    /**
     * Sets the value of the additionalDocFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setAdditionalDocFileName(JAXBElement<String> value) {
        this.additionalDocFileName = value;
    }

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
     * Gets the value of the fromHSCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getFromHSCode() {
        return fromHSCode;
    }

    /**
     * Sets the value of the fromHSCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setFromHSCode(JAXBElement<String> value) {
        this.fromHSCode = value;
    }

    /**
     * Gets the value of the hsCodeList property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHSCodeList() {
        return hsCodeList;
    }

    /**
     * Sets the value of the hsCodeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHSCodeList(JAXBElement<String> value) {
        this.hsCodeList = value;
    }

    /**
     * Gets the value of the languageCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getLanguageCd() {
        return languageCd;
    }

    /**
     * Sets the value of the languageCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setLanguageCd(JAXBElement<String> value) {
        this.languageCd = value;
    }

    /**
     * Gets the value of the organizationCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getOrganizationCd() {
        return organizationCd;
    }

    /**
     * Sets the value of the organizationCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setOrganizationCd(JAXBElement<String> value) {
        this.organizationCd = value;
    }

    /**
     * Gets the value of the pCategory property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPCategory() {
        return pCategory;
    }

    /**
     * Sets the value of the pCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPCategory(JAXBElement<String> value) {
        this.pCategory = value;
    }

    /**
     * Gets the value of the restrictedCountries property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRestrictedCountries() {
        return restrictedCountries;
    }

    /**
     * Sets the value of the restrictedCountries property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRestrictedCountries(JAXBElement<String> value) {
        this.restrictedCountries = value;
    }

    /**
     * Gets the value of the restrictionNote property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRestrictionNote() {
        return restrictionNote;
    }

    /**
     * Sets the value of the restrictionNote property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRestrictionNote(JAXBElement<String> value) {
        this.restrictionNote = value;
    }

    /**
     * Gets the value of the toHSCode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getToHSCode() {
        return toHSCode;
    }

    /**
     * Sets the value of the toHSCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setToHSCode(JAXBElement<String> value) {
        this.toHSCode = value;
    }

    /**
     * Gets the value of the websiteInfo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getWebsiteInfo() {
        return websiteInfo;
    }

    /**
     * Sets the value of the websiteInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setWebsiteInfo(JAXBElement<String> value) {
        this.websiteInfo = value;
    }

}
