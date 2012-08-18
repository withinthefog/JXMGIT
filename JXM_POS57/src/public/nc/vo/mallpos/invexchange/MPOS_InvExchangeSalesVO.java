package nc.vo.mallpos.invexchange;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;

/**
 * ��Ʒ�һ��ӱ���
 * 
 * @author cj
 * @date 2012-8-10 ����02:39:51
 */
public class MPOS_InvExchangeSalesVO extends SuperVO {

	private static final long serialVersionUID = 1L;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����"))
	private String pk_invexchange_sales;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��������"))
	private String pk_invexchange_h;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "������ϸ��������"))
	private String pk_saledetail_h;

	@Override
	public String getPKFieldName() {
		return "pk_invexchange_sales";
	}

	@Override
	public String getParentPKFieldName() {
		return "pk_invexchange_h";
	}

	@Override
	public String getTableName() {
		return "mpos_invexchange_sales";
	}

	/**
	 * ��ȡpk_invexchange_h
	 * @return pk_invexchange_h pk_invexchange_h
	 */
	public String getPk_invexchange_h() {
		return pk_invexchange_h;
	}

	/**
	 * ����pk_invexchange_h
	 * @param pk_invexchange_h pk_invexchange_h
	 */
	public void setPk_invexchange_h(String pk_invexchange_h) {
		this.pk_invexchange_h = pk_invexchange_h;
	}

	/**
	 * ��ȡpk_saledetail_h
	 * @return pk_saledetail_h pk_saledetail_h
	 */
	public String getPk_saledetail_h() {
		return pk_saledetail_h;
	}

	/**
	 * ����pk_saledetail_h
	 * @param pk_saledetail_h pk_saledetail_h
	 */
	public void setPk_saledetail_h(String pk_saledetail_h) {
		this.pk_saledetail_h = pk_saledetail_h;
	}

	public String getPk_invexchange_sales() {
		return pk_invexchange_sales;
	}

	public void setPk_invexchange_sales(String pk_invexchange_sales) {
		this.pk_invexchange_sales = pk_invexchange_sales;
	}

}
