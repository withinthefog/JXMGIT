package nc.ui.mallpos.config.payarg;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubManageItf;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.mallpos.pub.MPOS_ArgConfigVO;

/**
 * �����������
 * @author chenjun
 * @date 2011-12-18 ����11:38:00
 */
public class MPOS_ArgConfigUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * ˢ��
	 */
	private ButtonObject m_btnRefresh;

	/**
	 * �޸�
	 */
	private ButtonObject m_btnUpdate;

	/**
	 * ����
	 */
	private ButtonObject m_btnSave;

	/**
	 * ȡ��
	 */
	private ButtonObject m_btnCancel;

	private ButtonObject MainButtonGroup[];

	/**
	 * ��Ƭ���
	 */
	private MPOS_ArgConfigCardPanel cardPanel;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_ArgConfigUI()
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
		this.add(this.getCardPanel(), "Center");
		this.updateUI();
		this.doRefresh();
	}

	/**
	 * ��ʼ����ť
	 * 
	 */
	private void initButtons()
	{
		m_btnRefresh = new ButtonObject("ˢ��", "ˢ��", "ˢ��");
		m_btnUpdate = new ButtonObject("�޸�", "�޸�", "�޸�");
		m_btnSave = new ButtonObject("����", "����", "����");
		m_btnCancel = new ButtonObject("ȡ��", "ȡ��", "ȡ��");
		MainButtonGroup = (new ButtonObject[] { m_btnRefresh, m_btnUpdate, m_btnSave, m_btnCancel });
		this.setButtons(MainButtonGroup);
		updateButtons();
	}

	@Override
	public String getTitle()
	{
		return "�����������";
	}

	@Override
	public void onButtonClicked(ButtonObject arg0)
	{
		if (arg0 == this.m_btnCancel)
		{
			this.doCancel();
		} else if (arg0 == this.m_btnUpdate)
		{
			this.doUpdate();
		} else if (arg0 == this.m_btnRefresh)
		{
			this.doRefresh();
		} else if (arg0 == this.m_btnSave)
		{
			this.doSave();
		}
	}

	/**
	 * ����
	 * 
	 */
	private void doSave()
	{
		try
		{
			MPOS_PubManageItf imanage = (MPOS_PubManageItf) NCLocator.getInstance().lookup(MPOS_PubManageItf.class.getName());
			MPOS_ArgConfigVO vo = this.getCardPanel().getHeadVO();
			if (vo == null)
				return;
			if (vo.getPay_cycle() == 0)
			{
				MessageDialog.showWarningDlg(this, "����", "�������ڲ���Ϊ�գ�");
				return;
			}
			if (vo.getFirst_paydate() == null)
			{
				MessageDialog.showWarningDlg(this, "����", "�״θ������ڲ���Ϊ�գ�");
				return;
			}
			if (StringUtils.isEmpty(vo.getPk_userid()))
			{
				MessageDialog.showWarningDlg(this, "����", "Ĭ���Ƶ��˲���Ϊ�գ�");
				return;
			}
			imanage.saveArgConfig(vo);
			this.doRefresh();
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "����", e.getMessage());
		}
	}

	/**
	 * ˢ��
	 * 
	 */
	private void doRefresh()
	{
		try
		{
			MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class.getName());
			MPOS_ArgConfigVO vo = iquery.queryFirst();
			MPOS_ArgConfigVO[] vos = iquery.queryAllPrePayDate();
			this.getCardPanel().setHeadVO(vo);
			this.getCardPanel().fillGrid(vos);
			this.setBtnStatus(0, vo != null);
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "����", e.getMessage());
		}

	}

	/**
	 * ���ð�ť״̬
	 * 
	 * @param btnStatus
	 *            0:Ĭ�ϣ�1���޸� ״̬<br>
	 * @param hasData
	 *            �Ƿ�������
	 */
	public void setBtnStatus(int btnStatus, boolean hasData)
	{
		m_btnRefresh.setEnabled(hasData);
		m_btnUpdate.setEnabled(true);
		if (btnStatus == 0)
		{
			m_btnSave.setEnabled(false);
			m_btnCancel.setEnabled(false);
			this.getCardPanel().getHeadItem("pay_cycle").setEdit(false);
			this.getCardPanel().getHeadItem("first_paydate").setEdit(false);
			this.getCardPanel().getHeadItem("pk_userid").setEdit(false);
		} else if (btnStatus == 1)
		{
			m_btnRefresh.setEnabled(false);
			m_btnUpdate.setEnabled(false);
			m_btnSave.setEnabled(true);
			m_btnCancel.setEnabled(true);
			this.getCardPanel().getHeadItem("pay_cycle").setEdit(true);
			this.getCardPanel().getHeadItem("first_paydate").setEdit((this.getCardPanel().getBodyValueVOs() == null || this.getCardPanel().getBodyValueVOs().length == 0));
			this.getCardPanel().getHeadItem("pk_userid").setEdit(true);
		}
		updateButtons();
	}

	/**
	 * �޸�
	 * 
	 */
	private void doUpdate()
	{
		this.setBtnStatus(1, true);

	}

	/**
	 * ȡ��
	 * 
	 */
	private void doCancel()
	{
		this.doRefresh();
	}

	/**
	 * @return the cardPanel
	 */
	public MPOS_ArgConfigCardPanel getCardPanel()
	{
		if (cardPanel == null)
		{
			cardPanel = new MPOS_ArgConfigCardPanel();
		}

		return cardPanel;
	}
}
