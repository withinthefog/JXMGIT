package nc.ui.mallpos.pos;

import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.vo.pub.hotkey.NCKey;


/**
 * ǰ̨����������
 * 
 * @author chenjun
 * @date 2011-12-23 ����10:30:53
 */
public class MPOS_FCashierUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * ǩ��
	 */
	private ButtonObject m_btnSignIn;

	/**
	 * ����-����
	 */
	private ButtonObject m_btnExpense;

	/**
	 * �˻�
	 */
	private ButtonObject m_btnReturnGoods;

	/**
	 * ����
	 */
	private ButtonObject m_btnSettlement;

	/**
	 * ����ѯ
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
	 * ��ʼ��
	 * 
	 */
	private void initialize()
	{
		this.initButtons();
		// add(this.getBillPanel(), "Center");
		this.updateUI();

	}

	/**
	 * ��ʼ����ť
	 * 
	 */
	private void initButtons()
	{
		m_btnSignIn = new ButtonObject("ǩ��", "ǩ��(Alt+S)", "ǩ��");
		m_btnSignIn.setModifiers(NCKey.MODIFIERS_ALT);
		m_btnSignIn.setHotKey("S");

		m_btnExpense = new ButtonObject("����", "����(Alt+E)", "����");
		m_btnExpense.setModifiers(NCKey.MODIFIERS_ALT);
		m_btnExpense.setHotKey("E");

		m_btnReturnGoods = new ButtonObject("�˻�", "�˻�(Alt+R)", "�˻�");
		m_btnReturnGoods.setModifiers(NCKey.MODIFIERS_ALT);
		m_btnReturnGoods.setHotKey("R");

		m_btnSettlement = new ButtonObject("����", "����(Alt+W)", "����");
		m_btnSettlement.setModifiers(NCKey.MODIFIERS_ALT);
		m_btnSettlement.setHotKey("W");

		m_btnBalanceQuery = new ButtonObject("����ѯ", "����ѯ(Alt+Q)", "����ѯ");
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
	 * ����
	 * 
	 */
	private void doSettlement()
	{
		if (MessageDialog.showYesNoDlg(this, "ѯ��", "�Ƿ����Ҫ�����㣿") == UIDialog.ID_YES)
		{
			try
			{
				Object reValue = MessageDialog.showSelectDlg(this, "����ѡ��", "��ѡ��Ҫ�����POS���˿�", new String[]{"�˿�1","�˿�2"}, 2);//���һ�������������������
				if(reValue!=null)
				{
					if (MPOS_POSCall.settlement(Integer.parseInt(reValue.toString().substring(2))))
					{
						this.showHintMessage("����ɹ�");
					} 
				}
			} catch (Exception e)
			{
				e.printStackTrace();
				MessageDialog.showErrorDlg(this, "����", e.getMessage());
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
	 * ����-����
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
	 * ����ѯ
	 * 
	 */
	private void doBalanceQuery()
	{
		try
		{
			Object reValue = MessageDialog.showSelectDlg(this, "��ѯ���ѡ��", "��ѡ��Ҫ��ѯ����POS���˿�", new String[]{"�˿�1","�˿�2"}, 2);//���һ�������������������
			if(reValue!=null)
			{
				if (MPOS_POSCall.balanceQuery(Integer.parseInt(reValue.toString().substring(2))))
				{
					this.showHintMessage("��ѯ���ɹ�");
				} 
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "����", e.getMessage());
		} 
	}
	
	/**
	 * ǩ��
	 * 
	 */
	private void doSignIn()
	{
		try
		{
			Object reValue = MessageDialog.showSelectDlg(this, "ǩ��ѡ��", "��ѡ��Ҫǩ����POS���˿�", new String[]{"�˿�1","�˿�2"}, 2);//���һ�������������������
			if(reValue!=null)
			{
				if (MPOS_POSCall.signin(Integer.parseInt(reValue.toString().substring(2))))
				{
					this.showHintMessage("ǩ���ɹ�");
				} 
			}
		} catch (Exception e)
		{
			MessageDialog.showErrorDlg(this, "����", e.getMessage());
		} 
	}
}
