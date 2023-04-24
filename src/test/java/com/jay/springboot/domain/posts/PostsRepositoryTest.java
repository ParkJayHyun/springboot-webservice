package com.jay.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postRepository;

    @After
    public void cleanUp() {
        postRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {

        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        Posts posts = Posts.builder()
                .title(title)
                .content(content)
                .author("kokikiko@knou.ac.kr")
                .build();

        postRepository.save(posts);

        //when
        Posts findPosts = postRepository.findById(posts.getId()).get();

        //then
        assertThat(posts.getTitle()).isEqualTo(findPosts.getTitle());
        assertThat(posts.getContent()).isEqualTo(findPosts.getContent());

    }
}
