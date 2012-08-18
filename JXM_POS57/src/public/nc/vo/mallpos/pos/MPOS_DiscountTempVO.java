package nc.vo.mallpos.pos;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class MPOS_DiscountTempVO extends SuperVO{

	private static final long serialVersionUID = 1L;
	
	private String pk_activity;
	private String pk_activity_type;
	private String activityname;
	private String discounttype_name;
	private String discounttype;
	private UFDouble beginmny;
	private UFDouble endmny;
	private UFDouble discountnum;
	

	@Override
	public String getPKFieldName() {
		return null;
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return null;
	}

	/**
	 * ��ȡactivityname
	 * @return activityname activityname
	 */
	public String getActivityname() {
		return activityname;
	}

	/**
	 * ����activityname
	 * @param activityname activityname
	 */
	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	/**
	 * ��ȡbeginmny
	 * @return beginmny beginmny
	 */
	public UFDouble getBeginmny() {
		return beginmny;
	}

	/**
	 * ����beginmny
	 * @param beginmny beginmny
	 */
	public void setBeginmny(UFDouble beginmny) {
		this.beginmny = beginmny;
	}

	/**
	 * ��ȡdiscountnum
	 * @return discountnum discountnum
	 */
	public UFDouble getDiscountnum() {
		return discountnum;
	}

	/**
	 * ����discountnum
	 * @param discountnum discountnum
	 */
	public void setDiscountnum(UFDouble discountnum) {
		this.discountnum = discountnum;
	}

	/**
	 * ��ȡdiscounttype
	 * @return discounttype discounttype
	 */
	public String getDiscounttype() {
		return discounttype;
	}

	/**
	 * ����discounttype
	 * @param discounttype discounttype
	 */
	public void setDiscounttype(String discounttype) {
		this.discounttype = discounttype;
	}

	/**
	 * ��ȡendmny
	 * @return endmny endmny
	 */
	public UFDouble getEndmny() {
		return endmny;
	}

	/**
	 * ����endmny
	 * @param endmny endmny
	 */
	public void setEndmny(UFDouble endmny) {
		this.endmny = endmny;
	}

	/**
	 * ��ȡpk_activity
	 * @return pk_activity pk_activity
	 */
	public String getPk_activity() {
		return pk_activity;
	}

	/**
	 * ����pk_activity
	 * @param pk_activity pk_activity
	 */
	public void setPk_activity(String pk_activity) {
		this.pk_activity = pk_activity;
	}

	/**
	 * ��ȡdiscounttype_name
	 * @return discounttype_name discounttype_name
	 */
	public String getDiscounttype_name() {
		return discounttype_name;
	}

	/**
	 * ����discounttype_name
	 * @param discounttype_name discounttype_name
	 */
	public void setDiscounttype_name(String discounttype_name) {
		this.discounttype_name = discounttype_name;
	}

	/**
	 * ��ȡpk_activity_type
	 * @return pk_activity_type pk_activity_type
	 */
	public String getPk_activity_type() {
		return pk_activity_type;
	}

	/**
	 * ����pk_activity_type
	 * @param pk_activity_type pk_activity_type
	 */
	public void setPk_activity_type(String pk_activity_type) {
		this.pk_activity_type = pk_activity_type;
	}

}
