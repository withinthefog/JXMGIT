package nc.vo.mallpos.pos;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

/**
 * 销售明细分摊对象
 * @author cj
 * @date 2012-8-16 下午10:25:25
 */
public class MPOS_SaleDetailShareVO extends SuperVO{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "主键"))
	private String pk_sale_share;
	/**
	 * 主表主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "主表主键"))
	private String pk_saledetail_h;
	/**
	 * 活动规则主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "活动规则"))
	private String pk_activity_type;
	
	/**
	 * 活动主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "活动"))
	private String pk_activity;
	
	/**
	 * 折扣率
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "折扣率"))
	private UFDouble discount_rate;

	/**
	 * 折扣金额
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "折扣金额"))
	private UFDouble discount_money;
	/**
	 * 折前总额
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "折前总额"))
	private UFDouble discount_be_amount;
	/**
	 * 折后总额
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "折后总额"))
	private UFDouble discount_af_amount;

	@Override
	public String getPKFieldName() {
		return "pk_sale_share";
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "mpos_sale_share";
	}

	/**
	 * 获取折后总额
	 * @return discount_af_amount 折后总额
	 */
	public UFDouble getDiscount_af_amount() {
		return discount_af_amount;
	}

	/**
	 * 设置折后总额
	 * @param discount_af_amount 折后总额
	 */
	public void setDiscount_af_amount(UFDouble discount_af_amount) {
		this.discount_af_amount = discount_af_amount;
	}

	/**
	 * 获取折前总额
	 * @return discount_be_amount 折前总额
	 */
	public UFDouble getDiscount_be_amount() {
		return discount_be_amount;
	}

	/**
	 * 设置折前总额
	 * @param discount_be_amount 折前总额
	 */
	public void setDiscount_be_amount(UFDouble discount_be_amount) {
		this.discount_be_amount = discount_be_amount;
	}

	/**
	 * 获取折扣金额
	 * @return discount_money 折扣金额
	 */
	public UFDouble getDiscount_money() {
		return discount_money;
	}

	/**
	 * 设置折扣金额
	 * @param discount_money 折扣金额
	 */
	public void setDiscount_money(UFDouble discount_money) {
		this.discount_money = discount_money;
	}

	/**
	 * 获取折扣率
	 * @return discount_rate 折扣率
	 */
	public UFDouble getDiscount_rate() {
		return discount_rate;
	}

	/**
	 * 设置折扣率
	 * @param discount_rate 折扣率
	 */
	public void setDiscount_rate(UFDouble discount_rate) {
		this.discount_rate = discount_rate;
	}

	/**
	 * 获取活动主键
	 * @return pk_activity 活动主键
	 */
	public String getPk_activity() {
		return pk_activity;
	}

	/**
	 * 设置活动主键
	 * @param pk_activity 活动主键
	 */
	public void setPk_activity(String pk_activity) {
		this.pk_activity = pk_activity;
	}

	/**
	 * 获取活动规则主键
	 * @return pk_activity_type 活动规则主键
	 */
	public String getPk_activity_type() {
		return pk_activity_type;
	}

	/**
	 * 设置活动规则主键
	 * @param pk_activity_type 活动规则主键
	 */
	public void setPk_activity_type(String pk_activity_type) {
		this.pk_activity_type = pk_activity_type;
	}

	/**
	 * 获取主表主键
	 * @return pk_saledetail_h 主表主键
	 */
	public String getPk_saledetail_h() {
		return pk_saledetail_h;
	}

	/**
	 * 设置主表主键
	 * @param pk_saledetail_h 主表主键
	 */
	public void setPk_saledetail_h(String pk_saledetail_h) {
		this.pk_saledetail_h = pk_saledetail_h;
	}

	/**
	 * 获取主键
	 * @return pk_sale_share 主键
	 */
	public String getPk_sale_share() {
		return pk_sale_share;
	}

	/**
	 * 设置主键
	 * @param pk_sale_share 主键
	 */
	public void setPk_sale_share(String pk_sale_share) {
		this.pk_sale_share = pk_sale_share;
	}

}
