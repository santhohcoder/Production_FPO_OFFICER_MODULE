
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CDSObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CDSObject"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/PTC.BusinessLayer.Core}Entity"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CDSStateCd" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="CustOrganizationCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MailObjectPId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid" minOccurs="0"/&gt;
 *         &lt;element name="PId" type="{http://schemas.microsoft.com/2003/10/Serialization/}guid" minOccurs="0"/&gt;
 *         &lt;element name="PostOrganizationCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="XMLData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CDSObject", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", propOrder = {
    "cdsStateCd",
    "custOrganizationCd",
    "mailObjectPId",
    "pId",
    "postOrganizationCd",
    "xmlData"
})
@XmlSeeAlso({
    Declaration.class,
    Response.class
})
public class CDSObject
    extends Entity
{

    @XmlElement(name = "CDSStateCd")
    protected Integer cdsStateCd;
    @XmlElementRef(name = "CustOrganizationCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> custOrganizationCd;
    @XmlElement(name = "MailObjectPId")
    protected String mailObjectPId;
    @XmlElementRef(name = "PId", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> pId;
    @XmlElementRef(name = "PostOrganizationCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> postOrganizationCd;
    @XmlElementRef(name = "XMLData", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> xmlData;

    /**
     * Gets the value of the cdsStateCd property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCDSStateCd() {
        return cdsStateCd;
    }

    /**
     * Sets the value of the cdsStateCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCDSStateCd(Integer value) {
        this.cdsStateCd = value;
    }

    /**
     * Gets the value of the custOrganizationCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getCustOrganizationCd() {
        return custOrganizationCd;
    }

    /**
     * Sets the value of the custOrganizationCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setCustOrganizationCd(JAXBElement<String> value) {
        this.custOrganizationCd = value;
    }

    /**
     * Gets the value of the mailObjectPId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMailObjectPId() {
        return mailObjectPId;
    }

    /**
     * Sets the value of the mailObjectPId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMailObjectPId(String value) {
        this.mailObjectPId = value;
    }

    /**
     * Gets the value of the pId property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPId() {
        return pId;
    }

    /**
     * Sets the value of the pId property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPId(JAXBElement<String> value) {
        this.pId = value;
    }

    /**
     * Gets the value of the postOrganizationCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPostOrganizationCd() {
        return postOrganizationCd;
    }

    /**
     * Sets the value of the postOrganizationCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPostOrganizationCd(JAXBElement<String> value) {
        this.postOrganizationCd = value;
    }

    /**
     * Gets the value of the xmlData property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getXMLData() {
        return xmlData;
    }

    /**
     * Sets the value of the xmlData property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setXMLData(JAXBElement<String> value) {
        this.xmlData = value;
    }

}
