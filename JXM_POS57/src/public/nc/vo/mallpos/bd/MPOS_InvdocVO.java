package nc.vo.mallpos.bd;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

/**
 * ���(��Ʒ)����
 * 
 * @author cj
 * @date 2012-8-8 ����02:08:56
 */
public class MPOS_InvdocVO extends SuperVO {
	private static final long serialVersionUID = 1L;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�һ�������"))
	private UFDouble req_money;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�������"))
	private String inv_code;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�һ��������"))
	private Integer req_grade;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����"))
	private String pk_invdoc;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����ͺ�"))
	private String spectype;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "���۵���"))
	private UFDouble sale_price;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�������"))
	private String inv_name;

	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�ɱ�����"))
	private UFDouble cost_price;

	/**
	 * ����req_money��Getter����. ��������:2012-08-07 14:48:06
	 * 
	 * @return UFDouble
	 */
	public UFDouble getReq_money() {
		return req_money;
	}

	/**
	 * ����req_money��Setter����. ��������:2012-08-07 14:48:06
	 * 
	 * @param newReq_money
	 *            UFDouble
	 */
	public void setReq_money(UFDouble newReq_money) {
		this.req_money = newReq_money;
	}

	/**
	 * ����inv_code��Getter����. ��������:2012-08-07 14:48:06
	 * 
	 * @return String
	 */
	public String getInv_code() {
		return inv_code;
	}

	/**
	 * ����inv_code��Setter����. ��������:2012-08-07 14:48:06
	 * 
	 * @param newInv_code
	 *            String
	 */
	public void setInv_code(String newInv_code) {
		this.inv_code = newInv_code;
	}

	/**
	 * ����req_grade��Getter����. ��������:2012-08-07 14:48:06
	 * 
	 * @return Integer
	 */
	public Integer getReq_grade() {
		return req_grade;
	}

	/**
	 * ����req_grade��Setter����. ��������:2012-08-07 14:48:06
	 * 
	 * @param newReq_grade
	 *            Integer
	 */
	public void setReq_grade(Integer newReq_grade) {
		this.req_grade = newReq_grade;
	}

	/**
	 * ����pk_invdoc��Getter����. ��������:2012-08-07 14:48:06
	 * 
	 * @return String
	 */
	public String getPk_invdoc() {
		return pk_invdoc;
	}

	/**
	 * ����pk_invdoc��Setter����. ��������:2012-08-07 14:48:06
	 * 
	 * @param newPk_invdoc
	 *            String
	 */
	public void setPk_invdoc(String newPk_invdoc) {
		this.pk_invdoc = newPk_invdoc;
	}

	/**
	 * ����spectype��Getter����. ��������:2012-08-07 14:48:06
	 * 
	 * @return String
	 */
	public String getSpectype() {
		return spectype;
	}

	/**
	 * ����spectype��Setter����. ��������:2012-08-07 14:48:06
	 * 
	 * @param newSpectype
	 *            String
	 */
	public void setSpectype(String newSpectype) {
		this.spectype = newSpectype;
	}

	/**
	 * ����sale_price��Getter����. ��������:2012-08-07 14:48:06
	 * 
	 * @return UFDouble
	 */
	public UFDouble getSale_price() {
		return sale_price;
	}

	/**
	 * ����sale_price��Setter����. ��������:2012-08-07 14:48:06
	 * 
	 * @param newSale_price
	 *            UFDouble
	 */
	public void setSale_price(UFDouble newSale_price) {
		this.sale_price = newSale_price;
	}

	/**
	 * ����inv_name��Getter����. ��������:2012-08-07 14:48:06
	 * 
	 * @return String
	 */
	public String getInv_name() {
		return inv_name;
	}

	/**
	 * ����inv_name��Setter����. ��������:2012-08-07 14:48:06
	 * 
	 * @param newInv_name
	 *            String
	 */
	public void setInv_name(String newInv_name) {
		this.inv_name = newInv_name;
	}

	/**
	 * ����cost_price��Getter����. ��������:2012-08-07 14:48:06
	 * 
	 * @return UFDouble
	 */
	public UFDouble getCost_price() {
		return cost_price;
	}

	/**
	 * ����cost_price��Setter����. ��������:2012-08-07 14:48:06
	 * 
	 * @param newCost_price
	 *            UFDouble
	 */
	public void setCost_price(UFDouble newCost_price) {
		this.cost_price = newCost_price;
	}

	/**
	 * <p>
	 * ȡ�ø�VO�����ֶ�.
	 * <p>
	 * ��������:2012-08-07 14:48:06
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getParentPKFieldName() {
		return null;
	}

	/**
	 * <p>
	 * ȡ�ñ�����.
	 * <p>
	 * ��������:2012-08-07 14:48:06
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getPKFieldName() {
		return "pk_invdoc";
	}

	/**
	 * <p>
	 * ���ر�����.
	 * <p>
	 * ��������:2012-08-07 14:48:06
	 * 
	 * @return java.lang.String
	 */
	public java.lang.String getTableName() {
		return "MPOS_INVDOC";
	}

	/**
	 * ����Ĭ�Ϸ�ʽ����������.
	 * 
	 * ��������:2012-08-07 14:48:06
	 */
	public MPOS_InvdocVO() {
		super();
	}
}
