package nc.bs.pub.activity;

import nc.vo.mallpos.invexchange.MPOS_InvExchangeBillVO;
import nc.vo.mallpos.pub.MPOS_IActivityExecuter;

public class MPOS_ExchangeActivityHandler implements MPOS_IActivityExecuter {

	public void executeActivity(Object[] params) throws Exception {
		// TODO Auto-generated method stub
		MPOS_InvExchangeBillVO billvos = null;
		try {
			billvos = (MPOS_InvExchangeBillVO) params[0];
		} catch (Exception e) {
			throw new IllegalArgumentException(
					"活动参数不合法,兑换活动要求第一个参数为兑换聚合VO的数组");
		}

		
	}

}
