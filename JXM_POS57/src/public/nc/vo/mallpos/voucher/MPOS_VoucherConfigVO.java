package nc.vo.mallpos.voucher;

import nc.vo.pub.SuperVO;

public class MPOS_VoucherConfigVO extends SuperVO
{

	private static final long serialVersionUID = 1L;
	
	private String pk_voucherconfig;
	private String itemcode;
	private String itemname;
	private String accsubjcode;
	private String accsubjname;

	@Override
	public String getPKFieldName()
	{
		return "pk_voucherconfig";
	}

	@Override
	public String getParentPKFieldName()
	{
		return null;
	}

	@Override
	public String getTableName()
	{
		return "kn_voucherconfig";
	}

	/**
	 * @return the accsubjcode
	 */
	public String getAccsubjcode()
	{
		return accsubjcode;
	}

	/**
	 * @param accsubjcode the accsubjcode to set
	 */
	public void setAccsubjcode(String accsubjcode)
	{
		this.accsubjcode = accsubjcode;
	}

	/**
	 * @return the accsubjname
	 */
	public String getAccsubjname()
	{
		return accsubjname;
	}

	/**
	 * @param accsubjname the accsubjname to set
	 */
	public void setAccsubjname(String accsubjname)
	{
		this.accsubjname = accsubjname;
	}

	/**
	 * @return the itemcode
	 */
	public String getItemcode()
	{
		return itemcode;
	}

	/**
	 * @param itemcode the itemcode to set
	 */
	public void setItemcode(String itemcode)
	{
		this.itemcode = itemcode;
	}

	/**
	 * @return the itemname
	 */
	public String getItemname()
	{
		return itemname;
	}

	/**
	 * @param itemname the itemname to set
	 */
	public void setItemname(String itemname)
	{
		this.itemname = itemname;
	}

	/**
	 * @return the pk_voucherconfig
	 */
	public String getPk_voucherconfig()
	{
		return pk_voucherconfig;
	}

	/**
	 * @param pk_voucherconfig the pk_voucherconfig to set
	 */
	public void setPk_voucherconfig(String pk_voucherconfig)
	{
		this.pk_voucherconfig = pk_voucherconfig;
	}

}
