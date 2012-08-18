package nc.vo.mallpos.cost;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDouble;

public class MPOS_CostVO extends SuperVO
{

	private static final long serialVersionUID = 1L;

	private String pk_bussincome;

	private String custcode;

	private String custname;

	private String bunkcode;

	private String bunkname;

	private String billdate;

	private String billtype;

	private String feetype;

	private UFDouble nmoney;

	private String billstate;

	@Override
	public String getPKFieldName()
	{
		return "pk_bussincome";
	}

	@Override
	public String getParentPKFieldName()
	{
		return null;
	}

	@Override
	public String getTableName()
	{
		return "kn_bussincome";
	}

	/**
	 * @return the billdate
	 */
	public String getBilldate()
	{
		return billdate;
	}

	/**
	 * @param billdate
	 *            the billdate to set
	 */
	public void setBilldate(String billdate)
	{
		this.billdate = billdate;
	}

	/**
	 * @return the billstate
	 */
	public String getBillstate()
	{
		return billstate;
	}

	/**
	 * @param billstate
	 *            the billstate to set
	 */
	public void setBillstate(String billstate)
	{
		this.billstate = billstate;
	}

	/**
	 * @return the billtype
	 */
	public String getBilltype()
	{
		return billtype;
	}

	/**
	 * @param billtype
	 *            the billtype to set
	 */
	public void setBilltype(String billtype)
	{
		this.billtype = billtype;
	}

	/**
	 * @return the bunkcode
	 */
	public String getBunkcode()
	{
		return bunkcode;
	}

	/**
	 * @param bunkcode
	 *            the bunkcode to set
	 */
	public void setBunkcode(String bunkcode)
	{
		this.bunkcode = bunkcode;
	}

	/**
	 * @return the bunkname
	 */
	public String getBunkname()
	{
		return bunkname;
	}

	/**
	 * @param bunkname
	 *            the bunkname to set
	 */
	public void setBunkname(String bunkname)
	{
		this.bunkname = bunkname;
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
	 * @return the feetype
	 */
	public String getFeetype()
	{
		return feetype;
	}

	/**
	 * @param feetype
	 *            the feetype to set
	 */
	public void setFeetype(String feetype)
	{
		this.feetype = feetype;
	}

	/**
	 * @return the nmoney
	 */
	public UFDouble getNmoney()
	{
		return nmoney;
	}

	/**
	 * @param nmoney
	 *            the nmoney to set
	 */
	public void setNmoney(UFDouble nmoney)
	{
		this.nmoney = nmoney;
	}

	/**
	 * @return the pk_bussincome
	 */
	public String getPk_bussincome()
	{
		return pk_bussincome;
	}

	/**
	 * @param pk_bussincome
	 *            the pk_bussincome to set
	 */
	public void setPk_bussincome(String pk_bussincome)
	{
		this.pk_bussincome = pk_bussincome;
	}

}
