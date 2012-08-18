package nc.ui.mallpos.config.balatype;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubManageItf;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.vo.mallpos.pub.MPOS_BalaTypeConfigVO;

/**
 * ���㷽ʽ����������
 * 
 * @author cj
 * @date 2012-8-16 ����01:09:50
 */
public class MPOS_BalaTypeConfigUI extends ToftPanel {

	private static final long serialVersionUID = 1L;

	private MPOS_BalaTypeBillPanel mainPanel = null;

	// ���水ť����
	private ButtonObject m_BtnRefresh; // ˢ��

	private ButtonObject m_BtnUpdate; // �޸�

	private ButtonObject m_BtnSave; // ����

	private ButtonObject m_BtnCancel; // ȡ��

	private ButtonObject m_BtnDel; // ɾ��

	private ButtonObject m_BtnAddLin; // ����

	private ButtonObject m_BtnDelLin; // ɾ��

	private ButtonObject MainButtonGroup[];

	@Override
	public String getTitle() {
		return "���㷽ʽ����";
	}

	public MPOS_BalaTypeConfigUI() {
		super();
		initialize();
	}

	/**
	 * ��ʼ��
	 */
	public void initialize() {
		this.initButtons();
		add(getMainCardPanel());
		this.setBtnStatus(0);
		this.doRefresh();
	}

	/**
	 * ��ʼ����ť
	 */
	public void initButtons() {
		m_BtnRefresh = new ButtonObject("ˢ��", "ˢ��", "ˢ��");
		m_BtnUpdate = new ButtonObject("�޸�", "�޸�", "�޸�");
		m_BtnSave = new ButtonObject("����", "����", "����");
		m_BtnCancel = new ButtonObject("ȡ��", "ȡ��", "ȡ��");
		m_BtnDel = new ButtonObject("ɾ��", "ɾ��", "ɾ��");
		m_BtnAddLin = new ButtonObject("����", "����", "����");
		m_BtnDelLin = new ButtonObject("ɾ��", "ɾ��", "ɾ��");

		MainButtonGroup = (new ButtonObject[] { m_BtnRefresh, m_BtnUpdate,
				m_BtnSave, m_BtnCancel, m_BtnDel, m_BtnAddLin, m_BtnDelLin });
		setButtons(MainButtonGroup);
	}

	/**
	 * �õ������
	 * 
	 * @return Grade_BZPanel
	 */
	public MPOS_BalaTypeBillPanel getMainCardPanel() {
		if (this.mainPanel == null) {
			this.mainPanel = new MPOS_BalaTypeBillPanel();
		}
		return this.mainPanel;
	}

	@Override
	public void onButtonClicked(ButtonObject arg0) {
		if (arg0 == m_BtnRefresh)
			doRefresh();
		else if (arg0 == m_BtnUpdate)
			doUpdate();
		else if (arg0 == m_BtnSave)
			doSave();
		else if (arg0 == m_BtnCancel)
			doCancel();
		else if (arg0 == m_BtnAddLin)
			doAddLin();
		else if (arg0 == m_BtnDelLin)
			doDelLin();
		else if (arg0 == m_BtnDel)
			doDel();
	}

	/**
	 * ���ð�ť״̬
	 * 
	 * @param btnStatus
	 *            ״̬ 0:Ĭ��,1:�༭
	 */
	public void setBtnStatus(int btnStatus) {
		boolean hasData = this.getMainCardPanel().getRowCount() > 0;

		if (btnStatus == 0) {
			m_BtnUpdate.setEnabled(true);
			m_BtnRefresh.setEnabled(true);
			m_BtnSave.setEnabled(false);
			m_BtnCancel.setEnabled(false);
			m_BtnAddLin.setEnabled(false);
			m_BtnDelLin.setEnabled(false);
			m_BtnDel.setEnabled(hasData);
			this.getMainCardPanel().setEnabled(false);
		} else if (btnStatus == 1) {
			m_BtnUpdate.setEnabled(false);
			m_BtnRefresh.setEnabled(false);
			m_BtnSave.setEnabled(true);
			m_BtnCancel.setEnabled(true);
			m_BtnAddLin.setEnabled(true);
			m_BtnDelLin.setEnabled(true);
			m_BtnDel.setEnabled(false);
			this.getMainCardPanel().setEnabled(true);
		}
		updateButtons();
	}

	/**
	 * ˢ��
	 */
	public void doRefresh() {
		MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance()
				.lookup(MPOS_PubQueryItf.class);
		try {
			MPOS_BalaTypeConfigVO[] vos = iquery.queryAllBalaTypeConfigVO();
			this.getMainCardPanel().fillGrid(vos);
			this.setBtnStatus(0);
		} catch (Exception e) {
			MessageDialog.showErrorDlg(this, "����", e.getMessage());
		}
	}

	/**
	 * �޸�����
	 */
	public void doUpdate() {
		this.setBtnStatus(1);
	}

	/**
	 * ��������
	 */
	public void doSave() {
		MPOS_BalaTypeConfigVO[] b_vos = this.getMainCardPanel()
				.getBodyValueChangeVOs();
		if (b_vos == null || b_vos.length == 0) {
			return;
		}
		MPOS_PubManageItf imanage = (MPOS_PubManageItf) NCLocator.getInstance()
				.lookup(MPOS_PubManageItf.class);
		try {
			imanage.saveBalaTypeConfig(b_vos);
			this.doRefresh();
			this.showHintMessage("����ɹ�!");
		} catch (Exception e) {
			MessageDialog.showErrorDlg(this, "����", e.getMessage());
		}
	}

	/**
	 * ȡ���޸�
	 */
	public void doCancel() {
		this.doRefresh();
	}

	/**
	 * �����
	 */
	public void doAddLin() {
		this.getMainCardPanel().addLine();
		this.getMainCardPanel().setBodyValueAt(0,
				this.getMainCardPanel().getRowCount() - 1, "charging_type");
		this.setBtnStatus(1);
	}

	/**
	 * ɾ����
	 */
	public void doDelLin() {
		MPOS_BalaTypeConfigVO[] b_vos = this.getMainCardPanel()
				.getBodySelectVOsByMouse();
		if (b_vos == null || b_vos.length <= 0) {
			MessageDialog.showWarningDlg(this, "����", "��ѡ����Ҫɾ�����У�");
			return;
		}
		if (StringUtils.isNotEmpty(b_vos[0].getPk_balatype_config())) {
			MessageDialog.showWarningDlg(this, "����", "ɾ���д������ݣ�����ɾ����");
			return;
		}
		this.getMainCardPanel().delLine();
		if (this.getMainCardPanel().getRowCount() == 0) {
			this.setBtnStatus(0);
		}
	}

	/**
	 * ɾ������
	 */
	public void doDel() {
		MPOS_BalaTypeConfigVO[] b_vos = this.getMainCardPanel()
				.getBodySelectVOsByMouse();
		if (b_vos == null || b_vos.length == 0) {
			MessageDialog.showWarningDlg(this, "����", "��ѡ����Ҫɾ�����У�");
			return;
		}
		if (MessageDialog.showOkCancelDlg(this, "ѯ��", "ȷ��Ҫɾ����ѡ������",
				MessageDialog.ID_CANCEL) == MessageDialog.ID_OK) {
			MPOS_PubManageItf imanage = (MPOS_PubManageItf) NCLocator.getInstance()
					.lookup(MPOS_PubManageItf.class);
			try {
				imanage.deleteBalaTypeConfig(b_vos[0].getPk_balatype_config());
				this.doRefresh();
				this.showHintMessage("ɾ���ɹ�!");
			} catch (Exception e) {
				MessageDialog.showErrorDlg(this, "����", e.getMessage());
			}
		}
	}

}
