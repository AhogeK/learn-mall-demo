package com.example.learnmalldemo.mbg.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class PmsSkuStock implements Serializable {
    private Long id;

    private Long productId;

    /**
     * sku编码
     *
     * @mbg.generated
     */
    private String skuCode;

    private BigDecimal price;

    /**
     * 库存
     *
     * @mbg.generated
     */
    private Integer stock;

    /**
     * 预警库存
     *
     * @mbg.generated
     */
    private Integer lowStock;

    /**
     * 展示图片
     *
     * @mbg.generated
     */
    private String pic;

    /**
     * 销量
     *
     * @mbg.generated
     */
    private Integer sale;

    /**
     * 单品促销价格
     *
     * @mbg.generated
     */
    private BigDecimal promotionPrice;

    /**
     * 锁定库存
     *
     * @mbg.generated
     */
    private Integer lockStock;

    /**
     * 商品销售属性，json格式
     *
     * @mbg.generated
     */
    private String spData;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getLowStock() {
        return lowStock;
    }

    public void setLowStock(Integer lowStock) {
        this.lowStock = lowStock;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getSale() {
        return sale;
    }

    public void setSale(Integer sale) {
        this.sale = sale;
    }

    public BigDecimal getPromotionPrice() {
        return promotionPrice;
    }

    public void setPromotionPrice(BigDecimal promotionPrice) {
        this.promotionPrice = promotionPrice;
    }

    public Integer getLockStock() {
        return lockStock;
    }

    public void setLockStock(Integer lockStock) {
        this.lockStock = lockStock;
    }

    public String getSpData() {
        return spData;
    }

    public void setSpData(String spData) {
        this.spData = spData;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productId=").append(productId);
        sb.append(", skuCode=").append(skuCode);
        sb.append(", price=").append(price);
        sb.append(", stock=").append(stock);
        sb.append(", lowStock=").append(lowStock);
        sb.append(", pic=").append(pic);
        sb.append(", sale=").append(sale);
        sb.append(", promotionPrice=").append(promotionPrice);
        sb.append(", lockStock=").append(lockStock);
        sb.append(", spData=").append(spData);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}