package nc.ui.mallpos.pub;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ListSelectionModel;

import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillData;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillItem;
import nc.ui.pub.bill.BillModel;
import nc.vo.mallpos.tools.MPOS_Toolkit;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.ExtendedAggregatedValueObject;
import nc.vo.pub.SuperVO;

import org.apache.commons.lang.StringUtils;

/**
 * BillCardPanel����
 * 
 * @author chenjun
 * @date 2011-4-14 ����10:47:55
 * @param
 *       <Th> ��ͷvo
 * @param <Tb>
 *            ����vo
 * @version 1.0
 */
public abstract class BaseBillCardPanel<Th extends SuperVO, Tb> extends BillCardPanel implements BillEditListener
{

	private static final long serialVersionUID = 1L;

	/**
	 * �Ƿ񱣳��ϴ�ѡ������Ȼ����ѡ��״̬
	 */
	private boolean isKeepLastSelectedRow = true;

	/**
	 * �ͻ��˲���
	 */
	public ClientEnvironment ce = ClientEnvironment.getInstance();

	public BaseBillCardPanel()
	{
		super();
		this.initialize();
	}

	/**
	 * ��ʼ��
	 * 
	 */
	private void initialize()
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
		// ����ֻ�ܵ�ѡ
		this.getBillTable().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// ѡ������
		this.getBillTable().setColumnSelectionAllowed(false);
		// getBillTable().removeSortListener();

		initializeEX();
	}

	public abstract void initializeEX();

	/**
	 * ��ʼ������Ŀ
	 * 
	 * @return BillData
	 */
	private BillData getThisBillData()
	{
		BillData billdata = new BillData();
		billdata.setHeadItems(getThisHeadItems());
		this.setBodyItems(billdata);
		billdata.setTailItems(getThisTailItems());
		return billdata;
	}

	/**
	 * ���ñ�����Ŀ������Ƕ��ӱ�������д�÷���
	 * 
	 * @param billdata
	 * @return
	 */
	public BillData setBodyItems(BillData billdata)
	{
		billdata.setBodyItems(getThisBodyItems());
		return billdata;
	}

	/**
	 * �õ���ͷ��
	 * 
	 * @return BillItem[], ���û�б�ͷ�ɷ���NULL
	 */
	public abstract BillItem[] getThisHeadItems();

	/**
	 * �õ�������
	 * 
	 * @return BillItem[], ���û�б���ɷ���NULL
	 */
	public abstract BillItem[] getThisBodyItems();

	/**
	 * �õ���β��
	 * 
	 * @return BillItem[], ���û�б�β�ɷ���NULL
	 */
	public abstract BillItem[] getThisTailItems();

	/**
	 * �õ����帴ѡ��KEYֵ
	 * 
	 * @return ���帴ѡ��KEYֵ�����û�пɷ���NULL
	 */
	public abstract String getBodyCheckBoxKey();

	/**
	 * �õ��ۺ�VO Class,����������getAggBillVO(),������д�÷���
	 * 
	 * @return
	 */
	public Class getAggVOClass()
	{
		return null;
	}

	/**
	 * �õ����ӱ�ģʽ�£����ӱ��������ƣ�����getAggExBillVO(),������д�÷���
	 * 
	 * @return
	 */
	public String[] getMultiChildClassName()
	{
		return null;
	}

	/**
	 * ���������
	 * <p>
	 * Ĭ�ϻ�ѡ����һ��ѡ�е��У���ͨ������<code>setKeepLastSelectedRow(boolean isKeepLastSelectedRow)</code>��ֵ���ı�
	 * </p>
	 * 
	 * @param vos
	 */
	public void fillGrid(Tb[] vos)
	{
		// ��ǰ���ѡ�е���
		int selectedRowIndex = -1;
		// ��ǰ���帴ѡ��ѡ�е���
		int[] selectedRowsIndex = null;
		// �������ɾ��(���������ݺ���������һ����)
		if (vos != null && vos.length == getRowCount())
		{
			selectedRowsIndex = this.getSelectedRowByCheckBox();
			selectedRowIndex = getBillTable().getSelectedRow();
		}

		this.getBillModel().setBodyDataVO((SuperVO[]) vos);
		// ִ�б�������ع�ʽ
		this.getBillModel().execLoadFormula();

		if (vos != null && vos.length > 0)
		{
			this.setSelectedRowByCheckBox(selectedRowsIndex);
			if (selectedRowIndex == -1 || !this.isKeepLastSelectedRow())
			{
				// ���ñ�ͷ���ݵ�һ�б�ѡ��
				this.getBillTable().setRowSelectionInterval(0, 0);
			} else
			{
				this.getBillTable().setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
			}
		}
	}

	/**
	 * װ��ҳ�� �Ծۺ�VO���
	 * 
	 * @param p_billvo
	 */
	public void fillGrid(AggregatedValueObject p_billvo)
	{
		setBillValueVO(p_billvo);
		getBillModel().execLoadFormula();
		getBillModel().updateValue();
	}

	/**
	 * װ��ҳ�� �Ծۺ�VO���
	 * 
	 * @param p_billvo
	 */
	public void fillGrid(ExtendedAggregatedValueObject p_billvo)
	{
		setBillValueVO(p_billvo);
		String[] tablecodes = p_billvo.getTableCodes();
		for (int i = 0; i < tablecodes.length; i++)
		{
			getBillModel(tablecodes[i]).execLoadFormula();
		}
		getBillModel().updateValue();
	}

	/**
	 * ��ñ�ͷ����ۺ�VO
	 * <p>
	 * ���������дgetAggVOClass()����
	 * </p>
	 */
	public AggregatedValueObject getAggBillVO()
	{
		return getBillValueVO(getAggVOClass().getName(), getClassGenricType(0).getName(), getClassGenricType(1).getName());
	}

	/**
	 * ��ñ�ͷ����ۺ�VO
	 * <p>
	 * ���������дgetAggVOClass()�Լ�getMultiChildClassName()����������
	 * </p>
	 */
	public ExtendedAggregatedValueObject getAggExBillVO()
	{
		return getBillValueVOExtended(getAggVOClass().getName(), getClassGenricType(0).getName(), this.getMultiChildClassName());
	}

	/**
	 * ����������ó���Ĳ�������
	 * 
	 * @param index
	 *            ����
	 */
	public Class getClassGenricType(final int index)
	{
		Type genType = this.getClass().getGenericSuperclass();
		if (!(genType instanceof ParameterizedType))
		{
			return Object.class;
		}
		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
		if (index >= params.length || index < 0)
		{
			return Object.class;
		}
		if (!(params[index] instanceof Class))
		{
			return Object.class;
		}
		return (Class) params[index];
	}

	/**
	 * ȡ�ñ���VO
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Tb[] getBodyValueVOs()
	{
		return (Tb[]) getBillModel().getBodyValueVOs(getClassGenricType(1).getName());
	}

	@SuppressWarnings("unchecked")
	public Tb[] getBodyValueChangeVOs()
	{
		return (Tb[]) getBillModel().getBodyValueChangeVOs(getClassGenricType(1).getName());
	}

	/**
	 * ȡ�ñ���VO:row-vo��ֵ��
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Tb> getBodyValueVOsHM()
	{
		int row_count = getRowCount();
		HashMap<Integer, Tb> hm = new HashMap<Integer, Tb>();
		for (int row = 0; row < row_count; row++)
		{
			hm.put(row, (Tb) this.getBillModel().getBodyValueRowVO(row, getClassGenricType(1).getName()));
		}
		return hm;
	}

	/**
	 * ȡ�ñ������ѡ���VO
	 * 
	 * @return ���嵱ǰ���ѡ���е�VO
	 */
	@SuppressWarnings("unchecked")
	public Tb[] getBodySelectVOsByMouse()
	{
		int row_count = getRowCount();
		if (row_count <= 0)
			return null;
		int[] rows = getBillTable().getSelectedRows();
		if (rows == null || rows.length == 0)
		{
			return null;
		}
		for (int i = 0; i < row_count; i++)
		{
			getBillModel().setRowState(i, BillModel.NORMAL);
		}
		for (int xxx = 0; xxx < rows.length; xxx++)
		{
			getBillModel().setRowState(rows[xxx], BillModel.SELECTED);
		}
		Tb[] vos = (Tb[]) getBillModel().getBodySelectedVOs(getClassGenricType(1).getName());
		return vos;
	}

	/**
	 * ȡ�ñ��帴ѡ��ѡ�е�VO
	 * 
	 * @return ���帴ѡ��ѡ���е�VO
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Tb> getBodySelectVOsByCheckBox()
	{
		ArrayList<Tb> list = new ArrayList<Tb>();
		if (StringUtils.isNotEmpty(this.getBodyCheckBoxKey()))
		{
			for (int row = 0; row < getRowCount(); row++)
			{
				Object select = this.getBillModel().getValueAt(row, this.getBodyCheckBoxKey());
				if (select != null && ((Boolean) select).booleanValue())
				{
					list.add((Tb) this.getBillModel().getBodyValueRowVO(row, getClassGenricType(1).getName()));
				}
			}
		}
		return list;
	}

	/**
	 * ȡ�ñ��帴ѡ��ѡ�е�VO:row-vo��ֵ��
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public HashMap<Integer, Tb> getBodySelectVOsHMByCheckBox()
	{
		HashMap<Integer, Tb> hm = new HashMap<Integer, Tb>();
		if (StringUtils.isNotEmpty(this.getBodyCheckBoxKey()))
		{
			for (int row = 0; row < getRowCount(); row++)
			{
				Object select = this.getBillModel().getValueAt(row, this.getBodyCheckBoxKey());
				if (select != null && ((Boolean) select).booleanValue())
				{
					hm.put(row, (Tb) this.getBillModel().getBodyValueRowVO(row, getClassGenricType(1).getName()));
				}
			}
		}
		return hm;
	}

	/**
	 * ����������
	 * 
	 * @param key
	 *            ���ݴ˲����жϸ����Ƿ��ǿ���
	 */
	public void clearNullLine(String key)
	{
		int rowcount = getRowCount();
		if (rowcount <= 0)
			return;
		ArrayList<Integer> dellist = new ArrayList<Integer>();
		// �����ж�
		for (int idx = 0; idx < rowcount; idx++)
		{
			Object itemjylb = getBodyValueAt(idx, key);
			if (itemjylb == null)
			{
				dellist.add(idx);
			}
		}
		if (dellist.size() <= 0)
			return;
		int[] dels = new int[dellist.size()];
		for (int iii = 0; iii < dels.length; iii++)
		{
			dels[iii] = dellist.get(iii);
		}
		getBillModel().delLine(dels);
	}

	/**
	 * �õ���ѡ��ѡ�����
	 * 
	 * @return ��ѡ��ѡ�����,�������û�и�ѡ��KEY�򷵻�NULL
	 */
	private int[] getSelectedRowByCheckBox()
	{
		if (StringUtils.isEmpty(this.getBodyCheckBoxKey()))
		{
			return null;
		}
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int row = 0; row < getRowCount(); row++)
		{
			Object select = this.getBillModel().getValueAt(row, this.getBodyCheckBoxKey());
			if (select != null && ((Boolean) select).booleanValue())
			{
				list.add(row);
			}
		}
		int[] rows = new int[list.size()];
		for (int i = 0; i < list.size(); i++)
		{
			rows[i] = list.get(i);
		}
		return rows;
	}

	/**
	 * ���ø�ѡ��ѡ����
	 * 
	 * @param rows
	 *            Ҫѡ�����
	 */
	public void setSelectedRowByCheckBox(int[] rows)
	{
		if (StringUtils.isEmpty(this.getBodyCheckBoxKey()) || !this.isKeepLastSelectedRow())
		{
			return;
		}
		if (rows != null)
		{
			for (int i = 0; i < rows.length; i++)
			{
				this.setSelectedRowByCheckBox(rows[i]);
			}
		}
	}

	/**
	 * ���ø�ѡ��ѡ����
	 * 
	 * @param row
	 *            Ҫѡ�����
	 */
	public void setSelectedRowByCheckBox(int row)
	{
		this.getBillModel().setValueAt(true, row, this.getBodyCheckBoxKey());
	}

	/**
	 * �����е�ѡ��״̬
	 * 
	 * @param isCheck
	 *            �Ƿ�ѡ��
	 */
	public void setRowCheckStatus(boolean isCheck)
	{
		if (StringUtils.isEmpty(this.getBodyCheckBoxKey()))
		{
			return;
		}
		for (int i = 0; i < getRowCount(); i++)
		{
			this.getBillModel().setValueAt(isCheck, i, this.getBodyCheckBoxKey());
			afterEdit(new BillEditEvent(this.getBodyItem(this.getBodyCheckBoxKey()).getComponent(), isCheck, getBodyCheckBoxKey(), i));
		}
	}

	public void afterEdit(BillEditEvent arg0)
	{
		this.execHeadTailEditFormulas(this.getHeadItem(arg0.getKey()));
	}

	/**
	 * �����е�ѡ��״̬
	 * 
	 * @param row
	 *            ������
	 * @param isCheck
	 *            �Ƿ�ѡ��
	 */
	public void setRowCheckStatus(int row, boolean isCheck)
	{
		if (StringUtils.isEmpty(this.getBodyCheckBoxKey()))
		{
			return;
		}
		this.getBillModel().setValueAt(isCheck, row, this.getBodyCheckBoxKey());
	}

	/**
	 * �ж��и�ѡ���Ƿ�ѡ��
	 * 
	 * @param row
	 * @return
	 */
	public boolean isRowChecked(int row)
	{
		boolean ischecked = false;
		if (StringUtils.isNotEmpty(this.getBodyCheckBoxKey()))
		{
			Object select = this.getBillModel().getValueAt(row, this.getBodyCheckBoxKey());
			if (select != null && ((Boolean) select).booleanValue())
			{
				ischecked = true;
			}
		}
		return ischecked;
	}

	/**
	 * @return the isKeepLastSelectedRow
	 */
	public boolean isKeepLastSelectedRow()
	{
		return isKeepLastSelectedRow;
	}

	/**
	 * @param isKeepLastSelectedRow
	 *            the isKeepLastSelectedRow to set
	 */
	public void setKeepLastSelectedRow(boolean isKeepLastSelectedRow)
	{
		this.isKeepLastSelectedRow = isKeepLastSelectedRow;
	}
	
	/**
	 * �õ���ͷVO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Th getHeadVO()
	{
		return (Th) getBillData().getHeaderValueVO(getClassGenricType(0).getName());
	}
	
	/**
	 * ���ñ�ͷVO
	 * @param t
	 */
	public void setHeadVO(Th t)
	{
		getBillData().setHeaderValueVO(t);
	}

//	/**
//	 * �õ���ͷVO
//	 * 
//	 * @param vo
//	 * 
//	 * @return ���ݵ�ǰ��ͷ��ֵ���õ�����VO�У�Ȼ�󷵻�
//	 */
//	public Th getHeadVO(Th vo)
//	{
//		BillItem[] items = this.getBillData().getHeadTailItems();
//		vo = this.getHeadVO(vo, items);
//		return vo;
//	}
//
//	/**
//	 * �õ���ͷVO
//	 * 
//	 * @param vo
//	 * @param items
//	 *            ������
//	 * @return ���ݵ�ǰ��ͷ��ֵ���õ�����VO�У�Ȼ�󷵻�
//	 */
//	public Th getHeadVO(Th vo, BillItem[] items)
//	{
//		if (items == null)
//		{
//			return vo;
//		}
//		for (int xxx = 0; xxx < items.length; xxx++)
//		{
//			Object valueobject = null;
//			try
//			{
//				// ����ǲ��գ�ȡ��ʾֵ
//				if (items[xxx].getDataType() == BillItem.UFREF)
//				{
//					UIRefPane ref = (UIRefPane) items[xxx].getComponent();
//					valueobject = ref.getText();
//				} else
//				{
//					valueobject = items[xxx].converType(items[xxx].getValueObject());
//					// ����VO����ֵ�����Item��DataType��VO�������Ͳ�һ�»ᱨ��argument type
//					// mismatch����˱��뱣��һ�¡�
//				}
//				vo.setAttributeValue(items[xxx].getKey(), valueobject);
//			} catch (Exception e)
//			{
//			}
//		}
//		return vo;
//	}

	/**
	 * ��ͷ����У���¼��
	 * 
	 * @return
	 */
	public String checkHeadNullValues()
	{
		BillItem[] items = getHeadShowItems();
		StringBuffer msg = new StringBuffer();
		for (int xxx = 0; xxx < items.length; xxx++)
		{
			if (items[xxx].isShow() && items[xxx].isEdit() && items[xxx].isNull())
			{
				if (items[xxx].getValueObject() == null || "".equals(items[xxx].getValueObject().toString()))
					msg.append("��ͷ��" + items[xxx].getName() + "  ����Ϊ��!\n");
			}
		}
		return msg.toString();
	}

	/***************************************************************************
	 * ��������У���¼��
	 * <p>
	 * itemҪ����edit����
	 * </p>
	 * 
	 * @return
	 */
	public String checkBodyNullValues()
	{

		StringBuffer msg = new StringBuffer();
		String[] bodyTableCodes = this.getBillData().getBodyTableCodes();
		for (String tableCode : bodyTableCodes)
		{
			if (this.getRowCount(tableCode) <= 0)
			{
				return (this.getBillData().getBodyTableName(tableCode).equals("table") ? "" : this.getBillData().getBodyTableName(tableCode)) + "���岻��Ϊ��!";
			} else
			{
				BillItem[] items = this.getBillData().getBodyShowItems(tableCode);
				for (int xx = 0; xx < this.getRowCount(tableCode); xx++)
				{
					for (int xxx = 0; xxx < items.length; xxx++)
					{
						if (items[xxx].isEdit() && items[xxx].isNull())
						{
							if (this.getBillModel(tableCode).getValueAt(xx, items[xxx].getKey()) == null
									|| "".equals(this.getBillModel(tableCode).getValueAt(xx, items[xxx].getKey())))
								msg.append((items[xxx].getTableName() == null ? "" : items[xxx].getTableName()) + "�����" + (xx + 1) + "�У�" + items[xxx].getName() + "  ����Ϊ��!\n");
						}
					}
				}
			}
		}
		return msg.toString();
	}

	/**
	 * ��ͷ����������У���¼��
	 * 
	 * @return
	 */
	public String checkBillNullValues()
	{
		StringBuffer msg = new StringBuffer();
		msg.append(this.checkHeadNullValues());
		msg.append(this.checkBodyNullValues());
		return msg.toString();
	}

	/**
	 * �����������ݴ�СУ��
	 * 
	 * @param tableCode
	 *            ����ҳǩ����
	 * @param startdate_key
	 *            ��ʼ����billitem key
	 * @param enddate_key
	 *            �������� billitem key
	 * @return
	 */
	public String checkBodyDateValues(String tableCode, String startdate_key, String enddate_key)
	{
		if (this.getRowCount(tableCode) <= 0)
		{
			return "���岻��Ϊ��!";
		} else
		{
			StringBuffer msg = new StringBuffer();
			for (int xx = 0; xx < getRowCount(tableCode); xx++)
			{
				String startdate = this.getBillModel(tableCode).getValueAt(xx, startdate_key) == null ? "" : this.getBillModel(tableCode).getValueAt(xx, startdate_key).toString();
				String enddate = this.getBillModel(tableCode).getValueAt(xx, enddate_key) == null ? "" : this.getBillModel(tableCode).getValueAt(xx, enddate_key).toString();
				if (StringUtils.isNotEmpty(startdate) && StringUtils.isNotEmpty(enddate))
					if (MPOS_Toolkit.compareDateFromAfterTo(startdate, enddate) > 0)
						msg.append((this.getBillData().getBodyTableName(tableCode).equals("table") ? "" : this.getBillData().getBodyTableName(tableCode)) + "�����" + (xx + 1)
								+ "�У���ʼ���ڲ��ܴ��ڽ�������!\n");
			}
			return msg.toString();
		}
	}

	/**
	 * �����������ݴ�СУ��
	 * 
	 * @param startdate_key
	 *            ��ʼ����billitem key
	 * @param enddate_key
	 *            �������� billitem key
	 * @return
	 */
	public String checkBodyDateValues(String startdate_key, String enddate_key)
	{
		return this.checkBodyDateValues(this.getCurrentBodyTableCode(), startdate_key, enddate_key);
	}
}
