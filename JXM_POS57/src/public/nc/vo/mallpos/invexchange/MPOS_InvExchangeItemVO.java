package nc.vo.mallpos.invexchange;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class MPOS_InvExchangeItemVO extends SuperVO {
	
	private static final long serialVersionUID = 3346723471341289082L;
	
	private String pk_invexchange_detail;
	private String pk_invexchange_h;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "成本单价"))
	private UFDouble cost_price;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "另收款金额"))
	private UFDouble receipt_money;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "分摊成本金额"))
	private UFDouble apportionmentmny;
	private String pk_invdoc;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "数量"))
	private Integer quantity;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "兑换所需积分"))
	private UFDouble exchange_grade;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "积分单价"))
	private UFDouble gradeprice;
	private String pk_cubasdoc;


	@Override
	public String getPKFieldName() {
		return "pk_invexchange_detail";
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "mpos_invexchange_detail";
	}

	public String getPk_invexchange_h() {
		return pk_invexchange_h;
	}

	public void setPk_invexchange_h(String pk_invexchange_h) {
		this.pk_invexchange_h = pk_invexchange_h;
	}

	public UFDouble getCost_price() {
		return cost_price;
	}

	public void setCost_price(UFDouble cost_price) {
		this.cost_price = cost_price;
	}

	public UFDouble getReceipt_money() {
		return receipt_money;
	}

	public void setReceipt_money(UFDouble receipt_money) {
		this.receipt_money = receipt_money;
	}

	public UFDouble getApportionmentmny() {
		return apportionmentmny;
	}

	public void setApportionmentmny(UFDouble apportionmentmny) {
		this.apportionmentmny = apportionmentmny;
	}

	public String getPk_invdoc() {
		return pk_invdoc;
	}

	public void setPk_invdoc(String pk_invdoc) {
		this.pk_invdoc = pk_invdoc;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPk_cubasdoc() {
		return pk_cubasdoc;
	}

	public void setPk_cubasdoc(String pk_cubasdoc) {
		this.pk_cubasdoc = pk_cubasdoc;
	}

	public UFDouble getExchange_grade() {
		return exchange_grade;
	}

	public void setExchange_grade(UFDouble exchange_grade) {
		this.exchange_grade = exchange_grade;
	}

	public UFDouble getGradeprice() {
		return gradeprice;
	}

	public void setGradeprice(UFDouble gradeprice) {
		this.gradeprice = gradeprice;
	}

	public String getPk_invexchange_detail() {
		return pk_invexchange_detail;
	}

	public void setPk_invexchange_detail(String pk_invexchange_detail) {
		this.pk_invexchange_detail = pk_invexchange_detail;
	}

}
