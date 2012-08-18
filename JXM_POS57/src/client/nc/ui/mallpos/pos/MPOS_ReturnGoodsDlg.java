package nc.ui.mallpos.pos;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubManageItf;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.itf.uif.pub.IUifService;
import nc.ui.mallpos.pub.MPOS_BtnPanel;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UITextField;
import nc.vo.mallpos.pos.MPOS_SaleDetailBillVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailBodyVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailHeadVO;
import nc.vo.mallpos.pub.MPOS_BalaTypeConfigVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.tools.MPOS_Toolkit;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;

import org.apache.commons.lang.StringUtils;

/**
 * 退货-撤消-按销售单号查询
 * 
 * @author chenjun
 * @date 2012-1-4 下午04:15:29
 */
public class MPOS_ReturnGoodsDlg extends UIDialog implements ActionListener
{

	private static final long serialVersionUID = 1L;

	/**
	 * 查询条件面板
	 */
	private UIPanel queryPanel;

	private MPOS_ReturnGoodsCardPanel billPanel;

	/**
	 * 按钮面板
	 */
	private MPOS_BtnPanel btnPanel;

	/**
	 * 客户端参数
	 */
	public ClientEnvironment ce = ClientEnvironment.getInstance();

	private String saleno;

	public UILabel lblSaleno;

	private UITextField txtSaleno;

	private String modulecode;

	/**
	 * 构造函数
	 * 
	 * @param p_contain
	 *            所属容器
	 */
	public MPOS_ReturnGoodsDlg(Container p_contain, String p_modulecode)
	{
		super(p_contain, "退货");
		this.modulecode = p_modulecode;
		this.initDlg();
	}

	/**
	 * 初始化
	 * 
	 */
	private void initDlg()
	{
		setBounds(0, 0, 800, 600);
		this.add(this.getQueryPanel(), "North");
		this.add(this.getBillPanel(), "Center");
		this.add(this.getBtnPanel(), "South");
	}

	public UIPanel getQueryPanel()
	{
		if (queryPanel == null)
		{
			queryPanel = new UIPanel();
			queryPanel.setLayout(null);
			queryPanel.setPreferredSize(new Dimension(720, 50));
			queryPanel.add(getLblSaleno());
			queryPanel.add(getTxtSaleno());
			queryPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
		}
		return queryPanel;
	}

	/**
	 * @return the lblSaleno
	 */
	public UILabel getLblSaleno()
	{
		if (lblSaleno == null)
		{
			lblSaleno = new UILabel();
			lblSaleno.setName("lblSaleno");
			lblSaleno.setText("销售单号");
			lblSaleno.setFont(MPOS_GlobalVariable.font);
			lblSaleno.setForeground(Color.BLUE);
			lblSaleno.setBounds(20, 10, 100, 28);
		}
		return lblSaleno;
	}

	/**
	 * @return the txtSaleno
	 */
	public UITextField getTxtSaleno()
	{
		if (txtSaleno == null)
		{
			txtSaleno = new UITextField();
			txtSaleno.setName("txtSaleno");
			txtSaleno.setAdjustHight(true);
			txtSaleno.setBounds(125, 10, 230, 28);
			txtSaleno.setFont(MPOS_GlobalVariable.font);
			txtSaleno.addActionListener(this);
		}
		return txtSaleno;
	}

	/**
	 * @return the btnPanel
	 */
	public MPOS_BtnPanel getBtnPanel()
	{
		if (btnPanel == null)
		{
			btnPanel = new MPOS_BtnPanel(this.getWidth());
			btnPanel.getButtonOK().addActionListener(this);
			btnPanel.getButtonExit().addActionListener(this);
		}
		return btnPanel;
	}

	public void actionPerformed(ActionEvent arg0)
	{
		if (arg0.getSource() == this.getTxtSaleno())
		{
			try
			{
				doShowData();
			} catch (Exception e)
			{
				e.printStackTrace();
				MessageDialog.showErrorDlg(this, "错误", e.getMessage());
			}
		} else if (arg0.getSource() == this.getBtnPanel().getButtonExit())
		{
			closeCancel();
		} else if (arg0.getSource() == this.getBtnPanel().getButtonOK())
		{
			doOK();
		}
	}

	private void doOK()
	{
		MPOS_SaleDetailHeadVO hvo = this.getBillPanel().getHeadVO();
		if(hvo==null)
			return;
		String msg = this.getBillPanel().checkHeadNullValues();
		if(StringUtils.isNotEmpty(msg))
		{
			MessageDialog.showWarningDlg(this, "警告", msg);
			return;
		}
		try {
			//存放需要刷卡退货的数据
			ArrayList<MPOS_SaleDetailBodyVO> cardTradevos = new ArrayList<MPOS_SaleDetailBodyVO>();
			MPOS_SaleDetailBodyVO[] bvos = this.getBillPanel().getBodySelectVOsByCheckBox().toArray(new MPOS_SaleDetailBodyVO[0]);
			if(bvos!=null&&bvos.length>0)
			{
				IUifService iservice = (IUifService) NCLocator.getInstance().lookup(
						IUifService.class);
				//是否当日退货
				UFBoolean istoday_return = new UFBoolean(this.getBillPanel().getBillData().getHeadItem("istoday_return").getValueObject()==null?"N":"Y");
				MPOS_BalaTypeConfigVO[] btcvo = null;
				
				//
				
				for (int i = 0; i < bvos.length; i++) {
					if (bvos[i].getReceived_money()==null||bvos[i].getReceived_money().compareTo(new UFDouble(0)) == 0) {
						MessageDialog.showWarningDlg(this, "警告", "第" + (i + 1)
								+ "行退货金额不能为空!");
						return;
					}
					bvos[i].setSale_datetime(ClientEnvironment.getServerTime());
					bvos[i].setReceived_money(bvos[i].getReceived_money().multiply(-1));
					bvos[i].setRt_pk_saledetail(bvos[i].getPk_saledetail_b());
					//判断哪些对象需要刷卡退货
					if(istoday_return.booleanValue())
					{
						btcvo = (MPOS_BalaTypeConfigVO[])iservice.queryByCondition(MPOS_BalaTypeConfigVO.class, "pk_balatype='"+bvos[i].getPk_balatype()+"'");
						if(btcvo!=null&&btcvo.length>0)
						{
							if(btcvo[0].getIscallpos()!=null&&btcvo[0].getIscallpos().booleanValue())
								cardTradevos.add(bvos[i]);
						}else
						{
							MessageDialog.showErrorDlg(this, "错误", "第" + (i + 1)
									+ "行结算方式没有配置!");
							return;
						}
					}
					bvos[i].setPk_saledetail_b(null);
				}
			}else
			{
				MessageDialog.showHintDlg(this, "提示", "请选择要退货的数据!");
				return;
			}
			
			//如果需要刷卡退货的数据大于1或者需要刷卡退货的数据等于1但与表体选择行数不相等则不能完成退货
			if(cardTradevos.size()>1||(cardTradevos.size()==1&&cardTradevos.size()!=bvos.length))
			{
				MessageDialog.showWarningDlg(this, "警告", "银行卡退货只能单独逐笔操作!");
				return;
			}
		
			String pk_saledetail_h = hvo.getPk_saledetail_h();
			hvo.setPk_corp(ce.getCorporation().getPk_corp());
			hvo.setBill_date(ClientEnvironment.getServerTime().getDate());
			hvo.setSale_no(MPOS_Toolkit.createBillCode(ce.getCorporation().getPk_corp(), MPOS_GlobalVariable.POS_BILL_CODE));
			hvo.setSale_type(MPOS_GlobalVariable.SALETYPE_RETURN);
			hvo.setPk_cashier(ce.getUser().getPrimaryKey());
			hvo.setPk_saledetail_h(null);
			
			MPOS_PubManageItf imanage = (MPOS_PubManageItf)NCLocator.getInstance().lookup(MPOS_PubManageItf.class);
			
			MPOS_SaleDetailBillVO aggvo = new MPOS_SaleDetailBillVO();
			aggvo.setParentVO(hvo);
			aggvo.setTableVO(MPOS_SaleDetailBillVO.CHILD_TABLE_DETAIL,bvos);
			
			if(cardTradevos.size()==1)
			{
				if (MPOS_POSCall.repeal(cardTradevos.get(0)))
				{
					cardTradevos.get(0).setNfee(cardTradevos.get(0).getNfee().multiply(-1));
					cardTradevos.get(0).setSystracdno(null);
					imanage.saveSaleDetailAggExVO(aggvo);
				}
			}else
			{
				imanage.saveSaleDetailAggExVO(aggvo);
			}
			this.refreshBodyTable(pk_saledetail_h);
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.showWarningDlg(this, "错误", "退货出错："+e.getMessage());
		}
	}

	/**
	 * 根据销售单号查询数据
	 *
	 */
	private void doShowData()
	{
		this.saleno = this.getTxtSaleno().getText();
		if (StringUtils.isEmpty(this.saleno))
		{
			MessageDialog.showErrorDlg(this, "错误", "请输入销售单号！");
			return;
		}
		try
		{
			IUifService iservice = (IUifService) NCLocator.getInstance().lookup(
					IUifService.class);
			MPOS_SaleDetailHeadVO[] hvo = (MPOS_SaleDetailHeadVO[])iservice.queryByCondition(MPOS_SaleDetailHeadVO.class, " sale_no='"+saleno+"'");
			if(hvo==null||hvo.length<=0)
			{
				MessageDialog.showErrorDlg(this, "提示", "没有查询到相关销售记录或已经退货！");
				this.getBtnPanel().getButtonOK().setEnabled(false);
				return;
			}else
				this.getBtnPanel().getButtonOK().setEnabled(true);
			String ago_proof_billno = hvo[0].getProof_billno();
			hvo[0].setProof_billno(null);
			this.getBillPanel().setHeadVO(hvo[0]);
			this.getBillPanel().getBillData().setHeadItem("ago_proof_billno", ago_proof_billno);
			
			if (hvo[0].getBill_date().compareTo(ClientEnvironment.getServerTime().getDate()) == 0)
			{
				this.getBillPanel().getBillData().setHeadItem("istoday_return", "Y");
			}
			this.refreshBodyTable(hvo[0].getPk_saledetail_h());
		} catch (Exception e)
		{
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		}

	}
	
	/**
	 * 刷新表体
	 * @param pk_saledetail_h
	 * @throws Exception
	 */
	private void refreshBodyTable(String pk_saledetail_h)throws Exception
	{
		MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(MPOS_PubQueryItf.class);
		MPOS_SaleDetailBodyVO[] bvos = iquery.queryReturnGoodsByHPK(pk_saledetail_h);
		if(bvos!=null&&bvos.length>0)
		{
			//清空实收金额即退货金额
			for (MPOS_SaleDetailBodyVO bodyVO : bvos) {
				bodyVO.setReceived_money(null);
			}
		}else
			this.getBtnPanel().getButtonOK().setEnabled(false);
		this.getBillPanel().fillGrid(bvos);
	}

	/**
	 * @return the billPanel
	 */
	public MPOS_ReturnGoodsCardPanel getBillPanel()
	{
		if (billPanel == null)
		{
			billPanel = new MPOS_ReturnGoodsCardPanel();
		}
		return billPanel;
	}


}
