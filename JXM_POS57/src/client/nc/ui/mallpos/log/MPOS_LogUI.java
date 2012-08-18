package nc.ui.mallpos.log;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.pub.MPOS_LogVO;

public class MPOS_LogUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * 查询
	 */
	private ButtonObject m_btnQuery;

	/**
	 * 刷新
	 */
	private ButtonObject m_btnRefresh;

	private ButtonObject MainButtonGroup[];

	/**
	 * 列表面板
	 */
	private MPOS_LogBillPanel billPanel;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	/**
	 * 查询对话框
	 */
	private MPOS_LogQueryDlg queryDlg;

	/**
	 * 查询条件
	 * 
	 */
	private String sqlWhere;

	public MPOS_LogUI()
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
		this.remove(this.getBillPanel());
		add(this.getBillPanel(), "Center");
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
		updateButtons();
	}

	@Override
	public String getTitle()
	{
		return "失败日志查询";
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
			queryDlg = new MPOS_LogQueryDlg(this);
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
			if (StringUtils.isEmpty(sqlWhere))
			{
				this.sqlWhere = " and log_type='" + MPOS_GlobalVariable.LOGTYPE_PAYMANT + "' and  log_date ='" + ce.getDate() + "'";
			}
			MPOS_LogVO[] vos = iquery.queryLogByWhere(this.sqlWhere);
			this.getBillPanel().fillGrid(vos);
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		}
	}

	/**
	 * @return the billPanel
	 */
	public MPOS_LogBillPanel getBillPanel()
	{
		if (billPanel == null)
		{
			billPanel = new MPOS_LogBillPanel();
		}
		return billPanel;
	}

}
