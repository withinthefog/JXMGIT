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
 * 数据查询接口
 * 
 * @author chenjun
 * @date 2011-10-9 上午11:05:04
 */
public interface MPOS_PubQueryItf {

	public MPOS_SaleDaySumVO[] querySale(String pk_corp, String p_sqlWhere) throws Exception;

	public MPOS_CostVO[] queryCost(String p_sqlWhere) throws Exception;

	public MPOS_ArgConfigVO queryFirst() throws Exception;

	public MPOS_ArgConfigVO[] queryAllPrePayDate() throws Exception;

	/**
	 * 查询最近一次付款日期
	 * 
	 * @return
	 * @throws Exception
	 */
	public UFDate queryPrePayDate() throws Exception;

	public MPOS_ReceivablesVO[] queryReceByWhere(String p_sqlWhere)
			throws Exception;

	public MPOS_LogVO[] queryLogByWhere(String p_sqlWhere) throws Exception;

	/**
	 * 查询付款结算单-用于核对及生成付款凭证
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
	 * 能否进行退货操作
	 * <p>
	 * 如果退货金额大于该商户的可用的余额，则不能进行退货操作
	 * </p>
	 * 
	 * @param pk_cubasdoc
	 *            商户主键
	 * @param money
	 *            退货金额
	 * @return true:能;false:不能
	 * @throws Exception
	 */
	public boolean canrRturnGoods(String pk_cubasdoc, UFDouble money)
			throws Exception;

	/**
	 * 退货时可退货明细查询
	 * @param pk_saledetail_h
	 * @return
	 * @throws Exception
	 */
	public MPOS_SaleDetailBodyVO[] queryReturnGoodsByHPK(String pk_saledetail_h)
			throws Exception;
	
	public MPOS_BalaTypeConfigVO[] queryAllBalaTypeConfigVO()throws Exception;
	
	/**
	 * 前台收银界面显示活动折扣
	 * @param pk_cubasdoc
	 * @return
	 * @throws Exception
	 */
	public MPOS_DiscountTempVO[] queryDiscountTempVO(String pk_cubasdoc)throws Exception;

}
