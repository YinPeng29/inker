package cc.mrbird.inker.domain;

import cc.mrbird.common.annotation.AutoInsertCreateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


/**
 * Date: 2019-01-14 22:50
 * Author: wh
 * Description:
 */
@Table(name = "size")
public class Size implements Serializable {


    private static final long serialVersionUID = -2897955305386397338L;
    @Id
    @GeneratedValue(generator="UUID")
    @Column(name = "id")
    private  String id;

    @Column(name = "size")
    private String size;

    @AutoInsertCreateTime
    @Column(name = "create_time")
    private Date create_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    @Override
    public String toString() {
        return "Size{" +
                "id='" + id + '\'' +
                ", size='" + size + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
