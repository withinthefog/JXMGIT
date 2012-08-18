package nc.ui.mallpos.custcenter.invexchange;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubManageItf;
import nc.ui.pf.pub.PfUIDataCache;
import nc.ui.pub.pf.IUIBeforeProcAction;
import nc.ui.trade.base.AbstractBillUI;
import nc.ui.trade.businessaction.BdBusinessAction;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.trade.pub.IBDGetCheckClass2;
import nc.vo.trade.pub.IRetCurrentDataAfterSave;

public class ActivityBdBusinessAction extends BdBusinessAction {

	public ActivityBdBusinessAction(AbstractBillUI billUI) {
		super(billUI);
	}

	@Override
	public AggregatedValueObject save(AggregatedValueObject billVO,
			String billType, String billDate, Object userObj,
			AggregatedValueObject checkVo) throws Exception {
		if (userObj instanceof IBDGetCheckClass2) {
			RunUIBeforeCheck(((IBDGetCheckClass2) userObj).getUICheckClass(),
					billType, "WRITE", userObj, checkVo);
		} else {
			BilltypeVO billVo = PfUIDataCache.getBillTypeInfo(billType);
			String strClassName = billVo != null ? billVo.getDef3() : null;
			RunUIBeforeCheck(strClassName, billType, "WRITE", userObj, checkVo);
		}
		// ±£´æ¶Ò»»µ¥¾Ý
		MPOS_PubManageItf service = NCLocator.getInstance().lookup(MPOS_PubManageItf.class);
		AggregatedValueObject retVo = service.saveExchangeBill(billVO,userObj);
		
		if (userObj instanceof IRetCurrentDataAfterSave) {
			fillUITotalVO(checkVo.getChildrenVO(), retVo.getChildrenVO());
			checkVo.setParentVO(retVo.getParentVO());
			retVo = checkVo;
		}
		return retVo;
	}
	
	private void RunUIBeforeCheck(String checkClsName, String billType,
			String actionName, Object userObj, AggregatedValueObject checkVo)
			throws Exception {
		if (checkClsName == null || checkClsName.length() == 0)
			return;
		Class c = Class.forName(checkClsName);
		Object o = c.newInstance();
		if (o instanceof IUIBeforeProcAction)
			((IUIBeforeProcAction) o).runClass(getUI(), billType, actionName,
					checkVo, userObj);
	}
}
