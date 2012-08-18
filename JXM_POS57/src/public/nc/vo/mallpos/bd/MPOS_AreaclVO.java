package nc.vo.mallpos.bd;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;

/**
 * 地区分类对象
 * @author cj
 * @date 2012-8-6 下午09:45:40
 */
public class MPOS_AreaclVO extends SuperVO
{

	private static final long serialVersionUID = 1L;

	private String pk_areacl;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "地区编码"))
	private String areacl_code;
	@BillTempletParams(@Param(key = Param.KEY_DEFAULTNAME, value = "地区名称"))
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
	 * 获取areacl_code
	 * 
	 * @return areacl_code areacl_code
	 */
	public String getAreacl_code()
	{
		return areacl_code;
	}

	/**
	 * 设置areacl_code
	 * 
	 * @param areacl_code
	 *            areacl_code
	 */
	public void setAreacl_code(String areacl_code)
	{
		this.areacl_code = areacl_code;
	}

	/**
	 * 获取areacl_name
	 * 
	 * @return areacl_name areacl_name
	 */
	public String getAreacl_name()
	{
		return areacl_name;
	}

	/**
	 * 设置areacl_name
	 * 
	 * @param areacl_name
	 *            areacl_name
	 */
	public void setAreacl_name(String areacl_name)
	{
		this.areacl_name = areacl_name;
	}

	/**
	 * 获取pk_areacl
	 * 
	 * @return pk_areacl pk_areacl
	 */
	public String getPk_areacl()
	{
		return pk_areacl;
	}

	/**
	 * 设置pk_areacl
	 * 
	 * @param pk_areacl
	 *            pk_areacl
	 */
	public void setPk_areacl(String pk_areacl)
	{
		this.pk_areacl = pk_areacl;
	}

	/**
	 * 获取pk_fareacl
	 * 
	 * @return pk_fareacl pk_fareacl
	 */
	public String getPk_fareacl()
	{
		return pk_fareacl;
	}

	/**
	 * 设置pk_fareacl
	 * 
	 * @param pk_fareacl
	 *            pk_fareacl
	 */
	public void setPk_fareacl(String pk_fareacl)
	{
		this.pk_fareacl = pk_fareacl;
	}

}
