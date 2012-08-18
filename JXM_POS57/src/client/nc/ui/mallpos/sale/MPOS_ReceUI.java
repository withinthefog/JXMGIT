package nc.ui.mallpos.sale;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.vo.mallpos.sale.MPOS_ReceivablesVO;

/**
 * Ӧ�տ���
 * 
 * @author chenjun
 * @date 2011-11-22 ����03:01:45
 */
public class MPOS_ReceUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * �б����
	 */
	private MPOS_ReceBillPanel billPanel;

	/**
	 * ��ѯ
	 */
	private ButtonObject m_btnQuery;

	/**
	 * ˢ��
	 */
	private ButtonObject m_btnRefresh;

	private ButtonObject MainButtonGroup[];

	private MPOS_ReceQueryDlg queryDlg;

	/**
	 * ��ѯ����
	 * 
	 */
	private String sqlWhere;

	@Override
	public String getTitle()
	{
		return "Ӧ���̻�����";
	}

	public MPOS_ReceUI()
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
	 * ��ѯ
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
	 * ˢ��
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
	public MPOS_ReceBillPanel getBillPanel()
	{
		if (billPanel == null)
		{
			billPanel = new MPOS_ReceBillPanel();
		}
		return billPanel;
	}

}
