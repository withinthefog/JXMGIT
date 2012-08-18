package nc.ui.mallpos.pos;

import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.vo.pub.hotkey.NCKey;


/**
 * 前台收银主界面
 * 
 * @author chenjun
 * @date 2011-12-23 上午10:30:53
 */
public class MPOS_FCashierUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * 签到
	 */
	private ButtonObject m_btnSignIn;

	/**
	 * 消费-收银
	 */
	private ButtonObject m_btnExpense;

	/**
	 * 退货
	 */
	private ButtonObject m_btnReturnGoods;

	/**
	 * 结算
	 */
	private ButtonObject m_btnSettlement;

	/**
	 * 余额查询
	 */
	private ButtonObject m_btnBalanceQuery;

	private ButtonObject MainButtonGroup[];

	@Override
	public String getTitle()
	{
		return null;
	}

	public MPOS_FCashierUI()
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
		// add(this.getBillPanel(), "Center");
		this.updateUI();

	}

	/**
	 * 初始化按钮
	 * 
	 */
	private void initButtons()
	{
		m_btnSignIn = new ButtonObject("签到", "签到(Alt+S)", "签到");
		m_btnSignIn.setModifiers(NCKey.MODIFIERS_ALT);
		m_btnSignIn.setHotKey("S");

		m_btnExpense = new ButtonObject("收银", "收银(Alt+E)", "收银");
		m_btnExpense.setModifiers(NCKey.MODIFIERS_ALT);
		m_btnExpense.setHotKey("E");

		m_btnReturnGoods = new ButtonObject("退货", "退货(Alt+R)", "退货");
		m_btnReturnGoods.setModifiers(NCKey.MODIFIERS_ALT);
		m_btnReturnGoods.setHotKey("R");

		m_btnSettlement = new ButtonObject("结算", "结算(Alt+W)", "结算");
		m_btnSettlement.setModifiers(NCKey.MODIFIERS_ALT);
		m_btnSettlement.setHotKey("W");

		m_btnBalanceQuery = new ButtonObject("余额查询", "余额查询(Alt+Q)", "余额查询");
		m_btnBalanceQuery.setModifiers(NCKey.MODIFIERS_ALT);
		m_btnBalanceQuery.setHotKey("Q");

		MainButtonGroup = (new ButtonObject[] { m_btnSignIn, m_btnExpense, m_btnReturnGoods, m_btnSettlement, m_btnBalanceQuery });
		this.setButtons(MainButtonGroup);
	}

	@Override
	public void onButtonClicked(ButtonObject arg0)
	{
		if (arg0 == m_btnSignIn)
		{
			this.doSignIn();
		} else if (arg0 == m_btnExpense)
		{
			this.doExpense();
		} else if (arg0 == m_btnReturnGoods)
		{
			this.doReturnGoods();
		} else if (arg0 == m_btnSettlement)
		{
			this.doSettlement();
		} else if (arg0 == m_btnBalanceQuery)
		{
			this.doBalanceQuery();
		}
	}

	/**
	 * 结算
	 * 
	 */
	private void doSettlement()
	{
		if (MessageDialog.showYesNoDlg(this, "询问", "是否真的要做结算？") == UIDialog.ID_YES)
		{
			try
			{
				Object reValue = MessageDialog.showSelectDlg(this, "结算选择", "请选择要结算的POS机端口", new String[]{"端口1","端口2"}, 2);//最后一个参数是下拉框的行数
				if(reValue!=null)
				{
					if (MPOS_POSCall.settlement(Integer.parseInt(reValue.toString().substring(2))))
					{
						this.showHintMessage("结算成功");
					} 
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				MessageDialog.showErrorDlg(this, "错误", e.getMessage());
			} 
		}
	}

	private void doReturnGoods()
	{

		MPOS_ReturnGoodsDlg qDlg = new MPOS_ReturnGoodsDlg(this,getModuleCode());
		if (qDlg.showModal() == UIDialog.ID_OK)
		{
			
		}

	}

	/**
	 * 消费-收银
	 * 
	 */
	private void doExpense()
	{
		MPOS_ExpenseDlg dlg = new MPOS_ExpenseDlg(this, getModuleCode());
		if (dlg.showModal() == UIDialog.ID_OK)
		{

		}
	}

	/**
	 * 余额查询
	 * 
	 */
	private void doBalanceQuery()
	{
		try
		{
			Object reValue = MessageDialog.showSelectDlg(this, "查询余额选择", "请选择要查询余额的POS机端口", new String[]{"端口1","端口2"}, 2);//最后一个参数是下拉框的行数
			if(reValue!=null)
			{
				if (MPOS_POSCall.balanceQuery(Integer.parseInt(reValue.toString().substring(2))))
				{
					this.showHintMessage("查询余额成功");
				} 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		} 
	}
	
	/**
	 * 签到
	 * 
	 */
	private void doSignIn()
	{
		try
		{
			Object reValue = MessageDialog.showSelectDlg(this, "签到选择", "请选择要签到的POS机端口", new String[]{"端口1","端口2"}, 2);//最后一个参数是下拉框的行数
			if(reValue!=null)
			{
				if (MPOS_POSCall.signin(Integer.parseInt(reValue.toString().substring(2))))
				{
					this.showHintMessage("签到成功");
				} 
			}
		} catch (Exception e)
		{
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		} 
	}
}
