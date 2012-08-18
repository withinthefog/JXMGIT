package nc.vo.mallpos.sale;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 销售收入天汇总
 * 
 * @author chenjun
 * @date 2011-11-10 上午10:15:37
 */
public class MPOS_SaleDaySumVO extends SuperVO {
	private static final long serialVersionUID = 1L;
	
	private String pk_corp;

	private UFDate billdate;

	private UFDouble nmoney;

	private UFDouble nfee;

	private Integer billstate;

	private Integer voucher;

	private String pk_busibill;

	private String pk_sale_daysum;

	private String pk_cubasdoc;

	private String pk_balatype;

	private String pk_cashier;

	// ========================================
	
	private String balancode;
	private String balanname;
	private String usercode;
	private String username;
	private String custcode;
	private String custname;
	private String strbillstate;
	private String strvoucher;
	private String strpay;
	private String djbh;

	/**
	 * 获取djbh
	 * 
	 * @return djbh djbh
	 */
	public String getDjbh() {
		return djbh;
	}

	/**
	 * 设置djbh
	 * 
	 * @param djbh
	 *            djbh
	 */
	public void setDjbh(String djbh) {
		this.djbh = djbh;
	}

	/**
	 * 获取strpay
	 * 
	 * @return strpay strpay
	 */
	public String getStrpay() {
		return strpay;
	}

	/**
	 * 设置strpay
	 * 
	 * @param strpay
	 *            strpay
	 */
	public void setStrpay(String strpay) {
		this.strpay = strpay;
	}

	// ========================================
	/**
	 * @return the pk_busibill
	 */
	public String getPk_busibill() {
		return pk_busibill;
	}

	/**
	 * @param pk_busibill
	 *            the pk_busibill to set
	 */
	public void setPk_busibill(String pk_busibill) {
		this.pk_busibill = pk_busibill;
	}

	/**
	 * @return the voucher
	 */
	public Integer getVoucher() {
		return voucher;
	}

	/**
	 * @param voucher
	 *            the voucher to set
	 */
	public void setVoucher(Integer voucher) {
		this.voucher = voucher;
	}

	@Override
	public String getPKFieldName() {
		return "pk_sale_daysum";
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "mpos_sale_daysum";
	}

	/**
	 * @return the billdate
	 */
	public UFDate getBilldate() {
		return billdate;
	}

	/**
	 * @param billdate
	 *            the billdate to set
	 */
	public void setBilldate(UFDate billdate) {
		this.billdate = billdate;
	}

	/**
	 * @return the billstate
	 */
	public Integer getBillstate() {
		return billstate;
	}

	/**
	 * @param billstate
	 *            the billstate to set
	 */
	public void setBillstate(Integer billstate) {
		this.billstate = billstate;
	}

	/**
	 * @return the custcode
	 */
	public String getCustcode() {
		return custcode;
	}

	/**
	 * @param custcode
	 *            the custcode to set
	 */
	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}

	/**
	 * @return the custname
	 */
	public String getCustname() {
		return custname;
	}

	/**
	 * @param custname
	 *            the custname to set
	 */
	public void setCustname(String custname) {
		this.custname = custname;
	}

	/**
	 * @return the nfee
	 */
	public UFDouble getNfee() {
		return nfee;
	}

	/**
	 * @param nfee
	 *            the nfee to set
	 */
	public void setNfee(UFDouble nfee) {
		this.nfee = nfee;
	}

	/**
	 * @return the nmoney
	 */
	public UFDouble getNmoney() {
		return nmoney;
	}

	/**
	 * @param nmoney
	 *            the nmoney to set
	 */
	public void setNmoney(UFDouble nmoney) {
		this.nmoney = nmoney;
	}

	/**
	 * @return the usercode
	 */
	public String getUsercode() {
		return usercode;
	}

	/**
	 * @param usercode
	 *            the usercode to set
	 */
	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the strbillstate
	 */
	public String getStrbillstate() {
		return strbillstate;
	}

	/**
	 * @param strbillstate
	 *            the strbillstate to set
	 */
	public void setStrbillstate(String strbillstate) {
		this.strbillstate = strbillstate;
	}


	/**
	 * @return the strvoucher
	 */
	public String getStrvoucher() {
		return strvoucher;
	}

	/**
	 * @param strvoucher
	 *            the strvoucher to set
	 */
	public void setStrvoucher(String strvoucher) {
		this.strvoucher = strvoucher;
	}

	/**
	 * 获取pk_balatype
	 * 
	 * @return pk_balatype pk_balatype
	 */
	public String getPk_balatype() {
		return pk_balatype;
	}

	/**
	 * 设置pk_balatype
	 * 
	 * @param pk_balatype
	 *            pk_balatype
	 */
	public void setPk_balatype(String pk_balatype) {
		this.pk_balatype = pk_balatype;
	}

	/**
	 * 获取pk_cashier
	 * 
	 * @return pk_cashier pk_cashier
	 */
	public String getPk_cashier() {
		return pk_cashier;
	}

	/**
	 * 设置pk_cashier
	 * 
	 * @param pk_cashier
	 *            pk_cashier
	 */
	public void setPk_cashier(String pk_cashier) {
		this.pk_cashier = pk_cashier;
	}

	/**
	 * 获取pk_cubasdoc
	 * 
	 * @return pk_cubasdoc pk_cubasdoc
	 */
	public String getPk_cubasdoc() {
		return pk_cubasdoc;
	}

	/**
	 * 设置pk_cubasdoc
	 * 
	 * @param pk_cubasdoc
	 *            pk_cubasdoc
	 */
	public void setPk_cubasdoc(String pk_cubasdoc) {
		this.pk_cubasdoc = pk_cubasdoc;
	}

	/**
	 * 获取pk_sale_daysum
	 * 
	 * @return pk_sale_daysum pk_sale_daysum
	 */
	public String getPk_sale_daysum() {
		return pk_sale_daysum;
	}

	/**
	 * 设置pk_sale_daysum
	 * 
	 * @param pk_sale_daysum
	 *            pk_sale_daysum
	 */
	public void setPk_sale_daysum(String pk_sale_daysum) {
		this.pk_sale_daysum = pk_sale_daysum;
	}

	/**
	 * 获取pk_corp
	 * @return pk_corp pk_corp
	 */
	public String getPk_corp() {
		return pk_corp;
	}

	/**
	 * 设置pk_corp
	 * @param pk_corp pk_corp
	 */
	public void setPk_corp(String pk_corp) {
		this.pk_corp = pk_corp;
	}

	/**
	 * 获取balancode
	 * @return balancode balancode
	 */
	public String getBalancode() {
		return balancode;
	}

	/**
	 * 设置balancode
	 * @param balancode balancode
	 */
	public void setBalancode(String balancode) {
		this.balancode = balancode;
	}

	/**
	 * 获取balanname
	 * @return balanname balanname
	 */
	public String getBalanname() {
		return balanname;
	}

	/**
	 * 设置balanname
	 * @param balanname balanname
	 */
	public void setBalanname(String balanname) {
		this.balanname = balanname;
	}

}
