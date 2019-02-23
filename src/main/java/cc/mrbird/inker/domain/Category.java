package cc.mrbird.inker.domain;

import cc.mrbird.common.annotation.AutoInsertCreateTime;
import cc.mrbird.common.annotation.AutoInsertCreator;
import cc.mrbird.common.annotation.AutoInsertModifier;
import cc.mrbird.common.annotation.AutoInsertModifyTime;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName Category
 * @Description 衣服类型（T恤，polo等）
 * @author wuhan
 * @date 2019-01-29 11:03
 */
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 9028634756826308812L;

    @Id
    @GeneratedValue(generator="UUID")
    @Column(name = "id")
    private  String id;

    @Column(name = "name")
    private String name;

    @AutoInsertCreator
    @Column(name = "creator")
    private String creator;

    @AutoInsertCreateTime
    @Column(name = "create_date")
    private Date create_date;

    @AutoInsertModifier
    @Column(name = "modifier")
    private String modifier;

    @AutoInsertModifyTime
    @Column(name = "modify_date")
    private Date modify_date;

    @Column(name = "parent_id")
    private String parent_id;

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Date getModify_date() {
        return modify_date;
    }

    public void setModify_date(Date modify_date) {
        this.modify_date = modify_date;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", creator='" + creator + '\'' +
                ", create_date=" + create_date +
                ", modifier='" + modifier + '\'' +
                ", modify_date=" + modify_date +
                ", parent_id='" + parent_id + '\'' +
                '}';
    }
}
