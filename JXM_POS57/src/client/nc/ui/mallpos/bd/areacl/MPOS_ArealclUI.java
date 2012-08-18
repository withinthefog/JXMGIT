package nc.ui.mallpos.bd.areacl;

import nc.ui.trade.bill.ICardController;
import nc.ui.trade.card.CardEventHandler;
import nc.ui.trade.pub.IVOTreeData;
import nc.ui.trade.treecard.BillTreeCardUI;
import nc.vo.pub.SuperVO;

/**
 * 地区分类维护主界面
 * 
 * @author cj
 * @date 2012-8-6 下午10:05:01
 */
public class MPOS_ArealclUI extends BillTreeCardUI
{

	private static final long serialVersionUID = 1L;

	@Override
	protected IVOTreeData createTableTreeData()
	{
		return null;
	}

	@Override
	protected IVOTreeData createTreeData()
	{
		return new MPOS_AreaclTreeData();
	}

	@Override
	protected ICardController createController()
	{
		return new MPOS_AreaclCtrl();
	}
	
	@Override
	protected CardEventHandler createEventHandler() {
		return new MPOS_AreaclHandler(this,getUIControl());
	}

	@Override
	public String getRefBillType()
	{
		return null;
	}

	@Override
	protected void initSelfData()
	{

	}

	@Override
	public void afterInit() throws Exception
	{
		modifyRootNodeShowName("地区分类");
	}
	
	@Override
	public void createBillTree(IVOTreeData treeData) {
		initBufferData(treeData.getTreeVO());
		super.createBillTree(treeData);
	}
	
	private void initBufferData(SuperVO queryVos[]) {
		try {
			getBufferData().clear();
			if (queryVos != null && queryVos.length != 0) {
				addBufferData(queryVos);
				setBillOperate(OP_NOTEDIT);
			} else {
				getTreeToBuffer().clear();
				getBufferData().setCurrentRow(-1);
				setBillOperate(OP_INIT);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
