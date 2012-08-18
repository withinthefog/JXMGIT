package nc.ui.mallpos.sale;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubManageItf;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.dev.pub.tools.PrintTools;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.vo.mallpos.sale.MPOS_SaleDaySumVO;
import nc.vo.pub.lang.UFDate;

/**
 * 销售记录天汇总主界面
 * 
 * @author cj
 * @date 2012-8-17 下午10:44:13
 */
public class MPOS_SaleDaySumUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * 列表面板
	 */
	private MPOS_SaleDaySumBillPanel billPanel;

	/**
	 * 查询
	 */
	private ButtonObject m_btnQuery;

	/**
	 * 刷新
	 */
	private ButtonObject m_btnRefresh;

	/**
	 * 生成付款单
	 */
	private ButtonObject m_btnCreateCMPBill;

	/**
	 * 生成代收商户货款凭证
	 */
	private ButtonObject m_btnCreateDSVoucher;
	
	/*
	 * 导出
	 */
	private ButtonObject m_btnExport;

	private ButtonObject MainButtonGroup[];

	public ClientEnvironment ce = ClientEnvironment.getInstance();

	/**
	 * 查询条件
	 * 
	 */
	private String sqlWhere;

	private MPOS_SaleQueryDlg queryDlg;

	@Override
	public String getTitle()
	{
		return "商户销售收入天汇总";
	}

	public MPOS_SaleDaySumUI()
	{
		super();
		initialize();
	}

	/**
	 * 初始化
	 * 
	 */
	private void initialize()
	{
		this.initButtons();
		add(this.getBillPanel(), "Center");
		// this.doRefresh();
		this.setBtnStatus(0, false);
		this.updateUI();
		
	}

	/**
	 * 初始化按钮
	 * 
	 */
	private void initButtons()
	{
		m_btnQuery = new ButtonObject("查询", "查询", "查询");
		m_btnRefresh = new ButtonObject("刷新", "刷新", "刷新");
		m_btnCreateCMPBill = new ButtonObject("生成付款单", "生成付款单", "生成付款单");
		m_btnCreateDSVoucher = new ButtonObject("生成代收货款凭证", "生成代收货款凭证", "生成代收货款凭证");
		m_btnExport = new ButtonObject("导出","导出","导出");
		m_btnCreateDSVoucher.setVisible(false);
		MainButtonGroup = (new ButtonObject[] { m_btnQuery, m_btnRefresh, m_btnCreateCMPBill, m_btnCreateDSVoucher,m_btnExport });
		this.setButtons(MainButtonGroup);
	}

	@Override
	public void onButtonClicked(ButtonObject arg0)
	{
		if (arg0 == this.m_btnRefresh)
		{
			this.doRefresh();
		} else if (arg0 == this.m_btnQuery)
		{
			this.doQuery();
		} else if (arg0 == this.m_btnCreateCMPBill)
		{
			this.doCreateCMPBill();
		} else if (arg0 == this.m_btnCreateDSVoucher)
		{
			this.doCreateDSVoucher();
		}else if (arg0 == this.m_btnExport)
		{
			this.doExport();
		}
	}

	private void doExport()
	{
		PrintTools print = new PrintTools(getBillPanel(), this.getTitle());
		print.onExport();
	}

	/**
	 * 生成代收商户货款凭证
	 * 
	 */
	public void doCreateDSVoucher()
	{

	}

	/**
	 * 生成付款结算单
	 * 
	 */
	private void doCreateCMPBill()
	{
		try
		{
			MPOS_GenCMPQueryDlg cmpQDlg = new MPOS_GenCMPQueryDlg(this);
			if (cmpQDlg.showModal() == UIDialog.ID_OK)
			{
				MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class.getName());
				MPOS_SaleDaySumVO[] vos = iquery.querySale(ce.getCorporation().getPk_corp(),cmpQDlg.getWhereCondition());
				MPOS_GenCMPDlg genDlg = new MPOS_GenCMPDlg(this);
				genDlg.getBillPanel().fillGrid(vos);
				if (vos == null || vos.length <= 0)
				{
					genDlg.getBtnPanel().getButtonOK().setEnabled(false);
				}
				if (genDlg.showModal() == UIDialog.ID_OK)
				{
					MPOS_PubManageItf imanage = (MPOS_PubManageItf) NCLocator.getInstance().lookup(MPOS_PubManageItf.class.getName());
					this.showHintMessage("正在生成付款单....");
					imanage.createCMPBill(ce.getUser().getPrimaryKey(), vos, ce.getCorporation().getPk_corp(), new UFDate(cmpQDlg.prepaydate));
					this.showHintMessage("生成付款单完成");
					this.doRefresh();
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		}
	}

	/**
	 * 查询
	 * 
	 */
	private void doQuery()
	{
		if (queryDlg == null)
		{
			queryDlg = new MPOS_SaleQueryDlg(this);
		}
		if (queryDlg.showModal() == UIDialog.ID_OK)
		{
			this.sqlWhere = queryDlg.getWhereCondition();
			this.doRefresh();
		}
	}

	/**
	 * 刷新
	 * 
	 */
	public void doRefresh()
	{

		MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class.getName());
		try
		{
			if (StringUtils.isEmpty(this.sqlWhere))
			{
				this.sqlWhere = " and mpos_sale_daysum.billdate = '" + ce.getDate().toString() + "'";
			}
			MPOS_SaleDaySumVO[] vos = iquery.querySale(ce.getCorporation().getPk_corp(),this.sqlWhere);
			this.getBillPanel().fillGrid(vos);
			setBtnStatus(0, (vos != null&&vos.length>0));
			this.showHintMessage("刷新完成！");
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		}
	}

	/**
	 * 设置按钮状态
	 * 
	 * @param status
	 *            状态
	 */
	public void setBtnStatus(int status, boolean hasData)
	{
		this.m_btnCreateDSVoucher.setEnabled(hasData);
		this.m_btnExport.setEnabled(hasData);
		updateButtons();
	}

	/**
	 * @return the billPanel
	 */
	public MPOS_SaleDaySumBillPanel getBillPanel()
	{
		if (billPanel == null)
		{
			billPanel = new MPOS_SaleDaySumBillPanel();
		}
		return billPanel;
	}

}
