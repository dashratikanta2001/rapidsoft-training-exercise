package com.test.entity;

import java.util.Date;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "dpr_bill_boq_item")
public class DprBillBoqItem {

	private Long id;

	private Long dprBillId;

	private DprBill dprBill;

	private Long structureTypeId;

	private Long boqId;

	private BoqItem boq;

	private Unit unit;

	private String vendorDescription;

	private String remark;

	private Long siteId;

	private Boolean isActive;

	private Date createdOn;

	private Long createdBy;

	private Date modifiedOn;

	private Long modifiedBy;

	private StructureType structureType;

	private Long woBoqQtyMapId;

	private WorkorderBoqWorkQtyMapping woBoqQtyMap;

	public DprBillBoqItem() {
		super();
	}

	public DprBillBoqItem(Long id) {
		super();
		this.id = id;
	}

	public DprBillBoqItem(Long id, Long dprBillId, Long structureTypeId, Long boqId, Unit unit, String vendorDescription,
			String remark, Long siteId, Boolean isActive, Date createdOn, Long createdBy, Date modifiedOn,
			Long modifiedBy, Long woBoqQtyMapId) {
		super();
		this.id = id;
		this.dprBillId = dprBillId;
		this.structureTypeId = structureTypeId;
		this.boqId = boqId;
		this.unit = unit;
		this.vendorDescription = vendorDescription;
		this.remark = remark;
		this.siteId = siteId;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
		this.woBoqQtyMapId = woBoqQtyMapId;
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

	public void setDprBillId(Long billId) {
		this.dprBillId = billId;
	}

	@ManyToOne
	@JoinColumn(name = "dpr_bill_id", insertable = false, updatable = false)
	public DprBill getDprBill() {
		return dprBill;
	}

	public void setDprBill(DprBill dprBill) {
		this.dprBill = dprBill;
	}

	@Column(name = "boq_id")
	public Long getBoqId() {
		return boqId;
	}

	public void setBoqId(Long boqId) {
		this.boqId = boqId;
	}

	@OneToOne
	@JoinColumn(name = "boq_id", insertable = false, updatable = false)
	public BoqItem getBoq() {
		return boq;
	}

	public void setBoq(BoqItem boq) {
		this.boq = boq;
	}

	@Column(name = "remark")
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "site_id")
	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
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

	@Column(name = "vendor_description")
	public String getVendorDescription() {
		return vendorDescription;
	}

	public void setVendorDescription(String vendorDescription) {
		this.vendorDescription = vendorDescription;
	}

	@OneToOne
	@JoinColumn(name = "unit_id")
	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	@Column(name = "structure_type_id")
	public Long getStructureTypeId() {
		return structureTypeId;
	}

	public void setStructureTypeId(Long structureTypeId) {
		this.structureTypeId = structureTypeId;
	}

	@OneToOne
	@JoinColumn(name = "structure_type_id", insertable = false, updatable = false)
	public StructureType getStructureType() {
		return structureType;
	}

	public void setStructureType(StructureType structureType) {
		this.structureType = structureType;
	}

	@Column(name = "wo_boq_quantity_map_id")
	public Long getWoBoqQtyMapId() {
		return woBoqQtyMapId;
	}

	public void setWoBoqQtyMapId(Long woBoqQtyMapId) {
		this.woBoqQtyMapId = woBoqQtyMapId;
	}

	@OneToOne
	@JoinColumn(name = "wo_boq_quantity_map_id", insertable = false, updatable = false)
	public WorkorderBoqWorkQtyMapping getWoBoqQtyMap() {
		return woBoqQtyMap;
	}

	public void setWoBoqQtyMap(WorkorderBoqWorkQtyMapping woBoqQtyMap) {
		this.woBoqQtyMap = woBoqQtyMap;
	}
}
