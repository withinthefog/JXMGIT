package nc.vo.mallpos.bd;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;

/**
 * �����������
 * @author cj
 * @date 2012-8-6 ����09:45:40
 */
public class MPOS_AreaclVO extends SuperVO
{

	private static final long serialVersionUID = 1L;

	private String pk_areacl;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��������"))
	private String areacl_code;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "��������"))
	private String areacl_name;
	private String pk_fareacl;

	@Override
	public String getPKFieldName()
	{
		return "pk_areacl";
	}

	@Override
	public String getParentPKFieldName()
	{
		return "pk_fareacl";
	}

	@Override
	public String getTableName()
	{
		return "mpos_areacl";
	}

	/**
	 * ��ȡareacl_code
	 * 
	 * @return areacl_code areacl_code
	 */
	public String getAreacl_code()
	{
		return areacl_code;
	}

	/**
	 * ����areacl_code
	 * 
	 * @param areacl_code
	 *            areacl_code
	 */
	public void setAreacl_code(String areacl_code)
	{
		this.areacl_code = areacl_code;
	}

	/**
	 * ��ȡareacl_name
	 * 
	 * @return areacl_name areacl_name
	 */
	public String getAreacl_name()
	{
		return areacl_name;
	}

	/**
	 * ����areacl_name
	 * 
	 * @param areacl_name
	 *            areacl_name
	 */
	public void setAreacl_name(String areacl_name)
	{
		this.areacl_name = areacl_name;
	}

	/**
	 * ��ȡpk_areacl
	 * 
	 * @return pk_areacl pk_areacl
	 */
	public String getPk_areacl()
	{
		return pk_areacl;
	}

	/**
	 * ����pk_areacl
	 * 
	 * @param pk_areacl
	 *            pk_areacl
	 */
	public void setPk_areacl(String pk_areacl)
	{
		this.pk_areacl = pk_areacl;
	}

	/**
	 * ��ȡpk_fareacl
	 * 
	 * @return pk_fareacl pk_fareacl
	 */
	public String getPk_fareacl()
	{
		return pk_fareacl;
	}

	/**
	 * ����pk_fareacl
	 * 
	 * @param pk_fareacl
	 *            pk_fareacl
	 */
	public void setPk_fareacl(String pk_fareacl)
	{
		this.pk_fareacl = pk_fareacl;
	}

}
