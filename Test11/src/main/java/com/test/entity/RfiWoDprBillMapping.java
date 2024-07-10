package com.test.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "rfi_wo_dpr_bill_mapping")
public class RfiWoDprBillMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "rfi_main_id")
	private Long rfiMainId;

	@Column(name = "dpr_bill_id")
	private Long dprBillId;

	@Column(name = "quantity")
	private Double quantity;

	@Column(name = "bill_amount")
	private Double billAmount;

	@Column(name = "is_active")
	private Boolean isActive;

	@Column(name = "created_by")
	private Long createdBy;

	@Column(name = "created_on")
	private Date createdOn;

	@Column(name = "updated_by")
	private Long updatedBy;

	@Column(name = "updated_on")
	private Date updatedOn;

	@OneToOne
	@JoinColumn(name = "dpr_bill_id", insertable = false, updatable = false)
	private DprBill dprBill;

	public RfiWoDprBillMapping() {
		super();
	}

	public RfiWoDprBillMapping(Long id, Long rfiMainId, Long dprBillId, Double quantity, Double billAmount, Boolean isActive,
			Long createdBy, Date createdOn, Long updatedBy, Date updatedOn) {
		super();
		this.id = id;
		this.rfiMainId = rfiMainId;
		this.dprBillId = dprBillId;
		this.quantity = quantity;
		this.billAmount = billAmount;
		this.isActive = isActive;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRfiMainId() {
		return rfiMainId;
	}

	public void setRfiMainId(Long rfiMainId) {
		this.rfiMainId = rfiMainId;
	}

	public Long getDprBillId() {
		return dprBillId;
	}

	public void setDprBillId(Long billId) {
		this.dprBillId = billId;
	}

	public DprBill getDprBill() {
		return dprBill;
	}

	public void setBill(DprBill dprBill) {
		this.dprBill = dprBill;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}
