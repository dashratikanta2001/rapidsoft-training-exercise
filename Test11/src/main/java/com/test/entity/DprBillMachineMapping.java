package com.test.entity;

import java.io.Serializable;
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
@Table(name = "dpr_bill_machine_mapping")
public class DprBillMachineMapping implements Serializable {

	private static final long serialVersionUID = -1254077197744693260L;

	private Long id;

	private Long dprBillId;

	private Byte machineType;

	private Long machineId;

	private Long woHiringMachineWorkItemId;

	private Date fromDate;

	private Date toDate;

	private Boolean isActive;

	private Date createdOn;

	private Long createdBy;

	private Date modifiedOn;

	private Long modifiedBy;

	private DprBill dprBill;

	public DprBillMachineMapping() {
		super();
	}

	public DprBillMachineMapping(Long id) {
		super();
		this.id = id;
	}

	public DprBillMachineMapping(Long id, Long dprBillId, Byte machineType, Long machineId, Long woHiringMachineWorkItemId,
			Date fromDate, Date toDate, Boolean isActive, Date createdOn, Long createdBy, Date modifiedOn,
			Long modifiedBy) {
		super();
		this.id = id;
		this.dprBillId = dprBillId;
		this.machineType = machineType;
		this.machineId = machineId;
		this.woHiringMachineWorkItemId = woHiringMachineWorkItemId;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "dpr_bill_id")
	public Long getDprBillId() {
		return dprBillId;
	}

	public void setDprBillId(Long dprBillId) {
		this.dprBillId = dprBillId;
	}

	@Column(name = "machine_type")
	public Byte getMachineType() {
		return machineType;
	}

	public void setMachineType(Byte machineType) {
		this.machineType = machineType;
	}

	@Column(name = "machine_id")
	public Long getMachineId() {
		return machineId;
	}

	public void setMachineId(Long machineId) {
		this.machineId = machineId;
	}

	@Column(name = "wo_hiring_machine_work_item_id")
	public Long getWoHiringMachineWorkItemId() {
		return woHiringMachineWorkItemId;
	}

	public void setWoHiringMachineWorkItemId(Long woHiringMachineWorkItemId) {
		this.woHiringMachineWorkItemId = woHiringMachineWorkItemId;
	}

	@Column(name = "from_date")
	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	@Column(name = "to_date")
	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	@Column(name = "is_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "created_on")
	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	@Column(name = "created_by")
	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "modified_on")
	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	@Column(name = "modified_by")
	public Long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(Long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	@OneToOne
	@JoinColumn(name = "dpr_bill_id", insertable = false, updatable = false)
	public DprBill getDprBill() {
		return dprBill;
	}

	public void setDprBill(DprBill dprBill) {
		this.dprBill = dprBill;
	}

}
