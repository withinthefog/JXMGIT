package nc.ui.mallpos.voucher;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.JFileChooser;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.NumberFormat;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubManageItf;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.ui.pub.ButtonObject;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.ToftPanel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIFileChooser;
import nc.ui.reportquery.demo.ExampleFileFilter;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.sale.MPOS_PaymentVO;
import nc.vo.mallpos.voucher.MPOS_VoucherConfigVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * ����ƾ֤����������
 * 
 * @author chenjun
 * @date 2011-12-21 ����11:26:21
 */
public class MPOS_GenPayVoucherUI extends ToftPanel
{

	private static final long serialVersionUID = 1L;

	/**
	 * �б����
	 */
	private MPOS_GenPayVoucherBillPanel billPanel;

	/**
	 * ��ѯ
	 */
	private ButtonObject m_btnQuery;

	/**
	 * ˢ��
	 */
	private ButtonObject m_btnRefresh;

	/**
	 * ���ɸ���ƾ֤
	 */
	private ButtonObject m_btnGenPayVoucher;

	private ButtonObject MainButtonGroup[];

	public ClientEnvironment ce = ClientEnvironment.getInstance();

	/**
	 * ��ѯ����
	 * 
	 */
	private String sqlWhere;

	private MPOS_GenPayVoucherQueryDlg queryDlg;

	@Override
	public String getTitle()
	{
		return "����ƾ֤����";
	}

	public MPOS_GenPayVoucherUI()
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
		m_btnGenPayVoucher = new ButtonObject("���ɸ���ƾ֤", "���ɸ���ƾ֤", "���ɸ���ƾ֤");
		MainButtonGroup = (new ButtonObject[] { m_btnQuery, m_btnRefresh, m_btnGenPayVoucher });
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
		} else if (arg0 == this.m_btnGenPayVoucher)
		{
			this.doGenPayVoucher();
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
		int result = m_filechooser.showSaveDialog(null);
		if (result != 0)
			return null;
		// ȡ���û������·�����ļ���
		String filepath = m_filechooser.getSelectedFile().getParent();
		String filename = m_filechooser.getSelectedFile().getName();
		String fileFullPath = filepath + "\\" + filename;
		String ex = filter.getExtension(new File(fileFullPath));
		if (StringUtils.isEmpty(ex))
		{
			fileFullPath += ".xls";
		}
		return fileFullPath;
	}

	/**
	 * ��ƾ֤���ñ��е���Ŀ���Ŀ�Ķ��հ��������HashMap
	 * 
	 * @return
	 */
	private HashMap<String, String> getVoucherAccsubj()
	{
		HashMap<String, String> hm = new HashMap<String, String>();
		MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class);
		try
		{
			MPOS_VoucherConfigVO[] vos = iquery.queryAllVouConfigVO();
			if (vos != null && vos.length > 0)
			{
				for (MPOS_VoucherConfigVO configVO : vos)
				{
					hm.put(configVO.getItemcode(), configVO.getAccsubjcode());
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "����", e.getMessage());
		}
		return hm;
	}

	/**
	 * ���ɸ���ƾ֤
	 * 
	 */
	private void doGenPayVoucher()
	{
		if (MessageDialog.showYesNoDlg(this, "ѯ��", "�Ƿ��б�������������֧���̻�����ƾ֤��") == MessageDialog.ID_YES)
		{
			// �õ��ļ�·��
			final String userFile_Path = getUserFilePath();
			if (userFile_Path == null)
				return;
			MPOS_PaymentVO[] vos = this.getBillPanel().getBodyValueVOs();
			if (vos == null || vos.length <= 0)
				return;
			try
			{

				HashMap<String, String> hm = this.getVoucherAccsubj();
				WritableWorkbook wwb = Workbook.createWorkbook(new File(userFile_Path));
				WritableSheet wsheet = wwb.createSheet("����ƾ֤", 0);
				int row = 1;
				UFDouble sum = new UFDouble();// �����ܽ��

				wsheet.addCell(new Label(0, 0, "����"));
				wsheet.addCell(new Label(1, 0, "ƾ֤���"));
				wsheet.addCell(new Label(2, 0, "ƾ֤��"));
				wsheet.addCell(new Label(3, 0, "��������"));
				wsheet.addCell(new Label(4, 0, "ժҪ"));
				wsheet.addCell(new Label(5, 0, "��Ŀ����"));
				wsheet.addCell(new Label(6, 0, "�跽���"));
				wsheet.addCell(new Label(7, 0, "�������"));
				wsheet.addCell(new Label(8, 0, "����"));
				wsheet.addCell(new Label(9, 0, "���"));
				wsheet.addCell(new Label(10, 0, "����"));
				wsheet.addCell(new Label(11, 0, "�Ƶ���"));
				wsheet.addCell(new Label(12, 0, "���㷽ʽ"));
				wsheet.addCell(new Label(13, 0, "Ʊ��"));
				wsheet.addCell(new Label(14, 0, "Ʊ�ŷ�������"));
				wsheet.addCell(new Label(15, 0, "���ű���"));
				wsheet.addCell(new Label(16, 0, "���˱���"));
				wsheet.addCell(new Label(17, 0, "�ͻ�����"));
				wsheet.addCell(new Label(18, 0, "��Ӧ�̱���"));
				wsheet.addCell(new Label(19, 0, "ҵ��Ա"));
				wsheet.addCell(new Label(20, 0, "��Ŀ����"));
				wsheet.addCell(new Label(21, 0, "��������"));
				HashMap<String, String> hmTemp = new HashMap<String, String>();
				String key = null;
				int voucherno = 1;
				NumberFormat nFormat = new NumberFormat("#.00");//������λС�������С��λ������λ����0���
				WritableCellFormat wcellFormat = new WritableCellFormat(nFormat);
				for (int i = 0; i < vos.length; i++)
				{
					key = vos[i].getCustcode().substring(0, 1);
					if (!hmTemp.containsKey(key))
					{
						for (int j = 0; j < vos.length; j++)
						{
							if (hmTemp.containsKey(vos[j].getCustcode().substring(0, 1)))
							{
								continue;
							}
							if (key.equals(vos[j].getCustcode().substring(0, 1)))
							{
								// Label(�к�,�к� ,���� )
								// ��
								wsheet.addCell(new Label(0, row, vos[j].getPaydate().toString()));
								wsheet.addCell(new Label(1, row, "��"));
								wsheet.addCell(new Label(2, row, String.valueOf(voucherno)));
								wsheet.addCell(new Label(3, row, String.valueOf(0)));
								wsheet.addCell(new Label(4, row, "֧���̻�����"));
								wsheet.addCell(new Label(5, row, hm.get(MPOS_GlobalVariable.ITEMCODE_DSFUND)));
								wsheet.addCell(new Number(6, row, vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP).doubleValue(),wcellFormat));
								//wsheet.addCell(new Label(6, row, String.valueOf(vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP))));
								wsheet.addCell(new Label(11, row, ce.getUser().getUserName()));
								wsheet.addCell(new Label(14, row, String.valueOf(vos[j].getPaydate())));
								wsheet.addCell(new Label(17, row, vos[j].getCustcode()));
								sum = sum.add(vos[j].getNmoney());
								row++;
							}
						}
						// ��
						wsheet.addCell(new Label(0, row, vos[0].getPaydate().toString()));
						wsheet.addCell(new Label(1, row, "��"));
						wsheet.addCell(new Label(2, row, String.valueOf(voucherno)));
						wsheet.addCell(new Label(3, row, String.valueOf(0)));
						wsheet.addCell(new Label(4, row, "֧���̻�����"));
						wsheet.addCell(new Label(5, row, hm.get(MPOS_GlobalVariable.ITEMCODE_BANKDEPOSIT)));
						wsheet.addCell(new Number(7, row, sum.setScale(2, UFDouble.ROUND_HALF_UP).doubleValue(),wcellFormat));
						//wsheet.addCell(new Label(7, row, String.valueOf(sum.setScale(2, UFDouble.ROUND_HALF_UP))));
						
						wsheet.addCell(new Label(11, row, ce.getUser().getUserName()));
						wsheet.addCell(new Label(14, row, String.valueOf(vos[0].getPaydate())));
						row++;
						sum = new UFDouble();
						
						hmTemp.put(key, key);
						voucherno++;
					}
				}

				wwb.write();
				wwb.close();
				MPOS_PubManageItf imanage = (MPOS_PubManageItf) NCLocator.getInstance().lookup(MPOS_PubManageItf.class);
				for (int i = 0; i < vos.length; i++)
				{
					// ��д����ƾ֤��ʶ
					imanage.writeBackCMPbill(vos[i].getVouchid());
				}
				this.doRefresh();
				MessageDialog.showHintDlg(this, "��ʾ", "���ɳɹ���" + userFile_Path);
			} catch (IOException e)
			{
				e.printStackTrace();
				MessageDialog.showErrorDlg(this, "����", e.getMessage());
			} catch (Exception e)
			{
				e.printStackTrace();
				MessageDialog.showErrorDlg(this, "����", e.getMessage());
			}
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
			queryDlg = new MPOS_GenPayVoucherQueryDlg(this);
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
				UFDate date = null;
				try
				{
					date = iquery.queryPrePayDate();
				} catch (Exception e)
				{
					e.printStackTrace();
					date = ce.getDate();
				}
				// zzzt = 1֧���ɹ�
				this.sqlWhere = " and b.zzzt = 1  and b.djrq = '" + date + "'";
			}
			MPOS_PaymentVO[] vos = iquery.queryPayBillByWhere(this.sqlWhere);
			this.getBillPanel().fillGrid(vos);
			setBtnStatus(0, (vos != null && vos.length > 0));
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
		this.m_btnGenPayVoucher.setEnabled(hasData);
		updateButtons();
	}

	/**
	 * @return the billPanel
	 */
	public MPOS_GenPayVoucherBillPanel getBillPanel()
	{
		if (billPanel == null)
		{
			billPanel = new MPOS_GenPayVoucherBillPanel();
		}
		return billPanel;
	}

}
