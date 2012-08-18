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
	 * 获取activityname
	 * @return activityname activityname
	 */
	public String getActivityname() {
		return activityname;
	}

	/**
	 * 设置activityname
	 * @param activityname activityname
	 */
	public void setActivityname(String activityname) {
		this.activityname = activityname;
	}

	/**
	 * 获取beginmny
	 * @return beginmny beginmny
	 */
	public UFDouble getBeginmny() {
		return beginmny;
	}

	/**
	 * 设置beginmny
	 * @param beginmny beginmny
	 */
	public void setBeginmny(UFDouble beginmny) {
		this.beginmny = beginmny;
	}

	/**
	 * 获取discountnum
	 * @return discountnum discountnum
	 */
	public UFDouble getDiscountnum() {
		return discountnum;
	}

	/**
	 * 设置discountnum
	 * @param discountnum discountnum
	 */
	public void setDiscountnum(UFDouble discountnum) {
		this.discountnum = discountnum;
	}

	/**
	 * 获取discounttype
	 * @return discounttype discounttype
	 */
	public String getDiscounttype() {
		return discounttype;
	}

	/**
	 * 设置discounttype
	 * @param discounttype discounttype
	 */
	public void setDiscounttype(String discounttype) {
		this.discounttype = discounttype;
	}

	/**
	 * 获取endmny
	 * @return endmny endmny
	 */
	public UFDouble getEndmny() {
		return endmny;
	}

	/**
	 * 设置endmny
	 * @param endmny endmny
	 */
	public void setEndmny(UFDouble endmny) {
		this.endmny = endmny;
	}

	/**
	 * 获取pk_activity
	 * @return pk_activity pk_activity
	 */
	public String getPk_activity() {
		return pk_activity;
	}

	/**
	 * 设置pk_activity
	 * @param pk_activity pk_activity
	 */
	public void setPk_activity(String pk_activity) {
		this.pk_activity = pk_activity;
	}

	/**
	 * 获取discounttype_name
	 * @return discounttype_name discounttype_name
	 */
	public String getDiscounttype_name() {
		return discounttype_name;
	}

	/**
	 * 设置discounttype_name
	 * @param discounttype_name discounttype_name
	 */
	public void setDiscounttype_name(String discounttype_name) {
		this.discounttype_name = discounttype_name;
	}

	/**
	 * 获取pk_activity_type
	 * @return pk_activity_type pk_activity_type
	 */
	public String getPk_activity_type() {
		return pk_activity_type;
	}

	/**
	 * 设置pk_activity_type
	 * @param pk_activity_type pk_activity_type
	 */
	public void setPk_activity_type(String pk_activity_type) {
		this.pk_activity_type = pk_activity_type;
	}

}
