package nc.itf.mallpos.pub;

import nc.vo.mallpos.pos.MPOS_SaleDetailBillVO;
import nc.vo.mallpos.pub.MPOS_ArgConfigVO;
import nc.vo.mallpos.pub.MPOS_BalaTypeConfigVO;
import nc.vo.mallpos.sale.MPOS_SaleDaySumVO;
import nc.vo.mallpos.voucher.MPOS_VoucherConfigVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFDate;

/**
 * 数据管理接口
 * 
 * @author chenjun
 * @date 2011-10-9 上午11:04:57
 */
public interface MPOS_PubManageItf {

	/**
	 * 生成付款结算单
	 * 
	 * @param llr
	 *            录入人
	 * @param vos
	 *            要生成的数据
	 * @param pk_corp
	 *            公司主键
	 * @param prePaydate
	 *            上次付款日期(如果不为空则会写入付款日期到配置表中，主要用于自动任务生成失败时在界面手动生成)
	 * @throws Exception
	 */
	public void createCMPBill(String llr, MPOS_SaleDaySumVO[] vos,
			String pk_corp, UFDate prePaydate) throws Exception;

	public void saveArgConfig(MPOS_ArgConfigVO p_cvo) throws Exception;

	public void saveVoucherConfig(MPOS_VoucherConfigVO[] p_vos)
			throws Exception;

	public void saveSaleVO(MPOS_SaleDaySumVO[] vos) throws Exception;

	/**
	 * 回写付款结算单主表zyx2生成付款凭证状态
	 * 
	 * @param vouchid
	 *            付款结算单主键
	 * @throws Exception
	 */
	public void writeBackCMPbill(String vouchid) throws Exception;

	/**
	 * 保存前台收银明细聚合对象
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
