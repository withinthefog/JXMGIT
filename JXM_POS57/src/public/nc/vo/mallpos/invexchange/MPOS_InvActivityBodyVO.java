package nc.vo.mallpos.invexchange;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class MPOS_InvActivityBodyVO extends SuperVO {
	
	private static final long serialVersionUID = -2877767007653907650L;
	
	private String pk_activity_inv_b;
	private String pk_activity_inv_h;
	private String pk_invdoc;
	private String pk_cubasdoc;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "成本单价"))
	private UFDouble cost_price;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "销售单价"))
	private UFDouble sale_price;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "所需积分"))
	private UFDouble cost_grade;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "现存数量"))
	private UFDouble balancenum;

	@Override
	public String getPKFieldName() {
		return "pk_activity_inv_b";
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "mpos_activity_inv_b";
	}

	public String getPk_activity_inv_b() {
		return pk_activity_inv_b;
	}

	public void setPk_activity_inv_b(String pk_activity_inv_b) {
		this.pk_activity_inv_b = pk_activity_inv_b;
	}

	public String getPk_activity_inv_h() {
		return pk_activity_inv_h;
	}

	public void setPk_activity_inv_h(String pk_activity_inv_h) {
		this.pk_activity_inv_h = pk_activity_inv_h;
	}

	public String getPk_invdoc() {
		return pk_invdoc;
	}

	public void setPk_invdoc(String pk_invdoc) {
		this.pk_invdoc = pk_invdoc;
	}

	public String getPk_cubasdoc() {
		return pk_cubasdoc;
	}

	public void setPk_cubasdoc(String pk_cubasdoc) {
		this.pk_cubasdoc = pk_cubasdoc;
	}

	public UFDouble getCost_price() {
		return cost_price;
	}

	public void setCost_price(UFDouble cost_price) {
		this.cost_price = cost_price;
	}

	public UFDouble getSale_price() {
		return sale_price;
	}

	public void setSale_price(UFDouble sale_price) {
		this.sale_price = sale_price;
	}

	public UFDouble getCost_grade() {
		return cost_grade;
	}

	public void setCost_grade(UFDouble cost_grade) {
		this.cost_grade = cost_grade;
	}

	public UFDouble getBalancenum() {
		return balancenum;
	}

	public void setBalancenum(UFDouble balancenum) {
		this.balancenum = balancenum;
	}

}
