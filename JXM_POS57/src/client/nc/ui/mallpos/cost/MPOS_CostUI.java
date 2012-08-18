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
	 * �б����
	 */
	private MPOS_CostBillPanel billPanel;

	/**
	 * ��ѯ
	 */
	private ButtonObject m_btnQuery;

	/**
	 * ˢ��
	 */
	private ButtonObject m_btnRefresh;

	/**
	 * ����ƾ֤
	 */
	private ButtonObject m_btnGenVoucher;

	private ButtonObject MainButtonGroup[];

	/**
	 * ��ѯ����
	 * 
	 */
	private String sqlWhere;


	@Override
	public String getTitle()
	{
		return "�̻�����";
	}

	public MPOS_CostUI()
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
		add(this.getBillPanel(), "Center");
		this.doRefresh();
		this.updateUI();
	}

	/**
	 * ��ʼ����ť
	 * 
	 */
	private void initButtons()
	{
		m_btnQuery = new ButtonObject("��ѯ", "��ѯ", "��ѯ");
		m_btnRefresh = new ButtonObject("ˢ��", "ˢ��", "ˢ��");
		m_btnGenVoucher = new ButtonObject("����ƾ֤", "����ƾ֤", "����ƾ֤");

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
	 * ��ѯ
	 * 
	 */
	private void doQuery()
	{

	}

	/**
	 * ˢ��
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
			MessageDialog.showErrorDlg(this, "����", e.getMessage());
		}
	}

	/**
	 * ���ð�ť״̬
	 * 
	 * @param status
	 *            ״̬
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
