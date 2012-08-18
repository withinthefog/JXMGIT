package nc.ui.mallpos.log;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nc.ui.mallpos.pub.MPOS_BtnPanel;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.vo.bd.ref.RefNodeNameConst;
import nc.vo.mallpos.tools.MPOS_Toolkit;

import org.apache.commons.lang.StringUtils;

/**
 * ��־��ѯ���
 * 
 * @author chenjun
 * @date 2011-11-25 ����01:18:52
 */
public class MPOS_LogQueryDlg extends UIDialog implements ActionListener
{

	private static final long serialVersionUID = 1L;

	/**
	 * ��ѯ�������
	 */
	private UIPanel queryPanel;

	/**
	 * ��ť���
	 */
	private MPOS_BtnPanel btnPanel;

	/**
	 * �ͻ��˲���
	 */
	public ClientEnvironment ce = ClientEnvironment.getInstance();

	private String log_type;

	private String log_date_start;

	private String log_date_end;

	public UIRefPane dateFromRef;

	public UILabel dateToLab;

	public UILabel dateFromLab;

	public UIRefPane dateToRef;

	public UILabel logTypeLab;

	public UIComboBox logTypeCbx;

	/**
	 * ���캯��
	 * 
	 * @param p_contain
	 *            ��������
	 */
	public MPOS_LogQueryDlg(Container p_contain)
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
		setBounds(0, 0, 332, 200);
		this.add(this.getQueryPanel(), "Center");
		this.add(this.getBtnPanel(), "South");
	}

	public UIPanel getQueryPanel()
	{
		if (queryPanel == null)
		{
			queryPanel = new UIPanel();
			queryPanel.setLayout(null);
			queryPanel.setPreferredSize(new Dimension(720, 30));
			queryPanel.add(getLogTypeLab());
			queryPanel.add(getLogTypeCbx());
			queryPanel.add(getDateFromLab());
			queryPanel.add(getDateFromRef());
			queryPanel.add(getDateToLab());
			queryPanel.add(getDateToRef());
		}
		return queryPanel;
	}

	public UILabel getLogTypeLab()
	{
		if (logTypeLab == null)
		{
			logTypeLab = new UILabel();
			logTypeLab.setName("suppLab");
			logTypeLab.setText("��־����");
			logTypeLab.setBounds(20, 10, 60, 20);
		}
		return logTypeLab;
	}

	public UIComboBox getLogTypeCbx()
	{
		if (logTypeCbx == null)
		{
			logTypeCbx = new UIComboBox();
			logTypeCbx.setName("logTypeCbx");
			logTypeCbx.setBounds(75, 10, 230, 20);
			String[] logTypeItems = { "���ɸ��","�����̻�ÿ�����ۻ���"};
			logTypeCbx.addItems(logTypeItems);
		}
		return logTypeCbx;
	}

	public UILabel getDateFromLab()
	{
		if (dateFromLab == null)
		{
			dateFromLab = new UILabel();
			dateFromLab.setName("dateFromLab");
			dateFromLab.setText("��־����");
			dateFromLab.setBounds(20, 35, 60, 20);
		}
		return dateFromLab;
	}

	public UIRefPane getDateFromRef()
	{
		if (dateFromRef == null)
		{
			dateFromRef = new UIRefPane();
			dateFromRef.setName("dateFromRef");
			dateFromRef.setRefNodeName(RefNodeNameConst.CANLENDAR);
			dateFromRef.setBounds(75, 35, 100, 20);
			dateFromRef.setText(ce.getDate().toString());
		}
		return dateFromRef;
	}

	public UILabel getDateToLab()
	{
		if (dateToLab == null)
		{
			dateToLab = new UILabel();
			dateToLab.setName("dateToLab");
			dateToLab.setText("��");
			dateToLab.setBounds(185, 35, 20, 20);
		}
		return dateToLab;
	}

	public UIRefPane getDateToRef()
	{
		if (dateToRef == null)
		{
			dateToRef = new UIRefPane();
			dateToRef.setName("dateToRef");
			dateToRef.setRefNodeName(RefNodeNameConst.CANLENDAR);
			dateToRef.setBounds(205, 35, 100, 20);
			dateToRef.setText(ce.getDate().toString());
		}
		return dateToRef;
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
				e.printStackTrace();
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
		this.log_type = String.valueOf(this.getLogTypeCbx().getSelectedIndex());
		this.log_date_start = this.getDateFromRef().getRefName();
		this.log_date_end = this.getDateToRef().getRefName();
		if (StringUtils.isNotEmpty(this.log_date_start) && StringUtils.isNotEmpty(this.log_date_end))
		{
			if (MPOS_Toolkit.compareDateFromAfterTo(this.log_date_start, this.log_date_end) > 0)
			{
				MessageDialog.showErrorDlg(this, "����", "��Ч�ڿ�ʼʱ�������Ч�ڽ���ʱ�䣬����������!");
				return;
			}
		}
		closeOK();
	}

	/**
	 * �õ���ѯ����
	 * 
	 * @return ��ѯ����
	 */
	public String getWhereCondition()
	{
		StringBuffer sqlWhere = new StringBuffer("");
		if (StringUtils.isNotEmpty(log_type))
		{
			sqlWhere.append(" and log_type='" + log_type + "'");
		}
		if (StringUtils.isNotEmpty(log_date_start))
		{
			sqlWhere.append(" and log_date>='" + log_date_start + "'");
		}
		if (StringUtils.isNotEmpty(log_date_end))
		{
			sqlWhere.append(" and log_date<='" + log_date_end + "'");
		}
		return sqlWhere.toString();
	}
}
