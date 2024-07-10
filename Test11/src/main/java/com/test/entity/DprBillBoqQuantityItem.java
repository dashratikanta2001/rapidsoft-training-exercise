package com.test.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "dpr_bill_boq_quantity_item")
public class DprBillBoqQuantityItem  implements Serializable {

	private static final long serialVersionUID = -4626037564259452705L;

	private Long id;

	private Long dprBillBoqItemId;

	private DprBillBoqItem dprBillBoqItem;

//	private Chainage fromChainage;
//
//	private Chainage toChainage;

	private Double quantity;

	private String description;

//	private ChainageSide side;

	private Double no;

	private Double length;

	private Double width;

	private Double height;

	private Double rate;

	private Long siteId;

	private Long workorderId;

	private Boolean isActive;

	private Date createdOn;

	private Long createdBy;

	private Date modifiedOn;

	private Long modifiedBy;

	public DprBillBoqQuantityItem() {
		super();
	}

	public DprBillBoqQuantityItem(Long id) {
		super();
		this.id = id;
	}

	public DprBillBoqQuantityItem(Long id, Long dprBillBoqItemId, Double quantity,
			Double rate, Long siteId, Long workorderId, Boolean isActive, Date createdOn, Long createdBy,
			Date modifiedOn, Long modifiedBy) {
		super();
		this.id = id;
		this.dprBillBoqItemId = dprBillBoqItemId;

		this.quantity = quantity;
		this.rate = rate;
		this.siteId = siteId;
		this.workorderId = workorderId;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
		this.modifiedOn = modifiedOn;
		this.modifiedBy = modifiedBy;
	}

	public DprBillBoqQuantityItem(Long id, Long dprBillBoqItemId, Double quantity,
			String description, Double no, Double length, Double width, Double height, Double rate,
			Long siteId, Long workorderId, Boolean isActive, Date createdOn, Long createdBy, Date modifiedOn,
			Long modifiedBy) {
		super();
		this.id = id;
		this.dprBillBoqItemId = dprBillBoqItemId;
		this.quantity = quantity;
		this.description = description;
		this.no = no;
		this.length = length;
		this.width = width;
		this.height = height;
		this.rate = rate;
		this.siteId = siteId;
		this.workorderId = workorderId;
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

	@Column(name = "dpr_bill_boq_id")
	public Long getDprBillBoqItemId() {
		return dprBillBoqItemId;
	}

	public void setDprBillBoqItemId(Long dprBillBoqItemId) {
		this.dprBillBoqItemId = dprBillBoqItemId;
	}



//	@OneToOne
//	@JoinColumn(name = "from_chainage_id")
//	public Chainage getFromChainage() {
//		return fromChainage;
//	}
//
//	public void setFromChainage(Chainage fromChainage) {
//		this.fromChainage = fromChainage;
//	}
//
//	@OneToOne
//	@JoinColumn(name = "to_chainage_id")
//	public Chainage getToChainage() {
//		return toChainage;
//	}
//
//	public void setToChainage(Chainage toChainage) {
//		this.toChainage = toChainage;
//	}
	@ManyToOne
	@JoinColumn(name = "dpr_bill_boq_id", insertable = false, updatable = false)
	public DprBillBoqItem getDprBillBoqItem() {
		return dprBillBoqItem;
	}

	public void setDprBillBoqItem(DprBillBoqItem dprBillBoqItem) {
		this.dprBillBoqItem = dprBillBoqItem;
	}

	@Column(name = "quantity")
	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	@Column(name = "site_id")
	public Long getSiteId() {
		return siteId;
	}

	public void setSiteId(Long siteId) {
		this.siteId = siteId;
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

	@Column(name = "is_active")
	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	@Column(name = "workorder_id")
	public Long getWorkorderId() {
		return workorderId;
	}

	public void setWorkorderId(Long workorderId) {
		this.workorderId = workorderId;
	}

	@Column(name = "rate")
	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	@Enumerated(EnumType.ORDINAL)
//	@Column(name = "side")
//	public ChainageSide getChainageSide() {
//		return side;
//	}
//
//	public void setChainageSide(ChainageSide side) {
//		this.side = side;
//	}

	@Column(name = "no")
	public Double getNo() {
		return no;
	}

	public void setNo(Double no) {
		this.no = no;
	}

	@Column(name = "length")
	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	@Column(name = "width")
	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	@Column(name = "height")
	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

}
