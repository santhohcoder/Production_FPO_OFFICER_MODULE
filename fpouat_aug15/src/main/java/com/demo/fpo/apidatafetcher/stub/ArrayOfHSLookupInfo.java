
package com.demo.fpo.apidatafetcher.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfHSLookupInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfHSLookupInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="HSLookupInfo" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}HSLookupInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfHSLookupInfo", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", propOrder = {
    "hsLookupInfo"
})
public class ArrayOfHSLookupInfo {

    @XmlElement(name = "HSLookupInfo", nillable = true)
    protected List<HSLookupInfo> hsLookupInfo;

    /**
     * Gets the value of the hsLookupInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the hsLookupInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHSLookupInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link HSLookupInfo }
     * 
     * 
     */
    public List<HSLookupInfo> getHSLookupInfo() {
        if (hsLookupInfo == null) {
            hsLookupInfo = new ArrayList<HSLookupInfo>();
        }
        return this.hsLookupInfo;
    }

}
