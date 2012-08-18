package nc.ui.mallpos.pub;

import java.awt.Dimension;
import java.awt.event.KeyEvent;

import nc.ui.pub.beans.UIButton;
import nc.ui.pub.beans.UIPanel;

/**
 * 查询对话框按扭
 * @author chenjun
 * @date 2011-3-7 下午01:38:18
 */
public class MPOS_BtnPanel extends UIPanel {
	
	private static final long serialVersionUID = 1L;

	private UIPanel m_ButtonPanel;

	private UIButton m_BtnOK;

	private UIButton m_BtnExit;
	
	/**
	 * 宽度
	 * <br>根据对话框宽度确定
	 */
	private int width;

	public MPOS_BtnPanel(int width) {
		super();
		this.setWidth(width);
		initPanel();
	}

	public void initPanel() {
		add(getButtonPanel(), "Center");
	}

	public UIPanel getButtonPanel() {
		if (m_ButtonPanel == null) {
			m_ButtonPanel = new UIPanel();
			m_ButtonPanel.setLayout(null);
			m_ButtonPanel.setPreferredSize(new Dimension(this.getWidth(), 30));
			m_ButtonPanel.add(getButtonOK());
			m_ButtonPanel.add(getButtonExit());
		}

		return m_ButtonPanel;
	}

	public UIButton getButtonOK() {
		if (m_BtnOK == null) {
			m_BtnOK = new UIButton();
			m_BtnOK.setText("确定(O)");
			m_BtnOK.setName("m_BtnOK");
			m_BtnOK.setBounds(this.getWidth()-160, 3, 70, 20);
			m_BtnOK.setRequestFocusEnabled(true);
			m_BtnOK.setMnemonic(KeyEvent.VK_O);//快捷键Alt+O
		}

		return m_BtnOK;
	}

	public UIButton getButtonExit() {
		if (m_BtnExit == null) {
			m_BtnExit = new UIButton();
			m_BtnExit.setText("取消(C)");
			m_BtnExit.setName("m_BtnExit");
			m_BtnExit.setBounds(this.getWidth()-80, 3, 70, 20);
			m_BtnExit.setRequestFocusEnabled(true);
			m_BtnExit.setMnemonic(KeyEvent.VK_C);//快捷键Alt+C
		}

		return m_BtnExit;
	}
	
	/**
	 * @return the width
	 */
	public int getWidth()
	{
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width)
	{
		this.width = width;
	}

}
