package nc.ui.mallpos.pos;

import nc.bs.framework.common.NCLocator;
import nc.impl.liandpos.pub.LiandPOS_CardTransImpl;
import nc.impl.sandpos.pub.SandPOS_CardTransImpl;
import nc.itf.liandpos.pub.LiandPOS_CardTransItf;
import nc.itf.sandpos.pub.SandPOS_CardTransItf;
import nc.itf.uif.pub.IUifService;
import nc.vo.liandpos.args.LiandPOS_OutArgsConvertedVO;
import nc.vo.liandpos.pub.LiandPOS_Constant;
import nc.vo.mallpos.pos.MPOS_POSConstant;
import nc.vo.mallpos.pos.MPOS_SaleDetailBodyVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.pub.MPOS_POSConfigVO;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sandpos.args.SandPOS_OutArgsConvertedVO;
import nc.vo.sandpos.pub.SandPOS_Constant;

/**
 * POS调用类
 * 
 * @author cj
 * @date 2012-8-12 上午11:09:50
 */
public class MPOS_POSCall {
	
	private static IUifService service;
	
	private static IUifService getService() {
		if (service == null) {
			service = (IUifService) NCLocator.getInstance().lookup(
					IUifService.class);
		}
		return service;
	}
	
	/**
	 * 签到
	 * @param comxx 端口号
	 * @return true:成功 flase:失败
	 * @throws Exception
	 */
	public static boolean signin(int comxx) throws Exception
	{
		boolean b = false;
		if (getPosItfClass(comxx) == SandPOS_CardTransItf.class) {
			SandPOS_CardTransItf iPOS = new SandPOS_CardTransImpl();
			SandPOS_OutArgsConvertedVO revo = iPOS.signin(comxx);
			if (SandPOS_Constant.SUCC_CODE.equals(revo.getResponsecode()))
				b = true;
			else
				throw new Exception(revo.getResponsemsg());
		}else if (getPosItfClass(comxx) == LiandPOS_CardTransItf.class) {
			LiandPOS_CardTransItf iPOS = new LiandPOS_CardTransImpl();
			LiandPOS_OutArgsConvertedVO revo = iPOS.signin(comxx);
			if (LiandPOS_Constant.SUCC_CODE.equals(revo.getResponsecode()))
				b = true;
			else
				throw new Exception(revo.getResponsemsg());
		}
		return b;
	}
	
	

	/**
	 * 刷卡消费
	 * 
	 * @param saledvo
	 *            消费明细对象
	 * @return
	 */
	public static MPOS_SaleDetailBodyVO expense(MPOS_SaleDetailBodyVO saledvo)
			throws Exception {
		return expense(saledvo, -1);
	}
	
	/**
	 * 刷卡消费
	 * @param saledvo 消费明细对象
	 * @param comxx POS机端口号
	 * @return
	 * @throws Exception
	 */
	public static MPOS_SaleDetailBodyVO expense(MPOS_SaleDetailBodyVO saledvo,int comxx)
	throws Exception {
		//IUifService service = NCLocator.getInstance().lookup(IUifService.class);
		//BusinessDelegator business = new BDBusinessDelegator();
		MPOS_POSConfigVO[] cvos = null;
		
		if(comxx==-1)
			cvos = (MPOS_POSConfigVO[])getService().queryByCondition(MPOS_POSConfigVO.class, saledvo.getReceived_money()+" >= lowestlimit_money and "+saledvo.getReceived_money()+" <= toplimit_money");
		else
			cvos = (MPOS_POSConfigVO[])getService().queryByCondition(MPOS_POSConfigVO.class, "comxx="+comxx);
		
		if(cvos==null||cvos.length<=0)
			throw new Exception("没有找到POS机配置信息！");
		
		if (getPosItfClass(cvos[0]) == SandPOS_CardTransItf.class) {

			SandPOS_CardTransItf iPOS = new SandPOS_CardTransImpl();
			SandPOS_OutArgsConvertedVO revo = iPOS.expense(Integer
					.parseInt(cvos[0].getComxx()), saledvo.getReceived_money()
					.doubleValue());

			if (SandPOS_Constant.SUCC_CODE.equals(revo.getResponsecode())) {

				saledvo.setBrand(cvos[0].getBrand());// 记录POS机品牌
				saledvo.setSystracdno(revo.getSystracdno());// 系统流水号-用于撤消
				saledvo.setCardno(revo.getCardno());// 银行卡号

				// 计算手续费
				if (MPOS_GlobalVariable.CARD_FEE_PERCENT == cvos[0]
						.getFee_caltype().intValue()) {
					saledvo.setNfee(saledvo.getReceived_money().multiply(
							cvos[0].getFee()).setScale(2,
							UFDouble.ROUND_HALF_UP));// 手续费四舍五入到2位小数
				} else {
					saledvo.setNfee(cvos[0].getFee());
				}
			} else {
				saledvo = null;
				throw new Exception(revo.getResponsemsg());
			}
		}else if (getPosItfClass(cvos[0]) == LiandPOS_CardTransItf.class) {

			LiandPOS_CardTransItf iPOS = new LiandPOS_CardTransImpl();
			LiandPOS_OutArgsConvertedVO revo = iPOS.expense(Integer
					.parseInt(cvos[0].getComxx()), saledvo.getReceived_money()
					.doubleValue());

			if (LiandPOS_Constant.SUCC_CODE.equals(revo.getResponsecode())) {

				saledvo.setBrand(cvos[0].getBrand());// 记录POS机品牌
				saledvo.setSystracdno(revo.getSystracdno());// 系统流水号-用于撤消
				saledvo.setCardno(revo.getCardno());// 银行卡号

				// 计算手续费
				if (MPOS_GlobalVariable.CARD_FEE_PERCENT == cvos[0]
						.getFee_caltype().intValue()) {
					saledvo.setNfee(saledvo.getReceived_money().multiply(
							cvos[0].getFee()).setScale(2,
							UFDouble.ROUND_HALF_UP));// 手续费四舍五入到2位小数
				} else {
					saledvo.setNfee(cvos[0].getFee());
				}
			} else {
				saledvo = null;
				throw new Exception(revo.getResponsemsg());
			}
		}
		return saledvo;
	}
	
	/**
	 * 撤消：退货
	 * @param bvo
	 * @return true:成功 flase:失败
	 * @throws Exception
	 */
	public static boolean repeal(MPOS_SaleDetailBodyVO bvo) throws Exception
	{
		boolean b = false;
		MPOS_POSConfigVO[] cvos = (MPOS_POSConfigVO[]) getService()
		.queryByCondition(MPOS_POSConfigVO.class, "brand=" + bvo.getBrand());
		if (cvos == null || cvos.length <= 0)
			throw new Exception("没有找到POS机配置信息！");
		if (getPosItfClass(cvos[0]) == SandPOS_CardTransItf.class) {
			SandPOS_CardTransItf iPOS = new SandPOS_CardTransImpl();
			SandPOS_OutArgsConvertedVO revo = iPOS.repeal(Integer.parseInt(cvos[0].getComxx()),Math.abs(bvo.getReceived_money().doubleValue()),bvo.getSystracdno());
			if (SandPOS_Constant.SUCC_CODE.equals(revo.getResponsecode()))
				b = true;
			else
				throw new Exception(revo.getResponsemsg());
		}else if (getPosItfClass(cvos[0]) == LiandPOS_CardTransItf.class) {
			LiandPOS_CardTransItf iPOS = new LiandPOS_CardTransImpl();
			LiandPOS_OutArgsConvertedVO revo = iPOS.repeal(Integer.parseInt(cvos[0].getComxx()),Math.abs(bvo.getReceived_money().doubleValue()),bvo.getSystracdno());
			if (LiandPOS_Constant.SUCC_CODE.equals(revo.getResponsecode()))
				b = true;
			else
				throw new Exception(revo.getResponsemsg());
		}
		return b;
	}
	
	/**
	 * 结算
	 * @param comxx 端口号
	 * @return true:成功 flase:失败
	 * @throws Exception
	 */
	public static boolean settlement(int comxx) throws Exception
	{
		boolean b = false;
		if (getPosItfClass(comxx) == SandPOS_CardTransItf.class) {
			SandPOS_CardTransItf iPOS = new SandPOS_CardTransImpl();
			SandPOS_OutArgsConvertedVO revo = iPOS.settlement(comxx);
			if (SandPOS_Constant.SUCC_CODE.equals(revo.getResponsecode()))
				b = true;
			else
				throw new Exception(revo.getResponsemsg());
		}else if (getPosItfClass(comxx) == LiandPOS_CardTransItf.class) {
			LiandPOS_CardTransItf iPOS = new LiandPOS_CardTransImpl();
			LiandPOS_OutArgsConvertedVO revo = iPOS.settlement(comxx);
			if (LiandPOS_Constant.SUCC_CODE.equals(revo.getResponsecode()))
				b = true;
			else
				throw new Exception(revo.getResponsemsg());
		}
		return b;
	}
	
	/**
	 * 余额查询
	 * 
	 * @param comxx
	 *            端口号
	 * @return true:成功 flase:失败
	 * @throws Exception
	 */
	public static boolean balanceQuery(int comxx) throws Exception
	{
		boolean b = false;
		if (getPosItfClass(comxx) == SandPOS_CardTransItf.class) {
		SandPOS_CardTransItf iPOS = new SandPOS_CardTransImpl();
		SandPOS_OutArgsConvertedVO revo = iPOS.balancequery(comxx);
		if (SandPOS_Constant.SUCC_CODE.equals(revo.getResponsecode()))
			b =  true;
		else
			throw new Exception(revo.getResponsemsg());
		}else if (getPosItfClass(comxx) == LiandPOS_CardTransItf.class) {
			LiandPOS_CardTransItf iPOS = new LiandPOS_CardTransImpl();
			LiandPOS_OutArgsConvertedVO revo = iPOS.balancequery(comxx);
			if (LiandPOS_Constant.SUCC_CODE.equals(revo.getResponsecode()))
				b =  true;
			else
				throw new Exception(revo.getResponsemsg());
			}
		return b;
	}
	
	/**
	 * 根据端口号取得调用POS的接口类
	 * @param comxx
	 * @return
	 * @throws Exception
	 */
	public static Class getPosItfClass(int comxx) throws Exception {
		MPOS_POSConfigVO[] cvos = (MPOS_POSConfigVO[]) getService()
				.queryByCondition(MPOS_POSConfigVO.class, "comxx=" + comxx);
		if (cvos == null || cvos.length <= 0)
			throw new Exception("没有找到POS机配置信息！");
		return getPosItfClass(cvos[0]);
	}
	
	/**
	 * 根据POS机配置取得调用POS的接口类
	 * @param cvo
	 * @return
	 * @throws Exception
	 */
	public static Class getPosItfClass(MPOS_POSConfigVO cvo) throws Exception {
		Class<?> itfClass = null;
		switch (cvo.getBrand()) {
		case MPOS_POSConstant.POS_BRAND_SAND:
			itfClass = SandPOS_CardTransItf.class;
			break;
		case MPOS_POSConstant.POS_BRAND_LIAND:
			itfClass = LiandPOS_CardTransItf.class;
			break;
		default:
			break;
		}
		return itfClass;
	}
}
