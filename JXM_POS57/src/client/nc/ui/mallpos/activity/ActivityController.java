package nc.ui.mallpos.activity;

import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.mallpos.activity.MPOS_ActivityCustVO;
import nc.vo.mallpos.activity.MPOS_ActivityHeadVO;
import nc.vo.mallpos.activity.MPOS_ActivityRuleVO;
import nc.vo.mallpos.activity.MPOS_ActivityVO;
import nc.vo.mallpos.pub.MPOS_ExtButton;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;

public class ActivityController extends AbstractManageController {

	public String[] getCardBodyHideCol() {
		return null;
	}

	public int[] getCardButtonAry() {
		return new int[]{
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Save,
				IBillButton.Cancel,
				IBillButton.Line,
				IBillButton.Del,
				IBillButton.Return,
				MPOS_ExtButton.ActivateActivity,
				MPOS_ExtButton.DeactivateActivity
		};
	}

	public boolean isShowCardRowNo() {
		return false;
	}

	public boolean isShowCardTotal() {
		return false;
	}

	public String getBillType() {
		return MPOS_GlobalVariable.BILLTYPE_ACTIVITY;
	}

	public String[] getBillVoName() {
		return new String[] { MPOS_ActivityVO.class.getName(),
				MPOS_ActivityHeadVO.class.getName(),
				MPOS_ActivityRuleVO.class.getName(),
				MPOS_ActivityCustVO.class.getName() };
	}

	public String getBodyCondition() {
		return null;
	}

	public String getBodyZYXKey() {
		return null;
	}

	public int getBusinessActionType() {
		return IBusinessActionType.BD;
	}

	public String getChildPkField() {
		return new MPOS_ActivityRuleVO().getPrimaryKey();
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return new MPOS_ActivityHeadVO().getPrimaryKey();
	}

	public Boolean isEditInGoing() throws Exception {
		return false;
	}

	public boolean isExistBillStatus() {
		return false;
	}

	public boolean isLoadCardFormula() {
		return true;
	}

	public String[] getListBodyHideCol() {
		return null;
	}

	public int[] getListButtonAry() {
		return new int[]{
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Save,
				IBillButton.Cancel,
				IBillButton.Del,
				IBillButton.Card,
				MPOS_ExtButton.ActivateActivity,
				MPOS_ExtButton.DeactivateActivity
		};
	}

	public String[] getListHeadHideCol() {
		return null;
	}

	public boolean isShowListRowNo() {
		return true;
	}

	public boolean isShowListTotal() {
		return true;
	}

}
