package nc.ui.mallpos.log;


import javax.swing.ListSelectionModel;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.vo.mallpos.pub.MPOS_LogVO;

public class MPOS_LogBillPanel extends BillCardPanel
{

	private static final long serialVersionUID = 1L;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_LogBillPanel()
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
		// 固定表处理
		getBillTable().removeSortListener();
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
		String sBodyItemKeys[] = new String[] { "pk_log", "log_date", "stage", "content" };
		String sBodyItemCaptions[] = new String[] { "主键", "日期", "业务阶段", "日志内容" };
		BillItem items[] = new BillItem[sBodyItemKeys.length];
		for (int i = 0; i < sBodyItemKeys.length; i++)
		{
			items[i] = new BillItem();
			items[i].setKey(sBodyItemKeys[i]);
			items[i].setName(sBodyItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setWidth(100);
			items[i].setEdit(false);
			items[i].setNull(false);
			if ("pk_log".equals(sBodyItemKeys[i]))
			{
				items[i].setShow(false);
			} else if ("content".equals(sBodyItemKeys[i]))
			{
				items[i].setWidth(600);
			}else if ("stage".equals(sBodyItemKeys[i]))
			{
				items[i].setWidth(150);
			}
		}
		return items;
	}

	/**
	 * 填充表格数据
	 * 
	 */
	public void fillGrid(MPOS_LogVO[] vos)
	{

		this.getBillModel().setBodyDataVO(vos);
		// 执行表体项加载公式
		this.getBillModel().execLoadFormula();
	}

	/**
	 * 取得页面VO
	 * 
	 * @return Grade_AccidentLogVO[]
	 */
	public MPOS_LogVO[] getBodyValueVOs()
	{
		return (MPOS_LogVO[]) getBillModel().getBodyValueVOs(MPOS_LogVO.class.getName());
	}

	/**
	 * 取得页面选择的VO(鼠标选中)
	 * 
	 * @return Grade_FBVO[]
	 */
	public MPOS_LogVO[] getBillSelectVOs()
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
		MPOS_LogVO[] vos = (MPOS_LogVO[]) getBillModel().getBodySelectedVOs(MPOS_LogVO.class.getName());
		return vos;
	}

}
