package nc.vo.mallpos.activity;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;

public class MPOS_ActivityHeadVO extends SuperVO {
	
	private static final long serialVersionUID = 5039323239652193120L;
	
	private String pk_activity;
	@BillTempletParams(@Param(key = "defaultName", value = "�����"))
	private String activitycode;
	@BillTempletParams(@Param(key = "defaultName", value = "�����"))
	private String activityname;
	@BillTempletParams(@Param(key = "defaultName", value = "���ʼ����"))
	private UFDate startdate;
	@BillTempletParams(@Param(key = "defaultName", value = "���������"))
	private UFDate enddate;
	@BillTempletParams(@Param(key = "defaultName", value = "��������"))
	private UFDate billdate;
	@BillTempletParams(@Param(key = "defaultName", value = "�״̬"))
	private Integer statusflag;
	private String pk_activity_type;
	@BillTempletParams(@Param(key = "defaultName", value = "��ע"))
	private String memo;

	@Override
	public String getPKFieldName() {
		return "pk_activity";
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "mpos_activity";
	}

	public String getPk_activity() {
		return pk_activity;
	}

	public void setPk_activity(String pk_activity) {
		this.pk_activity = pk_activity;
	}

	public String getActivityname() {
		return activityname;
	}

	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	public String getPk_activity_type() {
		return pk_activity_type;
	}

	public void setPk_activity_type(String pk_activity_type) {
		this.pk_activity_type = pk_activity_type;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public Integer getStatusflag() {
		return statusflag;
	}

	public void setStatusflag(Integer statusflag) {
		this.statusflag = statusflag;
	}

	public UFDate getStartdate() {
		return startdate;
	}

	public void setStartdate(UFDate startdate) {
		this.startdate = startdate;
	}

	public UFDate getEnddate() {
		return enddate;
	}

	public void setEnddate(UFDate enddate) {
		this.enddate = enddate;
	}

	public UFDate getBilldate() {
		return billdate;
	}

	public void setBilldate(UFDate billdate) {
		this.billdate = billdate;
	}

	public String getActivitycode() {
		return activitycode;
	}

	public void setActivitycode(String activitycode) {
		this.activitycode = activitycode;
	}

}
