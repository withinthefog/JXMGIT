/**
 * 
 */
package nc.itf.mallpos.pub;

import java.util.Map;

import nc.vo.mallpos.pos.MPOS_SaleDetailBillVO;
import nc.vo.mallpos.vipcard.MPOS_VipcardVO;
import nc.vo.mallpos.vipdoc.MPOS_VipdocCardVO;
import nc.vo.mallpos.vipdoc.MPOS_VipdocVO;
import nc.vo.pub.lang.UFDouble;

/**
 * @author chenguogang
 *
 */
public interface MPOS_PubVipItf {
	/**
	 * ���ݻ�Ա����Ϣ��ѯ��Ա��Ϣ
	 * @param pk_vip_card
	 * @return
	 * @throws Exception
	 */
	public MPOS_VipdocVO queryVipDocByPKVipCard(String pk_vip_card)throws Exception;
	/**
	 * ���ݻ�Ա������ָ������
	 * @param pk_vip_card
	 * @param grade
	 * @throws Exception
	 */
	public void addGrade(String pk_vip_card,UFDouble grade)throws Exception;
	/**
	 * ���ݻ�Ա���ۼ�ָ������
	 * @param pk_vip_card
	 * @param grade
	 * @throws Exception
	 */
	public void subGrade(String pk_vip_card,UFDouble grade)throws Exception;
	/**
	 * ���ݴ����������ϸbillvo�������ӻ���
	 * @return �������ӵĻ��֣���Ա���ܻ���
	 * @throws Exception
	 */
	public MPOS_SaleDetailBillVO addGrade(MPOS_SaleDetailBillVO saleDetailBillVo) throws Exception;
	/**
	 * ���ݴ����������ϸbillvo�ۼ�����
	 * @return ���οۼ��Ļ��֣���Ա���ܻ���
	 * @throws Exception
	 */
	public MPOS_SaleDetailBillVO subGrade(MPOS_SaleDetailBillVO saleDetailBillVo) throws Exception;
	/**
	 * ��Ա��������ʾ�����Ļ�Ա����ϸ��Ϣ
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public MPOS_VipdocCardVO[] queryForBD(String condition) throws Exception;
}
