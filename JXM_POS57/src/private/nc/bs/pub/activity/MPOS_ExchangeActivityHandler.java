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
					"��������Ϸ�,�һ��Ҫ���һ������Ϊ�һ��ۺ�VO������");
		}

		
	}

}
