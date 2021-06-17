package persional.cheneyjin.cloudisk.db;

import org.apache.ibatis.session.SqlSession;


// 用于try块自动回收SqlSession
public class MS implements java.lang.AutoCloseable {
	private SqlSession session;

	public MS() {
		session = DBLink.getSession().openSession();
	}

	public SqlSession getSession() {
		return session;
	}

	@Override
	public void close() throws Exception {
		session.close();
	}

}
