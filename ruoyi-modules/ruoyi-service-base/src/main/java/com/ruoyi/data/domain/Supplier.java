package com.ruoyi.data.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.annotation.Excel;
import com.ruoyi.common.core.web.domain.BaseEntity;

/**
 * 供应商管理对象 t_supplier
 * 
 * @author denglin
 * @date 2023-02-04
 */
public class Supplier extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 序号 */
    private Long id;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 手机号码 */
    @Excel(name = "手机号码")
    private Long number;

    /** 电子邮件 */
    @Excel(name = "电子邮件")
    private String email;

    /** 开行户 */
    @Excel(name = "开行户")
    private String bankDeposit;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 联系人 */
    @Excel(name = "联系人")
    private String contacts;

    /** 联系电话 */
    @Excel(name = "联系电话")
    private Long contactsTel;

    /** 传真 */
    @Excel(name = "传真")
    private String Fax;

    /** 账号 */
    @Excel(name = "账号")
    private String accountNumber;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setNumber(Long number) 
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setBankDeposit(String bankDeposit) 
    {
        this.bankDeposit = bankDeposit;
    }

    public String getBankDeposit() 
    {
        return bankDeposit;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setContacts(String contacts) 
    {
        this.contacts = contacts;
    }

    public String getContacts() 
    {
        return contacts;
    }
    public void setContactsTel(Long contactsTel) 
    {
        this.contactsTel = contactsTel;
    }

    public Long getContactsTel() 
    {
        return contactsTel;
    }
    public void setFax(String Fax) 
    {
        this.Fax = Fax;
    }

    public String getFax() 
    {
        return Fax;
    }
    public void setAccountNumber(String accountNumber) 
    {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() 
    {
        return accountNumber;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("number", getNumber())
            .append("email", getEmail())
            .append("bankDeposit", getBankDeposit())
            .append("address", getAddress())
            .append("contacts", getContacts())
            .append("contactsTel", getContactsTel())
            .append("Fax", getFax())
            .append("accountNumber", getAccountNumber())
            .append("remarks", getRemarks())
            .toString();
    }
}
