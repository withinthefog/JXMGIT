package nc.ui.mallpos.bd.areacl;

import nc.ui.trade.bsdelegate.BDBusinessDelegator;
import nc.ui.trade.bsdelegate.BusinessDelegator;
import nc.ui.trade.pub.IVOTreeDataByID;
import nc.vo.mallpos.bd.MPOS_AreaclVO;
import nc.vo.pub.SuperVO;

/**
 * 地区分类树的数据类
 * 
 * @author cj
 * @date 2012-8-6 下午10:13:51
 */
public class MPOS_AreaclTreeData implements IVOTreeDataByID
{

	public String getIDFieldName()
	{
		return "pk_areacl";
	}

	public String getParentIDFieldName()
	{
		return "pk_fareacl";
	}

	public String getShowFieldName()
	{
		return "areacl_code,areacl_name";
	}

	public SuperVO[] getTreeVO()
	{
		SuperVO[] treeVOs = null;
		BusinessDelegator business = new BDBusinessDelegator();
		try
		{
			treeVOs = business.queryByCondition(MPOS_AreaclVO.class, null);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return treeVOs;
	}
}
