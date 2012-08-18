/**
 * 
 */
package nc.ui.mallpos.vipcard;

import nc.bs.framework.common.NCLocator;
import nc.itf.uif.pub.IUifService;
import nc.ui.pub.ClientEnvironment;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;
import nc.vo.mallpos.vipcard.MPOS_VipcardVO;

/**
 * @author chenguogang
 *
 */
public class MPOS_VipcardHDL extends ManageEventHandler {

	/**
	 * @param billUi
	 * @param control
	 */
	public MPOS_VipcardHDL(BillManageUI billUi, IControllerBase control) {
		super(billUi, control);
		// TODO Auto-generated constructor stub
	}

}
