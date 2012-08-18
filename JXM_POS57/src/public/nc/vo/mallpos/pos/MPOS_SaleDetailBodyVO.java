package nc.vo.mallpos.pos;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;

/**
 * 交易明细表体
 * 
 * @author chenjun
 * @date 2011-12-27 上午10:00:22
 */
public class MPOS_SaleDetailBodyVO extends SuperVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "主键"))
	private String pk_saledetail_b;

	/**
	 * 主表主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "主表主键"))
	private String pk_saledetail_h;

	/**
	 * 销售时间
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "销售时间"))
	private UFDateTime sale_datetime;

	/**
	 * 结算方式
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "结算方式"))
	private String pk_balatype;

	/**
	 * 实收金额
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "实收金额"))
	private UFDouble received_money;

	/**
	 * 商场承担金额
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "商场承担金额"))
	private UFDouble mall_money;

	/**
	 * 商户承担金额
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "商户承担金额"))
	private UFDouble cust_money;

	/**
	 * 付款卡号
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "付款卡号"))
	private String cardno;

	/**
	 * 退货原始单主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "退货原始单主键"))
	private String rt_pk_saledetail;

	/**
	 * 刷卡-系統流水号-用于撤消
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "刷卡系統流水号"))
	private String systracdno;

	/**
	 * 手续费
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "手续费"))
	private UFDouble nfee;

	/**
	 * 折现积分
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "折现积分"))
	private UFDouble discash_grade;

	/**
	 * 消费积分
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "消费积分"))
	private UFDouble exp_grade;
	
	/**
	 * POS机品牌,用于退货
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "POS机品牌"))
	private Integer brand;
	
	/**
	 * 积分系数,用于退货
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "积分系数"))
	private UFDouble grade_cofit;

	// -------------------------------
	/**
	 * 退货原始单号
	 */
	private String returngoods_no;

	/**
	 * 可退货金额：主要用于退货检验
	 */
	private UFDouble canreturnmoney;

	/**
	 * 结算方式名称
	 */
	private String balanname;

	@Override
	public String getPKFieldName() {
		return "pk_saledetail_b";
	}

	@Override
	public String getParentPKFieldName() {
		return "pk_saledetail_h";
	}

	@Override
	public String getTableName() {
		return "mpos_saledetail_b";
	}

	/**
	 * 获取可退货金额：主要用于退货检验
	 * 
	 * @return canreturnmoney 可退货金额：主要用于退货检验
	 */
	public UFDouble getCanreturnmoney() {
		return canreturnmoney;
	}

	/**
	 * 设置可退货金额：主要用于退货检验
	 * 
	 * @param canreturnmoney
	 *            可退货金额：主要用于退货检验
	 */
	public void setCanreturnmoney(UFDouble canreturnmoney) {
		this.canreturnmoney = canreturnmoney;
	}

	/**
	 * 获取付款卡号
	 * 
	 * @return cardno 付款卡号
	 */
	public String getCardno() {
		return cardno;
	}

	/**
	 * 设置付款卡号
	 * 
	 * @param cardno
	 *            付款卡号
	 */
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}

	/**
	 * 获取手续费
	 * 
	 * @return nfee 手续费
	 */
	public UFDouble getNfee() {
		return nfee;
	}

	/**
	 * 设置手续费
	 * 
	 * @param nfee
	 *            手续费
	 */
	public void setNfee(UFDouble nfee) {
		this.nfee = nfee;
	}

	/**
	 * 获取主键
	 * 
	 * @return pk_saledetail_b 主键
	 */
	public String getPk_saledetail_b() {
		return pk_saledetail_b;
	}

	/**
	 * 设置主键
	 * 
	 * @param pk_saledetail_b
	 *            主键
	 */
	public void setPk_saledetail_b(String pk_saledetail_b) {
		this.pk_saledetail_b = pk_saledetail_b;
	}

	/**
	 * 获取主表主键
	 * 
	 * @return pk_saledetail_h 主表主键
	 */
	public String getPk_saledetail_h() {
		return pk_saledetail_h;
	}

	/**
	 * 设置主表主键
	 * 
	 * @param pk_saledetail_h
	 *            主表主键
	 */
	public void setPk_saledetail_h(String pk_saledetail_h) {
		this.pk_saledetail_h = pk_saledetail_h;
	}

	/**
	 * 获取-------------------
	 * 
	 * @return returngoods_no -------------------
	 */
	public String getReturngoods_no() {
		return returngoods_no;
	}

	/**
	 * 设置-------------------
	 * 
	 * @param returngoods_no
	 *            -------------------
	 */
	public void setReturngoods_no(String returngoods_no) {
		this.returngoods_no = returngoods_no;
	}

	/**
	 * 获取退货原始单主键
	 * 
	 * @return rt_pk_saledetail 退货原始单主键
	 */
	public String getRt_pk_saledetail() {
		return rt_pk_saledetail;
	}

	/**
	 * 设置退货原始单主键
	 * 
	 * @param rt_pk_saledetail
	 *            退货原始单主键
	 */
	public void setRt_pk_saledetail(String rt_pk_saledetail) {
		this.rt_pk_saledetail = rt_pk_saledetail;
	}

	/**
	 * 获取销售时间
	 * 
	 * @return sale_datetime 销售时间
	 */
	public UFDateTime getSale_datetime() {
		return sale_datetime;
	}

	/**
	 * 设置销售时间
	 * 
	 * @param sale_datetime
	 *            销售时间
	 */
	public void setSale_datetime(UFDateTime sale_datetime) {
		this.sale_datetime = sale_datetime;
	}

	/**
	 * 获取刷卡-系統流水号-用于撤消
	 * 
	 * @return systracdno 刷卡-系統流水号-用于撤消
	 */
	public String getSystracdno() {
		return systracdno;
	}

	/**
	 * 设置刷卡-系統流水号-用于撤消
	 * 
	 * @param systracdno
	 *            刷卡-系統流水号-用于撤消
	 */
	public void setSystracdno(String systracdno) {
		this.systracdno = systracdno;
	}

	/**
	 * 获取结算方式
	 * 
	 * @return pk_balatype 结算方式
	 */
	public String getPk_balatype() {
		return pk_balatype;
	}

	/**
	 * 设置结算方式
	 * 
	 * @param pk_balatype
	 *            结算方式
	 */
	public void setPk_balatype(String pk_balatype) {
		this.pk_balatype = pk_balatype;
	}

	/**
	 * 获取商户承担金额
	 * 
	 * @return cust_money 商户承担金额
	 */
	public UFDouble getCust_money() {
		return cust_money;
	}

	/**
	 * 设置商户承担金额
	 * 
	 * @param cust_money
	 *            商户承担金额
	 */
	public void setCust_money(UFDouble cust_money) {
		this.cust_money = cust_money;
	}

	/**
	 * 获取商场承担金额
	 * 
	 * @return mall_money 商场承担金额
	 */
	public UFDouble getMall_money() {
		return mall_money;
	}

	/**
	 * 设置商场承担金额
	 * 
	 * @param mall_money
	 *            商场承担金额
	 */
	public void setMall_money(UFDouble mall_money) {
		this.mall_money = mall_money;
	}

	/**
	 * 获取实收金额
	 * 
	 * @return received_money 实收金额
	 */
	public UFDouble getReceived_money() {
		return received_money;
	}

	/**
	 * 设置实收金额
	 * 
	 * @param received_money
	 *            实收金额
	 */
	public void setReceived_money(UFDouble received_money) {
		this.received_money = received_money;
	}

	/**
	 * 获取结算方式名称
	 * 
	 * @return balanname 结算方式名称
	 */
	public String getBalanname() {
		return balanname;
	}

	/**
	 * 设置结算方式名称
	 * 
	 * @param balanname
	 *            结算方式名称
	 */
	public void setBalanname(String balanname) {
		this.balanname = balanname;
	}

	/**
	 * 获取brand
	 * 
	 * @return brand brand
	 */
	public Integer getBrand() {
		return brand;
	}

	/**
	 * 设置brand
	 * 
	 * @param brand
	 *            brand
	 */
	public void setBrand(Integer brand) {
		this.brand = brand;
	}

	/**
	 * 获取折现积分
	 * 
	 * @return discash_grade 折现积分
	 */
	public UFDouble getDiscash_grade() {
		return discash_grade;
	}

	/**
	 * 设置折现积分
	 * 
	 * @param discash_grade
	 *            折现积分
	 */
	public void setDiscash_grade(UFDouble discash_grade) {
		this.discash_grade = discash_grade;
	}

	/**
	 * 获取消费积分
	 * 
	 * @return exp_grade 消费积分
	 */
	public UFDouble getExp_grade() {
		return exp_grade;
	}

	/**
	 * 设置消费积分
	 * 
	 * @param exp_grade
	 *            消费积分
	 */
	public void setExp_grade(UFDouble exp_grade) {
		this.exp_grade = exp_grade;
	}

	/**
	 * 获取积分系数用于退货
	 * @return grade_cofit 积分系数用于退货
	 */
	public UFDouble getGrade_cofit() {
		return grade_cofit;
	}

	/**
	 * 设置积分系数用于退货
	 * @param grade_cofit 积分系数用于退货
	 */
	public void setGrade_cofit(UFDouble grade_cofit) {
		this.grade_cofit = grade_cofit;
	}

}
