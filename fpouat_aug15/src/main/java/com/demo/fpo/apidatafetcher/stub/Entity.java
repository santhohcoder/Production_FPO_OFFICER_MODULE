
package com.demo.fpo.apidatafetcher.stub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Entity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Entity"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="entityState" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Entity", namespace = "http://schemas.datacontract.org/2004/07/PTC.BusinessLayer.Core", propOrder = {
    "entityState"
})
@XmlSeeAlso({
    CDSView.class,
    MailObject.class,
    PostalService.class,
    MarketedGood.class,
    CDSObject.class,
    RestrictionProhibition.class
})
public class Entity {

    protected int entityState;

    /**
     * Gets the value of the entityState property.
     * 
     */
    public int getEntityState() {
        return entityState;
    }

    /**
     * Sets the value of the entityState property.
     * 
     */
    public void setEntityState(int value) {
        this.entityState = value;
    }

}
