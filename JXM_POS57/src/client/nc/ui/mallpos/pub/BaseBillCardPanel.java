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
 * BillCardPanel基类
 * 
 * @author chenjun
 * @date 2011-4-14 上午10:47:55
 * @param
 *       <Th> 表头vo
 * @param <Tb>
 *            表体vo
 * @version 1.0
 */
public abstract class BaseBillCardPanel<Th extends SuperVO, Tb> extends BillCardPanel implements BillEditListener
{

	private static final long serialVersionUID = 1L;

	/**
	 * 是否保持上次选中行仍然处于选中状态
	 */
	private boolean isKeepLastSelectedRow = true;

	/**
	 * 客户端参数
	 */
	public ClientEnvironment ce = ClientEnvironment.getInstance();

	public BaseBillCardPanel()
	{
		super();
		this.initialize();
	}

	/**
	 * 初始化
	 * 
	 */
	private void initialize()
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
		// 选中整行
		this.getBillTable().setColumnSelectionAllowed(false);
		// getBillTable().removeSortListener();

		initializeEX();
	}

	public abstract void initializeEX();

	/**
	 * 初始化表项目
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
	 * 设置表体项目，如果是多子表，子类重写该方法
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
	 * 得到表头项
	 * 
	 * @return BillItem[], 如果没有表头可返回NULL
	 */
	public abstract BillItem[] getThisHeadItems();

	/**
	 * 得到表体项
	 * 
	 * @return BillItem[], 如果没有表体可返回NULL
	 */
	public abstract BillItem[] getThisBodyItems();

	/**
	 * 得到表尾项
	 * 
	 * @return BillItem[], 如果没有表尾可返回NULL
	 */
	public abstract BillItem[] getThisTailItems();

	/**
	 * 得到表体复选框KEY值
	 * 
	 * @return 表体复选框KEY值，如果没有可返回NULL
	 */
	public abstract String getBodyCheckBoxKey();

	/**
	 * 得到聚合VO Class,如果子类调用getAggBillVO(),必须重写该方法
	 * 
	 * @return
	 */
	public Class getAggVOClass()
	{
		return null;
	}

	/**
	 * 得到多子表模式下，各子表类型名称，调用getAggExBillVO(),必须重写该方法
	 * 
	 * @return
	 */
	public String[] getMultiChildClassName()
	{
		return null;
	}

	/**
	 * 填充表格数据
	 * <p>
	 * 默认会选中上一次选中的行，可通过设置<code>setKeepLastSelectedRow(boolean isKeepLastSelectedRow)</code>的值来改变
	 * </p>
	 * 
	 * @param vos
	 */
	public void fillGrid(Tb[] vos)
	{
		// 当前鼠标选中的行
		int selectedRowIndex = -1;
		// 当前表体复选框选中的行
		int[] selectedRowsIndex = null;
		// 如果不是删除(新填充的数据和现有数据一样多)
		if (vos != null && vos.length == getRowCount())
		{
			selectedRowsIndex = this.getSelectedRowByCheckBox();
			selectedRowIndex = getBillTable().getSelectedRow();
		}

		this.getBillModel().setBodyDataVO((SuperVO[]) vos);
		// 执行表体项加载公式
		this.getBillModel().execLoadFormula();

		if (vos != null && vos.length > 0)
		{
			this.setSelectedRowByCheckBox(selectedRowsIndex);
			if (selectedRowIndex == -1 || !this.isKeepLastSelectedRow())
			{
				// 设置表头数据第一行被选中
				this.getBillTable().setRowSelectionInterval(0, 0);
			} else
			{
				this.getBillTable().setRowSelectionInterval(selectedRowIndex, selectedRowIndex);
			}
		}
	}

	/**
	 * 装载页面 以聚合VO填充
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
	 * 装载页面 以聚合VO填充
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
	 * 获得表头表体聚合VO
	 * <p>
	 * 子类必须重写getAggVOClass()方法
	 * </p>
	 */
	public AggregatedValueObject getAggBillVO()
	{
		return getBillValueVO(getAggVOClass().getName(), getClassGenricType(0).getName(), getClassGenricType(1).getName());
	}

	/**
	 * 获得表头表体聚合VO
	 * <p>
	 * 子类必须重写getAggVOClass()以及getMultiChildClassName()这两个方法
	 * </p>
	 */
	public ExtendedAggregatedValueObject getAggExBillVO()
	{
		return getBillValueVOExtended(getAggVOClass().getName(), getClassGenricType(0).getName(), this.getMultiChildClassName());
	}

	/**
	 * 根据索引获得超类的参数类型
	 * 
	 * @param index
	 *            索引
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
	 * 取得表体VO
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
	 * 取得表体VO:row-vo键值对
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
	 * 取得表体鼠标选择的VO
	 * 
	 * @return 表体当前鼠标选中行的VO
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
	 * 取得表体复选框选中的VO
	 * 
	 * @return 表体复选框选中行的VO
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
	 * 取得表体复选框选中的VO:row-vo键值对
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
	 * 清除表体空行
	 * 
	 * @param key
	 *            根据此参数判断该行是否是空行
	 */
	public void clearNullLine(String key)
	{
		int rowcount = getRowCount();
		if (rowcount <= 0)
			return;
		ArrayList<Integer> dellist = new ArrayList<Integer>();
		// 空行判断
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
	 * 得到复选框选择的行
	 * 
	 * @return 复选框选择的行,如果表体没有复选框KEY则返回NULL
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
	 * 设置复选框选择行
	 * 
	 * @param rows
	 *            要选择的行
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
	 * 设置复选框选择行
	 * 
	 * @param row
	 *            要选择的行
	 */
	public void setSelectedRowByCheckBox(int row)
	{
		this.getBillModel().setValueAt(true, row, this.getBodyCheckBoxKey());
	}

	/**
	 * 设置行的选择状态
	 * 
	 * @param isCheck
	 *            是否选中
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
	 * 设置行的选择状态
	 * 
	 * @param row
	 *            行索引
	 * @param isCheck
	 *            是否选中
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
	 * 判断行复选框是否选中
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
	 * 得到表头VO
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Th getHeadVO()
	{
		return (Th) getBillData().getHeaderValueVO(getClassGenricType(0).getName());
	}
	
	/**
	 * 设置表头VO
	 * @param t
	 */
	public void setHeadVO(Th t)
	{
		getBillData().setHeaderValueVO(t);
	}

//	/**
//	 * 得到表头VO
//	 * 
//	 * @param vo
//	 * 
//	 * @return 根据当前表头项值设置到参数VO中，然后返回
//	 */
//	public Th getHeadVO(Th vo)
//	{
//		BillItem[] items = this.getBillData().getHeadTailItems();
//		vo = this.getHeadVO(vo, items);
//		return vo;
//	}
//
//	/**
//	 * 得到表头VO
//	 * 
//	 * @param vo
//	 * @param items
//	 *            数据项
//	 * @return 根据当前表头项值设置到参数VO中，然后返回
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
//				// 如果是参照，取显示值
//				if (items[xxx].getDataType() == BillItem.UFREF)
//				{
//					UIRefPane ref = (UIRefPane) items[xxx].getComponent();
//					valueobject = ref.getText();
//				} else
//				{
//					valueobject = items[xxx].converType(items[xxx].getValueObject());
//					// 设置VO属性值，如果Item的DataType与VO属性类型不一致会报：argument type
//					// mismatch，因此必须保持一致。
//				}
//				vo.setAttributeValue(items[xxx].getKey(), valueobject);
//			} catch (Exception e)
//			{
//			}
//		}
//		return vo;
//	}

	/**
	 * 表头数据校验必录项
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
					msg.append("表头：" + items[xxx].getName() + "  不能为空!\n");
			}
		}
		return msg.toString();
	}

	/***************************************************************************
	 * 表体数据校验必录项
	 * <p>
	 * item要设置edit属性
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
				return (this.getBillData().getBodyTableName(tableCode).equals("table") ? "" : this.getBillData().getBodyTableName(tableCode)) + "表体不能为空!";
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
								msg.append((items[xxx].getTableName() == null ? "" : items[xxx].getTableName()) + "表体第" + (xx + 1) + "行：" + items[xxx].getName() + "  不能为空!\n");
						}
					}
				}
			}
		}
		return msg.toString();
	}

	/**
	 * 表头、表体数据校验必录项
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
	 * 表体日期数据大小校验
	 * 
	 * @param tableCode
	 *            表体页签代码
	 * @param startdate_key
	 *            开始日期billitem key
	 * @param enddate_key
	 *            结束日期 billitem key
	 * @return
	 */
	public String checkBodyDateValues(String tableCode, String startdate_key, String enddate_key)
	{
		if (this.getRowCount(tableCode) <= 0)
		{
			return "表体不能为空!";
		} else
		{
			StringBuffer msg = new StringBuffer();
			for (int xx = 0; xx < getRowCount(tableCode); xx++)
			{
				String startdate = this.getBillModel(tableCode).getValueAt(xx, startdate_key) == null ? "" : this.getBillModel(tableCode).getValueAt(xx, startdate_key).toString();
				String enddate = this.getBillModel(tableCode).getValueAt(xx, enddate_key) == null ? "" : this.getBillModel(tableCode).getValueAt(xx, enddate_key).toString();
				if (StringUtils.isNotEmpty(startdate) && StringUtils.isNotEmpty(enddate))
					if (MPOS_Toolkit.compareDateFromAfterTo(startdate, enddate) > 0)
						msg.append((this.getBillData().getBodyTableName(tableCode).equals("table") ? "" : this.getBillData().getBodyTableName(tableCode)) + "表体第" + (xx + 1)
								+ "行：开始日期不能大于结束日期!\n");
			}
			return msg.toString();
		}
	}

	/**
	 * 表体日期数据大小校验
	 * 
	 * @param startdate_key
	 *            开始日期billitem key
	 * @param enddate_key
	 *            结束日期 billitem key
	 * @return
	 */
	public String checkBodyDateValues(String startdate_key, String enddate_key)
	{
		return this.checkBodyDateValues(this.getCurrentBodyTableCode(), startdate_key, enddate_key);
	}
}
