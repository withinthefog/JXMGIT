package nc.itf.mallpos.pub;

import nc.vo.mallpos.cost.MPOS_CostVO;
import nc.vo.mallpos.pos.MPOS_CustdocVO;
import nc.vo.mallpos.pos.MPOS_DiscountTempVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailBodyVO;
import nc.vo.mallpos.pub.MPOS_ArgConfigVO;
import nc.vo.mallpos.pub.MPOS_BalaTypeConfigVO;
import nc.vo.mallpos.pub.MPOS_LogVO;
import nc.vo.mallpos.sale.MPOS_PaymentVO;
import nc.vo.mallpos.sale.MPOS_ReceivablesVO;
import nc.vo.mallpos.sale.MPOS_SaleDaySumVO;
import nc.vo.mallpos.voucher.MPOS_VoucherConfigVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ���ݲ�ѯ�ӿ�
 * 
 * @author chenjun
 * @date 2011-10-9 ����11:05:04
 */
public interface MPOS_PubQueryItf {

	public MPOS_SaleDaySumVO[] querySale(String pk_corp, String p_sqlWhere) throws Exception;

	public MPOS_CostVO[] queryCost(String p_sqlWhere) throws Exception;

	public MPOS_ArgConfigVO queryFirst() throws Exception;

	public MPOS_ArgConfigVO[] queryAllPrePayDate() throws Exception;

	/**
	 * ��ѯ���һ�θ�������
	 * 
	 * @return
	 * @throws Exception
	 */
	public UFDate queryPrePayDate() throws Exception;

	public MPOS_ReceivablesVO[] queryReceByWhere(String p_sqlWhere)
			throws Exception;

	public MPOS_LogVO[] queryLogByWhere(String p_sqlWhere) throws Exception;

	/**
	 * ��ѯ������㵥-���ں˶Լ����ɸ���ƾ֤
	 * 
	 * @param p_sqlWhere
	 * @return
	 * @throws Exception
	 */
	public MPOS_PaymentVO[] queryPayBillByWhere(String p_sqlWhere)
			throws Exception;

	public MPOS_VoucherConfigVO[] queryAllVouConfigVO() throws Exception;

	public MPOS_CustdocVO queryByCode(String custcode) throws Exception;

	/**
	 * �ܷ�����˻�����
	 * <p>
	 * ����˻������ڸ��̻��Ŀ��õ������ܽ����˻�����
	 * </p>
	 * 
	 * @param pk_cubasdoc
	 *            �̻�����
	 * @param money
	 *            �˻����
	 * @return true:��;false:����
	 * @throws Exception
	 */
	public boolean canrRturnGoods(String pk_cubasdoc, UFDouble money)
			throws Exception;

	/**
	 * �˻�ʱ���˻���ϸ��ѯ
	 * @param pk_saledetail_h
	 * @return
	 * @throws Exception
	 */
	public MPOS_SaleDetailBodyVO[] queryReturnGoodsByHPK(String pk_saledetail_h)
			throws Exception;
	
	public MPOS_BalaTypeConfigVO[] queryAllBalaTypeConfigVO()throws Exception;
	
	/**
	 * ǰ̨����������ʾ��ۿ�
	 * @param pk_cubasdoc
	 * @return
	 * @throws Exception
	 */
	public MPOS_DiscountTempVO[] queryDiscountTempVO(String pk_cubasdoc)throws Exception;

}
