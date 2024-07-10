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
@Table(name = "dpr_carry_forward_diesel")
public class DprCarryForwardDiesel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "dpr_bill_id")
	private Long dprBillId;

	@Column(name = "is_diesel_forward")
	private Boolean isDieselForward;

	@Column(name = "diesel_qty")
	private Double dieselQty;

	@Column(name = "site_id")
	private Long siteId;

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
	@JoinColumn(name = "bill_id", insertable = false, updatable = false)
	private DprBill dprBill;

	public DprCarryForwardDiesel() {
		super();

	}

	public DprCarryForwardDiesel(Long id, Long dprBillId, Boolean isDieselForward, Double dieselQty, Long siteId,
			Boolean isActive, Long createdBy, Date createdOn, Long updatedBy, Date updatedOn) {
		super();
		this.id = id;
		this.dprBillId = dprBillId;
		this.isDieselForward = isDieselForward;
		this.dieselQty = dieselQty;
		this.siteId = siteId;
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

	public Long getDprBillId() {
		return dprBillId;
	}

	public void setDprBillId(Long billId) {
		this.dprBillId = billId;
	}

	public Boolean getIsDieselForward() {
		return isDieselForward;
	}

	public void setIsDieselForward(Boolean isDieselForward) {
		this.isDieselForward = isDieselForward;
	}

	public Double getDieselQty() {
		return dieselQty;
	}

	public void setDieselQty(Double dieselQty) {
		this.dieselQty = dieselQty;
	}

	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
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

	public DprBill getBill() {
		return dprBill;
	}

	public void setBill(DprBill dprBill) {
		this.dprBill = dprBill;
	}

}
