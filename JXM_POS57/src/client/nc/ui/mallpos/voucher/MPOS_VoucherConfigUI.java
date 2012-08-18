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
 * 生成凭证参数配置
 * 
 * @author chenjun
 * @date 2011-12-18 上午11:39:27
 */
public class MPOS_VoucherConfigUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * 刷新
	 */
	private ButtonObject m_btnRefresh;

	/**
	 * 修改
	 */
	private ButtonObject m_btnUpdate;

	/**
	 * 保存
	 */
	private ButtonObject m_btnSave;

	/**
	 * 取消
	 */
	private ButtonObject m_btnCancel;

	private ButtonObject MainButtonGroup[];

	/**
	 * 卡片面板
	 */
	private MPOS_VoucherConfigCardPanel cardPanel;

	ClientEnvironment ce = ClientEnvironment.getInstance();

	public MPOS_VoucherConfigUI()
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
		this.add(this.getCardPanel(), "Center");
		this.updateUI();
		this.doRefresh();
	}

	/**
	 * 初始化按钮
	 * 
	 */
	private void initButtons()
	{
		m_btnRefresh = new ButtonObject("刷新", "刷新", "刷新");
		m_btnUpdate = new ButtonObject("修改", "修改", "修改");
		m_btnSave = new ButtonObject("保存", "保存", "保存");
		m_btnCancel = new ButtonObject("取消", "取消", "取消");
		MainButtonGroup = (new ButtonObject[] { m_btnRefresh, m_btnUpdate, m_btnSave, m_btnCancel });
		this.setButtons(MainButtonGroup);
		updateButtons();
	}

	@Override
	public String getTitle()
	{
		return "凭证参数配置";
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
	 * 保存
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
					MessageDialog.showErrorDlg(this, "错误", "第" + (i + 1) + "行科目编码不能为空！");
					return;
				}
			}
			imanage.saveVoucherConfig(vos);
			this.doRefresh();
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		}
	}

	/**
	 * 刷新
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
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		}

	}

	/**
	 * 设置按钮状态
	 * 
	 * @param btnStatus
	 *            0:默认，1：修改 状态<br>
	 * @param hasData
	 *            是否有数据
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
	 * 修改
	 * 
	 */
	private void doUpdate()
	{
		this.setBtnStatus(1, true);
	}

	/**
	 * 取消
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
