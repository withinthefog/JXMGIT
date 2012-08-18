package nc.vo.mallpos.pos;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 交易明细表头
 * 
 * @author chenjun
 * @date 2011-12-27 上午10:00:22
 */
public class MPOS_SaleDetailHeadVO extends SuperVO {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "主键"))
	private String pk_saledetail_h;

	/**
	 * 销售流水号
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "销售流水号"))
	private String sale_no;

	/**
	 * 单据日期
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "单据日期"))
	private UFDate bill_date;

	/**
	 * 交款凭证单据号
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "交款单号"))
	private String proof_billno;

	/**
	 * 销售方式 销售、退貨
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "销售方式"))
	private String sale_type;

	/**
	 * 商户基本档案主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "商户基本档案主键"))
	private String pk_cubasdoc;

	/**
	 * 收银员主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "收银员主键"))
	private String pk_cashier;

	/**
	 * 铺位号
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "铺位号"))
	private String bunkno;

	/**
	 * 备注
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "备注"))
	private String memo;

	/**
	 * 是否已生成天汇总数据
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "是否已天汇总"))
	private UFBoolean issend;

	/**
	 * 本单积分
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "本单积分"))
	private UFDouble bill_grade;

	/**
	 * 商品大类主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "商品大类主键"))
	private String pk_goods_main;

	/**
	 * 商品小类主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "商品小类主键"))
	private String pk_goods_sub;

	/**
	 * 客户姓名
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "客户姓名"))
	private String cust_name;

	/**
	 * 客户电话
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "客户电话"))
	private String cust_phone;

	/**
	 * 客户地址
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "客户地址"))
	private String cust_address;

	/**
	 * 客户楼盘主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "客户楼盘主键"))
	private String pk_houses;

	/**
	 * 会员卡主键
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "会员卡主键"))
	private String pk_vip_card;

	/**
	 * 应收总额
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "应收总额"))
	private UFDouble receivable_amount;
	
	/**
	 * 源单据主键(主要确定分摊的源单据)
	 */
	private String spk_saledetail_h;
	
	/**
	 * 公司主键
	 */
	private String pk_corp;

	// -------------------------------
	private String custcode;

	private String custname;

	private String cashiercode;

	private String cashiername;

	@Override
	public String getPKFieldName() {
		return "pk_saledetail_h";
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "mpos_saledetail_h";
	}

	/**
	 * 获取铺位号
	 * 
	 * @return bunkno 铺位号
	 */
	public String getBunkno() {
		return bunkno;
	}

	/**
	 * 设置铺位号
	 * 
	 * @param bunkno
	 *            铺位号
	 */
	public void setBunkno(String bunkno) {
		this.bunkno = bunkno;
	}

	/**
	 * 获取cashiercode
	 * 
	 * @return cashiercode cashiercode
	 */
	public String getCashiercode() {
		return cashiercode;
	}

	/**
	 * 设置cashiercode
	 * 
	 * @param cashiercode
	 *            cashiercode
	 */
	public void setCashiercode(String cashiercode) {
		this.cashiercode = cashiercode;
	}

	/**
	 * 获取cashiername
	 * 
	 * @return cashiername cashiername
	 */
	public String getCashiername() {
		return cashiername;
	}

	/**
	 * 设置cashiername
	 * 
	 * @param cashiername
	 *            cashiername
	 */
	public void setCashiername(String cashiername) {
		this.cashiername = cashiername;
	}

	/**
	 * 获取-------------------
	 * 
	 * @return custcode -------------------
	 */
	public String getCustcode() {
		return custcode;
	}

	/**
	 * 设置-------------------
	 * 
	 * @param custcode
	 *            -------------------
	 */
	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}

	/**
	 * 获取custname
	 * 
	 * @return custname custname
	 */
	public String getCustname() {
		return custname;
	}

	/**
	 * 设置custname
	 * 
	 * @param custname
	 *            custname
	 */
	public void setCustname(String custname) {
		this.custname = custname;
	}

	/**
	 * 获取是否已生成天汇总数据
	 * 
	 * @return issend 是否已生成天汇总数据
	 */
	public UFBoolean getIssend() {
		return issend;
	}

	/**
	 * 设置是否已生成天汇总数据
	 * 
	 * @param issend
	 *            是否已生成天汇总数据
	 */
	public void setIssend(UFBoolean issend) {
		this.issend = issend;
	}

	/**
	 * 获取备注
	 * 
	 * @return memo 备注
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置备注
	 * 
	 * @param memo
	 *            备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 获取收银员主键
	 * 
	 * @return pk_cashier 收银员主键
	 */
	public String getPk_cashier() {
		return pk_cashier;
	}

	/**
	 * 设置收银员主键
	 * 
	 * @param pk_cashier
	 *            收银员主键
	 */
	public void setPk_cashier(String pk_cashier) {
		this.pk_cashier = pk_cashier;
	}

	/**
	 * 获取商户基本档案主键
	 * 
	 * @return pk_cubasdoc 商户基本档案主键
	 */
	public String getPk_cubasdoc() {
		return pk_cubasdoc;
	}

	/**
	 * 设置商户基本档案主键
	 * 
	 * @param pk_cubasdoc
	 *            商户基本档案主键
	 */
	public void setPk_cubasdoc(String pk_cubasdoc) {
		this.pk_cubasdoc = pk_cubasdoc;
	}

	/**
	 * 获取主键
	 * 
	 * @return pk_saledetail_h 主键
	 */
	public String getPk_saledetail_h() {
		return pk_saledetail_h;
	}

	/**
	 * 设置主键
	 * 
	 * @param pk_saledetail_h
	 *            主键
	 */
	public void setPk_saledetail_h(String pk_saledetail_h) {
		this.pk_saledetail_h = pk_saledetail_h;
	}

	/**
	 * 获取交款凭证单据号
	 * 
	 * @return proof_billno 交款凭证单据号
	 */
	public String getProof_billno() {
		return proof_billno;
	}

	/**
	 * 设置交款凭证单据号
	 * 
	 * @param proof_billno
	 *            交款凭证单据号
	 */
	public void setProof_billno(String proof_billno) {
		this.proof_billno = proof_billno;
	}

	/**
	 * 获取销售流水号
	 * 
	 * @return sale_no 销售流水号
	 */
	public String getSale_no() {
		return sale_no;
	}

	/**
	 * 设置销售流水号
	 * 
	 * @param sale_no
	 *            销售流水号
	 */
	public void setSale_no(String sale_no) {
		this.sale_no = sale_no;
	}

	/**
	 * 获取销售方式销售、退貨
	 * 
	 * @return sale_type 销售方式销售、退貨
	 */
	public String getSale_type() {
		return sale_type;
	}

	/**
	 * 设置销售方式销售、退貨
	 * 
	 * @param sale_type
	 *            销售方式销售、退貨
	 */
	public void setSale_type(String sale_type) {
		this.sale_type = sale_type;
	}

	/**
	 * 获取本单积分
	 * 
	 * @return bill_grade 本单积分
	 */
	public UFDouble getBill_grade() {
		return bill_grade;
	}

	/**
	 * 设置本单积分
	 * 
	 * @param bill_grade
	 *            本单积分
	 */
	public void setBill_grade(UFDouble bill_grade) {
		this.bill_grade = bill_grade;
	}

	/**
	 * 获取客户地址
	 * 
	 * @return cust_address 客户地址
	 */
	public String getCust_address() {
		return cust_address;
	}

	/**
	 * 设置客户地址
	 * 
	 * @param cust_address
	 *            客户地址
	 */
	public void setCust_address(String cust_address) {
		this.cust_address = cust_address;
	}

	/**
	 * 获取客户姓名
	 * 
	 * @return cust_name 客户姓名
	 */
	public String getCust_name() {
		return cust_name;
	}

	/**
	 * 设置客户姓名
	 * 
	 * @param cust_name
	 *            客户姓名
	 */
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}

	/**
	 * 获取客户电话
	 * 
	 * @return cust_phone 客户电话
	 */
	public String getCust_phone() {
		return cust_phone;
	}

	/**
	 * 设置客户电话
	 * 
	 * @param cust_phone
	 *            客户电话
	 */
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}

	/**
	 * 获取商品大类主键
	 * 
	 * @return pk_goods_main 商品大类主键
	 */
	public String getPk_goods_main() {
		return pk_goods_main;
	}

	/**
	 * 设置商品大类主键
	 * 
	 * @param pk_goods_main
	 *            商品大类主键
	 */
	public void setPk_goods_main(String pk_goods_main) {
		this.pk_goods_main = pk_goods_main;
	}

	/**
	 * 获取商品小类主键
	 * 
	 * @return pk_goods_sub 商品小类主键
	 */
	public String getPk_goods_sub() {
		return pk_goods_sub;
	}

	/**
	 * 设置商品小类主键
	 * 
	 * @param pk_goods_sub
	 *            商品小类主键
	 */
	public void setPk_goods_sub(String pk_goods_sub) {
		this.pk_goods_sub = pk_goods_sub;
	}

	/**
	 * 获取客户楼盘主键
	 * 
	 * @return pk_houses 客户楼盘主键
	 */
	public String getPk_houses() {
		return pk_houses;
	}

	/**
	 * 设置客户楼盘主键
	 * 
	 * @param pk_houses
	 *            客户楼盘主键
	 */
	public void setPk_houses(String pk_houses) {
		this.pk_houses = pk_houses;
	}

	/**
	 * 获取会员卡主键
	 * 
	 * @return pk_vip_card 会员卡主键
	 */
	public String getPk_vip_card() {
		return pk_vip_card;
	}

	/**
	 * 设置会员卡主键
	 * 
	 * @param pk_vip_card
	 *            会员卡主键
	 */
	public void setPk_vip_card(String pk_vip_card) {
		this.pk_vip_card = pk_vip_card;
	}

	/**
	 * 获取单据日期
	 * 
	 * @return bill_date 单据日期
	 */
	public UFDate getBill_date() {
		return bill_date;
	}

	/**
	 * 设置单据日期
	 * 
	 * @param bill_date
	 *            单据日期
	 */
	public void setBill_date(UFDate bill_date) {
		this.bill_date = bill_date;
	}

	/**
	 * 获取应收总额
	 * 
	 * @return receivable_amount 应收总额
	 */
	public UFDouble getReceivable_amount() {
		return receivable_amount;
	}

	/**
	 * 设置应收总额
	 * 
	 * @param receivable_amount
	 *            应收总额
	 */
	public void setReceivable_amount(UFDouble receivable_amount) {
		this.receivable_amount = receivable_amount;
	}

	/**
	 * 获取源单据主键
	 * @return spk_saledetail_h 源单据主键
	 */
	public String getSpk_saledetail_h() {
		return spk_saledetail_h;
	}

	/**
	 * 设置源单据主键
	 * @param spk_saledetail_h 源单据主键
	 */
	public void setSpk_saledetail_h(String spk_saledetail_h) {
		this.spk_saledetail_h = spk_saledetail_h;
	}

	/**
	 * 获取公司主键
	 * @return pk_corp 公司主键
	 */
	public String getPk_corp() {
		return pk_corp;
	}

	/**
	 * 设置公司主键
	 * @param pk_corp 公司主键
	 */
	public void setPk_corp(String pk_corp) {
		this.pk_corp = pk_corp;
	}

}
