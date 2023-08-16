
package com.demo.fpo.apidatafetcher.stub;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCDSView complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCDSView"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CDSView" type="{http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational}CDSView" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCDSView", namespace = "http://schemas.datacontract.org/2004/07/CDS.BusinessLayer.Operational", propOrder = {
    "cdsView"
})
public class ArrayOfCDSView {

    @XmlElement(name = "CDSView", nillable = true)
    protected List<CDSView> cdsView;

    /**
     * Gets the value of the cdsView property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cdsView property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCDSView().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CDSView }
     * 
     * 
     */
    public List<CDSView> getCDSView() {
        if (cdsView == null) {
            cdsView = new ArrayList<CDSView>();
        }
        return this.cdsView;
    }

}
