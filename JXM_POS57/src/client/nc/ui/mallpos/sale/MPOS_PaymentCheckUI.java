package nc.ui.mallpos.sale;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIFileChooser;
import nc.ui.reportquery.demo.ExampleFileFilter;
import nc.vo.mallpos.sale.MPOS_PaymentVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ����˶�
 * 
 * @author chenjun
 * @date 2011-12-11 ����04:42:22
 */
public class MPOS_PaymentCheckUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * �б����
	 */
	private MPOS_PaymentCheckBillPanel billPanel;

	/**
	 * ����
	 */
	private ButtonObject m_btnImport;

	/**
	 * ���
	 */
	private ButtonObject m_btnClear;

	/**
	 * �˶�
	 */
	private ButtonObject m_btnCheck;

	private ButtonObject MainButtonGroup[];

	@Override
	public String getTitle()
	{
		return "����˶�";
	}

	public MPOS_PaymentCheckUI()
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
		this.setBtnStatus(false);
		this.updateUI();
	}

	/**
	 * ��ʼ����ť
	 * 
	 */
	private void initButtons()
	{
		m_btnImport = new ButtonObject("����", "����", "����");
		m_btnClear = new ButtonObject("���", "���", "���");
		m_btnCheck = new ButtonObject("�˶�", "�˶�", "�˶�");

		MainButtonGroup = (new ButtonObject[] { m_btnImport, m_btnClear, m_btnCheck });
		this.setButtons(MainButtonGroup);
	}

	@Override
	public void onButtonClicked(ButtonObject arg0)
	{
		if (arg0 == this.m_btnImport)
		{
			this.doImport();
		} else if (arg0 == this.m_btnClear)
		{
			this.doClear();
		} else if (arg0 == this.m_btnCheck)
		{
			this.doCheck();
		}
	}

	private void doCheck()
	{
		MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class);
		try
		{
			MPOS_PaymentVO[] vos1 = this.getBillPanel().getBodyValueVOs();
			MPOS_PaymentVO[] vos2 = iquery.queryPayBillByWhere("  and b.djrq = '" + vos1[0].getPaydate() + "'");
			if (vos2 == null || vos2.length <= 0)
			{
				MessageDialog.showErrorDlg(this, "����", "û�в�ѯ��������㵥��");
				return;
			}
			int checkresult = -1;
			for (int i = 0; i < vos1.length; i++)
			{
				checkresult = -1;
				for (int j = 0; j < vos2.length; j++)
				{
					if (vos1[i].getCustcode().equals(vos2[j].getCustcode()) && vos1[i].getCustname().equals(vos2[j].getCustname()) && vos1[i].getNmoney().compareTo(vos2[j].getNmoney()) == 0)
					{
						checkresult = 0;

						break;
					} else if (vos1[i].getCustcode().equals(vos2[j].getCustcode()) && vos1[i].getCustname().equals(vos2[j].getCustname()) && vos1[i].getNmoney().compareTo(vos2[j].getNmoney()) != 0)
					{
						checkresult = 1;
						vos1[i].setResult("����ȣ�������㵥��"+vos2[j].getNmoney());
						break;
					}
				}
				switch (checkresult)
				{
				case 0:
					vos1[i].setResult("���");
					break;
				case 1:
					
					break;
				default:
					vos1[i].setResult("û���ҵ���Ӧ������㵥��");
					break;
				}
			}
			this.getBillPanel().fillGrid(vos1);
			
			
			boolean isExists = false;
			ArrayList<MPOS_PaymentVO> notExists = new ArrayList<MPOS_PaymentVO>();
			for (MPOS_PaymentVO checkVO2 : vos2)
			{
				isExists = false;
				for (MPOS_PaymentVO checkVO1 : vos1)
				{
					if (checkVO2.getCustcode().equals(checkVO1.getCustcode()) && checkVO2.getCustname().equals(checkVO1.getCustname()))
					{
						isExists = true;
						break;
					}
				}
				if(!isExists)
				{
					notExists.add(checkVO2);
				}
			}
			if(notExists.size()>0)
			{
				StringBuffer msg = new StringBuffer();
				for (MPOS_PaymentVO checkVO : notExists)
				{
					msg.append("������㵥�ݺţ�"+checkVO.getDjbh()+"\n");
				}
				msg.append("�ڴ˴ε���ĺ˶������в����ڣ�");
				MessageDialog.showErrorDlg(this, "����", msg.toString());
			}
			
			showHintMessage("�˶���ɣ�");
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "����", e.getMessage());
		}
	}

	private void doClear()
	{
		this.getBillPanel().getBillData().clearViewData();
	}

	private void doImport()
	{
		// �õ��ļ�·��
		final String userExcelFile_Path = getUserFilePath();
		if (userExcelFile_Path == null)
			return;
		int row = 0;
		// ���ļ�
		try
		{
			Workbook book = Workbook.getWorkbook(new File(userExcelFile_Path));
			Sheet sh = book.getSheet(0);
			int rowCount = sh.getRows();
			if (rowCount <= 1)
				return;
			ArrayList<MPOS_PaymentVO> vos = new ArrayList<MPOS_PaymentVO>();
			MPOS_PaymentVO vo = null;
			for (int i = 1; i < rowCount; i++)
			{
				row = i;
				vo = new MPOS_PaymentVO();
				vo.setPaydate(new UFDate(sh.getCell(0, i).getContents()));
				vo.setCustcode(sh.getCell(1, i).getContents());
				vo.setCustname(sh.getCell(2, i).getContents());
				String str = sh.getCell(3, i).getContents();
				vo.setNmoney(new UFDouble(Double.parseDouble(str)));
				vos.add(vo);
			}
			this.getBillPanel().fillGrid(vos.toArray(new MPOS_PaymentVO[0]));
			this.setBtnStatus(!vos.isEmpty());
			showHintMessage("������ɣ�");
		} catch (ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "����", row + "��" + e.getMessage());
		} catch (BiffException e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "����", row + "��" + e.getMessage());
		} catch (IOException e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "����", row + "��" + e.getMessage());
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "����", row + "��" + e.getMessage());
		}
	}

	public String getUserFilePath()
	{
		// �����ͻ��˶Ի���
		UIFileChooser m_filechooser = new UIFileChooser();
		ExampleFileFilter filter = new ExampleFileFilter();
		filter.addExtension("xls");
		filter.setDescription("EXCEL�ļ�");
		m_filechooser.setFileFilter(filter);
		m_filechooser.setDialogType(JFileChooser.FILES_ONLY);
		int result = m_filechooser.showOpenDialog(null);
		if (result != 0)
			return null;
		// ȡ���û������·�����ļ���
		final String filepath = m_filechooser.getSelectedFile().getParent();
		final String filename = m_filechooser.getSelectedFile().getName();
		final String ExcelFile_Path = filepath + "\\" + filename;
		return ExcelFile_Path;
	}

	/**
	 * ���ð�ť״̬
	 * 
	 */
	public void setBtnStatus(boolean hasdata)
	{
		this.m_btnClear.setEnabled(hasdata);
		this.m_btnCheck.setEnabled(hasdata);
		updateButtons();
		updateUI();
	}

	/**
	 * @return the billPanel
	 */
	public MPOS_PaymentCheckBillPanel getBillPanel()
	{
		if (billPanel == null)
		{
			billPanel = new MPOS_PaymentCheckBillPanel();
		}
		return billPanel;
	}

}
