package nc.ui.mallpos.bd.invdoc;

import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;

/**
 * 存货档案管理事件处理
 * @author cj
 * @date 2012-8-8 下午01:15:22
 */
public class MPOS_InvdocHandler extends ManageEventHandler {


	public MPOS_InvdocHandler(BillManageUI arg0, IControllerBase arg1) {
		super(arg0, arg1);
	}

	public void queryBodyData(String strWhere) throws Exception {
		doBodyQuery(strWhere != null ? strWhere : "");
	}

//	protected void doBodyQuery(String strWhere) throws Exception,
//			ClassNotFoundException, InstantiationException,
//			IllegalAccessException {
//		SuperVO queryVos[] = getBusiDelegator().queryByCondition(
//				Class.forName(getUIController().getBillVoName()[2]),
//				strWhere != null ? strWhere : "");
//		getBufferData().clear();
//		AggregatedValueObject vo = (AggregatedValueObject) Class.forName(
//				getUIController().getBillVoName()[0]).newInstance();
//		vo.setChildrenVO(queryVos);
//		getBufferData().addVOToBuffer(vo);
//		updateBuffer();
//	}
}
