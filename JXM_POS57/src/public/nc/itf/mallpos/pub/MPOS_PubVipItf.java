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
	 * 根据会员卡信息查询会员信息
	 * @param pk_vip_card
	 * @return
	 * @throws Exception
	 */
	public MPOS_VipdocVO queryVipDocByPKVipCard(String pk_vip_card)throws Exception;
	/**
	 * 根据会员卡增加指定积分
	 * @param pk_vip_card
	 * @param grade
	 * @throws Exception
	 */
	public void addGrade(String pk_vip_card,UFDouble grade)throws Exception;
	/**
	 * 根据会员卡扣减指定积分
	 * @param pk_vip_card
	 * @param grade
	 * @throws Exception
	 */
	public void subGrade(String pk_vip_card,UFDouble grade)throws Exception;
	/**
	 * 根据传入的销售明细billvo计算增加积分
	 * @return 本次增加的积分，会员卡总积分
	 * @throws Exception
	 */
	public MPOS_SaleDetailBillVO addGrade(MPOS_SaleDetailBillVO saleDetailBillVo) throws Exception;
	/**
	 * 根据传入的销售明细billvo扣减积分
	 * @return 本次扣减的积分，会员卡总积分
	 * @throws Exception
	 */
	public MPOS_SaleDetailBillVO subGrade(MPOS_SaleDetailBillVO saleDetailBillVo) throws Exception;
	/**
	 * 会员管理档案显示关联的会员卡详细信息
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public MPOS_VipdocCardVO[] queryForBD(String condition) throws Exception;
}
