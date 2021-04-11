package com.example.learnmalldemo.mbg.model;

import java.io.Serializable;

public class OmsCompanyAddress implements Serializable {
    private Long id;

    /**
     * 地址名称
     *
     * @mbg.generated
     */
    private String addressName;

    /**
     * 默认发货地址：0->否；1->是
     *
     * @mbg.generated
     */
    private Integer sendStatus;

    /**
     * 是否默认收货地址：0->否；1->是
     *
     * @mbg.generated
     */
    private Integer receiveStatus;

    /**
     * 收发货人姓名
     *
     * @mbg.generated
     */
    private String name;

    /**
     * 收货人电话
     *
     * @mbg.generated
     */
    private String phone;

    /**
     * 省/直辖市
     *
     * @mbg.generated
     */
    private String province;

    /**
     * 市
     *
     * @mbg.generated
     */
    private String city;

    /**
     * 区
     *
     * @mbg.generated
     */
    private String region;

    /**
     * 详细地址
     *
     * @mbg.generated
     */
    private String detailAddress;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public Integer getSendStatus() {
        return sendStatus;
    }

    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }

    public Integer getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(Integer receiveStatus) {
        this.receiveStatus = receiveStatus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", addressName=").append(addressName);
        sb.append(", sendStatus=").append(sendStatus);
        sb.append(", receiveStatus=").append(receiveStatus);
        sb.append(", name=").append(name);
        sb.append(", phone=").append(phone);
        sb.append(", province=").append(province);
        sb.append(", city=").append(city);
        sb.append(", region=").append(region);
        sb.append(", detailAddress=").append(detailAddress);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}