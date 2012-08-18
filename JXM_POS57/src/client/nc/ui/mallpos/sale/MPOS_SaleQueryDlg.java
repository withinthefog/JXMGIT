package nc.ui.mallpos.sale;

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
 * 商户销售天汇总查询面板
 * 
 * @author chenjun
 * @date 2011-11-25 下午01:18:52
 */
public class MPOS_SaleQueryDlg extends UIDialog implements ActionListener
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

	public String pk_cubasdoc;

	public String billstatus;

	public int paystatus;

	public String billdate_start;

	public String billdate_end;

	public String voucher;

	public UIRefPane dateFromRef;

	public UILabel dateToLab;

	public UILabel dateFromLab;

	public UIRefPane dateToRef;

	public UILabel billstatusLab;

	public UIComboBox billstatusCbx;

	public UILabel lblCust;

	private UIRefPane refCust;

	public UILabel voucherLab;

	public UIComboBox voucherCbx;

	public UILabel paystatusLab;

	public UIComboBox paystatusCbx;

	/**
	 * 构造函数
	 * 
	 * @param p_contain
	 *            所属容器
	 */
	public MPOS_SaleQueryDlg(Container p_contain)
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
		setBounds(0, 0, 332, 220);
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
			queryPanel.add(getDateToLab());
			queryPanel.add(getDateToRef());

			queryPanel.add(getBillstatusLab());
			queryPanel.add(getBillstatusCbx());

			queryPanel.add(getVoucherLab());
			queryPanel.add(getVoucherCbx());

			queryPanel.add(getPaystatusLab());
			queryPanel.add(getPaystatusCbx());
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
			refCust.setBounds(75, 10, 230, 20);
		}
		return refCust;
	}

	public UILabel getDateFromLab()
	{
		if (dateFromLab == null)
		{
			dateFromLab = new UILabel();
			dateFromLab.setName("dateFromLab");
			dateFromLab.setText("单据日期");
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
			dateFromRef.setValue(ce.getDate().toString());
			dateFromRef.setRefNodeName(RefNodeNameConst.CANLENDAR);
			dateFromRef.setBounds(75, 35, 100, 20);
		}
		return dateFromRef;
	}

	public UILabel getDateToLab()
	{
		if (dateToLab == null)
		{
			dateToLab = new UILabel();
			dateToLab.setName("dateToLab");
			dateToLab.setText("至");
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
			dateToRef.setValue(ce.getDate().toString());
			dateToRef.setRefNodeName(RefNodeNameConst.CANLENDAR);
			dateToRef.setBounds(205, 35, 100, 20);
		}
		return dateToRef;
	}

	public UILabel getBillstatusLab()
	{
		if (billstatusLab == null)
		{
			billstatusLab = new UILabel();
			billstatusLab.setName("billstatusLab");
			billstatusLab.setText("单据状态");
			billstatusLab.setBounds(20, 60, 60, 20);
		}
		return billstatusLab;
	}

	public UIComboBox getBillstatusCbx()
	{
		if (billstatusCbx == null)
		{
			billstatusCbx = new UIComboBox();
			billstatusCbx.setName("billstatusCbx");
			billstatusCbx.setBounds(75, 60, 230, 20);
			String[] logTypeItems = { "", "未生成付款单", "已生成付款单", };
			billstatusCbx.addItems(logTypeItems);
		}
		return billstatusCbx;
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
			voucherLab.setBounds(20, 85, 60, 20);
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
			voucherCbx.setBounds(75, 85, 230, 20);
			String[] voucherItems = { "", "未生成", "已生成", };
			voucherCbx.addItems(voucherItems);
		}
		return voucherCbx;
	}

	/**
	 * 获取paystatusCbx
	 * 
	 * @return paystatusCbx paystatusCbx
	 */
	public UIComboBox getPaystatusCbx()
	{
		if (paystatusCbx == null)
		{
			paystatusCbx = new UIComboBox();
			paystatusCbx.setName("paystatusCbx");
			paystatusCbx.setBounds(75, 110, 230, 20);
			String[] voucherItems = { "", "未付款", "已付款", };
			paystatusCbx.addItems(voucherItems);
		}
		return paystatusCbx;
	}

	/**
	 * 获取paystatusLab
	 * 
	 * @return paystatusLab paystatusLab
	 */
	public UILabel getPaystatusLab()
	{
		if (paystatusLab == null)
		{
			paystatusLab = new UILabel();
			paystatusLab.setName("paystatusLab");
			paystatusLab.setText("付款状态");
			paystatusLab.setBounds(20, 110, 60, 20);
		}
		return paystatusLab;
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
		this.billstatus = this.getBillstatusCbx().getSelectedIndex() == 0 ? "" : String.valueOf(this.getBillstatusCbx().getSelectedIndex() - 1);
		this.voucher = this.getVoucherCbx().getSelectedIndex() == 0 ? "" : String.valueOf(this.getVoucherCbx().getSelectedIndex() - 1);
		this.billdate_start = this.getDateFromRef().getRefName();
		this.billdate_end = this.getDateToRef().getRefName();
		this.pk_cubasdoc = this.getRefCust().getRefPK();

		if (StringUtils.isNotEmpty(this.billdate_start) && StringUtils.isNotEmpty(this.billdate_end))
		{
			if (MPOS_Toolkit.compareDateFromAfterTo(this.billdate_start, this.billdate_end) > 0)
			{
				MessageDialog.showErrorDlg(this, "错误", "有效期开始时间大于有效期结束时间，请重新输入!");
				return;
			}
		}
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
		if (StringUtils.isNotEmpty(this.pk_cubasdoc))
		{
			sqlWhere.append(" and mpos_sale_daysum.pk_cubasdoc = '" + this.pk_cubasdoc + "'");
		}
		if (StringUtils.isNotEmpty(this.billdate_start))
		{
			sqlWhere.append(" and mpos_sale_daysum.billdate >= '" + this.billdate_start + "'");
		}
		if (StringUtils.isNotEmpty(this.billdate_end))
		{
			sqlWhere.append(" and mpos_sale_daysum.billdate <= '" + this.billdate_end + "'");
		}
		if (StringUtils.isNotEmpty(this.billstatus))
		{
			sqlWhere.append(" and mpos_sale_daysum.billstate = '" + this.billstatus + "'");
		}
		if (StringUtils.isNotEmpty(this.voucher))
		{
			sqlWhere.append(" and mpos_sale_daysum.voucher = '" + this.voucher + "'");
		}
		if (this.getPaystatusCbx().getSelectedIndex() != 0)
		{
			if (this.getPaystatusCbx().getSelectedIndex() == 1)
			{
				sqlWhere.append("  and (mpos_sale_daysum.billstate = '0' or cmp_busibill.zzzt !=1)");
			} else if (this.getPaystatusCbx().getSelectedIndex() == 2)
			{
				sqlWhere.append("  and cmp_busibill.zzzt =1");
			}
		}
		return sqlWhere.toString();
	}

}
