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
import nc.ui.mallpos.sale.MPOS_SaleDaySumUI;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIFileChooser;
import nc.ui.reportquery.demo.ExampleFileFilter;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.sale.MPOS_SaleDaySumVO;
import nc.vo.mallpos.voucher.MPOS_VoucherConfigVO;
import nc.vo.pub.lang.UFDouble;

public class MPOS_GenDSVoucherUI extends MPOS_SaleDaySumUI
{

	private static final long serialVersionUID = 1L;

	public MPOS_GenDSVoucherUI()
	{
		super();
		this.getButtonObjectByCode("���ɸ��").setVisible(false);
		this.getButtonObjectByCode("���ɴ��ջ���ƾ֤").setVisible(true);
		this.getButtonObjectByCode("����").setVisible(false);
		this.updateButtons();
	}

	@Override
	public String getTitle()
	{
		return "���ջ���ƾ֤����";
	}

	/*
	 * ���ɿ�ֱ�ӵ���U8��������ƾ֤��txt�ļ�
	@Override
	public void doCreateDSVoucher()
	{
		if (MessageDialog.showYesNoDlg(this, "ѯ��", "�Ƿ��б������������ɴ��ջ���ƾ֤��") == MessageDialog.ID_YES)
		{
			// �õ��ļ�·��
			final String userFile_Path = getUserFilePath();
			if (userFile_Path == null)
				return;
			KNPOS_SaleVO[] vos = this.getBillPanel().getBodyValueVOs();
			if (vos == null || vos.length <= 0)
				return;
			try
			{

				HashMap<String, String> hm = this.getVoucherAccsubj();

				FileWriter writer = new FileWriter(userFile_Path, true);
				PrintWriter pw = new PrintWriter(writer);
				pw.println("����ƾ֤,V800");
				StringBuffer sbu = null;
				String key = null;
				int voucherno = 1;
				HashMap<String, String> hmTemp = new HashMap<String, String>();
				for (int i = 0; i < vos.length; i++)
				{
					if (!hmTemp.containsKey(vos[i].getBilldate() + vos[i].getUsercode()))
					{
						key = vos[i].getBilldate() + vos[i].getUsercode();
						for (int j = 0; j < vos.length; j++)
						{
							if (hmTemp.containsKey(vos[j].getBilldate() + vos[j].getUsercode()))
							{
								continue;
							}
							if (key.equals(vos[j].getBilldate() + vos[j].getUsercode()))
							{

								// ��
								sbu = new StringBuffer();
								sbu.append(vos[j].getBilldate() + ",");
								sbu.append("��,");
								sbu.append(voucherno + ",");
								sbu.append(0 + ",");
								sbu.append("�����̻�����" + vos[j].getBilldate() + ",");
								if (KNPOS_GlobalVariable.PAYTYPE_CARD.equals(vos[j].getBilltype()))
								{
									sbu.append(hm.get(KNPOS_GlobalVariable.ITEMCODE_BANKDEPOSIT) + ",");
									sbu.append(vos[j].getNmoney().sub(vos[j].getNfee()) + ",");
								} else
								{
									sbu.append(hm.get(KNPOS_GlobalVariable.ITEMCODE_CASH) + ",");
									sbu.append(vos[j].getNmoney() + ",");
								}

								sbu.append(",,,,");
								sbu.append(ce.getUser().getUserName() + ",");
								sbu.append(",," + vos[j].getBilldate());
								sbu.append(",,,,,,");
								pw.println(sbu.toString());

								// ��������
								if (KNPOS_GlobalVariable.PAYTYPE_CARD.equals(vos[j].getBilltype()))
								{
									sbu = new StringBuffer();
									sbu.append(vos[j].getBilldate() + ",");
									sbu.append("��,");
									sbu.append(voucherno + ",");
									sbu.append(0 + ",");
									sbu.append("�����ѿ�������" + vos[j].getBilldate() + ",");
									if (KNPOS_GlobalVariable.PAYTYPE_CARD.equals(vos[j].getBilltype()))
									{
										sbu.append(hm.get(KNPOS_GlobalVariable.ITEMCODE_HANDFEE) + ",");
										sbu.append(vos[j].getNfee() + ",");
									}
									sbu.append(",,,,");
									sbu.append(ce.getUser().getUserName() + ",");
									sbu.append(",," + vos[j].getBilldate());
									sbu.append(",,");
									sbu.append(vos[j].getCustcode());
									sbu.append(",,,,");
									pw.println(sbu.toString());
								}

								// ��
								sbu = new StringBuffer();
								sbu.append(vos[j].getBilldate() + ",");
								sbu.append("��,");
								sbu.append(voucherno + ",");
								sbu.append(0 + ",");
								sbu.append("�����̻�����" + vos[j].getBilldate() + ",");
								sbu.append(hm.get(KNPOS_GlobalVariable.ITEMCODE_DSFUND) + ",,");
								sbu.append(vos[j].getNmoney() + ",");
								sbu.append(",,,");
								sbu.append(ce.getUser().getUserName() + ",,,");
								sbu.append(vos[j].getBilldate());
								sbu.append(",,");
								sbu.append(vos[j].getCustcode());
								sbu.append(",,,,");
								pw.println(sbu.toString());

							}
						}
						hmTemp.put(key, key);
						voucherno++;
					}
				}
				pw.close();
				KNPOS_PubManageItf imanage = (KNPOS_PubManageItf) NCLocator.getInstance().lookup(KNPOS_PubManageItf.class);
				for (int i = 0; i < vos.length; i++)
				{
					vos[i].setVoucher("1");
					imanage.updateSaleVO(vos[i]);
				}
				super.doRefresh();
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
	
	public String getUserFilePath()
	{
		// �����ͻ��˶Ի���
		UIFileChooser m_filechooser = new UIFileChooser();
		ExampleFileFilter filter = new ExampleFileFilter();
		filter.addExtension("txt");
		filter.setDescription("�ı��ļ�");
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
			fileFullPath += ".txt";
		}
		return fileFullPath;
	}
	*/
	
	
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
	
	@Override
	public void doCreateDSVoucher()
	{
		if (MessageDialog.showYesNoDlg(this, "ѯ��", "�Ƿ��б������������ɴ��ջ���ƾ֤��") == MessageDialog.ID_YES)
		{
			// �õ��ļ�·��
			final String userFile_Path = getUserFilePath();
			if (userFile_Path == null)
				return;
			this.showHintMessage("��������...");
			MPOS_SaleDaySumVO[] vos = this.getBillPanel().getBodyValueVOs();
			if (vos == null || vos.length <= 0)
				return;
			try
			{
				HashMap<String, String> hm = this.getVoucherAccsubj();
				WritableWorkbook wwb = Workbook.createWorkbook(new File(userFile_Path));
				WritableSheet wsheet = wwb.createSheet("�����̻�����ƾ֤", 0);
				String key = null;
				int voucherno = 1;
				int row =1;
				HashMap<String, String> hmTemp = new HashMap<String, String>();
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
				NumberFormat nFormat = new NumberFormat("#.00");//������λС�������С��λ������λ����0���
				WritableCellFormat wcellFormat = new WritableCellFormat(nFormat);
				
				/*2012-04-25	�¾�	ע�ͣ������ǰ����ڡ�����Ա���̻����տʽ�������ÿ����¼���衢����ϸ*/
//				for (int i = 0; i < vos.length; i++)
//				{
//					key = vos[i].getBilldate() + vos[i].getUsercode();
//					if (!hmTemp.containsKey(key))
//					{
//						for (int j = 0; j < vos.length; j++)
//						{
//							if (hmTemp.containsKey(vos[j].getBilldate() + vos[j].getUsercode()))
//							{
//								continue;
//							}
//							if (key.equals(vos[j].getBilldate() + vos[j].getUsercode()))
//							{
//								
//								// Label(�к�,�к� ,���� )
//								// ��
//								wsheet.addCell(new Label(0, row, vos[j].getBilldate().toString()));
//								wsheet.addCell(new Label(1, row, "��"));
//								wsheet.addCell(new Label(2, row, String.valueOf(voucherno)));
//								wsheet.addCell(new Label(3, row, String.valueOf(0)));
//								if(vos[j].getNmoney().compareTo(new UFDouble(0,2))<0)
//								{
//									wsheet.addCell(new Label(4, row, "���̻�����"));
//								}else{
//									wsheet.addCell(new Label(4, row, "�����̻�����"));
//								}
//								
//								if (KNPOS_GlobalVariable.PAYTYPE_CARD.equals(vos[j].getBilltype()))
//								{
//									wsheet.addCell(new Label(5,row,hm.get(KNPOS_GlobalVariable.ITEMCODE_BANKDEPOSIT)));
//									//wsheet.addCell(new Label(6,row,String.valueOf(vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP).sub(vos[j].getNfee().setScale(2, UFDouble.ROUND_HALF_UP)).setScale(2, UFDouble.ROUND_HALF_UP))));
//									wsheet.addCell(new Number(6, row, vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP).sub(vos[j].getNfee().setScale(2, UFDouble.ROUND_HALF_UP)).setScale(2, UFDouble.ROUND_HALF_UP).doubleValue(),wcellFormat));
//								} else
//								{
//									wsheet.addCell(new Label(5,row,hm.get(KNPOS_GlobalVariable.ITEMCODE_CASH)));
//									//wsheet.addCell(new Label(6,row,String.valueOf(vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP))));
//									wsheet.addCell(new Number(6, row, vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP).doubleValue(),wcellFormat));
//								}
//								wsheet.addCell(new Label(11,row,ce.getUser().getUserName()));
//								wsheet.addCell(new Label(14,row,String.valueOf(vos[j].getBilldate())));
//
//								// ��������
//								if (KNPOS_GlobalVariable.PAYTYPE_CARD.equals(vos[j].getBilltype()))
//								{
//									row++;
//									wsheet.addCell(new Label(0,row,vos[j].getBilldate().toString()));
//									wsheet.addCell(new Label(1,row,"��"));
//									wsheet.addCell(new Label(2,row,String.valueOf(voucherno)));
//									wsheet.addCell(new Label(3,row,String.valueOf(0)));
//									if(vos[j].getNfee().compareTo(new UFDouble(0,2))<0)
//									{
//										wsheet.addCell(new Label(4, row, "�������ѿ�������"));
//									}else{
//										wsheet.addCell(new Label(4,row,"�����ѿ�������"));
//									}
//									
//									wsheet.addCell(new Label(5,row,hm.get(KNPOS_GlobalVariable.ITEMCODE_HANDFEE)));
//									//wsheet.addCell(new Label(6,row,String.valueOf(vos[j].getNfee().setScale(2, UFDouble.ROUND_HALF_UP))));
//									wsheet.addCell(new Number(6, row, vos[j].getNfee().setScale(2, UFDouble.ROUND_HALF_UP).doubleValue(),wcellFormat));
//									wsheet.addCell(new Label(11,row,ce.getUser().getUserName()));
//									wsheet.addCell(new Label(14,row,String.valueOf(vos[j].getBilldate())));
//									wsheet.addCell(new Label(17,row,vos[j].getCustcode()));
//								}
//
//								// ��
//								row++;
//								wsheet.addCell(new Label(0,row,vos[j].getBilldate().toString()));
//								wsheet.addCell(new Label(1,row,"��"));
//								wsheet.addCell(new Label(2,row,String.valueOf(voucherno)));
//								wsheet.addCell(new Label(3,row,String.valueOf(0)));
//								if(vos[j].getNmoney().compareTo(new UFDouble(0,2))<0)
//								{
//									wsheet.addCell(new Label(4, row, "���̻�����"));
//								}else{
//									wsheet.addCell(new Label(4, row, "�����̻�����"));
//								}
//								wsheet.addCell(new Label(5,row,hm.get(KNPOS_GlobalVariable.ITEMCODE_DSFUND)));
//								//wsheet.addCell(new Label(7,row,String.valueOf(vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP))));
//								wsheet.addCell(new Number(7, row, vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP).doubleValue(),wcellFormat));
//								wsheet.addCell(new Label(11,row,ce.getUser().getUserName()));
//								wsheet.addCell(new Label(14,row,String.valueOf(vos[j].getBilldate())));
//								wsheet.addCell(new Label(17,row,vos[j].getCustcode()));
//								row++;
//							}
//						}
//						hmTemp.put(key, key);
//						voucherno++;
//					}
//				}
				
				/*2012-04-25	�¾�	��ӣ������ǰ����ڡ�����Ա���̻����տʽ������ܵ��跽�����̻������ֽ�����д��ֱ�ϲ���һ����¼*/
				for (int i = 0; i < vos.length; i++)
				{
					key = vos[i].getBilldate() + vos[i].getUsercode();
					if (!hmTemp.containsKey(key))
					{
						UFDouble debit_sumcash = new UFDouble(0);
						UFDouble debit_sumcard = new UFDouble(0);
						for (int j = 0; j < vos.length; j++)
						{
							if (hmTemp.containsKey(vos[j].getBilldate() + vos[j].getUsercode()))
							{
								continue;
							}
							if (key.equals(vos[j].getBilldate() + vos[j].getUsercode()))
							{
								//������˻��򲻺ϲ�
								if (vos[j].getNmoney().compareTo(new UFDouble(0, 2)) < 0)
								{
									wsheet.addCell(new Label(0, row, vos[j].getBilldate().toString()));
									wsheet.addCell(new Label(1, row, "��"));
									wsheet.addCell(new Label(2, row, String.valueOf(voucherno)));
									wsheet.addCell(new Label(3, row, String.valueOf(0)));
									wsheet.addCell(new Label(4, row, "���̻�����"));
									if (MPOS_GlobalVariable.BALANCETYPE_CARD.equals(vos[j].getBalancode()))
									{
										wsheet.addCell(new Label(5, row, hm.get(MPOS_GlobalVariable.ITEMCODE_BANKDEPOSIT)));
										wsheet.addCell(new Number(6, row, vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP).sub(
												vos[j].getNfee().setScale(2, UFDouble.ROUND_HALF_UP)).setScale(2, UFDouble.ROUND_HALF_UP).doubleValue(), wcellFormat));
									} else
									{
										wsheet.addCell(new Label(5, row, hm.get(MPOS_GlobalVariable.ITEMCODE_CASH)));
										wsheet.addCell(new Number(6, row, vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP).doubleValue(), wcellFormat));
									}
									wsheet.addCell(new Label(11, row, ce.getUser().getUserName()));
									wsheet.addCell(new Label(14, row, String.valueOf(vos[j].getBilldate())));
									row++;
								} else
								{
									if (MPOS_GlobalVariable.BALANCETYPE_CARD.equals(vos[j].getBalancode()))
									{
										debit_sumcard = debit_sumcard.add(vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP).sub(
												vos[j].getNfee().setScale(2, UFDouble.ROUND_HALF_UP)).setScale(2, UFDouble.ROUND_HALF_UP).doubleValue());
									} else
									{
										debit_sumcash = debit_sumcash.add(vos[j].getNmoney());
									}
								}
								// ----------------
								// ��������
								if (MPOS_GlobalVariable.BALANCETYPE_CARD.equals(vos[j].getBalancode()))
								{
									
									wsheet.addCell(new Label(0,row,vos[j].getBilldate().toString()));
									wsheet.addCell(new Label(1,row,"��"));
									wsheet.addCell(new Label(2,row,String.valueOf(voucherno)));
									wsheet.addCell(new Label(3,row,String.valueOf(0)));
									if(vos[j].getNfee().compareTo(new UFDouble(0,2))<0)
									{
										wsheet.addCell(new Label(4, row, "�������ѿ�������"));
									}else{
										wsheet.addCell(new Label(4,row,"�����ѿ�������"));
									}
									
									wsheet.addCell(new Label(5,row,hm.get(MPOS_GlobalVariable.ITEMCODE_HANDFEE)));
									//wsheet.addCell(new Label(6,row,String.valueOf(vos[j].getNfee().setScale(2, UFDouble.ROUND_HALF_UP))));
									wsheet.addCell(new Number(6, row, vos[j].getNfee().setScale(2, UFDouble.ROUND_HALF_UP).doubleValue(),wcellFormat));
									wsheet.addCell(new Label(11,row,ce.getUser().getUserName()));
									wsheet.addCell(new Label(14,row,String.valueOf(vos[j].getBilldate())));
									wsheet.addCell(new Label(17,row,vos[j].getCustcode()));
									row++;
								}

								// ��
								
								wsheet.addCell(new Label(0,row,vos[j].getBilldate().toString()));
								wsheet.addCell(new Label(1,row,"��"));
								wsheet.addCell(new Label(2,row,String.valueOf(voucherno)));
								wsheet.addCell(new Label(3,row,String.valueOf(0)));
								if(vos[j].getNmoney().compareTo(new UFDouble(0,2))<0)
								{
									wsheet.addCell(new Label(4, row, "���̻�����"));
								}else{
									wsheet.addCell(new Label(4, row, "�����̻�����"));
								}
								wsheet.addCell(new Label(5,row,hm.get(MPOS_GlobalVariable.ITEMCODE_DSFUND)));
								//wsheet.addCell(new Label(7,row,String.valueOf(vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP))));
								wsheet.addCell(new Number(7, row, vos[j].getNmoney().setScale(2, UFDouble.ROUND_HALF_UP).doubleValue(),wcellFormat));
								wsheet.addCell(new Label(11,row,ce.getUser().getUserName()));
								wsheet.addCell(new Label(14,row,String.valueOf(vos[j].getBilldate())));
								wsheet.addCell(new Label(17,row,vos[j].getCustcode()));
								row++;						
								
							}
						}
						// ��
						if (debit_sumcard.compareTo(new UFDouble(0)) > 0)//ˢ��
						{
							wsheet.addCell(new Label(0, row, vos[i].getBilldate().toString()));
							wsheet.addCell(new Label(1, row, "��"));
							wsheet.addCell(new Label(2, row, String.valueOf(voucherno)));
							wsheet.addCell(new Label(3, row, String.valueOf(0)));
							wsheet.addCell(new Label(4, row, "�����̻�����"));
							wsheet.addCell(new Label(5, row, hm.get(MPOS_GlobalVariable.ITEMCODE_BANKDEPOSIT)));
							wsheet.addCell(new Number(6, row, debit_sumcard.doubleValue(), wcellFormat));
							wsheet.addCell(new Label(11, row, ce.getUser().getUserName()));
							wsheet.addCell(new Label(14, row, String.valueOf(vos[i].getBilldate())));
							row++;
						}
						if (debit_sumcash.compareTo(new UFDouble(0)) > 0)//�ֽ�
						{
							wsheet.addCell(new Label(0, row, vos[i].getBilldate().toString()));
							wsheet.addCell(new Label(1, row, "��"));
							wsheet.addCell(new Label(2, row, String.valueOf(voucherno)));
							wsheet.addCell(new Label(3, row, String.valueOf(0)));
							wsheet.addCell(new Label(4, row, "�����̻�����"));
							wsheet.addCell(new Label(5, row, hm.get(MPOS_GlobalVariable.ITEMCODE_CASH)));
							wsheet.addCell(new Number(6, row, debit_sumcash.setScale(2, UFDouble.ROUND_HALF_UP).doubleValue(), wcellFormat));
							wsheet.addCell(new Label(11, row, ce.getUser().getUserName()));
							wsheet.addCell(new Label(14, row, String.valueOf(vos[i].getBilldate())));
							row++;
						}
						
						
						
						
						hmTemp.put(key, key);
						voucherno++;
					}
				}
				wwb.write();
				wwb.close();
				MPOS_PubManageItf imanage = (MPOS_PubManageItf) NCLocator.getInstance().lookup(MPOS_PubManageItf.class);
				for (int i = 0; i < vos.length; i++)
				{
					vos[i].setVoucher(MPOS_GlobalVariable.SALEDAYSUM_VOUCHER_HANDLED);
				}
				imanage.saveSaleVO(vos);
				super.doRefresh();
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

	
}
