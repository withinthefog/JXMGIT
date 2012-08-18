package nc.ui.mallpos.bd.housedoc;

import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.ui.trade.treemanage.AbstractTreeManageController;
import nc.vo.mallpos.bd.MPOS_HouseBillVO;
import nc.vo.mallpos.bd.MPOS_HouseVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;

/**
 * 楼盘档案界面控制类
 * 
 * @author cj
 * @date 2012-8-6 下午10:08:39
 */
public class MPOS_HousedocCtrl extends AbstractTreeManageController{

	public boolean isAutoManageTree() {
		return true;
	}

	public boolean isTableTree() {
		return false;
	}

	public String[] getCardBodyHideCol() {
		return null;
	}

	public int[] getCardButtonAry() {
		return new int[]{
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Save,
				IBillButton.Cancel,
				IBillButton.Delete,
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
		return MPOS_GlobalVariable.BILLTYPE_BD_HOUSEDOC;
	}

	public String[] getBillVoName() {
		return new String[]{
				MPOS_HouseBillVO.class.getName(),
				MPOS_HouseVO.class.getName(),
				MPOS_HouseVO.class.getName()
		};
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
		return null;
	}

	public String getHeadZYXKey() {
		return null;
	}

	public String getPkField() {
		return  new MPOS_HouseVO().getPrimaryKey();
	}

	public Boolean isEditInGoing() throws Exception {
		return null;
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
				IBillButton.Refresh,
				IBillButton.Add,
				IBillButton.Edit,
				IBillButton.Delete,
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
