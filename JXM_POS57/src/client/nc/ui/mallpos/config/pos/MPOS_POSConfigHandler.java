package nc.ui.mallpos.config.pos;

import nc.ui.trade.bill.ICardController;
import nc.ui.trade.card.BillCardUI;
import nc.ui.trade.card.CardEventHandler;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.SuperVO;

/**
 * POS机配置信息事件处理
 * @author cj
 * @date 2012-8-8 下午01:15:22
 */
public class MPOS_POSConfigHandler extends CardEventHandler {

	public MPOS_POSConfigHandler(BillCardUI arg0, ICardController arg1) {
		super(arg0, arg1);
	}

	public void queryBodyData(String strWhere) throws Exception {
		doBodyQuery(strWhere != null ? strWhere : "");
	}

	protected void doBodyQuery(String strWhere) throws Exception,
			ClassNotFoundException, InstantiationException,
			IllegalAccessException {
		SuperVO queryVos[] = getBusiDelegator().queryByCondition(
				Class.forName(getUIController().getBillVoName()[2]),
				strWhere != null ? strWhere : "");
		getBufferData().clear();
		AggregatedValueObject vo = (AggregatedValueObject) Class.forName(
				getUIController().getBillVoName()[0]).newInstance();
		vo.setChildrenVO(queryVos);
		getBufferData().addVOToBuffer(vo);
		updateBuffer();
	}
}
