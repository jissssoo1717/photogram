package com.cos.photogramstart.domain.subscribe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SubscribeRepository extends JpaRepository<Subscribe, Integer>{
	
	// DB에 변경을 주는 Native Query에는 Modifying이 필요함.
	@Modifying // INSERT, DELETE, UPDATE를 네이티브 쿼리로 작성하기 위해 해당 어노테이션 필요
	@Query(value = "INSERT INTO subscribe(fromUserId, toUserId, createData) VALUES(:fromUserId, :toUserId, now())",
			nativeQuery = true)
	void mSubscribe(int fromUserId, int toUserId);
	
	
	@Modifying
	@Query(value = "DELETE FROM subscribe WHERE fromUserId = ::fromUserId AND toUserId = :toUserId",
			nativeQuery = true)
	void mUnSubscribe(int fromUserId, int toUserId);

}
