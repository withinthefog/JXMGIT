package nc.ui.mallpos.sale;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import nc.ui.mallpos.pub.MPOS_BtnPanel;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;

/**
 * ���ɸ���Ի���
 * 
 * @author chenjun
 * @date 2011-11-27 ����10:45:52
 */
public class MPOS_GenCMPDlg extends UIDialog implements ActionListener
{

	private static final long serialVersionUID = 1L;

	private MPOS_SaleDaySumBillPanel billPanel;

	/**
	 * ��ť���
	 */
	private MPOS_BtnPanel btnPanel;

	/**
	 * �ͻ��˲���
	 */
	public ClientEnvironment ce = ClientEnvironment.getInstance();

	/**
	 * ���캯��
	 * 
	 * @param p_contain
	 *            ��������
	 */
	public MPOS_GenCMPDlg(Container p_contain)
	{
		super(p_contain, "���ɸ��");
		this.initDlg();
	}

	/**
	 * ��ʼ��
	 * 
	 */
	private void initDlg()
	{
		setBounds(0, 0, 800, 600);
		this.add(this.getBillPanel(), "Center");
		this.add(this.getBtnPanel(), "South");
	}

	/**
	 * @return the btnPanel
	 */
	public MPOS_BtnPanel getBtnPanel()
	{
		if (btnPanel == null)
		{
			btnPanel = new MPOS_BtnPanel(this.getWidth());
			btnPanel.getButtonOK().setText("���ɸ��");
			btnPanel.getButtonOK().addActionListener(this);
			btnPanel.getButtonExit().addActionListener(this);
		}
		return btnPanel;
	}

	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource() == this.getBtnPanel().getButtonOK())
		{
			try
			{
				doOK();
			} catch (Exception e)
			{
				MessageDialog.showErrorDlg(this, "����", e.getMessage());
			}
		} else if (arg0.getSource() == this.getBtnPanel().getButtonExit())
		{
			closeCancel();
		}
	}

	/**
	 * ȷ��
	 * 
	 */
	private void doOK()
	{

		closeOK();
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
