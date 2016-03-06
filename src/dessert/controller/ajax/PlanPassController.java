package dessert.controller.ajax;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.AjaxController;
import dessert.rvo.ResultVO;
import dessert.service.PlanService;
import dessert.util.FormValidator;

@Controller("passPlan")
public class PlanPassController extends AjaxController{
	private static final long serialVersionUID = 1L;
	@Autowired
	PlanService planService;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		validator.put(Configure.P_ID, params.get(Configure.P_ID));
		validator.isRequired(Configure.P_ID, ErrorCode.ID_IS_EMPTY);
		
	}

	@Override
	public String process(FormValidator validator) {
		ResultVO rVo=planService.passPlan(validator.getS(Configure.P_ID));
		
		Map<String, Object> map=new HashMap<>();	
		map.put(Configure.SUCCESS, rVo.getSuccess());
		map.put(Configure.MESSAGE, rVo.getMessage());
		setJsonResult(map);
		return Configure.SUCCESS;
	}}
