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
 * 结算方式配置主界面
 * 
 * @author cj
 * @date 2012-8-16 下午01:09:50
 */
public class MPOS_BalaTypeConfigUI extends ToftPanel {

	private static final long serialVersionUID = 1L;

	private MPOS_BalaTypeBillPanel mainPanel = null;

	// 界面按钮定义
	private ButtonObject m_BtnRefresh; // 刷新

	private ButtonObject m_BtnUpdate; // 修改

	private ButtonObject m_BtnSave; // 保存

	private ButtonObject m_BtnCancel; // 取消

	private ButtonObject m_BtnDel; // 删除

	private ButtonObject m_BtnAddLin; // 增行

	private ButtonObject m_BtnDelLin; // 删行

	private ButtonObject MainButtonGroup[];

	@Override
	public String getTitle() {
		return "结算方式配置";
	}

	public MPOS_BalaTypeConfigUI() {
		super();
		initialize();
	}

	/**
	 * 初始化
	 */
	public void initialize() {
		this.initButtons();
		add(getMainCardPanel());
		this.setBtnStatus(0);
		this.doRefresh();
	}

	/**
	 * 初始化按钮
	 */
	public void initButtons() {
		m_BtnRefresh = new ButtonObject("刷新", "刷新", "刷新");
		m_BtnUpdate = new ButtonObject("修改", "修改", "修改");
		m_BtnSave = new ButtonObject("保存", "保存", "保存");
		m_BtnCancel = new ButtonObject("取消", "取消", "取消");
		m_BtnDel = new ButtonObject("删除", "删除", "删除");
		m_BtnAddLin = new ButtonObject("增行", "增行", "增行");
		m_BtnDelLin = new ButtonObject("删行", "删行", "删行");

		MainButtonGroup = (new ButtonObject[] { m_BtnRefresh, m_BtnUpdate,
				m_BtnSave, m_BtnCancel, m_BtnDel, m_BtnAddLin, m_BtnDelLin });
		setButtons(MainButtonGroup);
	}

	/**
	 * 得到主面板
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
	 * 设置按钮状态
	 * 
	 * @param btnStatus
	 *            状态 0:默认,1:编辑
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
	 * 刷新
	 */
	public void doRefresh() {
		MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance()
				.lookup(MPOS_PubQueryItf.class);
		try {
			MPOS_BalaTypeConfigVO[] vos = iquery.queryAllBalaTypeConfigVO();
			this.getMainCardPanel().fillGrid(vos);
			this.setBtnStatus(0);
		} catch (Exception e) {
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		}
	}

	/**
	 * 修改数据
	 */
	public void doUpdate() {
		this.setBtnStatus(1);
	}

	/**
	 * 保存数据
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
			this.showHintMessage("保存成功!");
		} catch (Exception e) {
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		}
	}

	/**
	 * 取消修改
	 */
	public void doCancel() {
		this.doRefresh();
	}

	/**
	 * 添加行
	 */
	public void doAddLin() {
		this.getMainCardPanel().addLine();
		this.getMainCardPanel().setBodyValueAt(0,
				this.getMainCardPanel().getRowCount() - 1, "charging_type");
		this.setBtnStatus(1);
	}

	/**
	 * 删除行
	 */
	public void doDelLin() {
		MPOS_BalaTypeConfigVO[] b_vos = this.getMainCardPanel()
				.getBodySelectVOsByMouse();
		if (b_vos == null || b_vos.length <= 0) {
			MessageDialog.showWarningDlg(this, "警告", "请选择你要删除的行！");
			return;
		}
		if (StringUtils.isNotEmpty(b_vos[0].getPk_balatype_config())) {
			MessageDialog.showWarningDlg(this, "警告", "删除行存在数据，请点击删除！");
			return;
		}
		this.getMainCardPanel().delLine();
		if (this.getMainCardPanel().getRowCount() == 0) {
			this.setBtnStatus(0);
		}
	}

	/**
	 * 删除数据
	 */
	public void doDel() {
		MPOS_BalaTypeConfigVO[] b_vos = this.getMainCardPanel()
				.getBodySelectVOsByMouse();
		if (b_vos == null || b_vos.length == 0) {
			MessageDialog.showWarningDlg(this, "警告", "请选择你要删除的行！");
			return;
		}
		if (MessageDialog.showOkCancelDlg(this, "询问", "确认要删除所选数据吗？",
				MessageDialog.ID_CANCEL) == MessageDialog.ID_OK) {
			MPOS_PubManageItf imanage = (MPOS_PubManageItf) NCLocator.getInstance()
					.lookup(MPOS_PubManageItf.class);
			try {
				imanage.deleteBalaTypeConfig(b_vos[0].getPk_balatype_config());
				this.doRefresh();
				this.showHintMessage("删除成功!");
			} catch (Exception e) {
				MessageDialog.showErrorDlg(this, "错误", e.getMessage());
			}
		}
	}

}
