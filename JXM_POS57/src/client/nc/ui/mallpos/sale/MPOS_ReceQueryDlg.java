package nc.ui.mallpos.sale;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.apache.commons.lang.StringUtils;

import nc.ui.mallpos.pub.MPOS_BtnPanel;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillItem;

public class MPOS_ReceQueryDlg extends UIDialog implements ActionListener
{

	private static final long serialVersionUID = 1L;

	/**
	 * 查询条件面板
	 */
	private MPOS_ReceQueryPanel queryPanel;

	/**
	 * 按钮面板
	 */
	private MPOS_BtnPanel btnPanel;

	/**
	 * 客户端参数
	 */
	public ClientEnvironment ce = ClientEnvironment.getInstance();

	public String pk_cubasdoc;

	public String custcode;

	public String billstatus;

	public String billdate;

	/**
	 * 构造函数
	 * 
	 * @param p_contain
	 *            所属容器
	 */
	public MPOS_ReceQueryDlg(Container p_contain)
	{
		super(p_contain, "查询条件");
		this.initDlg();
	}

	/**
	 * 初始化
	 * 
	 */
	private void initDlg()
	{
		setBounds(0, 0, 300, 220);
		this.add(this.getQueryPanel(), "Center");
		this.add(this.getBtnPanel(), "South");
	}

	/**
	 * @return the queryPanel
	 */
	public MPOS_ReceQueryPanel getQueryPanel()
	{
		if (queryPanel == null)
		{
			queryPanel = new MPOS_ReceQueryPanel();
		}
		return queryPanel;
	}

	/**
	 * @return the btnPanel
	 */
	public MPOS_BtnPanel getBtnPanel()
	{
		if (btnPanel == null)
		{
			btnPanel = new MPOS_BtnPanel(this.getWidth());
			btnPanel.getButtonOK().addActionListener(this);
			btnPanel.getButtonExit().addActionListener(this);
		}
		return btnPanel;
	}

	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource() == this.getBtnPanel().getButtonOK())
		{
			try
			{
				doOK();
			} catch (Exception e)
			{
				MessageDialog.showErrorDlg(this, "错误", e.getMessage());
			}
		} else if (arg0.getSource() == this.getBtnPanel().getButtonExit())
		{
			closeCancel();
		}
	}

	/**
	 * 确定
	 * 
	 */
	private void doOK()
	{
		this.pk_cubasdoc = this.getHeadItemValue("pk_cubasdoc");
		UIRefPane ref = (UIRefPane) this.getQueryPanel().getHeadItem("pk_cubasdoc").getComponent();
		this.custcode = ref.getRefCode();
		this.billdate = this.getHeadItemValue("billdate");
		this.billstatus = this.getHeadItemValue("billstatus");
		closeOK();
	}

	/**
	 * 得到查询条件
	 * 
	 * @param tableAName
	 *            表名或表别名
	 * @return 查询条件
	 */
	public String getWhereCondition()
	{
		StringBuffer sqlWhere = new StringBuffer("");
		if (StringUtils.isNotEmpty(this.pk_cubasdoc))
		{
			sqlWhere.append(" and r.pk_cubasdoc = '" + this.pk_cubasdoc + "'");
		}
		if (StringUtils.isNotEmpty(this.billdate))
		{
			sqlWhere.append(" and r.billdate = '" + this.billdate + "'");
		}
		if (StringUtils.isNotEmpty(this.billstatus))
		{
			sqlWhere.append(" and r.billstatus = '" + this.billstatus + "'");
		}
		return sqlWhere.toString();
	}

	/**
	 * 根据表头项Key得到值 <br>
	 * 如果Item类型是BOOLEAN,则选中返加Y，未选中返回空
	 * 
	 * @param p_itemKey
	 *            表头项Key
	 * @return 表头项值
	 */
	private String getHeadItemValue(String p_itemKey)
	{
		String value = "";
		BillItem item = this.getQueryPanel().getHeadItem(p_itemKey);

		if (item.getDataType() == BillItem.UFREF)
		{
			UIRefPane ref = (UIRefPane) item.getComponent();
			value = ref.getRefPK();
		} else if (item.getDataType() == BillItem.COMBO)
		{
			UIComboBox cbx = (UIComboBox) item.getComponent();
			value = cbx.getSelectedIndex() == 0 ? "" : String.valueOf(cbx.getSelectedIndex() - 1);
		} else
		{
			Object obj = item.getValueObject();
			if (obj != null)
			{
				value = obj.toString();

			}
		}
		if (item.getDataType() == BillItem.BOOLEAN)
		{
			value = "true".equals(value) ? "Y" : "";
		}

		return value;
	}
}
