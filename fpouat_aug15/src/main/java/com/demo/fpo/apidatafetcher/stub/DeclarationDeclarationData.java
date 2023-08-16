
package com.demo.fpo.apidatafetcher.stub;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for Declaration.DeclarationData complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Declaration.DeclarationData"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ContentPieces" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}ArrayOfDeclaration.DeclarationData.ContentPiece" minOccurs="0"/&gt;
 *         &lt;element name="Documents" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}ArrayOfDeclaration.DeclarationData.Document" minOccurs="0"/&gt;
 *         &lt;element name="ExtraFieldNames" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/&gt;
 *         &lt;element name="ExtraFieldValues" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfstring" minOccurs="0"/&gt;
 *         &lt;element name="GrossWeight" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="HandlingClassCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="InsuredValue" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="InsuredValueCurrencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MaxIndex" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="NatureTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PackageImageLocalPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PackageImageSourceInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Postage" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="PostageCurrencyCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientAddressLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientAddressLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientCountryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientFax" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientIdRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecipientZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderAddressLine1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderAddressLine2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderCity" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderCountryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderEmail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderFirstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderIdRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderLastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderTelephone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SenderZIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TransportDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="TransportMode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Declaration.DeclarationData", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", propOrder = {
    "contentPieces",
    "documents",
    "extraFieldNames",
    "extraFieldValues",
    "grossWeight",
    "handlingClassCd",
    "insuredValue",
    "insuredValueCurrencyCd",
    "maxIndex",
    "natureTypeCd",
    "packageImageLocalPath",
    "packageImageSourceInfo",
    "postage",
    "postageCurrencyCd",
    "recipientAddressLine1",
    "recipientAddressLine2",
    "recipientCity",
    "recipientCountryCd",
    "recipientEmail",
    "recipientFax",
    "recipientFirstName",
    "recipientIdRef",
    "recipientLastName",
    "recipientName",
    "recipientState",
    "recipientTelephone",
    "recipientZIP",
    "senderAddressLine1",
    "senderAddressLine2",
    "senderCity",
    "senderCountryCd",
    "senderEmail",
    "senderFirstName",
    "senderIdRef",
    "senderLastName",
    "senderName",
    "senderState",
    "senderTelephone",
    "senderZIP",
    "transportDate",
    "transportMode"
})
public class DeclarationDeclarationData {

    @XmlElementRef(name = "ContentPieces", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfDeclarationDeclarationDataContentPiece> contentPieces;
    @XmlElementRef(name = "Documents", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfDeclarationDeclarationDataDocument> documents;
    @XmlElementRef(name = "ExtraFieldNames", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> extraFieldNames;
    @XmlElementRef(name = "ExtraFieldValues", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<ArrayOfstring> extraFieldValues;
    @XmlElementRef(name = "GrossWeight", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> grossWeight;
    @XmlElementRef(name = "HandlingClassCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> handlingClassCd;
    @XmlElementRef(name = "InsuredValue", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> insuredValue;
    @XmlElementRef(name = "InsuredValueCurrencyCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> insuredValueCurrencyCd;
    @XmlElementRef(name = "MaxIndex", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<Integer> maxIndex;
    @XmlElementRef(name = "NatureTypeCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> natureTypeCd;
    @XmlElementRef(name = "PackageImageLocalPath", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> packageImageLocalPath;
    @XmlElementRef(name = "PackageImageSourceInfo", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> packageImageSourceInfo;
    @XmlElementRef(name = "Postage", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<BigDecimal> postage;
    @XmlElementRef(name = "PostageCurrencyCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> postageCurrencyCd;
    @XmlElementRef(name = "RecipientAddressLine1", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientAddressLine1;
    @XmlElementRef(name = "RecipientAddressLine2", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientAddressLine2;
    @XmlElementRef(name = "RecipientCity", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientCity;
    @XmlElementRef(name = "RecipientCountryCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientCountryCd;
    @XmlElementRef(name = "RecipientEmail", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientEmail;
    @XmlElementRef(name = "RecipientFax", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientFax;
    @XmlElementRef(name = "RecipientFirstName", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientFirstName;
    @XmlElementRef(name = "RecipientIdRef", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientIdRef;
    @XmlElementRef(name = "RecipientLastName", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientLastName;
    @XmlElementRef(name = "RecipientName", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientName;
    @XmlElementRef(name = "RecipientState", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientState;
    @XmlElementRef(name = "RecipientTelephone", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientTelephone;
    @XmlElementRef(name = "RecipientZIP", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> recipientZIP;
    @XmlElementRef(name = "SenderAddressLine1", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderAddressLine1;
    @XmlElementRef(name = "SenderAddressLine2", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderAddressLine2;
    @XmlElementRef(name = "SenderCity", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderCity;
    @XmlElementRef(name = "SenderCountryCd", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderCountryCd;
    @XmlElementRef(name = "SenderEmail", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderEmail;
    @XmlElementRef(name = "SenderFirstName", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderFirstName;
    @XmlElementRef(name = "SenderIdRef", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderIdRef;
    @XmlElementRef(name = "SenderLastName", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderLastName;
    @XmlElementRef(name = "SenderName", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderName;
    @XmlElementRef(name = "SenderState", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderState;
    @XmlElementRef(name = "SenderTelephone", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderTelephone;
    @XmlElementRef(name = "SenderZIP", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> senderZIP;
    @XmlElementRef(name = "TransportDate", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<XMLGregorianCalendar> transportDate;
    @XmlElementRef(name = "TransportMode", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", type = JAXBElement.class, required = false)
    protected JAXBElement<String> transportMode;

    /**
     * Gets the value of the contentPieces property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataContentPiece }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDeclarationDeclarationDataContentPiece> getContentPieces() {
        return contentPieces;
    }

    /**
     * Sets the value of the contentPieces property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataContentPiece }{@code >}
     *     
     */
    public void setContentPieces(JAXBElement<ArrayOfDeclarationDeclarationDataContentPiece> value) {
        this.contentPieces = value;
    }

    /**
     * Gets the value of the documents property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataDocument }{@code >}
     *     
     */
    public JAXBElement<ArrayOfDeclarationDeclarationDataDocument> getDocuments() {
        return documents;
    }

    /**
     * Sets the value of the documents property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfDeclarationDeclarationDataDocument }{@code >}
     *     
     */
    public void setDocuments(JAXBElement<ArrayOfDeclarationDeclarationDataDocument> value) {
        this.documents = value;
    }

    /**
     * Gets the value of the extraFieldNames property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getExtraFieldNames() {
        return extraFieldNames;
    }

    /**
     * Sets the value of the extraFieldNames property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setExtraFieldNames(JAXBElement<ArrayOfstring> value) {
        this.extraFieldNames = value;
    }

    /**
     * Gets the value of the extraFieldValues property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public JAXBElement<ArrayOfstring> getExtraFieldValues() {
        return extraFieldValues;
    }

    /**
     * Sets the value of the extraFieldValues property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}
     *     
     */
    public void setExtraFieldValues(JAXBElement<ArrayOfstring> value) {
        this.extraFieldValues = value;
    }

    /**
     * Gets the value of the grossWeight property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getGrossWeight() {
        return grossWeight;
    }

    /**
     * Sets the value of the grossWeight property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setGrossWeight(JAXBElement<BigDecimal> value) {
        this.grossWeight = value;
    }

    /**
     * Gets the value of the handlingClassCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getHandlingClassCd() {
        return handlingClassCd;
    }

    /**
     * Sets the value of the handlingClassCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setHandlingClassCd(JAXBElement<String> value) {
        this.handlingClassCd = value;
    }

    /**
     * Gets the value of the insuredValue property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getInsuredValue() {
        return insuredValue;
    }

    /**
     * Sets the value of the insuredValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setInsuredValue(JAXBElement<BigDecimal> value) {
        this.insuredValue = value;
    }

    /**
     * Gets the value of the insuredValueCurrencyCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getInsuredValueCurrencyCd() {
        return insuredValueCurrencyCd;
    }

    /**
     * Sets the value of the insuredValueCurrencyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setInsuredValueCurrencyCd(JAXBElement<String> value) {
        this.insuredValueCurrencyCd = value;
    }

    /**
     * Gets the value of the maxIndex property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public JAXBElement<Integer> getMaxIndex() {
        return maxIndex;
    }

    /**
     * Sets the value of the maxIndex property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link Integer }{@code >}
     *     
     */
    public void setMaxIndex(JAXBElement<Integer> value) {
        this.maxIndex = value;
    }

    /**
     * Gets the value of the natureTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getNatureTypeCd() {
        return natureTypeCd;
    }

    /**
     * Sets the value of the natureTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setNatureTypeCd(JAXBElement<String> value) {
        this.natureTypeCd = value;
    }

    /**
     * Gets the value of the packageImageLocalPath property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPackageImageLocalPath() {
        return packageImageLocalPath;
    }

    /**
     * Sets the value of the packageImageLocalPath property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPackageImageLocalPath(JAXBElement<String> value) {
        this.packageImageLocalPath = value;
    }

    /**
     * Gets the value of the packageImageSourceInfo property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPackageImageSourceInfo() {
        return packageImageSourceInfo;
    }

    /**
     * Sets the value of the packageImageSourceInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPackageImageSourceInfo(JAXBElement<String> value) {
        this.packageImageSourceInfo = value;
    }

    /**
     * Gets the value of the postage property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public JAXBElement<BigDecimal> getPostage() {
        return postage;
    }

    /**
     * Sets the value of the postage property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}
     *     
     */
    public void setPostage(JAXBElement<BigDecimal> value) {
        this.postage = value;
    }

    /**
     * Gets the value of the postageCurrencyCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getPostageCurrencyCd() {
        return postageCurrencyCd;
    }

    /**
     * Sets the value of the postageCurrencyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setPostageCurrencyCd(JAXBElement<String> value) {
        this.postageCurrencyCd = value;
    }

    /**
     * Gets the value of the recipientAddressLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientAddressLine1() {
        return recipientAddressLine1;
    }

    /**
     * Sets the value of the recipientAddressLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientAddressLine1(JAXBElement<String> value) {
        this.recipientAddressLine1 = value;
    }

    /**
     * Gets the value of the recipientAddressLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientAddressLine2() {
        return recipientAddressLine2;
    }

    /**
     * Sets the value of the recipientAddressLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientAddressLine2(JAXBElement<String> value) {
        this.recipientAddressLine2 = value;
    }

    /**
     * Gets the value of the recipientCity property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientCity() {
        return recipientCity;
    }

    /**
     * Sets the value of the recipientCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientCity(JAXBElement<String> value) {
        this.recipientCity = value;
    }

    /**
     * Gets the value of the recipientCountryCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientCountryCd() {
        return recipientCountryCd;
    }

    /**
     * Sets the value of the recipientCountryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientCountryCd(JAXBElement<String> value) {
        this.recipientCountryCd = value;
    }

    /**
     * Gets the value of the recipientEmail property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientEmail() {
        return recipientEmail;
    }

    /**
     * Sets the value of the recipientEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientEmail(JAXBElement<String> value) {
        this.recipientEmail = value;
    }

    /**
     * Gets the value of the recipientFax property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientFax() {
        return recipientFax;
    }

    /**
     * Sets the value of the recipientFax property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientFax(JAXBElement<String> value) {
        this.recipientFax = value;
    }

    /**
     * Gets the value of the recipientFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientFirstName() {
        return recipientFirstName;
    }

    /**
     * Sets the value of the recipientFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientFirstName(JAXBElement<String> value) {
        this.recipientFirstName = value;
    }

    /**
     * Gets the value of the recipientIdRef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientIdRef() {
        return recipientIdRef;
    }

    /**
     * Sets the value of the recipientIdRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientIdRef(JAXBElement<String> value) {
        this.recipientIdRef = value;
    }

    /**
     * Gets the value of the recipientLastName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientLastName() {
        return recipientLastName;
    }

    /**
     * Sets the value of the recipientLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientLastName(JAXBElement<String> value) {
        this.recipientLastName = value;
    }

    /**
     * Gets the value of the recipientName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientName() {
        return recipientName;
    }

    /**
     * Sets the value of the recipientName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientName(JAXBElement<String> value) {
        this.recipientName = value;
    }

    /**
     * Gets the value of the recipientState property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientState() {
        return recipientState;
    }

    /**
     * Sets the value of the recipientState property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientState(JAXBElement<String> value) {
        this.recipientState = value;
    }

    /**
     * Gets the value of the recipientTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientTelephone() {
        return recipientTelephone;
    }

    /**
     * Sets the value of the recipientTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientTelephone(JAXBElement<String> value) {
        this.recipientTelephone = value;
    }

    /**
     * Gets the value of the recipientZIP property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getRecipientZIP() {
        return recipientZIP;
    }

    /**
     * Sets the value of the recipientZIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setRecipientZIP(JAXBElement<String> value) {
        this.recipientZIP = value;
    }

    /**
     * Gets the value of the senderAddressLine1 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderAddressLine1() {
        return senderAddressLine1;
    }

    /**
     * Sets the value of the senderAddressLine1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderAddressLine1(JAXBElement<String> value) {
        this.senderAddressLine1 = value;
    }

    /**
     * Gets the value of the senderAddressLine2 property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderAddressLine2() {
        return senderAddressLine2;
    }

    /**
     * Sets the value of the senderAddressLine2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderAddressLine2(JAXBElement<String> value) {
        this.senderAddressLine2 = value;
    }

    /**
     * Gets the value of the senderCity property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderCity() {
        return senderCity;
    }

    /**
     * Sets the value of the senderCity property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderCity(JAXBElement<String> value) {
        this.senderCity = value;
    }

    /**
     * Gets the value of the senderCountryCd property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderCountryCd() {
        return senderCountryCd;
    }

    /**
     * Sets the value of the senderCountryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderCountryCd(JAXBElement<String> value) {
        this.senderCountryCd = value;
    }

    /**
     * Gets the value of the senderEmail property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderEmail() {
        return senderEmail;
    }

    /**
     * Sets the value of the senderEmail property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderEmail(JAXBElement<String> value) {
        this.senderEmail = value;
    }

    /**
     * Gets the value of the senderFirstName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderFirstName() {
        return senderFirstName;
    }

    /**
     * Sets the value of the senderFirstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderFirstName(JAXBElement<String> value) {
        this.senderFirstName = value;
    }

    /**
     * Gets the value of the senderIdRef property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderIdRef() {
        return senderIdRef;
    }

    /**
     * Sets the value of the senderIdRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderIdRef(JAXBElement<String> value) {
        this.senderIdRef = value;
    }

    /**
     * Gets the value of the senderLastName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderLastName() {
        return senderLastName;
    }

    /**
     * Sets the value of the senderLastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderLastName(JAXBElement<String> value) {
        this.senderLastName = value;
    }

    /**
     * Gets the value of the senderName property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderName() {
        return senderName;
    }

    /**
     * Sets the value of the senderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderName(JAXBElement<String> value) {
        this.senderName = value;
    }

    /**
     * Gets the value of the senderState property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderState() {
        return senderState;
    }

    /**
     * Sets the value of the senderState property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderState(JAXBElement<String> value) {
        this.senderState = value;
    }

    /**
     * Gets the value of the senderTelephone property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderTelephone() {
        return senderTelephone;
    }

    /**
     * Sets the value of the senderTelephone property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderTelephone(JAXBElement<String> value) {
        this.senderTelephone = value;
    }

    /**
     * Gets the value of the senderZIP property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getSenderZIP() {
        return senderZIP;
    }

    /**
     * Sets the value of the senderZIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setSenderZIP(JAXBElement<String> value) {
        this.senderZIP = value;
    }

    /**
     * Gets the value of the transportDate property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public JAXBElement<XMLGregorianCalendar> getTransportDate() {
        return transportDate;
    }

    /**
     * Sets the value of the transportDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     *     
     */
    public void setTransportDate(JAXBElement<XMLGregorianCalendar> value) {
        this.transportDate = value;
    }

    /**
     * Gets the value of the transportMode property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getTransportMode() {
        return transportMode;
    }

    /**
     * Sets the value of the transportMode property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setTransportMode(JAXBElement<String> value) {
        this.transportMode = value;
    }

}
