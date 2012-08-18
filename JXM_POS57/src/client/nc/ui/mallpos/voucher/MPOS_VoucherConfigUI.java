package nc.ui.mallpos.voucher;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubManageItf;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.mallpos.voucher.MPOS_VoucherConfigVO;

/**
 * ����ƾ֤��������
 * 
 * @author chenjun
 * @date 2011-12-18 ����11:39:27
 */
public class MPOS_VoucherConfigUI extends ToftPanel
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
	private MPOS_VoucherConfigCardPanel cardPanel;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_VoucherConfigUI()
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
		return "ƾ֤��������";
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
			MPOS_VoucherConfigVO[] vos = this.getCardPanel().getBodyValueVOs();
			for (int i = 0; i < vos.length; i++)
			{
				if (StringUtils.isEmpty(vos[i].getAccsubjcode()))
				{
					MessageDialog.showErrorDlg(this, "����", "��" + (i + 1) + "�п�Ŀ���벻��Ϊ�գ�");
					return;
				}
			}
			imanage.saveVoucherConfig(vos);
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
			MPOS_VoucherConfigVO[] vos = iquery.queryAllVouConfigVO();
			this.getCardPanel().fillGrid(vos);
			this.setBtnStatus(0, vos != null);
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
			this.getCardPanel().setEnabled(false);
		} else if (btnStatus == 1)
		{
			m_btnRefresh.setEnabled(false);
			m_btnUpdate.setEnabled(false);
			m_btnSave.setEnabled(true);
			m_btnCancel.setEnabled(true);
			this.getCardPanel().setEnabled(true);
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
	public MPOS_VoucherConfigCardPanel getCardPanel()
	{
		if (cardPanel == null)
		{
			cardPanel = new MPOS_VoucherConfigCardPanel();
		}

		return cardPanel;
	}
}
