package com.test.entity;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dpr_bill_state_transition_mapping")
public class DprBillStateTransitionMapping implements Serializable {

	private static final long serialVersionUID = -4269032239557534894L;
	
	private Long id;

	private Long dprBillId;

	private Integer stateId;

	private Boolean isActive;

	private Date createdOn;

	private Long createdBy;

	public DprBillStateTransitionMapping() {
		super();
	}

	public DprBillStateTransitionMapping(Long id, Long dprBillId, Integer stateId, Boolean isActive, Date createdOn,
			Long createdBy) {
		super();
		this.id = id;
		this.dprBillId = dprBillId;
		this.stateId = stateId;
		this.isActive = isActive;
		this.createdOn = createdOn;
		this.createdBy = createdBy;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "dpr_bill_id")
	public Long getBillId() {
		return dprBillId;
	}

	public void setBillId(Long dprBillId) {
		this.dprBillId = dprBillId;
	}

	@Column(name = "state_id")
	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
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

}
