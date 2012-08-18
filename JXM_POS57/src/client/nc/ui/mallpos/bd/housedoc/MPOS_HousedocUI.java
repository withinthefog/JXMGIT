package nc.ui.mallpos.bd.housedoc;

import nc.ui.mallpos.bd.areacl.MPOS_AreaclTreeData;
import nc.ui.trade.bill.AbstractManageController;
import nc.ui.trade.manage.ManageEventHandler;
import nc.ui.trade.pub.IVOTreeData;
import nc.ui.trade.treemanage.BillTreeManageUI;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.SuperVO;

/**
 * 楼盘档案维护主界面
 * 
 * @author cj
 * @date 2012-8-6 下午10:05:01
 */
public class MPOS_HousedocUI extends BillTreeManageUI
{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void afterInit() throws Exception {
		modifyRootNodeShowName("地区分类");
		
	}

	@Override
	protected IVOTreeData createTableTreeData() {
		return null;
	}

	@Override
	protected IVOTreeData createTreeData() {
		return new MPOS_AreaclTreeData();
	}

	@Override
	protected void setHeadSpecialData(CircularlyAccessibleValueObject arg0, int arg1) throws Exception {
		
	}

	@Override
	protected void setTotalHeadSpecialData(CircularlyAccessibleValueObject[] arg0) throws Exception {
		
	}

	@Override
	protected void initSelfData() {
		
	}
	
	@Override
	protected AbstractManageController createController() {
		return new MPOS_HousedocCtrl();
	}
	
	@Override
	protected ManageEventHandler createEventHandler() {
		return new MPOS_HousedocHandler(this,getUIControl());
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
