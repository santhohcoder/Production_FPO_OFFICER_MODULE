
package com.demo.fpo.apidatafetcher.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfRestrictionProhibition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfRestrictionProhibition"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="RestrictionProhibition" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management}RestrictionProhibition" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfRestrictionProhibition", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Management", propOrder = {
    "restrictionProhibition"
})
public class ArrayOfRestrictionProhibition {

    @XmlElement(name = "RestrictionProhibition", nillable = true)
    protected List<RestrictionProhibition> restrictionProhibition;

    /**
     * Gets the value of the restrictionProhibition property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the restrictionProhibition property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRestrictionProhibition().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RestrictionProhibition }
     * 
     * 
     */
    public List<RestrictionProhibition> getRestrictionProhibition() {
        if (restrictionProhibition == null) {
            restrictionProhibition = new ArrayList<RestrictionProhibition>();
        }
        return this.restrictionProhibition;
    }

}
