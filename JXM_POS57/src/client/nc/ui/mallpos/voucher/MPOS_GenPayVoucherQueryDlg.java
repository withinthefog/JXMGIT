package nc.ui.mallpos.voucher;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.mallpos.pub.MPOS_BtnPanel;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.vo.bd.ref.RefNodeNameConst;
import nc.vo.pub.lang.UFDate;

import org.apache.commons.lang.StringUtils;

/**
 * 查询付款结算单生成付款凭证
 * 
 * @author chenjun
 * @date 2011-12-21 下午02:52:27
 */
public class MPOS_GenPayVoucherQueryDlg extends UIDialog implements ActionListener
{

	private static final long serialVersionUID = 1L;

	/**
	 * 查询条件面板
	 */
	private UIPanel queryPanel;

	/**
	 * 按钮面板
	 */
	private MPOS_BtnPanel btnPanel;

	/**
	 * 客户端参数
	 */
	public ClientEnvironment ce = ClientEnvironment.getInstance();

	public String custcode;

	public String billstatus;

	public String billdate;

	public String voucher;

	public UIRefPane dateFromRef;

	public UILabel dateFromLab;

	public UIRefPane dateToRef;

	public UILabel billstatusLab;

	public UIComboBox billstatusCbx;

	public UILabel lblCust;

	private UIRefPane refCust;

	public UILabel voucherLab;

	public UIComboBox voucherCbx;

	/**
	 * 构造函数
	 * 
	 * @param p_contain
	 *            所属容器
	 */
	public MPOS_GenPayVoucherQueryDlg(Container p_contain)
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
		setBounds(0, 0, 300, 200);
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
			queryPanel.add(getLblCust());
			queryPanel.add(getRefCust());
			queryPanel.add(getDateFromLab());
			queryPanel.add(getDateFromRef());
			queryPanel.add(getVoucherLab());
			queryPanel.add(getVoucherCbx());
		}
		return queryPanel;
	}

	/**
	 * @return the lblCust
	 */
	public UILabel getLblCust()
	{
		if (lblCust == null)
		{
			lblCust = new UILabel();
			lblCust.setName("suppLab");
			lblCust.setText("商户编码");
			lblCust.setBounds(20, 10, 60, 20);
		}
		return lblCust;
	}

	/**
	 * @return the refCust
	 */
	public UIRefPane getRefCust()
	{
		if (refCust == null)
		{
			refCust = new UIRefPane();
			refCust.setName("refCust");
			refCust.setRefNodeName(RefNodeNameConst.CUSTBASDOC);
			refCust.setBounds(75, 10, 200, 20);
		}
		return refCust;
	}

	public UILabel getDateFromLab()
	{
		if (dateFromLab == null)
		{
			dateFromLab = new UILabel();
			dateFromLab.setName("dateFromLab");
			dateFromLab.setText("付款日期");
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
			MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class);
			UFDate date = null;
			try
			{
				date = iquery.queryPrePayDate();
			} catch (Exception e)
			{
				e.printStackTrace();
				date = ce.getDate();
			}
			dateFromRef.setValue(date.toString());
			dateFromRef.setRefNodeName(RefNodeNameConst.CANLENDAR);
			dateFromRef.setBounds(75, 35, 200, 20);
		}
		return dateFromRef;
	}

	/**
	 * @return the voucherLab
	 */
	public UILabel getVoucherLab()
	{
		if (voucherLab == null)
		{
			voucherLab = new UILabel();
			voucherLab.setName("voucherLab");
			voucherLab.setText("凭证状态");
			voucherLab.setBounds(20, 60, 60, 20);
		}
		return voucherLab;
	}

	/**
	 * @return the voucherCbx
	 */
	public UIComboBox getVoucherCbx()
	{
		if (voucherCbx == null)
		{
			voucherCbx = new UIComboBox();
			voucherCbx.setName("voucherCbx");
			voucherCbx.setBounds(75, 60, 200, 20);
			String[] voucherItems = { "", "未生成", "已生成", };
			voucherCbx.addItems(voucherItems);
		}
		return voucherCbx;
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
		this.voucher = String.valueOf(this.getVoucherCbx().getSelectedIndex());
		this.billdate = this.getDateFromRef().getRefName();
		this.custcode = this.getRefCust().getRefCode();
		closeOK();
	}

	/**
	 * 得到查询条件
	 * 
	 * @return 查询条件
	 */
	public String getWhereCondition()
	{
		StringBuffer sqlWhere = new StringBuffer("");
		sqlWhere.append("  and b.zzzt = 1 ");
		if (StringUtils.isNotEmpty(this.custcode))
		{
			sqlWhere.append(" and c.custcode = '" + this.custcode + "'");
		}
		if (StringUtils.isNotEmpty(this.billdate))
		{
			sqlWhere.append(" and b.djrq = '" + this.billdate + "'");
		}
		if (this.voucher.equals("2"))
		{
			sqlWhere.append(" and b.zyx2 = 'Y'");
		}if (this.voucher.equals("1"))
		{
			sqlWhere.append(" and b.zyx2 != 'Y'");
		}
		return sqlWhere.toString();
	}

}
