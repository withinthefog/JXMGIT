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
	 * ��ѯ�������
	 */
	private MPOS_ReceQueryPanel queryPanel;

	/**
	 * ��ť���
	 */
	private MPOS_BtnPanel btnPanel;

	/**
	 * �ͻ��˲���
	 */
	public ClientEnvironment ce = ClientEnvironment.getInstance();

	public String pk_cubasdoc;

	public String custcode;

	public String billstatus;

	public String billdate;

	/**
	 * ���캯��
	 * 
	 * @param p_contain
	 *            ��������
	 */
	public MPOS_ReceQueryDlg(Container p_contain)
	{
		super(p_contain, "��ѯ����");
		this.initDlg();
	}

	/**
	 * ��ʼ��
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
				MessageDialog.showErrorDlg(this, "����", e.getMessage());
			}
		} else if (arg0.getSource() == this.getBtnPanel().getButtonExit())
		{
			closeCancel();
		}
	}

	/**
	 * ȷ��
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
	 * �õ���ѯ����
	 * 
	 * @param tableAName
	 *            ����������
	 * @return ��ѯ����
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
	 * ���ݱ�ͷ��Key�õ�ֵ <br>
	 * ���Item������BOOLEAN,��ѡ�з���Y��δѡ�з��ؿ�
	 * 
	 * @param p_itemKey
	 *            ��ͷ��Key
	 * @return ��ͷ��ֵ
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
