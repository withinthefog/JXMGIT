package nc.vo.mallpos.pos;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

/**
 * ������ϸ��̯����
 * @author cj
 * @date 2012-8-16 ����10:25:25
 */
public class MPOS_SaleDetailShareVO extends SuperVO{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "����"))
	private String pk_sale_share;
	/**
	 * ��������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��������"))
	private String pk_saledetail_h;
	/**
	 * ���������
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�����"))
	private String pk_activity_type;
	
	/**
	 * �����
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�"))
	private String pk_activity;
	
	/**
	 * �ۿ���
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�ۿ���"))
	private UFDouble discount_rate;

	/**
	 * �ۿ۽��
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�ۿ۽��"))
	private UFDouble discount_money;
	/**
	 * ��ǰ�ܶ�
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��ǰ�ܶ�"))
	private UFDouble discount_be_amount;
	/**
	 * �ۺ��ܶ�
	 */
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "�ۺ��ܶ�"))
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
	 * ��ȡ�ۺ��ܶ�
	 * @return discount_af_amount �ۺ��ܶ�
	 */
	public UFDouble getDiscount_af_amount() {
		return discount_af_amount;
	}

	/**
	 * �����ۺ��ܶ�
	 * @param discount_af_amount �ۺ��ܶ�
	 */
	public void setDiscount_af_amount(UFDouble discount_af_amount) {
		this.discount_af_amount = discount_af_amount;
	}

	/**
	 * ��ȡ��ǰ�ܶ�
	 * @return discount_be_amount ��ǰ�ܶ�
	 */
	public UFDouble getDiscount_be_amount() {
		return discount_be_amount;
	}

	/**
	 * ������ǰ�ܶ�
	 * @param discount_be_amount ��ǰ�ܶ�
	 */
	public void setDiscount_be_amount(UFDouble discount_be_amount) {
		this.discount_be_amount = discount_be_amount;
	}

	/**
	 * ��ȡ�ۿ۽��
	 * @return discount_money �ۿ۽��
	 */
	public UFDouble getDiscount_money() {
		return discount_money;
	}

	/**
	 * �����ۿ۽��
	 * @param discount_money �ۿ۽��
	 */
	public void setDiscount_money(UFDouble discount_money) {
		this.discount_money = discount_money;
	}

	/**
	 * ��ȡ�ۿ���
	 * @return discount_rate �ۿ���
	 */
	public UFDouble getDiscount_rate() {
		return discount_rate;
	}

	/**
	 * �����ۿ���
	 * @param discount_rate �ۿ���
	 */
	public void setDiscount_rate(UFDouble discount_rate) {
		this.discount_rate = discount_rate;
	}

	/**
	 * ��ȡ�����
	 * @return pk_activity �����
	 */
	public String getPk_activity() {
		return pk_activity;
	}

	/**
	 * ���û����
	 * @param pk_activity �����
	 */
	public void setPk_activity(String pk_activity) {
		this.pk_activity = pk_activity;
	}

	/**
	 * ��ȡ���������
	 * @return pk_activity_type ���������
	 */
	public String getPk_activity_type() {
		return pk_activity_type;
	}

	/**
	 * ���û��������
	 * @param pk_activity_type ���������
	 */
	public void setPk_activity_type(String pk_activity_type) {
		this.pk_activity_type = pk_activity_type;
	}

	/**
	 * ��ȡ��������
	 * @return pk_saledetail_h ��������
	 */
	public String getPk_saledetail_h() {
		return pk_saledetail_h;
	}

	/**
	 * ������������
	 * @param pk_saledetail_h ��������
	 */
	public void setPk_saledetail_h(String pk_saledetail_h) {
		this.pk_saledetail_h = pk_saledetail_h;
	}

	/**
	 * ��ȡ����
	 * @return pk_sale_share ����
	 */
	public String getPk_sale_share() {
		return pk_sale_share;
	}

	/**
	 * ��������
	 * @param pk_sale_share ����
	 */
	public void setPk_sale_share(String pk_sale_share) {
		this.pk_sale_share = pk_sale_share;
	}

}
