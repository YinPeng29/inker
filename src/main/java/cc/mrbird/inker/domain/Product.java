package cc.mrbird.inker.domain;

import cc.mrbird.common.annotation.AutoInsertCreateTime;
import cc.mrbird.common.annotation.AutoInsertCreator;
import cc.mrbird.common.annotation.AutoInsertModifier;
import cc.mrbird.common.annotation.AutoInsertModifyTime;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Date: 2019-01-14 22:50
 * Author: pinoc
 * Description:
 */
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1398056392316083359L;

    @Id
    @GeneratedValue(generator="UUID")
    @Column(name = "id")
    private  String id;

    /**
     * 商品名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 品牌
     */
    @Column(name = "brand")
    private String brand;

    /**
     * 货号
     */
    @Column(name = "code")
    private String code;

    /**
     * 状态
     */
    @Column(name = "status")
    private char status;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
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
