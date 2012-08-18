package nc.ui.mallpos.sale;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubManageItf;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.dev.pub.tools.PrintTools;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.vo.mallpos.sale.MPOS_SaleDaySumVO;
import nc.vo.pub.lang.UFDate;

/**
 * ���ۼ�¼�����������
 * 
 * @author cj
 * @date 2012-8-17 ����10:44:13
 */
public class MPOS_SaleDaySumUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * �б����
	 */
	private MPOS_SaleDaySumBillPanel billPanel;

	/**
	 * ��ѯ
	 */
	private ButtonObject m_btnQuery;

	/**
	 * ˢ��
	 */
	private ButtonObject m_btnRefresh;

	/**
	 * ���ɸ��
	 */
	private ButtonObject m_btnCreateCMPBill;

	/**
	 * ���ɴ����̻�����ƾ֤
	 */
	private ButtonObject m_btnCreateDSVoucher;
	
	/*
	 * ����
	 */
	private ButtonObject m_btnExport;

	private ButtonObject MainButtonGroup[];

	public ClientEnvironment ce = ClientEnvironment.getInstance();

	/**
	 * ��ѯ����
	 * 
	 */
	private String sqlWhere;

	private MPOS_SaleQueryDlg queryDlg;

	@Override
	public String getTitle()
	{
		return "�̻��������������";
	}

	public MPOS_SaleDaySumUI()
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
		// this.doRefresh();
		this.setBtnStatus(0, false);
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
		m_btnCreateCMPBill = new ButtonObject("���ɸ��", "���ɸ��", "���ɸ��");
		m_btnCreateDSVoucher = new ButtonObject("���ɴ��ջ���ƾ֤", "���ɴ��ջ���ƾ֤", "���ɴ��ջ���ƾ֤");
		m_btnExport = new ButtonObject("����","����","����");
		m_btnCreateDSVoucher.setVisible(false);
		MainButtonGroup = (new ButtonObject[] { m_btnQuery, m_btnRefresh, m_btnCreateCMPBill, m_btnCreateDSVoucher,m_btnExport });
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
		} else if (arg0 == this.m_btnCreateCMPBill)
		{
			this.doCreateCMPBill();
		} else if (arg0 == this.m_btnCreateDSVoucher)
		{
			this.doCreateDSVoucher();
		}else if (arg0 == this.m_btnExport)
		{
			this.doExport();
		}
	}

	private void doExport()
	{
		PrintTools print = new PrintTools(getBillPanel(), this.getTitle());
		print.onExport();
	}

	/**
	 * ���ɴ����̻�����ƾ֤
	 * 
	 */
	public void doCreateDSVoucher()
	{

	}

	/**
	 * ���ɸ�����㵥
	 * 
	 */
	private void doCreateCMPBill()
	{
		try
		{
			MPOS_GenCMPQueryDlg cmpQDlg = new MPOS_GenCMPQueryDlg(this);
			if (cmpQDlg.showModal() == UIDialog.ID_OK)
			{
				MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class.getName());
				MPOS_SaleDaySumVO[] vos = iquery.querySale(ce.getCorporation().getPk_corp(),cmpQDlg.getWhereCondition());
				MPOS_GenCMPDlg genDlg = new MPOS_GenCMPDlg(this);
				genDlg.getBillPanel().fillGrid(vos);
				if (vos == null || vos.length <= 0)
				{
					genDlg.getBtnPanel().getButtonOK().setEnabled(false);
				}
				if (genDlg.showModal() == UIDialog.ID_OK)
				{
					MPOS_PubManageItf imanage = (MPOS_PubManageItf) NCLocator.getInstance().lookup(MPOS_PubManageItf.class.getName());
					this.showHintMessage("�������ɸ��....");
					imanage.createCMPBill(ce.getUser().getPrimaryKey(), vos, ce.getCorporation().getPk_corp(), new UFDate(cmpQDlg.prepaydate));
					this.showHintMessage("���ɸ�����");
					this.doRefresh();
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "����", e.getMessage());
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
			queryDlg = new MPOS_SaleQueryDlg(this);
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
	public void doRefresh()
	{

		MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class.getName());
		try
		{
			if (StringUtils.isEmpty(this.sqlWhere))
			{
				this.sqlWhere = " and mpos_sale_daysum.billdate = '" + ce.getDate().toString() + "'";
			}
			MPOS_SaleDaySumVO[] vos = iquery.querySale(ce.getCorporation().getPk_corp(),this.sqlWhere);
			this.getBillPanel().fillGrid(vos);
			setBtnStatus(0, (vos != null&&vos.length>0));
			this.showHintMessage("ˢ����ɣ�");
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
	public void setBtnStatus(int status, boolean hasData)
	{
		this.m_btnCreateDSVoucher.setEnabled(hasData);
		this.m_btnExport.setEnabled(hasData);
		updateButtons();
	}

	/**
	 * @return the billPanel
	 */
	public MPOS_SaleDaySumBillPanel getBillPanel()
	{
		if (billPanel == null)
		{
			billPanel = new MPOS_SaleDaySumBillPanel();
		}
		return billPanel;
	}

}
