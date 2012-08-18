package nc.ui.mallpos.bd.invdoc;


import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.bill.ISingleController;
import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.vo.mallpos.bd.MPOS_InvdocBillVO;
import nc.vo.mallpos.bd.MPOS_InvdocVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;

/**
 * 存货档案管理控制类
 * 
 * @author cj
 * @date 2012-8-7 下午03:56:16
 */
public class MPOS_InvdocCtrl extends AbstractManageController implements ISingleController{

	public String[] getCardBodyHideCol() {
		return null;
	}

	public int[] getCardButtonAry() {
		return new int[]{
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Save,
				IBillButton.Cancel,
				IBillButton.Return,
				IBillButton.Print
		};
	}

	public boolean isShowCardRowNo() {
		return false;
	}

	public boolean isShowCardTotal() {
		return false;
	}

	public String getBillType() {
		return MPOS_GlobalVariable.BILLTYPE_BD_INVDOC;
	}

	public String[] getBillVoName() {
		return new String[] { MPOS_InvdocBillVO.class.getName(),
				MPOS_InvdocVO.class.getName(),
				MPOS_InvdocVO.class.getName() };
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
		return new MPOS_InvdocVO().getPrimaryKey();
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return new MPOS_InvdocVO().getPrimaryKey();
	}

	public Boolean isEditInGoing() throws Exception {
		return null;
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
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Save,
				IBillButton.Cancel,
				IBillButton.Card,
				IBillButton.Print
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

	public boolean isSingleDetail() {
		//必须实现，不然后台会报错
		return false;
	}


}
