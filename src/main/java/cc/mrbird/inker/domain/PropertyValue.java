package cc.mrbird.inker.domain;

import cc.mrbird.common.annotation.AutoInsertCreateTime;
import cc.mrbird.common.annotation.AutoInsertCreator;
import cc.mrbird.common.annotation.AutoInsertModifier;
import cc.mrbird.common.annotation.AutoInsertModifyTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Date: 2019-01-22 10:48
 * Author: pinoc
 * Desc: 商品属性值的信息表
 */
@Table(name="property_value")
public class PropertyValue implements Serializable {

    private static final long serialVersionUID = 7469030286835871907L;

    @Id
    @GeneratedValue(generator="UUID")
    @Column(name = "id")
    private String id;

    @Column(name = "value")
    private String value;

    @AutoInsertCreateTime
    @Column(name = "create_time")
    private Date create_time;

    @AutoInsertModifyTime
    @Column(name = "modify_time")
    private Date modify_time;

    @AutoInsertCreator
    @Column(name = "creator")
    private String creator;

    @AutoInsertModifier
    @Column(name = "modifier")
    private String modifier;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getModify_time() {
        return modify_time;
    }

    public void setModify_time(Date modify_time) {
        this.modify_time = modify_time;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
