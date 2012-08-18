package nc.vo.mallpos.pub;


public interface MPOS_IActivityExecuter {
	
	/**
	 * 执行活动的通用接口,对应不同的活动传入不同的参数
	 * 折扣活动参数:
	 * 		0:MPOS_SaleDetailAggVO数组
	 * 兑换活动参数:
	 * 		0:
	 * @param params
	 * @throws Exception
	 */
	public void executeActivity(Object[] params) throws Exception;

}
