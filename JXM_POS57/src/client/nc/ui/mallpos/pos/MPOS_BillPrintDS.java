package nc.ui.mallpos.pos;

import nc.ui.pub.print.IDataSource;
import nc.vo.mallpos.pos.MPOS_SaleDetailBodyVO;
import nc.vo.mallpos.pos.MPOS_SaleDetailHeadVO;
import nc.vo.pub.SuperVO;

/**
 * 收银小票打印
 * 
 * @author chenjun
 * @date 2012-1-5 下午10:53:11
 */
public class MPOS_BillPrintDS implements IDataSource
{

	private static final long serialVersionUID = 1L;

	private MPOS_SaleDetailHeadVO h_vo;

	private MPOS_SaleDetailBodyVO[] b_vos;

	public MPOS_BillPrintDS(MPOS_SaleDetailBodyVO[] p_vos)
	{
		super();
		this.b_vos = p_vos;
		if (b_vos != null && b_vos.length > 0)
		{
			h_vo = (MPOS_SaleDetailHeadVO) b_vos[0].clone();
			for (int i = 1; i < b_vos.length; i++)
			{
				//h_vo.setSalemoney(h_vo.getSalemoney().add(b_vos[i].getSalemoney()));
			}
		}
	}

	public String[] getAllDataItemExpress()
	{
		return null;
	}

	public String[] getAllDataItemNames()
	{
		return null;
	}

	public String[] getDependentItemExpressByExpress(String arg0)
	{
		return null;
	}

	public String[] getItemValuesByExpress(String p_itemExpress)
	{
		if (b_vos == null)
			return null;
		String m_itemExpress = p_itemExpress.toLowerCase();
		String[] result = null;

		if (m_itemExpress.startsWith("h_"))
		{
			result = new String[1];
			result[0] = h_vo.getAttributeValue(m_itemExpress.substring(2, m_itemExpress.length())) == null ? "" : h_vo.getAttributeValue(m_itemExpress.substring(2, m_itemExpress.length())).toString();
		} else if (m_itemExpress.startsWith("b_"))
		{
			String[] names = b_vos[0].getAttributeNames();
			for (int xxx = 0; xxx < names.length; xxx++)
			{
				if (names[xxx].equals(m_itemExpress.substring(2, m_itemExpress.length())))
				{
					result = this.createPrintValues(b_vos, m_itemExpress.substring(2, m_itemExpress.length()));
					return result;
				}
			}
		}
		return result;
	}

	/**
	 * 创建打印表体
	 * 
	 * @param p_vos
	 * @param p_valuename
	 * @return
	 */
	public String[] createPrintValues(SuperVO[] p_vos, String p_valuename)
	{
		String[] result = new String[p_vos.length];

		for (int xxx = 0; xxx < result.length; xxx++)
		{
			result[xxx] = p_vos[xxx].getAttributeValue(p_valuename) == null ? "" : p_vos[xxx].getAttributeValue(p_valuename).toString();
		}

		return result;
	}

	public String getModuleName()
	{
		return null;
	}

	public boolean isNumber(String arg0)
	{
		return false;
	}

}
