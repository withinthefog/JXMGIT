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
	 * ��ʼ��
	 * 
	 */
	public void initialize()
	{
		// ���ñ�����
		setBillData(getThisBillData());
		// ��������
		getBillTable().setColumnSelectionAllowed(false);
		getBodyPanel().getRendererVO().setShowThMark(true);
		getBodyPanel().setTotalRowShow(false);// ���úϼ���
		getAddLineMenuItem().setVisible(false);
		getDelLineMenuItem().setVisible(false);
		getInsertLineMenuItem().setVisible(false);
		getCopyLineMenuItem().setVisible(false);
		getPasteLineMenuItem().setVisible(false);
		getPasteLineToTailMenuItem().setVisible(false);
		// �̶�����
		getBillTable().removeSortListener();
		// ����ֻ�ܵ�ѡ
		this.getBillTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	/**
	 * ��ʼ��������
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
	 * �õ�������
	 * 
	 * @return BillItem[]
	 */
	public BillItem[] getThisBodyItems()
	{
		String sBodyItemKeys[] = new String[] { "pk_log", "log_date", "stage", "content" };
		String sBodyItemCaptions[] = new String[] { "����", "����", "ҵ��׶�", "��־����" };
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
	 * ���������
	 * 
	 */
	public void fillGrid(MPOS_LogVO[] vos)
	{

		this.getBillModel().setBodyDataVO(vos);
		// ִ�б�������ع�ʽ
		this.getBillModel().execLoadFormula();
	}

	/**
	 * ȡ��ҳ��VO
	 * 
	 * @return Grade_AccidentLogVO[]
	 */
	public MPOS_LogVO[] getBodyValueVOs()
	{
		return (MPOS_LogVO[]) getBillModel().getBodyValueVOs(MPOS_LogVO.class.getName());
	}

	/**
	 * ȡ��ҳ��ѡ���VO(���ѡ��)
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
