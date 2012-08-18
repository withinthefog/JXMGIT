package nc.vo.mallpos.bd;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

/**
 * 存货(礼品)档案
 * 
 * @author cj
 * @date 2012-8-8 下午02:08:56
 */
public class MPOS_InvdocVO extends SuperVO {
	private static final long serialVersionUID = 1L;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "兑换所需金额"))
	private UFDouble req_money;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "存货编码"))
	private String inv_code;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "兑换所需积分"))
	private Integer req_grade;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "主键"))
	private String pk_invdoc;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "规格型号"))
	private String spectype;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "销售单价"))
	private UFDouble sale_price;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "存货名称"))
	private String inv_name;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "成本单价"))
	private UFDouble cost_price;

	/**
	 * 属性req_money的Getter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @return UFDouble
	 */
	public UFDouble getReq_money() {
		return req_money;
	}

	/**
	 * 属性req_money的Setter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @param newReq_money
	 *            UFDouble
	 */
	public void setReq_money(UFDouble newReq_money) {
		this.req_money = newReq_money;
	}

	/**
	 * 属性inv_code的Getter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @return String
	 */
	public String getInv_code() {
		return inv_code;
	}

	/**
	 * 属性inv_code的Setter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @param newInv_code
	 *            String
	 */
	public void setInv_code(String newInv_code) {
		this.inv_code = newInv_code;
	}

	/**
	 * 属性req_grade的Getter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @return Integer
	 */
	public Integer getReq_grade() {
		return req_grade;
	}

	/**
	 * 属性req_grade的Setter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @param newReq_grade
	 *            Integer
	 */
	public void setReq_grade(Integer newReq_grade) {
		this.req_grade = newReq_grade;
	}

	/**
	 * 属性pk_invdoc的Getter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @return String
	 */
	public String getPk_invdoc() {
		return pk_invdoc;
	}

	/**
	 * 属性pk_invdoc的Setter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @param newPk_invdoc
	 *            String
	 */
	public void setPk_invdoc(String newPk_invdoc) {
		this.pk_invdoc = newPk_invdoc;
	}

	/**
	 * 属性spectype的Getter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @return String
	 */
	public String getSpectype() {
		return spectype;
	}

	/**
	 * 属性spectype的Setter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @param newSpectype
	 *            String
	 */
	public void setSpectype(String newSpectype) {
		this.spectype = newSpectype;
	}

	/**
	 * 属性sale_price的Getter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @return UFDouble
	 */
	public UFDouble getSale_price() {
		return sale_price;
	}

	/**
	 * 属性sale_price的Setter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @param newSale_price
	 *            UFDouble
	 */
	public void setSale_price(UFDouble newSale_price) {
		this.sale_price = newSale_price;
	}

	/**
	 * 属性inv_name的Getter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @return String
	 */
	public String getInv_name() {
		return inv_name;
	}

	/**
	 * 属性inv_name的Setter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @param newInv_name
	 *            String
	 */
	public void setInv_name(String newInv_name) {
		this.inv_name = newInv_name;
	}

	/**
	 * 属性cost_price的Getter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @return UFDouble
	 */
	public UFDouble getCost_price() {
		return cost_price;
	}

	/**
	 * 属性cost_price的Setter方法. 创建日期:2012-08-07 14:48:06
	 * 
	 * @param newCost_price
	 *            UFDouble
	 */
	public void setCost_price(UFDouble newCost_price) {
		this.cost_price = newCost_price;
	}

	/**
	 * <p>
	 * 取得父VO主键字段.
	 * <p>
	 * 创建日期:2012-08-07 14:48:06
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getParentPKFieldName() {
		return null;
	}

	/**
	 * <p>
	 * 取得表主键.
	 * <p>
	 * 创建日期:2012-08-07 14:48:06
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPKFieldName() {
		return "pk_invdoc";
	}

	/**
	 * <p>
	 * 返回表名称.
	 * <p>
	 * 创建日期:2012-08-07 14:48:06
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "MPOS_INVDOC";
	}

	/**
	 * 按照默认方式创建构造子.
	 * 
	 * 创建日期:2012-08-07 14:48:06
	 */
	public MPOS_InvdocVO() {
		super();
	}
}
