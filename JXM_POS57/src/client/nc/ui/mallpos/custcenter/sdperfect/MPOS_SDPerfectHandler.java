package nc.ui.mallpos.custcenter.sdperfect;

import nc.ui.pub.ClientEnvironment;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.pub.SuperVO;

/**
 * 销售明细数据完善事件处理
 * 
 * @author cj
 * @date 2012-8-10 上午10:46:51
 */
public class MPOS_SDPerfectHandler extends ManageEventHandler {

	public MPOS_SDPerfectHandler(BillManageUI arg0, IControllerBase arg1) {
		super(arg0, arg1);
	}

	/**
	 * 初始化当前登陆日期的表头数据
	 * @throws Exception
	 */
	public void initHeadData() throws Exception {
		SuperVO queryVos[] = queryHeadVOs(" bill_date = '"+ClientEnvironment.getInstance().getDate()+"'");
		getBufferData().clear();
		addDataToBuffer(queryVos);
		updateBuffer();
	}
}
