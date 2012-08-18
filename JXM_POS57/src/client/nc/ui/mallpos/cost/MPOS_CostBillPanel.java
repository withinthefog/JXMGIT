package nc.ui.mallpos.cost;

import javax.swing.ListSelectionModel;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillItemNumberFormat;
import nc.ui.pub.bill.BillModel;
import nc.vo.mallpos.cost.MPOS_CostVO;

public class MPOS_CostBillPanel extends BillCardPanel
{

	private static final long serialVersionUID = 1L;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_CostBillPanel()
	{
		super();
		this.initialize();
	}

	/**
	 * 初始化
	 * 
	 */
	public void initialize()
	{
		// 设置表单内容
		setBillData(getThisBillData());
		// 界面设置
		getBillTable().setColumnSelectionAllowed(false);
		getBodyPanel().getRendererVO().setShowThMark(true);
		getBodyPanel().setTotalRowShow(false);// 设置合计行
		getAddLineMenuItem().setVisible(false);
		getDelLineMenuItem().setVisible(false);
		getInsertLineMenuItem().setVisible(false);
		getCopyLineMenuItem().setVisible(false);
		getPasteLineMenuItem().setVisible(false);
		getPasteLineToTailMenuItem().setVisible(false);
		// 设置只能单选
		this.getBillTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	/**
	 * 初始化表数据
	 * 
	 * @return BillData
	 */
	public BillData getThisBillData()
	{
		BillData billdata = new BillData();
		billdata.setBodyItems(getThisBodyItems());
		return billdata;
	}

	/**
	 * 得到表体项
	 * 
	 * @return BillItem[]
	 */
	public BillItem[] getThisBodyItems()
	{
		String sBodyItemKeys[] = new String[] { "pk_bussincome", "custcode", "custname", "bunkcode", "bunkname", "billdate", "feetype", "nmoney", "billstate" };
		String sBodyItemCaptions[] = new String[] { "主键", "商户编码", "商户名称", "铺位编码", "铺位名称", "单据日期", "费用类型", "金额", "单据状态" };
		BillItem items[] = new BillItem[sBodyItemKeys.length];
		BillItemNumberFormat format = new BillItemNumberFormat();
		format.setShowThMark(true);
		for (int i = 0; i < sBodyItemKeys.length; i++)
		{
			items[i] = new BillItem();
			items[i].setKey(sBodyItemKeys[i]);
			items[i].setName(sBodyItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setWidth(80);
			items[i].setEdit(false);
			items[i].setNull(false);
			if ("pk_bussincome".equals(sBodyItemKeys[i]))
			{
				items[i].setShow(false);
			} else if ("nmoney".equals(sBodyItemKeys[i]))
			{
				items[i].setDataType(BillItem.DECIMAL);
			}
		}
		return items;
	}

	public void fillGrid(MPOS_CostVO[] vos)
	{

		this.getBillModel().setBodyDataVO(vos);
		// 执行表体项加载公式
		this.getBillModel().execLoadFormula();

		if (vos != null && vos.length > 0)
		{
			// 设置表头数据第一行被选中
			this.getBillTable().setRowSelectionInterval(0, 0);
		}
	}

	/**
	 * 取得页面VO
	 * 
	 * @return KNPOS_CostVO[]
	 */
	public MPOS_CostVO[] getBodyValueVOs()
	{
		return (MPOS_CostVO[]) getBillModel().getBodyValueVOs(MPOS_CostVO.class.getName());
	}

	/**
	 * 取得页面选择的VO(鼠标选中)
	 * 
	 * @return KNPOS_CostVO[]
	 */
	public MPOS_CostVO[] getBillSelectVOs()
	{
		int row_count = getRowCount();
		if (row_count <= 0)
			return null;
		for (int i = 0; i < row_count; i++)
		{
			getBillModel().setRowState(i, BillModel.NORMAL);
		}
		int[] rows = getBillTable().getSelectedRows();
		for (int xxx = 0; xxx < rows.length; xxx++)
		{
			getBillModel().setRowState(rows[xxx], BillModel.SELECTED);
		}
		MPOS_CostVO[] vos = (MPOS_CostVO[]) getBillModel().getBodySelectedVOs(MPOS_CostVO.class.getName());
		return vos;
	}

}
