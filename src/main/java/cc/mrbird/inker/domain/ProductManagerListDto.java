package cc.mrbird.inker.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName ProductManagerListDto
 * @Description 商品管理列表dto
 * @author wuhan
 * @date 2019-02-14 10:08
 */
public class ProductManagerListDto implements Serializable {
    private static final long serialVersionUID = 4872941848249442783L;

    /**商品id*/
    private String productId;

    /**商品标题*/
    private String productName;

    /**商品出售状态*/
    private String status;

    /**商品的价格（同款衣服最低的那个-价格*/
    private Double price;

    /**
     * 商品的库存（所有尺寸总和）
     */
    private Long allStock;

    /**商品显示的标题图片地址*/
    private String sha2;

    /**商品创建时间*/
    private Date create_time;

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getAllStock() {
        return allStock;
    }

    public void setAllStock(Long allStock) {
        this.allStock = allStock;
    }

    public String getSha2() {
        return sha2;
    }

    public void setSha2(String sha2) {
        this.sha2 = sha2;
    }

    @Override
    public String toString() {
        return "ProductManagerListDto{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", status='" + status + '\'' +
                ", price=" + price +
                ", allStock=" + allStock +
                ", sha2='" + sha2 + '\'' +
                ", create_time=" + create_time +
                '}';
    }
}
