package cc.mrbird.inker.domain;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName BannerInfo
 * @Description 导航轮播信息
 * @author wuhan
 * @date 2019-02-19 17:12
 */
@Table(name = "banner_info")
public class BannerInfo implements Serializable {
    private static final long serialVersionUID = 692501874864095874L;


    @Id
    private String Id;

    @Column(name = "sha2")
    private String sha2;

    @Column(name = "to_url")
    private String to_url;

    @Column(name = "create_date")
    private Date create_date;

    @Column(name = "title")
    private String title;

    @Column(name = "banner_index")
    private Integer banner_index;

    @Column(name = "status")
    private Integer status;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getSha2() {
        return sha2;
    }

    public void setSha2(String sha2) {
        this.sha2 = sha2;
    }

    public String getTo_url() {
        return to_url;
    }

    public void setTo_url(String to_url) {
        this.to_url = to_url;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getBanner_index() {
        return banner_index;
    }

    public void setBanner_index(Integer banner_index) {
        this.banner_index = banner_index;
    }
}
