package nc.ui.mallpos.voucher;

import javax.swing.ListSelectionModel;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillItem;
import nc.vo.mallpos.voucher.MPOS_VoucherConfigVO;

public class MPOS_VoucherConfigCardPanel extends BillCardPanel
{

	private static final long serialVersionUID = 1L;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_VoucherConfigCardPanel()
	{
		super();
		this.initialize();
		this.initializeEx();
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

	public void initializeEx()
	{
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

	private BillItem[] getThisBodyItems()
	{
		String sBodyItemKeys[] = new String[] { "pk_voucherconfig", "itemcode", "itemname", "accsubjcode", "accsubjname" };
		String sBodyItemCaptions[] = new String[] { "����", "��Ŀ����", "��Ŀ����", "��Ŀ����", "��Ŀ����" };
		BillItem items[] = new BillItem[sBodyItemKeys.length];
		for (int i = 0; i < sBodyItemKeys.length; i++)
		{
			items[i] = new BillItem();
			items[i].setKey(sBodyItemKeys[i]);
			items[i].setName(sBodyItemCaptions[i]);
			items[i].setShowOrder(i);
			items[i].setEdit(false);
			items[i].setNull(false);
			items[i].setWidth(100);

			if ("pk_voucherconfig".equals(sBodyItemKeys[i]))
			{
				items[i].setShow(false);
			} else if ("accsubjcode".equals(sBodyItemKeys[i]))
			{
				items[i].setNull(true);
				items[i].setEdit(true);
			} else if ("accsubjname".equals(sBodyItemKeys[i]))
			{
				items[i].setEdit(true);
			}
		}
		return items;
	}

	public void fillGrid(MPOS_VoucherConfigVO[] vos)
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
	 * ȡ�ñ���vos
	 * 
	 * @return KNPOS_SaleVO[]
	 */
	public MPOS_VoucherConfigVO[] getBodyValueVOs()
	{
		return (MPOS_VoucherConfigVO[]) getBillModel().getBodyValueVOs(MPOS_VoucherConfigVO.class.getName());
	}

	/**
	 * �õ���ͷVO
	 * 
	 * @return ���ݵ�ǰ��ͷ��ֵ���õ�����VO�У�Ȼ�󷵻�
	 */
	public MPOS_VoucherConfigVO getHeadVO()
	{
		MPOS_VoucherConfigVO vo = new MPOS_VoucherConfigVO();
		vo = this.getHeadVO(vo, getHeadItems());
		return vo;
	}

	/**
	 * �õ���ͷVO
	 * 
	 * @param vo
	 * @param items
	 *            ������
	 * @return ���ݵ�ǰ��ͷ��ֵ���õ�����VO�У�Ȼ�󷵻�
	 */
	private MPOS_VoucherConfigVO getHeadVO(MPOS_VoucherConfigVO vo, BillItem[] items)
	{
		for (int xxx = 0; xxx < items.length; xxx++)
		{
			Object valueobject = null;
			try
			{
				valueobject = items[xxx].converType(items[xxx].getValueObject());
				// ����VO����ֵ�����Item��DataType��VO�������Ͳ�һ�»ᱨ��argument type
				// mismatch����˱��뱣��һ�¡�
				vo.setAttributeValue(items[xxx].getKey(), valueobject);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		return vo;
	}

	/**
	 * ���ñ�ͷVO
	 * 
	 */
	public void setHeadVO(MPOS_VoucherConfigVO vo)
	{
		this.setHeadVO(vo, getHeadItems());
	}

	/**
	 * ���ñ�ͷVO
	 * 
	 * @param vo
	 *            VO����
	 * @param items
	 *            ������
	 */
	private void setHeadVO(MPOS_VoucherConfigVO vo, BillItem[] items)
	{
		if (vo == null || items == null)
		{
			return;
		}
		for (int xxx = 0; xxx < items.length; xxx++)
		{
			items[xxx].setValue(vo.getAttributeValue(items[xxx].getKey()));
		}
	}

}
