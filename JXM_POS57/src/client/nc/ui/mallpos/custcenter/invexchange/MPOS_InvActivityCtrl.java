package nc.ui.mallpos.custcenter.invexchange;

import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.mallpos.invexchange.MPOS_InvActivityBillVO;
import nc.vo.mallpos.invexchange.MPOS_InvActivityBodyVO;
import nc.vo.mallpos.invexchange.MPOS_InvActivityHeadVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;

public class MPOS_InvActivityCtrl extends AbstractManageController {

	public String[] getCardBodyHideCol() {
		return null;
	}

	public int[] getCardButtonAry() {
		return new int[]{
				IBillButton.Query,
				IBillButton.Refresh,
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Line,
				IBillButton.Save,
				IBillButton.Cancel,
				IBillButton.Del,
				IBillButton.Return
		};
	}

	public boolean isShowCardRowNo() {
		return false;
	}

	public boolean isShowCardTotal() {
		return false;
	}

	public String getBillType() {
		return MPOS_GlobalVariable.BILLTYPE_INVACTIVITY;
	}

	public String[] getBillVoName() {
		return new String[] { MPOS_InvActivityBillVO.class.getName(),
				MPOS_InvActivityHeadVO.class.getName(),
				MPOS_InvActivityBodyVO.class.getName() };
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
		return new MPOS_InvActivityBodyVO().getPrimaryKey();
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return new MPOS_InvActivityHeadVO().getPrimaryKey();
	}

	public Boolean isEditInGoing() throws Exception {
		return false;
	}

	public boolean isExistBillStatus() {
		return false;
	}

	public boolean isLoadCardFormula() {
		return false;
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
				IBillButton.Card
		};
	}

	public String[] getListHeadHideCol() {
		return null;
	}

	public boolean isShowListRowNo() {
		return true;
	}

	public boolean isShowListTotal() {
		return false;
	}

}
