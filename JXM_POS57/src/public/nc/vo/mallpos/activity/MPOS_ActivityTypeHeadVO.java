package nc.vo.mallpos.activity;

import nc.dev.tools.BillTempletParams;
import nc.dev.tools.Param;
import nc.vo.pub.SuperVO;

public class MPOS_ActivityTypeHeadVO extends SuperVO {
	
	private static final long serialVersionUID = -189393577640738705L;
	
	private String pk_activity_type;
	@BillTempletParams(@Param(key = "defaultName", value = "类型编码"))
	private String typecode;
	@BillTempletParams(@Param(key = "defaultName", value = "类型名称"))
	private String typename;
	@BillTempletParams(@Param(key = "defaultName", value = "适配类"))
	private String adapter;
	@BillTempletParams(@Param(key = "defaultName", value = "参数1"))
	private String param1;
	@BillTempletParams(@Param(key = "defaultName", value = "参数2"))
	private String param2;
	@BillTempletParams(@Param(key = "defaultName", value = "参数3"))
	private String param3;
	@BillTempletParams(@Param(key = "defaultName", value = "参数4"))
	private String param4;
	@BillTempletParams(@Param(key = "defaultName", value = "参数5"))
	private String param5;

	@Override
	public String getPKFieldName() {
		return "pk_activity_type";
	}

	@Override
	public String getParentPKFieldName() {
		return null;
	}

	@Override
	public String getTableName() {
		return "mpos_activity_type";
	}

	public String getPk_activity_type() {
		return pk_activity_type;
	}

	public void setPk_activity_type(String pk_activity_type) {
		this.pk_activity_type = pk_activity_type;
	}

	public String getTypecode() {
		return typecode;
	}

	public void setTypecode(String typecode) {
		this.typecode = typecode;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getAdapter() {
		return adapter;
	}

	public void setAdapter(String adapter) {
		this.adapter = adapter;
	}

	public String getParam1() {
		return param1;
	}

	public void setParam1(String param1) {
		this.param1 = param1;
	}

	public String getParam2() {
		return param2;
	}

	public void setParam2(String param2) {
		this.param2 = param2;
	}

	public String getParam3() {
		return param3;
	}

	public void setParam3(String param3) {
		this.param3 = param3;
	}

	public String getParam4() {
		return param4;
	}

	public void setParam4(String param4) {
		this.param4 = param4;
	}

	public String getParam5() {
		return param5;
	}

	public void setParam5(String param5) {
		this.param5 = param5;
	}

}
