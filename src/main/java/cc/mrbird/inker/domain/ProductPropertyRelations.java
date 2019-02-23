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
 * Created by wuhan on 2019/2/13.
 * Description: 商品的属性关联关系
 */
@Table(name = "re_product_property")
public class ProductPropertyRelations implements Serializable {

    private static final long serialVersionUID = -2427336382191257369L;

    @Id
    @GeneratedValue(generator="UUID")
    @Column(name = "id")
    private String id;

    @Column(name = "product_id")
    private String product_id;

    @Column(name = "property_code")
    private String property_code;

    @Column(name = "property_value_id")
    private String property_value_id;

    @Column(name = "color_id")
    private String color_id;

    @Column(name = "price")
    private Double price;

    @Column(name = "stock")
    private Long stock;

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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProperty_code() {
        return property_code;
    }

    public void setProperty_code(String property_code) {
        this.property_code = property_code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
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

    public String getProperty_value_id() {
        return property_value_id;
    }

    public void setProperty_value_id(String property_value_id) {
        this.property_value_id = property_value_id;
    }

    public String getColor_id() {
        return color_id;
    }

    public void setColor_id(String color_id) {
        this.color_id = color_id;
    }
}
