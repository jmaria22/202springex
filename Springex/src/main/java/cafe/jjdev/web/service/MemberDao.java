package cafe.jjdev.web.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao {
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
	//매번 써줘야 되는 번거러움을 해소하기위해 상수화 시킨다.
	private final String NS = "cafe.jjdev.web.service.MemberMapper."; 
	
	public Member login(Member member) {
		return sqlSessionTemplate.selectOne(NS+"login", member);
	}
	
	public List<Member> selectMemberList() {
		return sqlSessionTemplate.selectList(NS+"selectMemberList");
	}
	
	public int insertMember(Member member) {
		return sqlSessionTemplate.insert(NS+"insertMember",member);
	}
	
	public Member selectMemberOne(int memberNo) {
		return sqlSessionTemplate.selectOne(
				"selectMemberOne"
				,memberNo);
				
	}
}
