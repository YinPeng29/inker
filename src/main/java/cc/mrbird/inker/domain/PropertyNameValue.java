package cc.mrbird.inker.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by yin on 2019/1/31.
 * Description:
 */
@Table(name="re_property_name_value")
public class PropertyNameValue implements Serializable{

    private static final long serialVersionUID = -87050160863932982L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private String id;

    @Column(name = "property_name_id")
    private String propertyNameId;

    @Column(name = "property_value_id")
    private String propertyValueId;

    public PropertyNameValue() {
    }

    public PropertyNameValue(String propertyNameId, String propertyValueId) {
        this.propertyNameId = propertyNameId;
        this.propertyValueId = propertyValueId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPropertyNameId() {
        return propertyNameId;
    }

    public void setPropertyNameId(String propertyNameId) {
        this.propertyNameId = propertyNameId;
    }

    public String getPropertyValueId() {
        return propertyValueId;
    }

    public void setPropertyValueId(String propertyValueId) {
        this.propertyValueId = propertyValueId;
    }
}
