package cafe.jjdev.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cafe.jjdev.web.service.Member;
import cafe.jjdev.web.service.MemberDao;
import cafe.jjdev.web.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberDao memberDao;
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/memberList")
	public String memberList(Model model) {
		System.out.println("memberList ��û");
		List<Member> list = memberDao.selectMemberList();
		model.addAttribute("list",list);
		//DB list get
		return "memberList"; //memberList view�� �����ְڴ�! 
	}
	
	@RequestMapping(value="/addMember", method = RequestMethod.POST)
	public String addMember(MemberRequest memberRequest) { //command��ü�̿��ϴ� ���
		System.out.println(memberRequest);
		// DB�Է�
		memberService.addMember(memberRequest);
		return "redirect:/memberList"; //response.sendRedirect("/memberList")�� ����
	}
	
	/*@RequestParam�� �̿��Ͽ� ������ ���� �޴� ��� (�Խ��� ���� ��쿡 Ȱ��)
	 * @RequestMapping(value= "/addMember", method = RequestMethod.POST)
	public String addMember(@RequestParam(value="memberId") String memberId, 
			@RequestParam(value="memberPw") String memberPw, 
			@RequestParam(value="memberName") String memberName) {
		System.out.println("memberId : " + memberId);
		System.out.println("memberPw : " + memberPw);
		System.out.println("memberName : " + memberName);
		return "addMember";
	}*/
	@RequestMapping(value= "/addMember", method = RequestMethod.GET)
	public String addMember() {
		return "addMember";
	}
	
	@RequestMapping("/getMember")
	public String getMember(Model model) {
		Member member = memberDao.selectMemberOne(1);
		model.addAttribute("member", member);		
		return "getMember";
	}
}
