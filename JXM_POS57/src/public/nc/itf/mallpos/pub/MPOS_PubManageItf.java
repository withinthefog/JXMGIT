package nc.itf.mallpos.pub;

import nc.vo.mallpos.pos.MPOS_SaleDetailBillVO;
import nc.vo.mallpos.pub.MPOS_ArgConfigVO;
import nc.vo.mallpos.pub.MPOS_BalaTypeConfigVO;
import nc.vo.mallpos.sale.MPOS_SaleDaySumVO;
import nc.vo.mallpos.voucher.MPOS_VoucherConfigVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFDate;

/**
 * ���ݹ���ӿ�
 * 
 * @author chenjun
 * @date 2011-10-9 ����11:04:57
 */
public interface MPOS_PubManageItf {

	/**
	 * ���ɸ�����㵥
	 * 
	 * @param llr
	 *            ¼����
	 * @param vos
	 *            Ҫ���ɵ�����
	 * @param pk_corp
	 *            ��˾����
	 * @param prePaydate
	 *            �ϴθ�������(�����Ϊ�����д�븶�����ڵ����ñ��У���Ҫ�����Զ���������ʧ��ʱ�ڽ����ֶ�����)
	 * @throws Exception
	 */
	public void createCMPBill(String llr, MPOS_SaleDaySumVO[] vos,
			String pk_corp, UFDate prePaydate) throws Exception;

	public void saveArgConfig(MPOS_ArgConfigVO p_cvo) throws Exception;

	public void saveVoucherConfig(MPOS_VoucherConfigVO[] p_vos)
			throws Exception;

	public void saveSaleVO(MPOS_SaleDaySumVO[] vos) throws Exception;

	/**
	 * ��д������㵥����zyx2���ɸ���ƾ֤״̬
	 * 
	 * @param vouchid
	 *            ������㵥����
	 * @throws Exception
	 */
	public void writeBackCMPbill(String vouchid) throws Exception;

	/**
	 * ����ǰ̨������ϸ�ۺ϶���
	 * 
	 * @param aggexvo
	 * @throws Exception
	 */
	public void saveSaleDetailAggExVO(MPOS_SaleDetailBillVO aggexvo)
			throws Exception;

	public void saveBalaTypeConfig(MPOS_BalaTypeConfigVO[] vos)
			throws Exception;

	public void deleteBalaTypeConfig(String pk_balatype_config)
			throws Exception;

	public AggregatedValueObject saveExchangeBill(AggregatedValueObject billvo,Object userObj)
			throws Exception;

}
