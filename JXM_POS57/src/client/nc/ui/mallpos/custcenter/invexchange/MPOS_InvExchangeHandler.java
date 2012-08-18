package nc.ui.mallpos.custcenter.invexchange;

import nc.ui.trade.businessaction.BusinessAction;
import nc.ui.trade.businessaction.IBusinessController;
import nc.ui.trade.controller.IControllerBase;
import nc.ui.trade.manage.BillManageUI;
import nc.ui.trade.manage.ManageEventHandler;

/**
 * 礼品兑换事件处理
 * 
 * @author cj
 * @date 2012-8-10 上午10:46:51
 */
public class MPOS_InvExchangeHandler extends ManageEventHandler {

	public MPOS_InvExchangeHandler(BillManageUI arg0, IControllerBase arg1) {
		super(arg0, arg1);
	}
	
	@Override
	protected IBusinessController createBusinessAction() {
		switch (getUIController().getBusinessActionType()) {
		case 0: // '\0'
			return new BusinessAction(getBillUI());

		case 1: // '\001'
			return new ActivityBdBusinessAction(getBillUI());
		}
		return new BusinessAction(getBillUI());
	}

}
