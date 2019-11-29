package com.ram.test001;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//현재 테스트 코드를 실행할 때 스프링이 로딩 되도록 하는 부분.
@RunWith(SpringJUnit4ClassRunner.class)
//속성 경로에 xml파일을 이용해 스프링이 로딩 된다.
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/**/root-context.xml" })

public class MybatisTest {
    //위에서 스프링이 정상적으로 동작할 경우 스프링이 생성해서 주입해준다.
    //root-context.xml 파일에서 설정한 SqlSesiionFactoryBean설정에 문제가 있었다면 이 코드에서 에러가 발생한다.
	@Inject
	private SqlSessionFactory sqlFactory;

	@Test
	public void testFactory() {
		System.out.println(sqlFactory);
	}

	@Test
	public void tesSession() throws Exception {
		try (SqlSession session = sqlFactory.openSession()) {
			System.out.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}






