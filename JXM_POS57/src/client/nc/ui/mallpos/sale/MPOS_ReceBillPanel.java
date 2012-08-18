package nc.ui.mallpos.sale;

import javax.swing.ListSelectionModel;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillItemNumberFormat;
import nc.ui.pub.bill.BillModel;
import nc.vo.mallpos.sale.MPOS_ReceivablesVO;

/**
 * Ӧ�տ������
 * 
 * @author chenjun
 * @date 2011-11-22 ����03:01:55
 */
public class MPOS_ReceBillPanel extends BillCardPanel
{

	private static final long serialVersionUID = 1L;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_ReceBillPanel()
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
		getBodyPanel().setTotalRowShow(true);// ���úϼ���
		getAddLineMenuItem().setVisible(false);
		getDelLineMenuItem().setVisible(false);
		getInsertLineMenuItem().setVisible(false);
		getCopyLineMenuItem().setVisible(false);
		getPasteLineMenuItem().setVisible(false);
		getPasteLineToTailMenuItem().setVisible(false);
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
		String sBodyItemKeys[] = new String[] { "pk_receivables", "pk_cubasdoc", "custcode", "custname", "billdate", "amount", "billstatus", "strbillstatus", "pk_busibill" };
		String sBodyItemCaptions[] = new String[] { "����", "�̻�����", "�̻�����", "�̻�����", "��������", "Ӧ�ս��", "״̬", "״̬", "�������" };
		BillItem items[] = new BillItem[sBodyItemKeys.length];
		BillItemNumberFormat format = new BillItemNumberFormat();
		format.setNegativeSign(false);//����ʾ����
		for (int i = 0; i < sBodyItemKeys.length; i++)
		{
			items[i] = new BillItem();
			items[i].setKey(sBodyItemKeys[i]);
			items[i].setName(sBodyItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setWidth(100);
			items[i].setEdit(false);
			items[i].setNull(false);
			if ("pk_receivables".equals(sBodyItemKeys[i]) || "pk_cubasdoc".equals(sBodyItemKeys[i]) || "billstatus".equals(sBodyItemKeys[i]) || "pk_busibill".equals(sBodyItemKeys[i]))
			{
				items[i].setShow(false);
			} else if ("amount".equals(sBodyItemKeys[i]))
			{
				items[i].setDataType(BillItem.DECIMAL);
				items[i].setDecimalDigits(8);
				items[i].setWidth(150);
				items[i].setTatol(true);
				
				items[i].setNumberFormat(format);
			} else if ("custname".equals(sBodyItemKeys[i]))
			{
				items[i].setWidth(150);
			}
		}
		return items;
	}

	public void fillGrid(MPOS_ReceivablesVO[] vos)
	{

		this.getBillModel().setBodyDataVO(vos);
		// ִ�б�������ع�ʽ
		this.getBillModel().execLoadFormula();

		if (vos != null && vos.length > 0)
		{
			// ���ñ�ͷ���ݵ�һ�б�ѡ��
			this.getBillTable().setRowSelectionInterval(0, 0);
		}
	}

	/**
	 * ȡ��ҳ��VO
	 * 
	 * @return KNPOS_SaleVO[]
	 */
	public MPOS_ReceivablesVO[] getBodyValueVOs()
	{
		return (MPOS_ReceivablesVO[]) getBillModel().getBodyValueVOs(MPOS_ReceivablesVO.class.getName());
	}

	/**
	 * ȡ��ҳ��ѡ���VO(���ѡ��)
	 * 
	 * @return KNPOS_SaleVO[]
	 */
	public MPOS_ReceivablesVO[] getBillSelectVOs()
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
		MPOS_ReceivablesVO[] vos = (MPOS_ReceivablesVO[]) getBillModel().getBodySelectedVOs(MPOS_ReceivablesVO.class.getName());
		return vos;
	}

}
