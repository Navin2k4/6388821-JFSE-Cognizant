package com.cognizant.jwtapp.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Integer> {
    List<Blog> findByAuthorId(Integer authorId);

    @Query("SELECT b FROM " +
            "Blog b " +
            "WHERE LOWER(b.title) LIKE %:keyword% " +
            "OR LOWER(b.content) LIKE %:keyword%")
    List<Blog> searchByKeyword(@Param("keyword") String keyword);
}
