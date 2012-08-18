package nc.vo.mallpos.invexchange;

import nc.vo.pub.SuperVO;

public class MPOS_InvActivityHeadVO extends SuperVO {
	
	private static final long serialVersionUID = 643401746290720910L;
	
	private String pk_activity_inv_h;
	private String pk_activity;

	@Override
	public String getPKFieldName() {
		return "pk_activity_inv_h";
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "mpos_activity_inv_h";
	}

	public String getPk_activity_inv_h() {
		return pk_activity_inv_h;
	}

	public void setPk_activity_inv_h(String pk_activity_inv_h) {
		this.pk_activity_inv_h = pk_activity_inv_h;
	}

	public String getPk_activity() {
		return pk_activity;
	}

	public void setPk_activity(String pk_activity) {
		this.pk_activity = pk_activity;
	}

}
