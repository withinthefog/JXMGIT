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
 * 付款核对
 * 
 * @author chenjun
 * @date 2011-12-11 下午04:42:22
 */
public class MPOS_PaymentCheckUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * 列表面板
	 */
	private MPOS_PaymentCheckBillPanel billPanel;

	/**
	 * 导入
	 */
	private ButtonObject m_btnImport;

	/**
	 * 清空
	 */
	private ButtonObject m_btnClear;

	/**
	 * 核对
	 */
	private ButtonObject m_btnCheck;

	private ButtonObject MainButtonGroup[];

	@Override
	public String getTitle()
	{
		return "付款核对";
	}

	public MPOS_PaymentCheckUI()
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
		add(this.getBillPanel(), "Center");
		this.setBtnStatus(false);
		this.updateUI();
	}

	/**
	 * 初始化按钮
	 * 
	 */
	private void initButtons()
	{
		m_btnImport = new ButtonObject("导入", "导入", "导入");
		m_btnClear = new ButtonObject("清空", "清空", "清空");
		m_btnCheck = new ButtonObject("核对", "核对", "核对");

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
				MessageDialog.showErrorDlg(this, "错误", "没有查询到付款结算单据");
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
						vos1[i].setResult("不相等，付款结算单金额："+vos2[j].getNmoney());
						break;
					}
				}
				switch (checkresult)
				{
				case 0:
					vos1[i].setResult("相等");
					break;
				case 1:
					
					break;
				default:
					vos1[i].setResult("没有找到对应付款结算单据");
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
					msg.append("付款结算单据号："+checkVO.getDjbh()+"\n");
				}
				msg.append("在此次导入的核对数据中不存在！");
				MessageDialog.showErrorDlg(this, "警告", msg.toString());
			}
			
			showHintMessage("核对完成！");
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		}
	}

	private void doClear()
	{
		this.getBillPanel().getBillData().clearViewData();
	}

	private void doImport()
	{
		// 得到文件路径
		final String userExcelFile_Path = getUserFilePath();
		if (userExcelFile_Path == null)
			return;
		int row = 0;
		// 打开文件
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
			showHintMessage("导入完成！");
		} catch (ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", row + "行" + e.getMessage());
		} catch (BiffException e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", row + "行" + e.getMessage());
		} catch (IOException e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", row + "行" + e.getMessage());
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", row + "行" + e.getMessage());
		}
	}

	public String getUserFilePath()
	{
		// 弹出客户端对话框
		UIFileChooser m_filechooser = new UIFileChooser();
		ExampleFileFilter filter = new ExampleFileFilter();
		filter.addExtension("xls");
		filter.setDescription("EXCEL文件");
		m_filechooser.setFileFilter(filter);
		m_filechooser.setDialogType(JFileChooser.FILES_ONLY);
		int result = m_filechooser.showOpenDialog(null);
		if (result != 0)
			return null;
		// 取得用户保存的路径和文件名
		final String filepath = m_filechooser.getSelectedFile().getParent();
		final String filename = m_filechooser.getSelectedFile().getName();
		final String ExcelFile_Path = filepath + "\\" + filename;
		return ExcelFile_Path;
	}

	/**
	 * 设置按钮状态
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
