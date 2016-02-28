package dessert.controller.html;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import dessert.configure.Configure;
import dessert.configure.ErrorCode;
import dessert.controller.HtmlController;
import dessert.rvo.member.CardInfoResultVO;
import dessert.service.MemberService;
import dessert.util.FormValidator;

@Controller("showCardInfo")
public class CardInfoController extends HtmlController {

	private static final long serialVersionUID = 1L;
	@Autowired
	MemberService memberService;

	@Override
	public String execute() throws Exception {
		return controller(response(), request());
	}

	@Override
	public void validate(Map<String, String> params, FormValidator validator) {
		HttpSession session = session();
		String id = (String) session.getAttribute(Configure.ID);// 取得会员ID
		validator.put(Configure.ID, id);
		validator.isRequired(Configure.ID, ErrorCode.ID_IS_EMPTY);
	}

	@Override
	public String process(FormValidator validator) {
		CardInfoResultVO rVo = memberService.getCardInfo(validator.getS(Configure.ID));
		ServletContext sc = request().getServletContext();
		sc.setAttribute(Configure.SUCCESS, rVo.getSuccess());
		sc.setAttribute(Configure.MESSAGE, rVo.getMessage());
		if (rVo.getSuccess() == Configure.SUCCESS_INT) {
			sc.setAttribute(Configure.ID, rVo.getId());
			sc.setAttribute(Configure.BALANCE, rVo.getBalance());
			sc.setAttribute(Configure.STATE, rVo.getState());
			sc.setAttribute(Configure.TOTAL, rVo.getTotal());
			sc.setAttribute(Configure.GRADE, rVo.getGrade());
			sc.setAttribute(Configure.INTEGRAL, rVo.getIntegral());
			sc.setAttribute(Configure.BANKCARD, rVo.getBackCard());
			return Configure.SUCCESS;
		}
		return Configure.ERROR;
	}

}
