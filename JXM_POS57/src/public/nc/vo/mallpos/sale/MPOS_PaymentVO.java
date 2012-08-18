package nc.vo.mallpos.sale;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 付款结算单
 * @author chenjun
 * @date 2011-12-21 上午11:32:38
 */
public class MPOS_PaymentVO extends SuperVO
{

	private static final long serialVersionUID = 1L;

	/**
	 * 付款日期
	 */
	private UFDate paydate;

	/**
	 * 商户编码
	 */
	private String custcode;

	/**
	 * 商户名称
	 */
	private String custname;

	/**
	 * 付款金额
	 */
	private UFDouble nmoney;
	
	/**
	 * 付款单据号
	 */
	private String djbh;
	
	/**
	 * 付款结算单主键
	 */
	private String vouchid;
	
	/**
	 * 核对结果
	 */
	private String result;
	
	/**
	 * 是否已生成付款凭证
	 */
	private String zyx2;

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
	 * @return the paydate
	 */
	public UFDate getPaydate()
	{
		return paydate;
	}

	/**
	 * @param paydate
	 *            the paydate to set
	 */
	public void setPaydate(UFDate paydate)
	{
		this.paydate = paydate;
	}

	@Override
	public String getPKFieldName()
	{
		return null;
	}

	@Override
	public String getParentPKFieldName()
	{
		return null;
	}

	@Override
	public String getTableName()
	{
		return null;
	}

	/**
	 * @return the djbh
	 */
	public String getDjbh()
	{
		return djbh;
	}

	/**
	 * @param djbh the djbh to set
	 */
	public void setDjbh(String djbh)
	{
		this.djbh = djbh;
	}

	/**
	 * @return the vouchid
	 */
	public String getVouchid()
	{
		return vouchid;
	}

	/**
	 * @param vouchid the vouchid to set
	 */
	public void setVouchid(String vouchid)
	{
		this.vouchid = vouchid;
	}

	/**
	 * @return the result
	 */
	public String getResult()
	{
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result)
	{
		this.result = result;
	}

	/**
	 * @return the zyx2
	 */
	public String getZyx2()
	{
		return zyx2;
	}

	/**
	 * @param zyx2 the zyx2 to set
	 */
	public void setZyx2(String zyx2)
	{
		this.zyx2 = zyx2;
	}

}
