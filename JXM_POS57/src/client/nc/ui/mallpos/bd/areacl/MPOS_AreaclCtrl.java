package nc.ui.mallpos.bd.areacl;

import nc.ui.trade.businessaction.IBusinessActionType;
import nc.ui.trade.button.IBillButton;
import nc.ui.trade.treecard.ITreeCardController;
import nc.vo.mallpos.bd.MPOS_AreaclBillVO;
import nc.vo.mallpos.bd.MPOS_AreaclVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;

/**
 * 地区分类界面控制类
 * @author cj
 * @date 2012-8-6 下午10:08:39
 */
public class MPOS_AreaclCtrl implements ITreeCardController
{

	/**
	 * 单据的增加删除时，是否自动维护树结构
	 */
	public boolean isAutoManageTree()
	{
		return true;
	}

	public boolean isChildTree()
	{
		return false;
	}

	public boolean isTableTree()
	{
		return false;
	}

	public String[] getCardBodyHideCol()
	{
		return null;
	}

	public int[] getCardButtonAry()
	{
		return new int[]{
				IBillButton.Refresh,
				IBillButton.Add,
	            IBillButton.Edit,
	            IBillButton.Save,
	            IBillButton.Cancel,
	            IBillButton.Delete
	        };
	}

	public boolean isShowCardRowNo()
	{
		return false;
	}

	public boolean isShowCardTotal()
	{
		return false;
	}

	public String getBillType()
	{
		return MPOS_GlobalVariable.BILLTYPE_BD_AREACL;
	}

	public String[] getBillVoName()
	{
		return new String[] { 
				MPOS_AreaclBillVO.class.getName(),
				MPOS_AreaclVO.class.getName(), 
				MPOS_AreaclVO.class.getName() };
	}

	public String getBodyCondition()
	{
		return null;
	}

	public String getBodyZYXKey()
	{
		return null;
	}

	public int getBusinessActionType()
	{
		return IBusinessActionType.BD;
	}

	public String getChildPkField()
	{
		return null;
	}

	public String getHeadZYXKey()
	{
		return null;
	}

	public String getPkField()
	{
		return new MPOS_AreaclVO().getPrimaryKey();
	}

	public Boolean isEditInGoing() throws Exception
	{
		return null;
	}

	public boolean isExistBillStatus()
	{
		return false;
	}

	public boolean isLoadCardFormula()
	{
		return false;
	}

}
