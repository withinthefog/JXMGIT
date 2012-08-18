package nc.vo.mallpos.activity;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class MPOS_ActivityCustVO extends SuperVO {
	
	private static final long serialVersionUID = 9150356084689883872L;
	
	private String pk_activity_custs;
	private String pk_activity;
	private String pk_cubasdoc;
	private UFDouble apportionmentrate;

	@Override
	public String getPKFieldName() {
		return "pk_activity_custs";
	}

	@Override
	public String getParentPKFieldName() {
		return "pk_activity";
	}

	@Override
	public String getTableName() {
		return "mpos_activity_custs";
	}

	public String getPk_activity_custs() {
		return pk_activity_custs;
	}

	public void setPk_activity_custs(String pk_activity_custs) {
		this.pk_activity_custs = pk_activity_custs;
	}

	public String getPk_activity() {
		return pk_activity;
	}

	public void setPk_activity(String pk_activity) {
		this.pk_activity = pk_activity;
	}

	public String getPk_cubasdoc() {
		return pk_cubasdoc;
	}

	public void setPk_cubasdoc(String pk_cubasdoc) {
		this.pk_cubasdoc = pk_cubasdoc;
	}

	public UFDouble getApportionmentrate() {
		return apportionmentrate;
	}

	public void setApportionmentrate(UFDouble apportionmentrate) {
		this.apportionmentrate = apportionmentrate;
	}

}
