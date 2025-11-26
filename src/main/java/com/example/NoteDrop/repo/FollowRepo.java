// package com.example.NoteDrop.repo;

// import com.example.NoteDrop.entity.Follow;
// import com.example.NoteDrop.entity.User;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.data.repository.query.Param;

// import java.util.List;

// public interface FollowRepo extends JpaRepository<Follow, Integer> {
//     boolean existsByFollowerAndFollowed(User follower, User followed);

//     ///  can be made simpler but for later
//     @Query("SELECT f.followed.username FROM Follow f WHERE f.follower = :follower")
//     List<String> findFollowedUsernamesByFollower(@Param("follower") User follower);

//     @Query("SELECT f.follower.username FROM Follow f WHERE f.followed.username = :username")
//     List<String> findFollowersUsernamesByUsername(@Param("username") String username);

//     @Query("SELECT f.followed.username FROM Follow f WHERE f.follower.username = :username")
//     List<String> findFollowedUsernamesByUsername(@Param("username") String username);
// }


package com.example.NoteDrop.repo;

import com.example.NoteDrop.entity.Follow;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepo extends MongoRepository<Follow, String> {
    boolean existsByFollowerUsernameAndFollowedUsername(String followerUsername, String followedUsername);
    
    List<Follow> findByFollowerUsername(String followerUsername);
    
    List<Follow> findByFollowedUsername(String followedUsername);
}