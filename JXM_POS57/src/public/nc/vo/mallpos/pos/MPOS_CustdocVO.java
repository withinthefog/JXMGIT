package nc.vo.mallpos.pos;

import nc.vo.pub.SuperVO;

/**
 * 客商档案
 * 
 * @author chenjun
 * @date 2011-8-8 下午03:18:06
 */
public class MPOS_CustdocVO extends SuperVO
{

	private static final long serialVersionUID = 1L;

	private String custcode;

	private String custname;

	private String pk_areacl;

	private String pk_cubasdoc;
	
	private String bunkno;//铺位号


	@Override
	public String getPKFieldName()
	{
		return "";
	}

	@Override
	public String getParentPKFieldName()
	{
		return null;
	}

	@Override
	public String getTableName()
	{
		return "";
	}

	/**
	 * @return the custcode
	 */
	public String getCustcode()
	{
		return custcode;
	}

	/**
	 * @param custcode
	 *            the custcode to set
	 */
	public void setCustcode(String custcode)
	{
		this.custcode = custcode;
	}

	/**
	 * @return the custname
	 */
	public String getCustname()
	{
		return custname;
	}

	/**
	 * @param custname
	 *            the custname to set
	 */
	public void setCustname(String custname)
	{
		this.custname = custname;
	}

	/**
	 * @return the pk_areacl
	 */
	public String getPk_areacl()
	{
		return pk_areacl;
	}

	/**
	 * @param pk_areacl
	 *            the pk_areacl to set
	 */
	public void setPk_areacl(String pk_areacl)
	{
		this.pk_areacl = pk_areacl;
	}

	/**
	 * @return the pk_cubasdoc
	 */
	public String getPk_cubasdoc()
	{
		return pk_cubasdoc;
	}

	/**
	 * @param pk_cubasdoc
	 *            the pk_cubasdoc to set
	 */
	public void setPk_cubasdoc(String pk_cubasdoc)
	{
		this.pk_cubasdoc = pk_cubasdoc;
	}

	/**
	 * @return the bunkno
	 */
	public String getBunkno()
	{
		return bunkno;
	}

	/**
	 * @param bunkno the bunkno to set
	 */
	public void setBunkno(String bunkno)
	{
		this.bunkno = bunkno;
	}

}
