package nc.ui.mallpos.pos;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

import nc.bs.framework.common.NCLocator;
import nc.itf.mallpos.pub.MPOS_PubManageItf;
import nc.itf.mallpos.pub.MPOS_PubQueryItf;
import nc.itf.mallpos.pub.MPOS_PubVipItf;
import nc.itf.uif.pub.IUifService;
import nc.ui.mallpos.pub.MPOS_BtnPanel;
import nc.ui.mallpos.pub.ref.MPOS_VipcardRefModel;
import nc.ui.pub.ClientEnvironment;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIComboBox;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UILabel;
import nc.ui.pub.beans.UIPanel;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.beans.UITextField;
import nc.ui.pub.beans.ValueChangedEvent;
import nc.ui.pub.beans.ValueChangedListener;
import nc.ui.pub.beans.textfield.UITextType;
import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.para.SysInitBO_Client;
import nc.vo.bd.ref.RefNodeNameConst;
import nc.vo.mallpos.pos.MPOS_CustdocVO;
import nc.vo.mallpos.pos.MPOS_DiscountTempVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailBillVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailBodyVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailHeadVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailShareVO;
import nc.vo.mallpos.pub.MPOS_BalaTypeConfigVO;
import nc.vo.mallpos.pub.MPOS_GlobalVariable;
import nc.vo.mallpos.tools.MPOS_Toolkit;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.trade.voutils.VOUtil;

import org.apache.commons.lang.StringUtils;

/**
 * 收银对话框
 * 
 * @author chenjun
 * @date 2012-1-3 下午09:22:29
 */
public class MPOS_ExpenseDlg extends UIDialog implements ActionListener,
		ValueChangedListener, BillEditListener {

	private static final long serialVersionUID = 1L;

	/**
	 * 主面板
	 */
	private UIPanel mainPanel;

	/**
	 * 按钮面板
	 */
	private MPOS_BtnPanel btnPanel;

	private MPOS_SaleDetailBodyPanel billPanel;

	private MPOS_SaleDiscountBillPanel disPanel;

	/**
	 * 会员信息面板
	 */
	private UIPanel vipPanel;

	/**
	 * 客户端参数
	 */
	public ClientEnvironment ce = ClientEnvironment.getInstance();

	private String modulecode;

	private UILabel lblProof_billno;

	private UITextField txtProof_billno;

	private UILabel lblCustCode;

	private UIRefPane refCustCode;

	private UILabel lblCustName;

	private UITextField txtCustName;

	private UILabel lblBunkno;

	private UITextField txtBunkno;

	private UILabel lblPayment_Type;

	private UIRefPane refPayment_Type;

	// 应收
	private UILabel lblReceMoney;

	private UITextField txtReceMoney;

	// 实收
	private UILabel lblPaidinMoney;

	private UITextField txtPaidinMoney;

	// 积分输入
	private UILabel lblGradeInput;

	private UITextField txtGradeInput;

	// 余额
	private UILabel lblBalanceMoney;

	private UITextField txtBalanceMoney;

	// 找零
	private UILabel lblChangeMoney;

	private UITextField txtChangeMoney;

	// POS端口
	private UILabel lblPOSCOM;

	private UIComboBox cbxPOSCOM;

	private MPOS_CustdocVO m_custvo;

	/**
	 * 各项的Y坐标
	 */
	private int item_pos_y = 10;

	private int item_vip_y = 30;

	/**
	 * 各项的Y坐标增量
	 */
	private int item_pos_step = 35;

	private int item_vip_step = 35;

	/**
	 * 输入项宽度
	 */
	private int item_pos_width = 150;

	// ***********会员信息*****************
	private UILabel lblVIPCard;

	private UIRefPane refVIPCard;

	private UILabel lblVIPName;

	private UITextField txtVIPName;

	// 上次累计
	private UILabel lblSumGrade;

	private UITextField txtSumGrade;

	// 本次积分
	private UILabel lblThisGrade;

	private UITextField txtThisGrade;

	// 本次累计
	private UILabel lblThisSumGrade;

	private UITextField txtThisSumGrade;

	private MPOS_PubQueryItf iquery;

	/**
	 * 折前金额，主要为了保存多次折扣前的余额
	 */
	private UITextField txtBeDisBalaMoney;

	/**
	 * 存放计算分摊数据
	 */
	private ArrayList<MPOS_SaleDetailShareVO> sharevos = new ArrayList<MPOS_SaleDetailShareVO>();

	/**
	 * 构造函数
	 * 
	 * @param p_contain
	 *            所属容器
	 */
	public MPOS_ExpenseDlg(Container p_contain, String p_modulecode) {
		super(p_contain, "收银前台");
		this.modulecode = p_modulecode;
		this.initDlg();
	}

	/**
	 * 初始化
	 * 
	 */
	private void initDlg() {
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setBounds(0, 0, getParent().getWidth() - 100,
				getParent().getHeight() - 100);
		this.add(this.getMainPanel(), "North");
		this.add(this.getBillPanel(), "Center");
		this.add(this.getBtnPanel(), "South");
		addVIPCardKeyAction();

		iquery = (MPOS_PubQueryItf) NCLocator.getInstance().lookup(
				MPOS_PubQueryItf.class);
	}

	/**
	 * 给会员卡添加键盘事件,按F3获得焦点
	 * 
	 */
	private void addVIPCardKeyAction() {
		KeyStroke stroke = KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0);
		InputMap inputMap = getMainPanel().getInputMap(
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(stroke, KeyEvent.VK_F3);
		getMainPanel().getActionMap().put(KeyEvent.VK_F3, new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				// 按F3获得焦点
				getRefVIPCard().getUITextField().setFocusable(true);
				getRefVIPCard().getUITextField().requestFocus();
			}
		});
	}

	public UIPanel getMainPanel() {
		if (mainPanel == null) {
			mainPanel = new UIPanel();
			mainPanel.setLayout(null);
			mainPanel.setPreferredSize(new Dimension(600, 270));

			mainPanel.add(getLblCustCode());
			mainPanel.add(getRefCustCode());

			// item_pos_y+=item_pos_step;
			// mainPanel.add(getLblCustName());
			// mainPanel.add(getTxtCustName());
			//
			// item_pos_y+=item_pos_step;
			// mainPanel.add(getLblBunkno());
			// mainPanel.add(getTxtBunkno());

			item_pos_y += item_pos_step;
			mainPanel.add(getLblProof_billno());
			mainPanel.add(getTxtProof_billno());

			item_pos_y += item_pos_step;
			mainPanel.add(getLblReceMoney());
			mainPanel.add(getTxtReceMoney());

			item_pos_y += item_pos_step;
			mainPanel.add(getLblBalanceMoney());
			mainPanel.add(getTxtBalanceMoney());

			mainPanel.add(getTxtBeDisBalaMoney());

			item_pos_y += item_pos_step;
			mainPanel.add(getLblPayment_Type());
			mainPanel.add(getRefPayment_Type());

			item_pos_y += item_pos_step;
			mainPanel.add(getLblPaidinMoney());
			mainPanel.add(getTxtPaidinMoney());
			mainPanel.add(getLblGradeInput());
			mainPanel.add(getTxtGradeInput());

			item_pos_y += item_pos_step;
			mainPanel.add(getLblChangeMoney());
			mainPanel.add(getTxtChangeMoney());

			mainPanel.add(getLblPOSCOM());
			mainPanel.add(getCbxPOSCOM());

			mainPanel.add(this.getVipPanel());

			mainPanel.add(this.getDisPanel());
		}
		return mainPanel;
	}

	/**
	 * @return the lblCustCode
	 */
	public UILabel getLblCustCode() {
		if (lblCustCode == null) {
			lblCustCode = new UILabel();
			lblCustCode.setName("lblCustCode");
			lblCustCode.setText("商户编码");
			lblCustCode.setFont(MPOS_GlobalVariable.font);
			lblCustCode.setBounds(20, item_pos_y, 100, 28);
			lblCustCode.setForeground(Color.BLUE);
		}
		return lblCustCode;
	}

	/**
	 * @return the refCustCode
	 */
	public UIRefPane getRefCustCode() {
		if (refCustCode == null) {
			refCustCode = new UIRefPane();
			refCustCode.setName("refCustCode");
			refCustCode.setAdjustHight(true);
			refCustCode.setBounds(125, item_pos_y, 371, 28);
			refCustCode.getUITextField().setFont(MPOS_GlobalVariable.font);
			refCustCode
					.setRefNodeName(RefNodeNameConst.CUSTDOC_CUSTOMERANDSUPPLIER);
			refCustCode.getUITextField().addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					// doShowCust();
					if (StringUtils.isEmpty(getRefCustCode().getRefName())) {
						// 加上此句，没选客商点击【确定】的话，按钮无法获得焦点
						// refCustCode.getUITextField().grabFocus();
					}
					getTxtChangeMoney().setText(null);
					getBillPanel().getBillModel().clearBodyData();
				}
			});
			refCustCode.addValueChangedListener(this);
		}
		return refCustCode;
	}

	/**
	 * @return the lblCustName
	 */
	public UILabel getLblCustName() {
		if (lblCustName == null) {
			lblCustName = new UILabel();
			lblCustName.setName("lblCustName");
			lblCustName.setText("商户名称");
			lblCustName.setFont(MPOS_GlobalVariable.font);
			lblCustName.setBounds(20, item_pos_y, 100, 28);
		}
		return lblCustName;
	}

	/**
	 * @return the txtCustName
	 */
	public UITextField getTxtCustName() {
		if (txtCustName == null) {
			txtCustName = new UITextField();
			txtCustName.setName("txtCustName");
			txtCustName.setAdjustHight(true);
			txtCustName.setBounds(125, item_pos_y, 400, 28);
			txtCustName.setFont(MPOS_GlobalVariable.font);
			txtCustName.setForeground(Color.RED);
			txtCustName.setEditable(false);
			txtCustName.setFocusable(false);
		}
		return txtCustName;
	}

	/**
	 * @return the lblBunkno
	 */
	public UILabel getLblBunkno() {
		if (lblBunkno == null) {
			lblBunkno = new UILabel();
			lblBunkno.setName("lblBunkno");
			lblBunkno.setText("铺位号");
			lblBunkno.setFont(MPOS_GlobalVariable.font);
			lblBunkno.setBounds(20, item_pos_y, 100, 28);
		}
		return lblBunkno;
	}

	/**
	 * @return the txtBunkno
	 */
	public UITextField getTxtBunkno() {
		if (txtBunkno == null) {
			txtBunkno = new UITextField();
			txtBunkno.setName("txtBunkno");
			txtBunkno.setAdjustHight(true);
			txtBunkno.setBounds(125, item_pos_y, 230, 28);
			txtBunkno.setFont(MPOS_GlobalVariable.font);
			txtBunkno.setEditable(false);
			txtBunkno.setFocusable(false);
		}
		return txtBunkno;
	}

	/**
	 * 获取lblProof_billno
	 * 
	 * @return lblProof_billno lblProof_billno
	 */
	public UILabel getLblProof_billno() {
		if (lblProof_billno == null) {
			lblProof_billno = new UILabel();
			lblProof_billno.setName("lblProof_billno");
			lblProof_billno.setText("交款单号");
			lblProof_billno.setFont(MPOS_GlobalVariable.font);
			lblProof_billno.setBounds(20, item_pos_y, 100, 28);
			lblProof_billno.setForeground(Color.BLUE);
		}
		return lblProof_billno;
	}

	/**
	 * 获取txtProof_billno
	 * 
	 * @return txtProof_billno txtProof_billno
	 */
	public UITextField getTxtProof_billno() {
		if (txtProof_billno == null) {
			txtProof_billno = new UITextField();
			txtProof_billno.setName("txtProof_billno");
			txtProof_billno.setAdjustHight(true);
			txtProof_billno.setBounds(125, item_pos_y, item_pos_width, 28);
			txtProof_billno.setFont(MPOS_GlobalVariable.font);
			txtProof_billno.setMaxLength(15);
			txtProof_billno.addActionListener(this);
		}
		return txtProof_billno;
	}

	/**
	 * @return the lblReceMoney
	 */
	public UILabel getLblReceMoney() {
		if (lblReceMoney == null) {
			lblReceMoney = new UILabel();
			lblReceMoney.setName("lblReceMoney");
			lblReceMoney.setText("应收金额");
			lblReceMoney.setFont(MPOS_GlobalVariable.font);
			lblReceMoney.setBounds(20, item_pos_y, 100, 28);
			lblReceMoney.setForeground(Color.BLUE);
		}
		return lblReceMoney;
	}

	/**
	 * @return the txtReceMoney
	 */
	public UITextField getTxtReceMoney() {
		if (txtReceMoney == null) {
			txtReceMoney = new UITextField();
			txtReceMoney.setName("txtReceMoney");
			txtReceMoney.setAdjustHight(true);
			txtReceMoney.setBounds(125, item_pos_y, item_pos_width, 28);
			txtReceMoney.setFont(MPOS_GlobalVariable.font);
			txtReceMoney.setNumPoint(2);// 小数位数
			txtReceMoney.setTextType("TextDbl");// Double型
			txtReceMoney.addActionListener(this);
			txtReceMoney.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					getTxtBalanceMoney().setText(getTxtReceMoney().getText());
					getTxtBeDisBalaMoney().setText(getTxtReceMoney().getText());
				}
			});
		}
		return txtReceMoney;
	}

	/**
	 * 获取lblGradeInput
	 * 
	 * @return lblGradeInput lblGradeInput
	 */
	public UILabel getLblGradeInput() {
		if (lblGradeInput == null) {
			lblGradeInput = new UILabel();
			lblGradeInput.setName("lblGradeInput");
			lblGradeInput.setText("折现积分");
			lblGradeInput.setFont(MPOS_GlobalVariable.font);
			lblGradeInput.setBounds(290, item_pos_y, 100, 28);
		}
		return lblGradeInput;
	}

	/**
	 * 获取txtGradeInput
	 * 
	 * @return txtGradeInput txtGradeInput
	 */
	public UITextField getTxtGradeInput() {
		if (txtGradeInput == null) {
			txtGradeInput = new UITextField();
			txtGradeInput.setName("txtGradeInput");
			txtGradeInput.setAdjustHight(true);
			txtGradeInput.setBounds(395, item_pos_y, 100, 28);
			txtGradeInput.setFont(MPOS_GlobalVariable.font);
			txtGradeInput.setNumPoint(2);// 小数位数
			txtGradeInput.setTextType("TextDbl");// Double型
			txtGradeInput.addActionListener(this);
			txtGradeInput.setFocusable(false);
		}
		return txtGradeInput;
	}

	/**
	 * @return the lblPayment_Type
	 */
	public UILabel getLblPayment_Type() {
		if (lblPayment_Type == null) {
			lblPayment_Type = new UILabel();
			lblPayment_Type.setName("lblPayment_Type");
			lblPayment_Type.setText("结算方式");
			lblPayment_Type.setFont(MPOS_GlobalVariable.font);
			lblPayment_Type.setBounds(20, item_pos_y, 100, 28);
			lblPayment_Type.setForeground(Color.BLUE);
		}
		return lblPayment_Type;
	}

	/**
	 * @return the refPayment_Type
	 */
	public UIRefPane getRefPayment_Type() {
		if (refPayment_Type == null) {
			refPayment_Type = new UIRefPane();
			refPayment_Type.setName("refPayment_Type");
			refPayment_Type.setAdjustHight(true);
			refPayment_Type.setBounds(125, item_pos_y, item_pos_width, 28);
			refPayment_Type.getUITextField().setFont(MPOS_GlobalVariable.font);
			refPayment_Type.setRefNodeName(RefNodeNameConst.BALANCETYPE);
			refPayment_Type.getRefModel().addWherePart(
					" and balancode like '0%'");
			refPayment_Type.getUITextField().addFocusListener(
					new FocusAdapter() {
						@Override
						public void focusLost(FocusEvent e) {
							if (StringUtils.isEmpty(refPayment_Type
									.getRefCode())) {
								// refPayment_Type.getUITextField().requestFocus();
							}
							// getTxtGradeInput().setText(null);
							// // 如果结算方式为积分折现
							// if (MPOS_GlobalVariable.BALANCETYPE_GRADE_DISCASH
							// .equals(getRefPayment_Type().getRefCode())) {
							// getTxtPaidinMoney().setFocusable(false);
							// getTxtGradeInput().setFocusable(true);
							// getTxtGradeInput().grabFocus();
							// } else {
							// getTxtPaidinMoney().setFocusable(true);
							// getTxtGradeInput().setFocusable(false);
							// }
						}

					});
			refPayment_Type.setButtonFireEvent(true);// 按钮选择数据后是否触发ValueChanged事件
			refPayment_Type.addValueChangedListener(this);
		}
		return refPayment_Type;
	}

	/**
	 * @return the lblPaidinMoney
	 */
	public UILabel getLblPaidinMoney() {
		if (lblPaidinMoney == null) {
			lblPaidinMoney = new UILabel();
			lblPaidinMoney.setName("lblPaidinMoney");
			lblPaidinMoney.setText("本次实收");
			lblPaidinMoney.setFont(MPOS_GlobalVariable.font);
			lblPaidinMoney.setBounds(20, item_pos_y, 100, 28);
			lblPaidinMoney.setForeground(Color.BLUE);
		}
		return lblPaidinMoney;
	}

	/**
	 * @return the txtPaidinMoney
	 */
	public UITextField getTxtPaidinMoney() {
		if (txtPaidinMoney == null) {
			txtPaidinMoney = new UITextField();
			txtPaidinMoney.setName("txtReceMoney");
			txtPaidinMoney.setAdjustHight(true);
			txtPaidinMoney.setBounds(125, item_pos_y, item_pos_width, 28);
			txtPaidinMoney.setFont(MPOS_GlobalVariable.font);
			txtPaidinMoney.setNumPoint(2);// 小数位数
			txtPaidinMoney.setTextType("TextDbl");// Double型
			txtPaidinMoney.addActionListener(this);
		}
		return txtPaidinMoney;
	}

	/**
	 * @return the lblBalanceMoney
	 */
	public UILabel getLblBalanceMoney() {
		if (lblBalanceMoney == null) {
			lblBalanceMoney = new UILabel();
			lblBalanceMoney.setName("lblBalanceMoney");
			lblBalanceMoney.setText("应收余额");
			lblBalanceMoney.setFont(MPOS_GlobalVariable.font);
			lblBalanceMoney.setBounds(20, item_pos_y, 100, 28);

		}
		return lblBalanceMoney;
	}

	/**
	 * @return the txtBalanceMoney
	 */
	public UITextField getTxtBalanceMoney() {
		if (txtBalanceMoney == null) {
			txtBalanceMoney = new UITextField();
			txtBalanceMoney.setName("txtBalanceMoney");
			txtBalanceMoney.setAdjustHight(true);
			txtBalanceMoney.setBounds(125, item_pos_y, item_pos_width, 28);
			txtBalanceMoney.setFont(MPOS_GlobalVariable.font);
			txtBalanceMoney.setNumPoint(2);// 小数位数
			txtBalanceMoney.setTextType("TextDbl");// Double型
			txtBalanceMoney.setEditable(false);
			txtBalanceMoney.setFocusable(false);
		}
		return txtBalanceMoney;
	}

	/**
	 * 获取折前金额，主要为了保存多次折扣前的余额
	 * 
	 * @return txtBeDisBalaMoney 折前金额，主要为了保存多次折扣前的余额
	 */
	public UITextField getTxtBeDisBalaMoney() {
		if (txtBeDisBalaMoney == null) {
			txtBeDisBalaMoney = new UITextField();
			txtBeDisBalaMoney.setName("txtBeDisBalaMoney");
			txtBeDisBalaMoney.setAdjustHight(true);
			txtBeDisBalaMoney.setBounds(395, item_pos_y, 100, 28);
			txtBeDisBalaMoney.setFont(MPOS_GlobalVariable.font);
			txtBeDisBalaMoney.setNumPoint(2);// 小数位数
			txtBeDisBalaMoney.setTextType("TextDbl");// Double型
			txtBeDisBalaMoney.setEditable(false);
			txtBeDisBalaMoney.setFocusable(false);

			txtBeDisBalaMoney.setVisible(false);
		}
		return txtBeDisBalaMoney;
	}

	/**
	 * @return the lblChangeMoney
	 */
	public UILabel getLblChangeMoney() {
		if (lblChangeMoney == null) {
			lblChangeMoney = new UILabel();
			lblChangeMoney.setName("lblChangeMoney");
			lblChangeMoney.setText("找零金额");
			lblChangeMoney.setFont(MPOS_GlobalVariable.font);
			lblChangeMoney.setBounds(20, item_pos_y, 100, 28);

		}
		return lblChangeMoney;
	}

	/**
	 * @return the txtChangeMoney
	 */
	public UITextField getTxtChangeMoney() {
		if (txtChangeMoney == null) {
			txtChangeMoney = new UITextField();
			txtChangeMoney.setName("txtChangeMoney");
			txtChangeMoney.setAdjustHight(true);
			txtChangeMoney.setBounds(125, item_pos_y, item_pos_width, 28);
			txtChangeMoney.setFont(MPOS_GlobalVariable.font);
			txtChangeMoney.setNumPoint(2);// 小数位数
			txtChangeMoney.setTextType("TextDbl");// Double型
			txtChangeMoney.setEditable(false);
			txtChangeMoney.setFocusable(false);
			txtChangeMoney.setForeground(Color.RED);
		}
		return txtChangeMoney;
	}

	/**
	 * 获取lblPOSCOM
	 * 
	 * @return lblPOSCOM lblPOSCOM
	 */
	public UILabel getLblPOSCOM() {
		if (lblPOSCOM == null) {
			lblPOSCOM = new UILabel();
			lblPOSCOM.setName("lblPOSCOM");
			lblPOSCOM.setText("POS端口");
			lblPOSCOM.setFont(MPOS_GlobalVariable.font);
			lblPOSCOM.setBounds(290, item_pos_y, 100, 28);
		}
		return lblPOSCOM;
	}

	/**
	 * 获取cbxPOSCOM
	 * 
	 * @return cbxPOSCOM cbxPOSCOM
	 */
	public UIComboBox getCbxPOSCOM() {
		if (cbxPOSCOM == null) {
			cbxPOSCOM = new UIComboBox();
			cbxPOSCOM.setName("cbxPOSCOM");
			cbxPOSCOM.setAdjustHight(true);
			cbxPOSCOM.setBounds(395, item_pos_y, 100, 28);
			cbxPOSCOM.setFont(MPOS_GlobalVariable.font);
			cbxPOSCOM.setFocusable(false);
			String[] items = { "", "端口1", "端口2" };
			cbxPOSCOM.addItems(items);

			cbxPOSCOM.addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					// 失去焦点时使其不能获得焦点
					cbxPOSCOM.setFocusable(false);
					super.focusLost(e);
				}
			});
		}
		return cbxPOSCOM;
	}

	/**
	 * @return the btnPanel
	 */
	public MPOS_BtnPanel getBtnPanel() {
		if (btnPanel == null) {
			btnPanel = new MPOS_BtnPanel(this.getWidth());
			btnPanel.getButtonOK().addActionListener(this);
			btnPanel.getButtonExit().addActionListener(this);
		}
		return btnPanel;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == this.getBtnPanel().getButtonOK()) {
			try {
				doOK();
			} catch (Exception e) {
				e.printStackTrace();
				MessageDialog.showErrorDlg(this, "错误", e.getMessage());
			}
		} else if (arg0.getSource() == this.getBtnPanel().getButtonExit()) {
			closeCancel();
		} else if (arg0.getSource() == this.getTxtReceMoney()) {
			if (StringUtils.isEmpty(this.getTxtReceMoney().getText())) {
				this.getTxtReceMoney().grabFocus();
				return;
			}
			this.getTxtBalanceMoney().setText(this.getTxtReceMoney().getText());
			this.getTxtBeDisBalaMoney().setText(
					this.getTxtReceMoney().getText());
		} else if (arg0.getSource() == this.getTxtPaidinMoney()) {
			if (StringUtils.isEmpty(this.getTxtPaidinMoney().getText())) {
				this.getTxtPaidinMoney().grabFocus();
				return;
			}
			if (!checkReceivedMoney())
				return;
			this.getBtnPanel().getButtonOK().requestFocus();// 使确定按钮获得焦点
		} else if (arg0.getSource() == this.getTxtProof_billno()) {
			if (StringUtils.isEmpty(this.getTxtProof_billno().getText())) {
				this.getTxtProof_billno().grabFocus();
				return;
			}
		} else if (arg0.getSource() == this.getTxtGradeInput()) {
			if (StringUtils.isEmpty(this.getTxtGradeInput().getText())) {
				this.getTxtGradeInput().grabFocus();
				return;
			}
			this.getBtnPanel().getButtonOK().requestFocus();// 使确定按钮获得焦点
			try {
				String value = SysInitBO_Client.getParaString(ce
						.getCorporation().getPk_corp(),
						MPOS_GlobalVariable.PARAM_CODE_DISCASHRULE);
				if (StringUtils.isEmpty(value)) {
					MessageDialog.showErrorDlg(this, "错误", "积分折现参数没有配置，请检查!");
					return;
				} else {
					this.getTxtPaidinMoney().setText(
							new UFDouble(value).multiply(
									new UFDouble(
											StringUtils.isEmpty(this
													.getTxtGradeInput()
													.getText()) ? "0" : this
													.getTxtGradeInput()
													.getText())).toString());
				}
			} catch (BusinessException e) {
				MessageDialog.showErrorDlg(this, "错误", "积分折现参数读取错误："
						+ e.getMessage());
			}
		}
	}

	@Override
	public void closeCancel() {
		if (doBeforeClose())
			super.closeCancel();
	}

	/**
	 * 关闭窗口之前检查是否收银完成
	 * 
	 * @return true:可以关闭,false:不关闭
	 */
	private boolean doBeforeClose() {
		boolean canClose = true;
		if (StringUtils.isNotEmpty(this.getTxtBalanceMoney().getText())) {
			if (MessageDialog.showYesNoDlg(this, "询问", "还未收银完成，是否确认退出？",
					MessageDialog.ID_NO) == MessageDialog.ID_YES) {
				if (MessageDialog.showYesNoDlg(this, "询问", "是否保存本次收银记录？",
						MessageDialog.ID_NO) == MessageDialog.ID_YES) {
					try {
						doSave();
					} catch (Exception e) {
						e.printStackTrace();
						MessageDialog.showErrorDlg(this, "错误", e.getMessage());
					}
				}
			} else {
				canClose = false;
			}
		}
		return canClose;
	}

	/**
	 * 检查实收金额
	 */
	private boolean checkReceivedMoney() {
		if (StringUtils.isNotEmpty(this.getTxtPaidinMoney().getText())) {
			UFDouble paidinmoney = new UFDouble(this.getTxtPaidinMoney()
					.getText(), 2);
			UFDouble balance = new UFDouble(
					this.getTxtBalanceMoney().getText(), 2);
			if (paidinmoney.compareTo(new UFDouble(0)) <= 0) {
				MessageDialog.showErrorDlg(this, "错误", "实收金额输入有误，请重新输入！");
				this.getTxtPaidinMoney().setText(null);
				return false;
			}
			// 判断加入是否银行卡是因为现金存在找零的情况，银行卡不存在
			if (!MPOS_GlobalVariable.BALANCETYPE_CASH.equals(this
					.getRefPayment_Type().getRefCode())) {
				if (paidinmoney.compareTo(balance) > 0) {
					MessageDialog.showErrorDlg(this, "错误", "实收金额输入有误，请重新输入！");
					this.getTxtPaidinMoney().setText(null);
					if (MPOS_GlobalVariable.BALANCETYPE_GRADE_DISCASH
							.equals(this.getRefPayment_Type().getRefCode())) {
						this.getTxtGradeInput().setText(null);
						this.getTxtGradeInput().grabFocus();
					} else {
						this.getTxtPaidinMoney().grabFocus();
					}
					return false;
				}
			}
		}
		return true;
	}

	private void doShowCust() {
		if (StringUtils.isEmpty(this.getRefCustCode().getText())) {
			return;
		}
		MPOS_PubQueryItf iquery = (MPOS_PubQueryItf) NCLocator.getInstance()
				.lookup(MPOS_PubQueryItf.class);
		try {
			this.getTxtBalanceMoney().setText(null);
			this.getTxtChangeMoney().setText(null);
			this.getBillPanel().getBillModel().clearBodyData();
			MPOS_CustdocVO custvo = iquery.queryByCode(this.getRefCustCode()
					.getText());
			if (custvo != null) {
				this.getTxtCustName().setText(custvo.getCustname());
				this.getTxtBunkno().setText(custvo.getBunkno());
				m_custvo = custvo;
			} else {
				MessageDialog.showHintDlg(this, "提示", "沒有找到該商戶信息！");
				this.resetItemValue();
			}
		} catch (Exception e) {
			e.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", e.getMessage());
		}
	}

	/**
	 * 确定
	 * 
	 */
	private void doOK() {
		// closeOK();
		if (getRefCustCode().getRefCode() == null) {
			MessageDialog.showWarningDlg(this, "警告", "输入商户不存在！");
			refCustCode.getUITextField().grabFocus();
			return;
		} else if (StringUtils.isEmpty(this.getTxtProof_billno().getText())) {
			MessageDialog.showWarningDlg(this, "警告", "请输入交款单号！");
			getTxtProof_billno().grabFocus();
			return;
		} else if (StringUtils.isEmpty(this.getTxtReceMoney().getText())) {
			MessageDialog.showWarningDlg(this, "警告", "请输入应收金额！");
			return;
		}

		try {
			UFDouble paidinmoney = new UFDouble(StringUtils.isEmpty(this
					.getTxtPaidinMoney().getText()) ? "0" : this
					.getTxtPaidinMoney().getText(), 2);// 实收
			UFDouble balance = new UFDouble(StringUtils.isEmpty(this
					.getTxtBalanceMoney().getText()) ? "0" : this
					.getTxtBalanceMoney().getText(), 2);// 余额

			if (balance.compareTo(new UFDouble(0)) < 0) {
				MessageDialog.showWarningDlg(this, "警告", "应收余额有误，请检查！");
				return;
			}

			if (balance.compareTo(new UFDouble(0)) == 0) {
				this.doSave();
				return;
			}

			if (StringUtils.isNotEmpty(this.getTxtBalanceMoney().getText())
					&& StringUtils.isEmpty(this.getTxtPaidinMoney().getText())) {
				MessageDialog.showWarningDlg(this, "警告", "请输入实收金额！");
				return;
			} else if (new UFDouble(this.getTxtReceMoney().getText(), 2)
					.compareTo(new UFDouble(0, 2)) == 0) {
				MessageDialog.showWarningDlg(this, "警告", "应收金额输入有误，请重新输入！");
				return;
			} else if (StringUtils.isEmpty(this.getRefPayment_Type()
					.getRefCode())) {
				MessageDialog.showWarningDlg(this, "警告", "请选择结算方式！");
				return;
			}

			if (!checkDisCash())
				return;

			if (!checkReceivedMoney())
				return;
			if (this.getTxtReceMoney().getText().equals(
					this.getTxtBalanceMoney().getText()))// 当应收与余额相等时取单据号，即第一次付时
			{
				this.getRefCustCode().setEnabled(false);
				this.getTxtProof_billno().setEnabled(false);
				this.getTxtReceMoney().setEnabled(false);
			}

			MPOS_SaleDetailBodyVO bvo = new MPOS_SaleDetailBodyVO();
			bvo.setSale_datetime(ClientEnvironment.getServerTime());
			bvo.setPk_balatype(this.getRefPayment_Type().getRefPK());
			bvo.setBalanname(this.getRefPayment_Type().getRefName());
			if (new UFDouble(this.getTxtBalanceMoney().getText(), 2).sub(
					new UFDouble(this.getTxtPaidinMoney().getText(), 2))
					.compareTo(new UFDouble(0)) < 0) {
				bvo.setReceived_money(balance);
			} else {
				bvo.setReceived_money(paidinmoney);
			}

			// 积分折现所用积分
			if (StringUtils.isNotEmpty(this.getTxtGradeInput().getText())) {
				bvo.setDiscash_grade(new UFDouble(this.getTxtGradeInput()
						.getText()));
			}

			// 银行卡结算
			IUifService iservice = (IUifService) NCLocator.getInstance()
					.lookup(IUifService.class);
			MPOS_BalaTypeConfigVO[] cvos = (MPOS_BalaTypeConfigVO[]) iservice
					.queryByCondition(MPOS_BalaTypeConfigVO.class,
							"pk_balatype='"
									+ this.getRefPayment_Type().getRefPK()
									+ "'");

			if (cvos != null && cvos.length > 0) {
				if (cvos[0].getIscallpos() != null
						&& cvos[0].getIscallpos().booleanValue()) {
					if (this.getCbxPOSCOM().getSelectedIndex() == 0) {
						bvo = MPOS_POSCall.expense(bvo);
					} else {
						bvo = MPOS_POSCall.expense(bvo, this.getCbxPOSCOM()
								.getSelectedIndex());
					}

					if (bvo == null) {
						return;
					}
				}
			} else {
				MessageDialog
						.showErrorDlg(this, "错误", "【"
								+ this.getRefPayment_Type().getRefName()
								+ "】结算方式没有配置!");
				return;
			}

			// MessageDialog.showHintDlg(this, "提示", "操作成功！");
			if (balance.sub(paidinmoney).compareTo(new UFDouble(0)) < 0)// 最后一次要找零
			{
				this.getTxtChangeMoney().setText(
						balance.sub(paidinmoney).toString());
				this.getTxtBalanceMoney().setText("0");
				this.getTxtBeDisBalaMoney().setText("0");
			} else {
				this.getTxtBalanceMoney().setText(
						balance.sub(paidinmoney).abs().toString());
				this.getTxtBeDisBalaMoney().setText(
						this.getTxtBalanceMoney().getText());
				// this.getTxtPaidinMoney().setText(balance.sub(paidinmoney).abs().toString());//默认为应收余额
				this.getTxtGradeInput().setText(null);
				this.getTxtPaidinMoney().setText(null);
				this.getRefPayment_Type().setPK(null);
				this.getRefPayment_Type().getUITextField().requestFocus();
			}

			this.getBillPanel().appendVO(bvo);

			if (new UFDouble(this.getTxtBalanceMoney().getText(), 2)
					.compareTo(new UFDouble(0)) == 0)// 收款完成
			{
				doSave();
			}

		} catch (Exception e1) {
			e1.printStackTrace();
			MessageDialog.showErrorDlg(this, "错误", e1.getMessage());
		}

	}

	/**
	 * 结算方式为积分折现时检查
	 * 
	 * @return true:能进行积分折现,false:不能进行积分折现
	 */
	private boolean checkDisCash() {
		boolean result = true;
		if (MPOS_GlobalVariable.BALANCETYPE_GRADE_DISCASH.equals(this
				.getRefPayment_Type().getRefCode())) {
			if (StringUtils.isEmpty(this.getRefVIPCard().getRefPK())) {
				MessageDialog.showWarningDlg(this, "警告", "请输入会员卡号！");
				result = false;
			} else if (StringUtils.isEmpty(this.getTxtGradeInput().getText())) {
				MessageDialog.showWarningDlg(this, "警告", "请输入折现积分！");
				result = false;
			} else if (new UFDouble(this.getTxtGradeInput().getText())
					.compareTo(new UFDouble(this.getTxtSumGrade().getText())) > 0) {
				MessageDialog.showWarningDlg(this, "警告", "没有足够的积分折现！");
				result = false;
			}
		}
		return result;
	}

	/**
	 * 计算本次积分
	 * 
	 * @param aggexvo
	 *            聚合类
	 * @return
	 * @throws Exception
	 */
	private MPOS_SaleDetailBillVO calGrade(MPOS_SaleDetailBillVO aggexvo)
			throws Exception {
		MPOS_PubVipItf ivip = NCLocator.getInstance().lookup(
				MPOS_PubVipItf.class);
		aggexvo = ivip.addGrade(aggexvo);
		UFDouble bill_grade = new UFDouble(((MPOS_SaleDetailHeadVO) aggexvo
				.getParentVO()).getBill_grade() == null ? "0"
				: ((MPOS_SaleDetailHeadVO) aggexvo.getParentVO())
						.getBill_grade().toString());
		this.getTxtThisGrade().setText(bill_grade.toString());
		this.getTxtThisSumGrade().setText(
				bill_grade.add(
						new UFDouble(
								this.getTxtSumGrade().getText() == null ? "0"
										: this.getTxtSumGrade().getText()))
						.toString());
		return aggexvo;
	}

	/**
	 * 保存收银明细
	 * 
	 * @throws Exception
	 */
	private void doSave() throws Exception {

		if (this.getBillPanel().getRowCount() <= 0) {
			MessageDialog.showWarningDlg(this, "警告", "没有可保存的交易明细！");
			return;
		}

		/* 构造单据编码 */
		String billcode = MPOS_Toolkit.createBillCode(ce.getCorporation()
				.getPk_corp(), MPOS_GlobalVariable.POS_BILL_CODE);
		if (StringUtils.isEmpty(billcode)) {
			MessageDialog.showErrorDlg(this, "错误", "销售流水号为空！");
			return;
		}
		MPOS_PubManageItf imanage = (MPOS_PubManageItf) NCLocator.getInstance()
				.lookup(MPOS_PubManageItf.class);
		MPOS_SaleDetailHeadVO hvo = new MPOS_SaleDetailHeadVO();
		hvo.setPk_corp(ce.getCorporation().getPk_corp());
		hvo.setBill_date(ClientEnvironment.getServerTime().getDate());
		hvo.setSale_no(billcode);
		hvo.setProof_billno(this.getTxtProof_billno().getText());
		hvo.setSale_type(MPOS_GlobalVariable.SALETYPE_SALE);
		hvo.setPk_cubasdoc(this.getRefCustCode().getRefValue(
				"bd_cubasdoc.pk_cubasdoc").toString());
		hvo.setPk_cashier(ce.getUser().getPrimaryKey());
		hvo.setPk_vip_card(this.getRefVIPCard().getRefPK());
		hvo
				.setReceivable_amount(new UFDouble(this.getTxtReceMoney()
						.getText()));

		MPOS_SaleDetailBillVO aggexvo = new MPOS_SaleDetailBillVO();
		aggexvo.setParentVO(hvo);
		aggexvo.setTableVO(MPOS_SaleDetailBillVO.CHILD_TABLE_DETAIL, this
				.getBillPanel().getBodyValueVOs());
		aggexvo.setTableVO(MPOS_SaleDetailBillVO.CHILD_TABLE_SHARE,
				this.sharevos.toArray(new MPOS_SaleDetailShareVO[sharevos
						.size()]));
		if (StringUtils.isNotEmpty(this.getRefVIPCard().getRefPK()))
			this.calGrade(aggexvo);

		imanage.saveSaleDetailAggExVO(aggexvo);

		resetItemValue();
		// // 打印小票
		// PrintEntry pe = new PrintEntry(this);
		// pe.setTemplateID(ce.getCorporation().getPrimaryKey(),
		// this.modulecode, ce.getUser().getPrimaryKey(), null);
		// pe.setDataSource(new MPOS_BillPrintDS(this.getBillPanel()
		// .getBodyValueVOs()));// 设置数据源
		// // public void print(boolean isShowProgressDlg, boolean
		// // isShowPrintSettingDlg)
		// pe.print(false, false);
	}

	/**
	 * 清空值
	 * 
	 */
	private void resetItemValue() {
		this.getRefCustCode().setEnabled(true);
		getRefCustCode().getUITextField().grabFocus();
		getRefCustCode().setText(null);
		getTxtProof_billno().setText(null);
		getTxtCustName().setText(null);
		getTxtBunkno().setText(null);
		getTxtReceMoney().setText(null);
		getTxtPaidinMoney().setText(null);
		getTxtBalanceMoney().setText(null);
		this.getTxtReceMoney().setEnabled(true);
		this.getTxtProof_billno().setEnabled(true);

		this.getTxtGradeInput().setText(null);
		this.m_custvo = null;
		this.getDisPanel().getBillModel().clearBodyData();
		refVIPCard.getUITextField().setFocusable(false);

		this.getRefVIPCard().setText(null);
		this.getTxtVIPName().setText(null);
		this.getTxtThisGrade().setText(null);
		this.getTxtThisSumGrade().setText(null);
		this.getTxtSumGrade().setText(null);

	}

	/**
	 * @return the billPanel
	 */
	public MPOS_SaleDetailBodyPanel getBillPanel() {
		if (billPanel == null) {
			billPanel = new MPOS_SaleDetailBodyPanel();
			billPanel.getBodyPanel().getTable().setFocusable(false);
		}
		return billPanel;
	}

	/**
	 * 获取会员信息面板
	 * 
	 * @return vipPanel 会员信息面板
	 */
	public UIPanel getVipPanel() {
		if (vipPanel == null) {
			vipPanel = new UIPanel();
			vipPanel.setBounds(505, 10, 270, 238);
			vipPanel.setBorder(BorderFactory.createTitledBorder("会员信息"));
			vipPanel.setLayout(null);

			vipPanel.add(this.getLblVIPCard());
			vipPanel.add(this.getRefVIPCard());

			item_vip_y += item_vip_step;
			vipPanel.add(this.getLblVIPName());
			vipPanel.add(this.getTxtVIPName());

			item_vip_y += item_vip_step;
			vipPanel.add(this.getLblSumGrade());
			vipPanel.add(this.getTxtSumGrade());

			item_vip_y += item_vip_step;
			vipPanel.add(this.getLblThisGrade());
			vipPanel.add(this.getTxtThisGrade());

			item_vip_y += item_vip_step;
			vipPanel.add(this.getLblThisSumGrade());
			vipPanel.add(this.getTxtThisSumGrade());

		}
		return vipPanel;
	}

	/**
	 * 获取lblVIPCard
	 * 
	 * @return lblVIPCard lblVIPCard
	 */
	public UILabel getLblVIPCard() {
		if (lblVIPCard == null) {
			lblVIPCard = new UILabel();
			lblVIPCard.setName("lblVIPCard");
			lblVIPCard.setText("会员卡");
			lblVIPCard.setFont(MPOS_GlobalVariable.font);
			lblVIPCard.setBounds(5, item_vip_y, 100, 28);
		}
		return lblVIPCard;
	}

	/**
	 * 获取refVIPCard
	 * 
	 * @return refVIPCard refVIPCard
	 */
	public UIRefPane getRefVIPCard() {
		if (refVIPCard == null) {
			refVIPCard = new UIRefPane();
			refVIPCard.setName("refVIPCard");
			refVIPCard.setAdjustHight(true);
			refVIPCard.setBounds(110, item_vip_y, 150, 28);
			refVIPCard.getUITextField().setFont(MPOS_GlobalVariable.font);
			refVIPCard.setRefModel(new MPOS_VipcardRefModel());
			refVIPCard.getUITextField().setFocusable(false);
			refVIPCard.getUITextField().setToolTipText("按F3获得焦点");
			refVIPCard.getUITextField().addFocusListener(new FocusAdapter() {
				@Override
				public void focusLost(FocusEvent e) {
					// 失去焦点时使其不能获得焦点
					refVIPCard.getUITextField().setFocusable(false);
					getBtnPanel().getButtonOK().grabFocus();

				}
			});
			refVIPCard.setButtonFireEvent(true);
			refVIPCard.addValueChangedListener(this);
		}
		return refVIPCard;
	}

	/**
	 * 获取lblVIPName
	 * 
	 * @return lblVIPName lblVIPName
	 */
	public UILabel getLblVIPName() {
		if (lblVIPName == null) {
			lblVIPName = new UILabel();
			lblVIPName.setName("lblVIPName");
			lblVIPName.setText("会员姓名");
			lblVIPName.setFont(MPOS_GlobalVariable.font);
			lblVIPName.setBounds(5, item_vip_y, 100, 28);
		}
		return lblVIPName;
	}

	/**
	 * 获取txtVIPName
	 * 
	 * @return txtVIPName txtVIPName
	 */
	public UITextField getTxtVIPName() {
		if (txtVIPName == null) {
			txtVIPName = new UITextField();
			txtVIPName.setName("txtVIPName");
			txtVIPName.setAdjustHight(true);
			txtVIPName.setBounds(110, item_vip_y, 150, 28);
			txtVIPName.setFont(MPOS_GlobalVariable.font);
			txtVIPName.setEditable(false);
			txtVIPName.setFocusable(false);
		}
		return txtVIPName;
	}

	/**
	 * 获取lblSumGrade
	 * 
	 * @return lblSumGrade lblSumGrade
	 */
	public UILabel getLblSumGrade() {
		if (lblSumGrade == null) {
			lblSumGrade = new UILabel();
			lblSumGrade.setName("lblSumGrade");
			lblSumGrade.setText("上次累计");
			lblSumGrade.setFont(MPOS_GlobalVariable.font);
			lblSumGrade.setBounds(5, item_vip_y, 100, 28);
		}
		return lblSumGrade;
	}

	/**
	 * 获取txtSumGrade
	 * 
	 * @return txtSumGrade txtSumGrade
	 */
	public UITextField getTxtSumGrade() {
		if (txtSumGrade == null) {
			txtSumGrade = new UITextField();
			txtSumGrade.setName("txtSumGrade");
			txtSumGrade.setAdjustHight(true);
			txtSumGrade.setBounds(110, item_vip_y, 150, 28);
			txtSumGrade.setFont(MPOS_GlobalVariable.font);
			txtSumGrade.setEditable(false);
			txtSumGrade.setFocusable(false);
			txtSumGrade.setTextType(UITextType.TextDbl);
			txtSumGrade.setNumPoint(2);
		}
		return txtSumGrade;
	}

	public void valueChanged(ValueChangedEvent arg0) {
		if (arg0.getSource() == this.getRefPayment_Type()) {
			getTxtGradeInput().setText(null);
			// 如果结算方式为积分折现
			if (MPOS_GlobalVariable.BALANCETYPE_GRADE_DISCASH
					.equals(getRefPayment_Type().getRefCode())) {
				getTxtPaidinMoney().setFocusable(false);
				getTxtGradeInput().setFocusable(true);
				getTxtGradeInput().grabFocus();
				if (StringUtils.isEmpty(this.getRefVIPCard().getRefPK())) {
					MessageDialog.showWarningDlg(this, "警告", "请输入会员卡号!");
				}
			} else {
				getTxtPaidinMoney().setFocusable(true);
				getTxtGradeInput().setFocusable(false);
			}
		} else if (arg0.getSource() == this.getRefVIPCard()) {
			this.getTxtSumGrade()
					.setText(
							this.getRefVIPCard().getRefValue(
									"mpos_vipdoc.grade") == null ? "0" : this
									.getRefVIPCard().getRefValue(
											"mpos_vipdoc.grade").toString());
		} else if (arg0.getSource() == this.getRefCustCode()) {
			String pk_cubasdoc = this.getRefCustCode().getRefValue(
					"bd_cubasdoc.pk_cubasdoc") == null ? "" : this
					.getRefCustCode().getRefValue("bd_cubasdoc.pk_cubasdoc")
					.toString();
			if (StringUtils.isNotEmpty(pk_cubasdoc)) {
				try {
					MPOS_DiscountTempVO[] vos = iquery
							.queryDiscountTempVO(pk_cubasdoc);
					this.getDisPanel().fillGrid(vos);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 获取lblThisGrade
	 * 
	 * @return lblThisGrade lblThisGrade
	 */
	public UILabel getLblThisGrade() {
		if (lblThisGrade == null) {
			lblThisGrade = new UILabel();
			lblThisGrade.setName("lblThisGrade");
			lblThisGrade.setText("本次积分");
			lblThisGrade.setFont(MPOS_GlobalVariable.font);
			lblThisGrade.setBounds(5, item_vip_y, 100, 28);
		}
		return lblThisGrade;
	}

	/**
	 * 获取lblThisSumGrade
	 * 
	 * @return lblThisSumGrade lblThisSumGrade
	 */
	public UILabel getLblThisSumGrade() {
		if (lblThisSumGrade == null) {
			lblThisSumGrade = new UILabel();
			lblThisSumGrade.setName("lblThisSumGrade");
			lblThisSumGrade.setText("本次累计");
			lblThisSumGrade.setFont(MPOS_GlobalVariable.font);
			lblThisSumGrade.setBounds(5, item_vip_y, 100, 28);
		}
		return lblThisSumGrade;
	}

	/**
	 * 获取txtThisGrade
	 * 
	 * @return txtThisGrade txtThisGrade
	 */
	public UITextField getTxtThisGrade() {
		if (txtThisGrade == null) {
			txtThisGrade = new UITextField();
			txtThisGrade.setName("txtThisGrade");
			txtThisGrade.setAdjustHight(true);
			txtThisGrade.setBounds(110, item_vip_y, 150, 28);
			txtThisGrade.setFont(MPOS_GlobalVariable.font);
			txtThisGrade.setEditable(false);
			txtThisGrade.setFocusable(false);
			txtThisGrade.setTextType(UITextType.TextDbl);
			txtThisGrade.setNumPoint(2);
		}
		return txtThisGrade;
	}

	/**
	 * 获取txtThisSumGrade
	 * 
	 * @return txtThisSumGrade txtThisSumGrade
	 */
	public UITextField getTxtThisSumGrade() {
		if (txtThisSumGrade == null) {
			txtThisSumGrade = new UITextField();
			txtThisSumGrade.setName("txtThisSumGrade");
			txtThisSumGrade.setAdjustHight(true);
			txtThisSumGrade.setBounds(110, item_vip_y, 150, 28);
			txtThisSumGrade.setFont(MPOS_GlobalVariable.font);
			txtThisSumGrade.setEditable(false);
			txtThisSumGrade.setFocusable(false);
			txtThisSumGrade.setTextType(UITextType.TextDbl);
			txtThisSumGrade.setNumPoint(2);
		}
		return txtThisSumGrade;
	}

	/**
	 * 获取disPanel
	 * 
	 * @return disPanel disPanel
	 */
	public MPOS_SaleDiscountBillPanel getDisPanel() {
		if (disPanel == null) {
			disPanel = new MPOS_SaleDiscountBillPanel();
			disPanel.setBounds(780, 10, 400, 238);
			disPanel.setBorder(BorderFactory.createTitledBorder("折扣信息"));
			disPanel.addEditListener(this);
			disPanel.getBodyPanel().getTable().setFocusable(false);
		}
		return disPanel;
	}

	public void afterEdit(BillEditEvent arg0) {
		if (this.getDisPanel().getBodyCheckBoxKey().equals(arg0.getKey())) {
			if (StringUtils.isEmpty(this.getTxtBalanceMoney().getText()))
				return;
			sharevos.clear();

			MPOS_DiscountTempVO[] vos = this.getDisPanel()
					.getBodySelectVOsByCheckBox().toArray(
							new MPOS_DiscountTempVO[0]);
			if (vos == null || vos.length <= 0) {
				this.getTxtBalanceMoney().setText(
						this.getTxtBeDisBalaMoney().getText());
			} else {
				try {
					String value = SysInitBO_Client.getParaString(ce
							.getCorporation().getPk_corp(),
							MPOS_GlobalVariable.PARAM_CODE_DISCALSEQU);
					if (MPOS_GlobalVariable.DISCOUNT_SEQU_FIRSTDIS
							.equals(value)) {
						VOUtil.descSort(vos, new String[] { "discounttype" });
					} else {
						VOUtil.ascSort(vos, new String[] { "discounttype" });
					}
					UFDouble money = new UFDouble(this.getTxtBeDisBalaMoney()
							.getText());

					for (int i = 0; i < vos.length; i++) {

						MPOS_SaleDetailShareVO svo = new MPOS_SaleDetailShareVO();
						svo.setPk_activity(vos[i].getPk_activity());
						svo.setPk_activity_type(vos[i].getPk_activity_type());
						svo.setDiscount_rate(vos[i].getDiscountnum());
						svo.setDiscount_be_amount(money);

						if (MPOS_GlobalVariable.DISCOUNT_TYPE_CUT.equals(vos[i]
								.getDiscounttype()))
							money = money.sub((vos[i].getDiscountnum()));
						else if (MPOS_GlobalVariable.DISCOUNT_TYPE_DISCOUNT
								.equals(vos[i].getDiscounttype()))
							money = money.multiply((vos[i].getDiscountnum()
									.div(100)));

						svo.setDiscount_af_amount(money);
						svo.setDiscount_money(svo.getDiscount_be_amount().sub(
								svo.getDiscount_af_amount()));
						sharevos.add(svo);
					}

					this.getTxtBalanceMoney().setText(money.toString());
				} catch (BusinessException e) {
					MessageDialog.showErrorDlg(this, "错误", e.getMessage());
				}
			}
		}
	}

	public void bodyRowChange(BillEditEvent arg0) {

	}
}
