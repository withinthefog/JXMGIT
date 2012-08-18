package nc.vo.mallpos.invexchange;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;

/**
 * 礼品兑换主表类
 * 
 * @author cj
 * @date 2012-8-10 下午02:39:31
 */
public class MPOS_InvExchangeHeadVO extends SuperVO {

	private static final long serialVersionUID = 1L;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "主键"))
	private String pk_invexchange_h;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "会员卡主键"))
	private String pk_vipdoc_card;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "活动主键"))
	private String pk_activity;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "收款金额"))
	private String receipt_money;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "制单人"))
	private String pk_creator;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "制单日期"))
	private String create_date;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "是否已退还"))
	private UFBoolean isreturn;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "是否积分换礼"))
	private UFBoolean isgrade_exchange;
	
	private String vbillno;
	private UFDate bill_date;

	@Override
	public String getPKFieldName() {
		return "pk_invexchange_h";
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "mpos_invexchange_h";
	}

	/**
	 * 获取create_date
	 * @return create_date create_date
	 */
	public String getCreate_date() {
		return create_date;
	}

	/**
	 * 设置create_date
	 * @param create_date create_date
	 */
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
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
	 * 获取pk_creator
	 * @return pk_creator pk_creator
	 */
	public String getPk_creator() {
		return pk_creator;
	}

	/**
	 * 设置pk_creator
	 * @param pk_creator pk_creator
	 */
	public void setPk_creator(String pk_creator) {
		this.pk_creator = pk_creator;
	}
	
	/**
	 * 获取pk_invexchange_h
	 * @return pk_invexchange_h pk_invexchange_h
	 */
	public String getPk_invexchange_h() {
		return pk_invexchange_h;
	}

	/**
	 * 设置pk_invexchange_h
	 * @param pk_invexchange_h pk_invexchange_h
	 */
	public void setPk_invexchange_h(String pk_invexchange_h) {
		this.pk_invexchange_h = pk_invexchange_h;
	}

	/**
	 * 获取receipt_money
	 * @return receipt_money receipt_money
	 */
	public String getReceipt_money() {
		return receipt_money;
	}

	/**
	 * 设置receipt_money
	 * @param receipt_money receipt_money
	 */
	public void setReceipt_money(String receipt_money) {
		this.receipt_money = receipt_money;
	}

	public String getPk_vipdoc_card() {
		return pk_vipdoc_card;
	}

	public void setPk_vipdoc_card(String pk_vipdoc_card) {
		this.pk_vipdoc_card = pk_vipdoc_card;
	}

	public UFBoolean getIsreturn() {
		return isreturn;
	}

	public void setIsreturn(UFBoolean isreturn) {
		this.isreturn = isreturn;
	}

	public UFBoolean getIsgrade_exchange() {
		return isgrade_exchange;
	}

	public void setIsgrade_exchange(UFBoolean isgrade_exchange) {
		this.isgrade_exchange = isgrade_exchange;
	}

	public UFDate getBill_date() {
		return bill_date;
	}

	public void setBill_date(UFDate bill_date) {
		this.bill_date = bill_date;
	}

	public String getVbillno() {
		return vbillno;
	}

	public void setVbillno(String vbillno) {
		this.vbillno = vbillno;
	}

}
