package com.jay.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    public static final String TITLE = "테스트 게시글";
    public static final String CONTENT = "테스트 본문";
    public static final String EMAIL = "kokikiko@knou.ac.kr";
    @Autowired
    PostsRepository postRepository;

    @After
    public void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {

        //given

        Posts posts = Posts.builder()
                .title(TITLE)
                .content(CONTENT)
                .author(EMAIL)
                .build();

        postRepository.save(posts);

        //when
        Posts findPosts = postRepository.findById(posts.getId()).get();

        //then
        assertThat(posts.getTitle()).isEqualTo(findPosts.getTitle());
        assertThat(posts.getContent()).isEqualTo(findPosts.getContent());

    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2023, 4, 25, 0, 0, 0);
        postRepository.save(Posts.builder()
                .title(TITLE)
                .content(CONTENT)
                .author(EMAIL)
                .build());
        //when
        List<Posts> posts = postRepository.findAll();

        //then
        Posts findPosts = posts.get(0);
        System.out.println(">>> createDate = " + findPosts.getCreateDate() + " , modifiedDate = " + findPosts.getModifiedDate());
        assertThat(findPosts.getCreateDate()).isAfter(now);
        assertThat(findPosts.getModifiedDate()).isAfter(now);


    }
}
