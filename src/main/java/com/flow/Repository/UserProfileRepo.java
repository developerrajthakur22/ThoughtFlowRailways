package com.flow.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.flow.Entity.UserProfile;

import jakarta.transaction.Transactional;

public interface UserProfileRepo extends JpaRepository<UserProfile, Long> {

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM user_profile_following WHERE following_id = ?1", nativeQuery = true)
	void deleteFollowingRelationships(long userProfileId);

	@Transactional
	@Modifying
	@Query(value = "DELETE FROM user_profile_followers WHERE followers_id = ?1", nativeQuery = true)
	void deleteFollowerRelationships(long userProfileId);

	@Query("Select u from UserProfile u where name like %:keyword% OR username like %:keyword%")
	List<UserProfile> searchUserByName(String keyword);
	
}
