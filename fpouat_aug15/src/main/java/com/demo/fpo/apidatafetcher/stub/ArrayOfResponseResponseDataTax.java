
package com.demo.fpo.apidatafetcher.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfResponse.ResponseData.Tax complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfResponse.ResponseData.Tax"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Response.ResponseData.Tax" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}Response.ResponseData.Tax" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfResponse.ResponseData.Tax", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", propOrder = {
    "responseResponseDataTax"
})
public class ArrayOfResponseResponseDataTax {

    @XmlElement(name = "Response.ResponseData.Tax", nillable = true)
    protected List<ResponseResponseDataTax> responseResponseDataTax;

    /**
     * Gets the value of the responseResponseDataTax property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the responseResponseDataTax property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getResponseResponseDataTax().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResponseResponseDataTax }
     * 
     * 
     */
    public List<ResponseResponseDataTax> getResponseResponseDataTax() {
        if (responseResponseDataTax == null) {
            responseResponseDataTax = new ArrayList<ResponseResponseDataTax>();
        }
        return this.responseResponseDataTax;
    }

}
