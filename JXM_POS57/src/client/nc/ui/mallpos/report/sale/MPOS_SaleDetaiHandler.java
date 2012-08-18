package nc.ui.mallpos.report.sale;

import nc.ui.pub.ClientEnvironment;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.pub.SuperVO;

/**
 * ������ϸ�����¼�����
 * 
 * @author cj
 * @date 2012-8-10 ����10:46:51
 */
public class MPOS_SaleDetaiHandler extends ManageEventHandler {

	public MPOS_SaleDetaiHandler(BillManageUI arg0, IControllerBase arg1) {
		super(arg0, arg1);
	}

	/**
	 * ��ʼ����ǰ��½���ڵı�ͷ����
	 * @throws Exception
	 */
	public void initHeadData() throws Exception {
		SuperVO queryVos[] = queryHeadVOs(" bill_date = '"+ClientEnvironment.getInstance().getDate()+"' order by sale_no desc");
		getBufferData().clear();
		addDataToBuffer(queryVos);
		updateBuffer();
	}
}