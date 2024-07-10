package com.test.entity;

import java.io.Serializable;
import java.util.Date;

import com.test.model.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "dpr_bill")
public class DprBill  implements Serializable {

	private static final long serialVersionUID = 2439239172519139654L; 
	private Long id;

	private BillType type;

	private Integer dprBillNo;

	private Date fromDate;

	private Date toDate;

	private Workorder workorder;

	private String taxInvoiceNo;

	private Date taxInvoiceDate;

	private Double applicableIgst;

	private Boolean isIgstOnly;

	private EngineState state;

	private Long siteId;

	private Boolean isActive;

	private Date createdOn;

	private Long createdBy;

	private Date modifiedOn;

	private Long modifiedBy;

	private User modifiedByUser;
	
	private Double totalBilledAmt;

	private DprCarryForwardDiesel dprCarryForwardDiesel;

	private Site site;

	public DprBill() {
		super();
	}

	public DprBill(Long id) {
		super();
		this.id = id;
	}

	public DprBill(Long id, BillType type, Integer dprBillNo, Date fromDate, Date toDate, Workorder workorder,
			String taxInvoiceNo, Date taxInvoiceDate, Double applicableIgst, Boolean isIgstOnly, EngineState state,
			Long siteId, Boolean isActive, Date createdOn, Long createdBy, Date modifiedOn, Long modifiedBy) {
		super();
		this.id = id;
		this.type = type;
		this.dprBillNo = dprBillNo;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.workorder = workorder;
		this.taxInvoiceNo = taxInvoiceNo;
		this.taxInvoiceDate = taxInvoiceDate;
		this.applicableIgst = applicableIgst;
		this.isIgstOnly = isIgstOnly;
		this.state = state;
		this.siteId = siteId;
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

	@OneToOne
	@JoinColumn(name = "type_id")
	public BillType getType() {
		return type;
	}

	public void setType(BillType type) {
		this.type = type;
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

	@OneToOne
	@JoinColumn(name = "workorder_id")
	public Workorder getWorkorder() {
		return workorder;
	}

	public void setWorkorder(Workorder workorder) {
		this.workorder = workorder;
	}

	@OneToOne
	@JoinColumn(name = "state_id")
	public EngineState getState() {
		return state;
	}

	public void setState(EngineState state) {
		this.state = state;
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

	@Column(name = "dpr_bill_no")
	public Integer getDprBillNo() {
		return dprBillNo;
	}

	public void setDprBillNo(Integer dprBillNo) {
		this.dprBillNo = dprBillNo;
	}

	@Column(name = "invoice_no")
	public String getTaxInvoiceNo() {
		return taxInvoiceNo;
	}

	public void setTaxInvoiceNo(String taxInvoiceNo) {
		this.taxInvoiceNo = taxInvoiceNo;
	}

	@Column(name = "invoice_date")
	public Date getTaxInvoiceDate() {
		return taxInvoiceDate;
	}

	public void setTaxInvoiceDate(Date taxInvoiceDate) {
		this.taxInvoiceDate = taxInvoiceDate;
	}

	@Column(name = "is_igst_only")
	public Boolean getIsIgstOnly() {
		return isIgstOnly;
	}

	public void setIsIgstOnly(Boolean isIgstOnly) {
		this.isIgstOnly = isIgstOnly;
	}

	@Column(name = "applicable_igst")
	public Double getApplicableIgst() {
		return applicableIgst;
	}

	public void setApplicableIgst(Double applicableIgst) {
		this.applicableIgst = applicableIgst;
	}

	@OneToOne
	@JoinColumn(name = "modified_by", insertable = false, updatable = false)
	public User getModifiedByUser() {
		return modifiedByUser;
	}

	public void setModifiedByUser(User modifiedByUser) {
		this.modifiedByUser = modifiedByUser;
	}

	@OneToOne(mappedBy = "dprBill", fetch = FetchType.LAZY)
//	@JoinColumn(mapp = "bill", insertable = false, updatable = false)
	public DprCarryForwardDiesel getDprCarryForwardDiesel() {
		return dprCarryForwardDiesel;
	}

	public void setDprCarryForwardDiesel(DprCarryForwardDiesel dprCarryForwardDiesel) {
		this.dprCarryForwardDiesel = dprCarryForwardDiesel;
	}

	@OneToOne
	@JoinColumn(name = "site_id", insertable = false, updatable = false)
	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	@Column(name = "bill_amount")
	public Double getTotalBilledAmt() {
		return totalBilledAmt;
	}

	public void setTotalBilledAmt(Double totalBilledAmt) {
		this.totalBilledAmt = totalBilledAmt;
	}

}
