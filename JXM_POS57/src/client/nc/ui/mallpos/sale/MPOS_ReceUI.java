package nc.ui.mallpos.sale;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.vo.mallpos.sale.MPOS_ReceivablesVO;

/**
 * 应收款项
 * 
 * @author chenjun
 * @date 2011-11-22 下午03:01:45
 */
public class MPOS_ReceUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * 列表面板
	 */
	private MPOS_ReceBillPanel billPanel;

	/**
	 * 查询
	 */
	private ButtonObject m_btnQuery;

	/**
	 * 刷新
	 */
	private ButtonObject m_btnRefresh;

	private ButtonObject MainButtonGroup[];

	private MPOS_ReceQueryDlg queryDlg;

	/**
	 * 查询条件
	 * 
	 */
	private String sqlWhere;

	@Override
	public String getTitle()
	{
		return "应收商户款项";
	}

	public MPOS_ReceUI()
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

		MainButtonGroup = (new ButtonObject[] { m_btnQuery, m_btnRefresh });
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
			queryDlg = new MPOS_ReceQueryDlg(this);
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
	private void doRefresh()
	{

		MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class.getName());
		try
		{
			MPOS_ReceivablesVO[] vos = iquery.queryReceByWhere(this.sqlWhere);
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
	public MPOS_ReceBillPanel getBillPanel()
	{
		if (billPanel == null)
		{
			billPanel = new MPOS_ReceBillPanel();
		}
		return billPanel;
	}

}
