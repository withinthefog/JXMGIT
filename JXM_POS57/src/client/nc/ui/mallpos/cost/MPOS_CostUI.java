package nc.ui.mallpos.cost;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.mallpos.cost.MPOS_CostVO;

public class MPOS_CostUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * 列表面板
	 */
	private MPOS_CostBillPanel billPanel;

	/**
	 * 查询
	 */
	private ButtonObject m_btnQuery;

	/**
	 * 刷新
	 */
	private ButtonObject m_btnRefresh;

	/**
	 * 生成凭证
	 */
	private ButtonObject m_btnGenVoucher;

	private ButtonObject MainButtonGroup[];

	/**
	 * 查询条件
	 * 
	 */
	private String sqlWhere;


	@Override
	public String getTitle()
	{
		return "商户费用";
	}

	public MPOS_CostUI()
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
		this.doRefresh();
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
		m_btnGenVoucher = new ButtonObject("生成凭证", "生成凭证", "生成凭证");

		MainButtonGroup = (new ButtonObject[] { m_btnQuery, m_btnRefresh, m_btnGenVoucher });
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
		} else if (arg0 == this.m_btnGenVoucher)
		{
			this.doCreateCMPBill();
		}
	}

	private void doCreateCMPBill()
	{

	}

	/**
	 * 查询
	 * 
	 */
	private void doQuery()
	{

	}

	/**
	 * 刷新
	 * 
	 */
	private void doRefresh()
	{

		MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class.getName());
		try
		{
			MPOS_CostVO[] vos = iquery.queryCost(this.sqlWhere);
			this.getBillPanel().fillGrid(vos);
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
	public void setBtnStatus(int status)
	{
		updateButtons();
		updateUI();
	}

	/**
	 * @return the billPanel
	 */
	public MPOS_CostBillPanel getBillPanel()
	{
		if (billPanel == null)
		{
			billPanel = new MPOS_CostBillPanel();
		}
		return billPanel;
	}

}
